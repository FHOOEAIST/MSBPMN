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
 * <p>Representation of Options inside a actor of a HL7® FHIR® plan definition</p>
 *
 * @author Andreas Pointner
 */
@Block
public class PlanDefinitionActorOptionsComponent extends BackboneElement {

    /**
     * The type of participant in the action.
     */
    @Child(name = "type", type = {CodeType.class}, order=1, min=1, max=1, modifier=false, summary=false)
    @Description(shortDefinition="patient | practitioner | related-person | device", formalDefinition="The type of participant in the action." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/action-participant-type")
    protected Enumeration<PlanDefinition.ActionParticipantType> type;

    /**
     * The role the participant should play in performing the described action.
     */
    @Child(name = "role", type = {CodeableConcept.class}, order=2, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="E.g. Nurse, Surgeon, Parent", formalDefinition="The role the participant should play in performing the described action." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/action-participant-role")
    protected CodeableConcept role;

    private static final long serialVersionUID = -1152013659L;

    /**
     * Constructor
     */
    public PlanDefinitionActorOptionsComponent() {
        super();
    }

    /**
     * Constructor
     */
    public PlanDefinitionActorOptionsComponent(Enumeration<PlanDefinition.ActionParticipantType> type) {
        super();
        this.type = type;
    }

    /**
     * @return {@link #type} (The type of participant in the action.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
     */
    public Enumeration<PlanDefinition.ActionParticipantType> getTypeElement() {
        if (this.type == null)
            if (Configuration.errorOnAutoCreate())
                throw new Error("Attempt to auto-create PlanDefinitionActionParticipantComponent.type");
            else if (Configuration.doAutoCreate())
                this.type = new Enumeration<PlanDefinition.ActionParticipantType>(new PlanDefinition.ActionParticipantTypeEnumFactory()); // bb
        return this.type;
    }

    public boolean hasTypeElement() {
        return this.type != null && !this.type.isEmpty();
    }

    public boolean hasType() {
        return this.type != null && !this.type.isEmpty();
    }

    /**
     * @param value {@link #type} (The type of participant in the action.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
     */
    public PlanDefinitionActorOptionsComponent setTypeElement(Enumeration<PlanDefinition.ActionParticipantType> value) {
        this.type = value;
        return this;
    }

    /**
     * @return The type of participant in the action.
     */
    public PlanDefinition.ActionParticipantType getType() {
        return this.type == null ? null : this.type.getValue();
    }

    /**
     * @param value The type of participant in the action.
     */
    public PlanDefinitionActorOptionsComponent setType(PlanDefinition.ActionParticipantType value) {
        if (this.type == null)
            this.type = new Enumeration<PlanDefinition.ActionParticipantType>(new PlanDefinition.ActionParticipantTypeEnumFactory());
        this.type.setValue(value);
        return this;
    }

    /**
     * @return {@link #role} (The role the participant should play in performing the described action.)
     */
    public CodeableConcept getRole() {
        if (this.role == null)
            if (Configuration.errorOnAutoCreate())
                throw new Error("Attempt to auto-create PlanDefinitionActionParticipantComponent.role");
            else if (Configuration.doAutoCreate())
                this.role = new CodeableConcept(); // cc
        return this.role;
    }

    public boolean hasRole() {
        return this.role != null && !this.role.isEmpty();
    }

    /**
     * @param value {@link #role} (The role the participant should play in performing the described action.)
     */
    public PlanDefinitionActorOptionsComponent setRole(CodeableConcept value) {
        this.role = value;
        return this;
    }

    protected void listChildren(List<Property> children) {
        super.listChildren(children);
        children.add(new Property("type", "code", "The type of participant in the action.", 0, 1, type));
        children.add(new Property("role", "CodeableConcept", "The role the participant should play in performing the described action.", 0, 1, role));
    }

    @Override
    public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("type")) {
            value = new PlanDefinition.ActionParticipantTypeEnumFactory().fromType(castToCode(value));
            this.type = (Enumeration) value; // Enumeration<ActionParticipantType>
        } else if (name.equals("role")) {
            this.role = castToCodeableConcept(value); // CodeableConcept
        } else
            return super.setProperty(name, value);
        return value;
    }

    @Override
    public Base addChild(String name) throws FHIRException {
        if (name.equals("type")) {
            throw new FHIRException("Cannot call addChild on a primitive type PlanDefinition.type");
        }
        else if (name.equals("role")) {
            this.role = new CodeableConcept();
            return this.role;
        }
        else
            return super.addChild(name);
    }

    public PlanDefinitionActorOptionsComponent copy() {
        PlanDefinitionActorOptionsComponent dst = new PlanDefinitionActorOptionsComponent();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.role = role == null ? null : role.copy();
        return dst;
    }

    @Override
    public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
            return false;
        if (!(other_ instanceof PlanDefinitionActorOptionsComponent))
            return false;
        PlanDefinitionActorOptionsComponent o = (PlanDefinitionActorOptionsComponent) other_;
        return compareDeep(type, o.type, true) && compareDeep(role, o.role, true);
    }

    @Override
    public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
            return false;
        if (!(other_ instanceof PlanDefinitionActorOptionsComponent))
            return false;
        PlanDefinitionActorOptionsComponent o = (PlanDefinitionActorOptionsComponent) other_;
        return compareValues(type, o.type, true);
    }

    public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(type, role);
    }

    public String fhirType() {
        return "PlanDefinition.actor.options";

    }
}
