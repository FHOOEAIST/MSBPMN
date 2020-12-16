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
 * <p>The Actor of a Plan Definition</p>
 *
 * @author Andreas Pointner
 */
@Block
public class PlanDefinitionActorComponent extends BackboneElement {

    @Child(name = "label", type = {StringType.class}, order = 1, min = 1, max = 1, modifier = false, summary = false)
    @Description(shortDefinition = "descriptive label for the actor", formalDefinition = "descriptive label for the actor")
    private StringType label;

    @Child(name = "description", type = {MarkdownType.class}, order = 2, min = 0, max = 1, modifier = false, summary = false)
    @Description(shortDefinition = "description of how the actor fits into the overall workflow of the plan definition",
            formalDefinition = "description of how the actor fits into the overall workflow of the plan definition")
    private MarkdownType description;

    @Child(name = "options", type = {PlanDefinitionActorOptionsComponent.class}, order = 3, min = 1, max = Child.MAX_UNLIMITED, modifier = false, summary = false)
    @Description(shortDefinition = "The characteristics of the candidates that could serve as the actor",
            formalDefinition = "The characteristics of the candidates that could serve as the actor")
    private List<PlanDefinitionActorOptionsComponent> options;

    @Override
    protected void listChildren(List<Property> children) {
        super.listChildren(children);
        children.add(new Property("label", "string", "descriptive label for the actor", 1, 1, label));
        children.add(new Property("description", "markdown", "description of how the actor fits into the overall workflow of the plan definition", 0, 1, description));
        children.add(new Property("options", "PlanDefinitionActorOptionsComponent", "The characteristics of the candidates that could serve as the actor", 0, Child.MAX_UNLIMITED, options));
    }


    public StringType getLabelElement() {
        if (this.label == null) {
            if (Configuration.errorOnAutoCreate())
                throw new Error("Attempt to auto-create PlanDefinitionActorComponent.label");
            else if (Configuration.doAutoCreate())
                this.label = new StringType();
        }
        return this.label;
    }

    public boolean hasLabelElement() {
        return this.label != null && !this.label.isEmpty();
    }

    public boolean hasLabel() {
        return hasLabelElement();
    }

    public PlanDefinitionActorComponent setLabelElement(StringType label) {
        this.label = label;
        return this;
    }

    public String getLabel() {
        return this.label == null ? null : this.label.getValue();
    }

    public PlanDefinitionActorComponent setLabel(String label) {
        if (this.label == null)
            this.label = new StringType();
        this.label.setValue(label);
        return this;
    }

    public MarkdownType getDescriptionElement() {
        if (this.description == null) {
            if (Configuration.errorOnAutoCreate())
                throw new Error("Attempt to auto-create PlanDefinitionActorComponent.description");
            else if (Configuration.doAutoCreate())
                this.description = new MarkdownType();
        }
        return this.description;
    }

    public boolean hasDescriptionElement() {
        return this.description != null && !this.description.isEmpty();
    }

    public boolean hasDescription() {
        return hasDescriptionElement();
    }

    public PlanDefinitionActorComponent setDescriptionElement(MarkdownType description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return this.description == null ? null : this.description.getValue();
    }

    public PlanDefinitionActorComponent setDescription(String description) {
        if (this.description == null)
            this.description = new MarkdownType();
        this.description.setValue(description);
        return this;
    }

    public List<PlanDefinitionActorOptionsComponent> getOptions() {
        if (this.options == null)
            this.options = new ArrayList<>();
        return this.options;
    }

    public PlanDefinitionActorComponent setOptions(List<PlanDefinitionActorOptionsComponent> options) {
        this.options = options;
        return this;
    }

    public boolean hasOptions() {
        if (this.options == null)
            return false;
        for (PlanDefinitionActorOptionsComponent option : this.options)
            if (!option.isEmpty())
                return true;
        return false;
    }

    public PlanDefinitionActorOptionsComponent addOptions() {
        PlanDefinitionActorOptionsComponent planDefinitionActorOptionsComponent = new PlanDefinitionActorOptionsComponent();
        getOptions().add(planDefinitionActorOptionsComponent);
        return planDefinitionActorOptionsComponent;
    }

    public PlanDefinitionActorComponent addOptions(PlanDefinitionActorOptionsComponent planDefinitionActorOptionsComponent) {
        if (planDefinitionActorOptionsComponent != null)
            getOptions().add(planDefinitionActorOptionsComponent);
        return this;
    }

    public PlanDefinitionActorOptionsComponent getOptionsFirstRep() {
        if (!hasOptions()) {
            addOptions();
        }
        return getOptions().get(0);
    }

    @Override
    public PlanDefinitionActorComponent copy() {
        PlanDefinitionActorComponent dst = new PlanDefinitionActorComponent();
        copyValues(dst);
        dst.label = this.label == null ? null : this.label.copy();
        dst.description = this.description == null ? null : this.description.copy();
        dst.options = this.options == null ? null : options.stream().map(PlanDefinitionActorOptionsComponent::copy).collect(Collectors.toList());
        return null;
    }
}
