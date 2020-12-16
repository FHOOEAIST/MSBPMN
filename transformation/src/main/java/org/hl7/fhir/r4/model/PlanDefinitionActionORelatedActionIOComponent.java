/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.hl7.fhir.r4.model;

import ca.uhn.fhir.model.api.annotation.Block;

/**
 * <p>Output reference to an input/output object inside an action of a HL7® FHIR® plan definition</p>
 *
 * @author Andreas Pointner
 */
@Block()
public class PlanDefinitionActionORelatedActionIOComponent extends PlanDefinitionActionIORelatedActionIOComponent {

    @Override
    public PlanDefinitionActionORelatedActionIOComponent copy() {
        PlanDefinitionActionORelatedActionIOComponent dst = new PlanDefinitionActionORelatedActionIOComponent();
        copyValues(dst);
        dst.requirementId = requirementId == null ? null : requirementId.copy();
        return dst;
    }

    public String fhirType() {
        return "PlanDefinition.action.output.relatedRequirement";
    }
}
