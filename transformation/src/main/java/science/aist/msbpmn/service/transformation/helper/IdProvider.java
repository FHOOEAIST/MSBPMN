/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.helper;

import org.omg.spec.bpmn.model.TBaseElement;

/**
 * <p>Helper class to provide the id element based on a TBaseElement</p>
 *
 * @author Andreas Pointner
 */
public class IdProvider extends TBaseElement {
    public IdProvider(String id) {
        this.id = "id_" + id;
    }
}
