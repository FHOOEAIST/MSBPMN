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
import at.fh.hagenberg.aist.msbpmn.service.transformation.impl.EdgeType;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.omg.spec.bpmn.model.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.builder.impl.GraphBuilderImpl;
import science.aist.gtf.graph.impl.AbstractVertex;
import science.aist.gtf.graph.impl.GraphStateImpl;
import science.aist.gtf.graph.impl.MetaTagImpl;
import science.aist.gtf.graph.impl.VertexImpl;
import science.aist.gtf.transformation.renderer.TransformationRender;
import science.aist.jack.general.util.CastUtils;

import javax.xml.bind.JAXBElement;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * <p>Test class for {@link BpmnSubprocessTransformationRenderer}</p>
 *
 * @author Andreas Pointner
 */

public class BpmnSubprocessTransformationRendererTest {

    @Test
    public void testCreateElement() {
        // given
        BpmnSubprocessTransformationRenderer renderer = new BpmnSubprocessTransformationRenderer(null, new ObjectFactory(), null, null);

        // when
        TSubProcess element = renderer.createElement();

        // then
        Assert.assertNotNull(element);
    }

    @Test
    public void testMapProperties() {
        // given
        ObjectFactory objectFactory = new ObjectFactory();
        TransformationRender<JAXBElement<? extends TFlowElement>, ?, Graph<BackboneElement, Void>, Vertex<BackboneElement, Void>> elementRenderer = Mockito.mock(TransformationRender.class);
        TransformationRender<Optional<JAXBElement<? extends TSequenceFlow>>, TSequenceFlow, Graph<BackboneElement, Void>, Edge<PlanDefinition.PlanDefinitionActionComponent, Void>> edgeRenderer = Mockito.mock(TransformationRender.class);
        Mockito.when(elementRenderer.renderElement(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(CastUtils.cast(objectFactory.createUserTask(new TUserTask())));
        Mockito.when(edgeRenderer.renderElement(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(Optional.of(objectFactory.createSequenceFlow(new TSequenceFlow())));

        var pd1 = new PlanDefinition.PlanDefinitionActionComponent();
        var pd2 = new PlanDefinition.PlanDefinitionActionComponent();
        var pd3 = new PlanDefinition.PlanDefinitionActionComponent();
        var graphBuilder = GraphBuilderImpl.<BackboneElement, Void>create()
                .from(pd1).toWith(pd2).with(e -> e.addMetaTag(new MetaTagImpl<>(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.SEQUENCE)))
                .from(pd2).toWith(pd3).with(e -> e.addMetaTag(new MetaTagImpl<>(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.SEQUENCE)));

        Graph<BackboneElement, Void> graph = graphBuilder.toGraph();
        graph.addMetaTag(new MetaTagImpl<>(TransformationConstants.START_NODE_META_TAG, List.of(graphBuilder.getOrAddVertex(pd1))));

        AbstractVertex<PlanDefinition.PlanDefinitionActionComponent, Void> currentElement = new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());
        currentElement.addMetaTag(new MetaTagImpl<>(TransformationConstants.SUBPROCESS_META_TAG, graph));
        new GraphStateImpl<PlanDefinition.PlanDefinitionActionComponent, Void>().addVertex(currentElement);

        BpmnSubprocessTransformationRenderer renderer = new BpmnSubprocessTransformationRenderer(
                () -> t -> true, new ObjectFactory(), elementRenderer, edgeRenderer);

        // when
        TSubProcess tSubProcess = renderer.mapProperties(new TSubProcess(), null, currentElement);

        // then
        Assert.assertNotNull(tSubProcess);
        Assert.assertNotNull(tSubProcess.getFlowElement());
        Assert.assertEquals(tSubProcess.getFlowElement().size(), 5);
    }

    @Test
    public void testConstructJaxBElementMapping() {
        // given
        BpmnSubprocessTransformationRenderer renderer = new BpmnSubprocessTransformationRenderer(null, new ObjectFactory(), null, null);

        // when
        Function<TSubProcess, JAXBElement<? extends TSubProcess>> tSubProcessJAXBElementFunction = renderer.constructJaxBElementMapping();

        // then
        Assert.assertNotNull(tSubProcessJAXBElementFunction);
        Assert.assertNotNull(tSubProcessJAXBElementFunction.apply(new TSubProcess()));
    }

    @Test
    public void testCanRendererPos() {
        // given
        BpmnSubprocessTransformationRenderer renderer = new BpmnSubprocessTransformationRenderer(null, null, null, null);
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());

        // when
        boolean b = renderer.canRenderer(planDefinitionActionIOComponentVertex);

        // then
        Assert.assertTrue(b);
    }

    @Test
    public void testCanRendererNeg() {
        // given
        BpmnSubprocessTransformationRenderer renderer = new BpmnSubprocessTransformationRenderer(null, null, null, null);
        VertexImpl<Object, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new Object());

        // when
        boolean b = renderer.canRenderer(planDefinitionActionIOComponentVertex);

        // then
        Assert.assertFalse(b);
    }
}