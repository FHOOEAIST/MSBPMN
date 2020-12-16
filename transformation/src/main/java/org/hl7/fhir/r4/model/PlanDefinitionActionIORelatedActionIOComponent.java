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
import org.hl7.fhir.exceptions.FHIRException;

import java.util.List;

/**
 * <p>Base class of a reference to an input/output object inside an action of a HL7® FHIR® plan definition</p>
 *
 * @author Andreas Pointner
 */
@Block()
public class PlanDefinitionActionIORelatedActionIOComponent extends BackboneElement {

    @Child(name = "requirementId", type = {IdType.class}, order=1, min=1, max=1, modifier=false, summary=false)
    @Description(shortDefinition="What requirement (other ActionIOComponent) is this related to", formalDefinition="The element id of the related requirement." )
    protected IdType requirementId;

    public PlanDefinitionActionIORelatedActionIOComponent() {
        super();
    }

    public PlanDefinitionActionIORelatedActionIOComponent(IdType requirementId) {
        super();
        this.requirementId = requirementId;
    }

    public IdType getRequirementIdElement() {
        if (this.requirementId == null)
            if (Configuration.errorOnAutoCreate())
                throw new Error("Attempt to auto-create PlanDefinitionActionRelatedActionComponent.actionId");
            else if (Configuration.doAutoCreate())
                this.requirementId = new IdType(); // bb
        return this.requirementId;
    }

    public boolean hasRequirementIdElement() {
        return this.requirementId != null && !this.requirementId.isEmpty();
    }

    public boolean hasRequirementId() {
        return this.requirementId != null && !this.requirementId.isEmpty();
    }

    public PlanDefinitionActionIORelatedActionIOComponent setRequirementIdElement(IdType value) {
        this.requirementId = value;
        return this;
    }

    public String getRequirementId() {
        return this.requirementId == null ? null : this.requirementId.getValue();
    }

    public PlanDefinitionActionIORelatedActionIOComponent setRequirementId(String value) {
        if (this.requirementId == null)
            this.requirementId = new IdType();
        this.requirementId.setValue(value);
        return this;
    }

    protected void listChildren(List<Property> children) {
        super.listChildren(children);
        children.add(new Property("requirementId", "id", "The element id of the related action.", 1, 1, requirementId));
    }

    @Override
    public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
        throw new UnsupportedOperationException("No sure what hash to use here");
    }

    @Override
    public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        throw new UnsupportedOperationException("No sure what hash to use here");
    }

    @Override
    public Base setProperty(int hash, String name, Base value) throws FHIRException {
        throw new UnsupportedOperationException("No sure what hash to use here");
    }

    @Override
    public Base setProperty(String name, Base value) throws FHIRException {
        throw new UnsupportedOperationException("Seems not to be needed...");
    }

    @Override
    public Base makeProperty(int hash, String name) throws FHIRException {
        throw new UnsupportedOperationException("No sure what hash to use here");
    }

    @Override
    public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        throw new UnsupportedOperationException("No sure what hash to use here");
    }

    @Override
    public Base addChild(String name) throws FHIRException {
        throw new UnsupportedOperationException("Seems not to be needed...");
    }

    @Override
    public PlanDefinitionActionIORelatedActionIOComponent copy() {
        PlanDefinitionActionIORelatedActionIOComponent dst = new PlanDefinitionActionIORelatedActionIOComponent();
        copyValues(dst);
        dst.requirementId = requirementId == null ? null : requirementId.copy();
        return dst;
    }

    @Override
    public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
            return false;
        if (!(other_ instanceof PlanDefinitionActionIORelatedActionIOComponent))
            return false;
        PlanDefinitionActionIORelatedActionIOComponent o = (PlanDefinitionActionIORelatedActionIOComponent) other_;
        return compareDeep(requirementId, o.requirementId, true);
    }

    @Override
    public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
            return false;
        if (!(other_ instanceof PlanDefinitionActionIORelatedActionIOComponent))
            return false;
        PlanDefinitionActionIORelatedActionIOComponent o = (PlanDefinitionActionIORelatedActionIOComponent) other_;
        return compareValues(requirementId, o.requirementId, true);
    }

    public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(requirementId);
    }
}
