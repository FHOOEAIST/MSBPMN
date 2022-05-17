/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer;

import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.factory.GraphFactoryFactory;
import science.aist.msbpmn.service.transformation.impl.renderer.condition.PlanDefinitionActionEndEventCondition;
import science.aist.msbpmn.service.transformation.impl.renderer.condition.PlanDefinitionActionStartEventCondition;

import java.util.List;

import static science.aist.msbpmn.service.transformation.TransformationConstants.END_NODE_META_TAG;
import static science.aist.msbpmn.service.transformation.TransformationConstants.START_NODE_META_TAG;

/**
 * <p>Test class for {@link PlanDefinitionBackboneGraphRenderer}</p>
 *
 * @author Andreas Pointner
 */

public class PlanDefinitionBackboneGraphRendererTest {

    @Test
    public void testCreateElement() {
        // given
        PlanDefinitionBackboneGraphRenderer renderer = new PlanDefinitionBackboneGraphRenderer(GraphFactoryFactory.getDefaultFactory(), null, null, null);

        // when
        GraphBuilder<BackboneElement, Void> element = renderer.createElement();

        // then
        Assert.assertNotNull(element);
    }

    @Test
    public void testRenderElement() {
        // given
        PlanDefinition planDefinition = new PlanDefinition();
        PlanDefinition.PlanDefinitionActionComponent action1 = planDefinition.addAction();
        action1.setId("action1");
        PlanDefinition.PlanDefinitionActionComponent action2 = planDefinition.addAction();
        action2.setId("action2");
        PlanDefinition.PlanDefinitionActionComponent action3 = planDefinition.addAction();
        action3.setId("action3");
        PlanDefinition.PlanDefinitionActionRelatedActionComponent relA1toA2 = action1.getRelatedActionFirstRep();
        relA1toA2.setRelationship(PlanDefinition.ActionRelationshipType.BEFORESTART);
        relA1toA2.setActionId(action2.getId());
        PlanDefinition.PlanDefinitionActionRelatedActionComponent relA2toA3 = action1.getRelatedActionFirstRep();
        relA2toA3.setRelationship(PlanDefinition.ActionRelationshipType.BEFORESTART);
        relA2toA3.setActionId(action3.getId());

        PlanDefinitionBackboneGraphComponentProcessor mock = Mockito.mock(PlanDefinitionBackboneGraphComponentProcessor.class);
        Mockito.doAnswer(x -> {
            List<Object> list = x.getArgument(1);
            GraphBuilder<Object, Void> gb = x.getArgument(2);
            list.forEach(gb::addVertex);
            return null;
        }).when(mock).process(Mockito.any(), Mockito.any(), Mockito.any());
        PlanDefinitionBackboneGraphRenderer renderer = new PlanDefinitionBackboneGraphRenderer(
                GraphFactoryFactory.getDefaultFactory(),
                mock,
                new PlanDefinitionActionStartEventCondition(),
                new PlanDefinitionActionEndEventCondition());

        // when
        GraphBuilder<BackboneElement, Void> graphBuilder = renderer.renderElement(planDefinition, planDefinition.getAction());

        // then
        Assert.assertNotNull(graphBuilder);
        var graph = graphBuilder.toGraph();
        Assert.assertNotNull(graph);
        List<?> startNodes = graph.getMetaTagValue(START_NODE_META_TAG, List.class);
        Assert.assertTrue(startNodes.stream()
                .map(Vertex.class::cast)
                .map(Vertex::getElement)
                .map(PlanDefinition.PlanDefinitionActionComponent.class::cast)
                .anyMatch(x -> x.equals(action1)));

        List<?> endNodes = graph.getMetaTagValue(END_NODE_META_TAG, List.class);
        Assert.assertTrue(endNodes.stream()
                .map(Vertex.class::cast)
                .map(Vertex::getElement)
                .map(PlanDefinition.PlanDefinitionActionComponent.class::cast)
                .anyMatch(x -> x.equals(action3)));

        Mockito.verify(mock, Mockito.times(1)).process(ArgumentMatchers.eq(planDefinition), ArgumentMatchers.eq(planDefinition.getAction()), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(mock);
    }
}