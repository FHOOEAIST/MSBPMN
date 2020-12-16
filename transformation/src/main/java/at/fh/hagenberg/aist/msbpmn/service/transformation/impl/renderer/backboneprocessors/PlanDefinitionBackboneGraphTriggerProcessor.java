/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer.backboneprocessors;

import at.fh.hagenberg.aist.msbpmn.service.transformation.BpmnEventTypes;
import at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer.PlanDefinitionBackboneGraphComponentProcessor;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.TriggerDefinition;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.impl.MetaTagImpl;

import java.util.ArrayList;
import java.util.List;

import static at.fh.hagenberg.aist.msbpmn.service.transformation.TransformationConstants.EVENT_META_TAG;

/**
 * <p>Processing implementation for Triggers</p>
 *
 * @author Andreas Pointner
 */
@Component
public class PlanDefinitionBackboneGraphTriggerProcessor implements PlanDefinitionBackboneGraphComponentProcessor {
    @Override
    public void process(PlanDefinition planDefinition, List<PlanDefinition.PlanDefinitionActionComponent> list, GraphBuilder<BackboneElement, Void> graphBuilder) {
        // copy list to avoid concurrent modification exception, because we add elements
        new ArrayList<>(list).stream()
                .filter(PlanDefinition.PlanDefinitionActionComponent::hasTrigger)
                .filter(actionDefinition ->
                        TriggerDefinition.TriggerType.NAMEDEVENT == actionDefinition.getTriggerFirstRep().getType()
                                || TriggerDefinition.TriggerType.PERIODIC == actionDefinition.getTriggerFirstRep().getType())
                .forEach(actionDefinition -> {
                    // there is a problem if the trigger has an in/output because it is added to both elements
                    PlanDefinition.PlanDefinitionActionComponent trigger = actionDefinition.copy();
                    graphBuilder.getOrAddVertex(trigger).addMetaTag(new MetaTagImpl<>(EVENT_META_TAG, BpmnEventTypes.getByTriggerType(actionDefinition.getTriggerFirstRep().getType())));

                    // remove the trigger and the condition from the original element, as they are now in the trigger element
                    actionDefinition.setTrigger(null);
                    actionDefinition.setCondition(null);
                    actionDefinition.setId(actionDefinition.getId() + "_triggerAction");

                    trigger.getRelatedAction().clear();
                    addRelation(trigger, actionDefinition.getId());
                    list.add(trigger);
                });
    }

    private static void addRelation(PlanDefinition.PlanDefinitionActionComponent planDefinitionActionComponent, String relation) {
        PlanDefinition.PlanDefinitionActionRelatedActionComponent planDefinitionActionRelatedActionComponent = new PlanDefinition.PlanDefinitionActionRelatedActionComponent();
        planDefinitionActionRelatedActionComponent.setActionId(relation);
        planDefinitionActionComponent.getRelatedAction().add(planDefinitionActionRelatedActionComponent);
    }
}
