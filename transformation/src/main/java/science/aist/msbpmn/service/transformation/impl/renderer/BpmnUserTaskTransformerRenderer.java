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
import org.omg.spec.bpmn.model.TUserTask;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;
import science.aist.msbpmn.service.transformation.renderer.AbstractTActivityBpmnGraphTransformationRenderer;

import javax.xml.bind.JAXBElement;
import java.util.function.Function;

/**
 * <p>Renderer for a BPMN UserTasks.</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */
@Component
public class BpmnUserTaskTransformerRenderer extends AbstractTActivityBpmnGraphTransformationRenderer<TUserTask, BackboneElement, PlanDefinition.PlanDefinitionActionComponent> {

    public BpmnUserTaskTransformerRenderer(@Qualifier("userTaskCondition") RendererCondition<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> rendererCondition, ObjectFactory objectFactory) {
        super(rendererCondition, objectFactory);
    }

    @Override
    public TUserTask createElement() {
        return objectFactory.createTUserTask();
    }

    @Override
    protected Function<TUserTask, JAXBElement<? extends TUserTask>> constructJaxBElementMapping() {
        return objectFactory::createUserTask;
    }

    @Override
    public boolean canRenderer(Vertex<?, ?> vertex) {
        return vertex != null && vertex.getElement() instanceof PlanDefinition.PlanDefinitionActionComponent;
    }
}
