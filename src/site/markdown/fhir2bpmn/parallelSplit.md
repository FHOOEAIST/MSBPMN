
# Parallel Split

In this section the transformation from Fhir->BPMN for an parallel split is shown.


## Graph

<script type="text/javascript" src="https://unpkg.com/bpmn-js@7.2.1/dist/bpmn-navigated-viewer.production.min.js"></script>
Usage: Mouse Click + Move: Scroll around. Ctrl + Mouse Wheel: Zoom.

<div id="container" style="width: 100%; height: 500px; border: 1px solid lightgray; overflow:auto;"></div>

<script type="text/javascript" src="../js/fhir2bpmn/parallelSplit.js"></script>

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
            <actionId value="par_group"/>
            <relationship value="before-start"/>
        </relatedAction>
    </action>

    <action>
        <id value="par_group"/>
        <title value="par_group"/>
        <groupingBehavior value="logical-group"/>
        <selectionBehavior value="all"/>
        <action>
            <id value="ad_2"/>
            <title value="Task 2"/>
            <relatedAction>
                <actionId value="par_group"/>
                <relationship value="before-end"/>
            </relatedAction>
        </action>
        <action>
            <id value="ad_3"/>
            <title value="Task 3"/>
            <relatedAction>
                <actionId value="par_group"/>
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
    <process isExecutable="false" name="Process_hifmeqg4aT" id="Process_hifmeqg4aT">
        <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start3">
            <outgoing>sf_start3_ad_1</outgoing>
        </startEvent>
        <sequenceFlow sourceRef="id_start3" targetRef="id_ad_1" id="sf_start3_ad_1"/>
        <userTask name="Task 1" id="id_ad_1">
            <incoming>sf_start3_ad_1</incoming>
            <outgoing>sf_ad_1_par_group</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_1" targetRef="id_par_group" id="sf_ad_1_par_group"/>
        <parallelGateway name="par_group" id="id_par_group">
            <incoming>sf_ad_1_par_group</incoming>
            <outgoing>sf_par_group_ad_3</outgoing>
            <outgoing>sf_par_group_ad_2</outgoing>
        </parallelGateway>
        <sequenceFlow sourceRef="id_par_group" targetRef="id_ad_3" id="sf_par_group_ad_3"/>
        <sequenceFlow sourceRef="id_par_group" targetRef="id_ad_2" id="sf_par_group_ad_2"/>
        <userTask name="Task 3" id="id_ad_3">
            <incoming>sf_par_group_ad_3</incoming>
            <outgoing>sf_ad_3_join_of_par_group</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_3" targetRef="id_join_of_par_group" id="sf_ad_3_join_of_par_group"/>
        <userTask name="Task 2" id="id_ad_2">
            <incoming>sf_par_group_ad_2</incoming>
            <outgoing>sf_ad_2_join_of_par_group</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_2" targetRef="id_join_of_par_group" id="sf_ad_2_join_of_par_group"/>
        <parallelGateway name="join" id="id_join_of_par_group">
            <incoming>sf_ad_2_join_of_par_group</incoming>
            <incoming>sf_ad_3_join_of_par_group</incoming>
            <outgoing>sf_join_of_par_group_ad_4</outgoing>
        </parallelGateway>
        <sequenceFlow sourceRef="id_join_of_par_group" targetRef="id_ad_4" id="sf_join_of_par_group_ad_4"/>
        <userTask name="Task 4" id="id_ad_4">
            <incoming>sf_join_of_par_group_ad_4</incoming>
            <outgoing>sf_ad_4_end4</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_4" targetRef="id_end4" id="sf_ad_4_end4"/>
        <endEvent name="end" id="id_end4">
            <incoming>sf_ad_4_end4</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="Process_hifmeqg4aT">
            <ns4:BPMNShape bpmnElement="id_start3" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="175.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="150.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_par_group" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="290.0" y="170.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_3" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="380.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="380.0" y="240.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_par_group" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="530.0" y="170.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_4" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="620.0" y="150.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end4" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="770.0" y="175.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_join_of_par_group_ad_4">
                <ns2:waypoint x="570.0" y="190.0"/>
                <ns2:waypoint x="620.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_4_end4">
                <ns2:waypoint x="720.0" y="190.0"/>
                <ns2:waypoint x="770.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_3_join_of_par_group">
                <ns2:waypoint x="480.0" y="100.0"/>
                <ns2:waypoint x="505.0" y="100.0"/>
                <ns2:waypoint x="505.0" y="190.0"/>
                <ns2:waypoint x="530.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_1_par_group">
                <ns2:waypoint x="240.0" y="190.0"/>
                <ns2:waypoint x="290.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_par_group_ad_3">
                <ns2:waypoint x="330.0" y="190.0"/>
                <ns2:waypoint x="355.0" y="190.0"/>
                <ns2:waypoint x="355.0" y="100.0"/>
                <ns2:waypoint x="380.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_par_group_ad_2">
                <ns2:waypoint x="330.0" y="190.0"/>
                <ns2:waypoint x="355.0" y="190.0"/>
                <ns2:waypoint x="355.0" y="280.0"/>
                <ns2:waypoint x="380.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start3_ad_1">
                <ns2:waypoint x="90.0" y="190.0"/>
                <ns2:waypoint x="140.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_2_join_of_par_group">
                <ns2:waypoint x="480.0" y="280.0"/>
                <ns2:waypoint x="505.0" y="280.0"/>
                <ns2:waypoint x="505.0" y="190.0"/>
                <ns2:waypoint x="530.0" y="190.0"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>

```

<script type="text/javascript" src="../js/highlight.js"></script>