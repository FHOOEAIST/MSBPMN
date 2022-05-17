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
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;
import science.aist.msbpmn.service.transformation.TransformationConstants;

import java.util.function.Predicate;

/**
 * <p>The condition to check if a given event is an end event</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */
@Component
public class EndEventCondition implements RendererCondition<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> {

    private final Predicate<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> condition =
            element -> element.getMetaTags().stream().anyMatch(meta -> meta.getKey().equals(TransformationConstants.END_META_TAG));


    public Predicate<Vertex<PlanDefinition.PlanDefinitionActionComponent, Void>> createCondition() {
        return this.condition;
    }

}
