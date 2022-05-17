/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer;

import science.aist.msbpmn.service.transformation.renderer.AbstractTFlowNodeBpmnGraphTransformationRenderer;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TEndEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;

import javax.xml.bind.JAXBElement;
import java.util.function.Function;

/**
 * <p>Renderer for BPMN endEvents</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */
@Component
public class BpmnEndEventTransformerRenderer extends AbstractTFlowNodeBpmnGraphTransformationRenderer<TEndEvent, BackboneElement, PlanDefinition.PlanDefinitionActionComponent> {
    public BpmnEndEventTransformerRenderer(@Qualifier("endEventCondition") RendererCondition<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> rendererCondition, ObjectFactory objectFactory) {
        super(rendererCondition, objectFactory);
    }

    @Override
    public TEndEvent createElement() {
        return objectFactory.createTEndEvent();
    }

    @Override
    public TEndEvent mapProperties(TEndEvent element, Graph<BackboneElement, Void> container, Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> currentElement) {
        element = super.mapProperties(element, container, currentElement);
        // Comments from official OMG BPMN 2.0.2 Specification
        element.setInputSet(null);
        return element;
    }

    @Override
    protected Function<TEndEvent, JAXBElement<? extends TEndEvent>> constructJaxBElementMapping() {
        return objectFactory::createEndEvent;
    }

    @Override
    public boolean canRenderer(Vertex<?, ?> vertex) {
        return vertex != null && vertex.getElement() instanceof PlanDefinition.PlanDefinitionActionComponent;
    }

}
