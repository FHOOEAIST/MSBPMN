/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.impl;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import science.aist.jack.exception.ExceptionUtils;
import science.aist.msbpmn.service.ModelTransformationService;
import science.aist.msbpmn.service.configuration.TransformationConfiguration;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.BiFunction;

/**
 * <p>Test class for {@link DefaultGraphBasedModelTransformationServiceImpl}</p>
 *
 * @author Andreas Schuler
 * @author Andreas Pointner
 */
@ContextConfiguration(classes = TransformationConfiguration.class)
public class DefaultGraphBasedModelTransformationServiceImplTest extends AbstractTestNGSpringContextTests {
    private static final XPathFactory xpathFactory = XPathFactory.newInstance();

    private static final BiFunction<Document, String, Boolean> booleanXpath = (document, xPathString) -> {
        try {
            String result = xpathFactory.newXPath().evaluate(xPathString, document);
            return (result != null && !result.isEmpty()) && Boolean.parseBoolean(result); // condition
        } catch (Exception e) {
            return false;
        }
    };

    @Autowired
    private ModelTransformationService service;

    @SneakyThrows
    private static String loadFile(String fileName) {
        return Files.readString(Paths.get(new ClassPathResource(fileName).getURI()));
    }

    private static Document createDocumentFromXmlString(String xmlString) {
        try {
            InputSource source = new InputSource(new StringReader(xmlString));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(source);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw ExceptionUtils.unchecked(e);
        }
    }

