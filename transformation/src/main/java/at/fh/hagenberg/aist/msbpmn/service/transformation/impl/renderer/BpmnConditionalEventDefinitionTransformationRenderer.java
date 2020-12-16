/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer;

import at.fh.hagenberg.aist.msbpmn.service.transformation.renderer.AbstractBpmnEventDefinitionTransformationRenderer;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.Expression;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.TriggerDefinition;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TConditionalEventDefinition;
import org.omg.spec.bpmn.model.TFormalExpression;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;

import javax.xml.bind.JAXBElement;
import java.util.function.Function;

/**
 * <p>The renderer for an conditional event</p>
 *
 * @author Andreas Pointner
 */
@Component
public class BpmnConditionalEventDefinitionTransformationRenderer extends AbstractBpmnEventDefinitionTransformationRenderer<TConditionalEventDefinition, BackboneElement, PlanDefinition.PlanDefinitionActionComponent> {
    public BpmnConditionalEventDefinitionTransformationRenderer(ObjectFactory objectFactory) {
        super(objectFactory);
    }

    @Override
    protected Function<TConditionalEventDefinition, JAXBElement<? extends TConditionalEventDefinition>> constructJaxBElementMapping() {
        return getObjectFactory()::createConditionalEventDefinition;
    }

    @Override
    public TConditionalEventDefinition createElement() {
        return getObjectFactory().createTConditionalEventDefinition();
    }

    @Override
    public TConditionalEventDefinition mapProperties(TConditionalEventDefinition tConditionalEventDefinition, Graph<BackboneElement, Void> graph, Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> currentElement) {
        tConditionalEventDefinition = super.mapProperties(tConditionalEventDefinition, graph, currentElement);

        TFormalExpression tExpression = getObjectFactory().createTFormalExpression();

        if (currentElement.getElement().hasTrigger()) {
            TriggerDefinition triggerFirstRep = currentElement.getElement().getTriggerFirstRep();
            if (triggerFirstRep.hasCondition()) {
                Expression expression = triggerFirstRep.getCondition();
                tExpression.setLanguage(expression.getLanguage());
                tExpression.getContent().add(expression.getExpression());
            }
        }

        tConditionalEventDefinition.setCondition(tExpression);

        return tConditionalEventDefinition;
    }
}
