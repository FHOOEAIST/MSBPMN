/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer;

import at.fh.hagenberg.aist.msbpmn.service.transformation.helper.IdProvider;
import at.fh.hagenberg.aist.msbpmn.service.transformation.renderer.AbstractBpmnGraphTransformationRenderer;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TEndEvent;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.GraphState;
import science.aist.gtf.graph.impl.AbstractVertex;
import science.aist.gtf.graph.impl.GraphStateImpl;
import science.aist.gtf.graph.impl.VertexImpl;

import javax.xml.bind.JAXBElement;
import java.util.Optional;

/**
 * <p>Test class for {@link BpmnEndEventTransformerRenderer}</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */

public class BpmnEndEventTransformerRendererTest {

    @Test
    public void testCreateElement() {
        // given
        AbstractBpmnGraphTransformationRenderer<TEndEvent, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnEndEventTransformerRenderer(() -> n -> true, new ObjectFactory());

        // when
        TEndEvent endEvent = renderer.createElement();

        // then
        Assert.assertNotNull(endEvent);
    }

    @Test
    public void testMapProperties() {
        // given
        AbstractBpmnGraphTransformationRenderer<TEndEvent, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnEndEventTransformerRenderer(() -> n -> true, new ObjectFactory());

        PlanDefinition.PlanDefinitionActionComponent action = new PlanDefinition.PlanDefinitionActionComponent();
        action.setTitle("testTitle");
        action.setId("testId");


        AbstractVertex<PlanDefinition.PlanDefinitionActionComponent, Void> vertex = new VertexImpl<>(action);
        GraphState<PlanDefinition.PlanDefinitionActionComponent, Void> gs = new GraphStateImpl<>();
        gs.addVertex(vertex);

        // when
        TEndEvent endEvent = renderer.mapProperties(renderer.createElement(), null, vertex);

        // then
        Assert.assertNotNull(endEvent);
        Assert.assertEquals(endEvent.getId(), new IdProvider(action.getId()).getId());
        Assert.assertEquals(endEvent.getName(), action.getTitle());
        Assert.assertNull(endEvent.getAuditing());
        Assert.assertNull(endEvent.getMonitoring());
        Assert.assertNull(endEvent.getInputSet());
    }


    @Test
    public void testRenderElementPositive() {
        // given
        AbstractBpmnGraphTransformationRenderer<TEndEvent, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnEndEventTransformerRenderer(() -> n -> true, new ObjectFactory());
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionComponentObjectVertex =
                new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());
        GraphState<PlanDefinition.PlanDefinitionActionComponent, Void> gs = new GraphStateImpl<>();
        gs.addVertex(planDefinitionActionComponentObjectVertex);

        // when
        Optional<JAXBElement<? extends TEndEvent>> endEvent = renderer.renderElement(null, planDefinitionActionComponentObjectVertex);

        // then
        Assert.assertTrue(endEvent.isPresent());
    }

    @Test
    public void testRenderElementNegative() {
        // given
        AbstractBpmnGraphTransformationRenderer<TEndEvent, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnEndEventTransformerRenderer(() -> n -> false, new ObjectFactory());

        // when
        Optional<JAXBElement<? extends TEndEvent>> endEvent = renderer.renderElement(null, new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent()));

        // then
        Assert.assertTrue(endEvent.isEmpty());
    }

    @Test
    public void testCanRendererPos() {
        // given
        AbstractBpmnGraphTransformationRenderer<TEndEvent, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnEndEventTransformerRenderer(() -> n -> false, new ObjectFactory());
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());

        // when
        boolean b = renderer.canRenderer(planDefinitionActionIOComponentVertex);

        // then
        Assert.assertTrue(b);
    }

    @Test
    public void testCanRendererNeg() {
        // given
        AbstractBpmnGraphTransformationRenderer<TEndEvent, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnEndEventTransformerRenderer(() -> n -> false, new ObjectFactory());
        VertexImpl<Object, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new Object());

        // when
        boolean b = renderer.canRenderer(planDefinitionActionIOComponentVertex);

        // then
        Assert.assertFalse(b);
    }
}