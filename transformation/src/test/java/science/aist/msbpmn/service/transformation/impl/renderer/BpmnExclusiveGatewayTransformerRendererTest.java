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
import org.omg.spec.bpmn.model.TExclusiveGateway;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.GraphState;
import science.aist.gtf.graph.impl.GraphStateImpl;
import science.aist.gtf.graph.impl.VertexImpl;

import javax.xml.bind.JAXBElement;
import java.util.Optional;

/**
 * <p>Test class for {@link BpmnExclusiveGatewayTransformerRenderer}</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */
public class BpmnExclusiveGatewayTransformerRendererTest {

    @Test
    public void testCreateElement() {
        // given
        AbstractBpmnGraphTransformationRenderer<TExclusiveGateway, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnExclusiveGatewayTransformerRenderer(() -> n -> true, new ObjectFactory());

        // when
        TExclusiveGateway exclusiveGateway = renderer.createElement();

        // then
        Assert.assertNotNull(exclusiveGateway);
    }

    @Test
    public void testRenderElementPositive() {
        // given
        AbstractBpmnGraphTransformationRenderer<TExclusiveGateway, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnExclusiveGatewayTransformerRenderer(() -> n -> true, new ObjectFactory());
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionComponentObjectVertex =
                new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());
        GraphState<PlanDefinition.PlanDefinitionActionComponent, Void> gs = new GraphStateImpl<>();
        gs.addVertex(planDefinitionActionComponentObjectVertex);

        // when
        Optional<JAXBElement<? extends TExclusiveGateway>> exclusiveGateway = renderer.renderElement(null, planDefinitionActionComponentObjectVertex);

        // then
        Assert.assertTrue(exclusiveGateway.isPresent());
    }

    @Test
    public void testMapProperties1() {
        // given
        AbstractBpmnGraphTransformationRenderer<TExclusiveGateway, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnExclusiveGatewayTransformerRenderer(() -> n -> true, new ObjectFactory());
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionComponentObjectVertex =
                new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());
        GraphState<PlanDefinition.PlanDefinitionActionComponent, Void> gs = new GraphStateImpl<>();
        gs.addVertex(planDefinitionActionComponentObjectVertex);

        // when
        TExclusiveGateway exclusiveGateway = renderer.mapProperties(renderer.createElement(), null, planDefinitionActionComponentObjectVertex);

        // then
        Assert.assertNotNull(exclusiveGateway);
    }

    @Test
    public void testRenderElementNegative() {
        // given
        AbstractBpmnGraphTransformationRenderer<TExclusiveGateway, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnExclusiveGatewayTransformerRenderer(() -> n -> false, new ObjectFactory());

        // when
        Optional<JAXBElement<? extends TExclusiveGateway>> exclusiveGateway = renderer.renderElement(null, new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent()));

        // then
        Assert.assertTrue(exclusiveGateway.isEmpty());
    }

    @Test
    public void testCanRendererPos() {
        // given
        BpmnExclusiveGatewayTransformerRenderer
                renderer = new BpmnExclusiveGatewayTransformerRenderer(() -> n -> false, new ObjectFactory());
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());

        // when
        boolean b = renderer.canRenderer(planDefinitionActionIOComponentVertex);

        // then
        Assert.assertTrue(b);
    }

    @Test
    public void testCanRendererNeg() {
        // given
        BpmnExclusiveGatewayTransformerRenderer
                renderer = new BpmnExclusiveGatewayTransformerRenderer(() -> n -> false, new ObjectFactory());
        VertexImpl<Object, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new Object());

        // when
        boolean b = renderer.canRenderer(planDefinitionActionIOComponentVertex);

        // then
        Assert.assertFalse(b);
    }

}