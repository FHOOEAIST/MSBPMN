/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer.condition;

import at.fh.hagenberg.aist.msbpmn.service.transformation.TransformationConstants;
import at.fh.hagenberg.aist.msbpmn.service.transformation.impl.EdgeType;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.impl.EdgeImpl;
import science.aist.gtf.graph.impl.MetaTagImpl;

/**
 * <p>Test class for {@link SequenceFlowRendererCondition}</p>
 *
 * @author Andreas Pointner
 */
public class SequenceFlowRendererConditionTest {
    private final SequenceFlowRendererCondition condition = new SequenceFlowRendererCondition();

    @Test
    public void testConditionPositive() {
        // given
        Edge<PlanDefinition.PlanDefinitionActionComponent, Void> e = new EdgeImpl<>(null);
        e.addMetaTag(new MetaTagImpl<>(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.SEQUENCE));

        // when
        boolean test = condition.createCondition().test(e);

        // then
        Assert.assertTrue(test);
    }

    @Test
    public void testConditionNegative() {
        // given
        Edge<PlanDefinition.PlanDefinitionActionComponent, Void> e = new EdgeImpl<>(null);

        // when
        boolean test = condition.createCondition().test(e);

        // then
        Assert.assertFalse(test);
    }
}