    /**
     * This provides a full test over every xml element that is created. all other tests, are based on the assumption
     * that if this test works, only the additional specifics of the other testcases have to be tested.
     */
    @Test
    public void testFromFhir() {
        // given
        String planDefinition = loadFile("/transformation/sequenceFlow.plandefinition");

        // when
        String bpmnString = service.fromFhir(planDefinition);

        // then
        Assert.assertNotNull(bpmnString);
        Assert.assertFalse(bpmnString.isEmpty());
        Document document = createDocumentFromXmlString(bpmnString);
        // contains expressions are needed here because start and end elements have a postfix with an id to make them unique, and we don't know them here.
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/startEvent[@name='start'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(contains(/definitions/process/startEvent[@name='start']/outgoing, '_ad_1'))"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/userTask[@name='Task 1'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(contains(/definitions/process/userTask[@name='Task 1']/incoming, '_ad_1'))"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/userTask[@name='Task 1']/outgoing='sf_ad_1_ad_2')"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/userTask[@name='Task 2'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/userTask[@name='Task 2']/incoming='sf_ad_1_ad_2')"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/userTask[@name='Task 2']/outgoing='sf_ad_2_ad_3')"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/userTask[@name='Task 3'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/userTask[@name='Task 3']/incoming='sf_ad_2_ad_3')"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(contains(/definitions/process/userTask[@name='Task 3']/outgoing, 'sf_ad_3_end'))"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/endEvent[@name='end'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/sequenceFlow[contains(@id, 'ad_1') and contains(@sourceRef, 'start') and @targetRef='id_ad_1'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/sequenceFlow[@id='sf_ad_1_ad_2' and @sourceRef='id_ad_1' and @targetRef='id_ad_2'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/sequenceFlow[@id='sf_ad_2_ad_3' and @sourceRef='id_ad_2' and @targetRef='id_ad_3'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/sequenceFlow[contains(@id, 'sf_ad_3_end') and @sourceRef='id_ad_3' and contains(@targetRef, 'end')])"));
    }

    @Test
    public void testExclusiveSplitFromFhir() {
        // given
        String planDefinition = loadFile("/transformation/exclusiveSplit.plandefinition");

        // when
        String bpmnString = service.fromFhir(planDefinition);

        // then
        Assert.assertNotNull(bpmnString);
        Assert.assertFalse(bpmnString.isEmpty());

        Document document = createDocumentFromXmlString(bpmnString);
        Assert.assertTrue(booleanXpath.apply(document, "boolean(//process/exclusiveGateway)"));
        // Assert that a condition element is available after transformation
        Assert.assertTrue(booleanXpath.apply(document, "boolean(//*/conditionExpression)"));

    }

    /* helper methods */

    @Test
    public void testParallelGatewayFromFhir() {
        // given
        String planDefinition = loadFile("/transformation/parallelSplit.plandefinition");

        // when
        String bpmnString = service.fromFhir(planDefinition);

        // then
        Assert.assertNotNull(bpmnString);
        Assert.assertFalse(bpmnString.isEmpty());

        Document document = createDocumentFromXmlString(bpmnString);
        Assert.assertTrue(booleanXpath.apply(document, "boolean(//process/parallelGateway)"));
    }

    @Test
    public void testTimedStartEventFromFhir() {
        // given
        String planDefinition = loadFile("/transformation/timedStart.plandefinition");

        // when
        String bpmnString = service.fromFhir(planDefinition);

        // then
        Assert.assertNotNull(bpmnString);
        Assert.assertFalse(bpmnString.isEmpty());

        Document document = createDocumentFromXmlString(bpmnString);
        Assert.assertTrue(booleanXpath.apply(document, "boolean(//process/startEvent/timerEventDefinition/timeDate)"));
    }

    @Test
    public void testTimedStartEventWithDurationFromFhir() {
        // given
        String planDefinition = loadFile("/transformation/timedStartDuration.plandefinition");

        // when
        String bpmnString = service.fromFhir(planDefinition);

        // then
        Assert.assertNotNull(bpmnString);
        Assert.assertFalse(bpmnString.isEmpty());

        Document document = createDocumentFromXmlString(bpmnString);
        Assert.assertTrue(booleanXpath.apply(document, "boolean(//process/startEvent/timerEventDefinition/timeDuration)"));
    }

    @Test
    public void testTimedStartEventWithRepeatFromFhir() {
        // given
        String planDefinition = loadFile("/transformation/timedStartRepeat.plandefinition");

        // when
        String bpmnString = service.fromFhir(planDefinition);

        // then
        Assert.assertNotNull(bpmnString);
        Assert.assertFalse(bpmnString.isEmpty());

        Document document = createDocumentFromXmlString(bpmnString);
        Assert.assertTrue(booleanXpath.apply(document, "boolean(//process/startEvent/timerEventDefinition/timeCycle)"));
    }

    @Test
    public void testCPGCommonRegistration() {
        // given
        String planDefinition = loadFile("/transformation/cpgCommonRegistration.plandefinition");

        // when
        String bpmnString = service.fromFhir(planDefinition);

        // then
        Assert.assertNotNull(bpmnString);
        Assert.assertFalse(bpmnString.isEmpty());

        Document document = createDocumentFromXmlString(bpmnString);
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/subProcess[@name='Registration'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/subProcess[@name='Registration']/userTask[@name='Gather identifying information'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/subProcess[@name='Registration']/userTask[@name='Patient match'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/subProcess[@name='Registration']/userTask[@name='Resolve patient match results'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/subProcess[@name='Registration']/exclusiveGateway[@name='xor_group'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/subProcess[@name='Registration']/subProcess[@name='Existing patient'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/subProcess[@name='Registration']/subProcess[@name='Existing patient']/userTask[@name='Ensure patient information is up to date'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/subProcess[@name='Registration']/subProcess[@name='Existing patient']/userTask[@name='Record patient data'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/subProcess[@name='Registration']/subProcess[@name='New patient'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/subProcess[@name='Registration']/subProcess[@name='New patient']/userTask[@name='Gather patient information'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/subProcess[@name='Registration']/subProcess[@name='New patient']/userTask[@name='Record patient data'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/subProcess[@name='Registration']/exclusiveGateway[@name='join'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/subProcess[@name='Registration']/userTask[@name='Patient summary lookup'])"));
    }

    @Test
    public void testMultipleStartAndEnd() {
        // given
        String planDefinition = loadFile("/transformation/multipleStartAndEnd.plandefinition");

        // when
        String bpmnString = service.fromFhir(planDefinition);

        // then
        Assert.assertNotNull(bpmnString);
        Assert.assertFalse(bpmnString.isEmpty());

        Document document = createDocumentFromXmlString(bpmnString);
        Assert.assertTrue(booleanXpath.apply(document, "boolean(count(//startEvent)=2)"));
    }

    @Test
    public void testActor() {
        // given
        String planDefinition = loadFile("/transformation/actors.plandefinition");

        // when
        String bpmnString = service.fromFhir(planDefinition);

        // then
        Assert.assertNotNull(bpmnString);
        Assert.assertFalse(bpmnString.isEmpty());

        Document document = createDocumentFromXmlString(bpmnString);
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/laneSet/lane[contains(@id, 'actor_1') and @name='Max Mustermann'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/laneSet/lane[contains(@id, 'actor_1')]/flowNodeRef/text()='id_ad_1')"));
        Assert.assertFalse(booleanXpath.apply(document, "boolean(/definitions/process/laneSet/lane[contains(@id, 'actor_2')]/flowNodeRef/text()='id_ad_1')"));

        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/laneSet/lane[contains(@id, 'actor_2') and @name='Maxine Musterfrau'])"));
        Assert.assertTrue(booleanXpath.apply(document, "boolean(/definitions/process/laneSet/lane[contains(@id, 'actor_2')]/flowNodeRef/text()='id_ad_2')"));
        Assert.assertFalse(booleanXpath.apply(document, "boolean(/definitions/process/laneSet/lane[contains(@id, 'actor_1')]/flowNodeRef/text()='id_ad_2')"));
    }

    @Test
    public void testPraeOpBqll() {
        // given
        String planDefinition = loadFile("/transformation/praeoperativeDiagnostic.plandefinition");

        // when
        String bpmnString = service.fromFhir(planDefinition);

        // then
        Assert.assertNotNull(bpmnString);
        Assert.assertFalse(bpmnString.isEmpty());

        Document document = createDocumentFromXmlString(bpmnString);
        Assert.assertNotNull(document);
    }

    @Test
    public void testHtep() {
        // given
        String planDefinition = loadFile("/transformation/htep.plandefinition");

        // when
        String bpmnString = service.fromFhir(planDefinition);
        System.out.println(bpmnString);

        // then
        Assert.assertNotNull(bpmnString);
        Assert.assertFalse(bpmnString.isEmpty());

        Document document = createDocumentFromXmlString(bpmnString);
        Assert.assertNotNull(document);
    }

    @Test
    public void testHernien() {
        // given
        String planDefinition = loadFile("/transformation/hernien.plandefinition");

        // when
        String bpmnString = service.fromFhir(planDefinition);
        System.out.println(bpmnString);

        // then
        Assert.assertNotNull(bpmnString);
        Assert.assertFalse(bpmnString.isEmpty());

        Document document = createDocumentFromXmlString(bpmnString);
        Assert.assertNotNull(document);
    }

    @Test(dataProvider = "testPlanDefinitionPaths")
    public void testPlanDefinition(String filename) {
        // given
        String planDefinition = loadFile(filename);

        // when
        String bpmnString = service.fromFhir(planDefinition);

        // then
        // just check if there is a result and no error for that specific plan definition. in the evaluation phase of the project we have to adapt the assert, but for the moment it is too much overhead to change time on every adaption of the transformation
        Assert.assertNotNull(bpmnString);
        Assert.assertFalse(bpmnString.isEmpty());
    }

    @DataProvider
    public Object[] testPlanDefinitionPaths() {
        return new Object[]{
                "/transformation/ancContact.plandefinition",
                "/transformation/ancContactSchedule.plandefinition",
                "/transformation/chronicKidneyDiseaseAmbulatory.plandefinition",
                "/transformation/vaCkdProtocol.plandefinition",
                "/transformation/cpgCommonPathway.plandefinition",
                "/transformation/pizza.plandefinition"
        };
    }
}