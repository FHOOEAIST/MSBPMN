/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.transformation.impl.renderer;

import org.hl7.fhir.r4.model.Expression;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.TriggerDefinition;
import org.omg.spec.bpmn.model.ObjectFactory;
import org.omg.spec.bpmn.model.TConditionalEventDefinition;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.impl.VertexImpl;

import javax.xml.bind.JAXBElement;
import java.util.function.Function;

/**
 * <p>Test class for {@link BpmnConditionalEventDefinitionTransformationRenderer}</p>
 *
 * @author Andreas Pointner
 */

public class BpmnConditionalEventDefinitionTransformationRendererTest {

    @Test
    public void testMapProperties() {
        // given
        BpmnConditionalEventDefinitionTransformationRenderer renderer = new BpmnConditionalEventDefinitionTransformationRenderer(new ObjectFactory());
        PlanDefinition.PlanDefinitionActionComponent planDefinitionActionComponent = new PlanDefinition.PlanDefinitionActionComponent();
        TriggerDefinition triggerDefinition = planDefinitionActionComponent.addTrigger();
        Expression expression = triggerDefinition.getCondition();
        expression.setExpression("Hello World!");
        triggerDefinition.setCondition(expression);

        // when
        TConditionalEventDefinition tConditionalEventDefinition = renderer.mapProperties(new TConditionalEventDefinition(), null, new VertexImpl<>(planDefinitionActionComponent));

        // then
        Assert.assertNotNull(tConditionalEventDefinition);
        Assert.assertNotNull(tConditionalEventDefinition.getCondition());
        Assert.assertNotNull(tConditionalEventDefinition.getCondition().getContent());
        Assert.assertEquals(tConditionalEventDefinition.getCondition().getContent().size(), 1);
        Assert.assertEquals(tConditionalEventDefinition.getCondition().getContent().get(0), "Hello World!");
    }

    @Test
    public void testConstructJaxBElementMapping() {
        // given
        BpmnConditionalEventDefinitionTransformationRenderer renderer = new BpmnConditionalEventDefinitionTransformationRenderer(new ObjectFactory());

        // when
        Function<TConditionalEventDefinition, JAXBElement<? extends TConditionalEventDefinition>> tConditionalEventDefinitionJAXBElementFunction = renderer.constructJaxBElementMapping();

        // then
        Assert.assertNotNull(tConditionalEventDefinitionJAXBElementFunction);
        Assert.assertNotNull(tConditionalEventDefinitionJAXBElementFunction.apply(new TConditionalEventDefinition()));
    }

    @Test
    public void testCreateElement() {
        // given
        BpmnConditionalEventDefinitionTransformationRenderer renderer = new BpmnConditionalEventDefinitionTransformationRenderer(new ObjectFactory());

        // when
        TConditionalEventDefinition element = renderer.createElement();

        // then
        Assert.assertNotNull(element);
    }
}