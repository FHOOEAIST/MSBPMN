/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package ca.uhn.fhir.jpa.msbpmn;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Operation;
import ca.uhn.fhir.rest.server.IResourceProvider;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import science.aist.gtf.transformation.Transformer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>Created by Andreas Pointner on 02.09.2020</p>
 * <p>The transformation to BPMN</p>
 *
 * @author Andreas Pointner andreas.pointner@fh-hagenberg.at
 */
public class BpmnProvider implements IResourceProvider {

    @Autowired
    IFhirResourceDao<PlanDefinition> planDefinitionDao;

    @Autowired
    Transformer<PlanDefinition, String> planDefinitionToBpmnStringTransformer;

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return PlanDefinition.class;
    }

    @Operation(name = "$bpmn", manualRequest = true, manualResponse = true, idempotent = true)
    public void toBpmnOperation(@IdParam IdType thePlanDefinitionId, HttpServletRequest theServletRequest, HttpServletResponse theServletResponse) throws IOException {
        theServletResponse.getWriter().write(planDefinitionToBpmn(planDefinitionDao.read(thePlanDefinitionId)));
    }

    public String planDefinitionToBpmn(PlanDefinition planDefinition) {
        Objects.requireNonNull(planDefinition, "Plan Definition must not be null");
        return planDefinitionToBpmnStringTransformer.applyTransformation(planDefinition);
    }
}
