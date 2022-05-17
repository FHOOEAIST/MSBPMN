/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.renderer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.TransformationRender;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;

import javax.xml.bind.JAXBElement;
import java.util.Optional;
import java.util.function.Function;

/**
 * <p>Additional Abstraction which combines graph transformation renderer and abstract conditional transformation
 * renderer</p>
 *
 * @author Andreas Pointner
 */
@Getter
@AllArgsConstructor
public abstract class AbstractConditionalGraphTransformationRenderer<R, S, T> implements TransformationRender<Optional<JAXBElement<? extends R>>, R, Graph<S, Void>, Vertex<T, Void>> {

    private final RendererCondition<Vertex<T, Void>> rendererCondition;

    public Optional<JAXBElement<? extends R>> renderElement(Graph<S, Void> container, Vertex<T, Void> currentElement) {
        return Optional.ofNullable(this.rendererCondition.createCondition().test(currentElement) ? this.mapProperties(this.createElement(), container, currentElement) : null)
                .map(constructJaxBElementMapping());
    }

    protected abstract Function<R, JAXBElement<? extends R>> constructJaxBElementMapping();

    public abstract boolean canRenderer(Vertex<?, ?> vertex);
}
