/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.traversal;

import lombok.CustomLog;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hl7.fhir.r4.model.BackboneElement;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.Visitor;
import science.aist.gtf.graph.factory.GraphFactory;
import science.aist.gtf.graph.factory.GraphFactoryFactory;
import science.aist.gtf.graph.traversal.TraversalStrategy;
import science.aist.jack.stream.FunctionUtil;
import science.aist.msbpmn.service.transformation.TransformationConstants;
import science.aist.msbpmn.service.transformation.impl.EdgeType;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <p>Specialized Iterator for HL7® FHIR® Graph</p>
 *
 * @author Andreas Pointner
 */
@CustomLog
@RequiredArgsConstructor
public class FhirGraphTraversalStrategy<T extends BackboneElement> implements TraversalStrategy<Vertex<T, Void>, Edge<T, Void>> {

    public static final String TRAVERSED_DATA_ELEMENT_MT = "FhirGraphTraversalStrategyTraversed";

    private final Queue<Vertex<T, Void>> queue = new ArrayDeque<>();

    private final Set<Vertex<T, Void>> visited = new HashSet<>();

    private final GraphFactory graphFactory = GraphFactoryFactory.getDefaultFactory();

    @NonNull
    private final Graph<T, Void> graph;

    private static boolean hasTraversedDataNode(Vertex<?, Void> v) {
        try {
            return v.getMetaTagValue(TRAVERSED_DATA_ELEMENT_MT);
        } catch (Exception e) {
            log.debug(e);
            return false;
        }
    }

    @Override
    public void traverse(Visitor<Vertex<T, Void>> visitor) {
        traverse(visitor, FunctionUtil.emptyConsumer()::accept);
    }

    @Override
    public void traverse(Visitor<Vertex<T, Void>> vertexVisitor, Visitor<Edge<T, Void>> edgeVisitor) {
        List<Vertex<T, Void>> startNodes = graph.getMetaTagValue(TransformationConstants.START_NODE_META_TAG);
        startNodes.forEach(queue::offer);
        while (!queue.isEmpty()) {
            Vertex<T, Void> poll = queue.poll();
            vertexVisitor.visit(poll);

            // Process sequences
            List<Edge<T, Void>> sequenceEdges = getOutgoingEdgesByType(poll, EdgeType.SEQUENCE);
            sequenceEdges.forEach(edgeVisitor::visit);
            sequenceEdges.stream()
                    .map(Edge::getTarget)
                    .filter(visited::add)
                    .forEach(queue::offer);

            Consumer<Vertex<T, Void>> visitDataNode = dataElementRef -> getOutgoingEdgesByType(dataElementRef, EdgeType.DATA)
                    .stream()
                    .map(Edge::getTarget)
                    .filter(((Predicate<? super Vertex<T, Void>>) FhirGraphTraversalStrategy::hasTraversedDataNode).negate())
                    .forEach(v -> {
                        v.addMetaTag(graphFactory.createMetaTag(TRAVERSED_DATA_ELEMENT_MT, true));
                        vertexVisitor.visit(v);
                    });

            // Process data
            for (Edge<T, Void> dataEdge : getOutgoingEdgesByType(poll, EdgeType.DATA_REF)) {
                edgeVisitor.visit(dataEdge);
                Vertex<T, Void> dataElementRef = dataEdge.getTarget();

                if (!hasTraversedDataNode(dataElementRef)) {
                    dataElementRef.addMetaTag(graphFactory.createMetaTag(TRAVERSED_DATA_ELEMENT_MT, true));
                    vertexVisitor.visit(dataElementRef);
                    // Process data element starting from dataRef
                    visitDataNode.accept(dataElementRef);
                    getOutgoingEdgesByType(dataElementRef, EdgeType.DATA_REF).forEach(edgeVisitor::visit);
                }
            }

            // check if there are incoming data element that do not have any incoming data_ref edges (thus means that this could be external data that would never be visited)
            for (Edge<T, Void> incomingDataEdge : poll.getIncomingEdges()
                    .stream()
                    .filter(e -> e.getMetaTagValue(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.class) == EdgeType.DATA_REF)
                    .filter(e -> e.getSource()
                            .getIncomingEdges()
                            .stream()
                            .noneMatch(innerE -> innerE.getMetaTagValue(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.class) == EdgeType.DATA_REF)
                    ).collect(Collectors.toList())) {
                edgeVisitor.visit(incomingDataEdge);
                Vertex<T, Void> dataElementRef = incomingDataEdge.getSource();
                if (!hasTraversedDataNode(dataElementRef)) {
                    dataElementRef.addMetaTag(graphFactory.createMetaTag(TRAVERSED_DATA_ELEMENT_MT, true));
                    vertexVisitor.visit(dataElementRef);
                    // Process data element starting from dataRef
                    visitDataNode.accept(dataElementRef);
                }
            }
        }
    }

    private List<Edge<T, Void>> getOutgoingEdgesByType(Vertex<T, Void> vertex, EdgeType type) {
        return vertex.getEdges().stream()
                .filter(e -> e.getSource().equals(vertex))
                .filter(e -> e.getMetaTagValue(TransformationConstants.EDGE_TYPE_META_TAG, EdgeType.class) == type)
                .collect(Collectors.toList());
    }

}
