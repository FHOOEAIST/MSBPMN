/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer;

import science.aist.msbpmn.service.transformation.helper.PlanDefinitionActionDataComponent;
import science.aist.msbpmn.service.transformation.renderer.AbstractBpmnGraphTransformationRenderer;
import org.hl7.fhir.r4.model.BackboneElement;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TDataObject;
import org.springframework.stereotype.Component;
import science.aist.gtf.graph.Vertex;

import javax.xml.bind.JAXBElement;
import java.util.function.Function;

/**
 * <p>Renderer for the BPMN Data Object element</p>
 *
 * @author Andreas Pointner
 */
@Component
public class BpmnDataObjectTransformerRenderer extends AbstractBpmnGraphTransformationRenderer<TDataObject, BackboneElement, PlanDefinitionActionDataComponent> {
    public BpmnDataObjectTransformerRenderer(ObjectFactory objectFactory) {
        // No condition needed, as this renderer is executed for every vertex where the decorated element is of type PlanDefinitionActionDataComponent
        super(() -> x -> true, objectFactory);
    }

    @Override
    protected Function<TDataObject, JAXBElement<? extends TDataObject>> constructJaxBElementMapping() {
        return objectFactory::createDataObject;
    }

    @Override
    public boolean canRenderer(Vertex<?, ?> vertex) {
        return vertex != null && vertex.getElement() instanceof PlanDefinitionActionDataComponent;
    }

    @Override
    public TDataObject createElement() {
        return objectFactory.createTDataObject();
    }

}
