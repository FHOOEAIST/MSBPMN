
const containerBefore = document.getElementById('container');
const originalViewer = new BpmnJS({
    container: containerBefore
});

originalViewer.importXML(`<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="Preoperative_Diagnosis" id="Preoperative_Diagnosis">
        <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start70">
            <outgoing>sf_start70_dmn_operation_category</outgoing>
        </startEvent>
        <sequenceFlow sourceRef="id_start70" targetRef="id_dmn_operation_category" id="sf_start70_dmn_operation_category"/>
        <userTask name="Which intervention category?" id="id_dmn_operation_category">
            <incoming>sf_start70_dmn_operation_category</incoming>
            <outgoing>sf_dmn_operation_category_task_anamnese_status</outgoing>
            <dataOutputAssociation id="df_id_dmn_operation_category_data_operation_category">
                <targetRef>id_data_operation_category</targetRef>
            </dataOutputAssociation>
        </userTask>
        <sequenceFlow sourceRef="id_dmn_operation_category" targetRef="id_task_anamnese_status" id="sf_dmn_operation_category_task_anamnese_status"/>
        <dataObjectReference name="Intervention category" id="id_data_operation_category">
            <extensionElements>
                <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">intervention_category</ns5:type>
            </extensionElements>
        </dataObjectReference>
        <subProcess name="Perform anamnesis and medical state" id="id_task_anamnese_status">
            <incoming>sf_dmn_operation_category_task_anamnese_status</incoming>
            <outgoing>sf_task_anamnese_status_xor_anamnesis_unobtrusive</outgoing>
            <dataOutputAssociation id="df_id_task_anamnese_status_data_anamnesis_report">
                <targetRef>id_data_anamnesis_report</targetRef>
            </dataOutputAssociation>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start72">
                <outgoing>sf_start72_xor_anamnesis_feasible</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start72" targetRef="id_xor_anamnesis_feasible" id="sf_start72_xor_anamnesis_feasible"/>
            <exclusiveGateway name="Anamnesis feasible" id="id_xor_anamnesis_feasible">
                <incoming>sf_start72_xor_anamnesis_feasible</incoming>
                <outgoing>sf_xor_anamnesis_feasible_anamnesis_medical_check_group</outgoing>
                <outgoing>sf_xor_anamnesis_feasible_anamnesis_not_feasible</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_xor_anamnesis_feasible" targetRef="id_anamnesis_medical_check_group" name="Yes" id="sf_xor_anamnesis_feasible_anamnesis_medical_check_group">
                <conditionExpression id="sf_xor_anamnesis_feasible_anamnesis_medical_check_group_condition">Yes</conditionExpression>
            </sequenceFlow>
            <sequenceFlow sourceRef="id_xor_anamnesis_feasible" targetRef="id_anamnesis_not_feasible" name="No" id="sf_xor_anamnesis_feasible_anamnesis_not_feasible">
                <conditionExpression id="sf_xor_anamnesis_feasible_anamnesis_not_feasible_condition">No</conditionExpression>
            </sequenceFlow>
            <subProcess id="id_anamnesis_medical_check_group">
                <incoming>sf_xor_anamnesis_feasible_anamnesis_medical_check_group</incoming>
                <outgoing>sf_anamnesis_medical_check_group_join_of_xor_anamnesis_feasible</outgoing>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start74">
                    <outgoing>sf_start74_task_perform_anamnesis</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start74" targetRef="id_task_perform_anamnesis" id="sf_start74_task_perform_anamnesis"/>
                <userTask name="Perform anamnesis" id="id_task_perform_anamnesis">
                    <incoming>sf_start74_task_perform_anamnesis</incoming>
                    <outgoing>sf_task_perform_anamnesis_task_perform_medical_check</outgoing>
                    <dataOutputAssociation id="df_id_task_perform_anamnesis_data_ogari">
                        <targetRef>id_data_ogari</targetRef>
                    </dataOutputAssociation>
                </userTask>
                <sequenceFlow sourceRef="id_task_perform_anamnesis" targetRef="id_task_perform_medical_check" id="sf_task_perform_anamnesis_task_perform_medical_check"/>
                <dataObjectReference name="Filled Ögari questionaire" id="id_data_ogari">
                    <extensionElements>
                        <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">FilledOegariQuestionaire</ns5:type>
                    </extensionElements>
                </dataObjectReference>
                <userTask name="Perform medical check" id="id_task_perform_medical_check">
                    <incoming>sf_task_perform_anamnesis_task_perform_medical_check</incoming>
                    <outgoing>sf_task_perform_medical_check_end75</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_task_perform_medical_check" targetRef="id_end75" id="sf_task_perform_medical_check_end75"/>
                <endEvent name="end" id="id_end75">
                    <incoming>sf_task_perform_medical_check_end75</incoming>
                </endEvent>
            </subProcess>
            <sequenceFlow sourceRef="id_anamnesis_medical_check_group" targetRef="id_join_of_xor_anamnesis_feasible" id="sf_anamnesis_medical_check_group_join_of_xor_anamnesis_feasible"/>
            <userTask name="Medical check not feasible" id="id_anamnesis_not_feasible">
                <incoming>sf_xor_anamnesis_feasible_anamnesis_not_feasible</incoming>
                <outgoing>sf_anamnesis_not_feasible_join_of_xor_anamnesis_feasible</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_anamnesis_not_feasible" targetRef="id_join_of_xor_anamnesis_feasible" id="sf_anamnesis_not_feasible_join_of_xor_anamnesis_feasible"/>
            <exclusiveGateway name="join" id="id_join_of_xor_anamnesis_feasible">
                <incoming>sf_anamnesis_medical_check_group_join_of_xor_anamnesis_feasible</incoming>
                <incoming>sf_anamnesis_not_feasible_join_of_xor_anamnesis_feasible</incoming>
                <outgoing>sf_join_of_xor_anamnesis_feasible_end73</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_join_of_xor_anamnesis_feasible" targetRef="id_end73" id="sf_join_of_xor_anamnesis_feasible_end73"/>
            <endEvent name="end" id="id_end73">
                <incoming>sf_join_of_xor_anamnesis_feasible_end73</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_task_anamnese_status" targetRef="id_xor_anamnesis_unobtrusive" id="sf_task_anamnese_status_xor_anamnesis_unobtrusive"/>
        <dataObjectReference name="Anamnesis report" id="id_data_anamnesis_report">
            <extensionElements>
                <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">anamnesis_report</ns5:type>
            </extensionElements>
        </dataObjectReference>
        <exclusiveGateway name="Anamnesis unobtrusive?" id="id_xor_anamnesis_unobtrusive">
            <incoming>sf_task_anamnese_status_xor_anamnesis_unobtrusive</incoming>
            <outgoing>sf_xor_anamnesis_unobtrusive_join_of_xor_anamnesis_unobtrusive</outgoing>
            <outgoing>sf_xor_anamnesis_unobtrusive_task_survey_medical_reports</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_xor_anamnesis_unobtrusive" targetRef="id_join_of_xor_anamnesis_unobtrusive" name="else" id="sf_xor_anamnesis_unobtrusive_join_of_xor_anamnesis_unobtrusive">
            <conditionExpression id="sf_xor_anamnesis_unobtrusive_join_of_xor_anamnesis_unobtrusive_condition">else</conditionExpression>
        </sequenceFlow>
        <sequenceFlow sourceRef="id_xor_anamnesis_unobtrusive" targetRef="id_task_survey_medical_reports" name="No" id="sf_xor_anamnesis_unobtrusive_task_survey_medical_reports">
            <conditionExpression id="sf_xor_anamnesis_unobtrusive_task_survey_medical_reports_condition">No</conditionExpression>
        </sequenceFlow>
        <exclusiveGateway name="join" id="id_join_of_xor_anamnesis_unobtrusive">
            <incoming>sf_xor_anamnesis_unobtrusive_join_of_xor_anamnesis_unobtrusive</incoming>
            <incoming>sf_task_survey_medical_reports_join_of_xor_anamnesis_unobtrusive</incoming>
            <outgoing>sf_join_of_xor_anamnesis_unobtrusive_xor_therapy_necessary</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_join_of_xor_anamnesis_unobtrusive" targetRef="id_xor_therapy_necessary" id="sf_join_of_xor_anamnesis_unobtrusive_xor_therapy_necessary"/>
        <subProcess name="Survey medical reports" id="id_task_survey_medical_reports">
            <incoming>sf_xor_anamnesis_unobtrusive_task_survey_medical_reports</incoming>
            <outgoing>sf_task_survey_medical_reports_join_of_xor_anamnesis_unobtrusive</outgoing>
            <property name="prop_Anamnesis report" id="prop_task_survey_medical_reports_data_anamnesis_report"/>
            <property name="prop_Intervention category" id="prop_task_survey_medical_reports_data_operation_category"/>
            <dataInputAssociation id="df_id_task_survey_medical_reports_data_anamnesis_report">
                <sourceRef>id_data_anamnesis_report</sourceRef>
                <targetRef>prop_task_survey_medical_reports_data_anamnesis_report</targetRef>
            </dataInputAssociation>
            <dataInputAssociation id="df_id_task_survey_medical_reports_data_operation_category">
                <sourceRef>id_data_operation_category</sourceRef>
                <targetRef>prop_task_survey_medical_reports_data_operation_category</targetRef>
            </dataInputAssociation>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start76">
                <outgoing>sf_start76_par_further_diagnosis</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start76" targetRef="id_par_further_diagnosis" id="sf_start76_par_further_diagnosis"/>
            <parallelGateway name="Further diagnosis" id="id_par_further_diagnosis">
                <incoming>sf_start76_par_further_diagnosis</incoming>
                <outgoing>sf_par_further_diagnosis_task_labor</outgoing>
                <outgoing>sf_par_further_diagnosis_task_other_medical_reports</outgoing>
                <outgoing>sf_par_further_diagnosis_task_cardio</outgoing>
            </parallelGateway>
            <sequenceFlow sourceRef="id_par_further_diagnosis" targetRef="id_task_labor" id="sf_par_further_diagnosis_task_labor"/>
            <sequenceFlow sourceRef="id_par_further_diagnosis" targetRef="id_task_other_medical_reports" id="sf_par_further_diagnosis_task_other_medical_reports"/>
            <sequenceFlow sourceRef="id_par_further_diagnosis" targetRef="id_task_cardio" id="sf_par_further_diagnosis_task_cardio"/>
            <subProcess name="Perform laboratory tests" id="id_task_labor">
                <incoming>sf_par_further_diagnosis_task_labor</incoming>
                <outgoing>sf_task_labor_join_of_par_further_diagnosis</outgoing>
                <dataOutputAssociation id="df_id_task_labor_data_lab">
                    <targetRef>id_data_lab</targetRef>
                </dataOutputAssociation>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start78">
                    <outgoing>sf_start78_par_investigation_request_take_blood</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start78" targetRef="id_par_investigation_request_take_blood" id="sf_start78_par_investigation_request_take_blood"/>
                <parallelGateway id="id_par_investigation_request_take_blood">
                    <incoming>sf_start78_par_investigation_request_take_blood</incoming>
                    <outgoing>sf_par_investigation_request_take_blood_task_take_blood_sample</outgoing>
                    <outgoing>sf_par_investigation_request_take_blood_dmn_investigation_request_lab</outgoing>
                </parallelGateway>
                <sequenceFlow sourceRef="id_par_investigation_request_take_blood" targetRef="id_task_take_blood_sample" id="sf_par_investigation_request_take_blood_task_take_blood_sample"/>
                <sequenceFlow sourceRef="id_par_investigation_request_take_blood" targetRef="id_dmn_investigation_request_lab" id="sf_par_investigation_request_take_blood_dmn_investigation_request_lab"/>
                <userTask name="Take blood sample" id="id_task_take_blood_sample">
                    <incoming>sf_par_investigation_request_take_blood_task_take_blood_sample</incoming>
                    <outgoing>sf_task_take_blood_sample_join_of_par_investigation_request_take_blood</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_task_take_blood_sample" targetRef="id_join_of_par_investigation_request_take_blood" id="sf_task_take_blood_sample_join_of_par_investigation_request_take_blood"/>
                <userTask name="What is the investigation request?" id="id_dmn_investigation_request_lab">
                    <incoming>sf_par_investigation_request_take_blood_dmn_investigation_request_lab</incoming>
                    <outgoing>sf_dmn_investigation_request_lab_join_of_par_investigation_request_take_blood</outgoing>
                    <property name="prop_Anamnesis report" id="prop_dmn_investigation_request_lab_data_anamnesis_report_1"/>
                    <dataInputAssociation id="df_id_dmn_investigation_request_lab_data_anamnesis_report_1">
                        <sourceRef>id_data_anamnesis_report_1</sourceRef>
                        <targetRef>prop_dmn_investigation_request_lab_data_anamnesis_report_1</targetRef>
                    </dataInputAssociation>
                    <dataOutputAssociation id="df_id_dmn_investigation_request_lab_data_investigation_request_1">
                        <targetRef>id_data_investigation_request_1</targetRef>
                    </dataOutputAssociation>
                </userTask>
                <sequenceFlow sourceRef="id_dmn_investigation_request_lab" targetRef="id_join_of_par_investigation_request_take_blood" id="sf_dmn_investigation_request_lab_join_of_par_investigation_request_take_blood"/>
                <dataObjectReference name="Investigation request" id="id_data_investigation_request_1">
                    <extensionElements>
                        <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">investigation_request</ns5:type>
                    </extensionElements>
                </dataObjectReference>
                <dataObjectReference name="Anamnesis report" id="id_data_anamnesis_report_1">
                    <extensionElements>
                        <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">anamnesis_report</ns5:type>
                    </extensionElements>
                </dataObjectReference>
                <parallelGateway name="join" id="id_join_of_par_investigation_request_take_blood">
                    <incoming>sf_dmn_investigation_request_lab_join_of_par_investigation_request_take_blood</incoming>
                    <incoming>sf_task_take_blood_sample_join_of_par_investigation_request_take_blood</incoming>
                    <outgoing>sf_join_of_par_investigation_request_take_blood_task_request_laboratory_results</outgoing>
                </parallelGateway>
                <sequenceFlow sourceRef="id_join_of_par_investigation_request_take_blood" targetRef="id_task_request_laboratory_results" id="sf_join_of_par_investigation_request_take_blood_task_request_laboratory_results"/>
                <userTask name="Request laboratory results" id="id_task_request_laboratory_results">
                    <incoming>sf_join_of_par_investigation_request_take_blood_task_request_laboratory_results</incoming>
                    <outgoing>sf_task_request_laboratory_results_task_laboratory_results_transmitted</outgoing>
                    <property name="prop_Investigation request" id="prop_task_request_laboratory_results_data_investigation_request_1"/>
                    <dataInputAssociation id="df_id_task_request_laboratory_results_data_investigation_request_1">
                        <sourceRef>id_data_investigation_request_1</sourceRef>
                        <targetRef>prop_task_request_laboratory_results_data_investigation_request_1</targetRef>
                    </dataInputAssociation>
                </userTask>
                <sequenceFlow sourceRef="id_task_request_laboratory_results" targetRef="id_task_laboratory_results_transmitted" id="sf_task_request_laboratory_results_task_laboratory_results_transmitted"/>
                <intermediateCatchEvent name="Laboratory results transmitted" id="id_task_laboratory_results_transmitted">
                    <incoming>sf_task_request_laboratory_results_task_laboratory_results_transmitted</incoming>
                    <outgoing>sf_task_laboratory_results_transmitted_task_laboratory_results_transmitted_triggerAction</outgoing>
                    <conditionalEventDefinition id="event_task_laboratory_results_transmitted">
                        <condition xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="tFormalExpression" language="text/cql">Laboratory results transmitted</condition>
                    </conditionalEventDefinition>
                </intermediateCatchEvent>
                <sequenceFlow sourceRef="id_task_laboratory_results_transmitted" targetRef="id_task_laboratory_results_transmitted_triggerAction" id="sf_task_laboratory_results_transmitted_task_laboratory_results_transmitted_triggerAction"/>
                <dataObjectReference name="Laboratory results" id="id_data_laboratory_results">
                    <extensionElements>
                        <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">laboratory_results</ns5:type>
                    </extensionElements>
                </dataObjectReference>
                <userTask name="Laboratory results transmitted" id="id_task_laboratory_results_transmitted_triggerAction">
                    <incoming>sf_task_laboratory_results_transmitted_task_laboratory_results_transmitted_triggerAction</incoming>
                    <outgoing>sf_task_laboratory_results_transmitted_triggerAction_end79</outgoing>
                    <dataOutputAssociation id="df_id_task_laboratory_results_transmitted_triggerAction_data_laboratory_results">
                        <targetRef>id_data_laboratory_results</targetRef>
                    </dataOutputAssociation>
                </userTask>
                <sequenceFlow sourceRef="id_task_laboratory_results_transmitted_triggerAction" targetRef="id_end79" id="sf_task_laboratory_results_transmitted_triggerAction_end79"/>
                <endEvent name="end" id="id_end79">
                    <incoming>sf_task_laboratory_results_transmitted_triggerAction_end79</incoming>
                </endEvent>
            </subProcess>
            <sequenceFlow sourceRef="id_task_labor" targetRef="id_join_of_par_further_diagnosis" id="sf_task_labor_join_of_par_further_diagnosis"/>
            <dataObjectReference name="Laboratory results" id="id_data_lab">
                <extensionElements>
                    <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">laboratory_results</ns5:type>
                </extensionElements>
            </dataObjectReference>
            <userTask name="Other medical reports" id="id_task_other_medical_reports">
                <incoming>sf_par_further_diagnosis_task_other_medical_reports</incoming>
                <outgoing>sf_task_other_medical_reports_join_of_par_further_diagnosis</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_task_other_medical_reports" targetRef="id_join_of_par_further_diagnosis" id="sf_task_other_medical_reports_join_of_par_further_diagnosis"/>
            <subProcess name="Perform cardioplumonary test" id="id_task_cardio">
                <incoming>sf_par_further_diagnosis_task_cardio</incoming>
                <outgoing>sf_task_cardio_join_of_par_further_diagnosis</outgoing>
                <dataOutputAssociation id="df_id_task_cardio_data_cardio">
                    <targetRef>id_data_cardio</targetRef>
                </dataOutputAssociation>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start80">
                    <outgoing>sf_start80_dmn_investigation_request</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start80" targetRef="id_dmn_investigation_request" id="sf_start80_dmn_investigation_request"/>
                <userTask name="What is the Investigation request?" id="id_dmn_investigation_request">
                    <incoming>sf_start80_dmn_investigation_request</incoming>
                    <outgoing>sf_dmn_investigation_request_task_request_cardiological_results</outgoing>
                    <property name="prop_Anamnesis report" id="prop_dmn_investigation_request_data_anamnesis"/>
                    <dataInputAssociation id="df_id_dmn_investigation_request_data_anamnesis">
                        <sourceRef>id_data_anamnesis</sourceRef>
                        <targetRef>prop_dmn_investigation_request_data_anamnesis</targetRef>
                    </dataInputAssociation>
                    <dataOutputAssociation id="df_id_dmn_investigation_request_data_examination_request">
                        <targetRef>id_data_examination_request</targetRef>
                    </dataOutputAssociation>
                </userTask>
                <sequenceFlow sourceRef="id_dmn_investigation_request" targetRef="id_task_request_cardiological_results" id="sf_dmn_investigation_request_task_request_cardiological_results"/>
                <dataObjectReference name="Investigation request" id="id_data_examination_request">
                    <extensionElements>
                        <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">Investigation request</ns5:type>
                    </extensionElements>
                </dataObjectReference>
                <dataObjectReference name="Anamnesis report" id="id_data_anamnesis">
                    <extensionElements>
                        <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">anamnesis_report</ns5:type>
                    </extensionElements>
                </dataObjectReference>
                <userTask name="Request cardiological results" id="id_task_request_cardiological_results">
                    <incoming>sf_dmn_investigation_request_task_request_cardiological_results</incoming>
                    <outgoing>sf_task_request_cardiological_results_task_cardiological_results_transmitted</outgoing>
                    <property name="prop_Investigation request" id="prop_task_request_cardiological_results_data_examination_request"/>
                    <dataInputAssociation id="df_id_task_request_cardiological_results_data_examination_request">
                        <sourceRef>id_data_examination_request</sourceRef>
                        <targetRef>prop_task_request_cardiological_results_data_examination_request</targetRef>
                    </dataInputAssociation>
                </userTask>
                <sequenceFlow sourceRef="id_task_request_cardiological_results" targetRef="id_task_cardiological_results_transmitted" id="sf_task_request_cardiological_results_task_cardiological_results_transmitted"/>
                <intermediateCatchEvent name="Cardiological results transitted" id="id_task_cardiological_results_transmitted">
                    <incoming>sf_task_request_cardiological_results_task_cardiological_results_transmitted</incoming>
                    <outgoing>sf_task_cardiological_results_transmitted_task_cardiological_results_transmitted_triggerAction</outgoing>
                    <conditionalEventDefinition id="event_task_cardiological_results_transmitted">
                        <condition xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="tFormalExpression" language="text/cql">Cardiological results transitted</condition>
                    </conditionalEventDefinition>
                </intermediateCatchEvent>
                <sequenceFlow sourceRef="id_task_cardiological_results_transmitted" targetRef="id_task_cardiological_results_transmitted_triggerAction" id="sf_task_cardiological_results_transmitted_task_cardiological_results_transmitted_triggerAction"/>
                <dataObjectReference name="Cardiopulmonary results" id="id_data_cardiopulmonary_results">
                    <extensionElements>
                        <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">cardiopulmonary_results</ns5:type>
                    </extensionElements>
                </dataObjectReference>
                <userTask name="Cardiological results transitted" id="id_task_cardiological_results_transmitted_triggerAction">
                    <incoming>sf_task_cardiological_results_transmitted_task_cardiological_results_transmitted_triggerAction</incoming>
                    <outgoing>sf_task_cardiological_results_transmitted_triggerAction_end81</outgoing>
                    <dataOutputAssociation id="df_id_task_cardiological_results_transmitted_triggerAction_data_cardiopulmonary_results">
                        <targetRef>id_data_cardiopulmonary_results</targetRef>
                    </dataOutputAssociation>
                </userTask>
                <sequenceFlow sourceRef="id_task_cardiological_results_transmitted_triggerAction" targetRef="id_end81" id="sf_task_cardiological_results_transmitted_triggerAction_end81"/>
                <endEvent name="end" id="id_end81">
                    <incoming>sf_task_cardiological_results_transmitted_triggerAction_end81</incoming>
                </endEvent>
            </subProcess>
            <sequenceFlow sourceRef="id_task_cardio" targetRef="id_join_of_par_further_diagnosis" id="sf_task_cardio_join_of_par_further_diagnosis"/>
            <dataObjectReference name="Cardiopulmonary results" id="id_data_cardio">
                <extensionElements>
                    <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">cardio_pulmonary_results</ns5:type>
                </extensionElements>
            </dataObjectReference>
            <parallelGateway name="join" id="id_join_of_par_further_diagnosis">
                <incoming>sf_task_labor_join_of_par_further_diagnosis</incoming>
                <incoming>sf_task_cardio_join_of_par_further_diagnosis</incoming>
                <incoming>sf_task_other_medical_reports_join_of_par_further_diagnosis</incoming>
                <outgoing>sf_join_of_par_further_diagnosis_xor_interdisciplinary_optimisation</outgoing>
            </parallelGateway>
            <sequenceFlow sourceRef="id_join_of_par_further_diagnosis" targetRef="id_xor_interdisciplinary_optimisation" id="sf_join_of_par_further_diagnosis_xor_interdisciplinary_optimisation"/>
            <exclusiveGateway name="interdisciplinary optimisation needed?" id="id_xor_interdisciplinary_optimisation">
                <incoming>sf_join_of_par_further_diagnosis_xor_interdisciplinary_optimisation</incoming>
                <outgoing>sf_xor_interdisciplinary_optimisation_task_interdis_opt</outgoing>
                <outgoing>sf_xor_interdisciplinary_optimisation_join_of_xor_interdisciplinary_optimisation</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_xor_interdisciplinary_optimisation" targetRef="id_task_interdis_opt" name="Yes" id="sf_xor_interdisciplinary_optimisation_task_interdis_opt">
                <conditionExpression id="sf_xor_interdisciplinary_optimisation_task_interdis_opt_condition">Yes</conditionExpression>
            </sequenceFlow>
            <sequenceFlow sourceRef="id_xor_interdisciplinary_optimisation" targetRef="id_join_of_xor_interdisciplinary_optimisation" name="else" id="sf_xor_interdisciplinary_optimisation_join_of_xor_interdisciplinary_optimisation">
                <conditionExpression id="sf_xor_interdisciplinary_optimisation_join_of_xor_interdisciplinary_optimisation_condition">else</conditionExpression>
            </sequenceFlow>
            <userTask name="Perform interdisciplinary optimisation" id="id_task_interdis_opt">
                <incoming>sf_xor_interdisciplinary_optimisation_task_interdis_opt</incoming>
                <outgoing>sf_task_interdis_opt_join_of_xor_interdisciplinary_optimisation</outgoing>
                <property name="prop_Laboratory results" id="prop_task_interdis_opt_data_lab"/>
                <property name="prop_Cardiopulmonary results" id="prop_task_interdis_opt_data_cardio"/>
                <dataInputAssociation id="df_id_task_interdis_opt_data_lab">
                    <sourceRef>id_data_lab</sourceRef>
                    <targetRef>prop_task_interdis_opt_data_lab</targetRef>
                </dataInputAssociation>
                <dataInputAssociation id="df_id_task_interdis_opt_data_cardio">
                    <sourceRef>id_data_cardio</sourceRef>
                    <targetRef>prop_task_interdis_opt_data_cardio</targetRef>
                </dataInputAssociation>
            </userTask>
            <sequenceFlow sourceRef="id_task_interdis_opt" targetRef="id_join_of_xor_interdisciplinary_optimisation" id="sf_task_interdis_opt_join_of_xor_interdisciplinary_optimisation"/>
            <exclusiveGateway name="join" id="id_join_of_xor_interdisciplinary_optimisation">
                <incoming>sf_task_interdis_opt_join_of_xor_interdisciplinary_optimisation</incoming>
                <incoming>sf_xor_interdisciplinary_optimisation_join_of_xor_interdisciplinary_optimisation</incoming>
                <outgoing>sf_join_of_xor_interdisciplinary_optimisation_end77</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_join_of_xor_interdisciplinary_optimisation" targetRef="id_end77" id="sf_join_of_xor_interdisciplinary_optimisation_end77"/>
            <endEvent name="end" id="id_end77">
                <incoming>sf_join_of_xor_interdisciplinary_optimisation_end77</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_task_survey_medical_reports" targetRef="id_join_of_xor_anamnesis_unobtrusive" id="sf_task_survey_medical_reports_join_of_xor_anamnesis_unobtrusive"/>
        <exclusiveGateway id="id_xor_therapy_necessary">
            <incoming>sf_join_of_xor_anamnesis_unobtrusive_xor_therapy_necessary</incoming>
            <outgoing>sf_xor_therapy_necessary_task_therapy</outgoing>
            <outgoing>sf_xor_therapy_necessary_join_of_xor_therapy_necessary</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_xor_therapy_necessary" targetRef="id_task_therapy" name="therapy and medical care needed" id="sf_xor_therapy_necessary_task_therapy">
            <conditionExpression id="sf_xor_therapy_necessary_task_therapy_condition">therapy and medical care needed</conditionExpression>
        </sequenceFlow>
        <sequenceFlow sourceRef="id_xor_therapy_necessary" targetRef="id_join_of_xor_therapy_necessary" name="else" id="sf_xor_therapy_necessary_join_of_xor_therapy_necessary">
            <conditionExpression id="sf_xor_therapy_necessary_join_of_xor_therapy_necessary_condition">else</conditionExpression>
        </sequenceFlow>
        <userTask name="Initiate therapy and medical care" id="id_task_therapy">
            <incoming>sf_xor_therapy_necessary_task_therapy</incoming>
            <outgoing>sf_task_therapy_join_of_xor_therapy_necessary</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_task_therapy" targetRef="id_join_of_xor_therapy_necessary" id="sf_task_therapy_join_of_xor_therapy_necessary"/>
        <exclusiveGateway name="join" id="id_join_of_xor_therapy_necessary">
            <incoming>sf_xor_therapy_necessary_join_of_xor_therapy_necessary</incoming>
            <incoming>sf_task_therapy_join_of_xor_therapy_necessary</incoming>
            <outgoing>sf_join_of_xor_therapy_necessary_xor_surgery_feasible</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_join_of_xor_therapy_necessary" targetRef="id_xor_surgery_feasible" id="sf_join_of_xor_therapy_necessary_xor_surgery_feasible"/>
        <exclusiveGateway name="Surgery feasible?" id="id_xor_surgery_feasible">
            <incoming>sf_join_of_xor_therapy_necessary_xor_surgery_feasible</incoming>
            <outgoing>sf_xor_surgery_feasible_task_approve_surgery</outgoing>
            <outgoing>sf_xor_surgery_feasible_task_refuse_surgery</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_xor_surgery_feasible" targetRef="id_task_approve_surgery" name="Yes" id="sf_xor_surgery_feasible_task_approve_surgery">
            <conditionExpression id="sf_xor_surgery_feasible_task_approve_surgery_condition">Yes</conditionExpression>
        </sequenceFlow>
        <sequenceFlow sourceRef="id_xor_surgery_feasible" targetRef="id_task_refuse_surgery" name="No" id="sf_xor_surgery_feasible_task_refuse_surgery">
            <conditionExpression id="sf_xor_surgery_feasible_task_refuse_surgery_condition">No</conditionExpression>
        </sequenceFlow>
        <subProcess name="Approve surgery" id="id_task_approve_surgery">
            <incoming>sf_xor_surgery_feasible_task_approve_surgery</incoming>
            <outgoing>sf_task_approve_surgery_join_of_xor_surgery_feasible</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start82">
                <outgoing>sf_start82_par_airway_control</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start82" targetRef="id_par_airway_control" id="sf_start82_par_airway_control"/>
            <parallelGateway name="Airway control" id="id_par_airway_control">
                <incoming>sf_start82_par_airway_control</incoming>
                <outgoing>sf_par_airway_control_task_survey_mellampati_score</outgoing>
                <outgoing>sf_par_airway_control_task_survey_tyromental_distance</outgoing>
                <outgoing>sf_par_airway_control_task_perform_lower_jaw_protrusion_test</outgoing>
            </parallelGateway>
            <sequenceFlow sourceRef="id_par_airway_control" targetRef="id_task_survey_mellampati_score" id="sf_par_airway_control_task_survey_mellampati_score"/>
            <sequenceFlow sourceRef="id_par_airway_control" targetRef="id_task_survey_tyromental_distance" id="sf_par_airway_control_task_survey_tyromental_distance"/>
            <sequenceFlow sourceRef="id_par_airway_control" targetRef="id_task_perform_lower_jaw_protrusion_test" id="sf_par_airway_control_task_perform_lower_jaw_protrusion_test"/>
            <userTask name="Survey Mellampati-Score" id="id_task_survey_mellampati_score">
                <incoming>sf_par_airway_control_task_survey_mellampati_score</incoming>
                <outgoing>sf_task_survey_mellampati_score_join_of_par_airway_control</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_task_survey_mellampati_score" targetRef="id_join_of_par_airway_control" id="sf_task_survey_mellampati_score_join_of_par_airway_control"/>
            <userTask name="Survey tyromental distance" id="id_task_survey_tyromental_distance">
                <incoming>sf_par_airway_control_task_survey_tyromental_distance</incoming>
                <outgoing>sf_task_survey_tyromental_distance_join_of_par_airway_control</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_task_survey_tyromental_distance" targetRef="id_join_of_par_airway_control" id="sf_task_survey_tyromental_distance_join_of_par_airway_control"/>
            <userTask name="Perform lower jaw protrusion test" id="id_task_perform_lower_jaw_protrusion_test">
                <incoming>sf_par_airway_control_task_perform_lower_jaw_protrusion_test</incoming>
                <outgoing>sf_task_perform_lower_jaw_protrusion_test_join_of_par_airway_control</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_task_perform_lower_jaw_protrusion_test" targetRef="id_join_of_par_airway_control" id="sf_task_perform_lower_jaw_protrusion_test_join_of_par_airway_control"/>
            <parallelGateway name="join" id="id_join_of_par_airway_control">
                <incoming>sf_task_survey_tyromental_distance_join_of_par_airway_control</incoming>
                <incoming>sf_task_perform_lower_jaw_protrusion_test_join_of_par_airway_control</incoming>
                <incoming>sf_task_survey_mellampati_score_join_of_par_airway_control</incoming>
                <outgoing>sf_join_of_par_airway_control_task_survey_tooth_status</outgoing>
            </parallelGateway>
            <sequenceFlow sourceRef="id_join_of_par_airway_control" targetRef="id_task_survey_tooth_status" id="sf_join_of_par_airway_control_task_survey_tooth_status"/>
            <userTask name="Survey tooth status" id="id_task_survey_tooth_status">
                <incoming>sf_join_of_par_airway_control_task_survey_tooth_status</incoming>
                <outgoing>sf_task_survey_tooth_status_task_inspection_of_the_corresponding_anatomic_region</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_task_survey_tooth_status" targetRef="id_task_inspection_of_the_corresponding_anatomic_region" id="sf_task_survey_tooth_status_task_inspection_of_the_corresponding_anatomic_region"/>
            <userTask name="Inspection of the corresponding anatomic region" id="id_task_inspection_of_the_corresponding_anatomic_region">
                <incoming>sf_task_survey_tooth_status_task_inspection_of_the_corresponding_anatomic_region</incoming>
                <outgoing>sf_task_inspection_of_the_corresponding_anatomic_region_end83</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_task_inspection_of_the_corresponding_anatomic_region" targetRef="id_end83" id="sf_task_inspection_of_the_corresponding_anatomic_region_end83"/>
            <endEvent name="end" id="id_end83">
                <incoming>sf_task_inspection_of_the_corresponding_anatomic_region_end83</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_task_approve_surgery" targetRef="id_join_of_xor_surgery_feasible" id="sf_task_approve_surgery_join_of_xor_surgery_feasible"/>
        <userTask name="Refuse surgery" id="id_task_refuse_surgery">
            <incoming>sf_xor_surgery_feasible_task_refuse_surgery</incoming>
            <outgoing>sf_task_refuse_surgery_join_of_xor_surgery_feasible</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_task_refuse_surgery" targetRef="id_join_of_xor_surgery_feasible" id="sf_task_refuse_surgery_join_of_xor_surgery_feasible"/>
        <exclusiveGateway name="join" id="id_join_of_xor_surgery_feasible">
            <incoming>sf_task_approve_surgery_join_of_xor_surgery_feasible</incoming>
            <incoming>sf_task_refuse_surgery_join_of_xor_surgery_feasible</incoming>
            <outgoing>sf_join_of_xor_surgery_feasible_end71</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_join_of_xor_surgery_feasible" targetRef="id_end71" id="sf_join_of_xor_surgery_feasible_end71"/>
        <endEvent name="end" id="id_end71">
            <incoming>sf_join_of_xor_surgery_feasible_end71</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="Preoperative_Diagnosis">
            <ns4:BPMNShape bpmnElement="id_start82" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3920.0" y="675.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_par_airway_control" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4000.0" y="670.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_survey_mellampati_score" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4090.0" y="830.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_survey_tyromental_distance" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4090.0" y="650.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_perform_lower_jaw_protrusion_test" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4090.0" y="470.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_par_airway_control" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4240.0" y="670.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_survey_tooth_status" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4330.0" y="650.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_inspection_of_the_corresponding_anatomic_region" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4480.0" y="650.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end83" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4630.0" y="675.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_par_airway_control_task_perform_lower_jaw_protrusion_test">
                <ns2:waypoint x="4040.0" y="690.0"/>
                <ns2:waypoint x="4065.0" y="690.0"/>
                <ns2:waypoint x="4065.0" y="510.0"/>
                <ns2:waypoint x="4090.0" y="510.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_par_airway_control_task_survey_tooth_status">
                <ns2:waypoint x="4280.0" y="690.0"/>
                <ns2:waypoint x="4330.0" y="690.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_survey_mellampati_score_join_of_par_airway_control">
                <ns2:waypoint x="4190.0" y="870.0"/>
                <ns2:waypoint x="4215.0" y="870.0"/>
                <ns2:waypoint x="4215.0" y="690.0"/>
                <ns2:waypoint x="4240.0" y="690.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_par_airway_control_task_survey_tyromental_distance">
                <ns2:waypoint x="4040.0" y="690.0"/>
                <ns2:waypoint x="4090.0" y="690.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_survey_tooth_status_task_inspection_of_the_corresponding_anatomic_region">
                <ns2:waypoint x="4430.0" y="690.0"/>
                <ns2:waypoint x="4480.0" y="690.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start82_par_airway_control">
                <ns2:waypoint x="3950.0" y="690.0"/>
                <ns2:waypoint x="4000.0" y="690.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_perform_lower_jaw_protrusion_test_join_of_par_airway_control">
                <ns2:waypoint x="4190.0" y="510.0"/>
                <ns2:waypoint x="4215.0" y="510.0"/>
                <ns2:waypoint x="4215.0" y="690.0"/>
                <ns2:waypoint x="4240.0" y="690.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_inspection_of_the_corresponding_anatomic_region_end83">
                <ns2:waypoint x="4580.0" y="690.0"/>
                <ns2:waypoint x="4630.0" y="690.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_par_airway_control_task_survey_mellampati_score">
                <ns2:waypoint x="4040.0" y="690.0"/>
                <ns2:waypoint x="4065.0" y="690.0"/>
                <ns2:waypoint x="4065.0" y="870.0"/>
                <ns2:waypoint x="4090.0" y="870.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_survey_tyromental_distance_join_of_par_airway_control">
                <ns2:waypoint x="4190.0" y="690.0"/>
                <ns2:waypoint x="4240.0" y="690.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_anamnesis" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1880.0" y="1090.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_start80" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1920.0" y="1215.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_dmn_investigation_request" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2000.0" y="1190.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_request_cardiological_results" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2150.0" y="1190.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_cardiological_results_transmitted" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2300.0" y="1215.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_cardiological_results_transmitted_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2380.0" y="1190.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end81" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2530.0" y="1215.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_task_cardiological_results_transmitted_task_cardiological_results_transmitted_triggerAction">
                <ns2:waypoint x="2330.0" y="1230.0"/>
                <ns2:waypoint x="2380.0" y="1230.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_request_cardiological_results_task_cardiological_results_transmitted">
                <ns2:waypoint x="2250.0" y="1230.0"/>
                <ns2:waypoint x="2300.0" y="1230.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_dmn_investigation_request_task_request_cardiological_results">
                <ns2:waypoint x="2100.0" y="1230.0"/>
                <ns2:waypoint x="2150.0" y="1230.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_cardiological_results_transmitted_triggerAction_end81">
                <ns2:waypoint x="2480.0" y="1230.0"/>
                <ns2:waypoint x="2530.0" y="1230.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start80_dmn_investigation_request">
                <ns2:waypoint x="1950.0" y="1230.0"/>
                <ns2:waypoint x="2000.0" y="1230.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_examination_request" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2032.0" y="1320.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_data_cardiopulmonary_results" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2412.0" y="1400.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="df_id_dmn_investigation_request_data_examination_request">
                <ns2:waypoint x="2050.0" y="1270.0"/>
                <ns2:waypoint x="2050.0" y="1320.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_task_cardiological_results_transmitted_triggerAction_data_cardiopulmonary_results">
                <ns2:waypoint x="2430.0" y="1270.0"/>
                <ns2:waypoint x="2430.0" y="1400.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_dmn_investigation_request_data_anamnesis">
                <ns2:waypoint x="1916.0" y="1128.6184210526317"/>
                <ns2:waypoint x="2000.0" y="1192.171052631579"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_task_request_cardiological_results_data_examination_request">
                <ns2:waypoint x="2068.0" y="1331.2"/>
                <ns2:waypoint x="2150.0" y="1268.3333333333333"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_anamnesis_report_1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1790.0" y="200.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_start78" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1830.0" y="415.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_par_investigation_request_take_blood" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1910.0" y="410.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_take_blood_sample" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2000.0" y="300.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_dmn_investigation_request_lab" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2000.0" y="480.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_par_investigation_request_take_blood" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2150.0" y="410.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_request_laboratory_results" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2240.0" y="390.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_laboratory_results_transmitted" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2390.0" y="415.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_laboratory_results_transmitted_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2470.0" y="390.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end79" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2620.0" y="415.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_task_laboratory_results_transmitted_triggerAction_end79">
                <ns2:waypoint x="2570.0" y="430.0"/>
                <ns2:waypoint x="2620.0" y="430.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_par_investigation_request_take_blood_task_take_blood_sample">
                <ns2:waypoint x="1950.0" y="430.0"/>
                <ns2:waypoint x="1975.0" y="430.0"/>
                <ns2:waypoint x="1975.0" y="340.0"/>
                <ns2:waypoint x="2000.0" y="340.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_take_blood_sample_join_of_par_investigation_request_take_blood">
                <ns2:waypoint x="2100.0" y="340.0"/>
                <ns2:waypoint x="2125.0" y="340.0"/>
                <ns2:waypoint x="2125.0" y="430.0"/>
                <ns2:waypoint x="2150.0" y="430.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_dmn_investigation_request_lab_join_of_par_investigation_request_take_blood">
                <ns2:waypoint x="2100.0" y="520.0"/>
                <ns2:waypoint x="2125.0" y="520.0"/>
                <ns2:waypoint x="2125.0" y="430.0"/>
                <ns2:waypoint x="2150.0" y="430.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_request_laboratory_results_task_laboratory_results_transmitted">
                <ns2:waypoint x="2340.0" y="430.0"/>
                <ns2:waypoint x="2390.0" y="430.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_laboratory_results_transmitted_task_laboratory_results_transmitted_triggerAction">
                <ns2:waypoint x="2420.0" y="430.0"/>
                <ns2:waypoint x="2470.0" y="430.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start78_par_investigation_request_take_blood">
                <ns2:waypoint x="1860.0" y="430.0"/>
                <ns2:waypoint x="1910.0" y="430.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_par_investigation_request_take_blood_dmn_investigation_request_lab">
                <ns2:waypoint x="1950.0" y="430.0"/>
                <ns2:waypoint x="1975.0" y="430.0"/>
                <ns2:waypoint x="1975.0" y="520.0"/>
                <ns2:waypoint x="2000.0" y="520.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_par_investigation_request_take_blood_task_request_laboratory_results">
                <ns2:waypoint x="2190.0" y="430.0"/>
                <ns2:waypoint x="2240.0" y="430.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_investigation_request_1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2032.0" y="610.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_data_laboratory_results" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2502.0" y="690.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="df_id_task_laboratory_results_transmitted_triggerAction_data_laboratory_results">
                <ns2:waypoint x="2520.0" y="470.0"/>
                <ns2:waypoint x="2520.0" y="690.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_dmn_investigation_request_lab_data_investigation_request_1">
                <ns2:waypoint x="2050.0" y="560.0"/>
                <ns2:waypoint x="2050.0" y="610.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_dmn_investigation_request_lab_data_anamnesis_report_1">
                <ns2:waypoint x="1826.0" y="246.94214876033058"/>
                <ns2:waypoint x="2017.1864406779662" y="480.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_task_request_laboratory_results_data_investigation_request_1">
                <ns2:waypoint x="2068.0" y="619.625"/>
                <ns2:waypoint x="2243.170731707317" y="470.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start76" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1570.0" y="883.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_par_further_diagnosis" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1650.0" y="878.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_labor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1740.0" y="150.0" width="970.0" height="610.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_other_medical_reports" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2175.0" y="860.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_cardio" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1830.0" y="1040.0" width="790.0" height="430.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_par_further_diagnosis" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2760.0" y="866.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_xor_interdisciplinary_optimisation" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2850.0" y="828.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_interdis_opt" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2940.0" y="720.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_xor_interdisciplinary_optimisation" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3090.0" y="809.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end77" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3180.0" y="814.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_par_further_diagnosis_task_labor">
                <ns2:waypoint x="1690.0" y="898.0"/>
                <ns2:waypoint x="1715.0" y="898.0"/>
                <ns2:waypoint x="1715.0" y="455.00000000000006"/>
                <ns2:waypoint x="1740.0" y="455.00000000000006"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_labor_join_of_par_further_diagnosis">
                <ns2:waypoint x="2710.0" y="455.0"/>
                <ns2:waypoint x="2735.0" y="455.0"/>
                <ns2:waypoint x="2735.0" y="886.0"/>
                <ns2:waypoint x="2760.0" y="886.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_interdisciplinary_optimisation_task_interdis_opt">
                <ns2:waypoint x="2890.0" y="848.0"/>
                <ns2:waypoint x="2915.0" y="848.0"/>
                <ns2:waypoint x="2915.0" y="760.0"/>
                <ns2:waypoint x="2940.0" y="760.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="2825.0" y="804.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start76_par_further_diagnosis">
                <ns2:waypoint x="1600.0" y="898.0"/>
                <ns2:waypoint x="1650.0" y="898.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_cardio_join_of_par_further_diagnosis">
                <ns2:waypoint x="2620.0" y="1255.0"/>
                <ns2:waypoint x="2690.0" y="1255.0"/>
                <ns2:waypoint x="2690.0" y="886.0"/>
                <ns2:waypoint x="2760.0" y="886.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_par_further_diagnosis_task_other_medical_reports">
                <ns2:waypoint x="1690.0" y="898.0"/>
                <ns2:waypoint x="1932.5" y="898.0"/>
                <ns2:waypoint x="1932.5" y="900.0"/>
                <ns2:waypoint x="2175.0" y="900.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_xor_interdisciplinary_optimisation_end77">
                <ns2:waypoint x="3130.0" y="829.0"/>
                <ns2:waypoint x="3180.0" y="829.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_interdisciplinary_optimisation_join_of_xor_interdisciplinary_optimisation">
                <ns2:waypoint x="2890.0" y="848.0"/>
                <ns2:waypoint x="2990.0" y="848.0"/>
                <ns2:waypoint x="2990.0" y="829.0"/>
                <ns2:waypoint x="3090.0" y="829.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="2900.0" y="838.5" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_par_further_diagnosis_xor_interdisciplinary_optimisation">
                <ns2:waypoint x="2800.0" y="886.0"/>
                <ns2:waypoint x="2825.0" y="886.0"/>
                <ns2:waypoint x="2825.0" y="848.0"/>
                <ns2:waypoint x="2850.0" y="848.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_interdis_opt_join_of_xor_interdisciplinary_optimisation">
                <ns2:waypoint x="3040.0" y="760.0"/>
                <ns2:waypoint x="3065.0" y="760.0"/>
                <ns2:waypoint x="3065.0" y="829.0"/>
                <ns2:waypoint x="3090.0" y="829.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_other_medical_reports_join_of_par_further_diagnosis">
                <ns2:waypoint x="2275.0" y="900.0"/>
                <ns2:waypoint x="2517.5" y="900.0"/>
                <ns2:waypoint x="2517.5" y="886.0"/>
                <ns2:waypoint x="2760.0" y="886.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_par_further_diagnosis_task_cardio">
                <ns2:waypoint x="1690.0" y="898.0"/>
                <ns2:waypoint x="1760.0" y="898.0"/>
                <ns2:waypoint x="1760.0" y="1255.0"/>
                <ns2:waypoint x="1830.0" y="1255.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_cardio" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2207.0" y="1500.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_data_lab" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2258.0" y="1500.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="df_id_task_cardio_data_cardio">
                <ns2:waypoint x="2225.0" y="1470.0"/>
                <ns2:waypoint x="2225.0" y="1500.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_task_labor_data_lab">
                <ns2:waypoint x="2239.53738317757" y="760.0"/>
                <ns2:waypoint x="2274.808411214953" y="1500.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_task_interdis_opt_data_lab">
                <ns2:waypoint x="2294.0" y="1505.7142857142858"/>
                <ns2:waypoint x="2952.6666666666665" y="800.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_task_interdis_opt_data_cardio">
                <ns2:waypoint x="2243.0" y="1507.0"/>
                <ns2:waypoint x="2950.0" y="800.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start74" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="640.0" y="478.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_perform_anamnesis" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="720.0" y="453.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_perform_medical_check" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="870.0" y="453.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end75" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1020.0" y="478.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_start74_task_perform_anamnesis">
                <ns2:waypoint x="670.0" y="493.0"/>
                <ns2:waypoint x="720.0" y="493.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_perform_medical_check_end75">
                <ns2:waypoint x="970.0" y="493.0"/>
                <ns2:waypoint x="1020.0" y="493.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_perform_anamnesis_task_perform_medical_check">
                <ns2:waypoint x="820.0" y="493.0"/>
                <ns2:waypoint x="870.0" y="493.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_ogari" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="752.0" y="563.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="df_id_task_perform_anamnesis_data_ogari">
                <ns2:waypoint x="770.0" y="533.0"/>
                <ns2:waypoint x="770.0" y="563.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start72" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="380.0" y="346.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_xor_anamnesis_feasible" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="460.0" y="341.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_anamnesis_medical_check_group" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="550.0" y="363.0" width="560.0" height="270.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_anamnesis_not_feasible" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="780.0" y="183.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_xor_anamnesis_feasible" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1160.0" y="341.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end73" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1250.0" y="346.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_join_of_xor_anamnesis_feasible_end73">
                <ns2:waypoint x="1200.0" y="361.0"/>
                <ns2:waypoint x="1250.0" y="361.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_anamnesis_feasible_anamnesis_not_feasible">
                <ns2:waypoint x="500.0" y="361.0"/>
                <ns2:waypoint x="640.0" y="361.0"/>
                <ns2:waypoint x="640.0" y="223.0"/>
                <ns2:waypoint x="780.0" y="223.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="550.0" y="292.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_anamnesis_feasible_anamnesis_medical_check_group">
                <ns2:waypoint x="500.0" y="361.0"/>
                <ns2:waypoint x="525.0" y="361.0"/>
                <ns2:waypoint x="525.0" y="498.00000000000006"/>
                <ns2:waypoint x="550.0" y="498.00000000000006"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="435.0" y="429.5" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start72_xor_anamnesis_feasible">
                <ns2:waypoint x="410.0" y="361.0"/>
                <ns2:waypoint x="460.0" y="361.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_anamnesis_medical_check_group_join_of_xor_anamnesis_feasible">
                <ns2:waypoint x="1110.0" y="498.0"/>
                <ns2:waypoint x="1135.0" y="498.0"/>
                <ns2:waypoint x="1135.0" y="361.0"/>
                <ns2:waypoint x="1160.0" y="361.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_anamnesis_not_feasible_join_of_xor_anamnesis_feasible">
                <ns2:waypoint x="880.0" y="223.0"/>
                <ns2:waypoint x="1020.0" y="223.0"/>
                <ns2:waypoint x="1020.0" y="361.0"/>
                <ns2:waypoint x="1160.0" y="361.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start70" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="380.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_dmn_operation_category" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="355.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_anamnese_status" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="290.0" y="93.0" width="1050.0" height="600.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_xor_anamnesis_unobtrusive" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1390.0" y="369.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_xor_anamnesis_unobtrusive" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3320.0" y="466.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_survey_medical_reports" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1480.0" y="60.0" width="1790.0" height="1510.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_xor_therapy_necessary" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3410.0" y="666.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_therapy" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3500.0" y="675.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_xor_therapy_necessary" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3650.0" y="791.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_xor_surgery_feasible" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3740.0" y="845.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_approve_surgery" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3830.0" y="380.0" width="890.0" height="590.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_task_refuse_surgery" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4225.0" y="1070.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_xor_surgery_feasible" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4770.0" y="870.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end71" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4860.0" y="875.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_join_of_xor_anamnesis_unobtrusive_xor_therapy_necessary">
                <ns2:waypoint x="3360.0" y="486.0"/>
                <ns2:waypoint x="3385.0" y="486.0"/>
                <ns2:waypoint x="3385.0" y="686.0"/>
                <ns2:waypoint x="3410.0" y="686.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_dmn_operation_category_task_anamnese_status">
                <ns2:waypoint x="240.0" y="395.0"/>
                <ns2:waypoint x="265.0" y="395.0"/>
                <ns2:waypoint x="265.0" y="393.00000000000006"/>
                <ns2:waypoint x="290.0" y="393.00000000000006"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_surgery_feasible_task_approve_surgery">
                <ns2:waypoint x="3780.0" y="865.0"/>
                <ns2:waypoint x="3805.0" y="865.0"/>
                <ns2:waypoint x="3805.0" y="675.0"/>
                <ns2:waypoint x="3830.0" y="675.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="3715.0" y="770.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_approve_surgery_join_of_xor_surgery_feasible">
                <ns2:waypoint x="4720.0" y="675.0"/>
                <ns2:waypoint x="4745.0" y="675.0"/>
                <ns2:waypoint x="4745.0" y="890.0"/>
                <ns2:waypoint x="4770.0" y="890.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_refuse_surgery_join_of_xor_surgery_feasible">
                <ns2:waypoint x="4325.0" y="1110.0"/>
                <ns2:waypoint x="4547.5" y="1110.0"/>
                <ns2:waypoint x="4547.5" y="890.0"/>
                <ns2:waypoint x="4770.0" y="890.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_anamnese_status_xor_anamnesis_unobtrusive">
                <ns2:waypoint x="1340.0" y="393.0"/>
                <ns2:waypoint x="1365.0" y="393.0"/>
                <ns2:waypoint x="1365.0" y="389.0"/>
                <ns2:waypoint x="1390.0" y="389.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_anamnesis_unobtrusive_join_of_xor_anamnesis_unobtrusive">
                <ns2:waypoint x="1430.0" y="389.0"/>
                <ns2:waypoint x="2375.0" y="389.0"/>
                <ns2:waypoint x="2375.0" y="486.0"/>
                <ns2:waypoint x="3320.0" y="486.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="2285.0" y="437.5" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_survey_medical_reports_join_of_xor_anamnesis_unobtrusive">
                <ns2:waypoint x="3270.0" y="815.0"/>
                <ns2:waypoint x="3295.0" y="815.0"/>
                <ns2:waypoint x="3295.0" y="486.0"/>
                <ns2:waypoint x="3320.0" y="486.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_therapy_necessary_task_therapy">
                <ns2:waypoint x="3450.0" y="686.0"/>
                <ns2:waypoint x="3475.0" y="686.0"/>
                <ns2:waypoint x="3475.0" y="715.0"/>
                <ns2:waypoint x="3500.0" y="715.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="3385.0" y="700.5" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_anamnesis_unobtrusive_task_survey_medical_reports">
                <ns2:waypoint x="1430.0" y="389.0"/>
                <ns2:waypoint x="1455.0" y="389.0"/>
                <ns2:waypoint x="1455.0" y="815.0000000000001"/>
                <ns2:waypoint x="1480.0" y="815.0000000000001"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="1365.0" y="602.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_surgery_feasible_task_refuse_surgery">
                <ns2:waypoint x="3780.0" y="865.0"/>
                <ns2:waypoint x="4002.5" y="865.0"/>
                <ns2:waypoint x="4002.5" y="1110.0"/>
                <ns2:waypoint x="4225.0" y="1110.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="3912.5" y="987.5" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_xor_surgery_feasible_end71">
                <ns2:waypoint x="4810.0" y="890.0"/>
                <ns2:waypoint x="4860.0" y="890.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_therapy_necessary_join_of_xor_therapy_necessary">
                <ns2:waypoint x="3450.0" y="686.0"/>
                <ns2:waypoint x="3550.0" y="686.0"/>
                <ns2:waypoint x="3550.0" y="811.0"/>
                <ns2:waypoint x="3650.0" y="811.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="3460.0" y="748.5" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_xor_therapy_necessary_xor_surgery_feasible">
                <ns2:waypoint x="3690.0" y="811.0"/>
                <ns2:waypoint x="3715.0" y="811.0"/>
                <ns2:waypoint x="3715.0" y="865.0"/>
                <ns2:waypoint x="3740.0" y="865.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start70_dmn_operation_category">
                <ns2:waypoint x="90.0" y="395.0"/>
                <ns2:waypoint x="140.0" y="395.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_task_therapy_join_of_xor_therapy_necessary">
                <ns2:waypoint x="3600.0" y="715.0"/>
                <ns2:waypoint x="3625.0" y="715.0"/>
                <ns2:waypoint x="3625.0" y="811.0"/>
                <ns2:waypoint x="3650.0" y="811.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_operation_category" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="172.0" y="1600.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_data_anamnesis_report" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="797.0" y="1680.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="df_id_task_anamnese_status_data_anamnesis_report">
                <ns2:waypoint x="815.0" y="693.0"/>
                <ns2:waypoint x="815.0" y="1680.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_dmn_operation_category_data_operation_category">
                <ns2:waypoint x="190.0" y="435.0"/>
                <ns2:waypoint x="190.0" y="1600.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_task_survey_medical_reports_data_anamnesis_report">
                <ns2:waypoint x="833.0" y="1694.7307692307693"/>
                <ns2:waypoint x="1480.0" y="1325.6089743589741"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_task_survey_medical_reports_data_operation_category">
                <ns2:waypoint x="208.0" y="1618.3272311212816"/>
                <ns2:waypoint x="1480.0" y="1146.7848970251719"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>
`, function(err){
    originalViewer.get('canvas').zoom('fit-viewport');
});