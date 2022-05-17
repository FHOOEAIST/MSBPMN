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
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.TriggerDefinition;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.builder.impl.GraphBuilderImpl;

import java.util.Optional;

/**
 * <p>Test class for {@link PlanDefinitionBackboneGraphTriggerProcessor}</p>
 *
 * @author Andreas Pointner
 */

public class PlanDefinitionBackboneGraphTriggerProcessorTest {

    @Test
    public void testProcess() {
        // given
        PlanDefinitionBackboneGraphTriggerProcessor processor = new PlanDefinitionBackboneGraphTriggerProcessor();

        PlanDefinition pd = new PlanDefinition();
        PlanDefinition.PlanDefinitionActionComponent action1 = pd.addAction();
        action1.setId("action1");
        TriggerDefinition trigger = action1.addTrigger();
        trigger.setType(TriggerDefinition.TriggerType.NAMEDEVENT);
        trigger.getCondition().setExpression("expression1");
        GraphBuilder<BackboneElement, Void> graphBuilder = GraphBuilderImpl.create(TransformationConstants.KEY_MAPPER());

        // when
        processor.process(pd, pd.getAction(), graphBuilder);

        // then
        Assert.assertEquals(pd.getAction().size(), 2);
        Optional<PlanDefinition.PlanDefinitionActionComponent> action1Opt = pd.getAction().stream().filter(a -> a.getId().equals("action1")).findFirst();
        Assert.assertTrue(action1Opt.isPresent());
        Assert.assertNotEquals(action1Opt.get(), action1);
        Assert.assertEquals(action1Opt.get().getRelatedActionFirstRep().getActionId(), action1.getId());
        Assert.assertTrue(action1Opt.get().getTriggerFirstRep().equalsDeep(trigger));
        Assert.assertFalse(action1.hasTrigger());
    }
}