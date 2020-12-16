
const containerBefore = document.getElementById('container');
const originalViewer = new BpmnJS({
    container: containerBefore
});

originalViewer.importXML(`<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="PlanDefinition_ANC_Contact" id="PlanDefinition_ANC_Contact">
        <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start30">
            <outgoing>sf_start30_ad_01</outgoing>
        </startEvent>
        <sequenceFlow sourceRef="id_start30" targetRef="id_ad_01" id="sf_start30_ad_01"/>
        <userTask name="Registration" id="id_ad_01">
            <incoming>sf_start30_ad_01</incoming>
            <outgoing>sf_ad_01_ad_02</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_01" targetRef="id_ad_02" id="sf_ad_01_ad_02"/>
        <subProcess name="Record health history" id="id_ad_02">
            <incoming>sf_ad_01_ad_02</incoming>
            <outgoing>sf_ad_02_ad_05</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start32">
                <outgoing>sf_start32_xor_group</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start32" targetRef="id_xor_group" id="sf_start32_xor_group"/>
            <exclusiveGateway name="Xor_group" id="id_xor_group">
                <incoming>sf_start32_xor_group</incoming>
                <outgoing>sf_xor_group_ad_03</outgoing>
                <outgoing>sf_xor_group_join_of_xor_group</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_xor_group" targetRef="id_ad_03" name="Is First Contact" id="sf_xor_group_ad_03">
                <conditionExpression id="sf_xor_group_ad_03_condition">Is First Contact</conditionExpression>
            </sequenceFlow>
            <sequenceFlow sourceRef="id_xor_group" targetRef="id_join_of_xor_group" name="else" id="sf_xor_group_join_of_xor_group">
                <conditionExpression id="sf_xor_group_join_of_xor_group_condition">else</conditionExpression>
            </sequenceFlow>
            <userTask name="Record first contact information" id="id_ad_03">
                <incoming>sf_xor_group_ad_03</incoming>
                <outgoing>sf_ad_03_join_of_xor_group</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_ad_03" targetRef="id_join_of_xor_group" id="sf_ad_03_join_of_xor_group"/>
            <exclusiveGateway name="join" id="id_join_of_xor_group">
                <incoming>sf_ad_03_join_of_xor_group</incoming>
                <incoming>sf_xor_group_join_of_xor_group</incoming>
                <outgoing>sf_join_of_xor_group_ad_04</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_join_of_xor_group" targetRef="id_ad_04" id="sf_join_of_xor_group_ad_04"/>
            <userTask name="Record every contact information" id="id_ad_04">
                <incoming>sf_join_of_xor_group_ad_04</incoming>
                <outgoing>sf_ad_04_end33</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_ad_04" targetRef="id_end33" id="sf_ad_04_end33"/>
            <endEvent name="end" id="id_end33">
                <incoming>sf_ad_04_end33</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_ad_02" targetRef="id_ad_05" id="sf_ad_02_ad_05"/>
        <userTask name="Assess danger signs" id="id_ad_05">
            <incoming>sf_ad_02_ad_05</incoming>
            <outgoing>sf_ad_05_ad_06</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_05" targetRef="id_ad_06" id="sf_ad_05_ad_06"/>
        <userTask name="Assess current pregnancy conditions, including symptoms and lab tests" id="id_ad_06">
            <incoming>sf_ad_05_ad_06</incoming>
            <outgoing>sf_ad_06_ad_07</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_06" targetRef="id_ad_07" id="sf_ad_06_ad_07"/>
        <userTask name="Case management or referral" id="id_ad_07">
            <incoming>sf_ad_06_ad_07</incoming>
            <outgoing>sf_ad_07_ad_08</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_07" targetRef="id_ad_08" id="sf_ad_07_ad_08"/>
        <userTask name="Schedule follow-up visit" id="id_ad_08">
            <incoming>sf_ad_07_ad_08</incoming>
            <outgoing>sf_ad_08_end31</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_ad_08" targetRef="id_end31" id="sf_ad_08_end31"/>
        <endEvent name="end" id="id_end31">
            <incoming>sf_ad_08_end31</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="PlanDefinition_ANC_Contact">
            <ns4:BPMNShape bpmnElement="id_start32" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="380.0" y="244.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_xor_group" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="460.0" y="239.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_03" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="550.0" y="150.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_xor_group" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="700.0" y="237.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_04" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="790.0" y="214.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end33" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="940.0" y="239.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_join_of_xor_group_ad_04">
                <ns2:waypoint x="740.0" y="257.0"/>
                <ns2:waypoint x="765.0" y="257.0"/>
                <ns2:waypoint x="765.0" y="254.0"/>
                <ns2:waypoint x="790.0" y="254.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start32_xor_group">
                <ns2:waypoint x="410.0" y="259.0"/>
                <ns2:waypoint x="460.0" y="259.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_group_ad_03">
                <ns2:waypoint x="500.0" y="259.0"/>
                <ns2:waypoint x="525.0" y="259.0"/>
                <ns2:waypoint x="525.0" y="190.0"/>
                <ns2:waypoint x="550.0" y="190.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="435.0" y="224.5" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_03_join_of_xor_group">
                <ns2:waypoint x="650.0" y="190.0"/>
                <ns2:waypoint x="675.0" y="190.0"/>
                <ns2:waypoint x="675.0" y="257.0"/>
                <ns2:waypoint x="700.0" y="257.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_group_join_of_xor_group">
                <ns2:waypoint x="500.0" y="259.0"/>
                <ns2:waypoint x="600.0" y="259.0"/>
                <ns2:waypoint x="600.0" y="257.0"/>
                <ns2:waypoint x="700.0" y="257.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="510.0" y="258.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_04_end33">
                <ns2:waypoint x="890.0" y="254.0"/>
                <ns2:waypoint x="940.0" y="254.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start30" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="192.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_01" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="167.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_02" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="290.0" y="60.0" width="740.0" height="294.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_05" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1080.0" y="167.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_06" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1230.0" y="167.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_07" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1380.0" y="167.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ad_08" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1530.0" y="167.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end31" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1680.0" y="192.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_ad_06_ad_07">
                <ns2:waypoint x="1330.0" y="207.0"/>
                <ns2:waypoint x="1380.0" y="207.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start30_ad_01">
                <ns2:waypoint x="90.0" y="207.0"/>
                <ns2:waypoint x="140.0" y="207.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_02_ad_05">
                <ns2:waypoint x="1030.0" y="207.0"/>
                <ns2:waypoint x="1080.0" y="207.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_08_end31">
                <ns2:waypoint x="1630.0" y="207.0"/>
                <ns2:waypoint x="1680.0" y="207.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_01_ad_02">
                <ns2:waypoint x="240.0" y="207.0"/>
                <ns2:waypoint x="265.0" y="207.0"/>
                <ns2:waypoint x="265.0" y="207.00000000000006"/>
                <ns2:waypoint x="290.0" y="207.00000000000006"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_05_ad_06">
                <ns2:waypoint x="1180.0" y="207.0"/>
                <ns2:waypoint x="1230.0" y="207.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ad_07_ad_08">
                <ns2:waypoint x="1480.0" y="207.0"/>
                <ns2:waypoint x="1530.0" y="207.0"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>
`, function(err){
    originalViewer.get('canvas').zoom('fit-viewport');
});