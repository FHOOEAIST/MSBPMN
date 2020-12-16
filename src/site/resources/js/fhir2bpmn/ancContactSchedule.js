
const containerBefore = document.getElementById('container');
const originalViewer = new BpmnJS({
    container: containerBefore
});

originalViewer.importXML(`<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="PlanDefinition_ANC_Contact_Schedule" id="PlanDefinition_ANC_Contact_Schedule">
        <startEvent isInterrupting="false" parallelMultiple="false" name="Contact 1" id="id_start35">
            <outgoing>sf_start35_ad_01_triggerAction</outgoing>
            <timerEventDefinition id="event_start35">
                <timeDuration id="event_start35_timeExpression">Up to 12 weeks gestation</timeDuration>
            </timerEventDefinition>
        </startEvent>
        <sequenceFlow sourceRef="id_start35" targetRef="id_ad_01_triggerAction" id="sf_start35_ad_01_triggerAction"/>
        <userTask name="Contact 1" id="id_ad_01_triggerAction">
            <incoming>sf_start35_ad_01_triggerAction</incoming>
            <outgoing>sf_ad_01_triggerAction_ad_02</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_01_triggerAction" targetRef="id_ad_02" id="sf_ad_01_triggerAction_ad_02"/>
        <intermediateCatchEvent name="Contact 2" id="id_ad_02">
            <incoming>sf_ad_01_triggerAction_ad_02</incoming>
            <outgoing>sf_ad_02_ad_02_triggerAction</outgoing>
            <timerEventDefinition id="event_ad_02">
                <timeDuration id="event_ad_02_timeExpression">20 weeks gestation</timeDuration>
            </timerEventDefinition>
        </intermediateCatchEvent>
        <sequenceFlow sourceRef="id_ad_02" targetRef="id_ad_02_triggerAction" id="sf_ad_02_ad_02_triggerAction"/>
        <userTask name="Contact 2" id="id_ad_02_triggerAction">
            <incoming>sf_ad_02_ad_02_triggerAction</incoming>
            <outgoing>sf_ad_02_triggerAction_ad_03</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_02_triggerAction" targetRef="id_ad_03" id="sf_ad_02_triggerAction_ad_03"/>
        <intermediateCatchEvent name="Contact 3" id="id_ad_03">
            <incoming>sf_ad_02_triggerAction_ad_03</incoming>
            <outgoing>sf_ad_03_ad_03_triggerAction</outgoing>
            <timerEventDefinition id="event_ad_03">
                <timeDuration id="event_ad_03_timeExpression">26 weeks gestation</timeDuration>
            </timerEventDefinition>
        </intermediateCatchEvent>
        <sequenceFlow sourceRef="id_ad_03" targetRef="id_ad_03_triggerAction" id="sf_ad_03_ad_03_triggerAction"/>
        <userTask name="Contact 3" id="id_ad_03_triggerAction">
            <incoming>sf_ad_03_ad_03_triggerAction</incoming>
            <outgoing>sf_ad_03_triggerAction_ad_04</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_03_triggerAction" targetRef="id_ad_04" id="sf_ad_03_triggerAction_ad_04"/>
        <intermediateCatchEvent name="Contact 4" id="id_ad_04">
            <incoming>sf_ad_03_triggerAction_ad_04</incoming>
            <outgoing>sf_ad_04_ad_04_triggerAction</outgoing>
            <timerEventDefinition id="event_ad_04">
                <timeDuration id="event_ad_04_timeExpression">30 weeks gestation</timeDuration>
            </timerEventDefinition>
        </intermediateCatchEvent>
        <sequenceFlow sourceRef="id_ad_04" targetRef="id_ad_04_triggerAction" id="sf_ad_04_ad_04_triggerAction"/>
        <userTask name="Contact 4" id="id_ad_04_triggerAction">
            <incoming>sf_ad_04_ad_04_triggerAction</incoming>
            <outgoing>sf_ad_04_triggerAction_ad_05</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_04_triggerAction" targetRef="id_ad_05" id="sf_ad_04_triggerAction_ad_05"/>
        <intermediateCatchEvent name="Contact 5" id="id_ad_05">
            <incoming>sf_ad_04_triggerAction_ad_05</incoming>
            <outgoing>sf_ad_05_ad_05_triggerAction</outgoing>
            <timerEventDefinition id="event_ad_05">
                <timeDuration id="event_ad_05_timeExpression">34 weeks gestation</timeDuration>
            </timerEventDefinition>
        </intermediateCatchEvent>
        <sequenceFlow sourceRef="id_ad_05" targetRef="id_ad_05_triggerAction" id="sf_ad_05_ad_05_triggerAction"/>
        <userTask name="Contact 5" id="id_ad_05_triggerAction">
            <incoming>sf_ad_05_ad_05_triggerAction</incoming>
            <outgoing>sf_ad_05_triggerAction_ad_06</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_05_triggerAction" targetRef="id_ad_06" id="sf_ad_05_triggerAction_ad_06"/>
        <intermediateCatchEvent name="Contact 6" id="id_ad_06">
            <incoming>sf_ad_05_triggerAction_ad_06</incoming>
            <outgoing>sf_ad_06_ad_06_triggerAction</outgoing>
            <timerEventDefinition id="event_ad_06">
                <timeDuration id="event_ad_06_timeExpression">36 weeks gestation</timeDuration>
            </timerEventDefinition>
        </intermediateCatchEvent>
        <sequenceFlow sourceRef="id_ad_06" targetRef="id_ad_06_triggerAction" id="sf_ad_06_ad_06_triggerAction"/>
        <userTask name="Contact 6" id="id_ad_06_triggerAction">
            <incoming>sf_ad_06_ad_06_triggerAction</incoming>
            <outgoing>sf_ad_06_triggerAction_ad_07</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_06_triggerAction" targetRef="id_ad_07" id="sf_ad_06_triggerAction_ad_07"/>
        <intermediateCatchEvent name="Contact 7" id="id_ad_07">
            <incoming>sf_ad_06_triggerAction_ad_07</incoming>
            <outgoing>sf_ad_07_ad_07_triggerAction</outgoing>
            <timerEventDefinition id="event_ad_07">
                <timeDuration id="event_ad_07_timeExpression">38 weeks gestation</timeDuration>
            </timerEventDefinition>
        </intermediateCatchEvent>
        <sequenceFlow sourceRef="id_ad_07" targetRef="id_ad_07_triggerAction" id="sf_ad_07_ad_07_triggerAction"/>
        <userTask name="Contact 7" id="id_ad_07_triggerAction">
            <incoming>sf_ad_07_ad_07_triggerAction</incoming>
            <outgoing>sf_ad_07_triggerAction_ad_08</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_07_triggerAction" targetRef="id_ad_08" id="sf_ad_07_triggerAction_ad_08"/>
        <intermediateCatchEvent name="Contact 8" id="id_ad_08">
            <incoming>sf_ad_07_triggerAction_ad_08</incoming>
            <outgoing>sf_ad_08_ad_08_triggerAction</outgoing>
            <timerEventDefinition id="event_ad_08">
                <timeDuration id="event_ad_08_timeExpression">40 weeks gestation</timeDuration>
            </timerEventDefinition>
        </intermediateCatchEvent>
        <sequenceFlow sourceRef="id_ad_08" targetRef="id_ad_08_triggerAction" id="sf_ad_08_ad_08_triggerAction"/>
        <userTask name="Contact 8" id="id_ad_08_triggerAction">
            <incoming>sf_ad_08_ad_08_triggerAction</incoming>
            <outgoing>sf_ad_08_triggerAction_ad_09</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_08_triggerAction" targetRef="id_ad_09" id="sf_ad_08_triggerAction_ad_09"/>
        <userTask name="Delivery" id="id_ad_09">
            <incoming>sf_ad_08_triggerAction_ad_09</incoming>
            <outgoing>sf_ad_09_end34</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_09" targetRef="id_end34" id="sf_ad_09_end34"/>
        <endEvent name="end" id="id_end34">
            <incoming>sf_ad_09_end34</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="PlanDefinition_ANC_Contact_Schedule">
            <ns4:BPMNShape bpmnElement="id_start35" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_01_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_02" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="290.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_02_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="370.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_03" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="520.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_03_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="600.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_04" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="750.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_04_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="830.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_05" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="980.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_05_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1060.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_06" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1210.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_06_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1290.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_07" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1440.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_07_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1520.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_08" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1670.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_08_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1750.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_09" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1900.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end34" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2050.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_ad_03_triggerAction_ad_04">
                <ns2:waypoint x="700.0" y="100.0"/>
                <ns2:waypoint x="750.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_08_ad_08_triggerAction">
                <ns2:waypoint x="1700.0" y="100.0"/>
                <ns2:waypoint x="1750.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_03_ad_03_triggerAction">
                <ns2:waypoint x="550.0" y="100.0"/>
                <ns2:waypoint x="600.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_06_triggerAction_ad_07">
                <ns2:waypoint x="1390.0" y="100.0"/>
                <ns2:waypoint x="1440.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_06_ad_06_triggerAction">
                <ns2:waypoint x="1240.0" y="100.0"/>
                <ns2:waypoint x="1290.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_02_triggerAction_ad_03">
                <ns2:waypoint x="470.0" y="100.0"/>
                <ns2:waypoint x="520.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_05_ad_05_triggerAction">
                <ns2:waypoint x="1010.0" y="100.0"/>
                <ns2:waypoint x="1060.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_07_triggerAction_ad_08">
                <ns2:waypoint x="1620.0" y="100.0"/>
                <ns2:waypoint x="1670.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_09_end34">
                <ns2:waypoint x="2000.0" y="100.0"/>
                <ns2:waypoint x="2050.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_08_triggerAction_ad_09">
                <ns2:waypoint x="1850.0" y="100.0"/>
                <ns2:waypoint x="1900.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_01_triggerAction_ad_02">
                <ns2:waypoint x="240.0" y="100.0"/>
                <ns2:waypoint x="290.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_02_ad_02_triggerAction">
                <ns2:waypoint x="320.0" y="100.0"/>
                <ns2:waypoint x="370.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_05_triggerAction_ad_06">
                <ns2:waypoint x="1160.0" y="100.0"/>
                <ns2:waypoint x="1210.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_04_triggerAction_ad_05">
                <ns2:waypoint x="930.0" y="100.0"/>
                <ns2:waypoint x="980.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_07_ad_07_triggerAction">
                <ns2:waypoint x="1470.0" y="100.0"/>
                <ns2:waypoint x="1520.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_04_ad_04_triggerAction">
                <ns2:waypoint x="780.0" y="100.0"/>
                <ns2:waypoint x="830.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start35_ad_01_triggerAction">
                <ns2:waypoint x="90.0" y="100.0"/>
                <ns2:waypoint x="140.0" y="100.0"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>
`, function(err){
    originalViewer.get('canvas').zoom('fit-viewport');
});