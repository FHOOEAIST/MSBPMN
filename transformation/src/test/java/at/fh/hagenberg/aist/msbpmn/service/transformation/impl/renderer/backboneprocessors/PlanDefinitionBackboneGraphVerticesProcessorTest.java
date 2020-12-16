/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer.backboneprocessors;

import at.fh.hagenberg.aist.msbpmn.service.transformation.TransformationConstants;
import at.fh.hagenberg.aist.msbpmn.service.transformation.impl.EdgeType;
import org.hl7.fhir.instance.model.api.IBaseElement;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.Element;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.builder.impl.GraphBuilderImpl;
import science.aist.gtf.graph.factory.GraphFactory;
import science.aist.gtf.graph.factory.GraphFactoryFactory;
import science.aist.gtf.graph.impl.MetaTagImpl;
import science.aist.gtf.transformation.renderer.TransformationRender;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static at.fh.hagenberg.aist.msbpmn.service.transformation.TransformationConstants.EDGE_TYPE_META_TAG;
import static at.fh.hagenberg.aist.msbpmn.service.transformation.TransformationConstants.SUBPROCESS_META_TAG;

/**
 * <p>Test class for {@link PlanDefinitionBackboneGraphVerticesProcessor}</p>
 *
 * @author Andreas Pointner
 */

public class PlanDefinitionBackboneGraphVerticesProcessorTest {

    private final GraphFactory defaultFactory = GraphFactoryFactory.getDefaultFactory();
    private final BiConsumer<Edge<?, Void>, EdgeType> edgeTypeConsumer =
            (e, t) -> e.addMetaTag(defaultFactory.createMetaTag(EDGE_TYPE_META_TAG, t));

    private final Consumer<Edge<BackboneElement, Void>> edgeSequenceConsumer = e -> edgeTypeConsumer.accept(e,
            EdgeType.SEQUENCE);

    /**
     * Process:
     * <pre>
     * action1 -&gt; action2
     * </pre>
     */
    @Test
    public void testNormalProcess() {
        // given
        PlanDefinition planDefinition = new PlanDefinition();
        PlanDefinition.PlanDefinitionActionComponent action1 = planDefinition.addAction();
        action1.setId("action1");
        action1.addRelatedAction().setActionId("action2").setRelationship(PlanDefinition.ActionRelationshipType.BEFORESTART);
        PlanDefinition.PlanDefinitionActionComponent action2 = planDefinition.addAction();
        action2.setId("action2");


        PlanDefinitionBackboneGraphVerticesProcessor processor =
                new PlanDefinitionBackboneGraphVerticesProcessor(null, defaultFactory, edgeTypeConsumer,
                        edgeSequenceConsumer);
        GraphBuilder<BackboneElement, Void> graphBuilder =
                GraphBuilderImpl.create(TransformationConstants.KEY_MAPPER(), defaultFactory);

        // when
        processor.process(planDefinition, planDefinition.getAction(), graphBuilder);

        // then
        Graph<BackboneElement, Void> graph = graphBuilder.toGraph();
        Assert.assertNotNull(graph);
        Assert.assertEquals(graph.getVertices().size(), 2);
        Optional<Vertex<BackboneElement, Void>> action1VertexOpt =
                graph.getVertices().stream().filter(v -> v.getElement() == action1).findFirst();
        Optional<Vertex<BackboneElement, Void>> action2VertexOpt =
                graph.getVertices().stream().filter(v -> v.getElement() == action2).findFirst();
        Assert.assertTrue(action1VertexOpt.isPresent());
        Assert.assertTrue(action2VertexOpt.isPresent());
        Assert.assertTrue(action1VertexOpt.get().getOutgoingEdges().stream().map(Edge::getTarget).anyMatch(v -> v == action2VertexOpt.get()));
    }

