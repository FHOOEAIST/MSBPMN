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
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.PlanDefinitionActorComponent;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.builder.impl.GraphBuilderImpl;
import science.aist.gtf.graph.factory.GraphFactoryFactory;
import science.aist.gtf.graph.impl.MetaTagImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>Test class for {@link PlanDefinitionBackboneGraphParticipantsProcessor}</p>
 *
 * @author Andreas Pointner
 */

public class PlanDefinitionBackboneGraphParticipantsProcessorTest {

    @Test
    public void testProcess() {
        // given
        PlanDefinitionBackboneGraphParticipantsProcessor planDefinitionBackboneGraphParticipantsProcessor = new PlanDefinitionBackboneGraphParticipantsProcessor(
                GraphFactoryFactory.getDefaultFactory(),
                e -> e.addMetaTag(new MetaTagImpl<>(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.PARTICIPANT))
        );

        PlanDefinition pd = new PlanDefinition();
        PlanDefinitionActorComponent actor1 = pd.addActor()
                .setLabel("My first Actor");
        actor1.setId("actor1");
        PlanDefinitionActorComponent actor2 = pd.addActor()
                .setLabel("My second Actor");
        actor2.setId("actor2");
        PlanDefinition.PlanDefinitionActionComponent action1 = pd.addAction();
        action1.addParticipant()
                .setActor("actor1");
        action1.setId("action1");

        PlanDefinition.PlanDefinitionActionComponent action2 = pd.addAction();
        action2.addParticipant()
                .setActor("actor1");
        action2.setId("action2");

        PlanDefinition.PlanDefinitionActionComponent action3 = pd.addAction();
        action3.addParticipant()
                .setActor("actor2");
        action3.setId("action3");

        GraphBuilder<BackboneElement, Void> graphBuilder = GraphBuilderImpl.create(TransformationConstants.KEY_MAPPER());

        // when
        planDefinitionBackboneGraphParticipantsProcessor.process(pd, pd.getAction(), graphBuilder);

        // then
        Graph<BackboneElement, Void> graph = graphBuilder.toGraph();
        Assert.assertNotNull(graph);
        Optional<Vertex<BackboneElement, Void>> participantsCollOpt = graph.getVertices().stream().filter(v -> v.tryGetMetaTagValue(TransformationConstants.PARTICIPANTS_COLL_META_TAG).isPresent()).findFirst();
        Assert.assertTrue(participantsCollOpt.isPresent());
        List<Vertex<BackboneElement, Void>> actorVertices = participantsCollOpt.get().getOutgoingEdges().stream().map(Edge::getTarget).collect(Collectors.toList());
        Assert.assertEquals(actorVertices.size(), 2);
        Optional<Vertex<BackboneElement, Void>> actorVertex1Opt = actorVertices.stream().filter(v -> v.getElement() == actor1).findAny();
        Optional<Vertex<BackboneElement, Void>> actorVertex2Opt = actorVertices.stream().filter(v -> v.getElement() == actor2).findAny();
        Assert.assertTrue(actorVertex1Opt.isPresent());
        Assert.assertTrue(actorVertex2Opt.isPresent());
        List<BackboneElement> actor1Elements = actorVertex1Opt.get().getOutgoingEdges().stream().map(Edge::getTarget).map(Vertex::getElement).collect(Collectors.toList());
        List<BackboneElement> actor2Elements = actorVertex2Opt.get().getOutgoingEdges().stream().map(Edge::getTarget).map(Vertex::getElement).collect(Collectors.toList());
        Assert.assertEquals(actor1Elements.size(), 2);
        Assert.assertEquals(actor2Elements.size(), 1);
        Assert.assertTrue(actor1Elements.contains(action1));
        Assert.assertTrue(actor1Elements.contains(action2));
        Assert.assertTrue(actor2Elements.contains(action3));
    }
}