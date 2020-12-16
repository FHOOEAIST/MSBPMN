
const containerBefore = document.getElementById('container');
const originalViewer = new BpmnJS({
    container: containerBefore
});

originalViewer.importXML(`<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="CPG_Common_Pathway" id="CPG_Common_Pathway">
        <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start28">
            <outgoing>sf_start28_id_001</outgoing>
        </startEvent>
        <sequenceFlow sourceRef="id_start28" targetRef="id_id_001" id="sf_start28_id_001"/>
        <userTask name="Registration" id="id_id_001">
            <incoming>sf_start28_id_001</incoming>
            <outgoing>sf_id_001_id_002</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_001" targetRef="id_id_002" id="sf_id_001_id_002"/>
        <userTask name="Triage" id="id_id_002">
            <incoming>sf_id_001_id_002</incoming>
            <outgoing>sf_id_002_id_003</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_002" targetRef="id_id_003" id="sf_id_002_id_003"/>
        <userTask name="Local Urgent Care" id="id_id_003">
            <incoming>sf_id_002_id_003</incoming>
            <outgoing>sf_id_003_id_004</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_003" targetRef="id_id_004" id="sf_id_003_id_004"/>
        <userTask name="History and Phyiscal" id="id_id_004">
            <incoming>sf_id_003_id_004</incoming>
            <outgoing>sf_id_004_id_005</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_004" targetRef="id_id_005" id="sf_id_004_id_005"/>
        <userTask name="Provide Counseling" id="id_id_005">
            <incoming>sf_id_004_id_005</incoming>
            <outgoing>sf_id_005_id_006</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_005" targetRef="id_id_006" id="sf_id_005_id_006"/>
        <userTask name="Diagnostic Testing" id="id_id_006">
            <incoming>sf_id_005_id_006</incoming>
            <outgoing>sf_id_006_id_007</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_006" targetRef="id_id_007" id="sf_id_006_id_007"/>
        <userTask name="Determine Diagnosis" id="id_id_007">
            <incoming>sf_id_006_id_007</incoming>
            <outgoing>sf_id_007_id_008</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_007" targetRef="id_id_008" id="sf_id_007_id_008"/>
        <userTask name="Guideline-based Care" id="id_id_008">
            <incoming>sf_id_007_id_008</incoming>
            <outgoing>sf_id_008_id_009</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_008" targetRef="id_id_009" id="sf_id_008_id_009"/>
        <userTask name="Dispense Medications" id="id_id_009">
            <incoming>sf_id_008_id_009</incoming>
            <outgoing>sf_id_009_id_010</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_009" targetRef="id_id_010" id="sf_id_009_id_010"/>
        <userTask name="Emergency Care" id="id_id_010">
            <incoming>sf_id_009_id_010</incoming>
            <outgoing>sf_id_010_id_011</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_010" targetRef="id_id_011" id="sf_id_010_id_011"/>
        <userTask name="Acute/Tertiary Care" id="id_id_011">
            <incoming>sf_id_010_id_011</incoming>
            <outgoing>sf_id_011_id_012</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_011" targetRef="id_id_012" id="sf_id_011_id_012"/>
        <userTask name="Charge for Service" id="id_id_012">
            <incoming>sf_id_011_id_012</incoming>
            <outgoing>sf_id_012_id_013</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_012" targetRef="id_id_013" id="sf_id_012_id_013"/>
        <userTask name="Discharge/Referral of Patient" id="id_id_013">
            <incoming>sf_id_012_id_013</incoming>
            <outgoing>sf_id_013_id_014</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_013" targetRef="id_id_014" id="sf_id_013_id_014"/>
        <userTask name="Record and Report" id="id_id_014">
            <incoming>sf_id_013_id_014</incoming>
            <outgoing>sf_id_014_id_015</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_014" targetRef="id_id_015" id="sf_id_014_id_015"/>
        <userTask name="Monitor and Follow-up of Patient" id="id_id_015">
            <incoming>sf_id_014_id_015</incoming>
            <outgoing>sf_id_015_id_016</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_015" targetRef="id_id_016" id="sf_id_015_id_016"/>
        <userTask name="Alerts Reminders Education" id="id_id_016">
            <incoming>sf_id_015_id_016</incoming>
            <outgoing>sf_id_016_end29</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_id_016" targetRef="id_end29" id="sf_id_016_end29"/>
        <endEvent name="end" id="id_end29">
            <incoming>sf_id_016_end29</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="CPG_Common_Pathway">
            <ns4:BPMNShape bpmnElement="id_start28" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_001" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_002" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="290.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_003" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="440.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_004" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="590.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_005" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="740.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_006" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="890.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_007" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1040.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_008" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1190.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_009" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1340.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_010" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1490.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_011" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1640.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_012" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1790.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_013" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1940.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_014" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2090.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_015" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2240.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_016" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2390.0" y="60.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end29" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2540.0" y="85.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_id_016_end29">
                <ns2:waypoint x="2490.0" y="100.0"/>
                <ns2:waypoint x="2540.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_009_id_010">
                <ns2:waypoint x="1440.0" y="100.0"/>
                <ns2:waypoint x="1490.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start28_id_001">
                <ns2:waypoint x="90.0" y="100.0"/>
                <ns2:waypoint x="140.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_001_id_002">
                <ns2:waypoint x="240.0" y="100.0"/>
                <ns2:waypoint x="290.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_006_id_007">
                <ns2:waypoint x="990.0" y="100.0"/>
                <ns2:waypoint x="1040.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_012_id_013">
                <ns2:waypoint x="1890.0" y="100.0"/>
                <ns2:waypoint x="1940.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_013_id_014">
                <ns2:waypoint x="2040.0" y="100.0"/>
                <ns2:waypoint x="2090.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_007_id_008">
                <ns2:waypoint x="1140.0" y="100.0"/>
                <ns2:waypoint x="1190.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_014_id_015">
                <ns2:waypoint x="2190.0" y="100.0"/>
                <ns2:waypoint x="2240.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_008_id_009">
                <ns2:waypoint x="1290.0" y="100.0"/>
                <ns2:waypoint x="1340.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_002_id_003">
                <ns2:waypoint x="390.0" y="100.0"/>
                <ns2:waypoint x="440.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_003_id_004">
                <ns2:waypoint x="540.0" y="100.0"/>
                <ns2:waypoint x="590.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_004_id_005">
                <ns2:waypoint x="690.0" y="100.0"/>
                <ns2:waypoint x="740.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_010_id_011">
                <ns2:waypoint x="1590.0" y="100.0"/>
                <ns2:waypoint x="1640.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_005_id_006">
                <ns2:waypoint x="840.0" y="100.0"/>
                <ns2:waypoint x="890.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_011_id_012">
                <ns2:waypoint x="1740.0" y="100.0"/>
                <ns2:waypoint x="1790.0" y="100.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_015_id_016">
                <ns2:waypoint x="2340.0" y="100.0"/>
                <ns2:waypoint x="2390.0" y="100.0"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>
`, function(err){
    originalViewer.get('canvas').zoom('fit-viewport');
});