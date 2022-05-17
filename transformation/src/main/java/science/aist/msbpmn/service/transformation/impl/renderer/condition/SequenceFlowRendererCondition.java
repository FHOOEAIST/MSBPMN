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
import science.aist.msbpmn.service.transformation.impl.EdgeType;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;

import java.util.function.Predicate;

/**
 * <p>Edge Sequence flow condition</p>
 *
 * @author Andreas Pointner
 */
@Component
public class SequenceFlowRendererCondition implements RendererCondition<Edge<PlanDefinition.PlanDefinitionActionComponent, Void>> {
    private final Predicate<Edge<PlanDefinition.PlanDefinitionActionComponent, Void>> condition =
            e -> e.tryGetMetaTagValue(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.class)
                    .map(x -> x == EdgeType.SEQUENCE)
                    .orElse(false);

    @Override
    public Predicate<Edge<PlanDefinition.PlanDefinitionActionComponent, Void>> createCondition() {
        return condition;
    }
}
