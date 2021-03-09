
const containerBefore = document.getElementById('container');
const originalViewer = new BpmnJS({
    container: containerBefore
});

originalViewer.importXML(`<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="Process_PNSAVNViU8" id="Process_PNSAVNViU8">
        <laneSet>
            <lane name="Maxine Musterfrau" id="id_actor_2">
                <flowNodeRef>id_ad_2</flowNodeRef>
                <flowNodeRef>id_end19</flowNodeRef>
            </lane>
            <lane name="Max Mustermann" id="id_actor_1">
                <flowNodeRef>id_start18</flowNodeRef>
                <flowNodeRef>id_ad_1</flowNodeRef>
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
        <ns4:BPMNPlane bpmnElement="Process_PNSAVNViU8">
            <ns4:BPMNShape bpmnElement="id_ad_2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="260.0" y="30.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end19" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="410.0" y="55.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_ad_2_end19">
                <ns2:waypoint x="360.0" y="70.0"/>
                <ns2:waypoint x="410.0" y="70.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_actor_2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="0.0" y="0.0" width="470.0" height="140.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_start18" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="30.0" y="195.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="110.0" y="170.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_start18_ad_1">
                <ns2:waypoint x="60.0" y="210.0"/>
                <ns2:waypoint x="110.0" y="210.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_actor_1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="0.0" y="140.0" width="470.0" height="140.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_ad_1_ad_2">
                <ns2:waypoint x="210.0" y="210.0"/>
                <ns2:waypoint x="235.0" y="210.0"/>
                <ns2:waypoint x="235.0" y="70.0"/>
                <ns2:waypoint x="260.0" y="70.0"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>
`, function(err){
    originalViewer.get('canvas').zoom('fit-viewport');
});