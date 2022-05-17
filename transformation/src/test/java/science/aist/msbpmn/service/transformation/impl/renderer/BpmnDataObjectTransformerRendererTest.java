/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer;

import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TDataObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.impl.VertexImpl;
import science.aist.msbpmn.service.transformation.helper.PlanDefinitionActionDataComponent;

import javax.xml.bind.JAXBElement;
import java.util.function.Function;

/**
 * <p>Test class for {@link BpmnDataObjectTransformerRenderer}</p>
 *
 * @author Andreas Pointner
 */

public class BpmnDataObjectTransformerRendererTest {

    private final BpmnDataObjectTransformerRenderer renderer = new BpmnDataObjectTransformerRenderer(new ObjectFactory());

    @Test
    public void testConstructJaxBElementMapping() {
        // given

        // when
        Function<TDataObject, JAXBElement<? extends TDataObject>> tDataObjectJAXBElementFunction = renderer.constructJaxBElementMapping();

        // then
        Assert.assertNotNull(tDataObjectJAXBElementFunction);
        Assert.assertNotNull(tDataObjectJAXBElementFunction.apply(new TDataObject()));
    }

    @Test
    public void testCanRendererPos() {
        // given
        VertexImpl<PlanDefinitionActionDataComponent, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new PlanDefinitionActionDataComponent(""));

        // when
        boolean b = renderer.canRenderer(planDefinitionActionIOComponentVertex);

        // then
        Assert.assertTrue(b);
    }

    @Test
    public void testCanRendererNeg() {
        // given
        VertexImpl<Object, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new Object());

        // when
        boolean b = renderer.canRenderer(planDefinitionActionIOComponentVertex);

        // then
        Assert.assertFalse(b);
    }

    @Test
    public void testCreateElement() {
        // given

        // when
        TDataObject element = renderer.createElement();

        // then
        Assert.assertNotNull(element);
    }
}