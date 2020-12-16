
# ANC Contact Schedule

Transformation of an adapted version of the ANC Contact Schedule process as defined [here](http://hl7.org/fhir/uv/cpg/2019Sep/PlanDefinition-anc-contact-schedule.xml.html).

## Discussions

 * Each of the concats was modified to have the correct trigger definition according to the defined conditions.
 * There maybe a missing trigger of the last action?

## Graph

<script type="text/javascript" src="https://unpkg.com/bpmn-js@7.2.1/dist/bpmn-navigated-viewer.production.min.js"></script>
Usage: Mouse Click + Move: Scroll around. Ctrl + Mouse Wheel: Zoom.

<div id="container" style="width: 100%; height: 500px; border: 1px solid lightgray; overflow:auto;"></div>

<script type="text/javascript" src="../js/fhir2bpmn/ancContactSchedule.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.0.0/styles/darcula.min.css"></link>
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.0.0/highlight.min.js"></script>


## HL7 FHIR

```xml
<!--
  ~ Copyright (c) 2020 the original author or authors.
  ~ DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
  ~
  ~ This Source Code Form is subject to the terms of the Mozilla Public
  ~ License, v. 2.0. If a copy of the MPL was not distributed with this
  ~ file, You can obtain one at https://mozilla.org/MPL/2.0/.
  -->

<PlanDefinition xmlns="http://hl7.org/fhir">
    <id value="anc-contact-schedule"/>
    <text>
        <status value="generated"/>
        <div xmlns="http://www.w3.org/1999/xhtml"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: anc-contact-schedule</p><p><b>url</b>: <a href="http://fhir.org/guides/who/anc-cds/PlanDefinition/anc-contact-schedule">http://fhir.org/guides/who/anc-cds/PlanDefinition/anc-contact-schedule</a></p><p><b>identifier</b>: PlanDefinition_ANC_Contact_Schedule (OFFICIAL)</p><p><b>version</b>: 0.1.0</p><p><b>name</b>: PlanDefinition_ANC_Contact_Schedule</p><p><b>title</b>: PlanDefinition - WHO ANC Guideline Contact Schedule</p><p><b>type</b>: Clinical Protocol <span style="background: LightGoldenRodYellow">(Details : {http://terminology.hl7.org/CodeSystem/plan-definition-type code 'clinical-protocol' = 'Clinical Protocol', given as 'Clinical Protocol'})</span></p><p><b>status</b>: draft</p><p><b>experimental</b>: true</p><p><b>date</b>: May 15, 2019 12:00:00 AM</p><p><b>useContext</b>: </p><p><b>jurisdiction</b>: World <span style="background: LightGoldenRodYellow">(Details : {http://unstats.un.org/unsd/methods/m49/m49.htm code '001' = 'World', given as 'World'})</span></p><p><b>copyright</b>: © WHO 2019+.</p><p><b>library</b>: <a href="Library/anc-common">Library/anc-common</a></p><blockquote><p><b>action</b></p><p><b>title</b>: Contact 1</p><p><b>description</b>: ANC Contact 1: up to 12 weeks</p><p><b>code</b>: Guideline-based Care <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'guideline-based-care' = 'Guideline-based Care)</span></p><h3>Conditions</h3><table class="grid"><tr><td>-</td><td><b>Kind</b></td><td><b>Expression</b></td></tr><tr><td>*</td><td>applicability</td><td/></tr></table><blockquote><p><b>participant</b></p><p><b>type</b>: patient</p><p><b>role</b>: Patient <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code 'C000' = 'Patient', given as 'Patient'})</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Midwifery associate professional <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code '3222' = 'Midwifery associate professional', given as 'Midwifery associate professional'})</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Midwifery professional <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code '2222' = 'Midwifery professional', given as 'Midwifery professional'})</span></p></blockquote></blockquote><blockquote><p><b>action</b></p><p><b>title</b>: Contact 2</p><p><b>description</b>: ANC Contact 2: 20 weeks</p><p><b>code</b>: Guideline-based Care <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'guideline-based-care' = 'Guideline-based Care)</span></p><h3>Conditions</h3><table class="grid"><tr><td>-</td><td><b>Kind</b></td><td><b>Expression</b></td></tr><tr><td>*</td><td>applicability</td><td/></tr></table><blockquote><p><b>participant</b></p><p><b>type</b>: patient</p><p><b>role</b>: Patient <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code 'C000' = 'Patient', given as 'Patient'})</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Midwifery associate professional <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code '3222' = 'Midwifery associate professional', given as 'Midwifery associate professional'})</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Midwifery professional <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code '2222' = 'Midwifery professional', given as 'Midwifery professional'})</span></p></blockquote></blockquote><blockquote><p><b>action</b></p><p><b>title</b>: Contact 3</p><p><b>description</b>: ANC Contact 3: 26 weeks</p><p><b>code</b>: Guideline-based Care <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'guideline-based-care' = 'Guideline-based Care)</span></p><h3>Conditions</h3><table class="grid"><tr><td>-</td><td><b>Kind</b></td><td><b>Expression</b></td></tr><tr><td>*</td><td>applicability</td><td/></tr></table><blockquote><p><b>participant</b></p><p><b>type</b>: patient</p><p><b>role</b>: Patient <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code 'C000' = 'Patient', given as 'Patient'})</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Midwifery associate professional <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code '3222' = 'Midwifery associate professional', given as 'Midwifery associate professional'})</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Midwifery professional <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code '2222' = 'Midwifery professional', given as 'Midwifery professional'})</span></p></blockquote></blockquote><blockquote><p><b>action</b></p><p><b>title</b>: Contact 4</p><p><b>description</b>: ANC Contact 4: 30 weeks</p><p><b>code</b>: Guideline-based Care <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'guideline-based-care' = 'Guideline-based Care)</span></p><h3>Conditions</h3><table class="grid"><tr><td>-</td><td><b>Kind</b></td><td><b>Expression</b></td></tr><tr><td>*</td><td>applicability</td><td/></tr></table><blockquote><p><b>participant</b></p><p><b>type</b>: patient</p><p><b>role</b>: Patient <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code 'C000' = 'Patient', given as 'Patient'})</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Midwifery associate professional <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code '3222' = 'Midwifery associate professional', given as 'Midwifery associate professional'})</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Midwifery professional <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code '2222' = 'Midwifery professional', given as 'Midwifery professional'})</span></p></blockquote></blockquote><blockquote><p><b>action</b></p><p><b>title</b>: Contact 5</p><p><b>description</b>: ANC Contact 5: 34 weeks</p><p><b>code</b>: Guideline-based Care <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'guideline-based-care' = 'Guideline-based Care)</span></p><h3>Conditions</h3><table class="grid"><tr><td>-</td><td><b>Kind</b></td><td><b>Expression</b></td></tr><tr><td>*</td><td>applicability</td><td/></tr></table><blockquote><p><b>participant</b></p><p><b>type</b>: patient</p><p><b>role</b>: Patient <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code 'C000' = 'Patient', given as 'Patient'})</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Midwifery associate professional <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code '3222' = 'Midwifery associate professional', given as 'Midwifery associate professional'})</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Midwifery professional <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code '2222' = 'Midwifery professional', given as 'Midwifery professional'})</span></p></blockquote></blockquote><blockquote><p><b>action</b></p><p><b>title</b>: Contact 6</p><p><b>description</b>: ANC Contact 6: 36 weeks</p><p><b>code</b>: Guideline-based Care <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'guideline-based-care' = 'Guideline-based Care)</span></p><h3>Conditions</h3><table class="grid"><tr><td>-</td><td><b>Kind</b></td><td><b>Expression</b></td></tr><tr><td>*</td><td>applicability</td><td/></tr></table><blockquote><p><b>participant</b></p><p><b>type</b>: patient</p><p><b>role</b>: Patient <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code 'C000' = 'Patient', given as 'Patient'})</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Midwifery associate professional <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code '3222' = 'Midwifery associate professional', given as 'Midwifery associate professional'})</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Midwifery professional <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code '2222' = 'Midwifery professional', given as 'Midwifery professional'})</span></p></blockquote></blockquote><blockquote><p><b>action</b></p><p><b>title</b>: Contact 7</p><p><b>description</b>: ANC Contact 7: 38 weeks</p><p><b>code</b>: Guideline-based Care <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'guideline-based-care' = 'Guideline-based Care)</span></p><h3>Conditions</h3><table class="grid"><tr><td>-</td><td><b>Kind</b></td><td><b>Expression</b></td></tr><tr><td>*</td><td>applicability</td><td/></tr></table><blockquote><p><b>participant</b></p><p><b>type</b>: patient</p><p><b>role</b>: Patient <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code 'C000' = 'Patient', given as 'Patient'})</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Midwifery associate professional <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code '3222' = 'Midwifery associate professional', given as 'Midwifery associate professional'})</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Midwifery professional <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code '2222' = 'Midwifery professional', given as 'Midwifery professional'})</span></p></blockquote></blockquote><blockquote><p><b>action</b></p><p><b>title</b>: Contact 8</p><p><b>description</b>: ANC Contact 8: 40 weeks</p><p><b>code</b>: Guideline-based Care <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'guideline-based-care' = 'Guideline-based Care)</span></p><h3>Conditions</h3><table class="grid"><tr><td>-</td><td><b>Kind</b></td><td><b>Expression</b></td></tr><tr><td>*</td><td>applicability</td><td/></tr></table><blockquote><p><b>participant</b></p><p><b>type</b>: patient</p><p><b>role</b>: Patient <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code 'C000' = 'Patient', given as 'Patient'})</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Midwifery associate professional <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code '3222' = 'Midwifery associate professional', given as 'Midwifery associate professional'})</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Midwifery professional <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code '2222' = 'Midwifery professional', given as 'Midwifery professional'})</span></p></blockquote></blockquote><blockquote><p><b>action</b></p><p><b>title</b>: Delivery</p><p><b>description</b>: ANC Delivery: 41+ weeks</p></blockquote></div>
    </text>
    <url
            value="http://fhir.org/guides/who/anc-cds/PlanDefinition/anc-contact-schedule"/>
    <identifier>
        <use value="official"/>
        <value value="PlanDefinition_ANC_Contact_Schedule"/>
    </identifier>
    <version value="0.1.0"/>
    <name value="PlanDefinition_ANC_Contact_Schedule"/>
    <title value="PlanDefinition - WHO ANC Guideline Contact Schedule"/>
    <type>
        <coding>
            <system value="http://terminology.hl7.org/CodeSystem/plan-definition-type"/>
            <code value="clinical-protocol"/>
            <display value="Clinical Protocol"/>
        </coding>
    </type>
    <status value="draft"/>
    <experimental value="true"/>
    <date value="2019-05-15T00:00:00-04:00"/>
    <useContext>
        <code>
            <system value="http://terminology.hl7.org/CodeSystem/usage-context-type"/>
            <code value="focus"/>
        </code>
        <valueCodeableConcept>
            <coding>
                <system value="http://snomed.info/sct"/>
                <code value="77386006"/>
                <display value="Pregnant (finding)"/>
            </coding>
        </valueCodeableConcept>
    </useContext>
    <jurisdiction>
        <coding>
            <system value="http://unstats.un.org/unsd/methods/m49/m49.htm"/>
            <code value="001"/>
            <display value="World"/>
        </coding>
    </jurisdiction>
    <copyright value="© WHO 2019+."/>
    <library value="Library/anc-common"/>
    <action>
        <id value="ad_01"/> <!-- added -->
        <title value="Contact 1"/>
        <description value="ANC Contact 1: up to 12 weeks"/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="guideline-based-care"/>
            </coding>
        </code>
        <trigger> <!-- added -->
            <type value="periodic"/> <!-- added -->
            <condition> <!-- changed moved to trigger -->
                <language value="text/cql"/>
                <expression value="Up to 12 weeks gestation"/>
            </condition>
        </trigger> <!-- added -->
        <!-- not supported in this way anymore see: https://jira.hl7.org/browse/FHIR-20825 -->
<!--        <participant>-->
<!--            <type value="patient"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="C000"/>-->
<!--                    <display value="Patient"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="3222"/>-->
<!--                    <display value="Midwifery associate professional"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="2222"/>-->
<!--                    <display value="Midwifery professional"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
        <relatedAction> <!-- added -->
            <actionId value="ad_02"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action>
        <id value="ad_02"/> <!-- added -->
        <title value="Contact 2"/>
        <description value="ANC Contact 2: 20 weeks"/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="guideline-based-care"/>
            </coding>
        </code>
        <trigger> <!-- added -->
            <type value="periodic"/> <!-- added -->
            <condition> <!-- changed moved to trigger -->
                <language value="text/cql"/>
                <expression value="20 weeks gestation"/>
            </condition>
        </trigger> <!-- added -->
        <!-- not supported in this way anymore see: https://jira.hl7.org/browse/FHIR-20825 -->
<!--        <participant>-->
<!--            <type value="patient"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="C000"/>-->
<!--                    <display value="Patient"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="3222"/>-->
<!--                    <display value="Midwifery associate professional"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="2222"/>-->
<!--                    <display value="Midwifery professional"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
        <relatedAction> <!-- added -->
            <actionId value="ad_03"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action>
        <id value="ad_03"/> <!-- added -->
        <title value="Contact 3"/>
        <description value="ANC Contact 3: 26 weeks"/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="guideline-based-care"/>
            </coding>
        </code>
        <trigger> <!-- added -->
            <type value="periodic"/> <!-- added -->
            <condition> <!-- changed moved to trigger -->
                <language value="text/cql"/>
                <expression value="26 weeks gestation"/>
            </condition>
        </trigger> <!-- added -->
        <!-- not supported in this way anymore see: https://jira.hl7.org/browse/FHIR-20825 -->
<!--        <participant>-->
<!--            <type value="patient"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="C000"/>-->
<!--                    <display value="Patient"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="3222"/>-->
<!--                    <display value="Midwifery associate professional"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="2222"/>-->
<!--                    <display value="Midwifery professional"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
        <relatedAction> <!-- added -->
            <actionId value="ad_04"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action>
        <id value="ad_04"/> <!-- added -->
        <title value="Contact 4"/>
        <description value="ANC Contact 4: 30 weeks"/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="guideline-based-care"/>
            </coding>
        </code>
        <trigger> <!-- added -->
            <type value="periodic"/> <!-- added -->
            <condition> <!-- changed moved to trigger -->
                <language value="text/cql"/>
                <expression value="30 weeks gestation"/>
            </condition>
        </trigger> <!-- added -->
        <!-- not supported in this way anymore see: https://jira.hl7.org/browse/FHIR-20825 -->
<!--        <participant>-->
<!--            <type value="patient"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="C000"/>-->
<!--                    <display value="Patient"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="3222"/>-->
<!--                    <display value="Midwifery associate professional"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="2222"/>-->
<!--                    <display value="Midwifery professional"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
        <relatedAction> <!-- added -->
            <actionId value="ad_05"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action>
        <id value="ad_05"/> <!-- added -->
        <title value="Contact 5"/>
        <description value="ANC Contact 5: 34 weeks"/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="guideline-based-care"/>
            </coding>
        </code>
        <trigger> <!-- added -->
            <type value="periodic"/> <!-- added -->
            <condition> <!-- changed moved to trigger -->
                <language value="text/cql"/>
                <expression value="34 weeks gestation"/>
            </condition>
        </trigger> <!-- added -->
        <!-- not supported in this way anymore see: https://jira.hl7.org/browse/FHIR-20825 -->
<!--        <participant>-->
<!--            <type value="patient"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="C000"/>-->
<!--                    <display value="Patient"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="3222"/>-->
<!--                    <display value="Midwifery associate professional"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="2222"/>-->
<!--                    <display value="Midwifery professional"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
        <relatedAction> <!-- added -->
            <actionId value="ad_06"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action>
        <id value="ad_06"/> <!-- added -->
        <title value="Contact 6"/>
        <description value="ANC Contact 6: 36 weeks"/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="guideline-based-care"/>
            </coding>
        </code>
        <trigger> <!-- added -->
            <type value="periodic"/> <!-- added -->
            <condition> <!-- changed moved to trigger -->
                <language value="text/cql"/>
                <expression value="36 weeks gestation"/>
            </condition>
        </trigger> <!-- added -->
        <!-- not supported in this way anymore see: https://jira.hl7.org/browse/FHIR-20825 -->
<!--        <participant>-->
<!--            <type value="patient"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="C000"/>-->
<!--                    <display value="Patient"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="3222"/>-->
<!--                    <display value="Midwifery associate professional"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="2222"/>-->
<!--                    <display value="Midwifery professional"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
        <relatedAction> <!-- added -->
            <actionId value="ad_07"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action>
        <id value="ad_07"/> <!-- added -->
        <title value="Contact 7"/>
        <description value="ANC Contact 7: 38 weeks"/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="guideline-based-care"/>
            </coding>
        </code>
        <trigger> <!-- added -->
            <type value="periodic"/> <!-- added -->
            <condition> <!-- changed moved to trigger -->
                <language value="text/cql"/>
                <expression value="38 weeks gestation"/>
            </condition>
        </trigger> <!-- added -->
        <!-- not supported in this way anymore see: https://jira.hl7.org/browse/FHIR-20825 -->
<!--        <participant>-->
<!--            <type value="patient"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="C000"/>-->
<!--                    <display value="Patient"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="3222"/>-->
<!--                    <display value="Midwifery associate professional"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="2222"/>-->
<!--                    <display value="Midwifery professional"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
        <relatedAction> <!-- added -->
            <actionId value="ad_08"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action>
        <id value="ad_08"/> <!-- added -->
        <title value="Contact 8"/>
        <description value="ANC Contact 8: 40 weeks"/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="guideline-based-care"/>
            </coding>
        </code>
        <trigger> <!-- added -->
            <type value="periodic"/> <!-- added -->
            <condition> <!-- changed moved to trigger -->
                <language value="text/cql"/>
                <expression value="40 weeks gestation"/>
            </condition>
        </trigger> <!-- added -->
        <!-- not supported in this way anymore see: https://jira.hl7.org/browse/FHIR-20825 -->
<!--        <participant>-->
<!--            <type value="patient"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="C000"/>-->
<!--                    <display value="Patient"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="3222"/>-->
<!--                    <display value="Midwifery associate professional"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="2222"/>-->
<!--                    <display value="Midwifery professional"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
        <relatedAction> <!-- added -->
            <actionId value="ad_09"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action>
        <id value="ad_09"/> <!-- added -->
        <title value="Delivery"/>
        <description value="ANC Delivery: 41+ weeks"/>
    </action>
</PlanDefinition>
```

## BPMN

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="PlanDefinition_ANC_Contact_Schedule" id="PlanDefinition_ANC_Contact_Schedule">
        <startEvent isInterrupting="false" parallelMultiple="false" name="Contact 1" id="id_start35">
            <outgoing>sf_start35_ad_01_triggerAction</outgoing>
            <timerEventDefinition id="event_start35">
                <timeDuration id="event_start35_timeExpression">Up to 12 weeks gestation</timeDuration>
            </timerEventDefinition>
        </startEvent>
        <sequenceFlow sourceRef="id_start35" targetRef="id_ad_01_triggerAction" id="sf_start35_ad_01_triggerAction"/>
        <userTask name="Contact 1" id="id_ad_01_triggerAction">
            <incoming>sf_start35_ad_01_triggerAction</incoming>
            <outgoing>sf_ad_01_triggerAction_ad_02</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_01_triggerAction" targetRef="id_ad_02" id="sf_ad_01_triggerAction_ad_02"/>
        <intermediateCatchEvent name="Contact 2" id="id_ad_02">
            <incoming>sf_ad_01_triggerAction_ad_02</incoming>
            <outgoing>sf_ad_02_ad_02_triggerAction</outgoing>
            <timerEventDefinition id="event_ad_02">
                <timeDuration id="event_ad_02_timeExpression">20 weeks gestation</timeDuration>
            </timerEventDefinition>
        </intermediateCatchEvent>
        <sequenceFlow sourceRef="id_ad_02" targetRef="id_ad_02_triggerAction" id="sf_ad_02_ad_02_triggerAction"/>
        <userTask name="Contact 2" id="id_ad_02_triggerAction">
            <incoming>sf_ad_02_ad_02_triggerAction</incoming>
            <outgoing>sf_ad_02_triggerAction_ad_03</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_02_triggerAction" targetRef="id_ad_03" id="sf_ad_02_triggerAction_ad_03"/>
        <intermediateCatchEvent name="Contact 3" id="id_ad_03">
            <incoming>sf_ad_02_triggerAction_ad_03</incoming>
            <outgoing>sf_ad_03_ad_03_triggerAction</outgoing>
            <timerEventDefinition id="event_ad_03">
                <timeDuration id="event_ad_03_timeExpression">26 weeks gestation</timeDuration>
            </timerEventDefinition>
        </intermediateCatchEvent>
        <sequenceFlow sourceRef="id_ad_03" targetRef="id_ad_03_triggerAction" id="sf_ad_03_ad_03_triggerAction"/>
        <userTask name="Contact 3" id="id_ad_03_triggerAction">
            <incoming>sf_ad_03_ad_03_triggerAction</incoming>
            <outgoing>sf_ad_03_triggerAction_ad_04</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_03_triggerAction" targetRef="id_ad_04" id="sf_ad_03_triggerAction_ad_04"/>
        <intermediateCatchEvent name="Contact 4" id="id_ad_04">
            <incoming>sf_ad_03_triggerAction_ad_04</incoming>
            <outgoing>sf_ad_04_ad_04_triggerAction</outgoing>
            <timerEventDefinition id="event_ad_04">
                <timeDuration id="event_ad_04_timeExpression">30 weeks gestation</timeDuration>
            </timerEventDefinition>
        </intermediateCatchEvent>
        <sequenceFlow sourceRef="id_ad_04" targetRef="id_ad_04_triggerAction" id="sf_ad_04_ad_04_triggerAction"/>
        <userTask name="Contact 4" id="id_ad_04_triggerAction">
            <incoming>sf_ad_04_ad_04_triggerAction</incoming>
            <outgoing>sf_ad_04_triggerAction_ad_05</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_04_triggerAction" targetRef="id_ad_05" id="sf_ad_04_triggerAction_ad_05"/>
        <intermediateCatchEvent name="Contact 5" id="id_ad_05">
            <incoming>sf_ad_04_triggerAction_ad_05</incoming>
            <outgoing>sf_ad_05_ad_05_triggerAction</outgoing>
            <timerEventDefinition id="event_ad_05">
                <timeDuration id="event_ad_05_timeExpression">34 weeks gestation</timeDuration>
            </timerEventDefinition>
        </intermediateCatchEvent>
        <sequenceFlow sourceRef="id_ad_05" targetRef="id_ad_05_triggerAction" id="sf_ad_05_ad_05_triggerAction"/>
        <userTask name="Contact 5" id="id_ad_05_triggerAction">
            <incoming>sf_ad_05_ad_05_triggerAction</incoming>
            <outgoing>sf_ad_05_triggerAction_ad_06</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_05_triggerAction" targetRef="id_ad_06" id="sf_ad_05_triggerAction_ad_06"/>
        <intermediateCatchEvent name="Contact 6" id="id_ad_06">
            <incoming>sf_ad_05_triggerAction_ad_06</incoming>
            <outgoing>sf_ad_06_ad_06_triggerAction</outgoing>
            <timerEventDefinition id="event_ad_06">
                <timeDuration id="event_ad_06_timeExpression">36 weeks gestation</timeDuration>
            </timerEventDefinition>
        </intermediateCatchEvent>
        <sequenceFlow sourceRef="id_ad_06" targetRef="id_ad_06_triggerAction" id="sf_ad_06_ad_06_triggerAction"/>
        <userTask name="Contact 6" id="id_ad_06_triggerAction">
            <incoming>sf_ad_06_ad_06_triggerAction</incoming>
            <outgoing>sf_ad_06_triggerAction_ad_07</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_06_triggerAction" targetRef="id_ad_07" id="sf_ad_06_triggerAction_ad_07"/>
        <intermediateCatchEvent name="Contact 7" id="id_ad_07">
            <incoming>sf_ad_06_triggerAction_ad_07</incoming>
            <outgoing>sf_ad_07_ad_07_triggerAction</outgoing>
            <timerEventDefinition id="event_ad_07">
                <timeDuration id="event_ad_07_timeExpression">38 weeks gestation</timeDuration>
            </timerEventDefinition>
        </intermediateCatchEvent>
        <sequenceFlow sourceRef="id_ad_07" targetRef="id_ad_07_triggerAction" id="sf_ad_07_ad_07_triggerAction"/>
        <userTask name="Contact 7" id="id_ad_07_triggerAction">
            <incoming>sf_ad_07_ad_07_triggerAction</incoming>
            <outgoing>sf_ad_07_triggerAction_ad_08</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_07_triggerAction" targetRef="id_ad_08" id="sf_ad_07_triggerAction_ad_08"/>
        <intermediateCatchEvent name="Contact 8" id="id_ad_08">
            <incoming>sf_ad_07_triggerAction_ad_08</incoming>
            <outgoing>sf_ad_08_ad_08_triggerAction</outgoing>
            <timerEventDefinition id="event_ad_08">
                <timeDuration id="event_ad_08_timeExpression">40 weeks gestation</timeDuration>
            </timerEventDefinition>
        </intermediateCatchEvent>
        <sequenceFlow sourceRef="id_ad_08" targetRef="id_ad_08_triggerAction" id="sf_ad_08_ad_08_triggerAction"/>
        <userTask name="Contact 8" id="id_ad_08_triggerAction">
            <incoming>sf_ad_08_ad_08_triggerAction</incoming>
            <outgoing>sf_ad_08_triggerAction_ad_09</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_08_triggerAction" targetRef="id_ad_09" id="sf_ad_08_triggerAction_ad_09"/>
        <userTask name="Delivery" id="id_ad_09">
            <incoming>sf_ad_08_triggerAction_ad_09</incoming>
            <outgoing>sf_ad_09_end34</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_09" targetRef="id_end34" id="sf_ad_09_end34"/>
        <endEvent name="end" id="id_end34">
            <incoming>sf_ad_09_end34</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="PlanDefinition_ANC_Contact_Schedule">
            <ns4:BPMNShape bpmnElement="id_start35" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_01_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_02" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="290.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_02_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="370.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_03" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="520.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_03_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="600.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_04" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="750.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_04_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="830.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_05" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="980.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_05_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1060.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_06" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1210.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_06_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1290.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_07" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1440.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_07_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1520.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_08" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1670.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_08_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1750.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_09" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1900.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end34" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2050.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_ad_03_triggerAction_ad_04">
                <ns2:waypoint x="700.0" y="100.0"/>
                <ns2:waypoint x="750.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_08_ad_08_triggerAction">
                <ns2:waypoint x="1700.0" y="100.0"/>
                <ns2:waypoint x="1750.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_03_ad_03_triggerAction">
                <ns2:waypoint x="550.0" y="100.0"/>
                <ns2:waypoint x="600.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_06_triggerAction_ad_07">
                <ns2:waypoint x="1390.0" y="100.0"/>
                <ns2:waypoint x="1440.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_06_ad_06_triggerAction">
                <ns2:waypoint x="1240.0" y="100.0"/>
                <ns2:waypoint x="1290.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_02_triggerAction_ad_03">
                <ns2:waypoint x="470.0" y="100.0"/>
                <ns2:waypoint x="520.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_05_ad_05_triggerAction">
                <ns2:waypoint x="1010.0" y="100.0"/>
                <ns2:waypoint x="1060.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_07_triggerAction_ad_08">
                <ns2:waypoint x="1620.0" y="100.0"/>
                <ns2:waypoint x="1670.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_09_end34">
                <ns2:waypoint x="2000.0" y="100.0"/>
                <ns2:waypoint x="2050.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_08_triggerAction_ad_09">
                <ns2:waypoint x="1850.0" y="100.0"/>
                <ns2:waypoint x="1900.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_01_triggerAction_ad_02">
                <ns2:waypoint x="240.0" y="100.0"/>
                <ns2:waypoint x="290.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_02_ad_02_triggerAction">
                <ns2:waypoint x="320.0" y="100.0"/>
                <ns2:waypoint x="370.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_05_triggerAction_ad_06">
                <ns2:waypoint x="1160.0" y="100.0"/>
                <ns2:waypoint x="1210.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_04_triggerAction_ad_05">
                <ns2:waypoint x="930.0" y="100.0"/>
                <ns2:waypoint x="980.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_07_ad_07_triggerAction">
                <ns2:waypoint x="1470.0" y="100.0"/>
                <ns2:waypoint x="1520.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_04_ad_04_triggerAction">
                <ns2:waypoint x="780.0" y="100.0"/>
                <ns2:waypoint x="830.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start35_ad_01_triggerAction">
                <ns2:waypoint x="90.0" y="100.0"/>
                <ns2:waypoint x="140.0" y="100.0"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>

```

<script type="text/javascript" src="../js/highlight.js"></script>