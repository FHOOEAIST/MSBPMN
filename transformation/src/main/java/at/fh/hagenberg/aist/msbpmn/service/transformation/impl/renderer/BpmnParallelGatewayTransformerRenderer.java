/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer;

import at.fh.hagenberg.aist.msbpmn.service.transformation.renderer.AbstractTFlowNodeBpmnGraphTransformationRenderer;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TParallelGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;

import javax.xml.bind.JAXBElement;
import java.util.function.Function;

/**
 * <p>Renderer for a BPMN Parallel Gateway.</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */
@Component
public class BpmnParallelGatewayTransformerRenderer extends AbstractTFlowNodeBpmnGraphTransformationRenderer<TParallelGateway, BackboneElement, PlanDefinition.PlanDefinitionActionComponent> {

    public BpmnParallelGatewayTransformerRenderer(@Qualifier("parallelGatewayCondition") RendererCondition<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> rendererCondition, ObjectFactory objectFactory) {
        super(rendererCondition, objectFactory);
    }

    @Override
    public TParallelGateway createElement() {
        return objectFactory.createTParallelGateway();
    }

    @Override
    protected Function<TParallelGateway, JAXBElement<? extends TParallelGateway>> constructJaxBElementMapping() {
        return objectFactory::createParallelGateway;
    }

    @Override
    public boolean canRenderer(Vertex<?, ?> vertex) {
        return vertex != null && vertex.getElement() instanceof PlanDefinition.PlanDefinitionActionComponent;
    }
}
