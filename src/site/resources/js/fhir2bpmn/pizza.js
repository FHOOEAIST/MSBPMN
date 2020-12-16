
const containerBefore = document.getElementById('container');
const originalViewer = new BpmnJS({
    container: containerBefore
});

originalViewer.importXML(`<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
`, function(err){
    originalViewer.get('canvas').zoom('fit-viewport');
});