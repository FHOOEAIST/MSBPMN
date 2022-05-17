/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.helper;

import org.hl7.fhir.r4.model.BackboneElement;

/**
 * <p>This is just a helper class, that is only created during transformation to make it easier to transform
 * into the BPMN structure having dataObjects and data references. We assume that a "normal" IOComponent is a data
 * reference. Thus this class create a temporary node representing a data object</p>
 *
 * @author Andreas Pointner
 */
public class PlanDefinitionActionDataComponent extends BackboneElement {

    public PlanDefinitionActionDataComponent(String id) {
        setId(id);
    }

    @Override
    public BackboneElement copy() {
        throw new UnsupportedOperationException("This should never be called");
    }
}
