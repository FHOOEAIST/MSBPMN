/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer;

import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TStartEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;
import science.aist.msbpmn.service.transformation.renderer.AbstractTFlowNodeBpmnGraphTransformationRenderer;

import javax.xml.bind.JAXBElement;
import java.util.function.Function;

/**
 * <p>Renderer for BPMN StartEvent.</p>
 *
 * @author Andreas Schuler andreas.schuler@fh-hagenberg.at
 * @author Andreas Pointner andreas.pointner@fh-hagenberg.at
 */
@Component
public class BpmnStartEventTransformerRenderer extends AbstractTFlowNodeBpmnGraphTransformationRenderer<TStartEvent, BackboneElement, PlanDefinition.PlanDefinitionActionComponent> {

    public BpmnStartEventTransformerRenderer(@Qualifier("startEventCondition") RendererCondition<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> rendererCondition, ObjectFactory objectFactory) {
        super(rendererCondition, objectFactory);
    }

    @Override
    public TStartEvent createElement() {
        return objectFactory.createTStartEvent();
    }

    @Override
    public TStartEvent mapProperties(TStartEvent element, Graph<BackboneElement, Void> container, Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> currentElement) {
        element = super.mapProperties(element, container, currentElement);
        // Comments from official OMG BPMN 2.0.2 Specification

        /* This attribute only applies to Start Events of Event Sub-Processes; it is ignored for
           other Start Events. This attribute denotes whether the Sub-Process encompassing
           the Event Sub-Process should be canceled or not, If the encompassing SubProcess
           is not canceled, multiple instances of the Event Sub-Process can run
           concurrently. This attribute cannot be applied to Error Events (where it’s always
           true), or Compensation Events (where it doesn’t apply). */
        element.setIsInterrupting(false); // doesn't apply here

        /* A reference to the OutputSets defined by the
           InputOutputSpecification. Every Data Interface MUST define
           at least one OutputSet. */
        element.setOutputSet(null); // doesn't apply here


        element.setParallelMultiple(false); // doesnt' apply here
        return element;
    }

    @Override
    protected Function<TStartEvent, JAXBElement<? extends TStartEvent>> constructJaxBElementMapping() {
        return objectFactory::createStartEvent;
    }

    @Override
    public boolean canRenderer(Vertex<?, ?> vertex) {
        return vertex != null && vertex.getElement() instanceof PlanDefinition.PlanDefinitionActionComponent;
    }
}
