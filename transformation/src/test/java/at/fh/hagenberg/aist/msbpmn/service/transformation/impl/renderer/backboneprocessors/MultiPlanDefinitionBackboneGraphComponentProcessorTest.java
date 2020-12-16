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
import at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer.PlanDefinitionBackboneGraphComponentProcessor;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.mockito.ArgumentMatchers;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.testng.annotations.Test;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.builder.impl.GraphBuilderImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Test class for {@link MultiPlanDefinitionBackboneGraphComponentProcessor}</p>
 *
 * @author Andreas Pointner
 */

public class MultiPlanDefinitionBackboneGraphComponentProcessorTest {
    @Test
    public void testProcessing() {
        // given
        PlanDefinition planDefinition = new PlanDefinition();
        List<PlanDefinition.PlanDefinitionActionComponent> list = new ArrayList<>();
        GraphBuilder<BackboneElement, Void> graphBuilder = GraphBuilderImpl.create(TransformationConstants.KEY_MAPPER());
        PlanDefinitionBackboneGraphComponentProcessor processor1 = Mockito.mock(PlanDefinitionBackboneGraphComponentProcessor.class);
        PlanDefinitionBackboneGraphComponentProcessor processor2 = Mockito.mock(PlanDefinitionBackboneGraphComponentProcessor.class);

        MultiPlanDefinitionBackboneGraphComponentProcessor multiPlanDefinitionBackboneGraphComponentProcessor = new MultiPlanDefinitionBackboneGraphComponentProcessor(List.of(processor1, processor2));

        // when
        multiPlanDefinitionBackboneGraphComponentProcessor.process(planDefinition, list, graphBuilder);

        // then
        InOrder inOrder = Mockito.inOrder(processor1, processor2);
        inOrder.verify(processor1).process(ArgumentMatchers.eq(planDefinition), ArgumentMatchers.eq(list), ArgumentMatchers.eq(graphBuilder));
        inOrder.verify(processor2).process(ArgumentMatchers.eq(planDefinition), ArgumentMatchers.eq(list), ArgumentMatchers.eq(graphBuilder));
        inOrder.verifyNoMoreInteractions();
    }
}