
const containerBefore = document.getElementById('container');
const originalViewer = new BpmnJS({
    container: containerBefore
});

originalViewer.importXML(`<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
`, function(err){
    originalViewer.get('canvas').zoom('fit-viewport');
});