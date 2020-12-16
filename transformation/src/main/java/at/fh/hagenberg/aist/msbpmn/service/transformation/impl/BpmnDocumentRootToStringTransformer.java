/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package at.fh.hagenberg.aist.msbpmn.service.transformation.impl;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.omg.spec.bpmn.model.TDefinitions;
import org.springframework.stereotype.Component;
import science.aist.bpmn.model.XMLRepository;
import science.aist.bpmn.viz.BpmnAutoLayout;
import science.aist.gtf.transformation.Transformer;

import javax.xml.bind.JAXBElement;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

/**
 * <p>Transforms a given Document Root into a BPMN Representation</p>
 *
 * @author Andreas Pointner
 */
@Component
@AllArgsConstructor
public class BpmnDocumentRootToStringTransformer implements Transformer<JAXBElement<TDefinitions>, String> {

    private final XMLRepository<TDefinitions> repository;

    @Override
    @SneakyThrows
    public String applyTransformation(@NonNull JAXBElement<TDefinitions> documentRoot) {
        BpmnAutoLayout bpmnAutoLayout = new BpmnAutoLayout(documentRoot, true);
        bpmnAutoLayout.execute();
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            repository.save(documentRoot, byteArrayOutputStream);
            return new String(byteArrayOutputStream.toByteArray(), Charset.defaultCharset());
        }
    }
}
