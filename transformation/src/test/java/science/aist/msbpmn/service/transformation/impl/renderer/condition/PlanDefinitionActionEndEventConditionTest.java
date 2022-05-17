/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer.condition;

import org.hl7.fhir.r4.model.PlanDefinition;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>Test class for {@link PlanDefinitionActionEndEventCondition}</p>
 *
 * @author Andreas Pointner
 */
public class PlanDefinitionActionEndEventConditionTest {

    private final PlanDefinitionActionEndEventCondition condition = new PlanDefinitionActionEndEventCondition();

    @Test
    public void testConditionPositive() {
        // given
        PlanDefinition.PlanDefinitionActionComponent action = new PlanDefinition.PlanDefinitionActionComponent();

        // when
        boolean test = condition.createCondition().test(null, action);

        // then
        Assert.assertTrue(test);
    }

    @Test
    public void testConditionNegative() {
        // given
        PlanDefinition.PlanDefinitionActionComponent action = new PlanDefinition.PlanDefinitionActionComponent();
        action.addRelatedAction().setActionId("someReferenceId");

        // when
        boolean test = condition.createCondition().test(null, action);

        // then
        Assert.assertFalse(test);
    }
}