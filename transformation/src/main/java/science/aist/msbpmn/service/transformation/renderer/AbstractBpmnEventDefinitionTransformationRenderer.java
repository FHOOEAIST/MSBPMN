/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.renderer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TEventDefinition;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.TransformationRender;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.xml.bind.JAXBElement;
import java.util.function.Function;

/**
 * <p>Abstract class to create event definitions</p>
 *
 * @author Andreas Pointner
 */
@AllArgsConstructor
public abstract class AbstractBpmnEventDefinitionTransformationRenderer<R extends TEventDefinition, S extends BackboneElement, T extends PlanDefinition.PlanDefinitionActionComponent> implements TransformationRender<JAXBElement<? extends R>, R, Graph<S, Void>, Vertex<T, Void>> {

    private static final String ID_PREFIX = "event_";

    @Getter(AccessLevel.PROTECTED)
    private final ObjectFactory objectFactory;

    protected abstract Function<R, JAXBElement<? extends R>> constructJaxBElementMapping();

    public JAXBElement<? extends R> renderElement(Graph<S, Void> container, Vertex<T, Void> currentElement) {
        return constructJaxBElementMapping().apply(mapProperties(createElement(), container, currentElement));
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public R mapProperties(R r, Graph<S, Void> graph, Vertex<T, Void> vertex) {
        r.setId(ID_PREFIX + vertex.getElement().getId());
        return r;
    }
}
