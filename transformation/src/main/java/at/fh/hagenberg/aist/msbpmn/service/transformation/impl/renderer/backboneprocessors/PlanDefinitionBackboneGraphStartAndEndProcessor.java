/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer.backboneprocessors;

import at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer.PlanDefinitionBackboneGraphComponentProcessor;
import at.fh.hagenberg.aist.msbpmn.service.transformation.renderer.condition.ContextRendererCondition;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hl7.fhir.instance.model.api.IBaseElement;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.factory.GraphFactory;
import science.aist.jack.general.util.CastUtils;

import java.util.ArrayList;
import java.util.List;

import static at.fh.hagenberg.aist.msbpmn.service.transformation.TransformationConstants.*;

/**
 * <p>Processes the start and end nodes of a plan definition</p>
 *
 * @author Andreas Pointner
 */
@RequiredArgsConstructor
@Component
public class PlanDefinitionBackboneGraphStartAndEndProcessor implements PlanDefinitionBackboneGraphComponentProcessor {

    @NonNull
    private final GraphFactory graphFactory;

    @NonNull
    private final ContextRendererCondition<List<PlanDefinition.PlanDefinitionActionComponent>, PlanDefinition.PlanDefinitionActionComponent> planDefinitionActionStartEventCondition;

    @NonNull
    private final ContextRendererCondition<List<PlanDefinition.PlanDefinitionActionComponent>, PlanDefinition.PlanDefinitionActionComponent> planDefinitionActionEndEventCondition;

    private int specialUniqueId = 1;

    private static final String START = "start";
    private static final String END = "end";

    @Override
    public void process(PlanDefinition planDefinition, List<PlanDefinition.PlanDefinitionActionComponent> list, GraphBuilder<BackboneElement, Void> graphBuilder) {
        for (PlanDefinition.PlanDefinitionActionComponent planDefinitionActionComponent : new ArrayList<>(list)) {
            if (planDefinitionActionStartEventCondition.createCondition().test(list, planDefinitionActionComponent)) {
                handleStartAction(graphBuilder, list, CastUtils.cast(graphBuilder.getOrAddVertex(planDefinitionActionComponent)));
            }
            if (planDefinitionActionEndEventCondition.createCondition().test(list, planDefinitionActionComponent)) {
                handleEndAction(graphBuilder, list, planDefinitionActionComponent);
            }
        }
    }

    private void handleStartAction(GraphBuilder<BackboneElement, Void> graphBuilder, List<PlanDefinition.PlanDefinitionActionComponent> list, Vertex<PlanDefinition.PlanDefinitionActionComponent, Void> startElement) {

        if (startElement.tryGetMetaTagValue(EVENT_META_TAG).isPresent()) {
            startElement.addMetaTag(graphFactory.createMetaTag(START_META_TAG, startElement.getElement()));
            graphBuilder.updateKey(startElement.getElement(), START + specialUniqueId, IBaseElement::setId);
            specialUniqueId++;
            return;
        }

        PlanDefinition.PlanDefinitionActionComponent startAction = new PlanDefinition.PlanDefinitionActionComponent();
        startAction.setId(START + specialUniqueId);
        specialUniqueId++;
        startAction.setTitle(START);
        // #93 copy trigger definition to start element
        startAction.getParticipant().addAll(startElement.getElement().getParticipant());
        graphBuilder.getOrAddVertex(startAction).addMetaTag(graphFactory.createMetaTag(START_META_TAG, startElement.getElement()));
        addRelation(startAction, startElement.getElement().getId());
        list.add(startAction);
    }


    private void handleEndAction(GraphBuilder<BackboneElement, Void> graphBuilder, List<PlanDefinition.PlanDefinitionActionComponent> list, PlanDefinition.PlanDefinitionActionComponent endDefinition) {
        PlanDefinition.PlanDefinitionActionComponent endAction = new PlanDefinition.PlanDefinitionActionComponent();
        endAction.setId(END + specialUniqueId);
        specialUniqueId++;
        endAction.setTitle(END);
        graphBuilder.getOrAddVertex(endAction).addMetaTag(graphFactory.createMetaTag(END_META_TAG, endDefinition));

        endAction.getParticipant().addAll(endDefinition.getParticipant());
        addRelation(endDefinition, endAction.getId());
        list.add(endAction);
    }

    private static void addRelation(PlanDefinition.PlanDefinitionActionComponent planDefinitionActionComponent, String relation) {
        PlanDefinition.PlanDefinitionActionRelatedActionComponent planDefinitionActionRelatedActionComponent = new PlanDefinition.PlanDefinitionActionRelatedActionComponent();
        planDefinitionActionRelatedActionComponent.setActionId(relation);
        planDefinitionActionComponent.getRelatedAction().add(planDefinitionActionRelatedActionComponent);
    }
}
