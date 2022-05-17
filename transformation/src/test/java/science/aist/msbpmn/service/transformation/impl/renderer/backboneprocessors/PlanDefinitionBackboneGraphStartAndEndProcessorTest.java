/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer.backboneprocessors;

import science.aist.msbpmn.service.transformation.TransformationConstants;
import science.aist.msbpmn.service.transformation.impl.renderer.condition.PlanDefinitionActionEndEventCondition;
import science.aist.msbpmn.service.transformation.impl.renderer.condition.PlanDefinitionActionStartEventCondition;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.builder.impl.GraphBuilderImpl;
import science.aist.gtf.graph.factory.GraphFactoryFactory;

import java.util.List;
import java.util.Optional;

/**
 * <p>Test class for {@link PlanDefinitionBackboneGraphStartAndEndProcessor}</p>
 *
 * @author Andreas Pointner
 */

public class PlanDefinitionBackboneGraphStartAndEndProcessorTest {

    @Test
    public void testProcess() {
        // given
        PlanDefinitionBackboneGraphStartAndEndProcessor processor = new PlanDefinitionBackboneGraphStartAndEndProcessor(
                GraphFactoryFactory.getDefaultFactory(),
                new PlanDefinitionActionStartEventCondition(),
                new PlanDefinitionActionEndEventCondition()
        );

        PlanDefinition pd = new PlanDefinition();
        PlanDefinition.PlanDefinitionActionComponent action1 = pd.addAction();
        action1.setId("action1");
        action1.addRelatedAction().setActionId("action2");
        PlanDefinition.PlanDefinitionActionComponent action2 = pd.addAction();
        action2.setId("action2");

        GraphBuilder<BackboneElement, Void> graphBuilder = GraphBuilderImpl.create(TransformationConstants.KEY_MAPPER());

        // when
        processor.process(pd, pd.getAction(), graphBuilder);

        // then
        List<PlanDefinition.PlanDefinitionActionComponent> action = pd.getAction();
        Assert.assertEquals(action.size(), 4);
        Optional<PlanDefinition.PlanDefinitionActionComponent> startOpt = action.stream().filter(a -> a.getId().startsWith("start")).findAny();
        Assert.assertTrue(startOpt.isPresent());
        Assert.assertEquals(startOpt.get().getRelatedActionFirstRep().getActionId(), action1.getId());
        Optional<PlanDefinition.PlanDefinitionActionComponent> endOpt = action.stream().filter(a -> a.getId().startsWith("end")).findAny();
        Assert.assertTrue(endOpt.isPresent());
        Assert.assertEquals(action2.getRelatedActionFirstRep().getActionId(), endOpt.get().getId());
    }
}