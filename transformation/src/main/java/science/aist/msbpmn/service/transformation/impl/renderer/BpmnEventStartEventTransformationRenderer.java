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
import org.omg.spec.bpmn.model.TEventDefinition;
import org.omg.spec.bpmn.model.TStartEvent;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;
import science.aist.msbpmn.service.transformation.renderer.AbstractBpmnEventDefinitionTransformationRenderer;

import javax.xml.bind.JAXBElement;

/**
 * <p>Renderer for specific start events</p>
 *
 * @author Andreas Pointner
 */
public class BpmnEventStartEventTransformationRenderer extends BpmnStartEventTransformerRenderer {

    private final AbstractBpmnEventDefinitionTransformationRenderer<? extends TEventDefinition, BackboneElement, PlanDefinition.PlanDefinitionActionComponent> bpmnEventDefinitionTransformationRenderer;

    public BpmnEventStartEventTransformationRenderer(RendererCondition<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> rendererCondition, ObjectFactory objectFactory, AbstractBpmnEventDefinitionTransformationRenderer<? extends TEventDefinition, BackboneElement, PlanDefinition.PlanDefinitionActionComponent> bpmnEventDefinitionTransformationRenderer) {
        super(rendererCondition, objectFactory);
        this.bpmnEventDefinitionTransformationRenderer = bpmnEventDefinitionTransformationRenderer;
    }

    @Override
    public TStartEvent mapProperties(TStartEvent element, Graph<BackboneElement, Void> container, Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> currentElement) {
        element = super.mapProperties(element, container, currentElement);
        JAXBElement<? extends TEventDefinition> jaxbElement = bpmnEventDefinitionTransformationRenderer.renderElement(container, currentElement);
        if (jaxbElement != null)
            element.getEventDefinition().add(jaxbElement);
        return element;
    }
}
