
# Actors

Transformation of the new actor concept, which can be found [here](https://jira.hl7.org/browse/FHIR-20825).


## Graph

<script type="text/javascript" src="https://unpkg.com/bpmn-js@7.2.1/dist/bpmn-navigated-viewer.production.min.js"></script>
Usage: Mouse Click + Move: Scroll around. Ctrl + Mouse Wheel: Zoom.

<div id="container" style="width: 100%; height: 500px; border: 1px solid lightgray; overflow:auto;"></div>

<script type="text/javascript" src="../js/fhir2bpmn/actors.js"></script>

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
    <id value="participant"/>

    <actor>
        <id value="actor_1"/>
        <label value="Max Mustermann"/>
        <description value="Spezialist für eh alles"/>
        <options>
            <type value="patient"/>
        </options>
        <options>
            <type value="patient"/>
            <role>
                <coding>
                    <system value="some_system"/>
                    <code value="some_code"/>
                </coding>
            </role>
        </options>
    </actor>

    <actor>
        <id value="actor_2"/>
        <label value="Maxine Musterfrau"/>
        <description value="Spezialistin für noch viel mehr"/>
        <options>
            <type value="patient"/>
        </options>
        <options>
            <type value="patient"/>
            <role>
                <coding>
                    <system value="some_system"/>
                    <code value="some_code"/>
                </coding>
            </role>
        </options>
    </actor>

    <action>
        <id value="ad_1" />
        <title value="Action 1"/>
        <participant>
            <actor value="actor_1"/>
            <function>
                <coding>
                    <system value="some_system"/>
                    <code value="some_code"/>
                </coding>
            </function>
        </participant>
        <relatedAction>
            <actionId value='ad_2'/>
            <relationship value='before-start'/>
        </relatedAction>
    </action>

    <action>
        <id value="ad_2" />
        <title value="Action 2"/>
        <participant>
            <actor value="actor_2"/>
        </participant>
    </action>
</PlanDefinition>

```

## BPMN

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="Process_AeKbenZGUd" id="Process_AeKbenZGUd">
        <laneSet>
            <lane name="Max Mustermann" id="id_actor_1">
                <flowNodeRef>id_ad_1</flowNodeRef>
                <flowNodeRef>id_start18</flowNodeRef>
            </lane>
            <lane name="Maxine Musterfrau" id="id_actor_2">
                <flowNodeRef>id_end19</flowNodeRef>
                <flowNodeRef>id_ad_2</flowNodeRef>
            </lane>
        </laneSet>
        <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start18">
            <outgoing>sf_start18_ad_1</outgoing>
        </startEvent>
        <sequenceFlow sourceRef="id_start18" targetRef="id_ad_1" id="sf_start18_ad_1"/>
        <userTask name="Action 1" id="id_ad_1">
            <incoming>sf_start18_ad_1</incoming>
            <outgoing>sf_ad_1_ad_2</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_1" targetRef="id_ad_2" id="sf_ad_1_ad_2"/>
        <userTask name="Action 2" id="id_ad_2">
            <incoming>sf_ad_1_ad_2</incoming>
            <outgoing>sf_ad_2_end19</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_2" targetRef="id_end19" id="sf_ad_2_end19"/>
        <endEvent name="end" id="id_end19">
            <incoming>sf_ad_2_end19</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="Process_AeKbenZGUd">
            <ns4:BPMNShape bpmnElement="id_ad_1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="110.0" y="30.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_start18" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="30.0" y="55.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_start18_ad_1">
                <ns2:waypoint x="60.0" y="70.0"/>
                <ns2:waypoint x="110.0" y="70.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_actor_1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="0.0" y="0.0" width="470.0" height="140.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end19" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="410.0" y="195.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="260.0" y="170.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_ad_2_end19">
                <ns2:waypoint x="360.0" y="210.0"/>
                <ns2:waypoint x="410.0" y="210.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_actor_2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="0.0" y="140.0" width="470.0" height="140.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_ad_1_ad_2">
                <ns2:waypoint x="210.0" y="70.0"/>
                <ns2:waypoint x="235.0" y="70.0"/>
                <ns2:waypoint x="235.0" y="210.0"/>
                <ns2:waypoint x="260.0" y="210.0"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>

```

<script type="text/javascript" src="../js/highlight.js"></script>