/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer.backboneprocessors;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.Element;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.PlanDefinitionActorComponent;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.factory.GraphFactory;
import science.aist.msbpmn.service.transformation.helper.BackboneIdProvider;
import science.aist.msbpmn.service.transformation.impl.renderer.PlanDefinitionBackboneGraphComponentProcessor;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static science.aist.msbpmn.service.transformation.TransformationConstants.PARTICIPANTS_COLL_META_TAG;

/**
 * <p>Processes the participants of a plan definition</p>
 *
 * @author Andreas Pointner
 */
@Component
@AllArgsConstructor
public class PlanDefinitionBackboneGraphParticipantsProcessor implements PlanDefinitionBackboneGraphComponentProcessor {

    @NonNull
    private final GraphFactory graphFactory;

    @NonNull
    private final Consumer<Edge<BackboneElement, Void>> edgeParticipantConsumer;

    @SuppressWarnings("squid:S1698")
    // we want an == comparison between actions and planDefinition.getAction() as we want to check if it is the same object
    @Override
    public void process(PlanDefinition planDefinition, List<PlanDefinition.PlanDefinitionActionComponent> actions, GraphBuilder<BackboneElement, Void> graphBuilder) {
        if (!(planDefinition.getAction() == actions && planDefinition.hasActor())) return;

        Map<String, PlanDefinitionActorComponent> actorCache = planDefinition.getActor().stream()
                .collect(Collectors.toMap(Element::getId, Function.identity()));
        Set<String> usedActors = new HashSet<>();

        // Collecting all actions:
        List<PlanDefinition.PlanDefinitionActionComponent> allActions = new ArrayList<>();
        for (PlanDefinition.PlanDefinitionActionComponent action : actions) {
            extractActions(allActions, action);
        }

        allActions.stream()
                .filter(PlanDefinition.PlanDefinitionActionComponent::hasParticipant)
                .forEach(planDefinitionActionComponent -> planDefinitionActionComponent.getParticipant().forEach(
                        participant -> {
                            usedActors.add(participant.getActor());
                            graphBuilder
                                    .from(actorCache.get(participant.getActor()))
                                    .toWith(planDefinitionActionComponent)
                                    .with(edgeParticipantConsumer);
                        })
                );

        graphBuilder.getOrAddVertex(new BackboneIdProvider("participantHelperElement"))
                .addMetaTag(graphFactory.createMetaTag(PARTICIPANTS_COLL_META_TAG, true));

        actorCache.entrySet()
                .stream()
                // Ignore unused actors
                .filter(es -> usedActors.contains(es.getKey()))
                .map(Map.Entry::getValue)
                .forEach(p -> graphBuilder.fromByKey("participantHelperElement").to(p));
    }

    /**
     * This is used to extract the actions from the current process, because there are groups for xor / parallel split
     * with actions in it, that would be missed otherwise.
     *
     * @param actionComponents All current collected action Components
     * @param current          The current element to process
     */
    public void extractActions(List<PlanDefinition.PlanDefinitionActionComponent> actionComponents, PlanDefinition.PlanDefinitionActionComponent current) {
        actionComponents.add(current);
        if (current.hasAction() && current.hasGroupingBehavior() && current.getGroupingBehavior() == PlanDefinition.ActionGroupingBehavior.LOGICALGROUP) {
            for (PlanDefinition.PlanDefinitionActionComponent planDefinitionActionComponent : current.getAction()) {
                extractActions(actionComponents, planDefinitionActionComponent);
            }
        }
    }
}
