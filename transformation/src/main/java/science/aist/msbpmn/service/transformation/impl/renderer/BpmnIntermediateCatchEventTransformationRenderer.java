/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer;

import science.aist.msbpmn.service.transformation.renderer.AbstractBpmnEventDefinitionTransformationRenderer;
import science.aist.msbpmn.service.transformation.renderer.AbstractTFlowNodeBpmnGraphTransformationRenderer;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TEventDefinition;
import org.omg.spec.bpmn.model.TIntermediateCatchEvent;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;

import javax.xml.bind.JAXBElement;
import java.util.function.Function;

/**
 * <p>Renderer for Conditional Events in BPMN</p>
 *
 * @author Andreas Pointner
 */
public class BpmnIntermediateCatchEventTransformationRenderer extends AbstractTFlowNodeBpmnGraphTransformationRenderer<TIntermediateCatchEvent, BackboneElement, PlanDefinition.PlanDefinitionActionComponent> {

    private final AbstractBpmnEventDefinitionTransformationRenderer<? extends TEventDefinition, BackboneElement, PlanDefinition.PlanDefinitionActionComponent> bpmnEventDefinitionTransformationRenderer;

    public BpmnIntermediateCatchEventTransformationRenderer(RendererCondition<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> rendererCondition, ObjectFactory objectFactory, AbstractBpmnEventDefinitionTransformationRenderer<? extends TEventDefinition, BackboneElement, PlanDefinition.PlanDefinitionActionComponent> bpmnEventDefinitionTransformationRenderer) {
        super(rendererCondition, objectFactory);
        this.bpmnEventDefinitionTransformationRenderer = bpmnEventDefinitionTransformationRenderer;
    }

    @Override
    public TIntermediateCatchEvent createElement() {
        return objectFactory.createTIntermediateCatchEvent();
    }

    @Override
    public TIntermediateCatchEvent mapProperties(TIntermediateCatchEvent element, Graph<BackboneElement, Void> container, Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> currentElement) {
        element = super.mapProperties(element, container, currentElement);
        element.getEventDefinition().add(bpmnEventDefinitionTransformationRenderer.renderElement(container, currentElement));
        return element;
    }

    @Override
    protected Function<TIntermediateCatchEvent, JAXBElement<? extends TIntermediateCatchEvent>> constructJaxBElementMapping() {
        return objectFactory::createIntermediateCatchEvent;
    }

    @Override
    public boolean canRenderer(Vertex<?, ?> vertex) {
        return vertex != null && vertex.getElement() instanceof PlanDefinition.PlanDefinitionActionComponent;
    }

}
