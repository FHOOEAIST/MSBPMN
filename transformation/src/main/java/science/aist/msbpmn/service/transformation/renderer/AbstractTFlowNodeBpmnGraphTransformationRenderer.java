/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.renderer;

import lombok.NonNull;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TFlowNode;
import science.aist.bpmn.model.BPMNHelper;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;
import science.aist.msbpmn.service.transformation.TransformationConstants;
import science.aist.msbpmn.service.transformation.impl.EdgeType;

/**
 * <p>Abstract Base class for BPMN FlowNode elements</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */
public abstract class AbstractTFlowNodeBpmnGraphTransformationRenderer<R extends TFlowNode, S extends BackboneElement, T extends PlanDefinition.PlanDefinitionActionComponent> extends AbstractBpmnGraphTransformationRenderer<R, S, T> {

    private static final String BPMN_SEQUENCE_FLOW_PREFIX = "sf_";


    public AbstractTFlowNodeBpmnGraphTransformationRenderer(RendererCondition<Vertex<T, Void>> rendererCondition, ObjectFactory objectFactory) {
        super(rendererCondition, objectFactory);
    }

    @Override
    // The default behaviour of the mapProperties method is to just return the provided element
    // if additional mapping of properties is needed, sub classes need to override this method
    public R mapProperties(@NonNull R element, Graph<S, Void> container, @NonNull Vertex<T, Void> currentElement) {
        element = super.mapProperties(element, container, currentElement);
        element.setName(currentElement.getElement().getTitle());

        for (Edge<T, Void> edge : currentElement.getEdges()) {
            if (edge.getMetaTagValue(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.class) == EdgeType.SEQUENCE) {
                if (edge.getTarget().equals(currentElement)) {
                    // incoming edge
                    element.getIncoming().add(BPMNHelper.createQName(BPMN_SEQUENCE_FLOW_PREFIX + edge.getSource().getElement().getId() + "_" + currentElement.getElement().getId()));
                } else if (edge.getSource().equals(currentElement)) {
                    // outgoing edge
                    element.getOutgoing().add(BPMNHelper.createQName(BPMN_SEQUENCE_FLOW_PREFIX + currentElement.getElement().getId() + "_" + edge.getTarget().getElement().getId()));
                }
            }
        }

        return element;
    }
}
