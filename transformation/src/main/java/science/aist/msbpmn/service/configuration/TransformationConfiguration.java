/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.configuration;

import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TFlowElement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import science.aist.bpmn.model.impl.BPMNTDefinitionsRepository;
import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.Vertex;
import science.aist.gtf.graph.factory.GraphFactory;
import science.aist.gtf.graph.factory.GraphFactoryFactory;
import science.aist.gtf.transformation.Transformer;
import science.aist.gtf.transformation.renderer.TransformationRender;
import science.aist.msbpmn.service.transformation.TransformationConstants;
import science.aist.msbpmn.service.transformation.impl.BpmnDocumentRootToStringTransformer;
import science.aist.msbpmn.service.transformation.impl.BpmnGraphTransformer;
import science.aist.msbpmn.service.transformation.impl.EdgeType;
import science.aist.msbpmn.service.transformation.impl.PlanDefinitionToGraphTransformer;
import science.aist.msbpmn.service.transformation.impl.renderer.*;
import science.aist.msbpmn.service.transformation.impl.renderer.backboneprocessors.*;
import science.aist.msbpmn.service.transformation.impl.renderer.condition.ConditionalEventCondition;
import science.aist.msbpmn.service.transformation.impl.renderer.condition.StartConditionalEventCondition;
import science.aist.msbpmn.service.transformation.impl.renderer.condition.TimedEventCondition;
import science.aist.msbpmn.service.transformation.impl.renderer.condition.TimedStartEventCondition;
import science.aist.msbpmn.service.transformation.renderer.MultiConditionalGraphTransformationRenderer;

import javax.xml.bind.JAXBElement;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * <p>Configuration for Transformation Service</p>
 *
 * @author Andreas Pointner
 */
@Configuration
@ComponentScan("science.aist.msbpmn.service")
public class TransformationConfiguration {

    @Bean
    public ObjectFactory bpmnObjectFactory() {
        return new ObjectFactory();
    }

    @Bean
    public GraphFactory graphFactory() {
        return GraphFactoryFactory.getDefaultFactory();
    }

    @Bean
    public BiConsumer<Edge<?, Void>, EdgeType> edgeTypeConsumer() {
        return (e, t) -> e.addMetaTag(graphFactory().createMetaTag(TransformationConstants.EDGE_TYPE_META_TAG, t));
    }

    @Bean
    public Consumer<Edge<BackboneElement, Void>> edgeDataRefConsumer() {
        return e -> edgeTypeConsumer().accept(e, EdgeType.DATA_REF);
    }

    @Bean
    public Consumer<Edge<BackboneElement, Void>> edgeSequenceConsumer() {
        return e -> edgeTypeConsumer().accept(e, EdgeType.SEQUENCE);
    }

    @Bean
    public Consumer<Edge<BackboneElement, Void>> edgeParticipantConsumer() {
        return e -> edgeTypeConsumer().accept(e, EdgeType.PARTICIPANT);
    }

    @Bean
    public BPMNTDefinitionsRepository bpmntDefinitionsRepository() {
        return new BPMNTDefinitionsRepository();
    }

    @Bean
    public BpmnIntermediateCatchEventTransformationRenderer bpmnIntermediateCatchEventConditionalEventDefinitionTransformationRenderer(
            ConditionalEventCondition rendererCondition,
            ObjectFactory objectFactory,
            BpmnConditionalEventDefinitionTransformationRenderer bpmnConditionalEventDefinitionTransformationRenderer) {
        return new BpmnIntermediateCatchEventTransformationRenderer(rendererCondition, objectFactory, bpmnConditionalEventDefinitionTransformationRenderer);
    }

    @Bean
    public BpmnIntermediateCatchEventTransformationRenderer bpmnIntermediateCatchEventStartEventDefinitionTransformationRenderer(
            TimedEventCondition rendererCondition,
            ObjectFactory objectFactory,
            BpmnTTimerEventDefinitionTransformerRenderer bpmnConditionalEventDefinitionTransformationRenderer) {
        return new BpmnIntermediateCatchEventTransformationRenderer(rendererCondition, objectFactory, bpmnConditionalEventDefinitionTransformationRenderer);
    }

    @Bean
    public BpmnEventStartEventTransformationRenderer bpmnTimedStartEventTransformationRenderer(
            TimedStartEventCondition rendererCondition,
            ObjectFactory objectFactory,
            BpmnTTimerEventDefinitionTransformerRenderer bpmnTTimerEventDefinitionTransformerRenderer) {
        return new BpmnEventStartEventTransformationRenderer(rendererCondition, objectFactory, bpmnTTimerEventDefinitionTransformerRenderer);
    }

