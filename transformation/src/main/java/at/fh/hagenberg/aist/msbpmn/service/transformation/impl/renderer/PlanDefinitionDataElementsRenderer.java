/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer;

import at.fh.hagenberg.aist.msbpmn.service.transformation.helper.PlanDefinitionActionDataComponent;
import lombok.AllArgsConstructor;
import lombok.CustomLog;
import org.hl7.fhir.r4.model.*;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.transformation.renderer.TransformationRender;
import science.aist.jack.general.util.CastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * <p>Processes a given graph of backbone elements and adds the data elements + its flow to the graph</p>
 * <p>
 * Notes: - We assume that every output create a new dataobject + ref - Every input has a reference to an output and can
 * thus lead to the correct ref or has no ref, that it creates a new dataobject + ref
 * </p>
 *
 * @author Andreas Pointner
 */
@Component
@AllArgsConstructor
@CustomLog
public class PlanDefinitionDataElementsRenderer implements TransformationRender<GraphBuilder<BackboneElement, Void>, Map<String, Vertex<PlanDefinitionActionIOComponent, Void>>, GraphBuilder<BackboneElement, Void>, PlanDefinition> {
    private final Consumer<Edge<BackboneElement, Void>> edgeDataRefConsumer;

    @Override
    public GraphBuilder<BackboneElement, Void> renderElement(GraphBuilder<BackboneElement, Void> graphBuilder, PlanDefinition currentElement) {
        mapProperties(createElement(), graphBuilder, currentElement);
        return graphBuilder;
    }

    @Override
    public Map<String, Vertex<PlanDefinitionActionIOComponent, Void>> createElement() {
        return new HashMap<>();
    }


    @Override
    public Map<String, Vertex<PlanDefinitionActionIOComponent, Void>> mapProperties(Map<String, Vertex<PlanDefinitionActionIOComponent, Void>> dataObjectRefMap, GraphBuilder<BackboneElement, Void> graphBuilder, PlanDefinition currentElement) {
        Consumer<PlanDefinitionActionIOComponent> createDataElem =
                elem -> graphBuilder.from(elem)
                        .toWith(new PlanDefinitionActionDataComponent("dataObj_" + elem.getId()))
                        .with(edgeDataRefConsumer);

        List<PlanDefinition.PlanDefinitionActionComponent> actions = graphBuilder.getGraphState().getVertices().stream()
                .filter(v -> v.getElement() instanceof PlanDefinition.PlanDefinitionActionComponent)
                .map(CastUtils::<Vertex<BackboneElement, Void>, Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>>cast)
                .map(Vertex::getElement)
                .collect(Collectors.toList());

        // handle data output (new element for every data output)
        handleDataOutput(graphBuilder, createDataElem, actions);

        // handle data input
        handleDataInput(graphBuilder, createDataElem, actions);

        return dataObjectRefMap;
    }

    private void handleDataInput(GraphBuilder<BackboneElement, Void> graphBuilder, Consumer<PlanDefinitionActionIOComponent> createDataElem, List<PlanDefinition.PlanDefinitionActionComponent> actions) {
        for (PlanDefinition.PlanDefinitionActionComponent elem : actions) {
            for (PlanDefinitionActionIComponent inputComponent : elem.getInput()) {
                if (inputComponent.hasRelatedRequirement()) {
                    // case 1: there are related requirements, that means an output element already exists-
                    for (PlanDefinitionActionIRelatedActionIOComponent planDefinitionActionIRelatedActionIOComponent : inputComponent.getRelatedRequirement()) {
                        graphBuilder.fromByKey(planDefinitionActionIRelatedActionIOComponent.getRequirementId()).toWith(elem).with(edgeDataRefConsumer);
                    }
                } else {
                    // case 2: this means that there is no reference on an input element: -- create it new
                    log.warn("Input " + inputComponent.getId() + " of action " + elem.getId() + " has no reference, and thus creates a new data element");
                    createDataElem.accept(inputComponent);
                    graphBuilder.from(inputComponent).toWith(elem).with(edgeDataRefConsumer);
                }
            }
        }
    }

    private void handleDataOutput(GraphBuilder<BackboneElement, Void> graphBuilder, Consumer<PlanDefinitionActionIOComponent> createDataElem, List<PlanDefinition.PlanDefinitionActionComponent> actions) {
        for (PlanDefinition.PlanDefinitionActionComponent elem : actions) {
            for (PlanDefinitionActionOComponent outputComponent : elem.getOutput()) {
                createDataElem.accept(outputComponent);
                graphBuilder.from(elem).toWith(outputComponent).with(edgeDataRefConsumer);
            }
        }
    }
}
