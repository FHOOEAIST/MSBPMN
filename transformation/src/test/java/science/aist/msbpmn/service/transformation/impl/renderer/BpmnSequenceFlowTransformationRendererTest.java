/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer;

import org.hl7.fhir.r4.model.PlanDefinition;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TBaseElement;
import org.omg.spec.bpmn.model.TSequenceFlow;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.GraphState;
import science.aist.gtf.graph.impl.EdgeImpl;
import science.aist.gtf.graph.impl.GraphStateImpl;
import science.aist.gtf.graph.impl.VertexImpl;
import science.aist.msbpmn.service.transformation.helper.IdProvider;

import javax.xml.bind.JAXBElement;
import java.util.Optional;

/**
 * <p>Test class for {@link BpmnSequenceFlowTransformationRenderer}</p>
 *
 * @author Andreas Pointner
 */

public class BpmnSequenceFlowTransformationRendererTest {
    private final BpmnSequenceFlowTransformationRenderer renderer = new BpmnSequenceFlowTransformationRenderer(new ObjectFactory(), () -> t -> true);

    @Test
    public void testCanRendererPos() {
        // given
        var source = new VertexImpl<Object, Void>(new PlanDefinition.PlanDefinitionActionComponent());
        var target = new VertexImpl<Object, Void>(new PlanDefinition.PlanDefinitionActionComponent());
        var edge = new EdgeImpl<Object, Void>(null);
        GraphState<Object, Void> graphState = new GraphStateImpl<>();
        graphState.addVertex(source);
        graphState.addVertex(target);
        graphState.addEdge(edge, source, target);

        // when
        boolean b = renderer.canRenderer(edge);

        // then
        Assert.assertTrue(b);
    }

    @Test
    public void testCanRendererNeg() {
        // given
        var source = new VertexImpl<Object, Void>(new Object());
        var target = new VertexImpl<Object, Void>(new Object());
        var edge = new EdgeImpl<Object, Void>(null);
        GraphState<Object, Void> graphState = new GraphStateImpl<>();
        graphState.addVertex(source);
        graphState.addVertex(target);
        graphState.addEdge(edge, source, target);

        // when
        boolean b = renderer.canRenderer(edge);

        // then
        Assert.assertFalse(b);
    }

    @Test
    public void testRenderElementPos() {
        // given
        BpmnSequenceFlowTransformationRenderer spy = Mockito.spy(renderer);
        Mockito.doReturn(true).when(spy).canRenderer(ArgumentMatchers.any());
        Mockito.doReturn(new TSequenceFlow()).when(spy).mapProperties(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());

        // when
        Optional<JAXBElement<? extends TSequenceFlow>> jaxbElement = spy.renderElement(null, null);

        // then
        Assert.assertTrue(jaxbElement.isPresent());
    }

    @Test
    public void testRenderElementNeg() {
        // given
        BpmnSequenceFlowTransformationRenderer spy = Mockito.spy(renderer);
        Mockito.doReturn(false).when(spy).canRenderer(ArgumentMatchers.any());

        // when
        Optional<JAXBElement<? extends TSequenceFlow>> jaxbElement = spy.renderElement(null, null);

        // then
        Assert.assertFalse(jaxbElement.isPresent());
    }

    @Test
    public void testCreateElement() {
        // given
        // when
        TSequenceFlow element = renderer.createElement();

        // then
        Assert.assertNotNull(element);
    }

    @Test
    public void testMapProperties() {
        // given
        var edge = new EdgeImpl<PlanDefinition.PlanDefinitionActionComponent, Void>(null);
        PlanDefinition.PlanDefinitionActionComponent action1 = new PlanDefinition.PlanDefinitionActionComponent();
        action1.setId("action1");
        var source = new VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void>(action1);
        PlanDefinition.PlanDefinitionActionComponent action2 = new PlanDefinition.PlanDefinitionActionComponent();
        action2.setId("action2");
        var target = new VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void>(action2);
        GraphState<PlanDefinition.PlanDefinitionActionComponent, Void> graphState = new GraphStateImpl<>();
        graphState.addVertex(source);
        graphState.addVertex(target);
        graphState.addEdge(edge, source, target);

        // when
        TSequenceFlow tSequenceFlow = renderer.mapProperties(new TSequenceFlow(), null, edge);

        // then
        Assert.assertNotNull(tSequenceFlow);
        Assert.assertNotNull(tSequenceFlow.getSourceRef());
        Assert.assertNotNull(tSequenceFlow.getTargetRef());
        Assert.assertEquals(((TBaseElement) tSequenceFlow.getSourceRef()).getId(), new IdProvider(action1.getId()).getId());
        Assert.assertEquals(((TBaseElement) tSequenceFlow.getTargetRef()).getId(), new IdProvider(action2.getId()).getId());
    }

    @Test
    public void testProcessConditions() {
        // given
        var edge = new EdgeImpl<PlanDefinition.PlanDefinitionActionComponent, Void>(null);
        PlanDefinition.PlanDefinitionActionComponent action1 = new PlanDefinition.PlanDefinitionActionComponent();
        action1.setId("action1");
        var source = new VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void>(action1);
        PlanDefinition.PlanDefinitionActionComponent action2 = new PlanDefinition.PlanDefinitionActionComponent();
        action2.setId("action2");
        action2.getConditionFirstRep().getExpression().setExpression("myExpression");
        var target = new VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void>(action2);
        GraphState<PlanDefinition.PlanDefinitionActionComponent, Void> graphState = new GraphStateImpl<>();
        graphState.addVertex(source);
        graphState.addVertex(target);
        graphState.addEdge(edge, source, target);

        TSequenceFlow tSequenceFlow = new TSequenceFlow();

        // when
        renderer.processConditions(tSequenceFlow, edge);

        // then
        Assert.assertNotNull(tSequenceFlow.getConditionExpression());
        Assert.assertNotNull(tSequenceFlow.getConditionExpression().getContent());
        Assert.assertEquals(tSequenceFlow.getConditionExpression().getContent().size(), 1);
        Assert.assertEquals(tSequenceFlow.getConditionExpression().getContent().get(0), "myExpression");
    }
}