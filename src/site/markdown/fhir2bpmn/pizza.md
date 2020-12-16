
# Conditional Event

In this section the transformation from Fhir->BPMN for conditional events is shown.


## Graph

<script type="text/javascript" src="https://unpkg.com/bpmn-js@7.2.1/dist/bpmn-navigated-viewer.production.min.js"></script>
Usage: Mouse Click + Move: Scroll around. Ctrl + Mouse Wheel: Zoom.

<div id="container" style="width: 100%; height: 500px; border: 1px solid lightgray; overflow:auto;"></div>

<script type="text/javascript" src="../js/fhir2bpmn/pizza.js"></script>

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
        <title value='Take Pizza from freezer'/>

        <!-- related action definition -->
        <relatedAction>
            <actionId value='ad_2'/>
            <relationship value='before-start'/>
        </relatedAction>

        <trigger>
            <type value="named-event"/>
            <name value="frozen-pizza-desired"/>

            <condition>
                <language value="text/cql"/>
                <expression value="Frozen Pizza desired"/>
            </condition>
        </trigger>
    </action>

    <action>
        <id value='ad_2'/>
        <title value='Switch on Oven'/>

        <!-- related action definition -->
        <relatedAction>
            <actionId value='ad_3'/>
            <relationship value='before-start'/>
        </relatedAction>
    </action>

    <action>
        <id value='ad_3'/>
        <title value='Put Pizza in Oven'/>

        <!-- related action definition -->
        <relatedAction>
            <actionId value='ad_4'/>
            <relationship value='before-start'/>
        </relatedAction>

        <trigger>
            <type value="named-event"/>
            <name value="oven-to-180"/>
            <condition>
                <language value="text/cql"/>
                <expression value="Oven to 180°"/>
            </condition>
        </trigger>
    </action>

    <action>
        <id value='ad_4'/>
        <title value='Eat Pizza'/>

        <trigger>
            <type value="named-event"/>
            <name value="pizza-ready"/>
            <condition>
                <language value="text/cql"/>
                <expression value="Pizza Ready"/>
            </condition>
        </trigger>
    </action>

</PlanDefinition>
```

## BPMN

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="Process_F3F6O38U1y" id="Process_F3F6O38U1y">
        <startEvent isInterrupting="false" parallelMultiple="false" name="Take Pizza from freezer" id="id_start14">
            <outgoing>sf_start14_ad_1_triggerAction</outgoing>
            <conditionalEventDefinition id="event_start14">
                <condition xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="tFormalExpression" language="text/cql">Frozen Pizza desired</condition>
            </conditionalEventDefinition>
        </startEvent>
        <sequenceFlow sourceRef="id_start14" targetRef="id_ad_1_triggerAction" id="sf_start14_ad_1_triggerAction"/>
        <userTask name="Take Pizza from freezer" id="id_ad_1_triggerAction">
            <incoming>sf_start14_ad_1_triggerAction</incoming>
            <outgoing>sf_ad_1_triggerAction_ad_2</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_1_triggerAction" targetRef="id_ad_2" id="sf_ad_1_triggerAction_ad_2"/>
        <userTask name="Switch on Oven" id="id_ad_2">
            <incoming>sf_ad_1_triggerAction_ad_2</incoming>
            <outgoing>sf_ad_2_ad_3</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_2" targetRef="id_ad_3" id="sf_ad_2_ad_3"/>
        <intermediateCatchEvent name="Put Pizza in Oven" id="id_ad_3">
            <incoming>sf_ad_2_ad_3</incoming>
            <outgoing>sf_ad_3_ad_3_triggerAction</outgoing>
            <conditionalEventDefinition id="event_ad_3">
                <condition xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="tFormalExpression" language="text/cql">Oven to 180°</condition>
            </conditionalEventDefinition>
        </intermediateCatchEvent>
        <sequenceFlow sourceRef="id_ad_3" targetRef="id_ad_3_triggerAction" id="sf_ad_3_ad_3_triggerAction"/>
        <userTask name="Put Pizza in Oven" id="id_ad_3_triggerAction">
            <incoming>sf_ad_3_ad_3_triggerAction</incoming>
            <outgoing>sf_ad_3_triggerAction_ad_4</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_3_triggerAction" targetRef="id_ad_4" id="sf_ad_3_triggerAction_ad_4"/>
        <intermediateCatchEvent name="Eat Pizza" id="id_ad_4">
            <incoming>sf_ad_3_triggerAction_ad_4</incoming>
            <outgoing>sf_ad_4_ad_4_triggerAction</outgoing>
            <conditionalEventDefinition id="event_ad_4">
                <condition xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="tFormalExpression" language="text/cql">Pizza Ready</condition>
            </conditionalEventDefinition>
        </intermediateCatchEvent>
        <sequenceFlow sourceRef="id_ad_4" targetRef="id_ad_4_triggerAction" id="sf_ad_4_ad_4_triggerAction"/>
        <userTask name="Eat Pizza" id="id_ad_4_triggerAction">
            <incoming>sf_ad_4_ad_4_triggerAction</incoming>
            <outgoing>sf_ad_4_triggerAction_end13</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_4_triggerAction" targetRef="id_end13" id="sf_ad_4_triggerAction_end13"/>
        <endEvent name="end" id="id_end13">
            <incoming>sf_ad_4_triggerAction_end13</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="Process_F3F6O38U1y">
            <ns4:BPMNShape bpmnElement="id_start14" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_1_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="290.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_3" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="440.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_3_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="520.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_4" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="670.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_4_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="750.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end13" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="900.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_start14_ad_1_triggerAction">
                <ns2:waypoint x="90.0" y="100.0"/>
                <ns2:waypoint x="140.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_4_triggerAction_end13">
                <ns2:waypoint x="850.0" y="100.0"/>
                <ns2:waypoint x="900.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_3_triggerAction_ad_4">
                <ns2:waypoint x="620.0" y="100.0"/>
                <ns2:waypoint x="670.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_1_triggerAction_ad_2">
                <ns2:waypoint x="240.0" y="100.0"/>
                <ns2:waypoint x="290.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_3_ad_3_triggerAction">
                <ns2:waypoint x="470.0" y="100.0"/>
                <ns2:waypoint x="520.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_4_ad_4_triggerAction">
                <ns2:waypoint x="700.0" y="100.0"/>
                <ns2:waypoint x="750.0" y="100.0"/>
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