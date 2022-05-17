/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer;

import science.aist.msbpmn.service.transformation.renderer.AbstractBpmnGraphTransformationRenderer;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TParallelGateway;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.GraphState;
import science.aist.gtf.graph.impl.GraphStateImpl;
import science.aist.gtf.graph.impl.VertexImpl;

import javax.xml.bind.JAXBElement;
import java.util.Optional;

/**
 * <p>Test class for {@link BpmnParallelGatewayTransformerRenderer}</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */
public class BpmnParallelGatewayTransformerRendererTest {

    @Test
    public void testCreateElement() {
        // given
        AbstractBpmnGraphTransformationRenderer<TParallelGateway, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnParallelGatewayTransformerRenderer(() -> n -> true, new ObjectFactory());

        // when
        TParallelGateway parallelGateway = renderer.createElement();

        // then
        Assert.assertNotNull(parallelGateway);
    }

    @Test
    public void testMapProperties1() {
        // given
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionComponentVertex =
                new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());
        GraphState<PlanDefinition.PlanDefinitionActionComponent, Void> gs = new GraphStateImpl<>();
        gs.addVertex(planDefinitionActionComponentVertex);
        AbstractBpmnGraphTransformationRenderer<TParallelGateway, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnParallelGatewayTransformerRenderer(() -> n -> true, new ObjectFactory());

        // when
        TParallelGateway parallelGateway = renderer.mapProperties(renderer.createElement(), null, planDefinitionActionComponentVertex);

        // then
        Assert.assertNotNull(parallelGateway);
    }

    @Test
    public void testRenderElementPositive() {
        // given
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionComponentVertex =
                new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());
        GraphState<PlanDefinition.PlanDefinitionActionComponent, Void> gs = new GraphStateImpl<>();
        gs.addVertex(planDefinitionActionComponentVertex);
        AbstractBpmnGraphTransformationRenderer<TParallelGateway, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnParallelGatewayTransformerRenderer(() -> n -> true, new ObjectFactory());

        // when
        Optional<JAXBElement<? extends TParallelGateway>> parallelGateway = renderer.renderElement(null, planDefinitionActionComponentVertex);

        // then
        Assert.assertTrue(parallelGateway.isPresent());
    }

    @Test
    public void testRenderElementNegative() {
        // given
        AbstractBpmnGraphTransformationRenderer<TParallelGateway, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnParallelGatewayTransformerRenderer(() -> n -> false, new ObjectFactory());

        // when
        Optional<JAXBElement<? extends TParallelGateway>> parallelGateway = renderer.renderElement(null, null);

        // then
        Assert.assertTrue(parallelGateway.isEmpty());
    }

    @Test
    public void testCanRendererPos() {
        // given
        BpmnParallelGatewayTransformerRenderer
                renderer = new BpmnParallelGatewayTransformerRenderer(() -> n -> false, new ObjectFactory());
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());

        // when
        boolean b = renderer.canRenderer(planDefinitionActionIOComponentVertex);

        // then
        Assert.assertTrue(b);
    }

    @Test
    public void testCanRendererNeg() {
        // given
        BpmnParallelGatewayTransformerRenderer
                renderer = new BpmnParallelGatewayTransformerRenderer(() -> n -> false, new ObjectFactory());
        VertexImpl<Object, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new Object());

        // when
        boolean b = renderer.canRenderer(planDefinitionActionIOComponentVertex);

        // then
        Assert.assertFalse(b);
    }
}