/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.msbpmn.service.documentation;

import science.aist.msbpmn.service.ModelTransformationService;
import science.aist.msbpmn.service.impl.DefaultGraphBasedModelTransformationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.CustomLog;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import science.aist.gtf.template.GeneratorTemplate;
import science.aist.gtf.template.GeneratorTemplateFactory;
import science.aist.gtf.template.GeneratorTemplateRenderer;
import science.aist.gtf.template.TemplateResource;

import java.io.Closeable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * <p>Generates the documentation</p>
 *
 * @author Andreas Pointner
 */
@CustomLog
public class DocumentationGenerator implements Closeable {

    @SneakyThrows
    public static void main(String[] args) {
        try (DocumentationGenerator documentationGenerator = new DocumentationGenerator()) {
            DocumentationSamples documentationSamples = new ObjectMapper(new YAMLFactory()).readValue(DocumentationGenerator.class.getResource("/documentation-sample.yaml"), DocumentationSamples.class);
            documentationGenerator.setDocumentationSampleList(documentationSamples.getDocumentationSamples());
            documentationGenerator.process();
        }
    }

    private final ClassPathXmlApplicationContext classPathXmlApplicationContext;
    private final TemplateResource documentationTemplate;
    private final GeneratorTemplateFactory generatorTemplateFactory;
    private final GeneratorTemplateRenderer<GeneratorTemplate, Void> generatorTemplateRenderer;
    private final ModelTransformationService service;
    private final TemplateResource documentationOverviewTemplate;

    @Setter
    private List<DocumentationSample> documentationSampleList;

    @SuppressWarnings("unchecked")
    public DocumentationGenerator() {
        classPathXmlApplicationContext = new ClassPathXmlApplicationContext("/documentation-context.xml");
        documentationTemplate = classPathXmlApplicationContext.getBean("documentationTemplate", TemplateResource.class);
        documentationOverviewTemplate = classPathXmlApplicationContext.getBean("documentationOverviewTemplate", TemplateResource.class);
        generatorTemplateFactory = classPathXmlApplicationContext.getBean("generatorTemplateFactory", GeneratorTemplateFactory.class);
        generatorTemplateRenderer = classPathXmlApplicationContext.getBean("generatorTemplateRenderer", GeneratorTemplateRenderer.class);
        service = classPathXmlApplicationContext.getBean(DefaultGraphBasedModelTransformationServiceImpl.class);
    }


    public void process() {
        Objects.requireNonNull(documentationSampleList, "Please set documentation samples first.");
        log.info("Start processing documentation generation");
        Properties properties = new Properties();
        properties.put("samples", documentationSampleList);
        properties.setProperty("project", System.getProperty("user.dir"));
        GeneratorTemplate template = generatorTemplateFactory.loadGeneratorTemplate(documentationOverviewTemplate);
        template.setProperties(properties);
        generatorTemplateRenderer.renderElement(template, null);

        documentationSampleList.forEach(this::processSingle);
    }

    @Override
    public void close() {
        classPathXmlApplicationContext.close();
    }

    @SneakyThrows
    private void processSingle(DocumentationSample ds) {
        log.info("Processing {}", ds.getName());
        String plandefinitionStr = loadFile("/transformation/" + ds.getFilename() + ".plandefinition");

        Properties properties = new Properties();
        properties.setProperty("name", ds.getName());
        properties.setProperty("description", ds.getDescription());
        properties.setProperty("filename", ds.getFilename());
        if (ds.getDiscussions() != null && !ds.getDiscussions().isEmpty()) {
            properties.put("discussions", ds.getDiscussions());
        }
        properties.setProperty("fhir", plandefinitionStr);
        properties.setProperty("bpmn", service.fromFhir(plandefinitionStr));
        properties.setProperty("project", System.getProperty("user.dir"));

        GeneratorTemplate template = generatorTemplateFactory.loadGeneratorTemplate(documentationTemplate);
        template.setProperties(properties);

        generatorTemplateRenderer.renderElement(template, null);
        log.info("Completed {}", ds.getName());
    }

    @SneakyThrows
    private static String loadFile(String fileName) {
        return Files.readString(Paths.get(new ClassPathResource(fileName).getURI()));
    }

    @Getter
    @Setter
    public static class DocumentationSamples {
        private List<DocumentationSample> documentationSamples;
    }

    @Getter
    @Setter
    public static class DocumentationSample {
        private String name;
        private String description;
        private String filename;
        private List<String> discussions;
    }
}
