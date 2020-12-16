
# CPG Common Pathway

Transformation of an adapted version of the common pathway process as defined [here](http://hl7.org/fhir/uv/cpg/2019Sep/PlanDefinition-cpg-common-pathway.xml.html).

## Discussions

 * Is the idea of this plan definition to just have all actions as a sequence, where one is execute after the other?

## Graph

<script type="text/javascript" src="https://unpkg.com/bpmn-js@7.2.1/dist/bpmn-navigated-viewer.production.min.js"></script>
Usage: Mouse Click + Move: Scroll around. Ctrl + Mouse Wheel: Zoom.

<div id="container" style="width: 100%; height: 500px; border: 1px solid lightgray; overflow:auto;"></div>

<script type="text/javascript" src="../js/fhir2bpmn/cpgCommonPathway.js"></script>

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
    <id value="cpg-common-pathway"/>
    <text>
        <status value="generated"/>
        <div xmlns="http://www.w3.org/1999/xhtml"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: cpg-common-pathway</p><p><b>url</b>: <a href="http://hl7.org/fhir/uv/cpg/PlanDefinition/cpg-common-pathway">http://hl7.org/fhir/uv/cpg/PlanDefinition/cpg-common-pathway</a></p><p><b>identifier</b>: CPG_Common_Pathway (OFFICIAL)</p><p><b>version</b>: 0.1.0</p><p><b>name</b>: CPG_Common_Pathway</p><p><b>title</b>: CPG Common Pathway</p><p><b>type</b>: Clinical Protocol <span style="background: LightGoldenRodYellow">(Details : {http://terminology.hl7.org/CodeSystem/plan-definition-type code 'clinical-protocol' = 'Clinical Protocol', given as 'Clinical Protocol'})</span></p><p><b>status</b>: draft</p><p><b>date</b>: Jun 23, 2019 12:00:00 AM</p><p><b>jurisdiction</b>: World <span style="background: LightGoldenRodYellow">(Details : {http://unstats.un.org/unsd/methods/m49/m49.htm code '001' = 'World', given as 'World'})</span></p><blockquote><p><b>action</b></p><p><b>id</b>: 001</p><p><b>title</b>: Registration</p><p><b>description</b>: Identifying and recording the subject of care.</p><p><b>code</b>: Registration <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'registration' = 'Registration', given as 'Registration'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 002</p><p><b>title</b>: Triage</p><p><b>description</b>: Performing basic triage to identify any signs that emergency care is required.</p><p><b>code</b>: Triage <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'triage' = 'Triage', given as 'Triage'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 003</p><p><b>title</b>: Local Urgent Care</p><p><b>description</b>: Providing local urgent care based on the outcome of a triage process.</p><p><b>code</b>: Local Urgent Care <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'local-urgent-care' = 'Local Urgent Care', given as 'Local Urgent Care'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 004</p><p><b>title</b>: History and Phyiscal</p><p><b>description</b>: Gathering clinical history and performing and recording observations regarding the patient's health (e.g. blood pressure, temperature, etc.)</p><p><b>code</b>: History and Physical <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'history-and-physical' = 'History and Physical', given as 'History and Physical'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 005</p><p><b>title</b>: Provide Counseling</p><p><b>description</b>: Informing the subject of care about their treatment options and about how their ongoing care should be managed between visits. This is also where treatment constents are obtained and where health education is provided.</p><p><b>code</b>: Provide Counseling <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'provide-counseling' = 'Provide Counseling', given as 'Provide Counseling'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 006</p><p><b>title</b>: Diagnostic Testing</p><p><b>description</b>: Conducting diagnostic tests, including lab tests, collection of samples, and other diagnostic tests. Lab testing may be done locally (e.g. HIV quick test) or the samples may require lab order fulfillment workflow.</p><p><b>code</b>: Conduct Diagnostic Tests <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'diagnostic-testing' = 'Conduct Diagnostic Tests', given as 'Conduct Diagnostic Tests'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 007</p><p><b>title</b>: Determine Diagnosis</p><p><b>description</b>: Using available information from the patient's history, current examinations, tests, and assessments to assist in developing a diagnosis.</p><p><b>code</b>: Determine Diagnosis <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'determine-diagnosis' = 'Determine Diagnosis', given as 'Determine Diagnosis'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 008</p><p><b>title</b>: Guideline-based Care</p><p><b>description</b>: Performing and recording observations, interventions, and treatment plans recommended by specific guidelines.</p><p><b>code</b>: Guideline-based Care <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'guideline-based-care' = 'Guideline-based Care', given as 'Guideline-based Care'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 009</p><p><b>title</b>: Dispense Medications</p><p><b>description</b>: Administering medications, ordered by clinicians and dispensed by a pharmacy. Pharmacies may be local to the care facility or community-based, and involves supply chain transactions to support medication management.</p><p><b>code</b>: Dispense Medications <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'dispense-medications' = 'Dispense Medications', given as 'Dispense Medications'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 010</p><p><b>title</b>: Emergency Care</p><p><b>description</b>: Providing emergency care in trauma cases or as part of guildeline-based care escalation.</p><p><b>code</b>: Emergency Care <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'emergency-care' = 'Emergency Care', given as 'Emergency Care'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 011</p><p><b>title</b>: Acute/Tertiary Care</p><p><b>description</b>: Providing acute or tertiary care, either as an escalation from emergency care, or from primary care due to guideline-based referral patterns.</p><p><b>code</b>: Acute/Tertiary Care <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'actue-tertiary-care' = 'Acute/Tertiary Care', given as 'Acute/Tertiary Care'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 012</p><p><b>title</b>: Charge for Service</p><p><b>description</b>: Charging for services rendered, regardless of the mechanism of coverage.</p><p><b>code</b>: Charge for Service <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'charge-for-service' = 'Charge for Service', given as 'Charge for Service'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 013</p><p><b>title</b>: Discharge/Referral of Patient</p><p><b>description</b>: Discharging or referring a patient, including the movement of patients through levels of care delivery (e.g. acute to primary, primary to community, etc.) or the enrollment of patients in guideline-based care programs (e.g. HIV, maternal, diabetes, injury rehabilitiation, etc.).</p><p><b>code</b>: Discharge/Referral of Patient <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'discharge-referral-of-patient' = 'Discharge/Referral of Patient', given as 'Discharge/Referral of Patient'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 014</p><p><b>title</b>: Record and Report</p><p><b>description</b>: Recording and reporting patient-specific care management information which may be aggregated to develop reportable system management indicators at the priovider, facility, district, national, and international levels.</p><p><b>code</b>: Record and Report <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'record-and-report' = 'Record and Report', given as 'Record and Report'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 015</p><p><b>title</b>: Monitor and Follow-up of Patient</p><p><b>description</b>: Monitoring and tracking progress for each patient based on guideline recommendations.</p><p><b>code</b>: Monitor and Follow-up of Patient <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'monitor-and-follow-up-of-patient' = 'Monitor and Follow-up of Patient', given as 'Monitor and Follow-up of Patient'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 016</p><p><b>title</b>: Alerts Reminders Education</p><p><b>description</b>: Providing alerts, reminders, and education to patients, providers, and health system managers.</p><p><b>code</b>: Alerts Reminders Education <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'alerts-reminders-education' = 'Alerts Reminders Education', given as 'Alerts Reminders Education'})</span></p></blockquote></div>
    </text>
    <url value="http://hl7.org/fhir/uv/cpg/PlanDefinition/cpg-common-pathway"/>
    <identifier>
        <use value="official"/>
        <value value="CPG_Common_Pathway"/>
    </identifier>
    <version value="0.1.0"/>
    <name value="CPG_Common_Pathway"/>
    <title value="CPG Common Pathway"/>
    <type>
        <coding>
            <system value="http://terminology.hl7.org/CodeSystem/plan-definition-type"/>
            <code value="clinical-protocol"/>
            <display value="Clinical Protocol"/>
        </coding>
    </type>
    <status value="draft"/>
    <date value="2019-06-23T00:00:00-04:00"/>
    <jurisdiction>
        <coding>
            <system value="http://unstats.un.org/unsd/methods/m49/m49.htm"/>
            <code value="001"/>
            <display value="World"/>
        </coding>
    </jurisdiction>
    <action id="id_001">
        <title value="Registration"/>
        <description value="Identifying and recording the subject of care."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="registration"/>
                <display value="Registration"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_002"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_002">
        <title value="Triage"/>
        <description
                value="Performing basic triage to identify any signs that emergency care is required."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="triage"/>
                <display value="Triage"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_003"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_003">
        <title value="Local Urgent Care"/>
        <description
                value="Providing local urgent care based on the outcome of a triage process."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="local-urgent-care"/>
                <display value="Local Urgent Care"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_004"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_004">
        <title value="History and Phyiscal"/>
        <description
                value="Gathering clinical history and performing and recording observations regarding the patient&#39;s health (e.g. blood pressure, temperature, etc.)"/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="history-and-physical"/>
                <display value="History and Physical"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_005"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_005">
        <title value="Provide Counseling"/>
        <description
                value="Informing the subject of care about their treatment options and about how their ongoing care should be managed between visits. This is also where treatment constents are obtained and where health education is provided."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="provide-counseling"/>
                <display value="Provide Counseling"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_006"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_006">
        <title value="Diagnostic Testing"/>
        <description
                value="Conducting diagnostic tests, including lab tests, collection of samples, and other diagnostic tests. Lab testing may be done locally (e.g. HIV quick test) or the samples may require lab order fulfillment workflow."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="diagnostic-testing"/>
                <display value="Conduct Diagnostic Tests"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_007"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_007">
        <title value="Determine Diagnosis"/>
        <description
                value="Using available information from the patient&#39;s history, current examinations, tests, and assessments to assist in developing a diagnosis."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="determine-diagnosis"/>
                <display value="Determine Diagnosis"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_008"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_008">
        <title value="Guideline-based Care"/>
        <description
                value="Performing and recording observations, interventions, and treatment plans recommended by specific guidelines."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="guideline-based-care"/>
                <display value="Guideline-based Care"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_009"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_009">
        <title value="Dispense Medications"/>
        <description
                value="Administering medications, ordered by clinicians and dispensed by a pharmacy. Pharmacies may be local to the care facility or community-based, and involves supply chain transactions to support medication management."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="dispense-medications"/>
                <display value="Dispense Medications"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_010"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_010">
        <title value="Emergency Care"/>
        <description
                value="Providing emergency care in trauma cases or as part of guildeline-based care escalation."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="emergency-care"/>
                <display value="Emergency Care"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_011"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_011">
        <title value="Acute/Tertiary Care"/>
        <description
                value="Providing acute or tertiary care, either as an escalation from emergency care, or from primary care due to guideline-based referral patterns."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="actue-tertiary-care"/>
                <display value="Acute/Tertiary Care"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_012"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_012">
        <title value="Charge for Service"/>
        <description
                value="Charging for services rendered, regardless of the mechanism of coverage."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="charge-for-service"/>
                <display value="Charge for Service"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_013"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_013">
        <title value="Discharge/Referral of Patient"/>
        <description
                value="Discharging or referring a patient, including the movement of patients through levels of care delivery (e.g. acute to primary, primary to community, etc.) or the enrollment of patients in guideline-based care programs (e.g. HIV, maternal, diabetes, injury rehabilitiation, etc.)."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="discharge-referral-of-patient"/>
                <display value="Discharge/Referral of Patient"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_014"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_014">
        <title value="Record and Report"/>
        <description
                value="Recording and reporting patient-specific care management information which may be aggregated to develop reportable system management indicators at the priovider, facility, district, national, and international levels."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="record-and-report"/>
                <display value="Record and Report"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_015"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_015">
        <title value="Monitor and Follow-up of Patient"/>
        <description
                value="Monitoring and tracking progress for each patient based on guideline recommendations."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="monitor-and-follow-up-of-patient"/>
                <display value="Monitor and Follow-up of Patient"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_016"/> <!-- added -->
            <relationship value="before-start"/> <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_016">
        <title value="Alerts Reminders Education"/>
        <description
                value="Providing alerts, reminders, and education to patients, providers, and health system managers."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="alerts-reminders-education"/>
                <display value="Alerts Reminders Education"/>
            </coding>
        </code>
    </action>
</PlanDefinition>
```

## BPMN

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="CPG_Common_Pathway" id="CPG_Common_Pathway">
        <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start28">
            <outgoing>sf_start28_id_001</outgoing>
        </startEvent>
        <sequenceFlow sourceRef="id_start28" targetRef="id_id_001" id="sf_start28_id_001"/>
        <userTask name="Registration" id="id_id_001">
            <incoming>sf_start28_id_001</incoming>
            <outgoing>sf_id_001_id_002</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_001" targetRef="id_id_002" id="sf_id_001_id_002"/>
        <userTask name="Triage" id="id_id_002">
            <incoming>sf_id_001_id_002</incoming>
            <outgoing>sf_id_002_id_003</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_002" targetRef="id_id_003" id="sf_id_002_id_003"/>
        <userTask name="Local Urgent Care" id="id_id_003">
            <incoming>sf_id_002_id_003</incoming>
            <outgoing>sf_id_003_id_004</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_003" targetRef="id_id_004" id="sf_id_003_id_004"/>
        <userTask name="History and Phyiscal" id="id_id_004">
            <incoming>sf_id_003_id_004</incoming>
            <outgoing>sf_id_004_id_005</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_004" targetRef="id_id_005" id="sf_id_004_id_005"/>
        <userTask name="Provide Counseling" id="id_id_005">
            <incoming>sf_id_004_id_005</incoming>
            <outgoing>sf_id_005_id_006</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_005" targetRef="id_id_006" id="sf_id_005_id_006"/>
        <userTask name="Diagnostic Testing" id="id_id_006">
            <incoming>sf_id_005_id_006</incoming>
            <outgoing>sf_id_006_id_007</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_006" targetRef="id_id_007" id="sf_id_006_id_007"/>
        <userTask name="Determine Diagnosis" id="id_id_007">
            <incoming>sf_id_006_id_007</incoming>
            <outgoing>sf_id_007_id_008</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_007" targetRef="id_id_008" id="sf_id_007_id_008"/>
        <userTask name="Guideline-based Care" id="id_id_008">
            <incoming>sf_id_007_id_008</incoming>
            <outgoing>sf_id_008_id_009</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_008" targetRef="id_id_009" id="sf_id_008_id_009"/>
        <userTask name="Dispense Medications" id="id_id_009">
            <incoming>sf_id_008_id_009</incoming>
            <outgoing>sf_id_009_id_010</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_009" targetRef="id_id_010" id="sf_id_009_id_010"/>
        <userTask name="Emergency Care" id="id_id_010">
            <incoming>sf_id_009_id_010</incoming>
            <outgoing>sf_id_010_id_011</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_010" targetRef="id_id_011" id="sf_id_010_id_011"/>
        <userTask name="Acute/Tertiary Care" id="id_id_011">
            <incoming>sf_id_010_id_011</incoming>
            <outgoing>sf_id_011_id_012</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_011" targetRef="id_id_012" id="sf_id_011_id_012"/>
        <userTask name="Charge for Service" id="id_id_012">
            <incoming>sf_id_011_id_012</incoming>
            <outgoing>sf_id_012_id_013</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_012" targetRef="id_id_013" id="sf_id_012_id_013"/>
        <userTask name="Discharge/Referral of Patient" id="id_id_013">
            <incoming>sf_id_012_id_013</incoming>
            <outgoing>sf_id_013_id_014</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_013" targetRef="id_id_014" id="sf_id_013_id_014"/>
        <userTask name="Record and Report" id="id_id_014">
            <incoming>sf_id_013_id_014</incoming>
            <outgoing>sf_id_014_id_015</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_014" targetRef="id_id_015" id="sf_id_014_id_015"/>
        <userTask name="Monitor and Follow-up of Patient" id="id_id_015">
            <incoming>sf_id_014_id_015</incoming>
            <outgoing>sf_id_015_id_016</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_015" targetRef="id_id_016" id="sf_id_015_id_016"/>
        <userTask name="Alerts Reminders Education" id="id_id_016">
            <incoming>sf_id_015_id_016</incoming>
            <outgoing>sf_id_016_end29</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_016" targetRef="id_end29" id="sf_id_016_end29"/>
        <endEvent name="end" id="id_end29">
            <incoming>sf_id_016_end29</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="CPG_Common_Pathway">
            <ns4:BPMNShape bpmnElement="id_start28" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_001" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_002" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="290.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_003" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="440.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_004" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="590.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_005" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="740.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_006" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="890.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_007" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1040.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_008" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1190.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_009" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1340.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_010" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1490.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_011" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1640.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_012" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1790.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_013" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1940.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_014" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2090.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_015" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2240.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_016" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2390.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end29" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2540.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_id_016_end29">
                <ns2:waypoint x="2490.0" y="100.0"/>
                <ns2:waypoint x="2540.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_009_id_010">
                <ns2:waypoint x="1440.0" y="100.0"/>
                <ns2:waypoint x="1490.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start28_id_001">
                <ns2:waypoint x="90.0" y="100.0"/>
                <ns2:waypoint x="140.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_001_id_002">
                <ns2:waypoint x="240.0" y="100.0"/>
                <ns2:waypoint x="290.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_006_id_007">
                <ns2:waypoint x="990.0" y="100.0"/>
                <ns2:waypoint x="1040.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_012_id_013">
                <ns2:waypoint x="1890.0" y="100.0"/>
                <ns2:waypoint x="1940.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_013_id_014">
                <ns2:waypoint x="2040.0" y="100.0"/>
                <ns2:waypoint x="2090.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_007_id_008">
                <ns2:waypoint x="1140.0" y="100.0"/>
                <ns2:waypoint x="1190.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_014_id_015">
                <ns2:waypoint x="2190.0" y="100.0"/>
                <ns2:waypoint x="2240.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_008_id_009">
                <ns2:waypoint x="1290.0" y="100.0"/>
                <ns2:waypoint x="1340.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_002_id_003">
                <ns2:waypoint x="390.0" y="100.0"/>
                <ns2:waypoint x="440.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_003_id_004">
                <ns2:waypoint x="540.0" y="100.0"/>
                <ns2:waypoint x="590.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_004_id_005">
                <ns2:waypoint x="690.0" y="100.0"/>
                <ns2:waypoint x="740.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_010_id_011">
                <ns2:waypoint x="1590.0" y="100.0"/>
                <ns2:waypoint x="1640.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_005_id_006">
                <ns2:waypoint x="840.0" y="100.0"/>
                <ns2:waypoint x="890.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_011_id_012">
                <ns2:waypoint x="1740.0" y="100.0"/>
                <ns2:waypoint x="1790.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_015_id_016">
                <ns2:waypoint x="2340.0" y="100.0"/>
                <ns2:waypoint x="2390.0" y="100.0"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>

```

<script type="text/javascript" src="../js/highlight.js"></script>