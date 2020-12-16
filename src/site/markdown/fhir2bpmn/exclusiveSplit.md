
# Exclusive Split

In this section the transformation from Fhir->BPMN for an exclusive split is shown.


## Graph

<script type="text/javascript" src="https://unpkg.com/bpmn-js@7.2.1/dist/bpmn-navigated-viewer.production.min.js"></script>
Usage: Mouse Click + Move: Scroll around. Ctrl + Mouse Wheel: Zoom.

<div id="container" style="width: 100%; height: 500px; border: 1px solid lightgray; overflow:auto;"></div>

<script type="text/javascript" src="../js/fhir2bpmn/exclusiveSplit.js"></script>

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
    <id value="protocol-example"/>

    <text>
        <status value="generated"/>
    </text>

    <!--  The status of the protocol.draft | active | retired  -->
    <status value="draft"/>
    <action>
        <id value="ad_1"/>
        <title value="Task 1"/>
        <!-- related action definition -->
        <relatedAction>
            <actionId value="xor_group"/>
            <relationship value="before-start"/>
        </relatedAction>
    </action>

    <action>
        <id value="xor_group"/>
        <title value="Xor_group"/>
        <groupingBehavior value="logical-group"/>
        <selectionBehavior value="exactly-one"/>
        <action>
            <id value="ad_2"/>
            <title value="Task 2"/>
            <condition>
                <kind value="applicability"/>
                <expression>
                    <description value="Some short description of the expression"/>
                    <language value="EL"/>
                    <expression value="$name==2" />
                </expression>
            </condition>
            <relatedAction>
                <actionId value="xor_group"/>
                <relationship value="before-end"/>
            </relatedAction>
        </action>
        <action>
            <id value="ad_3"/>
            <title value="Task 3"/>
            <condition>
                <kind value="applicability"/>
                <expression>
                    <description value="Some short description of the expression"/>
                    <language value="EL"/>
                    <expression value="$name==1" />
                </expression>
            </condition>
            <relatedAction>
                <actionId value="xor_group"/>
                <relationship value="before-end"/>
            </relatedAction>
        </action>
        <relatedAction>
            <actionId value="ad_4"/>
            <relationship value="before-start"/>
        </relatedAction>
    </action>

    <action>
        <id value="ad_4"/>
        <title value="Task 4"/>
    </action>

</PlanDefinition>
```

## BPMN

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="Process_BOmvKZCgYt" id="Process_BOmvKZCgYt">
        <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start5">
            <outgoing>sf_start5_ad_1</outgoing>
        </startEvent>
        <sequenceFlow sourceRef="id_start5" targetRef="id_ad_1" id="sf_start5_ad_1"/>
        <userTask name="Task 1" id="id_ad_1">
            <incoming>sf_start5_ad_1</incoming>
            <outgoing>sf_ad_1_xor_group</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_1" targetRef="id_xor_group" id="sf_ad_1_xor_group"/>
        <exclusiveGateway name="Xor_group" id="id_xor_group">
            <incoming>sf_ad_1_xor_group</incoming>
            <outgoing>sf_xor_group_ad_2</outgoing>
            <outgoing>sf_xor_group_ad_3</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_xor_group" targetRef="id_ad_2" name="$name==2" id="sf_xor_group_ad_2">
            <conditionExpression id="sf_xor_group_ad_2_condition">$name==2</conditionExpression>
        </sequenceFlow>
        <sequenceFlow sourceRef="id_xor_group" targetRef="id_ad_3" name="$name==1" id="sf_xor_group_ad_3">
            <conditionExpression id="sf_xor_group_ad_3_condition">$name==1</conditionExpression>
        </sequenceFlow>
        <userTask name="Task 2" id="id_ad_2">
            <incoming>sf_xor_group_ad_2</incoming>
            <outgoing>sf_ad_2_join_of_xor_group</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_2" targetRef="id_join_of_xor_group" id="sf_ad_2_join_of_xor_group"/>
        <userTask name="Task 3" id="id_ad_3">
            <incoming>sf_xor_group_ad_3</incoming>
            <outgoing>sf_ad_3_join_of_xor_group</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_3" targetRef="id_join_of_xor_group" id="sf_ad_3_join_of_xor_group"/>
        <exclusiveGateway name="join" id="id_join_of_xor_group">
            <incoming>sf_ad_2_join_of_xor_group</incoming>
            <incoming>sf_ad_3_join_of_xor_group</incoming>
            <outgoing>sf_join_of_xor_group_ad_4</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_join_of_xor_group" targetRef="id_ad_4" id="sf_join_of_xor_group_ad_4"/>
        <userTask name="Task 4" id="id_ad_4">
            <incoming>sf_join_of_xor_group_ad_4</incoming>
            <outgoing>sf_ad_4_end6</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_4" targetRef="id_end6" id="sf_ad_4_end6"/>
        <endEvent name="end" id="id_end6">
            <incoming>sf_ad_4_end6</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="Process_BOmvKZCgYt">
            <ns4:BPMNShape bpmnElement="id_start5" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="175.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="150.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_xor_group" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="290.0" y="170.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="380.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_3" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="380.0" y="240.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_xor_group" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="530.0" y="170.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_4" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="620.0" y="150.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end6" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="770.0" y="175.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_ad_4_end6">
                <ns2:waypoint x="720.0" y="190.0"/>
                <ns2:waypoint x="770.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_1_xor_group">
                <ns2:waypoint x="240.0" y="190.0"/>
                <ns2:waypoint x="290.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start5_ad_1">
                <ns2:waypoint x="90.0" y="190.0"/>
                <ns2:waypoint x="140.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_group_ad_2">
                <ns2:waypoint x="330.0" y="190.0"/>
                <ns2:waypoint x="355.0" y="190.0"/>
                <ns2:waypoint x="355.0" y="100.0"/>
                <ns2:waypoint x="380.0" y="100.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="265.0" y="145.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_3_join_of_xor_group">
                <ns2:waypoint x="480.0" y="280.0"/>
                <ns2:waypoint x="505.0" y="280.0"/>
                <ns2:waypoint x="505.0" y="190.0"/>
                <ns2:waypoint x="530.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_xor_group_ad_4">
                <ns2:waypoint x="570.0" y="190.0"/>
                <ns2:waypoint x="620.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_group_ad_3">
                <ns2:waypoint x="330.0" y="190.0"/>
                <ns2:waypoint x="355.0" y="190.0"/>
                <ns2:waypoint x="355.0" y="280.0"/>
                <ns2:waypoint x="380.0" y="280.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="265.0" y="235.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_2_join_of_xor_group">
                <ns2:waypoint x="480.0" y="100.0"/>
                <ns2:waypoint x="505.0" y="100.0"/>
                <ns2:waypoint x="505.0" y="190.0"/>
                <ns2:waypoint x="530.0" y="190.0"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>

```

<script type="text/javascript" src="../js/highlight.js"></script>