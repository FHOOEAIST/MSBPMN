/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer;

import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.Timing;
import org.hl7.fhir.r4.model.TriggerDefinition;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TTimerEventDefinition;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.impl.VertexImpl;

import javax.xml.bind.JAXBElement;
import java.util.List;
import java.util.function.Function;

/**
 * <p>Test class for {@link BpmnTTimerEventDefinitionTransformerRenderer}</p>
 *
 * @author Andreas Pointner
 */

public class BpmnTTimerEventDefinitionTransformerRendererTest {

    private final BpmnTTimerEventDefinitionTransformerRenderer renderer = new BpmnTTimerEventDefinitionTransformerRenderer(new ObjectFactory());

    @Test
    public void testMapProperties() {
        // given
        PlanDefinition.PlanDefinitionActionComponent action = new PlanDefinition.PlanDefinitionActionComponent();
        TriggerDefinition trigger = action.getTriggerFirstRep();
        trigger.setType(TriggerDefinition.TriggerType.PERIODIC);
        Timing timingTiming = trigger.getTimingTiming();
        timingTiming.setEvent(List.of(DateTimeType.today()));

        // when
        TTimerEventDefinition tTimerEventDefinition = renderer.mapProperties(new TTimerEventDefinition(), null, new VertexImpl<>(action));

        // then
        Assert.assertNotNull(tTimerEventDefinition);
        Assert.assertNotNull(tTimerEventDefinition.getTimeDate());
        Assert.assertNotNull(tTimerEventDefinition.getTimeDate().getContent());
        Assert.assertEquals(tTimerEventDefinition.getTimeDate().getContent().size(), 1);
    }

    @Test
    public void testMapPropertiesDuration() {
        // given
        PlanDefinition.PlanDefinitionActionComponent action = new PlanDefinition.PlanDefinitionActionComponent();
        TriggerDefinition trigger = action.getTriggerFirstRep();
        trigger.setType(TriggerDefinition.TriggerType.PERIODIC);
        Timing timingTiming = trigger.getTimingTiming();
        Timing.TimingRepeatComponent repeat = timingTiming.getRepeat();
        repeat.setDuration(10);
        repeat.setDurationUnit(Timing.UnitsOfTime.S);

        // when
        TTimerEventDefinition tTimerEventDefinition = renderer.mapProperties(new TTimerEventDefinition(), null, new VertexImpl<>(action));

        // then
        Assert.assertNotNull(tTimerEventDefinition);
        Assert.assertNotNull(tTimerEventDefinition.getTimeDuration());
        Assert.assertNotNull(tTimerEventDefinition.getTimeDuration().getContent());
        Assert.assertEquals(tTimerEventDefinition.getTimeDuration().getContent().size(), 1);
        Assert.assertEquals(tTimerEventDefinition.getTimeDuration().getContent().get(0), "P10S");
    }

    @Test
    public void testMapPropertiesRepeat() {
        // given
        PlanDefinition.PlanDefinitionActionComponent action = new PlanDefinition.PlanDefinitionActionComponent();
        TriggerDefinition trigger = action.getTriggerFirstRep();
        trigger.setType(TriggerDefinition.TriggerType.PERIODIC);
        Timing timingTiming = trigger.getTimingTiming();
        Timing.TimingRepeatComponent repeat = timingTiming.getRepeat();
        repeat.setFrequency(1);
        repeat.setPeriod(10);
        repeat.setPeriodUnit(Timing.UnitsOfTime.S);

        // when
        TTimerEventDefinition tTimerEventDefinition = renderer.mapProperties(new TTimerEventDefinition(), null, new VertexImpl<>(action));

        // then
        Assert.assertNotNull(tTimerEventDefinition);
        Assert.assertNotNull(tTimerEventDefinition.getTimeCycle());
        Assert.assertNotNull(tTimerEventDefinition.getTimeCycle().getContent());
        Assert.assertEquals(tTimerEventDefinition.getTimeCycle().getContent().size(), 1);
        Assert.assertEquals(tTimerEventDefinition.getTimeCycle().getContent().get(0), "R1/P10S");
    }

    @Test
    public void testConstructJaxBElementMapping() {
        // given

        // when
        Function<TTimerEventDefinition, JAXBElement<? extends TTimerEventDefinition>> tTimerEventDefinitionJAXBElementFunction = renderer.constructJaxBElementMapping();

        // then
        Assert.assertNotNull(tTimerEventDefinitionJAXBElementFunction);
        Assert.assertNotNull(tTimerEventDefinitionJAXBElementFunction.apply(new TTimerEventDefinition()));
    }

    @Test
    public void testCreateElement() {
        // given

        // when
        TTimerEventDefinition element = renderer.createElement();

        // then
        Assert.assertNotNull(element);
    }
}