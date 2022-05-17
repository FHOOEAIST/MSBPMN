/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer;

import science.aist.msbpmn.service.transformation.TransformationConstants;
import science.aist.msbpmn.service.transformation.helper.IdProvider;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.PlanDefinitionActorComponent;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TBaseElement;
import org.omg.spec.bpmn.model.TLane;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.GraphState;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.builder.impl.GraphBuilderImpl;
import science.aist.gtf.graph.impl.GraphStateImpl;
import science.aist.gtf.graph.impl.VertexImpl;

import javax.xml.bind.JAXBElement;
import java.util.List;
import java.util.stream.Collectors;

import static science.aist.jack.general.util.CastUtils.cast;

/**
 * <p>Test class for {@link BpmnLaneTransformationRenderer}</p>
 *
 * @author Andreas Pointner
 */

public class BpmnLaneTransformationRendererTest {

    private final BpmnLaneTransformationRenderer renderer = new BpmnLaneTransformationRenderer(new ObjectFactory());

    @Test
    public void testRenderElement() {
        // given
        VertexImpl<PlanDefinitionActorComponent, Void> planDefinitionActionComponentVertex =
                new VertexImpl<>(new PlanDefinitionActorComponent());
        GraphState<PlanDefinitionActorComponent, Void> gs = new GraphStateImpl<>();
        gs.addVertex(planDefinitionActionComponentVertex);

        // when
        TLane tLane = renderer.renderElement(null, planDefinitionActionComponentVertex);

        // then
        Assert.assertNotNull(tLane);
    }

    @Test
    public void testCreateElement() {
        // given

        // when
        TLane element = renderer.createElement();

        // then
        Assert.assertNotNull(element);
    }

    @Test
    public void testMapProperties() {
        // given
        PlanDefinitionActorComponent planDefinitionActorComponent = new PlanDefinitionActorComponent();
        planDefinitionActorComponent.setId("id");
        planDefinitionActorComponent.setLabel("label");

        PlanDefinition.PlanDefinitionActionComponent action1 = new PlanDefinition.PlanDefinitionActionComponent();
        action1.setId("action1");

        PlanDefinition.PlanDefinitionActionComponent action2 = new PlanDefinition.PlanDefinitionActionComponent();
        action2.setId("action2");

        GraphBuilder<BackboneElement, Void> graphBuilder = GraphBuilderImpl.create(TransformationConstants.KEY_MAPPER());
        graphBuilder.from(planDefinitionActorComponent).to(action1)
                .from(planDefinitionActorComponent).to(action2);

        // when
        TLane tLane = renderer.renderElement(graphBuilder.toGraph(), cast(graphBuilder.getOrAddVertex(planDefinitionActorComponent)));

        // then
        Assert.assertNotNull(tLane);
        Assert.assertNotNull(tLane.getFlowNodeRef());
        Assert.assertEquals(tLane.getFlowNodeRef().size(), 2);
        List<String> references = tLane.getFlowNodeRef().stream()
                .map(JAXBElement::getValue)
                .map(TBaseElement.class::cast)
                .map(TBaseElement::getId)
                .collect(Collectors.toList());
        Assert.assertTrue(references.contains(new IdProvider(action1.getId()).getId()));
        Assert.assertTrue(references.contains(new IdProvider(action2.getId()).getId()));
    }
}