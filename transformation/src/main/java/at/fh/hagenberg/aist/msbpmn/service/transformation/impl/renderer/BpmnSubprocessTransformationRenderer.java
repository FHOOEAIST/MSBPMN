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
import at.fh.hagenberg.aist.msbpmn.service.transformation.impl.traversal.FhirGraphTraversalStrategy;
import at.fh.hagenberg.aist.msbpmn.service.transformation.renderer.AbstractTActivityBpmnGraphTransformationRenderer;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TFlowElement;
import org.omg.spec.bpmn.model.TSequenceFlow;
import org.omg.spec.bpmn.model.TSubProcess;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.TransformationRender;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;
import science.aist.jack.general.util.CastUtils;

import javax.xml.bind.JAXBElement;
import java.util.Optional;
import java.util.function.Function;

/**
 * <p>Renderer to create a Subprocess</p>
 *
 * @author Andreas Pointner
 */
@Component
public class BpmnSubprocessTransformationRenderer extends AbstractTActivityBpmnGraphTransformationRenderer<TSubProcess, BackboneElement, PlanDefinition.PlanDefinitionActionComponent> {

    private final TransformationRender<JAXBElement<? extends TFlowElement>, ?, Graph<BackboneElement, Void>, Vertex<BackboneElement, Void>> elementRenderer;
    private final TransformationRender<Optional<JAXBElement<? extends TSequenceFlow>>, TSequenceFlow, Graph<BackboneElement, Void>, Edge<PlanDefinition.PlanDefinitionActionComponent, Void>> edgeRenderer;

    public BpmnSubprocessTransformationRenderer(@Qualifier("subprocessCondition") RendererCondition<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> rendererCondition,
                                                ObjectFactory objectFactory,
                                                @Lazy TransformationRender<JAXBElement<? extends TFlowElement>, ?, Graph<BackboneElement, Void>, Vertex<BackboneElement, Void>> elementRenderer,
                                                TransformationRender<Optional<JAXBElement<? extends TSequenceFlow>>, TSequenceFlow, Graph<BackboneElement, Void>, Edge<PlanDefinition.PlanDefinitionActionComponent, Void>> edgeRenderer) {
        super(rendererCondition, objectFactory);
        this.elementRenderer = elementRenderer;
        this.edgeRenderer = edgeRenderer;
    }

    @Override
    public TSubProcess createElement() {
        return objectFactory.createTSubProcess();
    }

    @Override
    public TSubProcess mapProperties(TSubProcess processParam, Graph<BackboneElement, Void> container, Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> currentElement) {
        final TSubProcess process = super.mapProperties(processParam, container, currentElement);

        Graph<BackboneElement, Void> graph = CastUtils.cast(currentElement.getMetaTagValue(TransformationConstants.SUBPROCESS_META_TAG, Graph.class));
        graph.setVertexTraversalStrategy(new FhirGraphTraversalStrategy<>(graph));
        graph.traverseEdges(
                vertex -> process.getFlowElement().add(elementRenderer.renderElement(graph, vertex)),
                edge -> edgeRenderer.renderElement(graph, CastUtils.cast(edge)).ifPresent(process.getFlowElement()::add)
        );

        return process;
    }

    @Override
    protected Function<TSubProcess, JAXBElement<? extends TSubProcess>> constructJaxBElementMapping() {
        return objectFactory::createSubProcess;
    }

    @Override
    public boolean canRenderer(Vertex<?, ?> vertex) {
        return vertex != null && vertex.getElement() instanceof PlanDefinition.PlanDefinitionActionComponent;
    }
}
