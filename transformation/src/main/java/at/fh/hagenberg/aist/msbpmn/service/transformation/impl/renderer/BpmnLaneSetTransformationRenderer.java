/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer;

import lombok.AllArgsConstructor;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinitionActorComponent;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TLane;
import org.omg.spec.bpmn.model.TLaneSet;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.TransformationRender;
import science.aist.jack.general.util.CastUtils;

/**
 * <p>Renders a LaneSet</p>
 *
 * @author Andreas Pointner
 */
@Component
@AllArgsConstructor
public class BpmnLaneSetTransformationRenderer implements TransformationRender<TLaneSet, TLaneSet, Graph<BackboneElement, Void>, Vertex<BackboneElement, Void>> {

    private final ObjectFactory objectFactory;

    private final TransformationRender<TLane, TLane, Graph<BackboneElement, Void>, Vertex<PlanDefinitionActorComponent, Void>> tLaneTransformationRenderer;

    @Override
    public TLaneSet renderElement(Graph<BackboneElement, Void> vertices, Vertex<BackboneElement, Void> currentElement) {
        return mapProperties(createElement(), vertices, currentElement);
    }

    @Override
    public TLaneSet createElement() {
        return objectFactory.createTLaneSet();
    }

    @Override
    public TLaneSet mapProperties(TLaneSet tLaneSet, Graph<BackboneElement, Void> vertices, Vertex<BackboneElement, Void> currentElement) {
        currentElement.getOutgoingEdges()
                .stream()
                .map(Edge::getTarget)
                .map(CastUtils::<Vertex<BackboneElement, Void>, Vertex<PlanDefinitionActorComponent, Void>>cast)
                .map(v -> tLaneTransformationRenderer.renderElement(vertices, v))
                .forEach(tLaneSet.getLane()::add);
        return tLaneSet;
    }
}
