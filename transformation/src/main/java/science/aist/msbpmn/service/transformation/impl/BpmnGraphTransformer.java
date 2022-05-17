/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl;

import science.aist.msbpmn.service.transformation.TransformationConstants;
import science.aist.msbpmn.service.transformation.impl.traversal.FhirGraphTraversalStrategy;
import lombok.AllArgsConstructor;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.omg.spec.bpmn.model.*;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.GraphTransformer;
import science.aist.gtf.transformation.renderer.TransformationRender;
import science.aist.jack.general.util.CastUtils;

import javax.xml.bind.JAXBElement;
import java.util.Optional;

/**
 * <p>Transforms a given graph representation of a process to a BPMN process diagram.</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */
@Component
@AllArgsConstructor
public class BpmnGraphTransformer implements GraphTransformer<BackboneElement, Void, JAXBElement<TDefinitions>> {

    private static final String BPMN_AIST_MSBPMN_DEFAULT_NS = "http://aist.fh-hagenberg.at/msbpmn";

    private final ObjectFactory objectFactory;
    private final TransformationRender<JAXBElement<? extends TFlowNode>, ?, Graph<BackboneElement, Void>, Vertex<BackboneElement, Void>> elementRenderer;
    private final TransformationRender<Optional<JAXBElement<? extends TSequenceFlow>>, TSequenceFlow, Graph<BackboneElement, Void>, Edge<PlanDefinition.PlanDefinitionActionComponent, Void>> edgeRenderer;
    private final TransformationRender<TLaneSet, ?, Graph<BackboneElement, Void>, Vertex<BackboneElement, Void>> tLaneSetGraphVertexTransformationRender;

    @Override
    public JAXBElement<TDefinitions> applyTransformation(Graph<BackboneElement, Void> graph) {
        TDefinitions definitions = objectFactory.createTDefinitions();
        definitions.setTargetNamespace(BPMN_AIST_MSBPMN_DEFAULT_NS);

        TProcess process = objectFactory.createTProcess();
        process.setName(graph.getMetaTagValue(TransformationConstants.PROCESS_NAME_TAG));
        process.setId(graph.getMetaTagValue(TransformationConstants.PROCESS_NAME_TAG));
        process.setIsExecutable(false); // I guess we would need some more specific bpmn structure to make it executable

        graph.setVertexTraversalStrategy(new FhirGraphTraversalStrategy<>(graph));

        // Rendering the Lane sets
        graph.getVertices()
                .stream()
                .filter(v -> v.tryGetMetaTagValue(TransformationConstants.PARTICIPANTS_COLL_META_TAG).isPresent())
                .map(v -> tLaneSetGraphVertexTransformationRender.renderElement(graph, v))
                .forEach(process.getLaneSet()::add);

        // Traverse Sequence Flow
        graph.traverseEdges(
                vertex -> process.getFlowElement().add(elementRenderer.renderElement(graph, vertex)),
                edge -> edgeRenderer.renderElement(graph, CastUtils.cast(edge)).ifPresent(process.getFlowElement()::add)
        );

        definitions.getRootElement().add(objectFactory.createProcess(process));

        return objectFactory.createDefinitions(definitions);
    }
}
