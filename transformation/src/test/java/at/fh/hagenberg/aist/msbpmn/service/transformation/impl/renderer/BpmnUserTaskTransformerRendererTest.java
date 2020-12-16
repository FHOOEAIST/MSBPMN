/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer;

import at.fh.hagenberg.aist.msbpmn.service.transformation.renderer.AbstractBpmnGraphTransformationRenderer;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TUserTask;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.GraphState;
import science.aist.gtf.graph.impl.GraphStateImpl;
import science.aist.gtf.graph.impl.VertexImpl;

import javax.xml.bind.JAXBElement;
import java.util.Optional;
import java.util.function.Function;

/**
 * <p>Test class for {@link BpmnUserTaskTransformerRenderer}</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */

public class BpmnUserTaskTransformerRendererTest {

    @Test
    public void testCreateElement() {
        // given
        AbstractBpmnGraphTransformationRenderer<TUserTask, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnUserTaskTransformerRenderer(() -> n -> true, new ObjectFactory());

        // when
        TUserTask userTask = renderer.createElement();

        // then
        Assert.assertNotNull(userTask);
    }

    @Test
    public void testMapProperties1() {
        // given
        AbstractBpmnGraphTransformationRenderer<TUserTask, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnUserTaskTransformerRenderer(() -> n -> true, new ObjectFactory());
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionComponentVertex =
                new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());
        GraphState<PlanDefinition.PlanDefinitionActionComponent, Void> gs = new GraphStateImpl<>();
        gs.addVertex(planDefinitionActionComponentVertex);

        // when
        TUserTask userTask = renderer.mapProperties(renderer.createElement(), null, planDefinitionActionComponentVertex);

        // then
        Assert.assertNotNull(userTask);
    }

    @Test
    public void testRenderElementPositive() {
        // given
        AbstractBpmnGraphTransformationRenderer<TUserTask, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnUserTaskTransformerRenderer(() -> n -> true, new ObjectFactory());
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionComponentVertex =
                new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());
        GraphState<PlanDefinition.PlanDefinitionActionComponent, Void> gs = new GraphStateImpl<>();
        gs.addVertex(planDefinitionActionComponentVertex);

        // when
        Optional<JAXBElement<? extends TUserTask>> userTask = renderer.renderElement(null, planDefinitionActionComponentVertex);

        // then
        Assert.assertTrue(userTask.isPresent());
    }

    @Test
    public void testRenderElementNegative() {
        // given
        AbstractBpmnGraphTransformationRenderer<TUserTask, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnUserTaskTransformerRenderer(() -> n -> false, new ObjectFactory());

        // when
        Optional<JAXBElement<? extends TUserTask>> userTask = renderer.renderElement(null, null);

        // then
        Assert.assertTrue(userTask.isEmpty());
    }

    @Test
    public void testCanRendererPos() {
        // given
        AbstractBpmnGraphTransformationRenderer<TUserTask, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnUserTaskTransformerRenderer(() -> n -> false, new ObjectFactory());
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());

        // when
        boolean b = renderer.canRenderer(planDefinitionActionIOComponentVertex);

        // then
        Assert.assertTrue(b);
    }

    @Test
    public void testCanRendererNeg() {
        // given
        AbstractBpmnGraphTransformationRenderer<TUserTask, BackboneElement, PlanDefinition.PlanDefinitionActionComponent>
                renderer = new BpmnUserTaskTransformerRenderer(() -> n -> false, new ObjectFactory());
        VertexImpl<Object, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new Object());

        // when
        boolean b = renderer.canRenderer(planDefinitionActionIOComponentVertex);

        // then
        Assert.assertFalse(b);
    }

    @Test
    public void testConstructJaxBElementMapping() {
        // given
        BpmnUserTaskTransformerRenderer renderer = new BpmnUserTaskTransformerRenderer(() -> n -> false, new ObjectFactory());

        // when
        Function<TUserTask, JAXBElement<? extends TUserTask>> tTimerEventDefinitionJAXBElementFunction = renderer.constructJaxBElementMapping();

        // then
        Assert.assertNotNull(tTimerEventDefinitionJAXBElementFunction);
        Assert.assertNotNull(tTimerEventDefinitionJAXBElementFunction.apply(new TUserTask()));
    }
}