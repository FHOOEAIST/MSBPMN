/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer;

import at.fh.hagenberg.aist.msbpmn.service.transformation.renderer.AbstractBpmnEventDefinitionTransformationRenderer;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TEventDefinition;
import org.omg.spec.bpmn.model.TIntermediateCatchEvent;
import org.omg.spec.bpmn.model.TStartEvent;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.GraphState;
import science.aist.gtf.graph.impl.GraphStateImpl;
import science.aist.gtf.graph.impl.VertexImpl;
import science.aist.jack.general.util.CastUtils;

import javax.xml.bind.JAXBElement;
import java.util.Optional;

/**
 * <p>Test class for {@link BpmnEventStartEventTransformationRenderer}</p>
 *
 * @author Andreas Pointner
 */

public class BpmnEventStartEventTransformationRendererTest {

    @Test
    public void testMapProperties() {
        // given
        PlanDefinition.PlanDefinitionActionComponent input = new PlanDefinition.PlanDefinitionActionComponent();
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionComponentVertex = new VertexImpl<>(input);
        GraphState<PlanDefinition.PlanDefinitionActionComponent, Void> gs = new GraphStateImpl<>();
        gs.addVertex(planDefinitionActionComponentVertex);
        JAXBElement<TIntermediateCatchEvent> intermediateCatchEvent = new ObjectFactory().createIntermediateCatchEvent(new TIntermediateCatchEvent());

        //noinspection unchecked
        AbstractBpmnEventDefinitionTransformationRenderer<? extends TEventDefinition, BackboneElement, PlanDefinition.PlanDefinitionActionComponent> bpmnEventDefinitionTransformationRenderer =
                Mockito.mock(AbstractBpmnEventDefinitionTransformationRenderer.class);

        Mockito.when(bpmnEventDefinitionTransformationRenderer.renderElement(ArgumentMatchers.any(), ArgumentMatchers.eq(CastUtils.cast(planDefinitionActionComponentVertex))))
                .thenReturn(CastUtils.cast(intermediateCatchEvent));

        BpmnEventStartEventTransformationRenderer renderer = new BpmnEventStartEventTransformationRenderer(
                () -> t -> true,
                new ObjectFactory(),
                bpmnEventDefinitionTransformationRenderer
        );

        // when
        Optional<JAXBElement<? extends TStartEvent>> jaxbElement = renderer.renderElement(null, planDefinitionActionComponentVertex);

        // then
        Assert.assertTrue(jaxbElement.isPresent());
        TStartEvent startEventRes = jaxbElement.get().getValue();
        Assert.assertNotNull(startEventRes);
        Assert.assertNotNull(startEventRes.getEventDefinition());
        Assert.assertEquals(startEventRes.getEventDefinition().size(), 1);
        //noinspection AssertEqualsBetweenInconvertibleTypesTestNG
        Assert.assertEquals(startEventRes.getEventDefinition().get(0), intermediateCatchEvent);
    }
}