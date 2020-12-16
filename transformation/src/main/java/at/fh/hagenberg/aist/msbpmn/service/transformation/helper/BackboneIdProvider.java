/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.helper;

import org.hl7.fhir.r4.model.BackboneElement;

/**
 * <p>Helper class to create an element with an Id</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
public class BackboneIdProvider extends BackboneElement {

    public BackboneIdProvider(String id) {
        setId(id);
    }

    @Override
    public BackboneElement copy() {
        throw new UnsupportedOperationException("This should never be called");
    }
}
