/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl;

import lombok.AllArgsConstructor;
import lombok.CustomLog;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.PlanDefinitionActionIOComponent;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.factory.GraphFactory;
import science.aist.gtf.transformation.Transformer;
import science.aist.gtf.transformation.renderer.TransformationRender;
import science.aist.msbpmn.service.transformation.TransformationConstants;

import java.util.List;
import java.util.Map;

/**
 * <p>Transforms a {@link PlanDefinition} into a {@link Graph} of
 * {@link org.hl7.fhir.r4.model.PlanDefinition.PlanDefinitionActionComponent}s</p>
 *
 * @author Andreas Pointner
 */
@Component
@AllArgsConstructor
@CustomLog
public class PlanDefinitionToGraphTransformer implements Transformer<PlanDefinition, Graph<BackboneElement, Void>> {

    private final TransformationRender<GraphBuilder<BackboneElement, Void>, GraphBuilder<BackboneElement, Void>, PlanDefinition, List<PlanDefinition.PlanDefinitionActionComponent>> planDefinitionBackboneGraphRenderer;
    private final TransformationRender<GraphBuilder<BackboneElement, Void>, Map<String, Vertex<PlanDefinitionActionIOComponent, Void>>, GraphBuilder<BackboneElement, Void>, PlanDefinition> planDefinitionDataElementsRenderer;

    @NonNull
    private final GraphFactory graphFactory;

    @Override
    @SneakyThrows
    public Graph<BackboneElement, Void> applyTransformation(PlanDefinition planDefinition) {
        GraphBuilder<BackboneElement, Void> graphBuilder = planDefinitionBackboneGraphRenderer.renderElement(planDefinition, planDefinition.getAction());
        // This has to be it's own renderer, as it is only executed once for all processes (and hot for each subprocess
        // extra), which would be done if we move it to planDefinitionBackboneGraphRenderer (which is used in other
        // scopes as well).
        graphBuilder = planDefinitionDataElementsRenderer.renderElement(graphBuilder, planDefinition);

        Graph<BackboneElement, Void> graph = graphBuilder.toGraph();

        String processname = planDefinition.getName();
        if (processname == null) {
            processname = "Process_" + RandomStringUtils.random(10, true, true);
        }
        graph.addMetaTag(graphFactory.createMetaTag(TransformationConstants.PROCESS_NAME_TAG, processname));

        return graph;
    }
}
