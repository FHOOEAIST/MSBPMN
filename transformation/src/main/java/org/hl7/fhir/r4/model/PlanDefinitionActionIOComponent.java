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
import org.hl7.fhir.instance.model.api.IBaseBackboneElement;

import java.util.List;
import java.util.Objects;

/**
 * <p>Baseclass for input/output inside an action of a HL7® FHIR® plan definition</p>
 *
 * @author Andreas Pointner
 */
@Block()
public class PlanDefinitionActionIOComponent extends BackboneElement implements IBaseBackboneElement {
    @Child(name = "name", type = {StringType.class}, order=1, min=1, max=1, modifier=false, summary=false)
    @Description(shortDefinition="The name to identify the io element", formalDefinition="The identification of the io element" )
    protected StringType name;

    @Child(name = "dataRequirement", type = {DataRequirement.class}, order=2, min=1, max=1, modifier=false, summary=false)
    @Description(shortDefinition="The data requirement element", formalDefinition="The datarequirement element" )
    protected DataRequirement dataRequirement;

    public StringType getNameElement() {
        if (this.name == null)
            if (Configuration.errorOnAutoCreate())
                throw new Error("Attempt to auto-create PlanDefinitionActionIOComponent.name");
            else if (Configuration.doAutoCreate())
                this.name = new StringType();
        return this.name;
    }

    public boolean hasNameElement() {
        return this.name != null && !this.name.isEmpty();
    }

    public boolean hasName() {
        return hasNameElement();
    }

    public PlanDefinitionActionIOComponent setNameElement(StringType name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return this.name == null ? null : this.name.getValue();
    }

    public PlanDefinitionActionIOComponent setName(String name) {
        if (this.name == null)
            this.name = new StringType();
        this.name.setValue(name);
        return this;
    }

    public DataRequirement getDataRequirement() {
        if (this.dataRequirement == null)
            if (Configuration.errorOnAutoCreate())
                throw new Error("Attempt to auto-create PlanDefinitionActionIOComponent.name");
            else if (Configuration.doAutoCreate())
                this.dataRequirement = new DataRequirement();
        return this.dataRequirement;
    }

    public boolean hasDataRequirement() {
        return this.dataRequirement != null && !this.dataRequirement.isEmpty();
    }

    public PlanDefinitionActionIOComponent setDataRequirement(DataRequirement dataRequirement) {
        this.dataRequirement = dataRequirement;
        return this;
    }

    @Override
    protected void listChildren(List<Property> children) {
        super.listChildren(children);
        children.add(new Property("name", "string", "The name/id", 1, 1, name));
        children.add(new Property("dataRequirement", "DataRequirement", "The data requirement element", 1, 1, dataRequirement));
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
    public PlanDefinitionActionIOComponent copy() {
        PlanDefinitionActionIOComponent dst = new PlanDefinitionActionIOComponent();
        copyValues(dst);
        dst.name = name == null ? null : name.copy();
        dst.dataRequirement = dataRequirement == null ? null : dataRequirement.copy();
        return dst;
    }

    @Override
    public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
            return false;
        if (!(other_ instanceof PlanDefinitionActionIOComponent))
            return false;
        PlanDefinitionActionIOComponent o = (PlanDefinitionActionIOComponent) other_;
        return compareDeep(name, o.name, true) && compareDeep(dataRequirement, o.dataRequirement, true);
    }

    @Override
    public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
            return false;
        if (!(other_ instanceof PlanDefinitionActionIOComponent))
            return false;
        PlanDefinitionActionIOComponent o = (PlanDefinitionActionIOComponent) other_;
        return compareValues(name, o.name, true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanDefinitionActionIOComponent)) return false;
        PlanDefinitionActionIOComponent that = (PlanDefinitionActionIOComponent) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(name, dataRequirement);
    }

    @Override
    public String fhirType() {
        return "PlanDefinition.action.io";
    }
}
