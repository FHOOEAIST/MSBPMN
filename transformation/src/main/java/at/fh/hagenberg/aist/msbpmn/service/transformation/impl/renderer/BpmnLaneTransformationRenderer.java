/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer;

import at.fh.hagenberg.aist.msbpmn.service.transformation.helper.IdProvider;
import lombok.AllArgsConstructor;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinitionActorComponent;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TLane;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.TransformationRender;
import science.aist.jack.general.util.CastUtils;

/**
 * <p>Renders a BPMN Lane based on a {@link org.hl7.fhir.r4.model.PlanDefinition.PlanDefinitionActionParticipantComponent}</p>
 *
 * @author Andreas Pointner
 */
@Component
@AllArgsConstructor
public class BpmnLaneTransformationRenderer implements TransformationRender<TLane, TLane, Graph<BackboneElement, Void>, Vertex<PlanDefinitionActorComponent, Void>> {

    private final ObjectFactory objectFactory;

    @Override
    public TLane renderElement(Graph<BackboneElement, Void> vertices, Vertex<PlanDefinitionActorComponent, Void> currentElement) {
        return mapProperties(createElement(), vertices, currentElement);
    }

    @Override
    public TLane createElement() {
        return objectFactory.createTLane();
    }

    @Override
    public TLane mapProperties(TLane tLane, Graph<BackboneElement, Void> vertices, Vertex<PlanDefinitionActorComponent, Void> currentElement) {
        tLane.setId(new IdProvider(currentElement.getElement().getId()).getId());
        tLane.setName(currentElement.getElement().getLabel());
        currentElement
                .getOutgoingEdges()
                .stream()
                .map(CastUtils::<Edge<PlanDefinitionActorComponent, Void>, Edge<BackboneElement, Void>>cast)
                .map(Edge::getTarget)
                .map(Vertex::getElement)
                .map(BackboneElement::getId)
                .map(IdProvider::new)
                .map(objectFactory::createTLaneFlowNodeRef)
                .forEach(tLane.getFlowNodeRef()::add);
        return tLane;
    }
}
