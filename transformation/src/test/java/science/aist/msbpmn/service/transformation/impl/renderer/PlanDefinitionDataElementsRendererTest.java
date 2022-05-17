/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer;

import org.hl7.fhir.r4.model.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.builder.impl.GraphBuilderImpl;
import science.aist.gtf.graph.impl.MetaTagImpl;
import science.aist.msbpmn.service.transformation.TransformationConstants;
import science.aist.msbpmn.service.transformation.helper.PlanDefinitionActionDataComponent;
import science.aist.msbpmn.service.transformation.impl.EdgeType;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>Test class for {@link PlanDefinitionDataElementsRenderer}</p>
 *
 * @author Andreas Pointner
 */

public class PlanDefinitionDataElementsRendererTest {

    PlanDefinitionDataElementsRenderer renderer = new PlanDefinitionDataElementsRenderer(
            e -> e.addMetaTag(new MetaTagImpl<>(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.DATA_REF))
    );

    @Test
    public void testMapProperties() {
        // given
        PlanDefinition pd = new PlanDefinition();

        PlanDefinition.PlanDefinitionActionComponent action1 = pd.addAction();
        action1.setId("action1");
        action1.setTitle("action1");
        Element output = action1.addOutput()
                .setName("Data Element")
                .setDataRequirement(new DataRequirement().setType("MyDataType"))
                .setId("data_output_1");

        PlanDefinition.PlanDefinitionActionComponent action2 = pd.addAction();
        action2.setId("action2");
        action2.setTitle("action2");
        PlanDefinitionActionIComponent planDefinitionActionIComponent = action2.addInput();
        planDefinitionActionIComponent.addRelatedRequirement()
                .setRequirementId(output.getId());
        planDefinitionActionIComponent.setId("data_input_1");

        var graphBuilder = GraphBuilderImpl.<BackboneElement, Void>create(TransformationConstants.KEY_MAPPER())
                .from(action1).toWith(action2).with(e -> e.addMetaTag(new MetaTagImpl<>(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.SEQUENCE)));

        // when
        renderer.mapProperties(new HashMap<>(), graphBuilder, pd);

        // then
        var graph = graphBuilder.toGraph();
        Assert.assertEquals(graph.getVertices().size(), 4);
        Assert.assertTrue(graph.getVertices().stream().map(Vertex::getElement).anyMatch(e -> e instanceof PlanDefinitionActionDataComponent));
        Optional<Vertex<BackboneElement, Void>> dataEdgeOpt = graph.getVertices().stream().filter(e -> e.getElement() instanceof PlanDefinitionActionOComponent).findAny();
        Assert.assertTrue(dataEdgeOpt.isPresent());
        Vertex<BackboneElement, Void> dataVertex = dataEdgeOpt.get();
        Assert.assertTrue(dataVertex.getIncomingEdges().stream().map(Edge::getSource).map(Vertex::getElement).collect(Collectors.toList()).contains(action1));
        Assert.assertTrue(dataVertex.getOutgoingEdges().stream().map(Edge::getTarget).map(Vertex::getElement).collect(Collectors.toList()).contains(action2));
    }

    @Test
    public void testMapPropertiesWithInputWithoutReference() {
        // given
        PlanDefinition pd = new PlanDefinition();

        PlanDefinition.PlanDefinitionActionComponent action1 = pd.addAction();
        action1.setId("action1");
        action1.setTitle("action1");

        PlanDefinition.PlanDefinitionActionComponent action2 = pd.addAction();
        action2.setId("action2");
        action2.setTitle("action2");
        action2.addInput()
                .setName("Data Element")
                .setDataRequirement(new DataRequirement().setType("MyDataType"))
                .setId("data_input_1");

        var graphBuilder = GraphBuilderImpl.<BackboneElement, Void>create(TransformationConstants.KEY_MAPPER())
                .from(action1).toWith(action2).with(e -> e.addMetaTag(new MetaTagImpl<>(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.SEQUENCE)));

        // when
        renderer.mapProperties(new HashMap<>(), graphBuilder, pd);

        // then
        var graph = graphBuilder.toGraph();
        Assert.assertEquals(graph.getVertices().size(), 4);
        Assert.assertTrue(graph.getVertices().stream().map(Vertex::getElement).anyMatch(e -> e instanceof PlanDefinitionActionDataComponent));
        Optional<Vertex<BackboneElement, Void>> dataEdgeOpt = graph.getVertices().stream().filter(e -> e.getElement() instanceof PlanDefinitionActionIComponent).findAny();
        Assert.assertTrue(dataEdgeOpt.isPresent());
        Vertex<BackboneElement, Void> dataVertex = dataEdgeOpt.get();
        Assert.assertTrue(dataVertex.getOutgoingEdges().stream().map(Edge::getTarget).map(Vertex::getElement).collect(Collectors.toList()).contains(action2));
    }

}