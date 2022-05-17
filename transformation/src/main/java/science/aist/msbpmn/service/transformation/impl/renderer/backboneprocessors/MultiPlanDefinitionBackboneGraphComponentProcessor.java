/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer.backboneprocessors;

import lombok.AllArgsConstructor;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.msbpmn.service.transformation.impl.renderer.PlanDefinitionBackboneGraphComponentProcessor;

import java.util.List;

/**
 * <p>Helper class to execute a list of {@link PlanDefinitionBackboneGraphComponentProcessor}. Make sure that
 * the ordering of the processor in the list is also the order on how the processor are executed.</p>
 *
 * @author Andreas Pointner
 */
@AllArgsConstructor
public class MultiPlanDefinitionBackboneGraphComponentProcessor implements PlanDefinitionBackboneGraphComponentProcessor {

    private final List<PlanDefinitionBackboneGraphComponentProcessor> processors;

    @Override
    public void process(PlanDefinition planDefinition, List<PlanDefinition.PlanDefinitionActionComponent> list, GraphBuilder<BackboneElement, Void> graphBuilder) {
        processors.forEach(processor -> processor.process(planDefinition, list, graphBuilder));
    }
}
