
# Sequence Flow

In this section the transformation from Fhir->BPMN for an plain example is shown.


## Graph

<script type="text/javascript" src="https://unpkg.com/bpmn-js@7.2.1/dist/bpmn-navigated-viewer.production.min.js"></script>
Usage: Mouse Click + Move: Scroll around. Ctrl + Mouse Wheel: Zoom.

<div id="container" style="width: 100%; height: 500px; border: 1px solid lightgray; overflow:auto;"></div>

<script type="text/javascript" src="../js/fhir2bpmn/sequenceFlow.js"></script>

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

<PlanDefinition xmlns='http://hl7.org/fhir'>
    <id value='protocol-example'/>

    <text>
        <status value='generated'/>
    </text>

    <!--  The status of the protocol.draft | active | retired  -->
    <status value='draft'/>

    <action>
        <id value='ad_1'/>
        <title value='Task 1'/>

        <!-- related action definition -->
        <relatedAction>
            <actionId value='ad_2'/>
            <relationship value='before-start'/>
        </relatedAction>
    </action>

    <action>
        <id value='ad_2'/>
        <title value='Task 2'/>

        <!-- related action definition -->
        <relatedAction>
            <actionId value='ad_3'/>
            <relationship value='before-start'/>
        </relatedAction>
    </action>

    <action>
        <id value='ad_3'/>
        <title value='Task 3'/>
    </action>

</PlanDefinition>
```

## BPMN

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="Process_67T9TdYD6C" id="Process_67T9TdYD6C">
        <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start1">
            <outgoing>sf_start1_ad_1</outgoing>
        </startEvent>
        <sequenceFlow sourceRef="id_start1" targetRef="id_ad_1" id="sf_start1_ad_1"/>
        <userTask name="Task 1" id="id_ad_1">
            <incoming>sf_start1_ad_1</incoming>
            <outgoing>sf_ad_1_ad_2</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_1" targetRef="id_ad_2" id="sf_ad_1_ad_2"/>
        <userTask name="Task 2" id="id_ad_2">
            <incoming>sf_ad_1_ad_2</incoming>
            <outgoing>sf_ad_2_ad_3</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_2" targetRef="id_ad_3" id="sf_ad_2_ad_3"/>
        <userTask name="Task 3" id="id_ad_3">
            <incoming>sf_ad_2_ad_3</incoming>
            <outgoing>sf_ad_3_end2</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_3" targetRef="id_end2" id="sf_ad_3_end2"/>
        <endEvent name="end" id="id_end2">
            <incoming>sf_ad_3_end2</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="Process_67T9TdYD6C">
            <ns4:BPMNShape bpmnElement="id_start1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="290.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_3" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="440.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="590.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_ad_3_end2">
                <ns2:waypoint x="540.0" y="100.0"/>
                <ns2:waypoint x="590.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_1_ad_2">
                <ns2:waypoint x="240.0" y="100.0"/>
                <ns2:waypoint x="290.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start1_ad_1">
                <ns2:waypoint x="90.0" y="100.0"/>
                <ns2:waypoint x="140.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_2_ad_3">
                <ns2:waypoint x="390.0" y="100.0"/>
                <ns2:waypoint x="440.0" y="100.0"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>

```

<script type="text/javascript" src="../js/highlight.js"></script>