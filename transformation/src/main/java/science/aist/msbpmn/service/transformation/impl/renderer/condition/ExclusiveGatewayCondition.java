/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer.condition;

import science.aist.msbpmn.service.transformation.TransformationConstants;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.MetaTag;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;

import java.util.Optional;
import java.util.function.Predicate;


/**
 * <p>The condition to check if an ActionComponent is an exclusive gateway</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */
@Component
public class ExclusiveGatewayCondition implements RendererCondition<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> {

    /*
     * Predicate matches if the the vertex tested qualifies as BPMN Parallel Gateway
     * @see{http://www.omg.org/spec/BPMN/2.0.2/PDF/} section 10.6.2
     */
    private final Predicate<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> condition = element -> {
        // this is especially for exclusive join
        Optional<MetaTag<PlanDefinition.PlanDefinitionActionComponent>> joinSource =
                element.<PlanDefinition.PlanDefinitionActionComponent>getMetaTags().stream().filter(meta -> meta.getKey().equals(TransformationConstants.JOIN_META_TAG)).findFirst();
        if (joinSource.isPresent() && (joinSource.get().getValue().getSelectionBehavior() == PlanDefinition.ActionSelectionBehavior.EXACTLYONE || joinSource.get().getValue().getSelectionBehavior() == PlanDefinition.ActionSelectionBehavior.ATMOSTONE))
            return true;


        if (element.getElement().getGroupingBehavior() == null
                || element.getElement().getSelectionBehavior() == null)
            return false;

        // element has 1:n outgoing edges
        // element has exactly one incoming edge
        // element component has groupingBehavior=logical-group and selectionBehavior=exactly-one
        return (element.getElement().getSelectionBehavior() == PlanDefinition.ActionSelectionBehavior.EXACTLYONE
                || element.getElement().getSelectionBehavior() == PlanDefinition.ActionSelectionBehavior.ATMOSTONE)
                && element.getElement().getGroupingBehavior() == PlanDefinition.ActionGroupingBehavior.LOGICALGROUP;
    };


    public Predicate<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> createCondition() {
        return this.condition;
    }
}
