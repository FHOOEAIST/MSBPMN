/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.hl7.fhir.r4.model;

import ca.uhn.fhir.model.api.annotation.Block;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Representation of an input inside an action of a HL7® FHIR® plan definition</p>
 *
 * @author Andreas Pointner
 */
@Block()
public class PlanDefinitionActionIComponent extends PlanDefinitionActionIOComponent {

    @Child(name = "relatedRequirement", type = PlanDefinitionActionIRelatedActionIOComponent.class, order=3, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Relationship to another requirement", formalDefinition="A relationship to another requirement" )
    protected List<PlanDefinitionActionIRelatedActionIOComponent> relatedRequirement;

    public List<PlanDefinitionActionIRelatedActionIOComponent> getRelatedRequirement() {
        if (this.relatedRequirement == null)
            this.relatedRequirement = new ArrayList<PlanDefinitionActionIRelatedActionIOComponent>();
        return this.relatedRequirement;
    }

    public PlanDefinitionActionIComponent setRelatedRequirement(List<PlanDefinitionActionIRelatedActionIOComponent> relatedRequirement) {
        this.relatedRequirement = relatedRequirement;
        return this;
    }

    public boolean hasRelatedRequirement() {
        if (this.relatedRequirement == null)
            return false;
        for (PlanDefinitionActionIRelatedActionIOComponent item : this.relatedRequirement)
            if (!item.isEmpty())
                return true;
        return false;
    }

    public PlanDefinitionActionIRelatedActionIOComponent addRelatedRequirement() { //3
        PlanDefinitionActionIRelatedActionIOComponent t = new PlanDefinitionActionIRelatedActionIOComponent();
        if (this.relatedRequirement == null)
            this.relatedRequirement = new ArrayList<PlanDefinitionActionIRelatedActionIOComponent>();
        this.relatedRequirement.add(t);
        return t;
    }

    public PlanDefinitionActionIComponent addRelatedRequirement(PlanDefinitionActionIRelatedActionIOComponent t) { //3
        if (t == null)
            return this;
        if (this.relatedRequirement == null)
            this.relatedRequirement = new ArrayList<PlanDefinitionActionIRelatedActionIOComponent>();
        this.relatedRequirement.add(t);
        return this;
    }

    public PlanDefinitionActionIRelatedActionIOComponent getRelatedRequirementFirstRep() {
        if (getRelatedRequirement().isEmpty()) {
            addRelatedRequirement();
        }
        return getRelatedRequirement().get(0);
    }

    @Override
    public PlanDefinitionActionIComponent copy() {
        PlanDefinitionActionIComponent dst = new PlanDefinitionActionIComponent();
        copyValues(dst);
        dst.name = name == null ? null : name.copy();
        dst.dataRequirement = dataRequirement == null ? null : dataRequirement.copy();
        dst.relatedRequirement = relatedRequirement == null ? null : relatedRequirement.stream().map(PlanDefinitionActionIRelatedActionIOComponent::copy).collect(Collectors.toList());
        return dst;
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty() && relatedRequirement.isEmpty();
    }

    @Override
    public String fhirType() {
        return "PlanDefinition.action.input";
    }
}
