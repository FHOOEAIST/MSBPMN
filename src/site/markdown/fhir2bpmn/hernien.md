
# Hernien

Transformation for the Klinische SOP - K40 Hernia inguinalis

## Discussions

 * The transformation does not yet support triggers for actions, that are nested inside a XOR. This could be bypassed by nesting the action into a subprocess where it would be processed correct again.
 * Message flow to external "Participants" is not yet supported.

## Graph

<script type="text/javascript" src="https://unpkg.com/bpmn-js@7.2.1/dist/bpmn-navigated-viewer.production.min.js"></script>
Usage: Mouse Click + Move: Scroll around. Ctrl + Mouse Wheel: Zoom.

<div id="container" style="width: 100%; height: 500px; border: 1px solid lightgray; overflow:auto;"></div>

<script type="text/javascript" src="../js/fhir2bpmn/hernien.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.0.0/styles/darcula.min.css"></link>
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.0.0/highlight.min.js"></script>


## HL7 FHIR

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--
  ~ Copyright (c) 2020 the original author or authors.
  ~ DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
  ~
  ~ This Source Code Form is subject to the terms of the Mozilla Public
  ~ License, v. 2.0. If a copy of the MPL was not distributed with this
  ~ file, You can obtain one at https://mozilla.org/MPL/2.0/.
  -->

<PlanDefinition xmlns="http://hl7.org/fhir">
    <id value="hernien"/>
    <version value="1.0"/>
    <name value="hernien"/>
    <title value="klinische SOP - K40 Hernia inguinalis"/>
    <type>
        <coding>
            <system value="http://terminology.hl7.org/CodeSystem/plan-definition-type"/>
            <code value="clinical-protocol"/>
            <display value="Clinical Protocol"/>
        </coding>
    </type>
    <status value="draft"/>

    <action>
        <id value="chirurgische_anamnese_durchfuehren"/>
        <title value="Chirurgische Anamnese durchfuehren"/>
        <relatedAction>
            <actionId value="koerperliche_untersuchung_durchfuehren"/>
            <relationship value="before-start"/>
        </relatedAction>
    </action>
    <action>
        <id value="koerperliche_untersuchung_durchfuehren"/>
        <title value="Koerperliche Untersuchung durchfuehren"/>
        <relatedAction>
            <actionId value="diagnose_klar_xor"/>
            <relationship value="before-start"/>
        </relatedAction>
    </action>
    <action>
        <id value="diagnose_klar_xor"/>
        <title value="Diagnose klar?"/>
        <groupingBehavior value="logical-group"/>
        <selectionBehavior value="at-most-one"/>
        <relatedAction>
            <actionId value="hernie_diagnostiziert_xor"/>
            <relationship value="before-start"/>
        </relatedAction>
        <action>
            <id value="diagnose_klar_nein_grouping"/>
            <condition>
                <kind value="applicability"/>
                <expression>
                    <language value="text/cql"/>
                    <expression value="Nein"/>
                </expression>
            </condition>
            <relatedAction>
                <actionId value="diagnose_klar_xor"/>
                <relationship value="before-end"/>
            </relatedAction>
            <action>
                <id value="ultraschall_durchfuehren"/>
                <title value="Ultraschall durchfuehren"/>
                <relatedAction>
                    <actionId value="ultraschall_konklusiv_xor"/>
                    <relationship value="before-start"/>
                </relatedAction>
            </action>
            <action>
                <id value="ultraschall_konklusiv_xor"/>
                <title value="Ultraschall konklusiv?"/>
                <groupingBehavior value="logical-group"/>
                <selectionBehavior value="at-most-one"/>
                <action>
                    <id value="ct_abdomen_oder_mri_durchfuehren"/>
                    <title value="CT Abdomen oder MRI durchfuehren"/>
                    <condition>
                        <kind value="applicability"/>
                        <expression>
                            <language value="text/cql"/>
                            <expression value="Nein"/>
                        </expression>
                    </condition>
                    <relatedAction>
                        <actionId value="ultraschall_konklusiv_xor"/>
                        <relationship value="before-end"/>
                    </relatedAction>
                </action>
            </action>
        </action>
    </action>

    <action>
        <id value="hernie_diagnostiziert_xor"/>
        <title value="Hernie diagnostiziert?"/>
        <groupingBehavior value="logical-group"/>
        <selectionBehavior value="exactly-one"/>
        <action>
            <id value="andere_untersuchungen_durchfuehren"/>
            <title value="Andere Untersuchungen durchfuehren"/>
            <relatedAction>
                <actionId value="hernie_diagnostiziert_xor"/>
                <relationship value="before-end"/>
            </relatedAction>
            <condition>
                <kind value="applicability"/>
                <expression>
                    <language value="text/cql"/>
                    <expression value="Nein"/>
                </expression>
            </condition>
        </action>
        <action>
            <id value="hernie_diagnostiziert_ja_grouping"/>
            <relatedAction>
                <actionId value="hernie_diagnostiziert_xor"/>
                <relationship value="before-end"/>
            </relatedAction>
            <condition>
                <kind value="applicability"/>
                <expression>
                    <language value="text/cql"/>
                    <expression value="Ja"/>
                </expression>
            </condition>
            <action>
                <id value="einwilligung_des_patienten_einholen"/>
                <title value="Einwilligung des Patienten einholen"/>
                <output>
                    <id value="data_patienteneinwilligung"/>
                    <name value="Patienteneinwilligung"/>
                    <dataRequirement>
                        <type value="Patienteneinwilligung"/>
                    </dataRequirement>
                </output>
                <relatedAction>
                    <actionId value="patient_zu_op_eingewillig_xor"/>
                    <relationship value="before-start"/>
                </relatedAction>
            </action>
            <action>
                <id value="patient_zu_op_eingewillig_xor"/>
                <title value="Patient zu OP eingewilligt?"/>
                <groupingBehavior value="logical-group"/>
                <selectionBehavior value="exactly-one"/>
                <action>
                    <id value="patient_zu_op_eingewilligt_yes_grouping"/>
                    <relatedAction>
                        <actionId value="patient_zu_op_eingewillig_xor"/>
                        <relationship value="before-end"/>
                    </relatedAction>
                    <action>
                        <id value="operationsfreigabe_einholen"/>
                        <title value="Operationsfreigabe einholen"/>
                        <relatedAction>
                            <actionId value="operationsfreigabe"/>
                            <relationship value="before-start"/>
                        </relatedAction>
                    </action>
                    <action>
                        <id value="operationsfreigabe"/>
                        <title value="Operationsfreigabe"/>
                        <trigger>
                            <type value="named-event"/>
                            <name value="operationsfreigabe"/>
                            <condition>
                                <language value="text/cql"/>
                                <expression value="Operationsfreigabe"/>
                            </condition>
                        </trigger>
                        <input>
                            <id value="data_operationsfreigabe" />
                            <name value="Operationsfreigabe"/>
                            <dataRequirement>
                                <type value="Operationsfreigabe"/>
                            </dataRequirement>
                        </input>
                        <relatedAction>
                            <actionId value="operation_freigeben_xor"/>
                            <relationship value="before-start"/>
                        </relatedAction>
                    </action>
                    <action>
                        <id value="operation_freigeben_xor"/>
                        <title value="Operation freigeben?"/>
                        <groupingBehavior value="logical-group"/>
                        <selectionBehavior value="exactly-one"/>
                        <action>
                            <id value="keine_operation_durchfuehren_2"/>
                            <title value="Keine Operation durchfuehren"/>
                            <relatedAction>
                                <actionId value="operation_freigeben_xor"/>
                                <relationship value="before-end"/>
                            </relatedAction>
                            <condition>
                                <kind value="applicability"/>
                                <expression>
                                    <language value="text/cql"/>
                                    <expression value="Nein"/>
                                </expression>
                            </condition>
                        </action>
                        <action>
                            <id value="operation_freigeben_ja_grouping"/>
                            <relatedAction>
                                <actionId value="operation_freigeben_xor"/>
                                <relationship value="before-end"/>
                            </relatedAction>
                            <condition>
                                <kind value="applicability"/>
                                <expression>
                                    <language value="text/cql"/>
                                    <expression value="Ja"/>
                                </expression>
                            </condition>
                            <action>
                                <id value="kann_die_operation_tk_durchgefuehrt_werden"/>
                                <title value="Kann die Operation TK durchgefuehrt werden?"/>
                                <relatedAction>
                                    <actionId value="hernienoperation_durchfuehren"/>
                                    <relationship value="before-start"/>
                                </relatedAction>
                            </action>
                            <action>
                                <id value="hernienoperation_durchfuehren"/>
                                <title value="Hernienoperation durchfuehren"/>
                                <relatedAction>
                                    <actionId value="operation_tagesklinisch_xor"/>
                                    <relationship value="before-start"/>
                                </relatedAction>
                            </action>
                            <action>
                                <id value="operation_tagesklinisch_xor"/>
                                <title value="Operation tagesklinisch?"/>
                                <groupingBehavior value="logical-group"/>
                                <selectionBehavior value="exactly-one"/>
                                <relatedAction>
                                    <actionId value="postoperative_wundinspektion_durchfuehren"/>
                                    <relationship value="before-start"/>
                                </relatedAction>
                                <action>
                                    <id value="vier_h_nach_op"/>
                                    <title value="4h nach OP"/>
                                    <condition>
                                        <kind value="applicability"/>
                                        <expression>
                                            <language value="text/cql"/>
                                            <expression value="Ja"/>
                                        </expression>
                                    </condition>
                                    <relatedAction>
                                        <actionId value="operation_tagesklinisch_xor"/>
                                        <relationship value="before-end"/>
                                    </relatedAction>
                                    <trigger>
                                        <type value="periodic"/>
                                        <name value="vier_h_nach_op"/>
                                        <timingTiming>
                                            <repeat>
                                                <frequency value="4"/>
                                                <periodUnit value="h"/>
                                                <count value="1"/>
                                            </repeat>
                                        </timingTiming>
                                    </trigger>
                                </action>
                                <action>
                                    <id value="erster_tag_nach_op"/>
                                    <title value="am 1. post OP Tag"/>
                                    <condition>
                                        <kind value="applicability"/>
                                        <expression>
                                            <language value="text/cql"/>
                                            <expression value="Nein"/>
                                        </expression>
                                    </condition>
                                    <relatedAction>
                                        <actionId value="operation_tagesklinisch_xor"/>
                                        <relationship value="before-end"/>
                                    </relatedAction>
                                    <trigger>
                                        <type value="periodic"/>
                                        <name value="ein_tag_nach_op"/>
                                        <timingTiming>
                                            <repeat>
                                                <frequency value="1"/>
                                                <periodUnit value="d"/>
                                                <count value="1"/>
                                            </repeat>
                                        </timingTiming>
                                    </trigger>
                                </action>
                            </action>
                            <action>
                                <id value="postoperative_wundinspektion_durchfuehren"/>
                                <title value="postoperative Wundinspektion durchfuehren"/>
                                <relatedAction>
                                    <actionId value="patient_entlassen"/>
                                    <relationship value="before-start"/>
                                </relatedAction>
                            </action>
                            <action>
                                <id value="patient_entlassen"/>
                                <title value="Patient entlassen"/>
                                <output>
                                    <id value="data_entlassungspapiere"/>
                                    <name value="Entlassungspapiere"/>
                                    <dataRequirement>
                                        <type value="Entlassungspapiere"/>
                                    </dataRequirement>
                                </output>
                            </action>
                        </action>
                    </action>
                </action>
                <action>
                    <id value="keine_operation_durchfuehren"/>
                    <title value="Keine Operation durchfuehren"/>
                    <relatedAction>
                        <actionId value="patient_zu_op_eingewillig_xor"/>
                        <relationship value="before-end"/>
                    </relatedAction>
                </action>
            </action>
        </action>
    </action>

