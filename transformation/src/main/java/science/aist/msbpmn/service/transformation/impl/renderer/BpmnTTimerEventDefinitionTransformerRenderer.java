/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer;

import lombok.CustomLog;
import org.hl7.fhir.r4.model.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TExpression;
import org.omg.spec.bpmn.model.TTimerEventDefinition;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.msbpmn.service.transformation.renderer.AbstractBpmnEventDefinitionTransformationRenderer;

import javax.xml.bind.JAXBElement;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

/**
 * <p>Renderer for a BPMN TimerEvent.</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */
@Component
@CustomLog
public class BpmnTTimerEventDefinitionTransformerRenderer extends AbstractBpmnEventDefinitionTransformationRenderer<TTimerEventDefinition, BackboneElement, PlanDefinition.PlanDefinitionActionComponent> {
    public BpmnTTimerEventDefinitionTransformerRenderer(ObjectFactory objectFactory) {
        super(objectFactory);
    }

    private TExpression createTimeDateExpression(TTimerEventDefinition eventDefinition, Date timing) {
        DateTimeFormatter format = ISODateTimeFormat.dateTimeNoMillis();
        DateTime time = new DateTime(timing);
        TExpression expression = createExpression(time.toString(format));
        expression.setId(eventDefinition.getId() + "_timeDate");
        return expression;
    }

    private TExpression createTimeDurationExpression(TTimerEventDefinition eventDefinition, Timing.TimingRepeatComponent timingRepeat) {
        String duration = "P" + timingRepeat.getDuration().toString() + timingRepeat.getDurationUnit().toCode().toUpperCase();
        TExpression expression = createExpression(duration);
        expression.setId(eventDefinition.getId() + "_timeDuration");
        return expression;
    }

    private TExpression createTimeRepeatingExpression(TTimerEventDefinition eventDefinition, Timing.TimingRepeatComponent timingRepeat) {
        String repeating = "R" + timingRepeat.getFrequency() + "/P" + timingRepeat.getPeriod() + timingRepeat.getPeriodUnit().toCode().toUpperCase();
        TExpression expression = createExpression(repeating);
        expression.setId(eventDefinition.getId() + "_timeRepeating");
        return expression;
    }

    private TExpression createTimeConditionalExpression(TTimerEventDefinition eventDefinition, Expression expression) {
        TExpression res = createExpression(expression.getExpression());
        res.setId(eventDefinition.getId() + "_timeExpression");
        return res;
    }

    private TExpression createExpression(String textEntry) {
        TExpression expression = getObjectFactory().createTExpression();
        expression.getContent().add(textEntry);
        return expression;
    }

    @Override
    public TTimerEventDefinition mapProperties(TTimerEventDefinition definition, Graph<BackboneElement, Void> container, Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> currentElement) {
        definition = super.mapProperties(definition, container, currentElement);
        // add timed event definition here
        Optional<TriggerDefinition> triggerDefinition = currentElement.getElement().getTrigger().stream().filter(trigger -> trigger.getType() == TriggerDefinition.TriggerType.PERIODIC).findFirst();
        if (triggerDefinition.isPresent()) {
            TriggerDefinition triggerDefinitionElement = triggerDefinition.get();
            if (triggerDefinitionElement.hasName()) {
                definition.setId(triggerDefinitionElement.getName());
            }

            if (triggerDefinitionElement.hasTimingTiming()) {
                Timing timing = triggerDefinitionElement.getTimingTiming();
                // a timer definition can have exactly one element
                if (!timing.getEvent().isEmpty()) {
                    // a Time Date Element in ISO 8601 Format (2011-03-11T12:13:14Z)
                    definition.setTimeDate(createTimeDateExpression(definition, timing.getEvent().get(0).getValue()));
                } else if (timing.getRepeat().getDuration() != null && timing.getRepeat().getDurationUnit() != null) {
                    // a Time Duration in ISO 8601 Durations
                    /*
                    P is the duration designator (for period) placed at the start of the duration representation.
                    Y is the year designator that follows the value for the number of years.
                    M is the month designator that follows the value for the number of months.
                    W is the week designator that follows the value for the number of weeks.
                    D is the day designator that follows the value for the number of days.
                    T is the time designator that precedes the time components of the representation.
                        H is the hour designator that follows the value for the number of hours.
                        M is the minute designator that follows the value for the number of minutes.
                        S is the second designator that follows the value for the number of seconds.
                     */
                    // Duration starts with P
                    definition.setTimeDuration(createTimeDurationExpression(definition, timing.getRepeat()));

                } else if ((timing.getRepeat().getFrequency() >= 0)
                        && timing.getRepeat().getPeriod() != null
                        && timing.getRepeat().getPeriodUnit() != null) {
                    // a Time Cycle in ISO 8601 Repeating Intervals
                    definition.setTimeCycle(createTimeRepeatingExpression(definition, timing.getRepeat()));
                }
            } else if (triggerDefinitionElement.hasCondition()) {
                // https://docs.camunda.org/manual/7.9/reference/bpmn20/events/timer-events/#expressions
                definition.setTimeDuration(createTimeConditionalExpression(definition, triggerDefinitionElement.getCondition()));
            }
        }  // If no periodic trigger definition can be found, we render it as a normal start event
        return definition;
    }

    @Override
    protected Function<TTimerEventDefinition, JAXBElement<? extends TTimerEventDefinition>> constructJaxBElementMapping() {
        return getObjectFactory()::createTimerEventDefinition;
    }

    @Override
    public TTimerEventDefinition createElement() {
        return getObjectFactory().createTTimerEventDefinition();
    }
}
