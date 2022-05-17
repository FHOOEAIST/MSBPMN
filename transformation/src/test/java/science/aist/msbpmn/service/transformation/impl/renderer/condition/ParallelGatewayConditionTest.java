/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer.condition;

import science.aist.msbpmn.service.transformation.TransformationConstants;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.impl.MetaTagImpl;
import science.aist.gtf.graph.impl.VertexImpl;

/**
 * <p>Test class for {@link ParallelGatewayCondition}</p>
 *
 * @author Andreas Pointner
 */

public class ParallelGatewayConditionTest {
    private final ParallelGatewayCondition condition = new ParallelGatewayCondition();

    @Test
    public void testConditionPositive1() {
        // given
        PlanDefinition.PlanDefinitionActionComponent action = new PlanDefinition.PlanDefinitionActionComponent();
        action.setGroupingBehavior(PlanDefinition.ActionGroupingBehavior.LOGICALGROUP);
        action.setSelectionBehavior(PlanDefinition.ActionSelectionBehavior.ALL);

        // when
        boolean test = condition.createCondition().test(new VertexImpl<>(action));

        // then
        Assert.assertTrue(test);
    }

    @Test
    public void testConditionPositive2() {
        // given
        PlanDefinition.PlanDefinitionActionComponent join = new PlanDefinition.PlanDefinitionActionComponent();
        PlanDefinition.PlanDefinitionActionComponent action = new PlanDefinition.PlanDefinitionActionComponent();
        action.setGroupingBehavior(PlanDefinition.ActionGroupingBehavior.LOGICALGROUP);
        action.setSelectionBehavior(PlanDefinition.ActionSelectionBehavior.ALL);

        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionComponentVertex = new VertexImpl<>(join);
        planDefinitionActionComponentVertex.addMetaTag(new MetaTagImpl<>(TransformationConstants.JOIN_META_TAG, action));

        // when
        boolean test = condition.createCondition().test(planDefinitionActionComponentVertex);

        // then
        Assert.assertTrue(test);
    }


    @Test
    public void testConditionNegative1() {
        // given
        PlanDefinition.PlanDefinitionActionComponent action = new PlanDefinition.PlanDefinitionActionComponent();
        action.setGroupingBehavior(PlanDefinition.ActionGroupingBehavior.LOGICALGROUP);
        action.setSelectionBehavior(PlanDefinition.ActionSelectionBehavior.ATMOSTONE);

        // when
        boolean test = condition.createCondition().test(new VertexImpl<>(action));

        // then
        Assert.assertFalse(test);
    }

    @Test
    public void testConditionNegative2() {
        // given
        PlanDefinition.PlanDefinitionActionComponent action = new PlanDefinition.PlanDefinitionActionComponent();
        action.setGroupingBehavior(PlanDefinition.ActionGroupingBehavior.VISUALGROUP);

        // when
        boolean test = condition.createCondition().test(new VertexImpl<>(action));

        // then
        Assert.assertFalse(test);
    }

    @Test
    public void testConditionNegative3() {
        // given
        PlanDefinition.PlanDefinitionActionComponent action = new PlanDefinition.PlanDefinitionActionComponent();

        // when
        boolean test = condition.createCondition().test(new VertexImpl<>(action));

        // then
        Assert.assertFalse(test);
    }

    @Test
    public void testConditionNegative4() {
        // given
        PlanDefinition.PlanDefinitionActionComponent join = new PlanDefinition.PlanDefinitionActionComponent();
        PlanDefinition.PlanDefinitionActionComponent action = new PlanDefinition.PlanDefinitionActionComponent();
        action.setGroupingBehavior(PlanDefinition.ActionGroupingBehavior.LOGICALGROUP);
        action.setSelectionBehavior(PlanDefinition.ActionSelectionBehavior.ATMOSTONE);

        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionComponentVertex = new VertexImpl<>(join);
        planDefinitionActionComponentVertex.addMetaTag(new MetaTagImpl<>(TransformationConstants.JOIN_META_TAG, action));

        // when
        boolean test = condition.createCondition().test(planDefinitionActionComponentVertex);

        // then
        Assert.assertFalse(test);
    }
}