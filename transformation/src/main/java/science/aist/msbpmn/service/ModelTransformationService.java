/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service;

/**
 * <p>Transformation between HL7® FHIR® and BPMN representations</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */
public interface ModelTransformationService {
    /**
     * Transforms a bpmn String into a HL7® FHIR® plan definition
     *
     * @param bpmnResource the bpmn xml as string
     * @return the resulting HL7® FHIR® xml as string
     */
    String fromBpmn(String bpmnResource);

    /**
     * Transforms a HL7® FHIR® plan definition string into a bpmn
     *
     * @param planDefinition the HL7® FHIR® plan definition as string
     * @return the resulting bpmn xml as string
     */
    String fromFhir(String planDefinition);
}
