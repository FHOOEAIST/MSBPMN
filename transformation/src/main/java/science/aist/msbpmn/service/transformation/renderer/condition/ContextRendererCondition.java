/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.renderer.condition;

import java.util.function.BiPredicate;

/**
 * <p>A RendererCondition based on the concept of
 * {@link science.aist.gtf.transformation.renderer.condition.RendererCondition}
 * but with a given context in which the condition should be checked</p>
 *
 * @param <T> the type of the element to be checked
 * @param <C> the type of the context in which this should be checked
 * @author Andreas Pointner
 */
public interface ContextRendererCondition<C, T> {
    /**
     * Creates the condition object
     *
     * @return the predicate to test of the condition
     */
    BiPredicate<C, T> createCondition();
}
