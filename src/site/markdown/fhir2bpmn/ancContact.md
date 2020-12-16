
# ANC Contact

Transformation of an adapted version of the ANC Contact process as defined [here](http://hl7.org/fhir/uv/cpg/2019Sep/PlanDefinition-anc-contact.xml.html).

## Discussions

 * An adaption was made to the "Recrod first contact information" action to be surrounded by an xor? Is this how it was supposed to be?

## Graph

<script type="text/javascript" src="https://unpkg.com/bpmn-js@7.2.1/dist/bpmn-navigated-viewer.production.min.js"></script>
Usage: Mouse Click + Move: Scroll around. Ctrl + Mouse Wheel: Zoom.

<div id="container" style="width: 100%; height: 500px; border: 1px solid lightgray; overflow:auto;"></div>

<script type="text/javascript" src="../js/fhir2bpmn/ancContact.js"></script>

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
    <id value="anc-contact"/>
    <text>
        <status value="generated"/>
        <div xmlns="http://www.w3.org/1999/xhtml"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: anc-contact</p><p><b>url</b>: <a href="http://fhir.org/guides/who/anc-cds/PlanDefinition/anc-contact">http://fhir.org/guides/who/anc-cds/PlanDefinition/anc-contact</a></p><p><b>identifier</b>: PlanDefinition_ANC_Contact (OFFICIAL)</p><p><b>version</b>: 0.1.0</p><p><b>name</b>: PlanDefinition_ANC_Contact</p><p><b>title</b>: PlanDefinition - WHO ANC Guideline Contact</p><p><b>type</b>: Workflow Definition <span style="background: LightGoldenRodYellow">(Details : {http://terminology.hl7.org/CodeSystem/plan-definition-type code 'workflow-definition' = 'Workflow Definition', given as 'Workflow Definition'})</span></p><p><b>status</b>: draft</p><p><b>experimental</b>: true</p><p><b>date</b>: May 15, 2019 12:00:00 AM</p><p><b>useContext</b>: </p><p><b>jurisdiction</b>: World <span style="background: LightGoldenRodYellow">(Details : {http://unstats.un.org/unsd/methods/m49/m49.htm code '001' = 'World', given as 'World'})</span></p><p><b>copyright</b>: © WHO 2019+.</p><blockquote><p><b>action</b></p><p><b>title</b>: Registration</p><p><b>code</b>: Registration <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'registration' = 'Registration)</span></p></blockquote><blockquote><p><b>action</b></p><p><b>title</b>: Record health history</p><p><b>code</b>: History and Physical <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'history-and-physical' = 'History and Physical)</span></p><h3>Actions</h3><table class="grid"><tr><td>-</td></tr><tr><td>*</td></tr><tr><td>*</td></tr></table></blockquote><blockquote><p><b>action</b></p><p><b>title</b>: Assess danger signs</p><p><b>code</b>: Triage <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'triage' = 'Triage)</span></p></blockquote><blockquote><p><b>action</b></p><p><b>title</b>: Assess current pregnancy conditions, including symptoms and lab tests</p><p><b>code</b>: Conduct Diagnostic Tests <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'diagnostic-testing' = 'Conduct Diagnostic Tests)</span></p></blockquote><blockquote><p><b>action</b></p><p><b>title</b>: Case management or referral</p><p><b>code</b>: Discharge/Referral of Patient <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'discharge-referral-of-patient' = 'Discharge/Referral of Patient)</span></p></blockquote><blockquote><p><b>action</b></p><p><b>title</b>: Schedule follow-up visit</p><p><b>code</b>: Monitor and Follow-up of Patient <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'monitor-and-follow-up-of-patient' = 'Monitor and Follow-up of Patient)</span></p></blockquote></div>
    </text>
    <url value="http://fhir.org/guides/who/anc-cds/PlanDefinition/anc-contact"/>
    <identifier>
        <use value="official"/>
        <value value="PlanDefinition_ANC_Contact"/>
    </identifier>
    <version value="0.1.0"/>
    <name value="PlanDefinition_ANC_Contact"/>
    <title value="PlanDefinition - WHO ANC Guideline Contact"/>
    <type>
        <coding>
            <system value="http://terminology.hl7.org/CodeSystem/plan-definition-type"/>
            <code value="workflow-definition"/>
            <display value="Workflow Definition"/>
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
    <action>
        <id value="ad_01"/> <!-- added -->
        <title value="Registration"/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="registration"/>
            </coding>
        </code>
        <relatedAction>
            <actionId value="ad_02"/>
            <relationship value="before-start"/>
        </relatedAction>
    </action>
    <action>
        <id value="ad_02"/> <!-- added -->
        <title value="Record health history"/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="history-and-physical"/>
            </coding>
        </code>
        <action> <!-- added -->
            <id value="xor_group"/> <!-- added -->
            <title value="Xor_group"/> <!-- added -->
            <groupingBehavior value="logical-group"/> <!-- added -->
            <selectionBehavior value="at-most-one"/> <!-- added -->
            <action>  <!-- moved into xor_group -->
                <id value="ad_03"/> <!-- added -->
                <title value="Record first contact information"/>
                <condition>
                    <kind value="applicability"/>
                    <expression>
                        <language value="text/cql"/>
                        <expression value="Is First Contact"/>
                    </expression>
                </condition>
                <relatedAction>
                    <actionId value="xor_group"/>
                    <relationship value="before-end"/>
                </relatedAction>
            </action>
            <relatedAction>
                <actionId value="ad_04"/>
                <relationship value="before-start"/>
            </relatedAction>
        </action> <!-- added -->
        <action>
            <id value="ad_04"/> <!-- added -->
            <title value="Record every contact information"/>
        </action>
        <relatedAction>
            <actionId value="ad_05"/>
            <relationship value="before-start"/>
        </relatedAction>
    </action>
    <action>
        <id value="ad_05"/> <!-- added -->
        <title value="Assess danger signs"/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="triage"/>
            </coding>
        </code>
        <relatedAction>
            <actionId value="ad_06"/>
            <relationship value="before-start"/>
        </relatedAction>
    </action>
    <action>
        <id value="ad_06"/> <!-- added -->
        <title value="Assess current pregnancy conditions, including symptoms and lab tests"/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="diagnostic-testing"/>
            </coding>
        </code>
        <relatedAction>
            <actionId value="ad_07"/>
            <relationship value="before-start"/>
        </relatedAction>
    </action>
    <action>
        <id value="ad_07"/> <!-- added -->
        <title value="Case management or referral"/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="discharge-referral-of-patient"/>
            </coding>
        </code>
        <relatedAction>
            <actionId value="ad_08"/>
            <relationship value="before-start"/>
        </relatedAction>
    </action>
    <action>
        <id value="ad_08"/> <!-- added -->
        <title value="Schedule follow-up visit"/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="monitor-and-follow-up-of-patient"/>
            </coding>
        </code>
    </action>
</PlanDefinition>
```

## BPMN

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="PlanDefinition_ANC_Contact" id="PlanDefinition_ANC_Contact">
        <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start30">
            <outgoing>sf_start30_ad_01</outgoing>
        </startEvent>
        <sequenceFlow sourceRef="id_start30" targetRef="id_ad_01" id="sf_start30_ad_01"/>
        <userTask name="Registration" id="id_ad_01">
            <incoming>sf_start30_ad_01</incoming>
            <outgoing>sf_ad_01_ad_02</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_01" targetRef="id_ad_02" id="sf_ad_01_ad_02"/>
        <subProcess name="Record health history" id="id_ad_02">
            <incoming>sf_ad_01_ad_02</incoming>
            <outgoing>sf_ad_02_ad_05</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start32">
                <outgoing>sf_start32_xor_group</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start32" targetRef="id_xor_group" id="sf_start32_xor_group"/>
            <exclusiveGateway name="Xor_group" id="id_xor_group">
                <incoming>sf_start32_xor_group</incoming>
                <outgoing>sf_xor_group_ad_03</outgoing>
                <outgoing>sf_xor_group_join_of_xor_group</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_xor_group" targetRef="id_ad_03" name="Is First Contact" id="sf_xor_group_ad_03">
                <conditionExpression id="sf_xor_group_ad_03_condition">Is First Contact</conditionExpression>
            </sequenceFlow>
            <sequenceFlow sourceRef="id_xor_group" targetRef="id_join_of_xor_group" name="else" id="sf_xor_group_join_of_xor_group">
                <conditionExpression id="sf_xor_group_join_of_xor_group_condition">else</conditionExpression>
            </sequenceFlow>
            <userTask name="Record first contact information" id="id_ad_03">
                <incoming>sf_xor_group_ad_03</incoming>
                <outgoing>sf_ad_03_join_of_xor_group</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_ad_03" targetRef="id_join_of_xor_group" id="sf_ad_03_join_of_xor_group"/>
            <exclusiveGateway name="join" id="id_join_of_xor_group">
                <incoming>sf_ad_03_join_of_xor_group</incoming>
                <incoming>sf_xor_group_join_of_xor_group</incoming>
                <outgoing>sf_join_of_xor_group_ad_04</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_join_of_xor_group" targetRef="id_ad_04" id="sf_join_of_xor_group_ad_04"/>
            <userTask name="Record every contact information" id="id_ad_04">
                <incoming>sf_join_of_xor_group_ad_04</incoming>
                <outgoing>sf_ad_04_end33</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_ad_04" targetRef="id_end33" id="sf_ad_04_end33"/>
            <endEvent name="end" id="id_end33">
                <incoming>sf_ad_04_end33</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_ad_02" targetRef="id_ad_05" id="sf_ad_02_ad_05"/>
        <userTask name="Assess danger signs" id="id_ad_05">
            <incoming>sf_ad_02_ad_05</incoming>
            <outgoing>sf_ad_05_ad_06</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_05" targetRef="id_ad_06" id="sf_ad_05_ad_06"/>
        <userTask name="Assess current pregnancy conditions, including symptoms and lab tests" id="id_ad_06">
            <incoming>sf_ad_05_ad_06</incoming>
            <outgoing>sf_ad_06_ad_07</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_06" targetRef="id_ad_07" id="sf_ad_06_ad_07"/>
        <userTask name="Case management or referral" id="id_ad_07">
            <incoming>sf_ad_06_ad_07</incoming>
            <outgoing>sf_ad_07_ad_08</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_07" targetRef="id_ad_08" id="sf_ad_07_ad_08"/>
        <userTask name="Schedule follow-up visit" id="id_ad_08">
            <incoming>sf_ad_07_ad_08</incoming>
            <outgoing>sf_ad_08_end31</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_08" targetRef="id_end31" id="sf_ad_08_end31"/>
        <endEvent name="end" id="id_end31">
            <incoming>sf_ad_08_end31</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="PlanDefinition_ANC_Contact">
            <ns4:BPMNShape bpmnElement="id_start32" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="380.0" y="244.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_xor_group" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="460.0" y="239.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_03" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="550.0" y="150.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_xor_group" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="700.0" y="237.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_04" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="790.0" y="214.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end33" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="940.0" y="239.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_join_of_xor_group_ad_04">
                <ns2:waypoint x="740.0" y="257.0"/>
                <ns2:waypoint x="765.0" y="257.0"/>
                <ns2:waypoint x="765.0" y="254.0"/>
                <ns2:waypoint x="790.0" y="254.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start32_xor_group">
                <ns2:waypoint x="410.0" y="259.0"/>
                <ns2:waypoint x="460.0" y="259.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_group_ad_03">
                <ns2:waypoint x="500.0" y="259.0"/>
                <ns2:waypoint x="525.0" y="259.0"/>
                <ns2:waypoint x="525.0" y="190.0"/>
                <ns2:waypoint x="550.0" y="190.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="435.0" y="224.5" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_03_join_of_xor_group">
                <ns2:waypoint x="650.0" y="190.0"/>
                <ns2:waypoint x="675.0" y="190.0"/>
                <ns2:waypoint x="675.0" y="257.0"/>
                <ns2:waypoint x="700.0" y="257.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_group_join_of_xor_group">
                <ns2:waypoint x="500.0" y="259.0"/>
                <ns2:waypoint x="600.0" y="259.0"/>
                <ns2:waypoint x="600.0" y="257.0"/>
                <ns2:waypoint x="700.0" y="257.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="510.0" y="258.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_04_end33">
                <ns2:waypoint x="890.0" y="254.0"/>
                <ns2:waypoint x="940.0" y="254.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start30" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="192.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_01" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="167.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_02" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="290.0" y="60.0" width="740.0" height="294.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_05" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1080.0" y="167.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_06" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1230.0" y="167.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_07" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1380.0" y="167.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_08" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1530.0" y="167.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end31" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1680.0" y="192.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_ad_06_ad_07">
                <ns2:waypoint x="1330.0" y="207.0"/>
                <ns2:waypoint x="1380.0" y="207.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start30_ad_01">
                <ns2:waypoint x="90.0" y="207.0"/>
                <ns2:waypoint x="140.0" y="207.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_02_ad_05">
                <ns2:waypoint x="1030.0" y="207.0"/>
                <ns2:waypoint x="1080.0" y="207.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_08_end31">
                <ns2:waypoint x="1630.0" y="207.0"/>
                <ns2:waypoint x="1680.0" y="207.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_01_ad_02">
                <ns2:waypoint x="240.0" y="207.0"/>
                <ns2:waypoint x="265.0" y="207.0"/>
                <ns2:waypoint x="265.0" y="207.00000000000006"/>
                <ns2:waypoint x="290.0" y="207.00000000000006"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_05_ad_06">
                <ns2:waypoint x="1180.0" y="207.0"/>
                <ns2:waypoint x="1230.0" y="207.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_07_ad_08">
                <ns2:waypoint x="1480.0" y="207.0"/>
                <ns2:waypoint x="1530.0" y="207.0"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>

```

<script type="text/javascript" src="../js/highlight.js"></script>