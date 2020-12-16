
# VA CKD Protocol

Transformation of an adapted version of the VA CKD Protocol process as defined [here](http://hl7.org/fhir/uv/cpg/2019Sep/PlanDefinition-va-ckd-protocol.xml.html).

## Discussions

 * Action 3 / 3.1 "Refer to emergency department" are probably not modelled correctly. They should be surrounded by an xor and a condition, rather than defining a action with a subaction which results in a subprocess to perform a check.

## Graph

<script type="text/javascript" src="https://unpkg.com/bpmn-js@7.2.1/dist/bpmn-navigated-viewer.production.min.js"></script>
Usage: Mouse Click + Move: Scroll around. Ctrl + Mouse Wheel: Zoom.

<div id="container" style="width: 100%; height: 500px; border: 1px solid lightgray; overflow:auto;"></div>

<script type="text/javascript" src="../js/fhir2bpmn/vaCkdProtocol.js"></script>

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
    <id value="va-ckd-protocol"/>
    <meta>
        <profile
                value="http://hl7.org/fhir/uv/cpg/StructureDefinition/cpg-protocoldefinition"/>
    </meta>
    <text>
        <status value="generated"/>
        <div xmlns="http://www.w3.org/1999/xhtml"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: va-ckd-protocol</p><p><b>meta</b>: </p><p><b>url</b>: <a href="http://hl7.org/fhir/uv/cpg/PlanDefinition/va-ckd-protocol">http://hl7.org/fhir/uv/cpg/PlanDefinition/va-ckd-protocol</a></p><p><b>identifier</b>: VA_CKD_Protocol (OFFICIAL)</p><p><b>version</b>: 0.1.0</p><p><b>name</b>: VA_CKD_Protocol</p><p><b>title</b>: VA CKD Protocol</p><p><b>type</b>: Clinical Protocol <span style="background: LightGoldenRodYellow">(Details : {http://terminology.hl7.org/CodeSystem/plan-definition-type code 'clinical-protocol' = 'Clinical Protocol', given as 'Clinical Protocol'})</span></p><p><b>status</b>: draft</p><p><b>experimental</b>: true</p><p><b>date</b>: Jul 27, 2019 8:00:00 PM</p><p><b>publisher</b>: HL7 FHIR Clinical Guidelines Example Artifact</p><p><b>description</b>: VA CKD Protocol</p><p><b>useContext</b>: </p><p><b>jurisdiction</b>: World <span style="background: LightGoldenRodYellow">(Details : {http://unstats.un.org/unsd/methods/m49/m49.htm code '001' = 'World', given as 'World'})</span></p><p><b>copyright</b>: Copyright © Example.org 2019.</p><p><b>approvalDate</b>: Jul 28, 2019</p><p><b>lastReviewDate</b>: Jul 28, 2019</p><p><b>effectivePeriod</b>: Jul 28, 2019 12:00:00 AM --&gt; (ongoing)</p><p><b>topic</b>: Treatment <span style="background: LightGoldenRodYellow">(Details : {http://terminology.hl7.org/CodeSystem/definition-topic code 'treatment' = 'Treatment', given as 'Treatment'})</span></p><p><b>author</b>: </p><blockquote><p><b>action</b></p><p><b>id</b>: 001</p><p><b>title</b>: History and Phyiscal</p><p><b>description</b>: Obtain initial clinical information: medical history (including risk factors for kidney disease), physical examination and review existing laboratory results.</p><p><b>code</b>: History and Physical <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'history-and-physical' = 'History and Physical', given as 'History and Physical'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 002</p><p><b>title</b>: At-Risk Population</p><p><b>description</b>: Evaluate at-risk patients for CKD: obtain SCr, eGFR, urinalysis, &amp; spot urine UAER.</p><p><b>code</b>: History and Physical <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'history-and-physical' = 'History and Physical', given as 'History and Physical'})</span></p></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 003</p><p><b>title</b>: Urgent/Emergent Conditions</p><p><b>description</b>: Does patient have an urgent or emergent condition?</p><p><b>code</b>: Triage <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'triage' = 'Triage', given as 'Triage'})</span></p><h3>Actions</h3><table class="grid"><tr><td>-</td></tr><tr><td>*</td></tr></table></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 004</p><p><b>title</b>: Criteria for Confirmed CKD</p><p><b>description</b>: Does patient have evidence of CKD?</p><p><b>code</b>: Determine Diagnosis <span style="background: LightGoldenRodYellow">(Details : {http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process code 'determine-diagnosis' = 'Determine Diagnosis', given as 'Determine Diagnosis'})</span></p><h3>Conditions</h3><table class="grid"><tr><td>-</td><td><b>Kind</b></td><td><b>Expression</b></td></tr><tr><td>*</td><td>applicability</td><td/></tr></table><h3>Actions</h3><table class="grid"><tr><td>-</td></tr><tr><td>*</td></tr><tr><td>*</td></tr></table></blockquote></div>
    </text>
    <url value="http://hl7.org/fhir/uv/cpg/PlanDefinition/va-ckd-protocol"/>
    <identifier>
        <use value="official"/>
        <value value="VA_CKD_Protocol"/>
    </identifier>
    <version value="0.1.0"/>
    <name value="VA_CKD_Protocol"/>
    <title value="VA CKD Protocol"/>
    <type>
        <coding>
            <system value="http://terminology.hl7.org/CodeSystem/plan-definition-type"/>
            <code value="clinical-protocol"/>
            <display value="Clinical Protocol"/>
        </coding>
    </type>
    <status value="draft"/>
    <experimental value="true"/>
    <date value="2019-07-27T20:00:00-04:00"/>
    <publisher value="HL7 FHIR Clinical Guidelines Example Artifact"/>
    <description value="VA CKD Protocol"/>
    <useContext>
        <code>
            <system value="http://terminology.hl7.org/CodeSystem/usage-context-type"/>
            <code value="focus"/>
            <display value="Clinical Focus"/>
        </code>
        <valueCodeableConcept>
            <coding>
                <system value="http://snomed.info/sct"/>
                <code value="709044004"/>
                <display value="Chronic kidney disease (disorder)"/>
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
    <copyright value="Copyright © Example.org 2019."/>
    <approvalDate value="2019-07-28"/>
    <lastReviewDate value="2019-07-28"/>
    <effectivePeriod>
        <start value="2019-07-28T00:00:00-04:00"/>
    </effectivePeriod>
    <topic>
        <coding>
            <system value="http://terminology.hl7.org/CodeSystem/definition-topic"/>
            <code value="treatment"/>
            <display value="Treatment"/>
        </coding>
    </topic>
    <author>
        <name value="Dave Carlson"/>
    </author>
    <action id="id_001">
        <title value="History and Phyiscal"/>
        <description value="Obtain initial clinical information: medical history (including risk factors for kidney disease), physical examination and review existing laboratory results."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="history-and-physical"/>
                <display value="History and Physical"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_002"/>  <!-- added -->
            <relationship value="before-start"/>  <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_002">
        <title value="At-Risk Population"/>
        <description value="Evaluate at-risk patients for CKD: obtain SCr, eGFR, urinalysis, &amp; spot urine UAER."/>
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="history-and-physical"/>
                <display value="History and Physical"/>
            </coding>
        </code>
        <relatedAction> <!-- added -->
            <actionId value="id_003"/>  <!-- added -->
            <relationship value="before-start"/>  <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action id="id_003">
        <title value="Urgent/Emergent Conditions"/>
        <description value="Does patient have an urgent or emergent condition?"/> <!-- Note: This should probably become some condition with surrounding xor_group to make sense-->
        <code>
            <coding>
                <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                <code value="triage"/>
                <display value="Triage"/>
            </coding>
        </code>
        <action id="id_003.1">
            <title value="Refer to emergency department."/>
            <description value="Refer to emergency department to manage or stabilize."/>
            <code>
                <coding>
                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                    <code value="triage"/>
                    <display value="Triage"/>
                </coding>
            </code>
        </action>
        <relatedAction> <!-- added -->
            <actionId value="xor_group"/>  <!-- added -->
            <relationship value="before-start"/>  <!-- added -->
        </relatedAction> <!-- added -->
    </action>
    <action> <!-- added -->
        <id value="xor_group"/> <!-- added -->
        <title value="Xor_group"/> <!-- added -->
        <groupingBehavior value="logical-group"/> <!-- added -->
        <selectionBehavior value="at-most-one"/> <!-- added -->
        <action id="id_004"> <!-- moved to xor -->
            <title value="Criteria for Confirmed CKD"/>
            <description value="Does patient have evidence of CKD?"/>
            <code>
                <coding>
                    <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                    <code value="determine-diagnosis"/>
                    <display value="Determine Diagnosis"/>
                </coding>
            </code>
            <condition>
                <kind value="applicability"/>
                <expression>
                    <language value="text/cql"/>
                    <expression value="Has evidence of CKD?"/>
                </expression>
            </condition>
            <action id="id_004.1">
                <title value="Stages of CKD."/>
                <description value="Complete clinical assessment. Determine stage of CKD."/>
                <code>
                    <coding>
                        <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                        <code value="determine-diagnosis"/>
                        <display value="Determine Diagnosis"/>
                    </coding>
                </code>
                <relatedAction> <!-- added -->
                    <actionId value="id_004.2"/>  <!-- added -->
                    <relationship value="before-start"/>  <!-- added -->
                </relatedAction> <!-- added -->
            </action>
            <action id="id_004.2">
                <title value="Strategies to Slow Progression."/>
                <description value="Establish primary etiology of CKD and treatment plan to slow progression."/>
                <code>
                    <coding>
                        <system value="http://hl7.org/fhir/uv/cpg/CodeSystem/cpg-common-process"/>
                        <code value="guideline-based-care"/>
                        <display value="Guideline-based Care"/>
                    </coding>
                </code>
            </action>
            <relatedAction> <!-- added -->
                <actionId value="xor_group"/>  <!-- added -->
                <relationship value="before-end"/>  <!-- added -->
            </relatedAction> <!-- added -->
        </action>
    </action>
</PlanDefinition>
```

## BPMN

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="VA_CKD_Protocol" id="VA_CKD_Protocol">
        <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start64">
            <outgoing>sf_start64_id_001</outgoing>
        </startEvent>
        <sequenceFlow sourceRef="id_start64" targetRef="id_id_001" id="sf_start64_id_001"/>
        <userTask name="History and Phyiscal" id="id_id_001">
            <incoming>sf_start64_id_001</incoming>
            <outgoing>sf_id_001_id_002</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_001" targetRef="id_id_002" id="sf_id_001_id_002"/>
        <userTask name="At-Risk Population" id="id_id_002">
            <incoming>sf_id_001_id_002</incoming>
            <outgoing>sf_id_002_id_003</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_002" targetRef="id_id_003" id="sf_id_002_id_003"/>
        <subProcess name="Urgent/Emergent Conditions" id="id_id_003">
            <incoming>sf_id_002_id_003</incoming>
            <outgoing>sf_id_003_xor_group</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start66">
                <outgoing>sf_start66_id_003.1</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start66" targetRef="id_id_003.1" id="sf_start66_id_003.1"/>
            <userTask name="Refer to emergency department." id="id_id_003.1">
                <incoming>sf_start66_id_003.1</incoming>
                <outgoing>sf_id_003.1_end67</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_id_003.1" targetRef="id_end67" id="sf_id_003.1_end67"/>
            <endEvent name="end" id="id_end67">
                <incoming>sf_id_003.1_end67</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_id_003" targetRef="id_xor_group" id="sf_id_003_xor_group"/>
        <exclusiveGateway name="Xor_group" id="id_xor_group">
            <incoming>sf_id_003_xor_group</incoming>
            <outgoing>sf_xor_group_id_004</outgoing>
            <outgoing>sf_xor_group_join_of_xor_group</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_xor_group" targetRef="id_id_004" name="Has evidence of CKD?" id="sf_xor_group_id_004">
            <conditionExpression id="sf_xor_group_id_004_condition">Has evidence of CKD?</conditionExpression>
        </sequenceFlow>
        <sequenceFlow sourceRef="id_xor_group" targetRef="id_join_of_xor_group" name="else" id="sf_xor_group_join_of_xor_group">
            <conditionExpression id="sf_xor_group_join_of_xor_group_condition">else</conditionExpression>
        </sequenceFlow>
        <subProcess name="Criteria for Confirmed CKD" id="id_id_004">
            <incoming>sf_xor_group_id_004</incoming>
            <outgoing>sf_id_004_join_of_xor_group</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start68">
                <outgoing>sf_start68_id_004.1</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start68" targetRef="id_id_004.1" id="sf_start68_id_004.1"/>
            <userTask name="Stages of CKD." id="id_id_004.1">
                <incoming>sf_start68_id_004.1</incoming>
                <outgoing>sf_id_004.1_id_004.2</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_id_004.1" targetRef="id_id_004.2" id="sf_id_004.1_id_004.2"/>
            <userTask name="Strategies to Slow Progression." id="id_id_004.2">
                <incoming>sf_id_004.1_id_004.2</incoming>
                <outgoing>sf_id_004.2_end69</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_id_004.2" targetRef="id_end69" id="sf_id_004.2_end69"/>
            <endEvent name="end" id="id_end69">
                <incoming>sf_id_004.2_end69</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_id_004" targetRef="id_join_of_xor_group" id="sf_id_004_join_of_xor_group"/>
        <exclusiveGateway name="join" id="id_join_of_xor_group">
            <incoming>sf_id_004_join_of_xor_group</incoming>
            <incoming>sf_xor_group_join_of_xor_group</incoming>
            <outgoing>sf_join_of_xor_group_end65</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_join_of_xor_group" targetRef="id_end65" id="sf_join_of_xor_group_end65"/>
        <endEvent name="end" id="id_end65">
            <incoming>sf_join_of_xor_group_end65</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="VA_CKD_Protocol">
            <ns4:BPMNShape bpmnElement="id_start66" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="530.0" y="270.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_003.1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="610.0" y="245.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end67" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="760.0" y="270.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_id_003.1_end67">
                <ns2:waypoint x="710.0" y="285.0"/>
                <ns2:waypoint x="760.0" y="285.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start66_id_003.1">
                <ns2:waypoint x="560.0" y="285.0"/>
                <ns2:waypoint x="610.0" y="285.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start68" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1080.0" y="175.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_004.1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1160.0" y="150.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_004.2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1310.0" y="150.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end69" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1460.0" y="175.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_id_004.1_id_004.2">
                <ns2:waypoint x="1260.0" y="190.0"/>
                <ns2:waypoint x="1310.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start68_id_004.1">
                <ns2:waypoint x="1110.0" y="190.0"/>
                <ns2:waypoint x="1160.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_004.2_end69">
                <ns2:waypoint x="1410.0" y="190.0"/>
                <ns2:waypoint x="1460.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start64" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="247.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_001" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="222.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_002" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="290.0" y="225.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_003" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="440.0" y="155.0" width="410.0" height="230.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_xor_group" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="900.0" y="257.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_004" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="990.0" y="60.0" width="560.0" height="230.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_xor_group" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1600.0" y="261.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end65" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1690.0" y="266.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_id_003_xor_group">
                <ns2:waypoint x="850.0" y="270.0"/>
                <ns2:waypoint x="875.0" y="270.0"/>
                <ns2:waypoint x="875.0" y="277.0"/>
                <ns2:waypoint x="900.0" y="277.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_004_join_of_xor_group">
                <ns2:waypoint x="1550.0" y="175.0"/>
                <ns2:waypoint x="1575.0" y="175.0"/>
                <ns2:waypoint x="1575.0" y="281.0"/>
                <ns2:waypoint x="1600.0" y="281.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_002_id_003">
                <ns2:waypoint x="390.0" y="265.0"/>
                <ns2:waypoint x="415.0" y="265.0"/>
                <ns2:waypoint x="415.0" y="270.0"/>
                <ns2:waypoint x="440.0" y="270.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start64_id_001">
                <ns2:waypoint x="90.0" y="262.0"/>
                <ns2:waypoint x="140.0" y="262.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_group_id_004">
                <ns2:waypoint x="940.0" y="277.0"/>
                <ns2:waypoint x="965.0" y="277.0"/>
                <ns2:waypoint x="965.0" y="175.00000000000003"/>
                <ns2:waypoint x="990.0" y="175.00000000000003"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="875.0" y="226.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_001_id_002">
                <ns2:waypoint x="240.0" y="262.0"/>
                <ns2:waypoint x="265.0" y="262.0"/>
                <ns2:waypoint x="265.0" y="265.0"/>
                <ns2:waypoint x="290.0" y="265.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_group_join_of_xor_group">
                <ns2:waypoint x="940.0" y="277.0"/>
                <ns2:waypoint x="1270.0" y="277.0"/>
                <ns2:waypoint x="1270.0" y="281.0"/>
                <ns2:waypoint x="1600.0" y="281.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="1180.0" y="279.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_xor_group_end65">
                <ns2:waypoint x="1640.0" y="281.0"/>
                <ns2:waypoint x="1690.0" y="281.0"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>

```

<script type="text/javascript" src="../js/highlight.js"></script>