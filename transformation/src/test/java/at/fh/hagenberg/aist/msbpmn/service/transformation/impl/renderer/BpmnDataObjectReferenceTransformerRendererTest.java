/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl.renderer;

import at.fh.hagenberg.aist.msbpmn.service.transformation.TransformationConstants;
import at.fh.hagenberg.aist.msbpmn.service.transformation.helper.PlanDefinitionActionDataComponent;
import at.fh.hagenberg.aist.msbpmn.service.transformation.impl.EdgeType;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.DataRequirement;
import org.hl7.fhir.r4.model.PlanDefinitionActionIComponent;
import org.hl7.fhir.r4.model.PlanDefinitionActionIOComponent;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TBaseElement;
import org.omg.spec.bpmn.model.TDataObjectReference;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.builder.impl.GraphBuilderImpl;
import science.aist.gtf.graph.impl.MetaTagImpl;
import science.aist.gtf.graph.impl.VertexImpl;

import javax.xml.bind.JAXBElement;
import java.util.function.Function;

import static science.aist.jack.general.util.CastUtils.cast;

/**
 * <p>Test class for {@link BpmnDataObjectReferenceTransformerRenderer}</p>
 *
 * @author Andreas Pointner
 */

public class BpmnDataObjectReferenceTransformerRendererTest {

    BpmnDataObjectReferenceTransformerRenderer renderer = new BpmnDataObjectReferenceTransformerRenderer(new ObjectFactory());

    @Test
    public void testMapProperties() {
        // given
        PlanDefinitionActionIComponent planDefinitionActionIComponent = new PlanDefinitionActionIComponent();
        planDefinitionActionIComponent.setId("data");
        DataRequirement dataRequirement = planDefinitionActionIComponent.getDataRequirement();
        dataRequirement.setType("MyType");

        GraphBuilder<BackboneElement, Void> graphBuilder = GraphBuilderImpl.create(TransformationConstants.KEY_MAPPER());

        Vertex<PlanDefinitionActionIOComponent, Void> currentElement = cast(graphBuilder.getOrAddVertex(planDefinitionActionIComponent));
        graphBuilder.fromByKey("data").toWith(new PlanDefinitionActionDataComponent("ref")).with(e -> e.addMetaTag(new MetaTagImpl<>(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.DATA)));

        Graph<BackboneElement, Void> graph = graphBuilder.toGraph();

        // when
        TDataObjectReference tDataObjectReference = renderer.mapProperties(new TDataObjectReference(), graph, currentElement);

        // then
        Assert.assertNotNull(tDataObjectReference);
        Assert.assertNotNull(tDataObjectReference.getDataObjectRef());
        Assert.assertEquals(((TBaseElement)tDataObjectReference.getDataObjectRef()).getId(), "id_ref");
        Assert.assertNotNull(tDataObjectReference.getExtensionElements());
        Assert.assertNotNull(tDataObjectReference.getExtensionElements().getAny());
        Assert.assertEquals(tDataObjectReference.getExtensionElements().getAny().size(), 1);
        Assert.assertEquals(((JAXBElement<?>)tDataObjectReference.getExtensionElements().getAny().get(0)).getValue(), "MyType");
    }

    @Test
    public void testConstructJaxBElementMapping() {
        // given

        // when
        Function<TDataObjectReference, JAXBElement<? extends TDataObjectReference>> function = renderer.constructJaxBElementMapping();

        // then
        Assert.assertNotNull(function);
        Assert.assertNotNull(function.apply(new TDataObjectReference()));
    }

    @Test
    public void testCanRendererPos() {
        // given
        VertexImpl<PlanDefinitionActionIOComponent, Void> planDefinitionActionIOComponentVertex = new VertexImpl<>(new PlanDefinitionActionIOComponent());

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
        TDataObjectReference element = renderer.createElement();

        // then
        Assert.assertNotNull(element);
    }
}