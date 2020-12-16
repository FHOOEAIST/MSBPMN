/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer;

import at.fh.hagenberg.aist.msbpmn.service.transformation.TransformationConstants;
import at.fh.hagenberg.aist.msbpmn.service.transformation.helper.IdProvider;
import at.fh.hagenberg.aist.msbpmn.service.transformation.helper.PlanDefinitionActionDataComponent;
import at.fh.hagenberg.aist.msbpmn.service.transformation.impl.EdgeType;
import at.fh.hagenberg.aist.msbpmn.service.transformation.renderer.AbstractBpmnGraphTransformationRenderer;
import lombok.CustomLog;
import lombok.NonNull;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.DataRequirement;
import org.hl7.fhir.r4.model.PlanDefinitionActionIOComponent;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TDataObjectReference;
import org.omg.spec.bpmn.model.TExtensionElements;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.jack.general.util.CastUtils;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * <p>Renderer for the BPMN Data Object Reference element</p>
 *
 * @author Andreas Pointner
 */
@Component
@CustomLog
public class BpmnDataObjectReferenceTransformerRenderer extends AbstractBpmnGraphTransformationRenderer<TDataObjectReference, BackboneElement, PlanDefinitionActionIOComponent> {

    private static final String FHIR_EXTENSION_NAMESPACE = "http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir";

    public BpmnDataObjectReferenceTransformerRenderer(ObjectFactory objectFactory) {
        // No condition needed, as this renderer is executed for every vertex where the decorated element is of type PlanDefinitionActionIOComponent
        super(() -> x -> true, objectFactory);
    }

    @Override
    protected Function<TDataObjectReference, JAXBElement<? extends TDataObjectReference>> constructJaxBElementMapping() {
        return objectFactory::createDataObjectReference;
    }

    @Override
    public boolean canRenderer(Vertex<?, ?> vertex) {
        return vertex != null && vertex.getElement() instanceof PlanDefinitionActionIOComponent;
    }

    @Override
    public TDataObjectReference createElement() {
        return objectFactory.createTDataObjectReference();
    }

    @Override
    public TDataObjectReference mapProperties(@NonNull TDataObjectReference element, Graph<BackboneElement, Void> container, @NonNull Vertex<PlanDefinitionActionIOComponent, Void> currentElement) {
        element = super.mapProperties(element, container, currentElement);
        element.setName(currentElement.getElement().getName());
        element.setExtensionElements(createExtensionElementForRequirement(currentElement.getElement().getDataRequirement()));

        // Set the data reference
        CastUtils.<Stream<Edge<PlanDefinitionActionIOComponent, Void>>, Stream<Edge<PlanDefinitionActionDataComponent, Void>>>cast(currentElement.getEdges()
                .stream()
                .filter(BpmnDataObjectReferenceTransformerRenderer::isDataEdge))
                .findFirst()
                .map(Edge::getTarget)
                .map(Vertex::getElement)
                .map(BackboneElement::getId)
                .map(IdProvider::new)
                .ifPresent(element::setDataObjectRef);
        return element;
    }

    private static boolean isDataEdge(Edge<?, Void> edge) {
        try {
            return edge.getMetaTagValue(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.class) == EdgeType.DATA;
        } catch (Exception e) {
            log.debug(e);
            return false;
        }
    }

    private TExtensionElements createExtensionElementForRequirement(DataRequirement dr) {
        TExtensionElements tee = objectFactory.createTExtensionElements();
        // check in the future what else is necessary to map to bpmn
        tee.getAny().addAll(fromMap(Map.of(
                "type", dr.getType()
        )));
        return tee;
    }

    private static List<JAXBElement<String>> fromMap(Map<String, String> map) {
        List<JAXBElement<String>> result = new ArrayList<>();
        map.forEach((k, v) -> result.add(new JAXBElement<>(new QName(FHIR_EXTENSION_NAMESPACE, k), String.class, v)));
        return result;
    }
}