    @Bean
    public BpmnEventStartEventTransformationRenderer bpmnConditionalStartEventTransformationRenderer(
            StartConditionalEventCondition rendererCondition,
            ObjectFactory objectFactory,
            BpmnConditionalEventDefinitionTransformationRenderer bpmnTTimerEventDefinitionTransformerRenderer) {
        return new BpmnEventStartEventTransformationRenderer(rendererCondition, objectFactory, bpmnTTimerEventDefinitionTransformerRenderer);
    }

    @Bean
    public TransformationRender<JAXBElement<? extends TFlowElement>, ?, Graph<BackboneElement, Void>,
            Vertex<BackboneElement, Void>> multiConditionalGraphTransformationRenderer(
            @Lazy BpmnSubprocessTransformationRenderer subprocessTransformationRenderer,
            BpmnEventStartEventTransformationRenderer bpmnTimedStartEventTransformationRenderer,
            BpmnEventStartEventTransformationRenderer bpmnConditionalStartEventTransformationRenderer,
            BpmnStartEventTransformerRenderer bpmnStartEventTransformerRenderer,
            BpmnEndEventTransformerRenderer bpmnEndEventTransformerRenderer,
            BpmnIntermediateCatchEventTransformationRenderer bpmnIntermediateCatchEventStartEventDefinitionTransformationRenderer,
            BpmnIntermediateCatchEventTransformationRenderer bpmnIntermediateCatchEventConditionalEventDefinitionTransformationRenderer,
            BpmnParallelGatewayTransformerRenderer bpmnParallelGatewayTransformerRenderer,
            BpmnExclusiveGatewayTransformerRenderer bpmnExclusiveGatewayTransformerRenderer,
            BpmnUserTaskTransformerRenderer bpmnUserTaskTransformerRenderer,
            BpmnDataObjectReferenceTransformerRenderer bpmnDataObjectReferenceTransformerRenderer,
            BpmnDataObjectTransformerRenderer bpmnDataObjectTransformerRenderer
    ) {
        return new MultiConditionalGraphTransformationRenderer<>(List.of(
                subprocessTransformationRenderer,
                bpmnConditionalStartEventTransformationRenderer,
                bpmnTimedStartEventTransformationRenderer,
                bpmnStartEventTransformerRenderer,
                bpmnEndEventTransformerRenderer,
                bpmnIntermediateCatchEventStartEventDefinitionTransformationRenderer,
                bpmnIntermediateCatchEventConditionalEventDefinitionTransformationRenderer,
                bpmnParallelGatewayTransformerRenderer,
                bpmnExclusiveGatewayTransformerRenderer,
                bpmnUserTaskTransformerRenderer,
                bpmnDataObjectReferenceTransformerRenderer,
                bpmnDataObjectTransformerRenderer
        ));
    }

    @Bean
    public Transformer<PlanDefinition, String> planDefinitionToBpmnStringTransformer(PlanDefinitionToGraphTransformer planDefinitionToGraphTransformer,
                                                                                     BpmnGraphTransformer bpmnGraphTransformer,
                                                                                     BpmnDocumentRootToStringTransformer bpmnDocumentRootToStringTransformer) {
        return planDefinitionToGraphTransformer
                .andThen(bpmnGraphTransformer)
                .andThen(bpmnDocumentRootToStringTransformer);
    }

    @Bean
    public PlanDefinitionBackboneGraphComponentProcessor planDefinitionBackboneGraphComponentsProcessor(
            PlanDefinitionBackboneGraphParticipantsProcessor planDefinitionBackboneGraphParticipantsProcessor,
            PlanDefinitionBackboneGraphStartAndEndProcessor planDefinitionBackboneGraphStartAndEndProcessor,
            PlanDefinitionBackboneGraphTriggerProcessor planDefinitionBackboneGraphTriggerProcessor,
            @Lazy PlanDefinitionBackboneGraphVerticesProcessor planDefinitionBackboneGraphVerticesProcessor
    ) {
        return new MultiPlanDefinitionBackboneGraphComponentProcessor(List.of(
                planDefinitionBackboneGraphTriggerProcessor,
                planDefinitionBackboneGraphStartAndEndProcessor,
                planDefinitionBackboneGraphParticipantsProcessor,
                planDefinitionBackboneGraphVerticesProcessor
        ));
    }
}
