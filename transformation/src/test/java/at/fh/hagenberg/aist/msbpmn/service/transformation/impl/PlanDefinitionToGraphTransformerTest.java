/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl;

import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.PlanDefinitionActionIOComponent;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.builder.impl.GraphBuilderImpl;
import science.aist.gtf.graph.factory.GraphFactoryFactory;
import science.aist.gtf.transformation.renderer.TransformationRender;

import java.util.List;
import java.util.Map;

/**
 * <p>Test class for {@link PlanDefinitionToGraphTransformer}</p>
 *
 * @author Andreas Pointner
 */

public class PlanDefinitionToGraphTransformerTest {

    @Test
    public void testApplyTransformation() {
        // given
        PlanDefinition planDefinition = new PlanDefinition();
        PlanDefinition.PlanDefinitionActionComponent planDefinitionActionComponent = planDefinition.addAction();
        TransformationRender<GraphBuilder<BackboneElement, Void>, GraphBuilder<BackboneElement, Void>, PlanDefinition, List<PlanDefinition.PlanDefinitionActionComponent>> planDefinitionBackboneGraphRenderer = Mockito.mock(TransformationRender.class);
        TransformationRender<GraphBuilder<BackboneElement, Void>, Map<String, Vertex<PlanDefinitionActionIOComponent, Void>>, GraphBuilder<BackboneElement, Void>, PlanDefinition> planDefinitionDataElementsRenderer = Mockito.mock(TransformationRender.class);
        GraphBuilder<BackboneElement, Void> graphBuilder = GraphBuilderImpl.<BackboneElement, Void>create();
        Mockito.doReturn(graphBuilder).when(planDefinitionBackboneGraphRenderer).renderElement(ArgumentMatchers.eq(planDefinition), ArgumentMatchers.any());
        Mockito.doReturn(graphBuilder).when(planDefinitionDataElementsRenderer).renderElement(ArgumentMatchers.eq(graphBuilder), ArgumentMatchers.eq(planDefinition));

        PlanDefinitionToGraphTransformer planDefinitionToGraphTransformer = new PlanDefinitionToGraphTransformer(planDefinitionBackboneGraphRenderer, planDefinitionDataElementsRenderer, GraphFactoryFactory.getDefaultFactory());

        // when
        Graph<BackboneElement, Void> result = planDefinitionToGraphTransformer.applyTransformation(planDefinition);

        // then
        Assert.assertNotNull(result);
        Mockito.verify(planDefinitionBackboneGraphRenderer, Mockito.times(1)).renderElement(ArgumentMatchers.eq(planDefinition), ArgumentMatchers.eq(List.of(planDefinitionActionComponent)));
        Mockito.verify(planDefinitionDataElementsRenderer, Mockito.times(1)).renderElement(ArgumentMatchers.eq(graphBuilder), ArgumentMatchers.eq(planDefinition));
        Mockito.verifyNoMoreInteractions(planDefinitionBackboneGraphRenderer, planDefinitionDataElementsRenderer);
    }
}