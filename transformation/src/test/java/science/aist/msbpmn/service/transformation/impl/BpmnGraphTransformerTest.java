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
import science.aist.msbpmn.service.transformation.helper.BackboneIdProvider;
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
import science.aist.gtf.graph.impl.MetaTagImpl;
import science.aist.gtf.transformation.renderer.TransformationRender;

import javax.xml.bind.JAXBElement;
import java.util.List;
import java.util.Optional;

import static science.aist.msbpmn.service.transformation.TransformationConstants.PROCESS_NAME_TAG;

/**
 * <p>Test class for {@link BpmnGraphTransformer}</p>
 *
 * @author Andreas Pointner
 */

public class BpmnGraphTransformerTest {

    @SuppressWarnings("unchecked") // Mockito + generics does not work so well
    @Test
    public void testApplyTransformation() {
        // given
        ObjectFactory objectFactory = new ObjectFactory();

        var graphBuilder = GraphBuilderImpl.<BackboneElement, Void>create();

        var participantsColl = graphBuilder.getOrAddVertex(new BackboneIdProvider("participants_elem"));
        participantsColl.addMetaTag(new MetaTagImpl<>(TransformationConstants.PARTICIPANTS_COLL_META_TAG, true));
        var action1 = graphBuilder.getOrAddVertex(new PlanDefinition.PlanDefinitionActionComponent());
        var action2 = graphBuilder.getOrAddVertex(new PlanDefinition.PlanDefinitionActionComponent());
        var action3 = graphBuilder.getOrAddVertex(new PlanDefinition.PlanDefinitionActionComponent());

        graphBuilder.from(action1.getElement()).toWith(action2.getElement()).with(e -> e.addMetaTag(new MetaTagImpl<>(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.SEQUENCE)))
                .from(action2.getElement()).toWith(action3.getElement()).with(e -> e.addMetaTag(new MetaTagImpl<>(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.SEQUENCE)));

        var graph = graphBuilder.toGraph();

        graph.addMetaTag(new MetaTagImpl<>(TransformationConstants.START_NODE_META_TAG, List.of(action1)));
        graph.addMetaTag(new MetaTagImpl<>(PROCESS_NAME_TAG, "processname"));

        TransformationRender<JAXBElement<? extends TFlowNode>, ?, Graph<BackboneElement, Void>, Vertex<BackboneElement, Void>> elementRenderer = Mockito.mock(TransformationRender.class);
        Mockito.doReturn(objectFactory.createUserTask(new TUserTask())).when(elementRenderer).renderElement(ArgumentMatchers.any(), ArgumentMatchers.any());

        TransformationRender<Optional<JAXBElement<? extends TSequenceFlow>>, TSequenceFlow, Graph<BackboneElement, Void>, Edge<PlanDefinition.PlanDefinitionActionComponent, Void>> edgeRenderer = Mockito.mock(TransformationRender.class);
        Mockito.doReturn(Optional.of(objectFactory.createSequenceFlow(new TSequenceFlow()))).when(edgeRenderer).renderElement(ArgumentMatchers.any(), ArgumentMatchers.any());

        TransformationRender<TLaneSet, ?, Graph<BackboneElement, Void>, Vertex<BackboneElement, Void>> tLaneSetGraphVertexTransformationRender = Mockito.mock(TransformationRender.class);
        Mockito.doReturn(new TLaneSet()).when(tLaneSetGraphVertexTransformationRender).renderElement(ArgumentMatchers.any(), ArgumentMatchers.any());

        BpmnGraphTransformer bpmnGraphTransformer = new BpmnGraphTransformer(objectFactory, elementRenderer, edgeRenderer, tLaneSetGraphVertexTransformationRender);

        // when
        JAXBElement<TDefinitions> tDefinitionsJAXBElement = bpmnGraphTransformer.applyTransformation(graph);

        // then
        Assert.assertNotNull(tDefinitionsJAXBElement);
        TDefinitions tDefinitions = tDefinitionsJAXBElement.getValue();
        Assert.assertNotNull(tDefinitions);
        Assert.assertNotNull(tDefinitions.getRootElement());
        Assert.assertEquals(tDefinitions.getRootElement().size(), 1);
        TProcess value = (TProcess)((JAXBElement<?>) tDefinitions.getRootElement().get(0)).getValue();
        Assert.assertNotNull(value);
        Assert.assertEquals(value.getFlowElement().size(), 5);
        Assert.assertEquals(value.getLaneSet().size(), 1);
        Mockito.verify(elementRenderer, Mockito.times(1)).renderElement(ArgumentMatchers.eq(graph), ArgumentMatchers.eq(action1));
        Mockito.verify(elementRenderer, Mockito.times(1)).renderElement(ArgumentMatchers.eq(graph), ArgumentMatchers.eq(action2));
        Mockito.verify(elementRenderer, Mockito.times(1)).renderElement(ArgumentMatchers.eq(graph), ArgumentMatchers.eq(action3));
        Mockito.verify(edgeRenderer, Mockito.times(2)).renderElement(ArgumentMatchers.eq(graph), ArgumentMatchers.any(Edge.class));
        Mockito.verify(tLaneSetGraphVertexTransformationRender, Mockito.times(1)).renderElement(ArgumentMatchers.eq(graph), ArgumentMatchers.eq(participantsColl));
        Mockito.verifyNoMoreInteractions(elementRenderer, edgeRenderer, tLaneSetGraphVertexTransformationRender);
    }

}