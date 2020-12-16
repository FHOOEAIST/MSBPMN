/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer.condition;

import at.fh.hagenberg.aist.msbpmn.service.transformation.BpmnEventTypes;
import at.fh.hagenberg.aist.msbpmn.service.transformation.TransformationConstants;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.impl.MetaTagImpl;
import science.aist.gtf.graph.impl.VertexImpl;

/**
 * <p>Test class for {@link ConditionalEventCondition}</p>
 *
 * @author Andreas Pointner
 */

public class ConditionalEventConditionTest {

    private final ConditionalEventCondition conditionalEventCondition = new ConditionalEventCondition();

    @Test
    public void testConditionPositive(){
        // given
        Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> vertex = new VertexImpl<>(null);
        vertex.addMetaTag(new MetaTagImpl<>(TransformationConstants.EVENT_META_TAG, BpmnEventTypes.EVENT_CONDITION));

        // when
        boolean test = conditionalEventCondition.createCondition().test(vertex);

        // then
        Assert.assertTrue(test);
    }

    @Test
    public void testConditionNegative(){
        // given
        Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> vertex = new VertexImpl<>(null);

        // when
        boolean test = conditionalEventCondition.createCondition().test(vertex);

        // then
        Assert.assertFalse(test);
    }
}