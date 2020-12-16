/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.renderer;

import at.fh.hagenberg.aist.msbpmn.service.transformation.helper.IdProvider;
import lombok.NonNull;
import org.hl7.fhir.r4.model.BackboneElement;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TFlowElement;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;

/**
 * <p>Abstract base class for BPMN Renderers</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */
public abstract class AbstractBpmnGraphTransformationRenderer<R extends TFlowElement, S extends BackboneElement, T extends BackboneElement> extends AbstractConditionalGraphTransformationRenderer<R, S, T> {

    protected final ObjectFactory objectFactory;

    public AbstractBpmnGraphTransformationRenderer(RendererCondition<Vertex<T, Void>> rendererCondition, ObjectFactory objectFactory) {
        super(rendererCondition);
        this.objectFactory = objectFactory;
    }

    @Override
    // The default behaviour of the mapProperties method is to just return the provided element
    // if additional mapping of properties is needed, sub classes need to override this method
    public R mapProperties(@NonNull R element, Graph<S, Void> container, @NonNull Vertex<T, Void> currentElement) {
        // According to the BPMN specification auditing only applies to process element
        element.setId(new IdProvider(currentElement.getElement().getId()).getId());
        element.setAuditing(null);
        element.setMonitoring(null);

        return element;
    }
}
