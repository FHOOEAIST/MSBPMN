/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer;

import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import science.aist.gtf.graph.builder.GraphBuilder;

import java.util.List;

/**
 * <p>Interface to process single elements of the PlanDefinition Backbone Graph Transformation</p>
 *
 * @author Andreas Pointner
 */
public interface PlanDefinitionBackboneGraphComponentProcessor {
    void process(PlanDefinition planDefinition,
                 List<PlanDefinition.PlanDefinitionActionComponent> list,
                 GraphBuilder<BackboneElement, Void> graphBuilder);
}
