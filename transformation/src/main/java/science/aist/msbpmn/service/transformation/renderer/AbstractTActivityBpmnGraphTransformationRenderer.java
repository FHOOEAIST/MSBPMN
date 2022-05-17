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
import org.hl7.fhir.r4.model.PlanDefinitionActionIOComponent;
import org.omg.spec.bpmn.model.*;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.transformation.renderer.condition.RendererCondition;
import science.aist.jack.general.util.CastUtils;
import science.aist.msbpmn.service.transformation.TransformationConstants;
import science.aist.msbpmn.service.transformation.helper.IdProvider;
import science.aist.msbpmn.service.transformation.impl.EdgeType;

/**
 * <p>Created by Andreas Pointner on 14.05.2020</p>
 * <p>Abstraction for all renderer that are baesd on a TActivity</p>
 *
 * @author Andreas Pointner
 */
public abstract class AbstractTActivityBpmnGraphTransformationRenderer<R extends TActivity, S extends BackboneElement, T extends PlanDefinition.PlanDefinitionActionComponent> extends AbstractTFlowNodeBpmnGraphTransformationRenderer<R, S, T> {

    private static final String BPMN_DATA_FLOW_PREFIX = "df_";

    private static final String BPMN_PROPERTY_PREFIX = "prop_";

    public AbstractTActivityBpmnGraphTransformationRenderer(RendererCondition<Vertex<T, Void>> rendererCondition, ObjectFactory objectFactory) {
        super(rendererCondition, objectFactory);
    }

    @Override
    public R mapProperties(@NonNull R element, Graph<S, Void> container, @NonNull Vertex<T, Void> currentElement) {
        element = super.mapProperties(element, container, currentElement);
        for (Edge<T, Void> edge : currentElement.getEdges()) {
            if (edge.getMetaTagValue(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.class) == EdgeType.DATA_REF) {
                if (edge.getTarget().equals(currentElement)) {
                    // incoming edge
                    PlanDefinitionActionIOComponent ioComponent = CastUtils.<Object, PlanDefinitionActionIOComponent>cast(edge.getSource().getElement());

                    TProperty property = objectFactory.createTProperty();
                    // ad the name of the current element to the id as well, to make sure it is unique over the whole process.
                    property.setId(BPMN_PROPERTY_PREFIX + currentElement.getElement().getId() + "_" + ioComponent.getId());
                    property.setName(BPMN_PROPERTY_PREFIX + ioComponent.getName());
                    element.getProperty().add(property);

                    TDataInputAssociation tDataInputAssociation = objectFactory.createTDataInputAssociation();
                    tDataInputAssociation.setId(BPMN_DATA_FLOW_PREFIX + element.getId() + "_" + ioComponent.getId());
                    tDataInputAssociation.getSourceRef().add(objectFactory.createTDataAssociationSourceRef(new IdProvider(ioComponent.getId())));
                    tDataInputAssociation.setTargetRef(property);
                    element.getDataInputAssociation().add(tDataInputAssociation);
                } else if (edge.getSource().equals(currentElement)) {
                    // outgoing edge
                    PlanDefinitionActionIOComponent ioComponent = CastUtils.<Object, PlanDefinitionActionIOComponent>cast(edge.getTarget().getElement());
                    TDataOutputAssociation tDataOutputAssociation = objectFactory.createTDataOutputAssociation();
                    tDataOutputAssociation.setId(BPMN_DATA_FLOW_PREFIX + element.getId() + "_" + ioComponent.getId());
                    tDataOutputAssociation.setTargetRef(new IdProvider(ioComponent.getId()));
                    element.getDataOutputAssociation().add(tDataOutputAssociation);
                }
            }
        }

        return element;
    }
}
