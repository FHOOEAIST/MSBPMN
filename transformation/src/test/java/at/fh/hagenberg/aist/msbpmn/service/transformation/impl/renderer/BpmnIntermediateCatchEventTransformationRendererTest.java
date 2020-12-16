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
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.GraphState;
import science.aist.gtf.graph.impl.GraphStateImpl;
import science.aist.gtf.graph.impl.VertexImpl;
import science.aist.jack.general.util.CastUtils;

import javax.xml.bind.JAXBElement;
import java.util.Optional;
import java.util.function.Function;

/**
 * <p>Test class for {@link BpmnIntermediateCatchEventTransformationRenderer}</p>
 *
 * @author Andreas Pointner
 */

public class BpmnIntermediateCatchEventTransformationRendererTest {

    @Test
    public void testCreateElement() {
        // given
        BpmnIntermediateCatchEventTransformationRenderer renderer = new BpmnIntermediateCatchEventTransformationRenderer(() -> t -> true, new ObjectFactory(), null);

        // when
        TIntermediateCatchEvent element = renderer.createElement();

        // then
        Assert.assertNotNull(element);
    }

    @Test
    public void testMapProperties() {

        // given
        PlanDefinition.PlanDefinitionActionComponent input = new PlanDefinition.PlanDefinitionActionComponent();
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionComponentVertex =
                new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());
        GraphState<PlanDefinition.PlanDefinitionActionComponent, Void> gs = new GraphStateImpl<>();
        gs.addVertex(planDefinitionActionComponentVertex);
        JAXBElement<TIntermediateCatchEvent> intermediateCatchEvent = new ObjectFactory().createIntermediateCatchEvent(new TIntermediateCatchEvent());

        //noinspection unchecked
        AbstractBpmnEventDefinitionTransformationRenderer<? extends TEventDefinition, BackboneElement, PlanDefinition.PlanDefinitionActionComponent> bpmnEventDefinitionTransformationRenderer =
                Mockito.mock(AbstractBpmnEventDefinitionTransformationRenderer.class);

        Mockito.when(bpmnEventDefinitionTransformationRenderer.renderElement(ArgumentMatchers.any(), ArgumentMatchers.eq(CastUtils.cast(planDefinitionActionComponentVertex))))
                .thenReturn(CastUtils.cast(intermediateCatchEvent));

        BpmnIntermediateCatchEventTransformationRenderer renderer = new BpmnIntermediateCatchEventTransformationRenderer(
                () -> t -> true,
                new ObjectFactory(),
                bpmnEventDefinitionTransformationRenderer);

        // when
        Optional<JAXBElement<? extends TIntermediateCatchEvent>> jaxbElement = renderer.renderElement(null, planDefinitionActionComponentVertex);

        // then
        Assert.assertTrue(jaxbElement.isPresent());
        TIntermediateCatchEvent intermediateEventRes = jaxbElement.get().getValue();
        Assert.assertNotNull(intermediateEventRes);
        Assert.assertNotNull(intermediateEventRes.getEventDefinition());
        Assert.assertEquals(intermediateEventRes.getEventDefinition().size(), 1);
        //noinspection AssertEqualsBetweenInconvertibleTypesTestNG
        Assert.assertEquals(intermediateEventRes.getEventDefinition().get(0), intermediateCatchEvent);

        // given


        // when
        // then
    }

    @Test
    public void testConstructJaxBElementMapping() {
        // given
        BpmnIntermediateCatchEventTransformationRenderer renderer = new BpmnIntermediateCatchEventTransformationRenderer(() -> t -> true, new ObjectFactory(), null);

        // when
        Function<TIntermediateCatchEvent, JAXBElement<? extends TIntermediateCatchEvent>> tIntermediateCatchEventJAXBElementFunction = renderer.constructJaxBElementMapping();

        // then
        Assert.assertNotNull(tIntermediateCatchEventJAXBElementFunction);
        Assert.assertNotNull(tIntermediateCatchEventJAXBElementFunction.apply(new TIntermediateCatchEvent()));
    }

    @Test
    public void testCanRendererPos() {
        // given
        BpmnIntermediateCatchEventTransformationRenderer renderer = new BpmnIntermediateCatchEventTransformationRenderer(() -> t -> true, new ObjectFactory(), null);
        VertexImpl<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new PlanDefinition.PlanDefinitionActionComponent());

        // when
        boolean b = renderer.canRenderer(planDefinitionActionIOComponentVertex);

        // then
        Assert.assertTrue(b);
    }

    @Test
    public void testCanRendererNeg() {
        // given
        BpmnIntermediateCatchEventTransformationRenderer renderer = new BpmnIntermediateCatchEventTransformationRenderer(() -> t -> true, new ObjectFactory(), null);
        VertexImpl<Object, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new Object());

        // when
        boolean b = renderer.canRenderer(planDefinitionActionIOComponentVertex);

        // then
        Assert.assertFalse(b);
    }
}