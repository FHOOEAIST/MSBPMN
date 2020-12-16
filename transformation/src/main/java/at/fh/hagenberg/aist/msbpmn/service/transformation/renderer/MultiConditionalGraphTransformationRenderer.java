/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.renderer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.TransformationRender;
import science.aist.jack.general.util.CastUtils;

import javax.xml.bind.JAXBElement;
import java.util.List;
import java.util.Optional;

/**
 * <p>MultiRenderer to decide between a list of child renderers</p>
 *
 * @author Andreas Pointner
 */
@AllArgsConstructor
public class MultiConditionalGraphTransformationRenderer<R, S, T, E> implements TransformationRender<JAXBElement<? extends R>, R, Graph<S, E>, Vertex<T, E>> {

    @Getter
    private final List<AbstractConditionalGraphTransformationRenderer<? extends R, ? extends S, ? extends T>> childRenderers;

    public JAXBElement<? extends R> renderElement(Graph<S, E> container, Vertex<T, E> currentElement) {
        return this.getChildRenderers()
                .stream()
                .filter(cr -> cr.canRenderer(currentElement))
                .map(cr -> cr.renderElement(CastUtils.cast(container), CastUtils.cast(currentElement)))
                .filter(Optional::isPresent).map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No renderer matches!"));
    }

    @Override
    public R createElement() {
        throw new UnsupportedOperationException("MultiConditionalGraphTransformationRenderer does not support create element");
    }

    @Override
    public R mapProperties(R r, Graph<S, E> vertices, Vertex<T, E> sVertex) {
        throw new UnsupportedOperationException("MultiConditionalGraphTransformationRenderer does not support map properties");
    }
}
