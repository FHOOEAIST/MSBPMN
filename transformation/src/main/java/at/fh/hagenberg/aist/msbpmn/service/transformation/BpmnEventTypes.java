/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation;

import lombok.AllArgsConstructor;
import org.hl7.fhir.r4.model.TriggerDefinition;

import java.util.Arrays;

/**
 * <p>Enum that contains the different supported BPMN Event Types</p>
 *
 * @author Andreas Pointner
 */
@AllArgsConstructor
public enum BpmnEventTypes {
    EVENT_CONDITION(TriggerDefinition.TriggerType.NAMEDEVENT),
    EVENT_TIMED(TriggerDefinition.TriggerType.PERIODIC);

    private final TriggerDefinition.TriggerType triggerType;

    public static BpmnEventTypes getByTriggerType(TriggerDefinition.TriggerType triggerType) {
        return Arrays.stream(BpmnEventTypes.values())
                .filter(bpmnEventTypes -> bpmnEventTypes.triggerType == triggerType)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Not supported trigger type" + triggerType));
    }
}
