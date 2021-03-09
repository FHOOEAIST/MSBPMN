
const containerBefore = document.getElementById('container');
const originalViewer = new BpmnJS({
    container: containerBefore
});

originalViewer.importXML(`<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="VA_CKD_Protocol" id="VA_CKD_Protocol">
        <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start64">
            <outgoing>sf_start64_id_001</outgoing>
        </startEvent>
        <sequenceFlow sourceRef="id_start64" targetRef="id_id_001" id="sf_start64_id_001"/>
        <userTask name="History and Phyiscal" id="id_id_001">
            <incoming>sf_start64_id_001</incoming>
            <outgoing>sf_id_001_id_002</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_001" targetRef="id_id_002" id="sf_id_001_id_002"/>
        <userTask name="At-Risk Population" id="id_id_002">
            <incoming>sf_id_001_id_002</incoming>
            <outgoing>sf_id_002_id_003</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_002" targetRef="id_id_003" id="sf_id_002_id_003"/>
        <subProcess name="Urgent/Emergent Conditions" id="id_id_003">
            <incoming>sf_id_002_id_003</incoming>
            <outgoing>sf_id_003_xor_group</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start66">
                <outgoing>sf_start66_id_003.1</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start66" targetRef="id_id_003.1" id="sf_start66_id_003.1"/>
            <userTask name="Refer to emergency department." id="id_id_003.1">
                <incoming>sf_start66_id_003.1</incoming>
                <outgoing>sf_id_003.1_end67</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_id_003.1" targetRef="id_end67" id="sf_id_003.1_end67"/>
            <endEvent name="end" id="id_end67">
                <incoming>sf_id_003.1_end67</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_id_003" targetRef="id_xor_group" id="sf_id_003_xor_group"/>
        <exclusiveGateway name="Xor_group" id="id_xor_group">
            <incoming>sf_id_003_xor_group</incoming>
            <outgoing>sf_xor_group_id_004</outgoing>
            <outgoing>sf_xor_group_join_of_xor_group</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_xor_group" targetRef="id_id_004" name="Has evidence of CKD?" id="sf_xor_group_id_004">
            <conditionExpression id="sf_xor_group_id_004_condition">Has evidence of CKD?</conditionExpression>
        </sequenceFlow>
        <sequenceFlow sourceRef="id_xor_group" targetRef="id_join_of_xor_group" name="else" id="sf_xor_group_join_of_xor_group">
            <conditionExpression id="sf_xor_group_join_of_xor_group_condition">else</conditionExpression>
        </sequenceFlow>
        <subProcess name="Criteria for Confirmed CKD" id="id_id_004">
            <incoming>sf_xor_group_id_004</incoming>
            <outgoing>sf_id_004_join_of_xor_group</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start68">
                <outgoing>sf_start68_id_004.1</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start68" targetRef="id_id_004.1" id="sf_start68_id_004.1"/>
            <userTask name="Stages of CKD." id="id_id_004.1">
                <incoming>sf_start68_id_004.1</incoming>
                <outgoing>sf_id_004.1_id_004.2</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_id_004.1" targetRef="id_id_004.2" id="sf_id_004.1_id_004.2"/>
            <userTask name="Strategies to Slow Progression." id="id_id_004.2">
                <incoming>sf_id_004.1_id_004.2</incoming>
                <outgoing>sf_id_004.2_end69</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_id_004.2" targetRef="id_end69" id="sf_id_004.2_end69"/>
            <endEvent name="end" id="id_end69">
                <incoming>sf_id_004.2_end69</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_id_004" targetRef="id_join_of_xor_group" id="sf_id_004_join_of_xor_group"/>
        <exclusiveGateway name="join" id="id_join_of_xor_group">
            <incoming>sf_id_004_join_of_xor_group</incoming>
            <incoming>sf_xor_group_join_of_xor_group</incoming>
            <outgoing>sf_join_of_xor_group_end65</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_join_of_xor_group" targetRef="id_end65" id="sf_join_of_xor_group_end65"/>
        <endEvent name="end" id="id_end65">
            <incoming>sf_join_of_xor_group_end65</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="VA_CKD_Protocol">
            <ns4:BPMNShape bpmnElement="id_start66" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="530.0" y="270.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_003.1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="610.0" y="245.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end67" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="760.0" y="270.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_id_003.1_end67">
                <ns2:waypoint x="710.0" y="285.0"/>
                <ns2:waypoint x="760.0" y="285.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start66_id_003.1">
                <ns2:waypoint x="560.0" y="285.0"/>
                <ns2:waypoint x="610.0" y="285.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start68" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1080.0" y="175.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_004.1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1160.0" y="150.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_004.2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1310.0" y="150.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end69" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1460.0" y="175.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_id_004.1_id_004.2">
                <ns2:waypoint x="1260.0" y="190.0"/>
                <ns2:waypoint x="1310.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start68_id_004.1">
                <ns2:waypoint x="1110.0" y="190.0"/>
                <ns2:waypoint x="1160.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_004.2_end69">
                <ns2:waypoint x="1410.0" y="190.0"/>
                <ns2:waypoint x="1460.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start64" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="247.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_001" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="222.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_002" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="290.0" y="225.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_003" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="440.0" y="155.0" width="410.0" height="230.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_xor_group" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="900.0" y="257.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_004" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="990.0" y="60.0" width="560.0" height="230.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_xor_group" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1600.0" y="261.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end65" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1690.0" y="266.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_id_003_xor_group">
                <ns2:waypoint x="850.0" y="270.0"/>
                <ns2:waypoint x="875.0" y="270.0"/>
                <ns2:waypoint x="875.0" y="277.0"/>
                <ns2:waypoint x="900.0" y="277.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_004_join_of_xor_group">
                <ns2:waypoint x="1550.0" y="175.0"/>
                <ns2:waypoint x="1575.0" y="175.0"/>
                <ns2:waypoint x="1575.0" y="281.0"/>
                <ns2:waypoint x="1600.0" y="281.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_002_id_003">
                <ns2:waypoint x="390.0" y="265.0"/>
                <ns2:waypoint x="415.0" y="265.0"/>
                <ns2:waypoint x="415.0" y="270.0"/>
                <ns2:waypoint x="440.0" y="270.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start64_id_001">
                <ns2:waypoint x="90.0" y="262.0"/>
                <ns2:waypoint x="140.0" y="262.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_group_id_004">
                <ns2:waypoint x="940.0" y="277.0"/>
                <ns2:waypoint x="965.0" y="277.0"/>
                <ns2:waypoint x="965.0" y="175.00000000000003"/>
                <ns2:waypoint x="990.0" y="175.00000000000003"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="875.0" y="226.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_001_id_002">
                <ns2:waypoint x="240.0" y="262.0"/>
                <ns2:waypoint x="265.0" y="262.0"/>
                <ns2:waypoint x="265.0" y="265.0"/>
                <ns2:waypoint x="290.0" y="265.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_group_join_of_xor_group">
                <ns2:waypoint x="940.0" y="277.0"/>
                <ns2:waypoint x="1270.0" y="277.0"/>
                <ns2:waypoint x="1270.0" y="281.0"/>
                <ns2:waypoint x="1600.0" y="281.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="1180.0" y="279.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_xor_group_end65">
                <ns2:waypoint x="1640.0" y="281.0"/>
                <ns2:waypoint x="1690.0" y="281.0"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>
`, function(err){
    originalViewer.get('canvas').zoom('fit-viewport');
});