    /**
     * Process:
     * <pre>
     *             action2
     *           /         \
     * action 1 -           - action4
     *           \         /
     *             action3
     * </pre>
     */
    @Test
    public void testXorProcess() {
        // given
        PlanDefinition planDefinition = new PlanDefinition();
        PlanDefinition.PlanDefinitionActionComponent action1 = planDefinition.addAction();
        action1.setId("action1");
        action1.addRelatedAction().setActionId("xor").setRelationship(PlanDefinition.ActionRelationshipType.BEFORESTART);
        PlanDefinition.PlanDefinitionActionComponent xor = planDefinition.addAction();
        xor.setId("xor");
        xor.setSelectionBehavior(PlanDefinition.ActionSelectionBehavior.EXACTLYONE).setGroupingBehavior(PlanDefinition.ActionGroupingBehavior.LOGICALGROUP);
        PlanDefinition.PlanDefinitionActionComponent action2 = xor.addAction();
        action2.setId("action2");
        action2.addRelatedAction().setActionId("xor").setRelationship(PlanDefinition.ActionRelationshipType.BEFOREEND);
        PlanDefinition.PlanDefinitionActionComponent action3 = xor.addAction();
        action3.setId("action3");
        action3.addRelatedAction().setActionId("xor").setRelationship(PlanDefinition.ActionRelationshipType.BEFOREEND);
        xor.addRelatedAction().setActionId("action4").setRelationship(PlanDefinition.ActionRelationshipType.BEFORESTART);
        PlanDefinition.PlanDefinitionActionComponent action4 = planDefinition.addAction();
        action4.setId("action4");

        PlanDefinitionBackboneGraphVerticesProcessor processor =
                new PlanDefinitionBackboneGraphVerticesProcessor(null, defaultFactory, edgeTypeConsumer,
                        edgeSequenceConsumer);
        GraphBuilder<BackboneElement, Void> graphBuilder =
                GraphBuilderImpl.create(TransformationConstants.KEY_MAPPER(), defaultFactory);

        // when
        processor.process(planDefinition, planDefinition.getAction(), graphBuilder);

        // then
        Graph<BackboneElement, Void> graph = graphBuilder.toGraph();
        Assert.assertNotNull(graph);
        Assert.assertEquals(graph.getVertices().size(), 6);
        Optional<Vertex<BackboneElement, Void>> action1VertexOpt =
                graph.getVertices().stream().filter(v -> v.getElement() == action1).findFirst();
        Optional<Vertex<BackboneElement, Void>> xorVertexOpt =
                graph.getVertices().stream().filter(v -> v.getElement() == xor).findFirst();
        Optional<Vertex<BackboneElement, Void>> xorJoinVertexOpt =
                graph.getVertices().stream().filter(v -> v.getElement().getId().equals("join_of_" + xor.getId())).findFirst();
        Optional<Vertex<BackboneElement, Void>> action2VertexOpt =
                graph.getVertices().stream().filter(v -> v.getElement() == action2).findFirst();
        Optional<Vertex<BackboneElement, Void>> action3VertexOpt =
                graph.getVertices().stream().filter(v -> v.getElement() == action3).findFirst();
        Optional<Vertex<BackboneElement, Void>> action4VertexOpt =
                graph.getVertices().stream().filter(v -> v.getElement() == action4).findFirst();
        Assert.assertTrue(action1VertexOpt.isPresent());
        Assert.assertTrue(xorVertexOpt.isPresent());
        Assert.assertTrue(xorJoinVertexOpt.isPresent());
        Assert.assertTrue(action2VertexOpt.isPresent());
        Assert.assertTrue(action3VertexOpt.isPresent());
        Assert.assertTrue(action4VertexOpt.isPresent());
        Assert.assertTrue(action1VertexOpt.get().getOutgoingEdges().stream().map(Edge::getTarget).anyMatch(v -> v == xorVertexOpt.get()));
        Assert.assertTrue(xorVertexOpt.get().getOutgoingEdges().stream().map(Edge::getTarget).anyMatch(v -> v == action2VertexOpt.get()));
        Assert.assertTrue(xorVertexOpt.get().getOutgoingEdges().stream().map(Edge::getTarget).anyMatch(v -> v == action3VertexOpt.get()));
        Assert.assertTrue(action2VertexOpt.get().getOutgoingEdges().stream().map(Edge::getTarget).anyMatch(v -> v == xorJoinVertexOpt.get()));
        Assert.assertTrue(action3VertexOpt.get().getOutgoingEdges().stream().map(Edge::getTarget).anyMatch(v -> v == xorJoinVertexOpt.get()));
        Assert.assertTrue(xorJoinVertexOpt.get().getOutgoingEdges().stream().map(Edge::getTarget).anyMatch(v -> v == action4VertexOpt.get()));
    }

