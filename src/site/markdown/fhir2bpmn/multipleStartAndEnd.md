
# Multiple Start

In this section the transformation form Fhir->BPMN for multiple start events is shown.


## Graph

<script type="text/javascript" src="https://unpkg.com/bpmn-js@7.2.1/dist/bpmn-navigated-viewer.production.min.js"></script>
Usage: Mouse Click + Move: Scroll around. Ctrl + Mouse Wheel: Zoom.

<div id="container" style="width: 100%; height: 500px; border: 1px solid lightgray; overflow:auto;"></div>

<script type="text/javascript" src="../js/fhir2bpmn/multipleStartAndEnd.js"></script>

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
    <id value='multiple-example'/>

    <text>
        <status value='generated'/>
    </text>

    <!--  The status of the protocol.draft | active | retired  -->
    <status value='draft'/>

    <action>
        <id value='start_1'/>
        <title value='Start 1'/>

        <!-- related action definition -->
        <relatedAction>
            <actionId value='ad_1'/>
            <relationship value='before-start'/>
        </relatedAction>

        <trigger>
            <type value="periodic"/>
            <name value="trigger1"/>
            <timingTiming>
                <event value="2020-06-17T09:30:00"/>
            </timingTiming>
        </trigger>
    </action>

    <action>
        <id value='start_2'/>
        <title value='Start 2'/>

        <!-- related action definition -->
        <relatedAction>
            <actionId value='ad_1'/>
            <relationship value='before-start'/>
        </relatedAction>

        <trigger>
            <type value="periodic"/>
            <name value="trigger2"/>
            <timingTiming>
                <event value="2020-06-17T10:30:00"/>
            </timingTiming>
        </trigger>
    </action>

    <action>
        <id value="ad_1"/>
        <title value="Task 1"/>
    </action>

</PlanDefinition>
```

## BPMN

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="Process_yDBTqK8rnR" id="Process_yDBTqK8rnR">
        <startEvent isInterrupting="false" parallelMultiple="false" name="Start 1" id="id_start16">
            <outgoing>sf_start16_start_1_triggerAction</outgoing>
            <timerEventDefinition id="trigger1">
                <timeDate id="trigger1_timeDate">2020-06-17T09:30:00+02:00</timeDate>
            </timerEventDefinition>
        </startEvent>
        <sequenceFlow sourceRef="id_start16" targetRef="id_start_1_triggerAction" id="sf_start16_start_1_triggerAction"/>
        <startEvent isInterrupting="false" parallelMultiple="false" name="Start 2" id="id_start17">
            <outgoing>sf_start17_start_2_triggerAction</outgoing>
            <timerEventDefinition id="trigger2">
                <timeDate id="trigger2_timeDate">2020-06-17T10:30:00+02:00</timeDate>
            </timerEventDefinition>
        </startEvent>
        <sequenceFlow sourceRef="id_start17" targetRef="id_start_2_triggerAction" id="sf_start17_start_2_triggerAction"/>
        <userTask name="Start 1" id="id_start_1_triggerAction">
            <incoming>sf_start16_start_1_triggerAction</incoming>
            <outgoing>sf_start_1_triggerAction_ad_1</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_start_1_triggerAction" targetRef="id_ad_1" id="sf_start_1_triggerAction_ad_1"/>
        <userTask name="Start 2" id="id_start_2_triggerAction">
            <incoming>sf_start17_start_2_triggerAction</incoming>
            <outgoing>sf_start_2_triggerAction_ad_1</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_start_2_triggerAction" targetRef="id_ad_1" id="sf_start_2_triggerAction_ad_1"/>
        <userTask name="Task 1" id="id_ad_1">
            <incoming>sf_start_1_triggerAction_ad_1</incoming>
            <incoming>sf_start_2_triggerAction_ad_1</incoming>
            <outgoing>sf_ad_1_end15</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_1" targetRef="id_end15" id="sf_ad_1_end15"/>
        <endEvent name="end" id="id_end15">
            <incoming>sf_ad_1_end15</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="Process_yDBTqK8rnR">
            <ns4:BPMNShape bpmnElement="id_start16" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_start17" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="265.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_start_1_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_start_2_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="240.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="290.0" y="150.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end15" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="440.0" y="175.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_start_1_triggerAction_ad_1">
                <ns2:waypoint x="240.0" y="100.0"/>
                <ns2:waypoint x="265.0" y="100.0"/>
                <ns2:waypoint x="265.0" y="190.0"/>
                <ns2:waypoint x="290.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_1_end15">
                <ns2:waypoint x="390.0" y="190.0"/>
                <ns2:waypoint x="440.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start17_start_2_triggerAction">
                <ns2:waypoint x="90.0" y="280.0"/>
                <ns2:waypoint x="140.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start_2_triggerAction_ad_1">
                <ns2:waypoint x="240.0" y="280.0"/>
                <ns2:waypoint x="265.0" y="280.0"/>
                <ns2:waypoint x="265.0" y="190.0"/>
                <ns2:waypoint x="290.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start16_start_1_triggerAction">
                <ns2:waypoint x="90.0" y="100.0"/>
                <ns2:waypoint x="140.0" y="100.0"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>

```

<script type="text/javascript" src="../js/highlight.js"></script>