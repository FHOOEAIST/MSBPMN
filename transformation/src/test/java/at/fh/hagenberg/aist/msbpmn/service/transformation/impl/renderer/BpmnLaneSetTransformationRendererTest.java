/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer;

import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.PlanDefinitionActorComponent;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TLane;
import org.omg.spec.bpmn.model.TLaneSet;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.GraphState;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.builder.impl.GraphBuilderImpl;
import science.aist.gtf.graph.impl.GraphStateImpl;
import science.aist.gtf.graph.impl.VertexImpl;
import science.aist.gtf.transformation.renderer.TransformationRender;

/**
 * <p>Test class for {@link BpmnLaneSetTransformationRenderer}</p>
 *
 * @author Andreas Pointner
 */

public class BpmnLaneSetTransformationRendererTest {

    @Test
    public void testRenderElement() {
        // given
        BpmnLaneSetTransformationRenderer bpmnLaneSetTransformationRenderer = new BpmnLaneSetTransformationRenderer(new ObjectFactory(), null);
        VertexImpl<BackboneElement, Void> planDefinitionActionComponentVertex =
                new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());
        GraphState<BackboneElement, Void> gs = new GraphStateImpl<>();
        gs.addVertex(planDefinitionActionComponentVertex);

        // when
        TLaneSet tLaneSet = bpmnLaneSetTransformationRenderer.renderElement(null, planDefinitionActionComponentVertex);

        // then
        Assert.assertNotNull(tLaneSet);
    }

    @Test
    public void testCreateElement() {
        // given
        BpmnLaneSetTransformationRenderer bpmnLaneSetTransformationRenderer = new BpmnLaneSetTransformationRenderer(new ObjectFactory(), null);

        // when
        TLaneSet element = bpmnLaneSetTransformationRenderer.createElement();

        // then
        Assert.assertNotNull(element);
    }

    @Test
    public void testMapProperties() {
        // given
        TransformationRender<TLane, TLane, Graph<BackboneElement, Void>, Vertex<PlanDefinitionActorComponent, Void>> tLaneTLaneGraphVertexTransformationRender =
                Mockito.mock(TransformationRender.class);
        Mockito.when(tLaneTLaneGraphVertexTransformationRender.renderElement(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(new TLane());
        BpmnLaneSetTransformationRenderer bpmnLaneSetTransformationRenderer = new BpmnLaneSetTransformationRenderer(new ObjectFactory(), tLaneTLaneGraphVertexTransformationRender);
        PlanDefinition.PlanDefinitionActionComponent planDefinitionActionDataComponent = new PlanDefinition.PlanDefinitionActionComponent();

        GraphBuilder<BackboneElement, Void> graphBuilder = GraphBuilderImpl.create();
        graphBuilder.from(planDefinitionActionDataComponent).to(new PlanDefinitionActorComponent())
                .from(planDefinitionActionDataComponent).to(new PlanDefinitionActorComponent());


        // when
        TLaneSet tLaneSet = bpmnLaneSetTransformationRenderer.renderElement(graphBuilder.toGraph(), graphBuilder.getOrAddVertex(planDefinitionActionDataComponent));

        // then
        Assert.assertNotNull(tLaneSet);
        Assert.assertNotNull(tLaneSet.getLane());
        Assert.assertEquals(tLaneSet.getLane().size(), 2);
    }
}