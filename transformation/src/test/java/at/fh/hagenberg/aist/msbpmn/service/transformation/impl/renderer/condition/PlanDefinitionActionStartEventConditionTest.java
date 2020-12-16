/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer.condition;

import org.hl7.fhir.r4.model.PlanDefinition;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * <p>Test class for {@link PlanDefinitionActionStartEventCondition}</p>
 *
 * @author Andreas Pointner
 */

public class PlanDefinitionActionStartEventConditionTest {

    private final PlanDefinitionActionStartEventCondition condition = new PlanDefinitionActionStartEventCondition();

    @Test
    public void testConditionPositive() {
        // given
        PlanDefinition.PlanDefinitionActionComponent actionComponent1 = new PlanDefinition.PlanDefinitionActionComponent();
        actionComponent1.addRelatedAction()
                .setActionId("action2");
        actionComponent1.setId("action1");
        PlanDefinition.PlanDefinitionActionComponent actionComponent2 = new PlanDefinition.PlanDefinitionActionComponent();
        actionComponent2.setId("action2");

        List<PlanDefinition.PlanDefinitionActionComponent> actionComponents = List.of(actionComponent1, actionComponent2);

        // when
        boolean test = condition.createCondition().test(actionComponents, actionComponent1);

        // then
        Assert.assertTrue(test);
    }

    @Test
    public void testConditionNegative() {
        // given
        PlanDefinition.PlanDefinitionActionComponent actionComponent1 = new PlanDefinition.PlanDefinitionActionComponent();
        actionComponent1.addRelatedAction()
                .setActionId("action2");
        actionComponent1.setId("action1");
        PlanDefinition.PlanDefinitionActionComponent actionComponent2 = new PlanDefinition.PlanDefinitionActionComponent();
        actionComponent2.setId("action2");

        List<PlanDefinition.PlanDefinitionActionComponent> actionComponents = List.of(actionComponent1, actionComponent2);

        // when
        boolean test = condition.createCondition().test(actionComponents, actionComponent2);

        // then
        Assert.assertFalse(test);
    }
}