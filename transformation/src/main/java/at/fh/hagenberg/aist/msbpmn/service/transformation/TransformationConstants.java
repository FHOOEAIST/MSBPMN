/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation;

import org.hl7.fhir.instance.model.api.IBaseElement;

import java.util.function.Function;

/**
 * <p>Class with static strings, that contain different transformation constants</p>
 *
 * @author Andreas Pointner
 */
public final class TransformationConstants {

    public static final String START_NODE_META_TAG = "startNode";
    public static final String END_NODE_META_TAG = "endNode";
    public static final String SUBPROCESS_META_TAG = "subprocess";
    public static final String EDGE_TYPE_META_TAG = "edgetype";
    public static final String PROCESS_NAME_TAG = "processName";
    public static final String EVENT_META_TAG = "event";
    public static final String PARTICIPANTS_COLL_META_TAG = "participants_collector";
    public static final String JOIN_META_TAG = "join";
    public static final String START_META_TAG = "start";
    public static final String END_META_TAG = "end";

    public static <V extends IBaseElement> Function<V, Object> KEY_MAPPER() {
        return IBaseElement::getId;
    }

    private TransformationConstants() {
    }
}
