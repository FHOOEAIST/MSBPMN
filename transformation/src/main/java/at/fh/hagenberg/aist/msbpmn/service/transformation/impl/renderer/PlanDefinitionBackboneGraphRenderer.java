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
import at.fh.hagenberg.aist.msbpmn.service.transformation.renderer.condition.ContextRendererCondition;
import lombok.AllArgsConstructor;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.builder.impl.GraphBuilderImpl;
import science.aist.gtf.graph.factory.GraphFactory;
import science.aist.gtf.transformation.renderer.TransformationRender;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static at.fh.hagenberg.aist.msbpmn.service.transformation.TransformationConstants.END_NODE_META_TAG;
import static at.fh.hagenberg.aist.msbpmn.service.transformation.TransformationConstants.START_NODE_META_TAG;

/**
 * <p>Renderer that is used to create the backbone element graph out of a given list of actions</p>
 *
 * @author Andreas Pointner
 */
@Component
@AllArgsConstructor
public class PlanDefinitionBackboneGraphRenderer implements TransformationRender<GraphBuilder<BackboneElement, Void>, GraphBuilder<BackboneElement, Void>, PlanDefinition, List<PlanDefinition.PlanDefinitionActionComponent>> {

    private final GraphFactory graphFactory;
    private final PlanDefinitionBackboneGraphComponentProcessor planDefinitionBackboneGraphComponentsProcessor;
    private final ContextRendererCondition<List<PlanDefinition.PlanDefinitionActionComponent>, PlanDefinition.PlanDefinitionActionComponent> planDefinitionActionStartEventCondition;
    private final ContextRendererCondition<List<PlanDefinition.PlanDefinitionActionComponent>, PlanDefinition.PlanDefinitionActionComponent> planDefinitionActionEndEventCondition;

    @Override
    public GraphBuilder<BackboneElement, Void> renderElement(PlanDefinition planDefinition, List<PlanDefinition.PlanDefinitionActionComponent> list) {
        GraphBuilder<BackboneElement, Void> graphBuilder = createElement();
        planDefinitionBackboneGraphComponentsProcessor.process(planDefinition, list, graphBuilder);

        Function<ContextRendererCondition<List<PlanDefinition.PlanDefinitionActionComponent>, PlanDefinition.PlanDefinitionActionComponent>, List<Vertex<BackboneElement, Void>>> startEndExtractor =
                predicate -> list.stream()
                        .filter(pdac -> predicate.createCondition().test(list, pdac))
                        .map(graphBuilder::getOrAddVertex)
                        .collect(Collectors.toList());

        graphBuilder.addGraphCreationCallback(graph ->
                graph.addMetaTag(graphFactory.createMetaTag(START_NODE_META_TAG, startEndExtractor.apply(planDefinitionActionStartEventCondition))));
        graphBuilder.addGraphCreationCallback(graph ->
                graph.addMetaTag(graphFactory.createMetaTag(END_NODE_META_TAG, startEndExtractor.apply(planDefinitionActionEndEventCondition))));

        return graphBuilder;
    }

    @Override
    public GraphBuilder<BackboneElement, Void> createElement() {
        return GraphBuilderImpl.create(TransformationConstants.KEY_MAPPER(), graphFactory);
    }

    @Override
    public GraphBuilder<BackboneElement, Void> mapProperties(GraphBuilder<BackboneElement, Void> graphBuilder, PlanDefinition planDefinition, List<PlanDefinition.PlanDefinitionActionComponent> list) {
        throw new UnsupportedOperationException();
    }
}
