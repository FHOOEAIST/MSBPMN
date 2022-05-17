/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer;

import science.aist.msbpmn.service.transformation.helper.IdProvider;
import science.aist.msbpmn.service.transformation.renderer.AbstractBpmnGraphTransformationRenderer;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TStartEvent;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.GraphState;
import science.aist.gtf.graph.impl.GraphStateImpl;
import science.aist.gtf.graph.impl.VertexImpl;

import javax.xml.bind.JAXBElement;
import java.util.Optional;

/**
 * <p>Test class for {@link BpmnStartEventTransformerRenderer}</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */
public class BpmnStartEventTransformerRendererTest {

    @Test
    public void testCreateElement() {
        // given
        AbstractBpmnGraphTransformationRenderer<TStartEvent, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnStartEventTransformerRenderer(() -> n -> true, new ObjectFactory());

        // when
        TStartEvent startEvent = renderer.createElement();

        // then
        Assert.assertNotNull(startEvent);
    }

    @Test
    public void testMapProperties() {
        // given
        AbstractBpmnGraphTransformationRenderer<TStartEvent, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnStartEventTransformerRenderer(() -> n -> true, new ObjectFactory());

        PlanDefinition.PlanDefinitionActionComponent action = new PlanDefinition.PlanDefinitionActionComponent();
        action.setTitle("testTitle");
        action.setId("testId");

        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> vertex =
                new VertexImpl<>(action);
        GraphState<PlanDefinition.PlanDefinitionActionComponent, Void> gs = new GraphStateImpl<>();
        gs.addVertex(vertex);

        // when
        TStartEvent startEvent = renderer.mapProperties(renderer.createElement(), null, vertex);

        // then
        Assert.assertNotNull(startEvent);
        Assert.assertEquals(startEvent.getId(), new IdProvider(action.getId()).getId());
        Assert.assertEquals(startEvent.getName(), action.getTitle());
        Assert.assertNull(startEvent.getAuditing());
        Assert.assertNull(startEvent.getMonitoring());
        Assert.assertFalse(startEvent.isIsInterrupting());
        Assert.assertNull(startEvent.getOutputSet());
    }


    @Test
    public void testRenderElementPositive() {
        // given
        AbstractBpmnGraphTransformationRenderer<TStartEvent, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnStartEventTransformerRenderer(() -> n -> true, new ObjectFactory());
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionComponentVertex =
                new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());
        GraphState<PlanDefinition.PlanDefinitionActionComponent, Void> gs = new GraphStateImpl<>();
        gs.addVertex(planDefinitionActionComponentVertex);

        // when
        Optional<JAXBElement<? extends TStartEvent>> startEvent = renderer.renderElement(null, planDefinitionActionComponentVertex);

        // then
        Assert.assertTrue(startEvent.isPresent());
    }

    @Test
    public void testRenderElementNegative() {
        // given
        AbstractBpmnGraphTransformationRenderer<TStartEvent, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnStartEventTransformerRenderer(() -> n -> false, new ObjectFactory());

        // when
        Optional<JAXBElement<? extends TStartEvent>> startEvent = renderer.renderElement(null, null);

        // then
        Assert.assertTrue(startEvent.isEmpty());
    }

    @Test
    public void testCanRendererPos() {
        // given
        BpmnStartEventTransformerRenderer
                renderer = new BpmnStartEventTransformerRenderer(() -> n -> false, new ObjectFactory());
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());

        // when
        boolean b = renderer.canRenderer(planDefinitionActionIOComponentVertex);

        // then
        Assert.assertTrue(b);
    }

    @Test
    public void testCanRendererNeg() {
        // given
        BpmnStartEventTransformerRenderer
                renderer = new BpmnStartEventTransformerRenderer(() -> n -> false, new ObjectFactory());
        VertexImpl<Object, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new Object());

        // when
        boolean b = renderer.canRenderer(planDefinitionActionIOComponentVertex);

        // then
        Assert.assertFalse(b);
    }

}