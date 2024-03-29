/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer.condition;

import org.hl7.fhir.r4.model.PlanDefinition;
import org.springframework.stereotype.Component;
import science.aist.msbpmn.service.transformation.renderer.condition.ContextRendererCondition;

import java.util.Collection;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * <p>Checks if a action is a start action inside a list of actions</p>
 *
 * @author Andreas Pointner
 */
@Component
public class PlanDefinitionActionStartEventCondition implements ContextRendererCondition<List<PlanDefinition.PlanDefinitionActionComponent>, PlanDefinition.PlanDefinitionActionComponent> {

    private final BiPredicate<List<PlanDefinition.PlanDefinitionActionComponent>, PlanDefinition.PlanDefinitionActionComponent> predicate =
            (list, planDefinitionActionComponent) -> list.stream()
                    .filter(PlanDefinition.PlanDefinitionActionComponent::hasRelatedAction)
                    .map(PlanDefinition.PlanDefinitionActionComponent::getRelatedAction)
                    .flatMap(Collection::stream)
                    .map(PlanDefinition.PlanDefinitionActionRelatedActionComponent::getActionId)
                    .noneMatch(id -> planDefinitionActionComponent.getId().equals(id));

    @Override
    public BiPredicate<List<PlanDefinition.PlanDefinitionActionComponent>, PlanDefinition.PlanDefinitionActionComponent> createCondition() {
        return predicate;
    }
}
