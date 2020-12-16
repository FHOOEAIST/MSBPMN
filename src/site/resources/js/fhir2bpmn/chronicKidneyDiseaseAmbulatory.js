
const containerBefore = document.getElementById('container');
const originalViewer = new BpmnJS({
    container: containerBefore
});

originalViewer.importXML(`<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="ChronicKidneyDiseaseAmbulatory" id="ChronicKidneyDiseaseAmbulatory">
        <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start36">
            <outgoing>sf_start36_id_34387235</outgoing>
        </startEvent>
        <sequenceFlow sourceRef="id_start36" targetRef="id_id_34387235" id="sf_start36_id_34387235"/>
        <subProcess name="Assessment Scales" id="id_id_34387235">
            <incoming>sf_start36_id_34387235</incoming>
            <outgoing>sf_id_34387235_id_34387242</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start38">
                <outgoing>sf_start38_id_34387238</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start38" targetRef="id_id_34387238" id="sf_start38_id_34387238"/>
            <userTask name="GFR Equations and Classification according to GFR Category" id="id_id_34387238">
                <incoming>sf_start38_id_34387238</incoming>
                <outgoing>sf_id_34387238_end39</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_id_34387238" targetRef="id_end39" id="sf_id_34387238_end39"/>
            <endEvent name="end" id="id_end39">
                <incoming>sf_id_34387238_end39</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_id_34387235" targetRef="id_id_34387242" id="sf_id_34387235_id_34387242"/>
        <subProcess name="General Care" id="id_id_34387242">
            <incoming>sf_id_34387235_id_34387242</incoming>
            <outgoing>sf_id_34387242_id_34387251</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start40">
                <outgoing>sf_start40_id_34387243</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start40" targetRef="id_id_34387243" id="sf_start40_id_34387243"/>
            <subProcess name="Patient Education" id="id_id_34387243">
                <incoming>sf_start40_id_34387243</incoming>
                <outgoing>sf_id_34387243_end41</outgoing>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start42">
                    <outgoing>sf_start42_cc_cpg_activity_edu_hypertension</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start42" targetRef="id_cc_cpg_activity_edu_hypertension" id="sf_start42_cc_cpg_activity_edu_hypertension"/>
                <userTask name="Patient education: Managing Your High Blood Pressure (Hypertension)" id="id_cc_cpg_activity_edu_hypertension">
                    <incoming>sf_start42_cc_cpg_activity_edu_hypertension</incoming>
                    <outgoing>sf_cc_cpg_activity_edu_hypertension_cc_cpg_activity_edu_renal_diet</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_cc_cpg_activity_edu_hypertension" targetRef="id_cc_cpg_activity_edu_renal_diet" id="sf_cc_cpg_activity_edu_hypertension_cc_cpg_activity_edu_renal_diet"/>
                <userTask name="Patient education: Renal diet" id="id_cc_cpg_activity_edu_renal_diet">
                    <incoming>sf_cc_cpg_activity_edu_hypertension_cc_cpg_activity_edu_renal_diet</incoming>
                    <outgoing>sf_cc_cpg_activity_edu_renal_diet_end43</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_cc_cpg_activity_edu_renal_diet" targetRef="id_end43" id="sf_cc_cpg_activity_edu_renal_diet_end43"/>
                <endEvent name="end" id="id_end43">
                    <incoming>sf_cc_cpg_activity_edu_renal_diet_end43</incoming>
                </endEvent>
            </subProcess>
            <sequenceFlow sourceRef="id_id_34387243" targetRef="id_end41" id="sf_id_34387243_end41"/>
            <endEvent name="end" id="id_end41">
                <incoming>sf_id_34387243_end41</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_id_34387242" targetRef="id_id_34387251" id="sf_id_34387242_id_34387251"/>
        <subProcess name="Medications" id="id_id_34387251">
            <incoming>sf_id_34387242_id_34387251</incoming>
            <outgoing>sf_id_34387251_id_34387287</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start44">
                <outgoing>sf_start44_id_34387256</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start44" targetRef="id_id_34387256" id="sf_start44_id_34387256"/>
            <subProcess name="Antihypertensives" id="id_id_34387256">
                <incoming>sf_start44_id_34387256</incoming>
                <outgoing>sf_id_34387256_id_34387261</outgoing>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start46">
                    <outgoing>sf_start46_id_34387257</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start46" targetRef="id_id_34387257" id="sf_start46_id_34387257"/>
                <userTask name="Enalapril Oral Tablet; 10 mg 1 time a day (dispense 30 tablet(s); 3 refills)" id="id_id_34387257">
                    <incoming>sf_start46_id_34387257</incoming>
                    <outgoing>sf_id_34387257_id_34387258</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_id_34387257" targetRef="id_id_34387258" id="sf_id_34387257_id_34387258"/>
                <userTask name="Lisinopril 20 MG Oral Tablet; 1 tablet(s) 1 time a day (dispense 30 tablet(s); 3 refills)" id="id_id_34387258">
                    <incoming>sf_id_34387257_id_34387258</incoming>
                    <outgoing>sf_id_34387258_end47</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_id_34387258" targetRef="id_end47" id="sf_id_34387258_end47"/>
                <endEvent name="end" id="id_end47">
                    <incoming>sf_id_34387258_end47</incoming>
                </endEvent>
            </subProcess>
            <sequenceFlow sourceRef="id_id_34387256" targetRef="id_id_34387261" id="sf_id_34387256_id_34387261"/>
            <subProcess name="Antilipemics" id="id_id_34387261">
                <incoming>sf_id_34387256_id_34387261</incoming>
                <outgoing>sf_id_34387261_id_34387265</outgoing>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start48">
                    <outgoing>sf_start48_id_34387264</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start48" targetRef="id_id_34387264" id="sf_start48_id_34387264"/>
                <userTask name="Simvastatin Oral Tablet; 5 mg 1 time a day (dispense 30 tablet(s); 3 refills)" id="id_id_34387264">
                    <incoming>sf_start48_id_34387264</incoming>
                    <outgoing>sf_id_34387264_end49</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_id_34387264" targetRef="id_end49" id="sf_id_34387264_end49"/>
                <endEvent name="end" id="id_end49">
                    <incoming>sf_id_34387264_end49</incoming>
                </endEvent>
            </subProcess>
            <sequenceFlow sourceRef="id_id_34387261" targetRef="id_id_34387265" id="sf_id_34387261_id_34387265"/>
            <subProcess name="Diuretics" id="id_id_34387265">
                <incoming>sf_id_34387261_id_34387265</incoming>
                <outgoing>sf_id_34387265_end45</outgoing>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start50">
                    <outgoing>sf_start50_id_34387266</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start50" targetRef="id_id_34387266" id="sf_start50_id_34387266"/>
                <userTask name="Furosemide Oral Tablet; 20 mg 1 time a day (dispense 30 tablet(s); 3 refills)" id="id_id_34387266">
                    <incoming>sf_start50_id_34387266</incoming>
                    <outgoing>sf_id_34387266_end51</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_id_34387266" targetRef="id_end51" id="sf_id_34387266_end51"/>
                <endEvent name="end" id="id_end51">
                    <incoming>sf_id_34387266_end51</incoming>
                </endEvent>
            </subProcess>
            <sequenceFlow sourceRef="id_id_34387265" targetRef="id_end45" id="sf_id_34387265_end45"/>
            <endEvent name="end" id="id_end45">
                <incoming>sf_id_34387265_end45</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_id_34387251" targetRef="id_id_34387287" id="sf_id_34387251_id_34387287"/>
        <subProcess name="Laboratory" id="id_id_34387287">
            <incoming>sf_id_34387251_id_34387287</incoming>
            <outgoing>sf_id_34387287_id_34387217</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start52">
                <outgoing>sf_start52_id_34387288</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start52" targetRef="id_id_34387288" id="sf_start52_id_34387288"/>
            <subProcess name="Chemistry" id="id_id_34387288">
                <incoming>sf_start52_id_34387288</incoming>
                <outgoing>sf_id_34387288_id_34387206</outgoing>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start54">
                    <outgoing>sf_start54_cc_cpg_activity_lab_metabolic</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start54" targetRef="id_cc_cpg_activity_lab_metabolic" id="sf_start54_cc_cpg_activity_lab_metabolic"/>
                <userTask name="Lab: Comprehensive Metabolic Panel, Once" id="id_cc_cpg_activity_lab_metabolic">
                    <incoming>sf_start54_cc_cpg_activity_lab_metabolic</incoming>
                    <outgoing>sf_cc_cpg_activity_lab_metabolic_end55</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_cc_cpg_activity_lab_metabolic" targetRef="id_end55" id="sf_cc_cpg_activity_lab_metabolic_end55"/>
                <endEvent name="end" id="id_end55">
                    <incoming>sf_cc_cpg_activity_lab_metabolic_end55</incoming>
                </endEvent>
            </subProcess>
            <sequenceFlow sourceRef="id_id_34387288" targetRef="id_id_34387206" id="sf_id_34387288_id_34387206"/>
            <subProcess name="Urine" id="id_id_34387206">
                <incoming>sf_id_34387288_id_34387206</incoming>
                <outgoing>sf_id_34387206_end53</outgoing>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start56">
                    <outgoing>sf_start56_cc_cpg_activity_lab_creatinine</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start56" targetRef="id_cc_cpg_activity_lab_creatinine" id="sf_start56_cc_cpg_activity_lab_creatinine"/>
                <userTask name="Lab: Creatinine, Urine Random, Once" id="id_cc_cpg_activity_lab_creatinine">
                    <incoming>sf_start56_cc_cpg_activity_lab_creatinine</incoming>
                    <outgoing>sf_cc_cpg_activity_lab_creatinine_end57</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_cc_cpg_activity_lab_creatinine" targetRef="id_end57" id="sf_cc_cpg_activity_lab_creatinine_end57"/>
                <endEvent name="end" id="id_end57">
                    <incoming>sf_cc_cpg_activity_lab_creatinine_end57</incoming>
                </endEvent>
            </subProcess>
            <sequenceFlow sourceRef="id_id_34387206" targetRef="id_end53" id="sf_id_34387206_end53"/>
            <endEvent name="end" id="id_end53">
                <incoming>sf_id_34387206_end53</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_id_34387287" targetRef="id_id_34387217" id="sf_id_34387287_id_34387217"/>
        <subProcess name="Radiology" id="id_id_34387217">
            <incoming>sf_id_34387287_id_34387217</incoming>
            <outgoing>sf_id_34387217_cc_cpg_activity_referral_nephrology</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start58">
                <outgoing>sf_start58_cc-cpg-activity-ultrasound-renal</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start58" targetRef="id_cc-cpg-activity-ultrasound-renal" id="sf_start58_cc-cpg-activity-ultrasound-renal"/>
            <subProcess name="Ultrasound" id="id_cc-cpg-activity-ultrasound-renal">
                <incoming>sf_start58_cc-cpg-activity-ultrasound-renal</incoming>
                <outgoing>sf_cc-cpg-activity-ultrasound-renal_end59</outgoing>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start60">
                    <outgoing>sf_start60_id_34387219</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start60" targetRef="id_id_34387219" id="sf_start60_id_34387219"/>
                <userTask name="Ultrasound, Renal; History: [add diagnosis, symptom(s)]; Question: [add reason for exam]" id="id_id_34387219">
                    <incoming>sf_start60_id_34387219</incoming>
                    <outgoing>sf_id_34387219_end61</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_id_34387219" targetRef="id_end61" id="sf_id_34387219_end61"/>
                <endEvent name="end" id="id_end61">
                    <incoming>sf_id_34387219_end61</incoming>
                </endEvent>
            </subProcess>
            <sequenceFlow sourceRef="id_cc-cpg-activity-ultrasound-renal" targetRef="id_end59" id="sf_cc-cpg-activity-ultrasound-renal_end59"/>
            <endEvent name="end" id="id_end59">
                <incoming>sf_cc-cpg-activity-ultrasound-renal_end59</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_id_34387217" targetRef="id_cc_cpg_activity_referral_nephrology" id="sf_id_34387217_cc_cpg_activity_referral_nephrology"/>
        <subProcess name="Referrals" id="id_cc_cpg_activity_referral_nephrology">
            <incoming>sf_id_34387217_cc_cpg_activity_referral_nephrology</incoming>
            <outgoing>sf_cc_cpg_activity_referral_nephrology_end37</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start62">
                <outgoing>sf_start62_id_34387224</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start62" targetRef="id_id_34387224" id="sf_start62_id_34387224"/>
            <userTask name="Referral: Nephrology; History: [add diagnosis, symptom(s)]; Question: [add reason for referral]" id="id_id_34387224">
                <incoming>sf_start62_id_34387224</incoming>
                <outgoing>sf_id_34387224_cc_cpg_activity_referral_dietition</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_id_34387224" targetRef="id_cc_cpg_activity_referral_dietition" id="sf_id_34387224_cc_cpg_activity_referral_dietition"/>
            <userTask name="Referral: Dietitian; History: chronic kidney disease; Question: [add reason for referral]" id="id_cc_cpg_activity_referral_dietition">
                <incoming>sf_id_34387224_cc_cpg_activity_referral_dietition</incoming>
                <outgoing>sf_cc_cpg_activity_referral_dietition_end63</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_cc_cpg_activity_referral_dietition" targetRef="id_end63" id="sf_cc_cpg_activity_referral_dietition_end63"/>
            <endEvent name="end" id="id_end63">
                <incoming>sf_cc_cpg_activity_referral_dietition_end63</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_cc_cpg_activity_referral_nephrology" targetRef="id_end37" id="sf_cc_cpg_activity_referral_nephrology_end37"/>
        <endEvent name="end" id="id_end37">
            <incoming>sf_cc_cpg_activity_referral_nephrology_end37</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="ChronicKidneyDiseaseAmbulatory">
            <ns4:BPMNShape bpmnElement="id_start62" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5450.0" y="250.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387224" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5530.0" y="225.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_cc_cpg_activity_referral_dietition" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5680.0" y="225.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end63" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5830.0" y="250.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_start62_id_34387224">
                <ns2:waypoint x="5480.0" y="265.0"/>
                <ns2:waypoint x="5530.0" y="265.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_cc_cpg_activity_referral_dietition_end63">
                <ns2:waypoint x="5780.0" y="265.0"/>
                <ns2:waypoint x="5830.0" y="265.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_34387224_cc_cpg_activity_referral_dietition">
                <ns2:waypoint x="5630.0" y="265.0"/>
                <ns2:waypoint x="5680.0" y="265.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start42" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="860.0" y="265.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_cc_cpg_activity_edu_hypertension" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="940.0" y="240.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_cc_cpg_activity_edu_renal_diet" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1090.0" y="240.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end43" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1240.0" y="265.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_cc_cpg_activity_edu_renal_diet_end43">
                <ns2:waypoint x="1190.0" y="280.0"/>
                <ns2:waypoint x="1240.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_cc_cpg_activity_edu_hypertension_cc_cpg_activity_edu_renal_diet">
                <ns2:waypoint x="1040.0" y="280.0"/>
                <ns2:waypoint x="1090.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start42_cc_cpg_activity_edu_hypertension">
                <ns2:waypoint x="890.0" y="280.0"/>
                <ns2:waypoint x="940.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start40" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="690.0" y="250.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387243" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="770.0" y="150.0" width="560.0" height="230.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end41" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1380.0" y="250.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_id_34387243_end41">
                <ns2:waypoint x="1330.0" y="265.0"/>
                <ns2:waypoint x="1380.0" y="265.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start40_id_34387243">
                <ns2:waypoint x="720.0" y="265.0"/>
                <ns2:waypoint x="745.0" y="265.0"/>
                <ns2:waypoint x="745.0" y="265.00000000000006"/>
                <ns2:waypoint x="770.0" y="265.00000000000006"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start48" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2390.0" y="265.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387264" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2470.0" y="240.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end49" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2620.0" y="265.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_id_34387264_end49">
                <ns2:waypoint x="2570.0" y="280.0"/>
                <ns2:waypoint x="2620.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start48_id_34387264">
                <ns2:waypoint x="2420.0" y="280.0"/>
                <ns2:waypoint x="2470.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start46" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1780.0" y="265.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387257" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1860.0" y="240.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387258" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2010.0" y="240.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end47" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2160.0" y="265.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_id_34387257_id_34387258">
                <ns2:waypoint x="1960.0" y="280.0"/>
                <ns2:waypoint x="2010.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_34387258_end47">
                <ns2:waypoint x="2110.0" y="280.0"/>
                <ns2:waypoint x="2160.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start46_id_34387257">
                <ns2:waypoint x="1810.0" y="280.0"/>
                <ns2:waypoint x="1860.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start50" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2850.0" y="265.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387266" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2930.0" y="240.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end51" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3080.0" y="265.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_start50_id_34387266">
                <ns2:waypoint x="2880.0" y="280.0"/>
                <ns2:waypoint x="2930.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_34387266_end51">
                <ns2:waypoint x="3030.0" y="280.0"/>
                <ns2:waypoint x="3080.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start44" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1610.0" y="250.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387256" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1690.0" y="150.0" width="560.0" height="230.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387261" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2300.0" y="150.0" width="410.0" height="230.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387265" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2760.0" y="150.0" width="410.0" height="230.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end45" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3220.0" y="250.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_id_34387265_end45">
                <ns2:waypoint x="3170.0" y="265.0"/>
                <ns2:waypoint x="3220.0" y="265.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start44_id_34387256">
                <ns2:waypoint x="1640.0" y="265.0"/>
                <ns2:waypoint x="1665.0" y="265.0"/>
                <ns2:waypoint x="1665.0" y="265.00000000000006"/>
                <ns2:waypoint x="1690.0" y="265.00000000000006"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_34387261_id_34387265">
                <ns2:waypoint x="2710.0" y="265.0"/>
                <ns2:waypoint x="2760.0" y="265.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_34387256_id_34387261">
                <ns2:waypoint x="2250.0" y="265.0"/>
                <ns2:waypoint x="2300.0" y="265.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start38" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="230.0" y="250.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387238" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="310.0" y="225.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end39" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="460.0" y="250.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_id_34387238_end39">
                <ns2:waypoint x="410.0" y="265.0"/>
                <ns2:waypoint x="460.0" y="265.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start38_id_34387238">
                <ns2:waypoint x="260.0" y="265.0"/>
                <ns2:waypoint x="310.0" y="265.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start54" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3620.0" y="265.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_cc_cpg_activity_lab_metabolic" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3700.0" y="240.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end55" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3850.0" y="265.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_start54_cc_cpg_activity_lab_metabolic">
                <ns2:waypoint x="3650.0" y="280.0"/>
                <ns2:waypoint x="3700.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_cc_cpg_activity_lab_metabolic_end55">
                <ns2:waypoint x="3800.0" y="280.0"/>
                <ns2:waypoint x="3850.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start56" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4080.0" y="265.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_cc_cpg_activity_lab_creatinine" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4160.0" y="240.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end57" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4310.0" y="265.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_start56_cc_cpg_activity_lab_creatinine">
                <ns2:waypoint x="4110.0" y="280.0"/>
                <ns2:waypoint x="4160.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_cc_cpg_activity_lab_creatinine_end57">
                <ns2:waypoint x="4260.0" y="280.0"/>
                <ns2:waypoint x="4310.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start52" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3450.0" y="250.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387288" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3530.0" y="150.0" width="410.0" height="230.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387206" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3990.0" y="150.0" width="410.0" height="230.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end53" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4450.0" y="250.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_id_34387288_id_34387206">
                <ns2:waypoint x="3940.0" y="265.0"/>
                <ns2:waypoint x="3990.0" y="265.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start52_id_34387288">
                <ns2:waypoint x="3480.0" y="265.0"/>
                <ns2:waypoint x="3530.0" y="265.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_34387206_end53">
                <ns2:waypoint x="4400.0" y="265.0"/>
                <ns2:waypoint x="4450.0" y="265.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start60" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4850.0" y="265.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387219" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4930.0" y="240.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end61" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5080.0" y="265.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_start60_id_34387219">
                <ns2:waypoint x="4880.0" y="280.0"/>
                <ns2:waypoint x="4930.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_34387219_end61">
                <ns2:waypoint x="5030.0" y="280.0"/>
                <ns2:waypoint x="5080.0" y="280.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start58" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4680.0" y="250.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_cc-cpg-activity-ultrasound-renal" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4760.0" y="150.0" width="410.0" height="230.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end59" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5220.0" y="250.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_cc-cpg-activity-ultrasound-renal_end59">
                <ns2:waypoint x="5170.0" y="265.0"/>
                <ns2:waypoint x="5220.0" y="265.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start58_cc-cpg-activity-ultrasound-renal">
                <ns2:waypoint x="4710.0" y="265.0"/>
                <ns2:waypoint x="4760.0" y="265.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start36" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="235.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387235" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="135.0" width="410.0" height="230.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387242" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="600.0" y="60.0" width="870.0" height="380.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387251" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1520.0" y="60.0" width="1790.0" height="380.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387287" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3360.0" y="60.0" width="1180.0" height="380.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_id_34387217" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4590.0" y="60.0" width="720.0" height="380.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_cc_cpg_activity_referral_nephrology" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5360.0" y="135.0" width="560.0" height="230.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end37" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5970.0" y="235.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_cc_cpg_activity_referral_nephrology_end37">
                <ns2:waypoint x="5920.0" y="250.0"/>
                <ns2:waypoint x="5970.0" y="250.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_34387217_cc_cpg_activity_referral_nephrology">
                <ns2:waypoint x="5310.0" y="250.0"/>
                <ns2:waypoint x="5335.0" y="250.0"/>
                <ns2:waypoint x="5335.0" y="250.00000000000003"/>
                <ns2:waypoint x="5360.0" y="250.00000000000003"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_34387251_id_34387287">
                <ns2:waypoint x="3310.0" y="250.0"/>
                <ns2:waypoint x="3335.0" y="250.0"/>
                <ns2:waypoint x="3335.0" y="250.00000000000009"/>
                <ns2:waypoint x="3360.0" y="250.00000000000009"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_34387287_id_34387217">
                <ns2:waypoint x="4540.0" y="250.0"/>
                <ns2:waypoint x="4565.0" y="250.0"/>
                <ns2:waypoint x="4565.0" y="250.00000000000003"/>
                <ns2:waypoint x="4590.0" y="250.00000000000006"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start36_id_34387235">
                <ns2:waypoint x="90.0" y="250.0"/>
                <ns2:waypoint x="115.0" y="250.0"/>
                <ns2:waypoint x="115.0" y="250.00000000000003"/>
                <ns2:waypoint x="140.0" y="250.00000000000003"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_34387235_id_34387242">
                <ns2:waypoint x="550.0" y="250.0"/>
                <ns2:waypoint x="575.0" y="250.0"/>
                <ns2:waypoint x="575.0" y="250.00000000000003"/>
                <ns2:waypoint x="600.0" y="250.00000000000006"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_id_34387242_id_34387251">
                <ns2:waypoint x="1470.0" y="250.0"/>
                <ns2:waypoint x="1495.0" y="250.0"/>
                <ns2:waypoint x="1495.0" y="250.0000000000001"/>
                <ns2:waypoint x="1520.0" y="250.0000000000001"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>
`, function(err){
    originalViewer.get('canvas').zoom('fit-viewport');
});