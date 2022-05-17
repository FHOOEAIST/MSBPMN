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
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.impl.VertexImpl;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;

import java.util.function.Predicate;

/**
 * <p>Test class for {@link StartConditionalEventCondition}</p>
 *
 * @author Andreas Pointner
 */

public class StartConditionalEventConditionTest {

    @Test
    public void testCall() {
        // given
        Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> action = new VertexImpl<>(null);
        Predicate<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> conditionalEventConditionPredicate = new TestPredicate();
        conditionalEventConditionPredicate = Mockito.spy(conditionalEventConditionPredicate);

        RendererCondition<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> conditionalEventCondition = Mockito.mock(RendererCondition.class);
        Mockito.doReturn(conditionalEventConditionPredicate).when(conditionalEventCondition).createCondition();

        Predicate<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> startEventConditionPredicate = new TestPredicate();
        startEventConditionPredicate = Mockito.spy(startEventConditionPredicate);
        RendererCondition<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> startEventCondition = Mockito.mock(RendererCondition.class);
        Mockito.doReturn(startEventConditionPredicate).when(startEventCondition).createCondition();

        StartConditionalEventCondition condition = new StartConditionalEventCondition(conditionalEventCondition, startEventCondition);

        // when
        boolean test = condition.createCondition().test(action);

        // then
        Assert.assertTrue(test);
        Mockito.verify(conditionalEventCondition, Mockito.times(1)).createCondition();
        Mockito.verify(startEventCondition, Mockito.times(1)).createCondition();
        Mockito.verify(conditionalEventConditionPredicate, Mockito.times(1)).test(ArgumentMatchers.eq(action));
        Mockito.verify(startEventConditionPredicate, Mockito.times(1)).test(ArgumentMatchers.eq(action));
        Mockito.verifyNoMoreInteractions(conditionalEventCondition, startEventCondition);
    }

    static class TestPredicate implements Predicate<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> {

        @Override
        public boolean test(Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionComponentVertex) {
            return true;
        }
    }
}