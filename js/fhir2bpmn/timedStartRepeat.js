
const containerBefore = document.getElementById('container');
const originalViewer = new BpmnJS({
    container: containerBefore
});

originalViewer.importXML(`<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="Process_qBYCJZdml3" id="Process_qBYCJZdml3">
        <startEvent isInterrupting="false" parallelMultiple="false" name="Task 1" id="id_start12">
            <outgoing>sf_start12_ad_1_triggerAction</outgoing>
            <timerEventDefinition id="sampleTriggerDef">
                <timeCycle id="sampleTriggerDef_timeRepeating">R1/P10S</timeCycle>
            </timerEventDefinition>
        </startEvent>
        <sequenceFlow sourceRef="id_start12" targetRef="id_ad_1_triggerAction" id="sf_start12_ad_1_triggerAction"/>
        <userTask name="Task 1" id="id_ad_1_triggerAction">
            <incoming>sf_start12_ad_1_triggerAction</incoming>
            <outgoing>sf_ad_1_triggerAction_ad_2</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_1_triggerAction" targetRef="id_ad_2" id="sf_ad_1_triggerAction_ad_2"/>
        <userTask name="Task 2" id="id_ad_2">
            <incoming>sf_ad_1_triggerAction_ad_2</incoming>
            <outgoing>sf_ad_2_ad_3</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_2" targetRef="id_ad_3" id="sf_ad_2_ad_3"/>
        <userTask name="Task 3" id="id_ad_3">
            <incoming>sf_ad_2_ad_3</incoming>
            <outgoing>sf_ad_3_end11</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_3" targetRef="id_end11" id="sf_ad_3_end11"/>
        <endEvent name="end" id="id_end11">
            <incoming>sf_ad_3_end11</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="Process_qBYCJZdml3">
            <ns4:BPMNShape bpmnElement="id_start12" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_1_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="290.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_3" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="440.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end11" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="590.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_ad_3_end11">
                <ns2:waypoint x="540.0" y="100.0"/>
                <ns2:waypoint x="590.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_1_triggerAction_ad_2">
                <ns2:waypoint x="240.0" y="100.0"/>
                <ns2:waypoint x="290.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start12_ad_1_triggerAction">
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
`, function(err){
    originalViewer.get('canvas').zoom('fit-viewport');
});