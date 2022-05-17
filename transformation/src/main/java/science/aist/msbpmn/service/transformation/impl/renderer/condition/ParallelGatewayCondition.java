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
import science.aist.gtf.graph.MetaTag;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;
import science.aist.msbpmn.service.transformation.TransformationConstants;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * <p>The condition to check if an ActionComponent is an parallel gateway</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */
@Component
public class ParallelGatewayCondition implements RendererCondition<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> {


    /*
     * Predicate matches if the the vertex tested qualifies as BPMN Parallel Gateway
     * @see{http://www.omg.org/spec/BPMN/2.0.2/PDF/} section 10.6.4
     *
     */
    private final Predicate<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> condition = vertex -> {
        Optional<MetaTag<PlanDefinition.PlanDefinitionActionComponent>> joinSource =
                vertex.<PlanDefinition.PlanDefinitionActionComponent>getMetaTags().stream().filter(meta -> meta.getKey().equals(TransformationConstants.JOIN_META_TAG)).findFirst();
        if (joinSource.isPresent() && joinSource.get().getValue().getSelectionBehavior() == PlanDefinition.ActionSelectionBehavior.ALL)
            return true;

        if (vertex.getElement().getGroupingBehavior() == null
                || vertex.getElement().getSelectionBehavior() == null)
            return false;

        // element component has groupingBehavior=logical-group and selectionBehavior=all
        return vertex.getElement().getSelectionBehavior() == PlanDefinition.ActionSelectionBehavior.ALL
                && vertex.getElement().getGroupingBehavior() == PlanDefinition.ActionGroupingBehavior.LOGICALGROUP;
    };


    @Override
    public Predicate<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> createCondition() {
        return this.condition;
    }
}
