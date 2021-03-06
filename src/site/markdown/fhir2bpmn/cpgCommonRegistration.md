
# CPG Common Registration

Transformation of an adapted version of the common registration process as defined [here](http://hl7.org/fhir/uv/cpg/2019Sep/PlanDefinition-cpg-common-registration.xml.html).


## Graph

<script type="text/javascript" src="https://unpkg.com/bpmn-js@7.2.1/dist/bpmn-navigated-viewer.production.min.js"></script>
Usage: Mouse Click + Move: Scroll around. Ctrl + Mouse Wheel: Zoom.

<div id="container" style="width: 100%; height: 500px; border: 1px solid lightgray; overflow:auto;"></div>

<script type="text/javascript" src="../js/fhir2bpmn/cpgCommonRegistration.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.0.0/styles/darcula.min.css"></link>
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.0.0/highlight.min.js"></script>


## HL7 FHIR

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--
  ~ Copyright (c) 2020 the original author or authors.
  ~ DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
  ~
  ~ This Source Code Form is subject to the terms of the Mozilla Public
  ~ License, v. 2.0. If a copy of the MPL was not distributed with this
  ~ file, You can obtain one at https://mozilla.org/MPL/2.0/.
  -->

<PlanDefinition xmlns="http://hl7.org/fhir">
    <id value="cpg-common-registration"/>
    <text>
        <status value="generated"/>
        <div xmlns="http://www.w3.org/1999/xhtml"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: cpg-common-registration</p><p><b>url</b>: <a href="http://hl7.org/fhir/uv/cpg/PlanDefinition/cpg-common-registration">http://hl7.org/fhir/uv/cpg/PlanDefinition/cpg-common-registration</a></p><p><b>version</b>: 0.1.0</p><p><b>name</b>: PlanDefinition_CPG_Common_Registration</p><p><b>title</b>: PlanDefinition - CPG Common Registration</p><p><b>type</b>: Workflow Definition <span style="background: LightGoldenRodYellow">(Details : {http://terminology.hl7.org/CodeSystem/plan-definition-type code 'workflow-definition' = 'Workflow Definition', given as 'Workflow Definition'})</span></p><p><b>status</b>: draft</p><p><b>date</b>: May 15, 2019 12:00:00 AM</p><p><b>jurisdiction</b>: World <span style="background: LightGoldenRodYellow">(Details : {http://unstats.un.org/unsd/methods/m49/m49.htm code '001' = 'World', given as 'World'})</span></p><p><b>copyright</b>: © WHO 2019+.</p><blockquote><p><b>action</b></p><p><b>title</b>: Registration</p><p><b>code</b>: Registration <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'registration' = 'Registration)</span></p><blockquote><p><b>participant</b></p><p><b>type</b>: patient</p><p><b>role</b>: Patient <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code 'C000' = 'Patient)</span></p></blockquote><blockquote><p><b>participant</b></p><p><b>type</b>: practitioner</p><p><b>role</b>: Clerical support worker <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona code 'W000' = 'Clerical support worker', given as 'Clerical support worker'})</span></p></blockquote><h3>Actions</h3><table class="grid"><tr><td>-</td></tr><tr><td>*</td></tr><tr><td>*</td></tr><tr><td>*</td></tr><tr><td>*</td></tr><tr><td>*</td></tr><tr><td>*</td></tr></table></blockquote></div>
    </text>
    <url value="http://hl7.org/fhir/uv/cpg/PlanDefinition/cpg-common-registration"/>
    <version value="0.1.0"/>
    <name value="PlanDefinition_CPG_Common_Registration"/>
    <title value="PlanDefinition - CPG Common Registration"/>
    <type>
        <coding>
            <system value="http://terminology.hl7.org/CodeSystem/plan-definition-type"/>
            <code value="workflow-definition"/>
            <display value="Workflow Definition"/>
        </coding>
    </type>
    <status value="draft"/>
    <date value="2019-05-15T00:00:00-04:00"/>
    <jurisdiction>
        <coding>
            <system value="http://unstats.un.org/unsd/methods/m49/m49.htm"/>
            <code value="001"/>
            <display value="World"/>
        </coding>
    </jurisdiction>
    <copyright value="© WHO 2019+."/>
    <action>
        <id value="ad_1" /> <!-- added -->
        <title value="Registration"/>
        <output> <!-- this is our custom type --> <!-- added -->
            <id value="data_18" /> <!-- added -->
            <name value="Bundle" /> <!-- added -->
            <dataRequirement> <!-- added -->
                <type value="Bundle"/> <!-- added -->
            </dataRequirement> <!-- added -->
            <relatedRequirement> <!-- added -->
                <requirementId value="data_17"/> <!-- added -->
            </relatedRequirement> <!-- added -->
        </output>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="registration"/>
            </coding>
        </code>
        <!-- not supported in this way anymore see: https://jira.hl7.org/browse/FHIR-20825 -->
<!--        <participant>-->
<!--            <type value="patient"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="C000"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
<!--        <participant>-->
<!--            <type value="practitioner"/>-->
<!--            <role>-->
<!--                <coding>-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                    <code value="W000"/>-->
<!--                    <display value="Clerical support worker"/>-->
<!--                </coding>-->
<!--            </role>-->
<!--        </participant>-->
        <action>
            <id value="ad_2" /> <!-- added -->
            <relatedAction> <!-- added -->
                <actionId value="ad_3"/> <!-- added -->
                <relationship value="before-start"/> <!-- added -->
            </relatedAction> <!-- added -->
            <title value="Gather identifying information"/>
            <output> <!-- this is our custom type -->
                <id value="data_1" /> <!-- added -->
                <name value="Questionnaire Response" /> <!-- added -->
                <dataRequirement>
                    <type value="QuestionnaireResponse"/>
                </dataRequirement>
            </output>
<!--            <participant>-->
<!--                <type value="patient"/>-->
<!--                <role>-->
<!--                    <coding>-->
<!--                        <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                        <code value="C000"/>-->
<!--                    </coding>-->
<!--                </role>-->
<!--            </participant>-->
        </action>
        <action>
            <id value="ad_3" /> <!-- added -->
            <relatedAction> <!-- added -->
                <actionId value="ad_4"/> <!-- added -->
                <relationship value="before-start"/> <!-- added -->
            </relatedAction> <!-- added -->
            <title value="Patient match"/>
            <input> <!-- this is our custom type -->
                <id value="data_2" /> <!-- added -->
                <dataRequirement>
                    <type value="QuestionnaireResponse"/>
                    <codeFilter>
                        <path value="questionnaire"/>
                        <code>
                            <code value="http://hl7.org/fhir/uv/cpg/Questionnaire/cpg-common-identity"/>
                        </code>
                    </codeFilter>
                </dataRequirement>
                <relatedRequirement> <!-- added -->
                    <requirementId value="data_1"/> <!-- added -->
                </relatedRequirement> <!-- added -->
            </input>
            <output> <!-- this is our custom type -->
                <id value="data_3" /> <!-- added -->
                <name value="Bundle" /> <!-- added -->
                <dataRequirement>
                    <type value="Bundle"/>
                </dataRequirement>
            </output>
            <!-- not supported in this way anymore see: https://jira.hl7.org/browse/FHIR-20825 -->
<!--            <participant>-->
<!--                <coding> &lt;!&ndash; added &ndash;&gt;-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/> &lt;!&ndash; added &ndash;&gt;-->
<!--                    <code value="device"/> &lt;!&ndash; added &ndash;&gt;-->
<!--                </coding> &lt;!&ndash; added &ndash;&gt;-->
<!--                <extension url="http://hl7.org/fhir/uv/cpg/StructureDefinition/cpg-participantCapabilityStatement">-->
<!--                    <valueCanonical value="http://hl7.org/fhir/uv/cpg/CapabilityStatement/cpg-patient-registry"/>-->
<!--                </extension>-->
<!--                <type value="device"/>-->
<!--            </participant>-->
        </action>
        <action>
            <id value="ad_4" /> <!-- added -->
            <relatedAction> <!-- added -->
                <actionId value="xor_group"/> <!-- added -->
                <relationship value="before-start"/> <!-- added -->
            </relatedAction> <!-- added -->
            <title value="Resolve patient match results"/>
            <input> <!-- this is our custom type -->
                <id value="data_4" /> <!-- added -->
                <dataRequirement>
                    <type value="Bundle"/>
                </dataRequirement>
                <relatedRequirement> <!-- added -->
                    <requirementId value="data_3"/> <!-- added -->
                </relatedRequirement> <!-- added -->
            </input>
            <output> <!-- this is our custom type -->
                <id value="data_5" /> <!-- added -->
                <name value="Patient" /> <!-- added -->
                <dataRequirement>
                    <type value="Patient"/>
                </dataRequirement>
            </output>
            <!-- not supported in this way anymore see: https://jira.hl7.org/browse/FHIR-20825 -->
<!--            <participant>-->
<!--                <type value="practitioner"/>-->
<!--                <role>-->
<!--                    <coding>-->
<!--                        <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/>-->
<!--                        <code value="W000"/>-->
<!--                        <display value="Clerical support worker"/>-->
<!--                    </coding>-->
<!--                </role>-->
<!--            </participant>-->
        </action>
        <action> <!-- added -->
            <id value="xor_group"/> <!-- added -->
            <title value="xor_group"/> <!-- added -->
            <groupingBehavior value="logical-group"/> <!-- added -->
            <selectionBehavior value="exactly-one"/> <!-- added -->
            <relatedAction> <!-- added -->
                <actionId value="ad_11"/> <!-- added -->
                <relationship value="before-start"/> <!-- added -->
            </relatedAction> <!-- added -->
            <action>
                <id value="ad_5" /> <!-- added -->
                <relatedAction> <!-- added -->
                    <actionId value="xor_group"/> <!-- added -->
                    <relationship value="before-end"/> <!-- added -->
                </relatedAction> <!-- added -->
                <title value="New patient"/>
                <condition>
                    <kind value="applicability"/>
                    <expression>
                        <language value="text/cql"/>
                        <expression value="not exists %input i where i is Patient"/>
                    </expression>
                </condition>
                <input> <!-- this is our custom type -->
                    <id value="data_6" /> <!-- added -->
                    <dataRequirement>
                        <type value="Patient"/>
                    </dataRequirement>
                    <relatedRequirement> <!-- added -->
                        <requirementId value="data_5"/> <!-- added -->
                    </relatedRequirement> <!-- added -->
                </input>
                <input> <!-- this is our custom type --> <!-- added -->
                    <id value="data_21" /> <!-- added -->
                    <relatedRequirement> <!-- added -->
                        <requirementId value="data_1"/> <!-- added -->
                    </relatedRequirement> <!-- added -->
                </input>
                <output> <!-- this is our custom type --> <!-- added -->
                    <id value="data_19" /> <!-- added -->
                    <name value="Patient" /> <!-- added -->
                    <dataRequirement> <!-- added -->
                        <type value="Patient"/> <!-- added -->
                    </dataRequirement> <!-- added -->
                    <relatedRequirement> <!-- added -->
                        <requirementId value="data_10"/> <!-- added -->
                    </relatedRequirement> <!-- added -->
                </output>
                <action>
                    <id value="ad_6" /> <!-- added -->
                    <relatedAction> <!-- added -->
                        <actionId value="ad_7"/> <!-- added -->
                        <relationship value="before-start"/> <!-- added -->
                    </relatedAction> <!-- added -->
                    <title value="Gather patient information"/>
                    <input> <!-- this is our custom type -->
                        <id value="data_7" /> <!-- added -->
                        <name value="Questionnaire Response"/> <!-- added -->
                        <dataRequirement>
                            <type value="QuestionnaireResponse"/>
                            <codeFilter>
                                <path value="questionnaire"/>
                                <code>
                                    <code value="http://hl7.org/fhir/uv/cpg/Questionnaire/cpg-common-identity"/>
                                </code>
                            </codeFilter>
                        </dataRequirement>
                    </input>
                    <output> <!-- this is our custom type -->
                        <id value="data_8" /> <!-- added -->
                        <name value="Questionnaire Response filled" /> <!-- added -->
                        <dataRequirement>
                            <type value="QuestionnaireResponse"/>
                        </dataRequirement>
                    </output>
                </action>
                <action>
                    <id value="ad_7" /> <!-- added -->
                    <title value="Record patient data"/>
                    <input> <!-- this is our custom type -->
                        <id value="data_9" /> <!-- added -->
                        <dataRequirement>
                            <type value="QuestionnaireResponse"/>
                            <codeFilter>
                                <path value="questionnaire"/>
                                <code>
                                    <code value="http://hl7.org/fhir/uv/cpg/Questionnaire/cpg-common-patient-profile"/>
                                </code>
                            </codeFilter>
                        </dataRequirement>
                        <relatedRequirement> <!-- added -->
                            <requirementId value="data_8"/> <!-- added -->
                        </relatedRequirement> <!-- added -->
                    </input>
                    <output> <!-- this is our custom type -->
                        <id value="data_10" /> <!-- added -->
                        <name value="Patient" /> <!-- added -->
                        <dataRequirement>
                            <type value="Patient"/>
                        </dataRequirement>
                    </output>
                    <!-- not supported in this way anymore see: https://jira.hl7.org/browse/FHIR-20825 -->
<!--                    <participant>-->
<!--                        <coding> &lt;!&ndash; added &ndash;&gt;-->
<!--                            <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/> &lt;!&ndash; added &ndash;&gt;-->
<!--                            <code value="device"/> &lt;!&ndash; added &ndash;&gt;-->
<!--                        </coding> &lt;!&ndash; added &ndash;&gt;-->
<!--                        <extension url="http://hl7.org/fhir/uv/cpg/StructureDefinition/cpg-participantCapabilityStatement">-->
<!--                            <valueCanonical value="http://hl7.org/fhir/uv/cpg/CapabilityStatement/cpg-patient-registry"/>-->
<!--                        </extension>-->
<!--                        <type value="device"/>-->
<!--                    </participant>-->
                </action>
            </action>
            <action>
                <id value="ad_8" /> <!-- added -->
                <relatedAction> <!-- added -->
                    <actionId value="xor_group"/> <!-- added -->
                    <relationship value="before-end"/> <!-- added -->
                </relatedAction> <!-- added -->
                <title value="Existing patient"/>
                <condition>
                    <kind value="applicability"/>
                    <expression>
                        <language value="text/cql"/>
                        <expression value="exists %input i where i is Patient"/>
                    </expression>
                </condition>
                <input> <!-- this is our custom type -->
                    <id value="data_11" /> <!-- added -->
                    <dataRequirement>
                        <type value="Patient"/>
                    </dataRequirement>
                    <relatedRequirement> <!-- added -->
                        <requirementId value="data_5"/> <!-- added -->
                    </relatedRequirement> <!-- added -->
                </input>
                <input> <!-- this is our custom type --> <!-- added -->
                    <id value="data_22" /> <!-- added -->
                    <relatedRequirement> <!-- added -->
                        <requirementId value="data_1"/> <!-- added -->
                    </relatedRequirement> <!-- added -->
                </input>
                <output> <!-- this is our custom type --> <!-- added -->
                    <id value="data_20" /> <!-- added -->
                    <name value="Patient" /> <!-- added -->
                    <dataRequirement> <!-- added -->
                        <type value="Patient"/> <!-- added -->
                    </dataRequirement> <!-- added -->
                    <relatedRequirement> <!-- added -->
                        <requirementId value="data_15"/> <!-- added -->
                    </relatedRequirement> <!-- added -->
                </output>
                <action>
                    <id value="ad_9" /> <!-- added -->
                    <relatedAction> <!-- added -->
                        <actionId value="ad_10"/> <!-- added -->
                        <relationship value="before-start"/> <!-- added -->
                    </relatedAction> <!-- added -->
                    <title value="Ensure patient information is up to date"/>
                    <input> <!-- this is our custom type -->
                        <id value="data_12" /> <!-- added -->
                        <name value="Questionnaire Response"/> <!-- added -->
                        <dataRequirement>
                            <type value="QuestionnaireResponse"/>
                            <codeFilter>
                                <path value="questionnaire"/>
                                <code>
                                    <code value="http://hl7.org/fhir/uv/cpg/Questionnaire/cpg-common-identity"/>
                                </code>
                            </codeFilter>
                        </dataRequirement>
                    </input>
                    <output> <!-- this is our custom type -->
                        <id value="data_13" /> <!-- added -->
                        <name value="Questionnaire Response filled" /> <!-- added -->
                        <dataRequirement>
                            <type value="QuestionnaireResponse"/>
                        </dataRequirement>
                    </output>
                </action>
                <action>
                    <id value="ad_10" /> <!-- added -->
                    <title value="Record patient data"/>
                    <input> <!-- this is our custom type -->
                        <id value="data_14" /> <!-- added -->
                        <dataRequirement>
                            <type value="QuestionnaireResponse"/>
                            <codeFilter>
                                <path value="questionnaire"/>
                                <code>
                                    <code value="http://hl7.org/fhir/uv/cpg/Questionnaire/cpg-common-patient-profile"/>
                                </code>
                            </codeFilter>
                        </dataRequirement>
                        <relatedRequirement> <!-- added -->
                            <requirementId value="data_13"/> <!-- added -->
                        </relatedRequirement> <!-- added -->
                    </input>
                    <output> <!-- this is our custom type -->
                        <id value="data_15" /> <!-- added -->
                        <name value="Patient" /> <!-- added -->
                        <dataRequirement>
                            <type value="Patient"/>
                        </dataRequirement>
                    </output>
                    <!-- not supported in this way anymore see: https://jira.hl7.org/browse/FHIR-20825 -->
<!--                    <participant>-->
<!--                        <coding> &lt;!&ndash; added &ndash;&gt;-->
<!--                            <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/> &lt;!&ndash; added &ndash;&gt;-->
<!--                            <code value="device"/> &lt;!&ndash; added &ndash;&gt;-->
<!--                        </coding> &lt;!&ndash; added &ndash;&gt;-->
<!--                        <extension url="http://hl7.org/fhir/uv/cpg/StructureDefinition/cpg-participantCapabilityStatement">-->
<!--                            <valueCanonical value="http://hl7.org/fhir/uv/cpg/CapabilityStatement/cpg-patient-registry"/>-->
<!--                        </extension>-->
<!--                        <type value="device"/>-->
<!--                    </participant>-->
                </action>
            </action>
        </action> <!-- added -->
        <action>
            <id value="ad_11" /> <!-- added -->
            <title value="Patient summary lookup"/>
            <input> <!-- this is our custom type -->
                <id value="data_16" /> <!-- added -->
                <dataRequirement>
                    <type value="Patient"/>
                </dataRequirement>
                <relatedRequirement>  <!-- added -->
                    <requirementId value="data_19"/> <!-- added -->
                </relatedRequirement> <!-- added -->
                <relatedRequirement> <!-- added -->
                    <requirementId value="data_20"/> <!-- added -->
                </relatedRequirement> <!-- added -->
            </input>
            <output> <!-- this is our custom type -->
                <id value="data_17" /> <!-- added -->
                <name value="Bundle" /> <!-- added -->
                <dataRequirement>
                    <type value="Bundle"/>
                </dataRequirement>
            </output>
            <!-- not supported in this way anymore see: https://jira.hl7.org/browse/FHIR-20825 -->
<!--            <participant>-->
<!--                <coding> &lt;!&ndash; added &ndash;&gt;-->
<!--                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-persona"/> &lt;!&ndash; added &ndash;&gt;-->
<!--                    <code value="device"/> &lt;!&ndash; added &ndash;&gt;-->
<!--                </coding> &lt;!&ndash; added &ndash;&gt;-->
<!--                <extension url="http://hl7.org/fhir/uv/cpg/StructureDefinition/cpg-participantCapabilityStatement">-->
<!--                    <valueCanonical value="http://hl7.org/fhir/uv/cpg/CapabilityStatement/cpg-patient-registry"/>-->
<!--                </extension>-->
<!--                <type value="device"/>-->
<!--            </participant>-->
        </action>
    </action>
</PlanDefinition>
```

## BPMN

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="PlanDefinition_CPG_Common_Registration" id="PlanDefinition_CPG_Common_Registration">
        <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start20">
            <outgoing>sf_start20_ad_1</outgoing>
        </startEvent>
        <sequenceFlow sourceRef="id_start20" targetRef="id_ad_1" id="sf_start20_ad_1"/>
        <subProcess name="Registration" id="id_ad_1">
            <incoming>sf_start20_ad_1</incoming>
            <outgoing>sf_ad_1_end21</outgoing>
            <dataOutputAssociation id="df_id_ad_1_data_18">
                <targetRef>id_data_18</targetRef>
            </dataOutputAssociation>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start22">
                <outgoing>sf_start22_ad_2</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start22" targetRef="id_ad_2" id="sf_start22_ad_2"/>
            <userTask name="Gather identifying information" id="id_ad_2">
                <incoming>sf_start22_ad_2</incoming>
                <outgoing>sf_ad_2_ad_3</outgoing>
                <dataOutputAssociation id="df_id_ad_2_data_1">
                    <targetRef>id_data_1</targetRef>
                </dataOutputAssociation>
            </userTask>
            <sequenceFlow sourceRef="id_ad_2" targetRef="id_ad_3" id="sf_ad_2_ad_3"/>
            <dataObjectReference name="Questionnaire Response" id="id_data_1">
                <extensionElements>
                    <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">QuestionnaireResponse</ns5:type>
                </extensionElements>
            </dataObjectReference>
            <userTask name="Patient match" id="id_ad_3">
                <incoming>sf_ad_2_ad_3</incoming>
                <outgoing>sf_ad_3_ad_4</outgoing>
                <property name="prop_Questionnaire Response" id="prop_ad_3_data_1"/>
                <dataInputAssociation id="df_id_ad_3_data_1">
                    <sourceRef>id_data_1</sourceRef>
                    <targetRef>prop_ad_3_data_1</targetRef>
                </dataInputAssociation>
                <dataOutputAssociation id="df_id_ad_3_data_3">
                    <targetRef>id_data_3</targetRef>
                </dataOutputAssociation>
            </userTask>
            <sequenceFlow sourceRef="id_ad_3" targetRef="id_ad_4" id="sf_ad_3_ad_4"/>
            <dataObjectReference name="Bundle" id="id_data_3">
                <extensionElements>
                    <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">Bundle</ns5:type>
                </extensionElements>
            </dataObjectReference>
            <userTask name="Resolve patient match results" id="id_ad_4">
                <incoming>sf_ad_3_ad_4</incoming>
                <outgoing>sf_ad_4_xor_group</outgoing>
                <property name="prop_Bundle" id="prop_ad_4_data_3"/>
                <dataInputAssociation id="df_id_ad_4_data_3">
                    <sourceRef>id_data_3</sourceRef>
                    <targetRef>prop_ad_4_data_3</targetRef>
                </dataInputAssociation>
                <dataOutputAssociation id="df_id_ad_4_data_5">
                    <targetRef>id_data_5</targetRef>
                </dataOutputAssociation>
            </userTask>
            <sequenceFlow sourceRef="id_ad_4" targetRef="id_xor_group" id="sf_ad_4_xor_group"/>
            <dataObjectReference name="Patient" id="id_data_5">
                <extensionElements>
                    <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">Patient</ns5:type>
                </extensionElements>
            </dataObjectReference>
            <exclusiveGateway name="xor_group" id="id_xor_group">
                <incoming>sf_ad_4_xor_group</incoming>
                <outgoing>sf_xor_group_ad_8</outgoing>
                <outgoing>sf_xor_group_ad_5</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_xor_group" targetRef="id_ad_8" name="exists %input i where i is Patient" id="sf_xor_group_ad_8">
                <conditionExpression id="sf_xor_group_ad_8_condition">exists %input i where i is Patient</conditionExpression>
            </sequenceFlow>
            <sequenceFlow sourceRef="id_xor_group" targetRef="id_ad_5" name="not exists %input i where i is Patient" id="sf_xor_group_ad_5">
                <conditionExpression id="sf_xor_group_ad_5_condition">not exists %input i where i is Patient</conditionExpression>
            </sequenceFlow>
            <subProcess name="Existing patient" id="id_ad_8">
                <incoming>sf_xor_group_ad_8</incoming>
                <outgoing>sf_ad_8_join_of_xor_group</outgoing>
                <property name="prop_Patient" id="prop_ad_8_data_5"/>
                <property name="prop_Questionnaire Response" id="prop_ad_8_data_1"/>
                <dataInputAssociation id="df_id_ad_8_data_5">
                    <sourceRef>id_data_5</sourceRef>
                    <targetRef>prop_ad_8_data_5</targetRef>
                </dataInputAssociation>
                <dataInputAssociation id="df_id_ad_8_data_1">
                    <sourceRef>id_data_1</sourceRef>
                    <targetRef>prop_ad_8_data_1</targetRef>
                </dataInputAssociation>
                <dataOutputAssociation id="df_id_ad_8_data_20">
                    <targetRef>id_data_20</targetRef>
                </dataOutputAssociation>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start26">
                    <outgoing>sf_start26_ad_9</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start26" targetRef="id_ad_9" id="sf_start26_ad_9"/>
                <userTask name="Ensure patient information is up to date" id="id_ad_9">
                    <incoming>sf_start26_ad_9</incoming>
                    <outgoing>sf_ad_9_ad_10</outgoing>
                    <property name="prop_Questionnaire Response" id="prop_ad_9_data_12"/>
                    <dataInputAssociation id="df_id_ad_9_data_12">
                        <sourceRef>id_data_12</sourceRef>
                        <targetRef>prop_ad_9_data_12</targetRef>
                    </dataInputAssociation>
                    <dataOutputAssociation id="df_id_ad_9_data_13">
                        <targetRef>id_data_13</targetRef>
                    </dataOutputAssociation>
                </userTask>
                <sequenceFlow sourceRef="id_ad_9" targetRef="id_ad_10" id="sf_ad_9_ad_10"/>
                <dataObjectReference name="Questionnaire Response filled" id="id_data_13">
                    <extensionElements>
                        <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">QuestionnaireResponse</ns5:type>
                    </extensionElements>
                </dataObjectReference>
                <dataObjectReference name="Questionnaire Response" id="id_data_12">
                    <extensionElements>
                        <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">QuestionnaireResponse</ns5:type>
                    </extensionElements>
                </dataObjectReference>
                <userTask name="Record patient data" id="id_ad_10">
                    <incoming>sf_ad_9_ad_10</incoming>
                    <outgoing>sf_ad_10_end27</outgoing>
                    <property name="prop_Questionnaire Response filled" id="prop_ad_10_data_13"/>
                    <dataInputAssociation id="df_id_ad_10_data_13">
                        <sourceRef>id_data_13</sourceRef>
                        <targetRef>prop_ad_10_data_13</targetRef>
                    </dataInputAssociation>
                    <dataOutputAssociation id="df_id_ad_10_data_15">
                        <targetRef>id_data_15</targetRef>
                    </dataOutputAssociation>
                </userTask>
                <sequenceFlow sourceRef="id_ad_10" targetRef="id_end27" id="sf_ad_10_end27"/>
                <dataObjectReference name="Patient" id="id_data_15">
                    <extensionElements>
                        <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">Patient</ns5:type>
                    </extensionElements>
                </dataObjectReference>
                <endEvent name="end" id="id_end27">
                    <incoming>sf_ad_10_end27</incoming>
                </endEvent>
            </subProcess>
            <sequenceFlow sourceRef="id_ad_8" targetRef="id_join_of_xor_group" id="sf_ad_8_join_of_xor_group"/>
            <dataObjectReference name="Patient" id="id_data_20">
                <extensionElements>
                    <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">Patient</ns5:type>
                </extensionElements>
            </dataObjectReference>
            <subProcess name="New patient" id="id_ad_5">
                <incoming>sf_xor_group_ad_5</incoming>
                <outgoing>sf_ad_5_join_of_xor_group</outgoing>
                <property name="prop_Questionnaire Response" id="prop_ad_5_data_1"/>
                <property name="prop_Patient" id="prop_ad_5_data_5"/>
                <dataInputAssociation id="df_id_ad_5_data_1">
                    <sourceRef>id_data_1</sourceRef>
                    <targetRef>prop_ad_5_data_1</targetRef>
                </dataInputAssociation>
                <dataInputAssociation id="df_id_ad_5_data_5">
                    <sourceRef>id_data_5</sourceRef>
                    <targetRef>prop_ad_5_data_5</targetRef>
                </dataInputAssociation>
                <dataOutputAssociation id="df_id_ad_5_data_19">
                    <targetRef>id_data_19</targetRef>
                </dataOutputAssociation>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start24">
                    <outgoing>sf_start24_ad_6</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start24" targetRef="id_ad_6" id="sf_start24_ad_6"/>
                <userTask name="Gather patient information" id="id_ad_6">
                    <incoming>sf_start24_ad_6</incoming>
                    <outgoing>sf_ad_6_ad_7</outgoing>
                    <property name="prop_Questionnaire Response" id="prop_ad_6_data_7"/>
                    <dataInputAssociation id="df_id_ad_6_data_7">
                        <sourceRef>id_data_7</sourceRef>
                        <targetRef>prop_ad_6_data_7</targetRef>
                    </dataInputAssociation>
                    <dataOutputAssociation id="df_id_ad_6_data_8">
                        <targetRef>id_data_8</targetRef>
                    </dataOutputAssociation>
                </userTask>
                <sequenceFlow sourceRef="id_ad_6" targetRef="id_ad_7" id="sf_ad_6_ad_7"/>
                <dataObjectReference name="Questionnaire Response filled" id="id_data_8">
                    <extensionElements>
                        <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">QuestionnaireResponse</ns5:type>
                    </extensionElements>
                </dataObjectReference>
                <dataObjectReference name="Questionnaire Response" id="id_data_7">
                    <extensionElements>
                        <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">QuestionnaireResponse</ns5:type>
                    </extensionElements>
                </dataObjectReference>
                <userTask name="Record patient data" id="id_ad_7">
                    <incoming>sf_ad_6_ad_7</incoming>
                    <outgoing>sf_ad_7_end25</outgoing>
                    <property name="prop_Questionnaire Response filled" id="prop_ad_7_data_8"/>
                    <dataInputAssociation id="df_id_ad_7_data_8">
                        <sourceRef>id_data_8</sourceRef>
                        <targetRef>prop_ad_7_data_8</targetRef>
                    </dataInputAssociation>
                    <dataOutputAssociation id="df_id_ad_7_data_10">
                        <targetRef>id_data_10</targetRef>
                    </dataOutputAssociation>
                </userTask>
                <sequenceFlow sourceRef="id_ad_7" targetRef="id_end25" id="sf_ad_7_end25"/>
                <dataObjectReference name="Patient" id="id_data_10">
                    <extensionElements>
                        <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">Patient</ns5:type>
                    </extensionElements>
                </dataObjectReference>
                <endEvent name="end" id="id_end25">
                    <incoming>sf_ad_7_end25</incoming>
                </endEvent>
            </subProcess>
            <sequenceFlow sourceRef="id_ad_5" targetRef="id_join_of_xor_group" id="sf_ad_5_join_of_xor_group"/>
            <dataObjectReference name="Patient" id="id_data_19">
                <extensionElements>
                    <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">Patient</ns5:type>
                </extensionElements>
            </dataObjectReference>
            <exclusiveGateway name="join" id="id_join_of_xor_group">
                <incoming>sf_ad_5_join_of_xor_group</incoming>
                <incoming>sf_ad_8_join_of_xor_group</incoming>
                <outgoing>sf_join_of_xor_group_ad_11</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_join_of_xor_group" targetRef="id_ad_11" id="sf_join_of_xor_group_ad_11"/>
            <userTask name="Patient summary lookup" id="id_ad_11">
                <incoming>sf_join_of_xor_group_ad_11</incoming>
                <outgoing>sf_ad_11_end23</outgoing>
                <property name="prop_Patient" id="prop_ad_11_data_19"/>
                <property name="prop_Patient" id="prop_ad_11_data_20"/>
                <dataInputAssociation id="df_id_ad_11_data_19">
                    <sourceRef>id_data_19</sourceRef>
                    <targetRef>prop_ad_11_data_19</targetRef>
                </dataInputAssociation>
                <dataInputAssociation id="df_id_ad_11_data_20">
                    <sourceRef>id_data_20</sourceRef>
                    <targetRef>prop_ad_11_data_20</targetRef>
                </dataInputAssociation>
                <dataOutputAssociation id="df_id_ad_11_data_17">
                    <targetRef>id_data_17</targetRef>
                </dataOutputAssociation>
            </userTask>
            <sequenceFlow sourceRef="id_ad_11" targetRef="id_end23" id="sf_ad_11_end23"/>
            <dataObjectReference name="Bundle" id="id_data_17">
                <extensionElements>
                    <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">Bundle</ns5:type>
                </extensionElements>
            </dataObjectReference>
            <endEvent name="end" id="id_end23">
                <incoming>sf_ad_11_end23</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_ad_1" targetRef="id_end21" id="sf_ad_1_end21"/>
        <dataObjectReference name="Bundle" id="id_data_18">
            <extensionElements>
                <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">Bundle</ns5:type>
            </extensionElements>
        </dataObjectReference>
        <endEvent name="end" id="id_end21">
            <incoming>sf_ad_1_end21</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="PlanDefinition_CPG_Common_Registration">
            <ns4:BPMNShape bpmnElement="id_data_7" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="900.0" y="200.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_start24" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="940.0" y="325.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_6" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1020.0" y="300.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_7" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1170.0" y="300.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end25" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1320.0" y="325.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_ad_7_end25">
                <ns2:waypoint x="1270.0" y="340.0"/>
                <ns2:waypoint x="1320.0" y="340.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_6_ad_7">
                <ns2:waypoint x="1120.0" y="340.0"/>
                <ns2:waypoint x="1170.0" y="340.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start24_ad_6">
                <ns2:waypoint x="970.0" y="340.0"/>
                <ns2:waypoint x="1020.0" y="340.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_8" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1052.0" y="430.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_data_10" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1202.0" y="510.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="df_id_ad_7_data_10">
                <ns2:waypoint x="1220.0" y="380.0"/>
                <ns2:waypoint x="1220.0" y="510.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_6_data_8">
                <ns2:waypoint x="1070.0" y="380.0"/>
                <ns2:waypoint x="1070.0" y="430.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_6_data_7">
                <ns2:waypoint x="936.0" y="238.6184210526316"/>
                <ns2:waypoint x="1020.0" y="302.17105263157896"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_7_data_8">
                <ns2:waypoint x="1088.0" y="441.2"/>
                <ns2:waypoint x="1170.0" y="378.33333333333337"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_12" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="900.0" y="730.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_start26" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="940.0" y="855.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_9" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1020.0" y="830.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_10" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1170.0" y="830.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end27" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1320.0" y="855.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_ad_9_ad_10">
                <ns2:waypoint x="1120.0" y="870.0"/>
                <ns2:waypoint x="1170.0" y="870.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_10_end27">
                <ns2:waypoint x="1270.0" y="870.0"/>
                <ns2:waypoint x="1320.0" y="870.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start26_ad_9">
                <ns2:waypoint x="970.0" y="870.0"/>
                <ns2:waypoint x="1020.0" y="870.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_13" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1052.0" y="960.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_data_15" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1202.0" y="1040.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="df_id_ad_9_data_13">
                <ns2:waypoint x="1070.0" y="910.0"/>
                <ns2:waypoint x="1070.0" y="960.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_10_data_15">
                <ns2:waypoint x="1220.0" y="910.0"/>
                <ns2:waypoint x="1220.0" y="1040.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_9_data_12">
                <ns2:waypoint x="936.0" y="768.6184210526316"/>
                <ns2:waypoint x="1020.0" y="832.171052631579"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_10_data_13">
                <ns2:waypoint x="1088.0" y="971.2"/>
                <ns2:waypoint x="1170.0" y="908.3333333333334"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start22" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="230.0" y="615.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="310.0" y="590.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_3" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="460.0" y="590.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_4" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="610.0" y="590.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_xor_group" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="760.0" y="610.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_8" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="850.0" y="680.0" width="560.0" height="430.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_5" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="850.0" y="150.0" width="560.0" height="430.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_xor_group" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1460.0" y="610.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_11" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1550.0" y="590.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end23" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1700.0" y="615.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_ad_5_join_of_xor_group">
                <ns2:waypoint x="1410.0" y="365.0"/>
                <ns2:waypoint x="1435.0" y="365.0"/>
                <ns2:waypoint x="1435.0" y="630.0"/>
                <ns2:waypoint x="1460.0" y="630.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_3_ad_4">
                <ns2:waypoint x="560.0" y="630.0"/>
                <ns2:waypoint x="610.0" y="630.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_xor_group_ad_11">
                <ns2:waypoint x="1500.0" y="630.0"/>
                <ns2:waypoint x="1550.0" y="630.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_11_end23">
                <ns2:waypoint x="1650.0" y="630.0"/>
                <ns2:waypoint x="1700.0" y="630.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start22_ad_2">
                <ns2:waypoint x="260.0" y="630.0"/>
                <ns2:waypoint x="310.0" y="630.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_group_ad_5">
                <ns2:waypoint x="800.0" y="630.0"/>
                <ns2:waypoint x="825.0" y="630.0"/>
                <ns2:waypoint x="825.0" y="365.00000000000006"/>
                <ns2:waypoint x="850.0" y="365.00000000000006"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="735.0" y="497.5" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_4_xor_group">
                <ns2:waypoint x="710.0" y="630.0"/>
                <ns2:waypoint x="760.0" y="630.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_8_join_of_xor_group">
                <ns2:waypoint x="1410.0" y="895.0"/>
                <ns2:waypoint x="1435.0" y="895.0"/>
                <ns2:waypoint x="1435.0" y="630.0"/>
                <ns2:waypoint x="1460.0" y="630.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_group_ad_8">
                <ns2:waypoint x="800.0" y="630.0"/>
                <ns2:waypoint x="825.0" y="630.0"/>
                <ns2:waypoint x="825.0" y="895.0"/>
                <ns2:waypoint x="850.0" y="895.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="735.0" y="762.5" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_2_ad_3">
                <ns2:waypoint x="410.0" y="630.0"/>
                <ns2:waypoint x="460.0" y="630.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="342.0" y="1140.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_data_3" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="492.0" y="1220.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_data_5" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="642.0" y="1300.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_data_19" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1112.0" y="1380.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_data_20" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1163.0" y="1380.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_data_17" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1582.0" y="1460.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="df_id_ad_3_data_3">
                <ns2:waypoint x="510.0" y="670.0"/>
                <ns2:waypoint x="510.0" y="1220.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_2_data_1">
                <ns2:waypoint x="360.0" y="670.0"/>
                <ns2:waypoint x="360.0" y="1140.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_11_data_17">
                <ns2:waypoint x="1600.0" y="670.0"/>
                <ns2:waypoint x="1600.0" y="1460.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_5_data_19">
                <ns2:waypoint x="1130.0" y="580.0"/>
                <ns2:waypoint x="1130.0" y="1380.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_4_data_5">
                <ns2:waypoint x="660.0" y="670.0"/>
                <ns2:waypoint x="660.0" y="1300.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_8_data_20">
                <ns2:waypoint x="1151.5" y="1110.0"/>
                <ns2:waypoint x="1178.5" y="1380.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_3_data_1">
                <ns2:waypoint x="367.0093457943925" y="1140.0"/>
                <ns2:waypoint x="498.78504672897196" y="670.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_4_data_3">
                <ns2:waypoint x="516.0975609756098" y="1220.0"/>
                <ns2:waypoint x="650.2439024390244" y="670.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_8_data_5">
                <ns2:waypoint x="678.0" y="1308.531914893617"/>
                <ns2:waypoint x="895.0" y="1110.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_8_data_1">
                <ns2:waypoint x="378.0" y="1158.6883116883116"/>
                <ns2:waypoint x="850.0" y="993.1818181818181"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_5_data_1">
                <ns2:waypoint x="378.0" y="1146.2987012987012"/>
                <ns2:waypoint x="923.0625" y="580.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_5_data_5">
                <ns2:waypoint x="672.2395833333334" y="1300.0"/>
                <ns2:waypoint x="1024.7395833333333" y="580.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_11_data_19">
                <ns2:waypoint x="1145.1612903225807" y="1380.0"/>
                <ns2:waypoint x="1575.741935483871" y="670.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_ad_11_data_20">
                <ns2:waypoint x="1194.516129032258" y="1380.0"/>
                <ns2:waypoint x="1578.374193548387" y="670.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start20" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="780.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="60.0" width="1650.0" height="1470.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end21" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1840.0" y="780.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_start20_ad_1">
                <ns2:waypoint x="90.0" y="795.0"/>
                <ns2:waypoint x="115.0" y="795.0"/>
                <ns2:waypoint x="115.0" y="795.0000000000001"/>
                <ns2:waypoint x="140.0" y="795.0000000000001"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_1_end21">
                <ns2:waypoint x="1790.0" y="795.0"/>
                <ns2:waypoint x="1840.0" y="795.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_18" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="947.0" y="1560.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="df_id_ad_1_data_18">
                <ns2:waypoint x="965.0" y="1530.0"/>
                <ns2:waypoint x="965.0" y="1560.0"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>

```

<script type="text/javascript" src="../js/highlight.js"></script>