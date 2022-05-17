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
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.impl.MetaTagImpl;
import science.aist.gtf.graph.impl.VertexImpl;
import science.aist.msbpmn.service.transformation.TransformationConstants;

/**
 * <p>Test class for {@link EndEventCondition}</p>
 *
 * @author Andreas Pointner
 */

public class EndEventConditionTest {
    private final EndEventCondition condition = new EndEventCondition();

    @Test
    public void testConditionPositive() {
        // given
        Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> vertex = new VertexImpl<>(null);
        vertex.addMetaTag(new MetaTagImpl<>(TransformationConstants.END_META_TAG, true));

        // when
        boolean test = condition.createCondition().test(vertex);

        // then
        Assert.assertTrue(test);
    }

    @Test
    public void testConditionNegative() {
        // given
        Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> vertex = new VertexImpl<>(null);

        // when
        boolean test = condition.createCondition().test(vertex);

        // then
        Assert.assertFalse(test);
    }
}