</PlanDefinition>
```

## BPMN

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:ns2="http://www.omg.org/spec/DD/20100524/DI" xmlns:ns3="http://www.omg.org/spec/DD/20100524/DC" xmlns:ns4="http://www.omg.org/spec/BPMN/20100524/DI" targetNamespace="http://aist.fh-hagenberg.at/msbpmn">
    <process isExecutable="false" name="hernien" id="hernien">
        <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start108">
            <outgoing>sf_start108_chirurgische_anamnese_durchfuehren</outgoing>
        </startEvent>
        <sequenceFlow sourceRef="id_start108" targetRef="id_chirurgische_anamnese_durchfuehren" id="sf_start108_chirurgische_anamnese_durchfuehren"/>
        <userTask name="Chirurgische Anamnese durchfuehren" id="id_chirurgische_anamnese_durchfuehren">
            <incoming>sf_start108_chirurgische_anamnese_durchfuehren</incoming>
            <outgoing>sf_chirurgische_anamnese_durchfuehren_koerperliche_untersuchung_durchfuehren</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_chirurgische_anamnese_durchfuehren" targetRef="id_koerperliche_untersuchung_durchfuehren" id="sf_chirurgische_anamnese_durchfuehren_koerperliche_untersuchung_durchfuehren"/>
        <userTask name="Koerperliche Untersuchung durchfuehren" id="id_koerperliche_untersuchung_durchfuehren">
            <incoming>sf_chirurgische_anamnese_durchfuehren_koerperliche_untersuchung_durchfuehren</incoming>
            <outgoing>sf_koerperliche_untersuchung_durchfuehren_diagnose_klar_xor</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_koerperliche_untersuchung_durchfuehren" targetRef="id_diagnose_klar_xor" id="sf_koerperliche_untersuchung_durchfuehren_diagnose_klar_xor"/>
        <exclusiveGateway name="Diagnose klar?" id="id_diagnose_klar_xor">
            <incoming>sf_koerperliche_untersuchung_durchfuehren_diagnose_klar_xor</incoming>
            <outgoing>sf_diagnose_klar_xor_diagnose_klar_nein_grouping</outgoing>
            <outgoing>sf_diagnose_klar_xor_join_of_diagnose_klar_xor</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_diagnose_klar_xor" targetRef="id_diagnose_klar_nein_grouping" name="Nein" id="sf_diagnose_klar_xor_diagnose_klar_nein_grouping">
            <conditionExpression id="sf_diagnose_klar_xor_diagnose_klar_nein_grouping_condition">Nein</conditionExpression>
        </sequenceFlow>
        <sequenceFlow sourceRef="id_diagnose_klar_xor" targetRef="id_join_of_diagnose_klar_xor" name="else" id="sf_diagnose_klar_xor_join_of_diagnose_klar_xor">
            <conditionExpression id="sf_diagnose_klar_xor_join_of_diagnose_klar_xor_condition">else</conditionExpression>
        </sequenceFlow>
        <subProcess id="id_diagnose_klar_nein_grouping">
            <incoming>sf_diagnose_klar_xor_diagnose_klar_nein_grouping</incoming>
            <outgoing>sf_diagnose_klar_nein_grouping_join_of_diagnose_klar_xor</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start110">
                <outgoing>sf_start110_ultraschall_durchfuehren</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start110" targetRef="id_ultraschall_durchfuehren" id="sf_start110_ultraschall_durchfuehren"/>
            <userTask name="Ultraschall durchfuehren" id="id_ultraschall_durchfuehren">
                <incoming>sf_start110_ultraschall_durchfuehren</incoming>
                <outgoing>sf_ultraschall_durchfuehren_ultraschall_konklusiv_xor</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_ultraschall_durchfuehren" targetRef="id_ultraschall_konklusiv_xor" id="sf_ultraschall_durchfuehren_ultraschall_konklusiv_xor"/>
            <exclusiveGateway name="Ultraschall konklusiv?" id="id_ultraschall_konklusiv_xor">
                <incoming>sf_ultraschall_durchfuehren_ultraschall_konklusiv_xor</incoming>
                <outgoing>sf_ultraschall_konklusiv_xor_join_of_ultraschall_konklusiv_xor</outgoing>
                <outgoing>sf_ultraschall_konklusiv_xor_ct_abdomen_oder_mri_durchfuehren</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_ultraschall_konklusiv_xor" targetRef="id_join_of_ultraschall_konklusiv_xor" name="else" id="sf_ultraschall_konklusiv_xor_join_of_ultraschall_konklusiv_xor">
                <conditionExpression id="sf_ultraschall_konklusiv_xor_join_of_ultraschall_konklusiv_xor_condition">else</conditionExpression>
            </sequenceFlow>
            <sequenceFlow sourceRef="id_ultraschall_konklusiv_xor" targetRef="id_ct_abdomen_oder_mri_durchfuehren" name="Nein" id="sf_ultraschall_konklusiv_xor_ct_abdomen_oder_mri_durchfuehren">
                <conditionExpression id="sf_ultraschall_konklusiv_xor_ct_abdomen_oder_mri_durchfuehren_condition">Nein</conditionExpression>
            </sequenceFlow>
            <exclusiveGateway name="join" id="id_join_of_ultraschall_konklusiv_xor">
                <incoming>sf_ultraschall_konklusiv_xor_join_of_ultraschall_konklusiv_xor</incoming>
                <incoming>sf_ct_abdomen_oder_mri_durchfuehren_join_of_ultraschall_konklusiv_xor</incoming>
                <outgoing>sf_join_of_ultraschall_konklusiv_xor_end111</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_join_of_ultraschall_konklusiv_xor" targetRef="id_end111" id="sf_join_of_ultraschall_konklusiv_xor_end111"/>
            <userTask name="CT Abdomen oder MRI durchfuehren" id="id_ct_abdomen_oder_mri_durchfuehren">
                <incoming>sf_ultraschall_konklusiv_xor_ct_abdomen_oder_mri_durchfuehren</incoming>
                <outgoing>sf_ct_abdomen_oder_mri_durchfuehren_join_of_ultraschall_konklusiv_xor</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_ct_abdomen_oder_mri_durchfuehren" targetRef="id_join_of_ultraschall_konklusiv_xor" id="sf_ct_abdomen_oder_mri_durchfuehren_join_of_ultraschall_konklusiv_xor"/>
            <endEvent name="end" id="id_end111">
                <incoming>sf_join_of_ultraschall_konklusiv_xor_end111</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_diagnose_klar_nein_grouping" targetRef="id_join_of_diagnose_klar_xor" id="sf_diagnose_klar_nein_grouping_join_of_diagnose_klar_xor"/>
        <exclusiveGateway name="join" id="id_join_of_diagnose_klar_xor">
            <incoming>sf_diagnose_klar_xor_join_of_diagnose_klar_xor</incoming>
            <incoming>sf_diagnose_klar_nein_grouping_join_of_diagnose_klar_xor</incoming>
            <outgoing>sf_join_of_diagnose_klar_xor_hernie_diagnostiziert_xor</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_join_of_diagnose_klar_xor" targetRef="id_hernie_diagnostiziert_xor" id="sf_join_of_diagnose_klar_xor_hernie_diagnostiziert_xor"/>
        <exclusiveGateway name="Hernie diagnostiziert?" id="id_hernie_diagnostiziert_xor">
            <incoming>sf_join_of_diagnose_klar_xor_hernie_diagnostiziert_xor</incoming>
            <outgoing>sf_hernie_diagnostiziert_xor_hernie_diagnostiziert_ja_grouping</outgoing>
            <outgoing>sf_hernie_diagnostiziert_xor_andere_untersuchungen_durchfuehren</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_hernie_diagnostiziert_xor" targetRef="id_hernie_diagnostiziert_ja_grouping" name="Ja" id="sf_hernie_diagnostiziert_xor_hernie_diagnostiziert_ja_grouping">
            <conditionExpression id="sf_hernie_diagnostiziert_xor_hernie_diagnostiziert_ja_grouping_condition">Ja</conditionExpression>
        </sequenceFlow>
        <sequenceFlow sourceRef="id_hernie_diagnostiziert_xor" targetRef="id_andere_untersuchungen_durchfuehren" name="Nein" id="sf_hernie_diagnostiziert_xor_andere_untersuchungen_durchfuehren">
            <conditionExpression id="sf_hernie_diagnostiziert_xor_andere_untersuchungen_durchfuehren_condition">Nein</conditionExpression>
        </sequenceFlow>
        <subProcess id="id_hernie_diagnostiziert_ja_grouping">
            <incoming>sf_hernie_diagnostiziert_xor_hernie_diagnostiziert_ja_grouping</incoming>
            <outgoing>sf_hernie_diagnostiziert_ja_grouping_join_of_hernie_diagnostiziert_xor</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start112">
                <outgoing>sf_start112_einwilligung_des_patienten_einholen</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start112" targetRef="id_einwilligung_des_patienten_einholen" id="sf_start112_einwilligung_des_patienten_einholen"/>
            <userTask name="Einwilligung des Patienten einholen" id="id_einwilligung_des_patienten_einholen">
                <incoming>sf_start112_einwilligung_des_patienten_einholen</incoming>
                <outgoing>sf_einwilligung_des_patienten_einholen_patient_zu_op_eingewillig_xor</outgoing>
                <dataOutputAssociation id="df_id_einwilligung_des_patienten_einholen_data_patienteneinwilligung">
                    <targetRef>id_data_patienteneinwilligung</targetRef>
                </dataOutputAssociation>
            </userTask>
            <sequenceFlow sourceRef="id_einwilligung_des_patienten_einholen" targetRef="id_patient_zu_op_eingewillig_xor" id="sf_einwilligung_des_patienten_einholen_patient_zu_op_eingewillig_xor"/>
            <dataObjectReference name="Patienteneinwilligung" id="id_data_patienteneinwilligung">
                <extensionElements>
                    <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">Patienteneinwilligung</ns5:type>
                </extensionElements>
            </dataObjectReference>
            <exclusiveGateway name="Patient zu OP eingewilligt?" id="id_patient_zu_op_eingewillig_xor">
                <incoming>sf_einwilligung_des_patienten_einholen_patient_zu_op_eingewillig_xor</incoming>
                <outgoing>sf_patient_zu_op_eingewillig_xor_keine_operation_durchfuehren</outgoing>
                <outgoing>sf_patient_zu_op_eingewillig_xor_patient_zu_op_eingewilligt_yes_grouping</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_patient_zu_op_eingewillig_xor" targetRef="id_keine_operation_durchfuehren" id="sf_patient_zu_op_eingewillig_xor_keine_operation_durchfuehren"/>
            <sequenceFlow sourceRef="id_patient_zu_op_eingewillig_xor" targetRef="id_patient_zu_op_eingewilligt_yes_grouping" id="sf_patient_zu_op_eingewillig_xor_patient_zu_op_eingewilligt_yes_grouping"/>
            <userTask name="Keine Operation durchfuehren" id="id_keine_operation_durchfuehren">
                <incoming>sf_patient_zu_op_eingewillig_xor_keine_operation_durchfuehren</incoming>
                <outgoing>sf_keine_operation_durchfuehren_join_of_patient_zu_op_eingewillig_xor</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_keine_operation_durchfuehren" targetRef="id_join_of_patient_zu_op_eingewillig_xor" id="sf_keine_operation_durchfuehren_join_of_patient_zu_op_eingewillig_xor"/>
            <subProcess id="id_patient_zu_op_eingewilligt_yes_grouping">
                <incoming>sf_patient_zu_op_eingewillig_xor_patient_zu_op_eingewilligt_yes_grouping</incoming>
                <outgoing>sf_patient_zu_op_eingewilligt_yes_grouping_join_of_patient_zu_op_eingewillig_xor</outgoing>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start114">
                    <outgoing>sf_start114_operationsfreigabe_einholen</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start114" targetRef="id_operationsfreigabe_einholen" id="sf_start114_operationsfreigabe_einholen"/>
                <userTask name="Operationsfreigabe einholen" id="id_operationsfreigabe_einholen">
                    <incoming>sf_start114_operationsfreigabe_einholen</incoming>
                    <outgoing>sf_operationsfreigabe_einholen_operationsfreigabe</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_operationsfreigabe_einholen" targetRef="id_operationsfreigabe" id="sf_operationsfreigabe_einholen_operationsfreigabe"/>
                <intermediateCatchEvent name="Operationsfreigabe" id="id_operationsfreigabe">
                    <incoming>sf_operationsfreigabe_einholen_operationsfreigabe</incoming>
                    <outgoing>sf_operationsfreigabe_operationsfreigabe_triggerAction</outgoing>
                    <conditionalEventDefinition id="event_operationsfreigabe">
                        <condition xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="tFormalExpression" language="text/cql">Operationsfreigabe</condition>
                    </conditionalEventDefinition>
                </intermediateCatchEvent>
                <sequenceFlow sourceRef="id_operationsfreigabe" targetRef="id_operationsfreigabe_triggerAction" id="sf_operationsfreigabe_operationsfreigabe_triggerAction"/>
                <dataObjectReference name="Operationsfreigabe" id="id_data_operationsfreigabe">
                    <extensionElements>
                        <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">Operationsfreigabe</ns5:type>
                    </extensionElements>
                </dataObjectReference>
                <userTask name="Operationsfreigabe" id="id_operationsfreigabe_triggerAction">
                    <incoming>sf_operationsfreigabe_operationsfreigabe_triggerAction</incoming>
                    <outgoing>sf_operationsfreigabe_triggerAction_operation_freigeben_xor</outgoing>
                    <property name="prop_Operationsfreigabe" id="prop_operationsfreigabe_triggerAction_data_operationsfreigabe"/>
                    <dataInputAssociation id="df_id_operationsfreigabe_triggerAction_data_operationsfreigabe">
                        <sourceRef>id_data_operationsfreigabe</sourceRef>
                        <targetRef>prop_operationsfreigabe_triggerAction_data_operationsfreigabe</targetRef>
                    </dataInputAssociation>
                </userTask>
                <sequenceFlow sourceRef="id_operationsfreigabe_triggerAction" targetRef="id_operation_freigeben_xor" id="sf_operationsfreigabe_triggerAction_operation_freigeben_xor"/>
                <exclusiveGateway name="Operation freigeben?" id="id_operation_freigeben_xor">
                    <incoming>sf_operationsfreigabe_triggerAction_operation_freigeben_xor</incoming>
                    <outgoing>sf_operation_freigeben_xor_operation_freigeben_ja_grouping</outgoing>
                    <outgoing>sf_operation_freigeben_xor_keine_operation_durchfuehren_2</outgoing>
                </exclusiveGateway>
                <sequenceFlow sourceRef="id_operation_freigeben_xor" targetRef="id_operation_freigeben_ja_grouping" name="Ja" id="sf_operation_freigeben_xor_operation_freigeben_ja_grouping">
                    <conditionExpression id="sf_operation_freigeben_xor_operation_freigeben_ja_grouping_condition">Ja</conditionExpression>
                </sequenceFlow>
                <sequenceFlow sourceRef="id_operation_freigeben_xor" targetRef="id_keine_operation_durchfuehren_2" name="Nein" id="sf_operation_freigeben_xor_keine_operation_durchfuehren_2">
                    <conditionExpression id="sf_operation_freigeben_xor_keine_operation_durchfuehren_2_condition">Nein</conditionExpression>
                </sequenceFlow>
                <subProcess id="id_operation_freigeben_ja_grouping">
                    <incoming>sf_operation_freigeben_xor_operation_freigeben_ja_grouping</incoming>
                    <outgoing>sf_operation_freigeben_ja_grouping_join_of_operation_freigeben_xor</outgoing>
                    <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start116">
                        <outgoing>sf_start116_kann_die_operation_tk_durchgefuehrt_werden</outgoing>
                    </startEvent>
                    <sequenceFlow sourceRef="id_start116" targetRef="id_kann_die_operation_tk_durchgefuehrt_werden" id="sf_start116_kann_die_operation_tk_durchgefuehrt_werden"/>
                    <userTask name="Kann die Operation TK durchgefuehrt werden?" id="id_kann_die_operation_tk_durchgefuehrt_werden">
                        <incoming>sf_start116_kann_die_operation_tk_durchgefuehrt_werden</incoming>
                        <outgoing>sf_kann_die_operation_tk_durchgefuehrt_werden_hernienoperation_durchfuehren</outgoing>
                    </userTask>
                    <sequenceFlow sourceRef="id_kann_die_operation_tk_durchgefuehrt_werden" targetRef="id_hernienoperation_durchfuehren" id="sf_kann_die_operation_tk_durchgefuehrt_werden_hernienoperation_durchfuehren"/>
                    <userTask name="Hernienoperation durchfuehren" id="id_hernienoperation_durchfuehren">
                        <incoming>sf_kann_die_operation_tk_durchgefuehrt_werden_hernienoperation_durchfuehren</incoming>
                        <outgoing>sf_hernienoperation_durchfuehren_operation_tagesklinisch_xor</outgoing>
                    </userTask>
                    <sequenceFlow sourceRef="id_hernienoperation_durchfuehren" targetRef="id_operation_tagesklinisch_xor" id="sf_hernienoperation_durchfuehren_operation_tagesklinisch_xor"/>
                    <exclusiveGateway name="Operation tagesklinisch?" id="id_operation_tagesklinisch_xor">
                        <incoming>sf_hernienoperation_durchfuehren_operation_tagesklinisch_xor</incoming>
                        <outgoing>sf_operation_tagesklinisch_xor_vier_h_nach_op</outgoing>
                        <outgoing>sf_operation_tagesklinisch_xor_erster_tag_nach_op</outgoing>
                    </exclusiveGateway>
                    <sequenceFlow sourceRef="id_operation_tagesklinisch_xor" targetRef="id_vier_h_nach_op" id="sf_operation_tagesklinisch_xor_vier_h_nach_op"/>
                    <sequenceFlow sourceRef="id_operation_tagesklinisch_xor" targetRef="id_erster_tag_nach_op" id="sf_operation_tagesklinisch_xor_erster_tag_nach_op"/>
                    <userTask name="4h nach OP" id="id_vier_h_nach_op">
                        <incoming>sf_operation_tagesklinisch_xor_vier_h_nach_op</incoming>
                        <outgoing>sf_vier_h_nach_op_join_of_operation_tagesklinisch_xor</outgoing>
                    </userTask>
                    <sequenceFlow sourceRef="id_vier_h_nach_op" targetRef="id_join_of_operation_tagesklinisch_xor" id="sf_vier_h_nach_op_join_of_operation_tagesklinisch_xor"/>
                    <userTask name="am 1. post OP Tag" id="id_erster_tag_nach_op">
                        <incoming>sf_operation_tagesklinisch_xor_erster_tag_nach_op</incoming>
                        <outgoing>sf_erster_tag_nach_op_join_of_operation_tagesklinisch_xor</outgoing>
                    </userTask>
                    <sequenceFlow sourceRef="id_erster_tag_nach_op" targetRef="id_join_of_operation_tagesklinisch_xor" id="sf_erster_tag_nach_op_join_of_operation_tagesklinisch_xor"/>
                    <exclusiveGateway name="join" id="id_join_of_operation_tagesklinisch_xor">
                        <incoming>sf_erster_tag_nach_op_join_of_operation_tagesklinisch_xor</incoming>
                        <incoming>sf_vier_h_nach_op_join_of_operation_tagesklinisch_xor</incoming>
                        <outgoing>sf_join_of_operation_tagesklinisch_xor_postoperative_wundinspektion_durchfuehren</outgoing>
                    </exclusiveGateway>
                    <sequenceFlow sourceRef="id_join_of_operation_tagesklinisch_xor" targetRef="id_postoperative_wundinspektion_durchfuehren" id="sf_join_of_operation_tagesklinisch_xor_postoperative_wundinspektion_durchfuehren"/>
                    <userTask name="postoperative Wundinspektion durchfuehren" id="id_postoperative_wundinspektion_durchfuehren">
                        <incoming>sf_join_of_operation_tagesklinisch_xor_postoperative_wundinspektion_durchfuehren</incoming>
                        <outgoing>sf_postoperative_wundinspektion_durchfuehren_patient_entlassen</outgoing>
                    </userTask>
                    <sequenceFlow sourceRef="id_postoperative_wundinspektion_durchfuehren" targetRef="id_patient_entlassen" id="sf_postoperative_wundinspektion_durchfuehren_patient_entlassen"/>
                    <userTask name="Patient entlassen" id="id_patient_entlassen">
                        <incoming>sf_postoperative_wundinspektion_durchfuehren_patient_entlassen</incoming>
                        <outgoing>sf_patient_entlassen_end117</outgoing>
                        <dataOutputAssociation id="df_id_patient_entlassen_data_entlassungspapiere">
                            <targetRef>id_data_entlassungspapiere</targetRef>
                        </dataOutputAssociation>
                    </userTask>
                    <sequenceFlow sourceRef="id_patient_entlassen" targetRef="id_end117" id="sf_patient_entlassen_end117"/>
                    <dataObjectReference name="Entlassungspapiere" id="id_data_entlassungspapiere">
                        <extensionElements>
                            <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">Entlassungspapiere</ns5:type>
                        </extensionElements>
                    </dataObjectReference>
                    <endEvent name="end" id="id_end117">
                        <incoming>sf_patient_entlassen_end117</incoming>
                    </endEvent>
                </subProcess>
                <sequenceFlow sourceRef="id_operation_freigeben_ja_grouping" targetRef="id_join_of_operation_freigeben_xor" id="sf_operation_freigeben_ja_grouping_join_of_operation_freigeben_xor"/>
                <userTask name="Keine Operation durchfuehren" id="id_keine_operation_durchfuehren_2">
                    <incoming>sf_operation_freigeben_xor_keine_operation_durchfuehren_2</incoming>
                    <outgoing>sf_keine_operation_durchfuehren_2_join_of_operation_freigeben_xor</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_keine_operation_durchfuehren_2" targetRef="id_join_of_operation_freigeben_xor" id="sf_keine_operation_durchfuehren_2_join_of_operation_freigeben_xor"/>
                <exclusiveGateway name="join" id="id_join_of_operation_freigeben_xor">
                    <incoming>sf_operation_freigeben_ja_grouping_join_of_operation_freigeben_xor</incoming>
                    <incoming>sf_keine_operation_durchfuehren_2_join_of_operation_freigeben_xor</incoming>
                    <outgoing>sf_join_of_operation_freigeben_xor_end115</outgoing>
                </exclusiveGateway>
                <sequenceFlow sourceRef="id_join_of_operation_freigeben_xor" targetRef="id_end115" id="sf_join_of_operation_freigeben_xor_end115"/>
                <endEvent name="end" id="id_end115">
                    <incoming>sf_join_of_operation_freigeben_xor_end115</incoming>
                </endEvent>
            </subProcess>
            <sequenceFlow sourceRef="id_patient_zu_op_eingewilligt_yes_grouping" targetRef="id_join_of_patient_zu_op_eingewillig_xor" id="sf_patient_zu_op_eingewilligt_yes_grouping_join_of_patient_zu_op_eingewillig_xor"/>
            <exclusiveGateway name="join" id="id_join_of_patient_zu_op_eingewillig_xor">
                <incoming>sf_keine_operation_durchfuehren_join_of_patient_zu_op_eingewillig_xor</incoming>
                <incoming>sf_patient_zu_op_eingewilligt_yes_grouping_join_of_patient_zu_op_eingewillig_xor</incoming>
                <outgoing>sf_join_of_patient_zu_op_eingewillig_xor_end113</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_join_of_patient_zu_op_eingewillig_xor" targetRef="id_end113" id="sf_join_of_patient_zu_op_eingewillig_xor_end113"/>
            <endEvent name="end" id="id_end113">
                <incoming>sf_join_of_patient_zu_op_eingewillig_xor_end113</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_hernie_diagnostiziert_ja_grouping" targetRef="id_join_of_hernie_diagnostiziert_xor" id="sf_hernie_diagnostiziert_ja_grouping_join_of_hernie_diagnostiziert_xor"/>
        <userTask name="Andere Untersuchungen durchfuehren" id="id_andere_untersuchungen_durchfuehren">
            <incoming>sf_hernie_diagnostiziert_xor_andere_untersuchungen_durchfuehren</incoming>
            <outgoing>sf_andere_untersuchungen_durchfuehren_join_of_hernie_diagnostiziert_xor</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_andere_untersuchungen_durchfuehren" targetRef="id_join_of_hernie_diagnostiziert_xor" id="sf_andere_untersuchungen_durchfuehren_join_of_hernie_diagnostiziert_xor"/>
        <exclusiveGateway name="join" id="id_join_of_hernie_diagnostiziert_xor">
            <incoming>sf_andere_untersuchungen_durchfuehren_join_of_hernie_diagnostiziert_xor</incoming>
            <incoming>sf_hernie_diagnostiziert_ja_grouping_join_of_hernie_diagnostiziert_xor</incoming>
            <outgoing>sf_join_of_hernie_diagnostiziert_xor_end109</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_join_of_hernie_diagnostiziert_xor" targetRef="id_end109" id="sf_join_of_hernie_diagnostiziert_xor_end109"/>
        <endEvent name="end" id="id_end109">
            <incoming>sf_join_of_hernie_diagnostiziert_xor_end109</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="hernien">
            <ns4:BPMNShape bpmnElement="id_start116" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2640.0" y="684.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_kann_die_operation_tk_durchgefuehrt_werden" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2720.0" y="659.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_hernienoperation_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2870.0" y="659.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_operation_tagesklinisch_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3020.0" y="679.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_vier_h_nach_op" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3110.0" y="569.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_erster_tag_nach_op" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3110.0" y="749.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_operation_tagesklinisch_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3260.0" y="679.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_postoperative_wundinspektion_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3350.0" y="659.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_patient_entlassen" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3500.0" y="659.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end117" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3650.0" y="684.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_kann_die_operation_tk_durchgefuehrt_werden_hernienoperation_durchfuehren">
                <ns2:waypoint x="2820.0" y="699.041015625"/>
                <ns2:waypoint x="2870.0" y="699.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_operation_tagesklinisch_xor_vier_h_nach_op">
                <ns2:waypoint x="3060.0" y="699.041015625"/>
                <ns2:waypoint x="3085.0" y="699.041015625"/>
                <ns2:waypoint x="3085.0" y="609.041015625"/>
                <ns2:waypoint x="3110.0" y="609.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_operation_tagesklinisch_xor_postoperative_wundinspektion_durchfuehren">
                <ns2:waypoint x="3300.0" y="699.041015625"/>
                <ns2:waypoint x="3350.0" y="699.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_patient_entlassen_end117">
                <ns2:waypoint x="3600.0" y="699.041015625"/>
                <ns2:waypoint x="3650.0" y="699.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_hernienoperation_durchfuehren_operation_tagesklinisch_xor">
                <ns2:waypoint x="2970.0" y="699.041015625"/>
                <ns2:waypoint x="3020.0" y="699.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_postoperative_wundinspektion_durchfuehren_patient_entlassen">
                <ns2:waypoint x="3450.0" y="699.041015625"/>
                <ns2:waypoint x="3500.0" y="699.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_vier_h_nach_op_join_of_operation_tagesklinisch_xor">
                <ns2:waypoint x="3210.0" y="609.041015625"/>
                <ns2:waypoint x="3235.0" y="609.041015625"/>
                <ns2:waypoint x="3235.0" y="699.041015625"/>
                <ns2:waypoint x="3260.0" y="699.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_erster_tag_nach_op_join_of_operation_tagesklinisch_xor">
                <ns2:waypoint x="3210.0" y="789.041015625"/>
                <ns2:waypoint x="3235.0" y="789.041015625"/>
                <ns2:waypoint x="3235.0" y="699.041015625"/>
                <ns2:waypoint x="3260.0" y="699.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start116_kann_die_operation_tk_durchgefuehrt_werden">
                <ns2:waypoint x="2670.0" y="699.041015625"/>
                <ns2:waypoint x="2720.0" y="699.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_operation_tagesklinisch_xor_erster_tag_nach_op">
                <ns2:waypoint x="3060.0" y="699.041015625"/>
                <ns2:waypoint x="3085.0" y="699.041015625"/>
                <ns2:waypoint x="3085.0" y="789.041015625"/>
                <ns2:waypoint x="3110.0" y="789.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_entlassungspapiere" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3532.0" y="859.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="df_id_patient_entlassen_data_entlassungspapiere">
                <ns2:waypoint x="3550.0" y="739.0"/>
                <ns2:waypoint x="3550.0" y="859.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_operationsfreigabe" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1960.0" y="379.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_start114" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2000.0" y="845.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_operationsfreigabe_einholen" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2080.0" y="820.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_operationsfreigabe" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2230.0" y="850.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_operationsfreigabe_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2310.0" y="831.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_operation_freigeben_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2460.0" y="860.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_operation_freigeben_ja_grouping" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2550.0" y="479.0" width="1190.0" height="450.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_keine_operation_durchfuehren_2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3095.0" y="1029.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_operation_freigeben_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3790.0" y="864.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end115" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3880.0" y="869.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_start114_operationsfreigabe_einholen">
                <ns2:waypoint x="2030.0" y="860.041015625"/>
                <ns2:waypoint x="2080.0" y="860.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_operationsfreigabe_einholen_operationsfreigabe">
                <ns2:waypoint x="2180.0" y="860.041015625"/>
                <ns2:waypoint x="2205.0" y="860.041015625"/>
                <ns2:waypoint x="2205.0" y="865.041015625"/>
                <ns2:waypoint x="2230.0" y="865.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_operationsfreigabe_triggerAction_operation_freigeben_xor">
                <ns2:waypoint x="2410.0" y="871.041015625"/>
                <ns2:waypoint x="2435.0" y="871.041015625"/>
                <ns2:waypoint x="2435.0" y="880.041015625"/>
                <ns2:waypoint x="2460.0" y="880.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_operationsfreigabe_operationsfreigabe_triggerAction">
                <ns2:waypoint x="2260.0" y="865.041015625"/>
                <ns2:waypoint x="2285.0" y="865.041015625"/>
                <ns2:waypoint x="2285.0" y="871.041015625"/>
                <ns2:waypoint x="2310.0" y="871.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_operation_freigeben_ja_grouping_join_of_operation_freigeben_xor">
                <ns2:waypoint x="3740.0" y="704.041015625"/>
                <ns2:waypoint x="3765.0" y="704.041015625"/>
                <ns2:waypoint x="3765.0" y="884.041015625"/>
                <ns2:waypoint x="3790.0" y="884.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_operation_freigeben_xor_operation_freigeben_ja_grouping">
                <ns2:waypoint x="2500.0" y="880.041015625"/>
                <ns2:waypoint x="2525.0" y="880.041015625"/>
                <ns2:waypoint x="2525.0" y="704.041015625"/>
                <ns2:waypoint x="2550.0" y="704.041015625"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="2435.0" y="792.041015625" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_keine_operation_durchfuehren_2_join_of_operation_freigeben_xor">
                <ns2:waypoint x="3195.0" y="1069.041015625"/>
                <ns2:waypoint x="3492.5" y="1069.041015625"/>
                <ns2:waypoint x="3492.5" y="884.041015625"/>
                <ns2:waypoint x="3790.0" y="884.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_operation_freigeben_xor_end115">
                <ns2:waypoint x="3830.0" y="884.041015625"/>
                <ns2:waypoint x="3880.0" y="884.041015625"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_operation_freigeben_xor_keine_operation_durchfuehren_2">
                <ns2:waypoint x="2500.0" y="880.041015625"/>
                <ns2:waypoint x="2797.5" y="880.041015625"/>
                <ns2:waypoint x="2797.5" y="1069.041015625"/>
                <ns2:waypoint x="3095.0" y="1069.041015625"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="2707.5" y="974.541015625" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="df_id_operationsfreigabe_triggerAction_data_operationsfreigabe">
                <ns2:waypoint x="1996.0" y="426.00541666617767"/>
                <ns2:waypoint x="2327.2776399827676" y="831.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start112" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1590.0" y="451.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_einwilligung_des_patienten_einholen" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1670.0" y="426.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_patient_zu_op_eingewillig_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1820.0" y="445.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_keine_operation_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2891.0" y="150.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_patient_zu_op_eingewilligt_yes_grouping" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1910.0" y="329.0" width="2063.0" height="819.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_patient_zu_op_eingewillig_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4023.0" y="444.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end113" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4113.0" y="449.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_patient_zu_op_eingewillig_xor_keine_operation_durchfuehren">
                <ns2:waypoint x="1860.0" y="465.0"/>
                <ns2:waypoint x="2375.75" y="465.0"/>
                <ns2:waypoint x="2375.75" y="190.0"/>
                <ns2:waypoint x="2892.0" y="190.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_keine_operation_durchfuehren_join_of_patient_zu_op_eingewillig_xor">
                <ns2:waypoint x="2992.0" y="190.0"/>
                <ns2:waypoint x="3507.25" y="190.0"/>
                <ns2:waypoint x="3507.25" y="464.0"/>
                <ns2:waypoint x="4023.0" y="464.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start112_einwilligung_des_patienten_einholen">
                <ns2:waypoint x="1620.0" y="466.0"/>
                <ns2:waypoint x="1670.0" y="466.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_patient_zu_op_eingewillig_xor_patient_zu_op_eingewilligt_yes_grouping">
                <ns2:waypoint x="1860.0" y="465.0"/>
                <ns2:waypoint x="1885.0" y="465.0"/>
                <ns2:waypoint x="1885.0" y="739.0000000000001"/>
                <ns2:waypoint x="1910.0" y="739.0000000000001"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_patient_zu_op_eingewillig_xor_end113">
                <ns2:waypoint x="4063.0" y="464.0"/>
                <ns2:waypoint x="4113.0" y="464.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_einwilligung_des_patienten_einholen_patient_zu_op_eingewillig_xor">
                <ns2:waypoint x="1770.0" y="466.0"/>
                <ns2:waypoint x="1795.0" y="466.0"/>
                <ns2:waypoint x="1795.0" y="465.0"/>
                <ns2:waypoint x="1820.0" y="465.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_patient_zu_op_eingewilligt_yes_grouping_join_of_patient_zu_op_eingewillig_xor">
                <ns2:waypoint x="3973.0" y="739.0"/>
                <ns2:waypoint x="3998.0" y="739.0"/>
                <ns2:waypoint x="3998.0" y="464.0"/>
                <ns2:waypoint x="4023.0" y="464.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_patienteneinwilligung" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1702.0" y="1178.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="df_id_einwilligung_des_patienten_einholen_data_patienteneinwilligung">
                <ns2:waypoint x="1720.0" y="506.0"/>
                <ns2:waypoint x="1720.0" y="1179.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start110" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="620.0" y="826.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ultraschall_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="700.0" y="801.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ultraschall_konklusiv_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="850.0" y="824.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_ultraschall_konklusiv_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1090.0" y="826.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ct_abdomen_oder_mri_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="940.0" y="737.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end111" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1180.0" y="831.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_ultraschall_konklusiv_xor_ct_abdomen_oder_mri_durchfuehren">
                <ns2:waypoint x="890.0" y="844.4794921875"/>
                <ns2:waypoint x="915.0" y="844.4794921875"/>
                <ns2:waypoint x="915.0" y="777.4794921875"/>
                <ns2:waypoint x="940.0" y="777.4794921875"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="825.0" y="810.9794921875" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ultraschall_konklusiv_xor_join_of_ultraschall_konklusiv_xor">
                <ns2:waypoint x="890.0" y="844.4794921875"/>
                <ns2:waypoint x="990.0" y="844.4794921875"/>
                <ns2:waypoint x="990.0" y="846.4794921875"/>
                <ns2:waypoint x="1090.0" y="846.4794921875"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="900.0" y="845.4794921875" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ct_abdomen_oder_mri_durchfuehren_join_of_ultraschall_konklusiv_xor">
                <ns2:waypoint x="1040.0" y="777.4794921875"/>
                <ns2:waypoint x="1065.0" y="777.4794921875"/>
                <ns2:waypoint x="1065.0" y="846.4794921875"/>
                <ns2:waypoint x="1090.0" y="846.4794921875"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ultraschall_durchfuehren_ultraschall_konklusiv_xor">
                <ns2:waypoint x="800.0" y="841.4794921875"/>
                <ns2:waypoint x="825.0" y="841.4794921875"/>
                <ns2:waypoint x="825.0" y="844.4794921875"/>
                <ns2:waypoint x="850.0" y="844.4794921875"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_ultraschall_konklusiv_xor_end111">
                <ns2:waypoint x="1130.0" y="846.4794921875"/>
                <ns2:waypoint x="1180.0" y="846.4794921875"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start110_ultraschall_durchfuehren">
                <ns2:waypoint x="650.0" y="841.4794921875"/>
                <ns2:waypoint x="700.0" y="841.4794921875"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start108" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="60.0" y="663.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_chirurgische_anamnese_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="140.0" y="638.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_koerperliche_untersuchung_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="290.0" y="636.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_diagnose_klar_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="440.0" y="652.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_diagnose_klar_nein_grouping" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="530.0" y="647.0" width="740.0" height="294.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_diagnose_klar_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1320.0" y="736.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_hernie_diagnostiziert_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1410.0" y="912.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_hernie_diagnostiziert_ja_grouping" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1500.0" y="60.0" width="2703.0" height="1188.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_andere_untersuchungen_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2801.0" y="1348.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_hernie_diagnostiziert_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4253.0" y="999.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end109" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4343.0" y="1004.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_diagnose_klar_xor_join_of_diagnose_klar_xor">
                <ns2:waypoint x="480.0" y="672.4794921875"/>
                <ns2:waypoint x="900.0" y="672.4794921875"/>
                <ns2:waypoint x="900.0" y="756.4794921875"/>
                <ns2:waypoint x="1320.0" y="756.4794921875"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="810.0" y="714.4794921875" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start108_chirurgische_anamnese_durchfuehren">
                <ns2:waypoint x="90.0" y="678.4794921875"/>
                <ns2:waypoint x="140.0" y="678.4794921875"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_andere_untersuchungen_durchfuehren_join_of_hernie_diagnostiziert_xor">
                <ns2:waypoint x="2902.0" y="1388.4843355429293"/>
                <ns2:waypoint x="3577.25" y="1388.4843355429293"/>
                <ns2:waypoint x="3577.25" y="1019.4794921875"/>
                <ns2:waypoint x="4253.0" y="1019.4794921875"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_koerperliche_untersuchung_durchfuehren_diagnose_klar_xor">
                <ns2:waypoint x="390.0" y="676.4794921875"/>
                <ns2:waypoint x="415.0" y="676.4794921875"/>
                <ns2:waypoint x="415.0" y="672.4794921875"/>
                <ns2:waypoint x="440.0" y="672.4794921875"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_hernie_diagnostiziert_ja_grouping_join_of_hernie_diagnostiziert_xor">
                <ns2:waypoint x="4203.0" y="654.4794921875"/>
                <ns2:waypoint x="4228.0" y="654.4794921875"/>
                <ns2:waypoint x="4228.0" y="1019.4794921875"/>
                <ns2:waypoint x="4253.0" y="1019.4794921875"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_chirurgische_anamnese_durchfuehren_koerperliche_untersuchung_durchfuehren">
                <ns2:waypoint x="240.0" y="678.4794921875"/>
                <ns2:waypoint x="265.0" y="678.4794921875"/>
                <ns2:waypoint x="265.0" y="676.4794921875"/>
                <ns2:waypoint x="290.0" y="676.4794921875"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_diagnose_klar_nein_grouping_join_of_diagnose_klar_xor">
                <ns2:waypoint x="1270.0" y="794.4794921875"/>
                <ns2:waypoint x="1295.0" y="794.4794921875"/>
                <ns2:waypoint x="1295.0" y="756.4794921875"/>
                <ns2:waypoint x="1320.0" y="756.4794921875"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_hernie_diagnostiziert_xor_hernie_diagnostiziert_ja_grouping">
                <ns2:waypoint x="1450.0" y="932.4794921875"/>
                <ns2:waypoint x="1475.0" y="932.4794921875"/>
                <ns2:waypoint x="1475.0" y="654.4794921875002"/>
                <ns2:waypoint x="1500.0" y="654.4794921875002"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="1385.0" y="793.4794921875" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_diagnose_klar_xor_diagnose_klar_nein_grouping">
                <ns2:waypoint x="480.0" y="672.4794921875"/>
                <ns2:waypoint x="505.0" y="672.4794921875"/>
                <ns2:waypoint x="505.0" y="794.4794921874999"/>
                <ns2:waypoint x="530.0" y="794.4794921874999"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="415.0" y="733.4794921875" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_diagnose_klar_xor_hernie_diagnostiziert_xor">
                <ns2:waypoint x="1360.0" y="756.4794921875"/>
                <ns2:waypoint x="1385.0" y="756.4794921875"/>
                <ns2:waypoint x="1385.0" y="932.4794921875"/>
                <ns2:waypoint x="1410.0" y="932.4794921875"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_hernie_diagnostiziert_xor_andere_untersuchungen_durchfuehren">
                <ns2:waypoint x="1450.0" y="932.4794921875"/>
                <ns2:waypoint x="2125.75" y="932.4794921875"/>
                <ns2:waypoint x="2125.75" y="1388.474744740099"/>
                <ns2:waypoint x="2802.0" y="1388.474744740099"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="2035.875" y="1160.4771184637993" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_hernie_diagnostiziert_xor_end109">
                <ns2:waypoint x="4293.0" y="1019.4794921875"/>
                <ns2:waypoint x="4343.0" y="1019.4794921875"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>

```

<script type="text/javascript" src="../js/highlight.js"></script>