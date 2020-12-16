/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.impl;

import at.fh.hagenberg.aist.msbpmn.service.ModelTransformationService;
import ca.uhn.fhir.context.FhirContext;
import lombok.AllArgsConstructor;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.springframework.stereotype.Component;
import science.aist.gtf.transformation.Transformer;

/**
 * <p>Default Transformer, that transform the graph with any given transformer</p>
 *
 * @author Andreas Pointner
 */
@Component
@AllArgsConstructor
public class DefaultGraphBasedModelTransformationServiceImpl implements ModelTransformationService {

    private final Transformer<PlanDefinition, String> transformer;

    @Override
    public String fromBpmn(String bpmnResource) {
        throw new UnsupportedOperationException("Only supporting transformation from fhir to bpmn");
    }

    @Override
    public String fromFhir(String planDefinitionStr) {
        PlanDefinition planDefinition = FhirContext.forR4().newXmlParser().parseResource(PlanDefinition.class, planDefinitionStr);
        return transformer.applyTransformation(planDefinition);
    }
}
