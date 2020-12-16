/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer.backboneprocessors;

import at.fh.hagenberg.aist.msbpmn.service.transformation.impl.EdgeType;
import at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer.PlanDefinitionBackboneGraphComponentProcessor;
import lombok.RequiredArgsConstructor;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.factory.GraphFactory;
import science.aist.gtf.transformation.renderer.TransformationRender;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static at.fh.hagenberg.aist.msbpmn.service.transformation.TransformationConstants.*;

/**
 * <p>Processes the sequence flow between the different actions of a PlanDefinition</p>
 *
 * @author Andreas Pointner
 */
@Component
@RequiredArgsConstructor
public class PlanDefinitionBackboneGraphVerticesProcessor implements PlanDefinitionBackboneGraphComponentProcessor {

    private final TransformationRender<GraphBuilder<BackboneElement, Void>, GraphBuilder<BackboneElement, Void>, PlanDefinition, List<PlanDefinition.PlanDefinitionActionComponent>> planDefinitionBackboneGraphRenderer;
    private final GraphFactory graphFactory;
    private final BiConsumer<Edge<?, Void>, EdgeType> edgeTypeConsumer;
    private final Consumer<Edge<BackboneElement, Void>> edgeSequenceConsumer;
    private int subprocessId = 1;

    @Override
    public void process(PlanDefinition planDefinition, List<PlanDefinition.PlanDefinitionActionComponent> actions, GraphBuilder<BackboneElement, Void> graphBuilder) {
        // Add all actions to the cache
        actions.forEach(graphBuilder::addVertex);

        // create edges for PlanDefinition --> create edges
        for (PlanDefinition.PlanDefinitionActionComponent actionDefinition : actions) {
            if (PlanDefinition.ActionGroupingBehavior.LOGICALGROUP == actionDefinition.getGroupingBehavior()) {
                // collect all actions in logical group, if related action is pointing to an element outside
                // the logical group (not in list of actions in logical group) the element points to a syncing element
                // doesn't hold for actions pointing to different outside actions
                // a -> b (outside)  a  ->
                //                         <> -> b
                // c -> b (outside)  c  ->

                // a -> d (outside)  a ->
                //                         <> -> (d || b) ? won't work --> validate if such constructs occur, i would not allow them
                // c -> b (outside)  c ->

                // this node shouls be annotated (intermediary node)
                PlanDefinition.PlanDefinitionActionComponent intermediaryVertex = new PlanDefinition.PlanDefinitionActionComponent();
                intermediaryVertex.setId("join_of_" + actionDefinition.getId());
                intermediaryVertex.setTitle("join");
                graphBuilder.getOrAddVertex(intermediaryVertex).addMetaTag(graphFactory.createMetaTag(JOIN_META_TAG, actionDefinition));

                // we need an else branch as well
                if (PlanDefinition.ActionSelectionBehavior.ATMOSTONE == actionDefinition.getSelectionBehavior()) {
                    graphBuilder.from(actionDefinition).toWith(intermediaryVertex).with(edgeSequenceConsumer);
                }

                for (PlanDefinition.PlanDefinitionActionComponent actionDefinitionInGroup : actionDefinition.getAction()) {
                    graphBuilder.from(actionDefinition).toWith(actionDefinitionInGroup).with(edgeSequenceConsumer);

                    if (actionDefinitionInGroup.getAction() != null && !actionDefinitionInGroup.getAction().isEmpty()) {
                        addSubprocess(planDefinition, actionDefinitionInGroup, graphBuilder);
                    }
                    if (actionDefinitionInGroup.getRelatedAction() != null && !actionDefinitionInGroup.getRelatedAction().isEmpty()) {
                        // if the related action points to the logical group itself we define an edge to the sync element
                        if (actionDefinitionInGroup.getRelatedAction().size() > 1 || actionDefinitionInGroup.getRelatedAction().isEmpty()) {
                            throw new IllegalStateException("Actions inside a logical group must have a single related action to the logical group. It is not supported to reference non or more actions");
                        }
                        PlanDefinition.PlanDefinitionActionRelatedActionComponent related = actionDefinitionInGroup.getRelatedAction().get(0);
                        if (!related.getActionId().equals(actionDefinition.getId())) {
                            throw new IllegalStateException("Action inside a logical group need to have a related action to the logical group, but " + actionDefinition.getId() + " references to " + related.getId());
                        }
                        // related points to group
                        graphBuilder.from(actionDefinitionInGroup)
                                .toWith(intermediaryVertex)
                                .with(edgeSequenceConsumer);
                    }
                }
                addRelatedActions(intermediaryVertex, actionDefinition.getRelatedAction(), graphBuilder);
            } else {
                if (actionDefinition.hasAction()) {
                    addSubprocess(planDefinition, actionDefinition, graphBuilder);
                }

                // don't process related action in logical group
                addRelatedActions(actionDefinition, actionDefinition.getRelatedAction(), graphBuilder);
            }
        }
    }

    private void addSubprocess(PlanDefinition planDefinition, PlanDefinition.PlanDefinitionActionComponent actionDefinition, GraphBuilder<BackboneElement, Void> graphBuilder) {
        Graph<BackboneElement, Void> subGraph = planDefinitionBackboneGraphRenderer.renderElement(planDefinition, actionDefinition.getAction()).toGraph();
        subGraph.addMetaTag(graphFactory.createMetaTag(SUBPROCESS_META_TAG, subprocessId));
        subprocessId++;
        subGraph = graphBuilder.addSubGraph(subGraph);
        graphBuilder.getOrAddVertex(actionDefinition).addMetaTag(graphFactory.createMetaTag(SUBPROCESS_META_TAG, subGraph));
        graphBuilder.from(actionDefinition)
                .toWith(subGraph.<List<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>>>getMetaTagValue(START_NODE_META_TAG).stream().findFirst().orElseThrow().getElement())
                .with(e -> edgeTypeConsumer.accept(e, EdgeType.SUB_PROCESS));
    }

    private void addRelatedActions(PlanDefinition.PlanDefinitionActionComponent actionDefinition, List<PlanDefinition.PlanDefinitionActionRelatedActionComponent> relatedActions, GraphBuilder<BackboneElement, Void> graphBuilder) {
        for (PlanDefinition.PlanDefinitionActionRelatedActionComponent related : relatedActions) {
            graphBuilder.from(actionDefinition)
                    .toWithByKey(related.getActionId())
                    .with(edgeSequenceConsumer);
        }
    }
}