    /**
     * Process:
     * <pre>
     *             |--------------------|
     *  action1 -&gt; | action2 -&gt; action 3| -&gt; action4
     *             |--------------------|
     * </pre>
     */
    @Test
    public void testSubProcess() {
        // given
        PlanDefinition planDefinition = new PlanDefinition();
        PlanDefinition.PlanDefinitionActionComponent action1 = planDefinition.addAction();
        action1.setId("action1");
        action1.addRelatedAction()
                .setActionId("subprocess1")
                .setRelationship(PlanDefinition.ActionRelationshipType.BEFORESTART);
        PlanDefinition.PlanDefinitionActionComponent subprocess1 = planDefinition.addAction();
        subprocess1.setId("subprocess1");
        PlanDefinition.PlanDefinitionActionComponent action2 = subprocess1.addAction();
        action2.setId("action2");
        action2.addRelatedAction()
                .setActionId("action3")
                .setRelationship(PlanDefinition.ActionRelationshipType.BEFORESTART);
        PlanDefinition.PlanDefinitionActionComponent action3 = subprocess1.addAction();
        action3.setId("action3");
        subprocess1.addRelatedAction()
                .setActionId("action4")
                .setRelationship(PlanDefinition.ActionRelationshipType.BEFORESTART);
        PlanDefinition.PlanDefinitionActionComponent action4 = planDefinition.addAction();
        action4.setId("action4");

        TransformationRender<GraphBuilder<BackboneElement, Void>, GraphBuilder<BackboneElement, Void>, PlanDefinition,
                List<PlanDefinition.PlanDefinitionActionComponent>> planDefinitionBackboneGraphRenderer =
                Mockito.mock(TransformationRender.class);
        Mockito.doAnswer(invocation -> {
            GraphBuilder<IBaseElement, Void> graphBuilder =
                    GraphBuilderImpl.create(TransformationConstants.KEY_MAPPER());
            Element start = action2.copy().setRelatedAction(null).setId("start123");
            graphBuilder
                    .from(start).to(action2)
                    .from(action2).to(action3)
                    .from(action3).to(action3.copy().setRelatedAction(null).setId("end123"));
            // we just mock the base sequence flow, as this is already tested in other test (and we do not want to
            // test the whole pipeline here)
            Vertex<IBaseElement, Void> startVertex = graphBuilder.getOrAddVertex(start);
            graphBuilder.addGraphCreationCallback(graph ->
                    graph.addMetaTag(new MetaTagImpl<>(TransformationConstants.START_NODE_META_TAG, List.of(startVertex))));
            return graphBuilder;
        }).when(planDefinitionBackboneGraphRenderer).renderElement(ArgumentMatchers.any(), ArgumentMatchers.any());
        PlanDefinitionBackboneGraphVerticesProcessor processor =
                new PlanDefinitionBackboneGraphVerticesProcessor(planDefinitionBackboneGraphRenderer, defaultFactory,
                        edgeTypeConsumer, edgeSequenceConsumer);
        GraphBuilder<BackboneElement, Void> graphBuilder =
                GraphBuilderImpl.create(TransformationConstants.KEY_MAPPER(), defaultFactory);

        // when
        processor.process(planDefinition, planDefinition.getAction(), graphBuilder);

        // then
        Graph<BackboneElement, Void> graph = graphBuilder.toGraph();
        Assert.assertNotNull(graph);
        Assert.assertEquals(graph.getVertices().size(), 7);
        Optional<Vertex<BackboneElement, Void>> action1VertexOpt =
                graph.getVertices().stream().filter(v -> v.getElement() == action1).findFirst();
        Optional<Vertex<BackboneElement, Void>> subprocess1VertexOpt =
                graph.getVertices().stream().filter(v -> v.getElement() == subprocess1).findFirst();
        Optional<Vertex<BackboneElement, Void>> action4VertexOpt =
                graph.getVertices().stream().filter(v -> v.getElement() == action4).findFirst();
        Assert.assertTrue(action1VertexOpt.isPresent());
        Assert.assertTrue(subprocess1VertexOpt.isPresent());
        Assert.assertTrue(action4VertexOpt.isPresent());
        Assert.assertTrue(action1VertexOpt.get().getOutgoingEdges().stream().map(Edge::getTarget).anyMatch(v -> v == subprocess1VertexOpt.get()));
        Assert.assertTrue(subprocess1VertexOpt.get().getOutgoingEdges().stream().filter(e -> e.getMetaTagValue(EDGE_TYPE_META_TAG, EdgeType.class) == EdgeType.SEQUENCE).map(Edge::getTarget).anyMatch(v -> v == action4VertexOpt.get()));
        Assert.assertTrue(subprocess1VertexOpt.get().tryGetMetaTagValue(SUBPROCESS_META_TAG).isPresent());
        Assert.assertEquals(((Graph<?, ?>) subprocess1VertexOpt.get().tryGetMetaTagValue(SUBPROCESS_META_TAG).get()).getVertices().size(), 4);
        Assert.assertEquals(((Graph<?, ?>) subprocess1VertexOpt.get().tryGetMetaTagValue(SUBPROCESS_META_TAG).get()).getEdges().size(), 3);
    }
}