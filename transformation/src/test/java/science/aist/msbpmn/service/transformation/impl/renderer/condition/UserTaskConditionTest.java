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
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.builder.impl.GraphBuilderImpl;
import science.aist.gtf.graph.impl.MetaTagImpl;
import science.aist.msbpmn.service.transformation.TransformationConstants;
import science.aist.msbpmn.service.transformation.impl.EdgeType;

import static science.aist.jack.general.util.CastUtils.cast;

/**
 * <p>Test class for {@link UserTaskCondition}</p>
 *
 * @author Andreas Pointner
 */

public class UserTaskConditionTest {
    private final UserTaskCondition condition = new UserTaskCondition();

    @Test
    public void testConditionPositive() {
        // given
        GraphBuilder<PlanDefinition.PlanDefinitionActionComponent, Void> graphBuilder = GraphBuilderImpl.create(TransformationConstants.KEY_MAPPER());
        Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> action1 = graphBuilder.getOrAddVertex(cast(new PlanDefinition.PlanDefinitionActionComponent().setId("1")));
        Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> action2 = graphBuilder.getOrAddVertex(cast(new PlanDefinition.PlanDefinitionActionComponent().setId("2")));
        Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> action3 = graphBuilder.getOrAddVertex(cast(new PlanDefinition.PlanDefinitionActionComponent().setId("3")));
        graphBuilder.fromByKey("1").toWithByKey("2").with(e -> e.addMetaTag(new MetaTagImpl<>(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.SEQUENCE)));
        graphBuilder.fromByKey("2").toWithByKey("3").with(e -> e.addMetaTag(new MetaTagImpl<>(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.SEQUENCE)));

        // when
        boolean test = condition.createCondition().test(action2);

        // then
        Assert.assertTrue(test);
    }

    @Test
    public void testConditionNegative() {
        // given
        GraphBuilder<PlanDefinition.PlanDefinitionActionComponent, Void> graphBuilder = GraphBuilderImpl.create(TransformationConstants.KEY_MAPPER());
        Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> action1 = graphBuilder.getOrAddVertex(cast(new PlanDefinition.PlanDefinitionActionComponent().setId("1")));
        Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> action2 = graphBuilder.getOrAddVertex(cast(new PlanDefinition.PlanDefinitionActionComponent().setId("2")));
        Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> action3 = graphBuilder.getOrAddVertex(cast(new PlanDefinition.PlanDefinitionActionComponent().setId("3")));
        graphBuilder.fromByKey("1").toWithByKey("2").with(e -> e.addMetaTag(new MetaTagImpl<>(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.SEQUENCE)));
        graphBuilder.fromByKey("1").toWithByKey("3").with(e -> e.addMetaTag(new MetaTagImpl<>(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.SEQUENCE)));

        // when
        boolean test = condition.createCondition().test(action1);

        // then
        Assert.assertFalse(test);
    }
}