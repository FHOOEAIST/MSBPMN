/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer.condition;

import lombok.AllArgsConstructor;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;

import java.util.function.Predicate;

/**
 * <p>The condition to check if an ActionComponent is a timed start event</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */
@Component
@AllArgsConstructor
public class TimedStartEventCondition implements RendererCondition<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> {

    private final RendererCondition<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> timedEventCondition;
    private final RendererCondition<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> startEventCondition;

    public Predicate<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> createCondition() {
        return timedEventCondition.createCondition().and(startEventCondition.createCondition());
    }
}
