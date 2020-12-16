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
 * <p>Representation of an output inside an action of a HL7® FHIR® plan definition</p>
 *
 * @author Andreas Pointner
 */
@Block()
public class PlanDefinitionActionOComponent extends PlanDefinitionActionIOComponent {
    @Child(name = "relatedRequirement", type = PlanDefinitionActionORelatedActionIOComponent.class, order=3, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Relationship to another requirement", formalDefinition="A relationship to another requirement" )
    protected List<PlanDefinitionActionORelatedActionIOComponent> relatedRequirement;

    public List<PlanDefinitionActionORelatedActionIOComponent> getRelatedRequirement() {
        if (this.relatedRequirement == null)
            this.relatedRequirement = new ArrayList<PlanDefinitionActionORelatedActionIOComponent>();
        return this.relatedRequirement;
    }

    public PlanDefinitionActionOComponent setRelatedRequirement(List<PlanDefinitionActionORelatedActionIOComponent> relatedRequirement) {
        this.relatedRequirement = relatedRequirement;
        return this;
    }

    public boolean hasRelatedRequirement() {
        if (this.relatedRequirement == null)
            return false;
        for (PlanDefinitionActionORelatedActionIOComponent item : this.relatedRequirement)
            if (!item.isEmpty())
                return true;
        return false;
    }

    public PlanDefinitionActionORelatedActionIOComponent addRelatedRequirement() { //3
        PlanDefinitionActionORelatedActionIOComponent t = new PlanDefinitionActionORelatedActionIOComponent();
        if (this.relatedRequirement == null)
            this.relatedRequirement = new ArrayList<PlanDefinitionActionORelatedActionIOComponent>();
        this.relatedRequirement.add(t);
        return t;
    }

    public PlanDefinitionActionOComponent addRelatedRequirement(PlanDefinitionActionORelatedActionIOComponent t) { //3
        if (t == null)
            return this;
        if (this.relatedRequirement == null)
            this.relatedRequirement = new ArrayList<PlanDefinitionActionORelatedActionIOComponent>();
        this.relatedRequirement.add(t);
        return this;
    }

    public PlanDefinitionActionORelatedActionIOComponent getRelatedRequirementFirstRep() {
        if (relatedRequirement.isEmpty()) {
            addRelatedRequirement();
        }
        return relatedRequirement.get(0);
    }

    @Override
    public PlanDefinitionActionOComponent copy() {
        PlanDefinitionActionOComponent dst = new PlanDefinitionActionOComponent();
        copyValues(dst);
        dst.name = name == null ? null : name.copy();
        dst.dataRequirement = dataRequirement == null ? null : dataRequirement.copy();
        dst.relatedRequirement = relatedRequirement == null ? null : relatedRequirement.stream().map(PlanDefinitionActionORelatedActionIOComponent::copy).collect(Collectors.toList());
        return dst;
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty() && relatedRequirement.isEmpty();
    }

    @Override
    public String fhirType() {
        return "PlanDefinition.action.output";
    }
}
