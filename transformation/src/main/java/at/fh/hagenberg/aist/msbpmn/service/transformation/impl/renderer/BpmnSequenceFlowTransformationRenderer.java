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
import org.hl7.fhir.r4.model.PlanDefinition;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TExpression;
import org.omg.spec.bpmn.model.TSequenceFlow;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.transformation.renderer.TransformationRender;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;

import javax.xml.bind.JAXBElement;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>Renderer for the sequence flow element. So the connectors between the {@link org.omg.spec.bpmn.model.TFlowNode}s</p>
 *
 * @author Andreas Pointner
 */
@Component
@AllArgsConstructor
public class BpmnSequenceFlowTransformationRenderer implements TransformationRender<Optional<JAXBElement<? extends TSequenceFlow>>, TSequenceFlow, Graph<BackboneElement, Void>, Edge<PlanDefinition.PlanDefinitionActionComponent, Void>> {

    private static final String BPMN_SEQUENCE_FLOW_PREFIX = "sf_";

    private final ObjectFactory objectFactory;

    private final RendererCondition<Edge<PlanDefinition.PlanDefinitionActionComponent, Void>> condition;

    @Override
    public Optional<JAXBElement<? extends TSequenceFlow>> renderElement(Graph<BackboneElement, Void> vertices, Edge<PlanDefinition.PlanDefinitionActionComponent, Void> planDefinitionActionComponentEdge) {
        if (!canRenderer(planDefinitionActionComponentEdge)) return Optional.empty();
        return Optional.ofNullable(condition.createCondition().test(planDefinitionActionComponentEdge) ? objectFactory.createSequenceFlow(mapProperties(createElement(), vertices, planDefinitionActionComponentEdge)) : null);
    }

    boolean canRenderer(Edge<?, Void> edge) {
        return edge.getSource().getElement() instanceof PlanDefinition.PlanDefinitionActionComponent
                && edge.getTarget().getElement() instanceof PlanDefinition.PlanDefinitionActionComponent;
    }

    @Override
    public TSequenceFlow createElement() {
        return objectFactory.createTSequenceFlow();
    }

    @Override
    public TSequenceFlow mapProperties(TSequenceFlow sequenceFlow, Graph<BackboneElement, Void> vertices, Edge<PlanDefinition.PlanDefinitionActionComponent, Void> element) {
        sequenceFlow.setId(BPMN_SEQUENCE_FLOW_PREFIX + element.getSource().getElement().getId() + "_" + element.getTarget().getElement().getId());

        // This needs a object reference here, as we cannot access the new BPMN element anymore we need to create some helper object that is of exact the type that is required to extract the id.
        sequenceFlow.setSourceRef(new IdProvider(element.getSource().getElement().getId())); // id from sourceTask
        sequenceFlow.setTargetRef(new IdProvider(element.getTarget().getElement().getId())); // id from targetTask

        // Assumption: each edge that has an action target with a condition set
        // needs to be associated with that condition
        processConditions(sequenceFlow, element);

        return sequenceFlow;
    }

    protected void processConditions(TSequenceFlow flow, Edge<PlanDefinition.PlanDefinitionActionComponent, Void> edgeParam) {
        PlanDefinition.PlanDefinitionActionComponent element = edgeParam.getTarget().getElement();
        if (element.hasCondition() && !element.hasTrigger()) {
            // Problem is, how to interpret several conditions
            TExpression expression = objectFactory.createTExpression();
            expression.setId(flow.getId() + "_condition");

            String conditionExpression = element.getConditionFirstRep().getExpression().getExpression();
            Objects.requireNonNull(conditionExpression, "Empty condition expression leads to NullPointerException in eclipse ecore.");

            expression.getContent().add(conditionExpression);
            flow.setConditionExpression(expression);
            flow.setName(conditionExpression);
        }

        // special condition if start and end are both xor (with at most once)
        if (PlanDefinition.ActionSelectionBehavior.ATMOSTONE == edgeParam.getSource().getElement().getSelectionBehavior()
                && edgeParam.getTarget().getElement().getId().startsWith("join_of_")) {
            TExpression expression = objectFactory.createTExpression();
            expression.setId(flow.getId() + "_condition");
            expression.getContent().add("else");
            flow.setConditionExpression(expression);
            flow.setName("else");
        }
    }

}
