
# HTEP

Transformation for the Medizinische SOPHTEP – Totalendoprothetik des Hueftgelenks

## Discussions

 * As of yet there is no support for nested gateway in the transformation. This leads to a partially invalid result.
 * This is due to, that our decision logic for xor-gateways is tree-based, meaning that all action must be nested actions of the gateway. This does not allow "jumps" to actions that are in other gateways.
 * As the transformation of nested xor elements may become its own subprocess, it causes problems with participants. This can then not be transformed into BPMN, because expanded subprocesses do not allow having BPMN-Pools.

## Graph

<script type="text/javascript" src="https://unpkg.com/bpmn-js@7.2.1/dist/bpmn-navigated-viewer.production.min.js"></script>
Usage: Mouse Click + Move: Scroll around. Ctrl + Mouse Wheel: Zoom.

<div id="container" style="width: 100%; height: 500px; border: 1px solid lightgray; overflow:auto;"></div>

<script type="text/javascript" src="../js/fhir2bpmn/htep.js"></script>

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
    <id value="htep"/>
    <version value="1.0"/>
    <name value="HTEP"/>
    <title value="Totalendoprothetik des Hüftgelenks"/>
    <type>
        <coding>
            <system value="http://terminology.hl7.org/CodeSystem/plan-definition-type"/>
            <code value="clinical-protocol"/>
            <display value="Clinical Protocol"/>
        </coding>
    </type>
    <status value="draft"/>

    <actor>
        <id value="chirurgie"/>
        <label value="Chirurgie"/>
        <options>
            <type value="practitioner"/>
        </options>
    </actor>
    <actor>
        <id value="aufnahmemangement"/>
        <label value="Aufnahmemangement"/>
        <options>
            <type value="practitioner"/>
        </options>
    </actor>
    <actor>
        <id value="anaesthesie"/>
        <label value="Anaesthesie"/>
        <options>
            <type value="practitioner"/>
        </options>
    </actor>
    <actor>
        <id value="radiologie"/>
        <label value="Radiologie"/>
        <options>
            <type value="practitioner"/>
        </options>
    </actor>
    <actor>
        <id value="labor"/>
        <label value="Labor"/>
        <options>
            <type value="practitioner"/>
        </options>
    </actor>
    <actor>
        <id value="orthopaedie"/>
        <label value="Orthopaedie"/>
        <options>
            <type value="practitioner"/>
        </options>
    </actor>
    <actor>
        <id value="entlassungsmanagement"/>
        <label value="Entlassungsmanagement"/>
        <options>
            <type value="practitioner"/>
        </options>
    </actor>

    <action>
        <id value="orthopaedische_anamnese_durchfuehren"/>
        <title value="Orthopaedische Anamnese durchfuehren"/>
        <participant>
            <actor value="orthopaedie"/>
        </participant>
        <relatedAction>
            <actionId value="klinische_untersuchung"/>
            <relationship value="before-start"/>
        </relatedAction>
    </action>
    <action>
        <id value="klinische_untersuchung"/>
        <title value="Klinische Untersuchung entsprechend HHS durchfuehren"/>
        <participant>
            <actor value="orthopaedie"/>
        </participant>
        <relatedAction>
            <actionId value="radiologische_befunde_erheben"/>
            <relationship value="before-start"/>
        </relatedAction>
    </action>
    <action>
        <id value="radiologische_befunde_erheben"/>
        <title value="radiologische Befunde erheben"/>
        <participant>
            <actor value="radiologie"/>
        </participant>
        <relatedAction>
            <actionId value="laborbefunde_erheben"/>
            <relationship value="before-start"/>
        </relatedAction>
        <action>
            <id value="standardroentgen_durchfuehren"/>
            <title value="Standardroentgen durchfuehren"/>
            <participant>
                <actor value="radiologie"/>
            </participant>
            <relatedAction>
                <actionId value="ganzbeinroentgen_durchfuehren"/>
                <relationship value="before-start"/>
            </relatedAction>
        </action>
        <action>
            <id value="ganzbeinroentgen_durchfuehren"/>
            <title value="Ganzbeingoentgen durchfuehren"/>
            <participant>
                <actor value="radiologie"/>
            </participant>
            <relatedAction>
                <actionId value="par_szinitigraphie_xor_ct_mir"/>
                <relationship value="before-start"/>
            </relatedAction>
        </action>
        <action>
            <id value="par_szinitigraphie_xor_ct_mir"/>
            <groupingBehavior value="logical-group"/>
            <selectionBehavior value="all"/>
            <participant>
                <actor value="radiologie"/>
            </participant>
            <action>
                <id value="szintigraphie_durchfuehren"/>
                <title value="Szintigraphie durchfuehren"/>
                <participant>
                    <actor value="radiologie"/>
                </participant>
                <relatedAction>
                    <actionId value="par_szinitigraphie_xor_ct_mir"/>
                    <relationship value="before-end"/>
                </relatedAction>
            </action>
            <action>
                <id value="xor_ct_mri"/>
                <groupingBehavior value="logical-group"/>
                <selectionBehavior value="exactly-one"/>
                <participant>
                    <actor value="radiologie"/>
                </participant>
                <relatedAction>
                    <actionId value="par_szinitigraphie_xor_ct_mir"/>
                    <relationship value="before-end"/>
                </relatedAction>
                <action>
                    <id value="ct_durchfuehren"/>
                    <title value="CT durchfuehren"/>
                    <participant>
                        <actor value="radiologie"/>
                    </participant>
                    <!-- nested gatesways not implemented in transformation-->
                    <!-- <relatedAction>-->
                    <!--     <actionId value="xor_ct_mri"/>-->
                    <!--     <relationship value="before-end"/>-->
                    <!-- </relatedAction>-->
                </action>
                <action>
                    <id value="mri_durchfuehren"/>
                    <title value="MRI durchfuehren"/>
                    <participant>
                        <actor value="radiologie"/>
                    </participant>
                    <!-- nested gatesways not implemented in transformation-->
                    <!-- <relatedAction>-->
                    <!--     <actionId value="xor_ct_mri"/>-->
                    <!--     <relationship value="before-end"/>-->
                    <!-- </relatedAction>-->
                </action>
            </action>
        </action>

    </action>
    <action>
        <id value="laborbefunde_erheben"/>
        <title value="Laborbefunde erheben"/>
        <participant>
            <actor value="labor"/>
        </participant>
        <relatedAction>
            <actionId value="konservative_therapie_and_no_arthrose_xor"/>
            <relationship value="before-start"/>
        </relatedAction>
        <groupingBehavior value="logical-group"/>
        <selectionBehavior value="all"/>
        <action>
            <id value="blutbild_erheben"/>
            <title value="Blutbild erheben"/>
            <participant>
                <actor value="labor"/>
            </participant>
            <relatedAction>
                <actionId value="laborbefunde_erheben"/>
                <relationship value="before-end"/>
            </relatedAction>
        </action>
        <action>
            <id value="crp_erheben"/>
            <title value="CRP erheben"/>
            <participant>
                <actor value="labor"/>
            </participant>
            <relatedAction>
                <actionId value="laborbefunde_erheben"/>
                <relationship value="before-end"/>
            </relatedAction>
        </action>
        <action>
            <id value="nierenfunktionsparameter_erheben"/>
            <title value="Nierenfunktionsparameter erheben"/>
            <participant>
                <actor value="labor"/>
            </participant>
            <relatedAction>
                <actionId value="laborbefunde_erheben"/>
                <relationship value="before-end"/>
            </relatedAction>
        </action>
        <action>
            <id value="gerinnung_erheben"/>
            <title value="Gerinnung erheben"/>
            <participant>
                <actor value="labor"/>
            </participant>
            <relatedAction>
                <actionId value="laborbefunde_erheben"/>
                <relationship value="before-end"/>
            </relatedAction>
        </action>
    </action>
    <action>
        <id value="konservative_therapie_and_no_arthrose_xor"/>
        <title value=""/>
        <participant>
            <actor value="orthopaedie"/>
        </participant>
        <groupingBehavior value="logical-group"/>
        <selectionBehavior value="exactly-one"/>
        <!--
            Note:
            XOR Sprung ist mit aktuellem Konzept nicht abbildbar -> Unterteilung zwischen konservative Therapie
            und therapieresistente Arthose ist deshalb zusammengefasst zu einem XOR, darüber hinaus fehlt, ob die
            konservative Therapie erfolgreich war.
        -->
        <action>
            <id value="konservative_therapie_veranlassen"/>
            <title value="konservative Therapie veranlassen"/>
            <participant>
                <actor value="orthopaedie"/>
            </participant>
            <relatedAction>
                <actionId value="konservative_therapie_and_no_arthrose_xor"/>
                <relationship value="before-end"/>
            </relatedAction>
            <condition>
                <kind value="applicability"/>
                <expression>
                    <language value="text/cql"/>
                    <expression value="konservative Therapie moeglich und keine therapieresistente Arthose"/>
                </expression>
            </condition>
            <action>
                <id value="konservative_therapie_veranlassen_par"/>
                <groupingBehavior value="logical-group"/>
                <selectionBehavior value="all"/>
                <participant>
                    <actor value="orthopaedie"/>
                </participant>
                <action>
                    <id value="entsprechende_medikation_veranlassen"/>
                    <title value="Entsprechende Medikation veranlassen"/>
                    <relatedAction>
                        <actionId value="konservative_therapie_veranlassen_par"/>
                        <relationship value="before-end"/>
                    </relatedAction>
                    <participant>
                        <actor value="orthopaedie"/>
                    </participant>
                </action>
                <action>
                    <id value="muskelkraeftigung_veranlassen"/>
                    <title value="Muskelkraeftigung veranlassen"/>
                    <relatedAction>
                        <actionId value="konservative_therapie_veranlassen_par"/>
                        <relationship value="before-end"/>
                    </relatedAction>
                    <participant>
                        <actor value="orthopaedie"/>
                    </participant>
                </action>
                <action>
                    <id value="beinachsentraining_veranlassen"/>
                    <title value="Beinachsentraining veranlassen"/>
                    <relatedAction>
                        <actionId value="konservative_therapie_veranlassen_par"/>
                        <relationship value="before-end"/>
                    </relatedAction>
                    <participant>
                        <actor value="orthopaedie"/>
                    </participant>
                </action>
                <action>
                    <id value="patellazentrierung_durchfuehren"/>
                    <title value="Patellazentrierung durchfuehren"/>
                    <relatedAction>
                        <actionId value="konservative_therapie_veranlassen_par"/>
                        <relationship value="before-end"/>
                    </relatedAction>
                    <participant>
                        <actor value="orthopaedie"/>
                    </participant>
                </action>
                <action>
                    <id value="gewichtsreduktion_des_patienten_veranlassen"/>
                    <title value="Gewichtsreduktion des Patienten veranlassen"/>
                    <relatedAction>
                        <actionId value="konservative_therapie_veranlassen_par"/>
                        <relationship value="before-end"/>
                    </relatedAction>
                    <participant>
                        <actor value="orthopaedie"/>
                    </participant>
                </action>
            </action>
        </action>
        <action>
            <!-- Grouping Action: Diese bildet den anderen Bereich im XOR ab -->
            <!-- Note: Dies ergibt einen Subprozess wodurch die Participants keine wirklich mehr "haben". -->
            <id value="operation_grouping"/>
            <title value=""/>
            <participant>
                <actor value="orthopaedie"/>
            </participant>
            <relatedAction>
                <actionId value="konservative_therapie_and_no_arthrose_xor"/>
                <relationship value="before-end"/>
            </relatedAction>
            <condition>
                <kind value="applicability"/>
                <expression>
                    <language value="text/cql"/>
                    <expression value="else"/>
                </expression>
            </condition>
            <action>
                <id value="operations_indikation_erstellen"/>
                <title value="Operations indikation erstellen"/>
                <participant>
                    <actor value="orthopaedie"/>
                </participant>
                <relatedAction>
                    <actionId value="aufnahme_des_patienten"/>
                    <relationship value="before-start"/>
                </relatedAction>
            </action>
            <action>
                <id value="aufnahme_des_patienten"/>
                <title value="Aufnahme des Patienten"/>
                <participant>
                    <actor value="aufnahmemangement"/>
                </participant>
                <relatedAction>
                    <actionId value="einwilligung_des_patienten_xor"/>
                    <relationship value="before-start"/>
                </relatedAction>
                <output>
                    <id value="data_operationseinwilligung"/>
                    <name value="Operationseinwilligung"/>
                    <dataRequirement>
                        <type value="Operationseinwilligung"/>
                    </dataRequirement>
                </output>
                <action>
                    <id value="patient_administrativ_aufnehmen"/>
                    <title value="Patient administrativ aufnehmen"/>
                    <participant>
                        <actor value="aufnahmemangement"/>
                    </participant>
                    <relatedAction>
                        <actionId value="pflegerische_aufnahme_veranlassen"/>
                        <relationship value="before-start"/>
                    </relatedAction>
                </action>
                <action>
                    <id value="pflegerische_aufnahme_veranlassen"/>
                    <title value="Pflegerische Aufnahme veranlassen"/>
                    <participant>
                        <actor value="aufnahmemangement"/>
                    </participant>
                    <relatedAction>
                        <actionId value="medizinische_aufnahme_veranlassen"/>
                        <relationship value="before-start"/>
                    </relatedAction>
                </action>
                <action>
                    <id value="medizinische_aufnahme_veranlassen"/>
                    <title value="Medizinische Aufnahme veranlassen"/>
                    <participant>
                        <actor value="aufnahmemangement"/>
                    </participant>
                    <relatedAction>
                        <actionId value="op_aufklaerung_durchfuehren"/>
                        <relationship value="before-start"/>
                    </relatedAction>
                </action>
                <action>
                    <id value="op_aufklaerung_durchfuehren"/>
                    <title value="OP-Aufklaerung durchfuehren"/>
                    <participant>
                        <actor value="aufnahmemangement"/>
                    </participant>
                    <relatedAction>
                        <actionId value="einwilligung_des_patienten_einholen"/>
                        <relationship value="before-start"/>
                    </relatedAction>
                </action>
                <action>
                    <id value="einwilligung_des_patienten_einholen"/>
                    <title value="Einwilligung des Patienten einholen"/>
                    <participant>
                        <actor value="aufnahmemangement"/>
                    </participant>
                    <output>
                        <id value="data_operationseinwilligung_2"/>
                        <name value="Operationseinwilligung"/>
                        <dataRequirement>
                            <type value="Operationseinwilligung"/>
                        </dataRequirement>
                    </output>
                </action>
            </action>
            <action>
                <id value="einwilligung_des_patienten_xor"/>
                <title value="Einwilligung des Patienten erhalten?"/>
                <participant>
                    <actor value="aufnahmemangement"/>
                </participant>
                <groupingBehavior value="logical-group"/>
                <selectionBehavior value="exactly-one"/>
                <action>
                    <id value="keine_operation_durchfuehren_1"/>
                    <title value="Keine Operation durchfuehren"/>
                    <participant>
                        <actor value="entlassungsmanagement"/>
                    </participant>
                    <relatedAction>
                        <actionId value="einwilligung_des_patienten_xor"/>
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
                    <id value="patient_einwilligung_erhalten_group"/>
                    <title value=""/>
                    <participant>
                        <actor value="aufnahmemangement"/>
                    </participant>
                    <relatedAction>
                        <actionId value="einwilligung_des_patienten_xor"/>
                        <relationship value="before-end"/>
                    </relatedAction>
                    <condition>
                        <kind value="applicability"/>
                        <expression>
                            <language value="text/cql"/>
                            <expression value="ja"/>
                        </expression>
                    </condition>
                    <action>
                        <id value="bb_crp_par"/>
                        <title value=""/>
                        <participant>
                            <actor value="labor"/>
                        </participant>
                        <groupingBehavior value="logical-group"/>
                        <selectionBehavior value="all"/>
                        <!-- There is a transformation error, we do not process a logical-group inside a logical-group correctly. -->
                        <action>
                            <id value="bb_aelter_als_7_tage_xor"/>
                            <title value="BB aelter als 7 Tage?"/>
                            <participant>
                                <actor value="labor"/>
                            </participant>
                            <groupingBehavior value="logical-group"/>
                            <selectionBehavior value="at-most-one"/>
                            <!-- bb älter als 7 tage xor -->
                            <relatedAction>
                                <actionId value="bb_crp_par"/>
                                <relationship value="before-end"/>
                            </relatedAction>
                            <action>
                                <id value="bb_erheben"/>
                                <title value="BB erheben"/>
                                <!-- This would be correct, but needs to be commented due to the transformation error state above. -->
<!--                                <relatedAction>-->
<!--                                    <actionId value="bb_aelter_als_7_tage_xor"/>-->
<!--                                    <relationship value="before-end"/>-->
<!--                                </relatedAction>-->
                                <condition>
                                    <kind value="applicability"/>
                                    <expression>
                                        <language value="text/cql"/>
                                        <expression value="ja"/>
                                    </expression>
                                </condition>
                                <participant>
                                    <actor value="labor"/>
                                </participant>
                            </action>
                        </action>
                        <!-- There is a transformation error, we do not process a logical-group inside a logical-group correctly. -->
                        <action>
                            <id value="cpr_aelter_als_7_tage_xor"/>
                            <title value="CPR aelter als 7 Tage?"/>
                            <participant>
                                <actor value="labor"/>
                            </participant>
                            <groupingBehavior value="logical-group"/>
                            <selectionBehavior value="at-most-one"/>
                            <!-- bb älter als 7 tage xor -->
                            <relatedAction>
                                <actionId value="bb_crp_par"/>
                                <relationship value="before-end"/>
                            </relatedAction>
                            <action>
                                <id value="cpr_erheben"/>
                                <title value="CPR erheben"/>
                                <!-- This would be correct, but needs to be commented due to the transformation error state above. -->
                                <!-- <relatedAction>-->
                                <!--     <actionId value="cpr_aelter_als_7_tage_xor"/>-->
                                <!--     <relationship value="before-end"/>-->
                                <!-- </relatedAction>-->
                                <condition>
                                    <kind value="applicability"/>
                                    <expression>
                                        <language value="text/cql"/>
                                        <expression value="ja"/>
                                    </expression>
                                </condition>
                                <participant>
                                    <actor value="labor"/>
                                </participant>
                            </action>
                        </action>
                        <relatedAction>
                            <actionId value="kugelroentgen_durchfuehren" />
                            <relationship value="before-start" />
                        </relatedAction>
                    </action>
                    <action>
                        <id value="kugelroentgen_durchfuehren"/>
                        <title value="Kugelroentgen durchfuehren"/>
                        <participant>
                            <actor value="radiologie"/>
                        </participant>
                        <relatedAction>
                            <actionId value="operationsfreigabe_moeglich_xor"/>
                            <relationship value="before-start"/>
                        </relatedAction>
                    </action>
                    <action>
                        <id value="operationsfreigabe_moeglich_xor"/>
                        <title value="Operationsfreigabe moeglich?"/>
                        <participant>
                            <actor value="anaesthesie"/>
                        </participant>
                        <groupingBehavior value="logical-group"/>
                        <selectionBehavior value="exactly-one"/>
                        <action>
                            <id value="keine_operation_durchfuehren"/>
                            <title value="Keine Operation durchfuehren"/>
                            <relatedAction>
                                <actionId value="operationsfreigabe_moeglich_xor"/>
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
                            <id value="operation_freigeben_und_durchfuehren_grouping"/>
                            <participant>
                                <actor value="anaesthesie"/>
                            </participant>
                            <relatedAction>
                                <actionId value="operationsfreigabe_moeglich_xor"/>
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
                                <id value="operation_freigeben"/>
                                <title value="Operation freigeben"/>
                                <participant>
                                    <actor value="anaesthesie"/>
                                </participant>
                                <relatedAction>
                                    <actionId value="operation_durchfuehren"/>
                                    <relationship value="before-start"/>
                                </relatedAction>
                            </action>
                            <action>
                                <id value="operation_durchfuehren"/>
                                <title value="Operation durchfuehren"/>
                                <participant>
                                    <actor value="chirurgie"/>
                                </participant>
                                <relatedAction>
                                    <actionId value="hueftvergleich_durchfuehren"/>
                                    <relationship value="before-start"/>
                                </relatedAction>
                            </action>
                            <action>
                                <id value="hueftvergleich_durchfuehren"/>
                                <title value="Hueftvergleich durchfuehren"/>
                                <participant>
                                    <actor value="radiologie"/>
                                </participant>
                                <trigger>
                                    <type value="periodic"/>
                                    <name value="erster_post_op_tag"/>
                                    <timingTiming>
                                        <repeat>
                                            <frequency value="1"/>
                                            <periodUnit value="d"/>
                                            <count value="1"/>
                                        </repeat>
                                    </timingTiming>
                                </trigger>
                                <relatedAction>
                                    <actionId value="postoperative_laborbefundung_2"/>
                                    <relationship value="before-start"/>
                                </relatedAction>
                            </action>
                            <action>
                                <id value="postoperative_laborbefundung_2"/>
                                <title value="postoperative Laborbefunde 2 Tag post Op erhaben"/>
                                <participant>
                                    <actor value="labor"/>
                                </participant>
                                <trigger>
                                    <type value="periodic"/>
                                    <name value="zweiter_post_op_tag"/>
                                    <timingTiming>
                                        <repeat>
                                            <frequency value="2"/>
                                            <periodUnit value="d"/>
                                            <count value="1"/>
                                        </repeat>
                                    </timingTiming>
                                </trigger>
                                <relatedAction>
                                    <actionId value="postoperative_laborbefundung_4"/>
                                    <relationship value="before-start"/>
                                </relatedAction>
                            </action>
                            <action>
                                <id value="postoperative_laborbefundung_4"/>
                                <title value="postoperative Laborbefunde 4. Tag postOp erheben"/>
                                <participant>
                                    <actor value="labor"/>
                                </participant>
                                <trigger>
                                    <type value="periodic"/>
                                    <name value="vierter_post_op_tag"/>
                                    <timingTiming>
                                        <repeat>
                                            <frequency value="4"/>
                                            <periodUnit value="d"/>
                                            <count value="1"/>
                                        </repeat>
                                    </timingTiming>
                                </trigger>
                                <relatedAction>
                                    <actionId value="weitere_laborbefunde_notwendig_xor"/>
                                    <relationship value="before-start"/>
                                </relatedAction>
                            </action>
                            <action>
                                <id value="weitere_laborbefunde_notwendig_xor"/>
                                <title value="weitere Laborbefunde notwendig?"/>
                                <groupingBehavior value="logical-group"/>
                                <selectionBehavior value="at-most-one"/>
                                <participant>
                                    <actor value="labor"/>
                                </participant>
                                <action>
                                    <id value="weitere_post_op_laborbefunde_erheben"/>
                                    <title value="weitere postOP Laborbefunde erheben"/>
                                    <participant>
                                        <actor value="labor"/>
                                    </participant>
                                    <relatedAction>
                                        <actionId value="weitere_laborbefunde_notwendig_xor"/>
                                        <relationship value="before-end"/>
                                    </relatedAction>
                                </action>
                                <relatedAction>
                                    <actionId value="durchleuchtungsgezielte_lauensteinaufnahme_durchfuehren"/>
                                    <relationship value="before-start"/>
                                </relatedAction>
                            </action>
                            <action>
                                <id value="durchleuchtungsgezielte_lauensteinaufnahme_durchfuehren"/>
                                <title value="Durchleuchtungsgezielte Lauensteinaufnahme durchfuehren"/>
                                <participant>
                                    <actor value="radiologie"/>
                                </participant>
                            </action>
                        </action>
                    </action>
                </action>
            </action>
        </action>
        <relatedAction>
            <actionId value="entlassung_nach_hause_moeglich"/>
            <relationship value="before-start"/>
        </relatedAction>
    </action>
    <action>
        <id value="entlassung_nach_hause_moeglich"/>
        <title value="Entlassung nach Hause möglich?"/>
        <participant>
            <actor value="entlassungsmanagement"/>
        </participant>
        <groupingBehavior value="logical-group"/>
        <selectionBehavior value="exactly-one"/>
        <action>
            <id value="patient_wird_entlassen"/>
            <title value="Patient wird entlassen"/>
            <participant>
                <actor value="entlassungsmanagement"/>
            </participant>
            <relatedAction>
                <actionId value="entlassung_nach_hause_moeglich"/>
                <relationship value="before-end"/>
            </relatedAction>
            <condition>
                <kind value="applicability"/>
                <expression>
                    <language value="text/cql"/>
                    <expression value="Ja"/>
                </expression>
            </condition>
            <output>
                <id value="data_entlassungspapiere"/>
                <name value="Entlassungspapiere"/>
                <dataRequirement>
                    <type value="Entlassungspapiere"/>
                </dataRequirement>
            </output>
        </action>
        <action>
            <id value="patient_wird_nicht_entlassen_grouping"/>
            <title value=""/>
            <participant>
                <actor value="entlassungsmanagement"/>
            </participant>
            <relatedAction>
                <actionId value="entlassung_nach_hause_moeglich"/>
                <relationship value="before-end"/>
            </relatedAction>
            <condition>
                <kind value="applicability"/>
                <expression>
                    <language value="text/cql"/>
                    <expression value="Nein"/>
                </expression>
            </condition>
            <action>
                <id value="anschlussheilverfahren_einleiten"/>
                <title value="Anschlussheilverfahren einleiten"/>
                <participant>
                    <actor value="entlassungsmanagement"/>
                </participant>
                <relatedAction>
                    <actionId value="patient_in_remob_abteilung_verlegen_xor"/>
                    <relationship value="before-start"/>
                </relatedAction>
            </action>
            <action>
                <id value="patient_in_remob_abteilung_verlegen_xor"/>
                <title value="Patient in Remob-Abteilung verlegen?"/>
                <groupingBehavior value="logical-group"/>
                <selectionBehavior value="exactly-one"/>
                <participant>
                    <actor value="entlassungsmanagement"/>
                </participant>
                <relatedAction>
                    <actionId value="post_op_genesungsprozess_erfolgreich"/>
                    <relationship value="before-start"/>
                </relatedAction>
                <action>
                    <id value="patient_in_remobilisierungsabteilung_verlegen"/>
                    <title value="Patient in Remobilisierungsabteilung verlegen"/>
                    <participant>
                        <actor value="entlassungsmanagement"/>
                    </participant>
                    <relatedAction>
                        <actionId value="patient_in_remob_abteilung_verlegen_xor"/>
                        <relationship value="before-end"/>
                    </relatedAction>
                    <condition>
                        <kind value="applicability"/>
                        <expression>
                            <language value="text/cql"/>
                            <expression value="Ja"/>
                        </expression>
                    </condition>
                </action>
                <action>
                    <id value="patient_in_andere_abteilung_verlegen"/>
                    <title value="Patient in andere Abteilung verlegen"/>
                    <participant>
                        <actor value="entlassungsmanagement"/>
                    </participant>
                    <relatedAction>
                        <actionId value="patient_in_remob_abteilung_verlegen_xor"/>
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
            </action>
            <action>
                <id value="post_op_genesungsprozess_erfolgreich"/>
                <title value="post OP Genesungsprozess erfolgreich?"/>
                <groupingBehavior value="logical-group"/>
                <selectionBehavior value="at-most-one"/>
                <participant>
                    <actor value="entlassungsmanagement"/>
                </participant>
                <!-- Note: This action needs to be copied, because we cannot link to the other one. -->
                <action>
                    <id value="patient_wird_entlassen_2"/>
                    <title value="Patient wird entlassen"/>
                    <participant>
                        <actor value="entlassungsmanagement"/>
                    </participant>
                    <relatedAction>
                        <actionId value="post_op_genesungsprozess_erfolgreich"/>
                        <relationship value="before-end"/>
                    </relatedAction>
                    <condition>
                        <kind value="applicability"/>
                        <expression>
                            <language value="text/cql"/>
                            <expression value="Ja"/>
                        </expression>
                    </condition>
                    <output>
                        <id value="data_entlassungspapiere_2"/>
                        <name value="Entlassungspapiere"/>
                        <dataRequirement>
                            <type value="Entlassungspapiere"/>
                        </dataRequirement>
                    </output>
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
    <process isExecutable="false" name="HTEP" id="HTEP">
        <laneSet>
            <lane name="Entlassungsmanagement" id="id_entlassungsmanagement">
                <flowNodeRef>id_end85</flowNodeRef>
                <flowNodeRef>id_entlassung_nach_hause_moeglich</flowNodeRef>
                <flowNodeRef>id_patient_wird_entlassen</flowNodeRef>
                <flowNodeRef>id_patient_wird_nicht_entlassen_grouping</flowNodeRef>
            </lane>
            <lane name="Labor" id="id_labor">
                <flowNodeRef>id_nierenfunktionsparameter_erheben</flowNodeRef>
                <flowNodeRef>id_crp_erheben</flowNodeRef>
                <flowNodeRef>id_blutbild_erheben</flowNodeRef>
                <flowNodeRef>id_gerinnung_erheben</flowNodeRef>
                <flowNodeRef>id_laborbefunde_erheben</flowNodeRef>
            </lane>
            <lane name="Radiologie" id="id_radiologie">
                <flowNodeRef>id_radiologische_befunde_erheben</flowNodeRef>
            </lane>
            <lane name="Orthopaedie" id="id_orthopaedie">
                <flowNodeRef>id_orthopaedische_anamnese_durchfuehren</flowNodeRef>
                <flowNodeRef>id_start84</flowNodeRef>
                <flowNodeRef>id_konservative_therapie_veranlassen</flowNodeRef>
                <flowNodeRef>id_klinische_untersuchung</flowNodeRef>
                <flowNodeRef>id_operation_grouping</flowNodeRef>
                <flowNodeRef>id_konservative_therapie_and_no_arthrose_xor</flowNodeRef>
            </lane>
        </laneSet>
        <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start84">
            <outgoing>sf_start84_orthopaedische_anamnese_durchfuehren</outgoing>
        </startEvent>
        <sequenceFlow sourceRef="id_start84" targetRef="id_orthopaedische_anamnese_durchfuehren" id="sf_start84_orthopaedische_anamnese_durchfuehren"/>
        <userTask name="Orthopaedische Anamnese durchfuehren" id="id_orthopaedische_anamnese_durchfuehren">
            <incoming>sf_start84_orthopaedische_anamnese_durchfuehren</incoming>
            <outgoing>sf_orthopaedische_anamnese_durchfuehren_klinische_untersuchung</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_orthopaedische_anamnese_durchfuehren" targetRef="id_klinische_untersuchung" id="sf_orthopaedische_anamnese_durchfuehren_klinische_untersuchung"/>
        <userTask name="Klinische Untersuchung entsprechend HHS durchfuehren" id="id_klinische_untersuchung">
            <incoming>sf_orthopaedische_anamnese_durchfuehren_klinische_untersuchung</incoming>
            <outgoing>sf_klinische_untersuchung_radiologische_befunde_erheben</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_klinische_untersuchung" targetRef="id_radiologische_befunde_erheben" id="sf_klinische_untersuchung_radiologische_befunde_erheben"/>
        <subProcess name="radiologische Befunde erheben" id="id_radiologische_befunde_erheben">
            <incoming>sf_klinische_untersuchung_radiologische_befunde_erheben</incoming>
            <outgoing>sf_radiologische_befunde_erheben_laborbefunde_erheben</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start86">
                <outgoing>sf_start86_standardroentgen_durchfuehren</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start86" targetRef="id_standardroentgen_durchfuehren" id="sf_start86_standardroentgen_durchfuehren"/>
            <userTask name="Standardroentgen durchfuehren" id="id_standardroentgen_durchfuehren">
                <incoming>sf_start86_standardroentgen_durchfuehren</incoming>
                <outgoing>sf_standardroentgen_durchfuehren_ganzbeinroentgen_durchfuehren</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_standardroentgen_durchfuehren" targetRef="id_ganzbeinroentgen_durchfuehren" id="sf_standardroentgen_durchfuehren_ganzbeinroentgen_durchfuehren"/>
            <userTask name="Ganzbeingoentgen durchfuehren" id="id_ganzbeinroentgen_durchfuehren">
                <incoming>sf_standardroentgen_durchfuehren_ganzbeinroentgen_durchfuehren</incoming>
                <outgoing>sf_ganzbeinroentgen_durchfuehren_par_szinitigraphie_xor_ct_mir</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_ganzbeinroentgen_durchfuehren" targetRef="id_par_szinitigraphie_xor_ct_mir" id="sf_ganzbeinroentgen_durchfuehren_par_szinitigraphie_xor_ct_mir"/>
            <parallelGateway id="id_par_szinitigraphie_xor_ct_mir">
                <incoming>sf_ganzbeinroentgen_durchfuehren_par_szinitigraphie_xor_ct_mir</incoming>
                <outgoing>sf_par_szinitigraphie_xor_ct_mir_xor_ct_mri</outgoing>
                <outgoing>sf_par_szinitigraphie_xor_ct_mir_szintigraphie_durchfuehren</outgoing>
            </parallelGateway>
            <sequenceFlow sourceRef="id_par_szinitigraphie_xor_ct_mir" targetRef="id_xor_ct_mri" id="sf_par_szinitigraphie_xor_ct_mir_xor_ct_mri"/>
            <sequenceFlow sourceRef="id_par_szinitigraphie_xor_ct_mir" targetRef="id_szintigraphie_durchfuehren" id="sf_par_szinitigraphie_xor_ct_mir_szintigraphie_durchfuehren"/>
            <subProcess id="id_xor_ct_mri">
                <incoming>sf_par_szinitigraphie_xor_ct_mir_xor_ct_mri</incoming>
                <outgoing>sf_xor_ct_mri_join_of_par_szinitigraphie_xor_ct_mir</outgoing>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start88">
                    <outgoing>sf_start88_ct_durchfuehren</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start88" targetRef="id_ct_durchfuehren" id="sf_start88_ct_durchfuehren"/>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start90">
                    <outgoing>sf_start90_mri_durchfuehren</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start90" targetRef="id_mri_durchfuehren" id="sf_start90_mri_durchfuehren"/>
                <userTask name="CT durchfuehren" id="id_ct_durchfuehren">
                    <incoming>sf_start88_ct_durchfuehren</incoming>
                    <outgoing>sf_ct_durchfuehren_end89</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_ct_durchfuehren" targetRef="id_end89" id="sf_ct_durchfuehren_end89"/>
                <userTask name="MRI durchfuehren" id="id_mri_durchfuehren">
                    <incoming>sf_start90_mri_durchfuehren</incoming>
                    <outgoing>sf_mri_durchfuehren_end91</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_mri_durchfuehren" targetRef="id_end91" id="sf_mri_durchfuehren_end91"/>
                <endEvent name="end" id="id_end89">
                    <incoming>sf_ct_durchfuehren_end89</incoming>
                </endEvent>
                <endEvent name="end" id="id_end91">
                    <incoming>sf_mri_durchfuehren_end91</incoming>
                </endEvent>
            </subProcess>
            <sequenceFlow sourceRef="id_xor_ct_mri" targetRef="id_join_of_par_szinitigraphie_xor_ct_mir" id="sf_xor_ct_mri_join_of_par_szinitigraphie_xor_ct_mir"/>
            <userTask name="Szintigraphie durchfuehren" id="id_szintigraphie_durchfuehren">
                <incoming>sf_par_szinitigraphie_xor_ct_mir_szintigraphie_durchfuehren</incoming>
                <outgoing>sf_szintigraphie_durchfuehren_join_of_par_szinitigraphie_xor_ct_mir</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_szintigraphie_durchfuehren" targetRef="id_join_of_par_szinitigraphie_xor_ct_mir" id="sf_szintigraphie_durchfuehren_join_of_par_szinitigraphie_xor_ct_mir"/>
            <parallelGateway name="join" id="id_join_of_par_szinitigraphie_xor_ct_mir">
                <incoming>sf_szintigraphie_durchfuehren_join_of_par_szinitigraphie_xor_ct_mir</incoming>
                <incoming>sf_xor_ct_mri_join_of_par_szinitigraphie_xor_ct_mir</incoming>
                <outgoing>sf_join_of_par_szinitigraphie_xor_ct_mir_end87</outgoing>
            </parallelGateway>
            <sequenceFlow sourceRef="id_join_of_par_szinitigraphie_xor_ct_mir" targetRef="id_end87" id="sf_join_of_par_szinitigraphie_xor_ct_mir_end87"/>
            <endEvent name="end" id="id_end87">
                <incoming>sf_join_of_par_szinitigraphie_xor_ct_mir_end87</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_radiologische_befunde_erheben" targetRef="id_laborbefunde_erheben" id="sf_radiologische_befunde_erheben_laborbefunde_erheben"/>
        <parallelGateway name="Laborbefunde erheben" id="id_laborbefunde_erheben">
            <incoming>sf_radiologische_befunde_erheben_laborbefunde_erheben</incoming>
            <outgoing>sf_laborbefunde_erheben_nierenfunktionsparameter_erheben</outgoing>
            <outgoing>sf_laborbefunde_erheben_gerinnung_erheben</outgoing>
            <outgoing>sf_laborbefunde_erheben_crp_erheben</outgoing>
            <outgoing>sf_laborbefunde_erheben_blutbild_erheben</outgoing>
        </parallelGateway>
        <sequenceFlow sourceRef="id_laborbefunde_erheben" targetRef="id_nierenfunktionsparameter_erheben" id="sf_laborbefunde_erheben_nierenfunktionsparameter_erheben"/>
        <sequenceFlow sourceRef="id_laborbefunde_erheben" targetRef="id_gerinnung_erheben" id="sf_laborbefunde_erheben_gerinnung_erheben"/>
        <sequenceFlow sourceRef="id_laborbefunde_erheben" targetRef="id_crp_erheben" id="sf_laborbefunde_erheben_crp_erheben"/>
        <sequenceFlow sourceRef="id_laborbefunde_erheben" targetRef="id_blutbild_erheben" id="sf_laborbefunde_erheben_blutbild_erheben"/>
        <userTask name="Nierenfunktionsparameter erheben" id="id_nierenfunktionsparameter_erheben">
            <incoming>sf_laborbefunde_erheben_nierenfunktionsparameter_erheben</incoming>
            <outgoing>sf_nierenfunktionsparameter_erheben_join_of_laborbefunde_erheben</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_nierenfunktionsparameter_erheben" targetRef="id_join_of_laborbefunde_erheben" id="sf_nierenfunktionsparameter_erheben_join_of_laborbefunde_erheben"/>
        <userTask name="Gerinnung erheben" id="id_gerinnung_erheben">
            <incoming>sf_laborbefunde_erheben_gerinnung_erheben</incoming>
            <outgoing>sf_gerinnung_erheben_join_of_laborbefunde_erheben</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_gerinnung_erheben" targetRef="id_join_of_laborbefunde_erheben" id="sf_gerinnung_erheben_join_of_laborbefunde_erheben"/>
        <userTask name="CRP erheben" id="id_crp_erheben">
            <incoming>sf_laborbefunde_erheben_crp_erheben</incoming>
            <outgoing>sf_crp_erheben_join_of_laborbefunde_erheben</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_crp_erheben" targetRef="id_join_of_laborbefunde_erheben" id="sf_crp_erheben_join_of_laborbefunde_erheben"/>
        <userTask name="Blutbild erheben" id="id_blutbild_erheben">
            <incoming>sf_laborbefunde_erheben_blutbild_erheben</incoming>
            <outgoing>sf_blutbild_erheben_join_of_laborbefunde_erheben</outgoing>
        </userTask>
        <sequenceFlow sourceRef="id_blutbild_erheben" targetRef="id_join_of_laborbefunde_erheben" id="sf_blutbild_erheben_join_of_laborbefunde_erheben"/>
        <parallelGateway name="join" id="id_join_of_laborbefunde_erheben">
            <incoming>sf_gerinnung_erheben_join_of_laborbefunde_erheben</incoming>
            <incoming>sf_crp_erheben_join_of_laborbefunde_erheben</incoming>
            <incoming>sf_blutbild_erheben_join_of_laborbefunde_erheben</incoming>
            <incoming>sf_nierenfunktionsparameter_erheben_join_of_laborbefunde_erheben</incoming>
            <outgoing>sf_join_of_laborbefunde_erheben_konservative_therapie_and_no_arthrose_xor</outgoing>
        </parallelGateway>
        <sequenceFlow sourceRef="id_join_of_laborbefunde_erheben" targetRef="id_konservative_therapie_and_no_arthrose_xor" id="sf_join_of_laborbefunde_erheben_konservative_therapie_and_no_arthrose_xor"/>
        <exclusiveGateway id="id_konservative_therapie_and_no_arthrose_xor">
            <incoming>sf_join_of_laborbefunde_erheben_konservative_therapie_and_no_arthrose_xor</incoming>
            <outgoing>sf_konservative_therapie_and_no_arthrose_xor_konservative_therapie_veranlassen</outgoing>
            <outgoing>sf_konservative_therapie_and_no_arthrose_xor_operation_grouping</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_konservative_therapie_and_no_arthrose_xor" targetRef="id_konservative_therapie_veranlassen" name="konservative Therapie moeglich und keine therapieresistente Arthose" id="sf_konservative_therapie_and_no_arthrose_xor_konservative_therapie_veranlassen">
            <conditionExpression id="sf_konservative_therapie_and_no_arthrose_xor_konservative_therapie_veranlassen_condition">konservative Therapie moeglich und keine therapieresistente Arthose</conditionExpression>
        </sequenceFlow>
        <sequenceFlow sourceRef="id_konservative_therapie_and_no_arthrose_xor" targetRef="id_operation_grouping" name="else" id="sf_konservative_therapie_and_no_arthrose_xor_operation_grouping">
            <conditionExpression id="sf_konservative_therapie_and_no_arthrose_xor_operation_grouping_condition">else</conditionExpression>
        </sequenceFlow>
        <subProcess name="konservative Therapie veranlassen" id="id_konservative_therapie_veranlassen">
            <incoming>sf_konservative_therapie_and_no_arthrose_xor_konservative_therapie_veranlassen</incoming>
            <outgoing>sf_konservative_therapie_veranlassen_join_of_konservative_therapie_and_no_arthrose_xor</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start92">
                <outgoing>sf_start92_konservative_therapie_veranlassen_par</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start92" targetRef="id_konservative_therapie_veranlassen_par" id="sf_start92_konservative_therapie_veranlassen_par"/>
            <parallelGateway id="id_konservative_therapie_veranlassen_par">
                <incoming>sf_start92_konservative_therapie_veranlassen_par</incoming>
                <outgoing>sf_konservative_therapie_veranlassen_par_beinachsentraining_veranlassen</outgoing>
                <outgoing>sf_konservative_therapie_veranlassen_par_gewichtsreduktion_des_patienten_veranlassen</outgoing>
                <outgoing>sf_konservative_therapie_veranlassen_par_entsprechende_medikation_veranlassen</outgoing>
                <outgoing>sf_konservative_therapie_veranlassen_par_patellazentrierung_durchfuehren</outgoing>
                <outgoing>sf_konservative_therapie_veranlassen_par_muskelkraeftigung_veranlassen</outgoing>
            </parallelGateway>
            <sequenceFlow sourceRef="id_konservative_therapie_veranlassen_par" targetRef="id_beinachsentraining_veranlassen" id="sf_konservative_therapie_veranlassen_par_beinachsentraining_veranlassen"/>
            <sequenceFlow sourceRef="id_konservative_therapie_veranlassen_par" targetRef="id_gewichtsreduktion_des_patienten_veranlassen" id="sf_konservative_therapie_veranlassen_par_gewichtsreduktion_des_patienten_veranlassen"/>
            <sequenceFlow sourceRef="id_konservative_therapie_veranlassen_par" targetRef="id_entsprechende_medikation_veranlassen" id="sf_konservative_therapie_veranlassen_par_entsprechende_medikation_veranlassen"/>
            <sequenceFlow sourceRef="id_konservative_therapie_veranlassen_par" targetRef="id_patellazentrierung_durchfuehren" id="sf_konservative_therapie_veranlassen_par_patellazentrierung_durchfuehren"/>
            <sequenceFlow sourceRef="id_konservative_therapie_veranlassen_par" targetRef="id_muskelkraeftigung_veranlassen" id="sf_konservative_therapie_veranlassen_par_muskelkraeftigung_veranlassen"/>
            <userTask name="Beinachsentraining veranlassen" id="id_beinachsentraining_veranlassen">
                <incoming>sf_konservative_therapie_veranlassen_par_beinachsentraining_veranlassen</incoming>
                <outgoing>sf_beinachsentraining_veranlassen_join_of_konservative_therapie_veranlassen_par</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_beinachsentraining_veranlassen" targetRef="id_join_of_konservative_therapie_veranlassen_par" id="sf_beinachsentraining_veranlassen_join_of_konservative_therapie_veranlassen_par"/>
            <userTask name="Gewichtsreduktion des Patienten veranlassen" id="id_gewichtsreduktion_des_patienten_veranlassen">
                <incoming>sf_konservative_therapie_veranlassen_par_gewichtsreduktion_des_patienten_veranlassen</incoming>
                <outgoing>sf_gewichtsreduktion_des_patienten_veranlassen_join_of_konservative_therapie_veranlassen_par</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_gewichtsreduktion_des_patienten_veranlassen" targetRef="id_join_of_konservative_therapie_veranlassen_par" id="sf_gewichtsreduktion_des_patienten_veranlassen_join_of_konservative_therapie_veranlassen_par"/>
            <userTask name="Entsprechende Medikation veranlassen" id="id_entsprechende_medikation_veranlassen">
                <incoming>sf_konservative_therapie_veranlassen_par_entsprechende_medikation_veranlassen</incoming>
                <outgoing>sf_entsprechende_medikation_veranlassen_join_of_konservative_therapie_veranlassen_par</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_entsprechende_medikation_veranlassen" targetRef="id_join_of_konservative_therapie_veranlassen_par" id="sf_entsprechende_medikation_veranlassen_join_of_konservative_therapie_veranlassen_par"/>
            <userTask name="Patellazentrierung durchfuehren" id="id_patellazentrierung_durchfuehren">
                <incoming>sf_konservative_therapie_veranlassen_par_patellazentrierung_durchfuehren</incoming>
                <outgoing>sf_patellazentrierung_durchfuehren_join_of_konservative_therapie_veranlassen_par</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_patellazentrierung_durchfuehren" targetRef="id_join_of_konservative_therapie_veranlassen_par" id="sf_patellazentrierung_durchfuehren_join_of_konservative_therapie_veranlassen_par"/>
            <userTask name="Muskelkraeftigung veranlassen" id="id_muskelkraeftigung_veranlassen">
                <incoming>sf_konservative_therapie_veranlassen_par_muskelkraeftigung_veranlassen</incoming>
                <outgoing>sf_muskelkraeftigung_veranlassen_join_of_konservative_therapie_veranlassen_par</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_muskelkraeftigung_veranlassen" targetRef="id_join_of_konservative_therapie_veranlassen_par" id="sf_muskelkraeftigung_veranlassen_join_of_konservative_therapie_veranlassen_par"/>
            <parallelGateway name="join" id="id_join_of_konservative_therapie_veranlassen_par">
                <incoming>sf_beinachsentraining_veranlassen_join_of_konservative_therapie_veranlassen_par</incoming>
                <incoming>sf_gewichtsreduktion_des_patienten_veranlassen_join_of_konservative_therapie_veranlassen_par</incoming>
                <incoming>sf_patellazentrierung_durchfuehren_join_of_konservative_therapie_veranlassen_par</incoming>
                <incoming>sf_muskelkraeftigung_veranlassen_join_of_konservative_therapie_veranlassen_par</incoming>
                <incoming>sf_entsprechende_medikation_veranlassen_join_of_konservative_therapie_veranlassen_par</incoming>
                <outgoing>sf_join_of_konservative_therapie_veranlassen_par_end93</outgoing>
            </parallelGateway>
            <sequenceFlow sourceRef="id_join_of_konservative_therapie_veranlassen_par" targetRef="id_end93" id="sf_join_of_konservative_therapie_veranlassen_par_end93"/>
            <endEvent name="end" id="id_end93">
                <incoming>sf_join_of_konservative_therapie_veranlassen_par_end93</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_konservative_therapie_veranlassen" targetRef="id_join_of_konservative_therapie_and_no_arthrose_xor" id="sf_konservative_therapie_veranlassen_join_of_konservative_therapie_and_no_arthrose_xor"/>
        <subProcess id="id_operation_grouping">
            <incoming>sf_konservative_therapie_and_no_arthrose_xor_operation_grouping</incoming>
            <outgoing>sf_operation_grouping_join_of_konservative_therapie_and_no_arthrose_xor</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start94">
                <outgoing>sf_start94_operations_indikation_erstellen</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start94" targetRef="id_operations_indikation_erstellen" id="sf_start94_operations_indikation_erstellen"/>
            <userTask name="Operations indikation erstellen" id="id_operations_indikation_erstellen">
                <incoming>sf_start94_operations_indikation_erstellen</incoming>
                <outgoing>sf_operations_indikation_erstellen_aufnahme_des_patienten</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_operations_indikation_erstellen" targetRef="id_aufnahme_des_patienten" id="sf_operations_indikation_erstellen_aufnahme_des_patienten"/>
            <subProcess name="Aufnahme des Patienten" id="id_aufnahme_des_patienten">
                <incoming>sf_operations_indikation_erstellen_aufnahme_des_patienten</incoming>
                <outgoing>sf_aufnahme_des_patienten_einwilligung_des_patienten_xor</outgoing>
                <dataOutputAssociation id="df_id_aufnahme_des_patienten_data_operationseinwilligung">
                    <targetRef>id_data_operationseinwilligung</targetRef>
                </dataOutputAssociation>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start96">
                    <outgoing>sf_start96_patient_administrativ_aufnehmen</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start96" targetRef="id_patient_administrativ_aufnehmen" id="sf_start96_patient_administrativ_aufnehmen"/>
                <userTask name="Patient administrativ aufnehmen" id="id_patient_administrativ_aufnehmen">
                    <incoming>sf_start96_patient_administrativ_aufnehmen</incoming>
                    <outgoing>sf_patient_administrativ_aufnehmen_pflegerische_aufnahme_veranlassen</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_patient_administrativ_aufnehmen" targetRef="id_pflegerische_aufnahme_veranlassen" id="sf_patient_administrativ_aufnehmen_pflegerische_aufnahme_veranlassen"/>
                <userTask name="Pflegerische Aufnahme veranlassen" id="id_pflegerische_aufnahme_veranlassen">
                    <incoming>sf_patient_administrativ_aufnehmen_pflegerische_aufnahme_veranlassen</incoming>
                    <outgoing>sf_pflegerische_aufnahme_veranlassen_medizinische_aufnahme_veranlassen</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_pflegerische_aufnahme_veranlassen" targetRef="id_medizinische_aufnahme_veranlassen" id="sf_pflegerische_aufnahme_veranlassen_medizinische_aufnahme_veranlassen"/>
                <userTask name="Medizinische Aufnahme veranlassen" id="id_medizinische_aufnahme_veranlassen">
                    <incoming>sf_pflegerische_aufnahme_veranlassen_medizinische_aufnahme_veranlassen</incoming>
                    <outgoing>sf_medizinische_aufnahme_veranlassen_op_aufklaerung_durchfuehren</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_medizinische_aufnahme_veranlassen" targetRef="id_op_aufklaerung_durchfuehren" id="sf_medizinische_aufnahme_veranlassen_op_aufklaerung_durchfuehren"/>
                <userTask name="OP-Aufklaerung durchfuehren" id="id_op_aufklaerung_durchfuehren">
                    <incoming>sf_medizinische_aufnahme_veranlassen_op_aufklaerung_durchfuehren</incoming>
                    <outgoing>sf_op_aufklaerung_durchfuehren_einwilligung_des_patienten_einholen</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_op_aufklaerung_durchfuehren" targetRef="id_einwilligung_des_patienten_einholen" id="sf_op_aufklaerung_durchfuehren_einwilligung_des_patienten_einholen"/>
                <userTask name="Einwilligung des Patienten einholen" id="id_einwilligung_des_patienten_einholen">
                    <incoming>sf_op_aufklaerung_durchfuehren_einwilligung_des_patienten_einholen</incoming>
                    <outgoing>sf_einwilligung_des_patienten_einholen_end97</outgoing>
                    <dataOutputAssociation id="df_id_einwilligung_des_patienten_einholen_data_operationseinwilligung_2">
                        <targetRef>id_data_operationseinwilligung_2</targetRef>
                    </dataOutputAssociation>
                </userTask>
                <sequenceFlow sourceRef="id_einwilligung_des_patienten_einholen" targetRef="id_end97" id="sf_einwilligung_des_patienten_einholen_end97"/>
                <dataObjectReference name="Operationseinwilligung" id="id_data_operationseinwilligung_2">
                    <extensionElements>
                        <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">Operationseinwilligung</ns5:type>
                    </extensionElements>
                </dataObjectReference>
                <endEvent name="end" id="id_end97">
                    <incoming>sf_einwilligung_des_patienten_einholen_end97</incoming>
                </endEvent>
            </subProcess>
            <sequenceFlow sourceRef="id_aufnahme_des_patienten" targetRef="id_einwilligung_des_patienten_xor" id="sf_aufnahme_des_patienten_einwilligung_des_patienten_xor"/>
            <dataObjectReference name="Operationseinwilligung" id="id_data_operationseinwilligung">
                <extensionElements>
                    <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">Operationseinwilligung</ns5:type>
                </extensionElements>
            </dataObjectReference>
            <exclusiveGateway name="Einwilligung des Patienten erhalten?" id="id_einwilligung_des_patienten_xor">
                <incoming>sf_aufnahme_des_patienten_einwilligung_des_patienten_xor</incoming>
                <outgoing>sf_einwilligung_des_patienten_xor_patient_einwilligung_erhalten_group</outgoing>
                <outgoing>sf_einwilligung_des_patienten_xor_keine_operation_durchfuehren_1</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_einwilligung_des_patienten_xor" targetRef="id_patient_einwilligung_erhalten_group" name="ja" id="sf_einwilligung_des_patienten_xor_patient_einwilligung_erhalten_group">
                <conditionExpression id="sf_einwilligung_des_patienten_xor_patient_einwilligung_erhalten_group_condition">ja</conditionExpression>
            </sequenceFlow>
            <sequenceFlow sourceRef="id_einwilligung_des_patienten_xor" targetRef="id_keine_operation_durchfuehren_1" name="Nein" id="sf_einwilligung_des_patienten_xor_keine_operation_durchfuehren_1">
                <conditionExpression id="sf_einwilligung_des_patienten_xor_keine_operation_durchfuehren_1_condition">Nein</conditionExpression>
            </sequenceFlow>
            <subProcess id="id_patient_einwilligung_erhalten_group">
                <incoming>sf_einwilligung_des_patienten_xor_patient_einwilligung_erhalten_group</incoming>
                <outgoing>sf_patient_einwilligung_erhalten_group_join_of_einwilligung_des_patienten_xor</outgoing>
                <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start98">
                    <outgoing>sf_start98_bb_crp_par</outgoing>
                </startEvent>
                <sequenceFlow sourceRef="id_start98" targetRef="id_bb_crp_par" id="sf_start98_bb_crp_par"/>
                <parallelGateway id="id_bb_crp_par">
                    <incoming>sf_start98_bb_crp_par</incoming>
                    <outgoing>sf_bb_crp_par_cpr_aelter_als_7_tage_xor</outgoing>
                    <outgoing>sf_bb_crp_par_bb_aelter_als_7_tage_xor</outgoing>
                </parallelGateway>
                <sequenceFlow sourceRef="id_bb_crp_par" targetRef="id_cpr_aelter_als_7_tage_xor" id="sf_bb_crp_par_cpr_aelter_als_7_tage_xor"/>
                <sequenceFlow sourceRef="id_bb_crp_par" targetRef="id_bb_aelter_als_7_tage_xor" id="sf_bb_crp_par_bb_aelter_als_7_tage_xor"/>
                <subProcess name="CPR aelter als 7 Tage?" id="id_cpr_aelter_als_7_tage_xor">
                    <incoming>sf_bb_crp_par_cpr_aelter_als_7_tage_xor</incoming>
                    <outgoing>sf_cpr_aelter_als_7_tage_xor_join_of_bb_crp_par</outgoing>
                    <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start102">
                        <outgoing>sf_start102_cpr_erheben</outgoing>
                    </startEvent>
                    <sequenceFlow sourceRef="id_start102" targetRef="id_cpr_erheben" name="ja" id="sf_start102_cpr_erheben">
                        <conditionExpression id="sf_start102_cpr_erheben_condition">ja</conditionExpression>
                    </sequenceFlow>
                    <userTask name="CPR erheben" id="id_cpr_erheben">
                        <incoming>sf_start102_cpr_erheben</incoming>
                        <outgoing>sf_cpr_erheben_end103</outgoing>
                    </userTask>
                    <sequenceFlow sourceRef="id_cpr_erheben" targetRef="id_end103" id="sf_cpr_erheben_end103"/>
                    <endEvent name="end" id="id_end103">
                        <incoming>sf_cpr_erheben_end103</incoming>
                    </endEvent>
                </subProcess>
                <sequenceFlow sourceRef="id_cpr_aelter_als_7_tage_xor" targetRef="id_join_of_bb_crp_par" name="else" id="sf_cpr_aelter_als_7_tage_xor_join_of_bb_crp_par">
                    <conditionExpression id="sf_cpr_aelter_als_7_tage_xor_join_of_bb_crp_par_condition">else</conditionExpression>
                </sequenceFlow>
                <subProcess name="BB aelter als 7 Tage?" id="id_bb_aelter_als_7_tage_xor">
                    <incoming>sf_bb_crp_par_bb_aelter_als_7_tage_xor</incoming>
                    <outgoing>sf_bb_aelter_als_7_tage_xor_join_of_bb_crp_par</outgoing>
                    <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start100">
                        <outgoing>sf_start100_bb_erheben</outgoing>
                    </startEvent>
                    <sequenceFlow sourceRef="id_start100" targetRef="id_bb_erheben" name="ja" id="sf_start100_bb_erheben">
                        <conditionExpression id="sf_start100_bb_erheben_condition">ja</conditionExpression>
                    </sequenceFlow>
                    <userTask name="BB erheben" id="id_bb_erheben">
                        <incoming>sf_start100_bb_erheben</incoming>
                        <outgoing>sf_bb_erheben_end101</outgoing>
                    </userTask>
                    <sequenceFlow sourceRef="id_bb_erheben" targetRef="id_end101" id="sf_bb_erheben_end101"/>
                    <endEvent name="end" id="id_end101">
                        <incoming>sf_bb_erheben_end101</incoming>
                    </endEvent>
                </subProcess>
                <sequenceFlow sourceRef="id_bb_aelter_als_7_tage_xor" targetRef="id_join_of_bb_crp_par" name="else" id="sf_bb_aelter_als_7_tage_xor_join_of_bb_crp_par">
                    <conditionExpression id="sf_bb_aelter_als_7_tage_xor_join_of_bb_crp_par_condition">else</conditionExpression>
                </sequenceFlow>
                <parallelGateway name="join" id="id_join_of_bb_crp_par">
                    <incoming>sf_cpr_aelter_als_7_tage_xor_join_of_bb_crp_par</incoming>
                    <incoming>sf_bb_aelter_als_7_tage_xor_join_of_bb_crp_par</incoming>
                    <outgoing>sf_join_of_bb_crp_par_kugelroentgen_durchfuehren</outgoing>
                </parallelGateway>
                <sequenceFlow sourceRef="id_join_of_bb_crp_par" targetRef="id_kugelroentgen_durchfuehren" id="sf_join_of_bb_crp_par_kugelroentgen_durchfuehren"/>
                <userTask name="Kugelroentgen durchfuehren" id="id_kugelroentgen_durchfuehren">
                    <incoming>sf_join_of_bb_crp_par_kugelroentgen_durchfuehren</incoming>
                    <outgoing>sf_kugelroentgen_durchfuehren_operationsfreigabe_moeglich_xor</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_kugelroentgen_durchfuehren" targetRef="id_operationsfreigabe_moeglich_xor" id="sf_kugelroentgen_durchfuehren_operationsfreigabe_moeglich_xor"/>
                <exclusiveGateway name="Operationsfreigabe moeglich?" id="id_operationsfreigabe_moeglich_xor">
                    <incoming>sf_kugelroentgen_durchfuehren_operationsfreigabe_moeglich_xor</incoming>
                    <outgoing>sf_operationsfreigabe_moeglich_xor_operation_freigeben_und_durchfuehren_grouping</outgoing>
                    <outgoing>sf_operationsfreigabe_moeglich_xor_keine_operation_durchfuehren</outgoing>
                </exclusiveGateway>
                <sequenceFlow sourceRef="id_operationsfreigabe_moeglich_xor" targetRef="id_operation_freigeben_und_durchfuehren_grouping" name="Ja" id="sf_operationsfreigabe_moeglich_xor_operation_freigeben_und_durchfuehren_grouping">
                    <conditionExpression id="sf_operationsfreigabe_moeglich_xor_operation_freigeben_und_durchfuehren_grouping_condition">Ja</conditionExpression>
                </sequenceFlow>
                <sequenceFlow sourceRef="id_operationsfreigabe_moeglich_xor" targetRef="id_keine_operation_durchfuehren" name="Nein" id="sf_operationsfreigabe_moeglich_xor_keine_operation_durchfuehren">
                    <conditionExpression id="sf_operationsfreigabe_moeglich_xor_keine_operation_durchfuehren_condition">Nein</conditionExpression>
                </sequenceFlow>
                <subProcess id="id_operation_freigeben_und_durchfuehren_grouping">
                    <incoming>sf_operationsfreigabe_moeglich_xor_operation_freigeben_und_durchfuehren_grouping</incoming>
                    <outgoing>sf_operation_freigeben_und_durchfuehren_grouping_join_of_operationsfreigabe_moeglich_xor</outgoing>
                    <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start104">
                        <outgoing>sf_start104_operation_freigeben</outgoing>
                    </startEvent>
                    <sequenceFlow sourceRef="id_start104" targetRef="id_operation_freigeben" id="sf_start104_operation_freigeben"/>
                    <userTask name="Operation freigeben" id="id_operation_freigeben">
                        <incoming>sf_start104_operation_freigeben</incoming>
                        <outgoing>sf_operation_freigeben_operation_durchfuehren</outgoing>
                    </userTask>
                    <sequenceFlow sourceRef="id_operation_freigeben" targetRef="id_operation_durchfuehren" id="sf_operation_freigeben_operation_durchfuehren"/>
                    <userTask name="Operation durchfuehren" id="id_operation_durchfuehren">
                        <incoming>sf_operation_freigeben_operation_durchfuehren</incoming>
                        <outgoing>sf_operation_durchfuehren_hueftvergleich_durchfuehren</outgoing>
                    </userTask>
                    <sequenceFlow sourceRef="id_operation_durchfuehren" targetRef="id_hueftvergleich_durchfuehren" id="sf_operation_durchfuehren_hueftvergleich_durchfuehren"/>
                    <intermediateCatchEvent name="Hueftvergleich durchfuehren" id="id_hueftvergleich_durchfuehren">
                        <incoming>sf_operation_durchfuehren_hueftvergleich_durchfuehren</incoming>
                        <outgoing>sf_hueftvergleich_durchfuehren_hueftvergleich_durchfuehren_triggerAction</outgoing>
                        <timerEventDefinition id="erster_post_op_tag"/>
                    </intermediateCatchEvent>
                    <sequenceFlow sourceRef="id_hueftvergleich_durchfuehren" targetRef="id_hueftvergleich_durchfuehren_triggerAction" id="sf_hueftvergleich_durchfuehren_hueftvergleich_durchfuehren_triggerAction"/>
                    <userTask name="Hueftvergleich durchfuehren" id="id_hueftvergleich_durchfuehren_triggerAction">
                        <incoming>sf_hueftvergleich_durchfuehren_hueftvergleich_durchfuehren_triggerAction</incoming>
                        <outgoing>sf_hueftvergleich_durchfuehren_triggerAction_postoperative_laborbefundung_2</outgoing>
                    </userTask>
                    <sequenceFlow sourceRef="id_hueftvergleich_durchfuehren_triggerAction" targetRef="id_postoperative_laborbefundung_2" id="sf_hueftvergleich_durchfuehren_triggerAction_postoperative_laborbefundung_2"/>
                    <intermediateCatchEvent name="postoperative Laborbefunde 2 Tag post Op erhaben" id="id_postoperative_laborbefundung_2">
                        <incoming>sf_hueftvergleich_durchfuehren_triggerAction_postoperative_laborbefundung_2</incoming>
                        <outgoing>sf_postoperative_laborbefundung_2_postoperative_laborbefundung_2_triggerAction</outgoing>
                        <timerEventDefinition id="zweiter_post_op_tag"/>
                    </intermediateCatchEvent>
                    <sequenceFlow sourceRef="id_postoperative_laborbefundung_2" targetRef="id_postoperative_laborbefundung_2_triggerAction" id="sf_postoperative_laborbefundung_2_postoperative_laborbefundung_2_triggerAction"/>
                    <userTask name="postoperative Laborbefunde 2 Tag post Op erhaben" id="id_postoperative_laborbefundung_2_triggerAction">
                        <incoming>sf_postoperative_laborbefundung_2_postoperative_laborbefundung_2_triggerAction</incoming>
                        <outgoing>sf_postoperative_laborbefundung_2_triggerAction_postoperative_laborbefundung_4</outgoing>
                    </userTask>
                    <sequenceFlow sourceRef="id_postoperative_laborbefundung_2_triggerAction" targetRef="id_postoperative_laborbefundung_4" id="sf_postoperative_laborbefundung_2_triggerAction_postoperative_laborbefundung_4"/>
                    <intermediateCatchEvent name="postoperative Laborbefunde 4. Tag postOp erheben" id="id_postoperative_laborbefundung_4">
                        <incoming>sf_postoperative_laborbefundung_2_triggerAction_postoperative_laborbefundung_4</incoming>
                        <outgoing>sf_postoperative_laborbefundung_4_postoperative_laborbefundung_4_triggerAction</outgoing>
                        <timerEventDefinition id="vierter_post_op_tag"/>
                    </intermediateCatchEvent>
                    <sequenceFlow sourceRef="id_postoperative_laborbefundung_4" targetRef="id_postoperative_laborbefundung_4_triggerAction" id="sf_postoperative_laborbefundung_4_postoperative_laborbefundung_4_triggerAction"/>
                    <userTask name="postoperative Laborbefunde 4. Tag postOp erheben" id="id_postoperative_laborbefundung_4_triggerAction">
                        <incoming>sf_postoperative_laborbefundung_4_postoperative_laborbefundung_4_triggerAction</incoming>
                        <outgoing>sf_postoperative_laborbefundung_4_triggerAction_weitere_laborbefunde_notwendig_xor</outgoing>
                    </userTask>
                    <sequenceFlow sourceRef="id_postoperative_laborbefundung_4_triggerAction" targetRef="id_weitere_laborbefunde_notwendig_xor" id="sf_postoperative_laborbefundung_4_triggerAction_weitere_laborbefunde_notwendig_xor"/>
                    <exclusiveGateway name="weitere Laborbefunde notwendig?" id="id_weitere_laborbefunde_notwendig_xor">
                        <incoming>sf_postoperative_laborbefundung_4_triggerAction_weitere_laborbefunde_notwendig_xor</incoming>
                        <outgoing>sf_weitere_laborbefunde_notwendig_xor_join_of_weitere_laborbefunde_notwendig_xor</outgoing>
                        <outgoing>sf_weitere_laborbefunde_notwendig_xor_weitere_post_op_laborbefunde_erheben</outgoing>
                    </exclusiveGateway>
                    <sequenceFlow sourceRef="id_weitere_laborbefunde_notwendig_xor" targetRef="id_join_of_weitere_laborbefunde_notwendig_xor" name="else" id="sf_weitere_laborbefunde_notwendig_xor_join_of_weitere_laborbefunde_notwendig_xor">
                        <conditionExpression id="sf_weitere_laborbefunde_notwendig_xor_join_of_weitere_laborbefunde_notwendig_xor_condition">else</conditionExpression>
                    </sequenceFlow>
                    <sequenceFlow sourceRef="id_weitere_laborbefunde_notwendig_xor" targetRef="id_weitere_post_op_laborbefunde_erheben" id="sf_weitere_laborbefunde_notwendig_xor_weitere_post_op_laborbefunde_erheben"/>
                    <exclusiveGateway name="join" id="id_join_of_weitere_laborbefunde_notwendig_xor">
                        <incoming>sf_weitere_laborbefunde_notwendig_xor_join_of_weitere_laborbefunde_notwendig_xor</incoming>
                        <incoming>sf_weitere_post_op_laborbefunde_erheben_join_of_weitere_laborbefunde_notwendig_xor</incoming>
                        <outgoing>sf_join_of_weitere_laborbefunde_notwendig_xor_durchleuchtungsgezielte_lauensteinaufnahme_durchfuehren</outgoing>
                    </exclusiveGateway>
                    <sequenceFlow sourceRef="id_join_of_weitere_laborbefunde_notwendig_xor" targetRef="id_durchleuchtungsgezielte_lauensteinaufnahme_durchfuehren" id="sf_join_of_weitere_laborbefunde_notwendig_xor_durchleuchtungsgezielte_lauensteinaufnahme_durchfuehren"/>
                    <userTask name="weitere postOP Laborbefunde erheben" id="id_weitere_post_op_laborbefunde_erheben">
                        <incoming>sf_weitere_laborbefunde_notwendig_xor_weitere_post_op_laborbefunde_erheben</incoming>
                        <outgoing>sf_weitere_post_op_laborbefunde_erheben_join_of_weitere_laborbefunde_notwendig_xor</outgoing>
                    </userTask>
                    <sequenceFlow sourceRef="id_weitere_post_op_laborbefunde_erheben" targetRef="id_join_of_weitere_laborbefunde_notwendig_xor" id="sf_weitere_post_op_laborbefunde_erheben_join_of_weitere_laborbefunde_notwendig_xor"/>
                    <userTask name="Durchleuchtungsgezielte Lauensteinaufnahme durchfuehren" id="id_durchleuchtungsgezielte_lauensteinaufnahme_durchfuehren">
                        <incoming>sf_join_of_weitere_laborbefunde_notwendig_xor_durchleuchtungsgezielte_lauensteinaufnahme_durchfuehren</incoming>
                        <outgoing>sf_durchleuchtungsgezielte_lauensteinaufnahme_durchfuehren_end105</outgoing>
                    </userTask>
                    <sequenceFlow sourceRef="id_durchleuchtungsgezielte_lauensteinaufnahme_durchfuehren" targetRef="id_end105" id="sf_durchleuchtungsgezielte_lauensteinaufnahme_durchfuehren_end105"/>
                    <endEvent name="end" id="id_end105">
                        <incoming>sf_durchleuchtungsgezielte_lauensteinaufnahme_durchfuehren_end105</incoming>
                    </endEvent>
                </subProcess>
                <sequenceFlow sourceRef="id_operation_freigeben_und_durchfuehren_grouping" targetRef="id_join_of_operationsfreigabe_moeglich_xor" id="sf_operation_freigeben_und_durchfuehren_grouping_join_of_operationsfreigabe_moeglich_xor"/>
                <userTask name="Keine Operation durchfuehren" id="id_keine_operation_durchfuehren">
                    <incoming>sf_operationsfreigabe_moeglich_xor_keine_operation_durchfuehren</incoming>
                    <outgoing>sf_keine_operation_durchfuehren_join_of_operationsfreigabe_moeglich_xor</outgoing>
                </userTask>
                <sequenceFlow sourceRef="id_keine_operation_durchfuehren" targetRef="id_join_of_operationsfreigabe_moeglich_xor" id="sf_keine_operation_durchfuehren_join_of_operationsfreigabe_moeglich_xor"/>
                <exclusiveGateway name="join" id="id_join_of_operationsfreigabe_moeglich_xor">
                    <incoming>sf_operation_freigeben_und_durchfuehren_grouping_join_of_operationsfreigabe_moeglich_xor</incoming>
                    <incoming>sf_keine_operation_durchfuehren_join_of_operationsfreigabe_moeglich_xor</incoming>
                    <outgoing>sf_join_of_operationsfreigabe_moeglich_xor_end99</outgoing>
                </exclusiveGateway>
                <sequenceFlow sourceRef="id_join_of_operationsfreigabe_moeglich_xor" targetRef="id_end99" id="sf_join_of_operationsfreigabe_moeglich_xor_end99"/>
                <endEvent name="end" id="id_end99">
                    <incoming>sf_join_of_operationsfreigabe_moeglich_xor_end99</incoming>
                </endEvent>
            </subProcess>
            <sequenceFlow sourceRef="id_patient_einwilligung_erhalten_group" targetRef="id_join_of_einwilligung_des_patienten_xor" id="sf_patient_einwilligung_erhalten_group_join_of_einwilligung_des_patienten_xor"/>
            <userTask name="Keine Operation durchfuehren" id="id_keine_operation_durchfuehren_1">
                <incoming>sf_einwilligung_des_patienten_xor_keine_operation_durchfuehren_1</incoming>
                <outgoing>sf_keine_operation_durchfuehren_1_join_of_einwilligung_des_patienten_xor</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_keine_operation_durchfuehren_1" targetRef="id_join_of_einwilligung_des_patienten_xor" id="sf_keine_operation_durchfuehren_1_join_of_einwilligung_des_patienten_xor"/>
            <exclusiveGateway name="join" id="id_join_of_einwilligung_des_patienten_xor">
                <incoming>sf_patient_einwilligung_erhalten_group_join_of_einwilligung_des_patienten_xor</incoming>
                <incoming>sf_keine_operation_durchfuehren_1_join_of_einwilligung_des_patienten_xor</incoming>
                <outgoing>sf_join_of_einwilligung_des_patienten_xor_end95</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_join_of_einwilligung_des_patienten_xor" targetRef="id_end95" id="sf_join_of_einwilligung_des_patienten_xor_end95"/>
            <endEvent name="end" id="id_end95">
                <incoming>sf_join_of_einwilligung_des_patienten_xor_end95</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_operation_grouping" targetRef="id_join_of_konservative_therapie_and_no_arthrose_xor" id="sf_operation_grouping_join_of_konservative_therapie_and_no_arthrose_xor"/>
        <exclusiveGateway name="join" id="id_join_of_konservative_therapie_and_no_arthrose_xor">
            <incoming>sf_konservative_therapie_veranlassen_join_of_konservative_therapie_and_no_arthrose_xor</incoming>
            <incoming>sf_operation_grouping_join_of_konservative_therapie_and_no_arthrose_xor</incoming>
            <outgoing>sf_join_of_konservative_therapie_and_no_arthrose_xor_entlassung_nach_hause_moeglich</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_join_of_konservative_therapie_and_no_arthrose_xor" targetRef="id_entlassung_nach_hause_moeglich" id="sf_join_of_konservative_therapie_and_no_arthrose_xor_entlassung_nach_hause_moeglich"/>
        <exclusiveGateway name="Entlassung nach Hause möglich?" id="id_entlassung_nach_hause_moeglich">
            <incoming>sf_join_of_konservative_therapie_and_no_arthrose_xor_entlassung_nach_hause_moeglich</incoming>
            <outgoing>sf_entlassung_nach_hause_moeglich_patient_wird_entlassen</outgoing>
            <outgoing>sf_entlassung_nach_hause_moeglich_patient_wird_nicht_entlassen_grouping</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_entlassung_nach_hause_moeglich" targetRef="id_patient_wird_entlassen" name="Ja" id="sf_entlassung_nach_hause_moeglich_patient_wird_entlassen">
            <conditionExpression id="sf_entlassung_nach_hause_moeglich_patient_wird_entlassen_condition">Ja</conditionExpression>
        </sequenceFlow>
        <sequenceFlow sourceRef="id_entlassung_nach_hause_moeglich" targetRef="id_patient_wird_nicht_entlassen_grouping" name="Nein" id="sf_entlassung_nach_hause_moeglich_patient_wird_nicht_entlassen_grouping">
            <conditionExpression id="sf_entlassung_nach_hause_moeglich_patient_wird_nicht_entlassen_grouping_condition">Nein</conditionExpression>
        </sequenceFlow>
        <userTask name="Patient wird entlassen" id="id_patient_wird_entlassen">
            <incoming>sf_entlassung_nach_hause_moeglich_patient_wird_entlassen</incoming>
            <outgoing>sf_patient_wird_entlassen_join_of_entlassung_nach_hause_moeglich</outgoing>
            <dataOutputAssociation id="df_id_patient_wird_entlassen_data_entlassungspapiere">
                <targetRef>id_data_entlassungspapiere</targetRef>
            </dataOutputAssociation>
        </userTask>
        <sequenceFlow sourceRef="id_patient_wird_entlassen" targetRef="id_join_of_entlassung_nach_hause_moeglich" id="sf_patient_wird_entlassen_join_of_entlassung_nach_hause_moeglich"/>
        <dataObjectReference name="Entlassungspapiere" id="id_data_entlassungspapiere">
            <extensionElements>
                <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">Entlassungspapiere</ns5:type>
            </extensionElements>
        </dataObjectReference>
        <subProcess id="id_patient_wird_nicht_entlassen_grouping">
            <incoming>sf_entlassung_nach_hause_moeglich_patient_wird_nicht_entlassen_grouping</incoming>
            <outgoing>sf_patient_wird_nicht_entlassen_grouping_join_of_entlassung_nach_hause_moeglich</outgoing>
            <startEvent isInterrupting="false" parallelMultiple="false" name="start" id="id_start106">
                <outgoing>sf_start106_anschlussheilverfahren_einleiten</outgoing>
            </startEvent>
            <sequenceFlow sourceRef="id_start106" targetRef="id_anschlussheilverfahren_einleiten" id="sf_start106_anschlussheilverfahren_einleiten"/>
            <userTask name="Anschlussheilverfahren einleiten" id="id_anschlussheilverfahren_einleiten">
                <incoming>sf_start106_anschlussheilverfahren_einleiten</incoming>
                <outgoing>sf_anschlussheilverfahren_einleiten_patient_in_remob_abteilung_verlegen_xor</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_anschlussheilverfahren_einleiten" targetRef="id_patient_in_remob_abteilung_verlegen_xor" id="sf_anschlussheilverfahren_einleiten_patient_in_remob_abteilung_verlegen_xor"/>
            <exclusiveGateway name="Patient in Remob-Abteilung verlegen?" id="id_patient_in_remob_abteilung_verlegen_xor">
                <incoming>sf_anschlussheilverfahren_einleiten_patient_in_remob_abteilung_verlegen_xor</incoming>
                <outgoing>sf_patient_in_remob_abteilung_verlegen_xor_patient_in_remobilisierungsabteilung_verlegen</outgoing>
                <outgoing>sf_patient_in_remob_abteilung_verlegen_xor_patient_in_andere_abteilung_verlegen</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_patient_in_remob_abteilung_verlegen_xor" targetRef="id_patient_in_remobilisierungsabteilung_verlegen" name="Ja" id="sf_patient_in_remob_abteilung_verlegen_xor_patient_in_remobilisierungsabteilung_verlegen">
                <conditionExpression id="sf_patient_in_remob_abteilung_verlegen_xor_patient_in_remobilisierungsabteilung_verlegen_condition">Ja</conditionExpression>
            </sequenceFlow>
            <sequenceFlow sourceRef="id_patient_in_remob_abteilung_verlegen_xor" targetRef="id_patient_in_andere_abteilung_verlegen" name="Nein" id="sf_patient_in_remob_abteilung_verlegen_xor_patient_in_andere_abteilung_verlegen">
                <conditionExpression id="sf_patient_in_remob_abteilung_verlegen_xor_patient_in_andere_abteilung_verlegen_condition">Nein</conditionExpression>
            </sequenceFlow>
            <userTask name="Patient in Remobilisierungsabteilung verlegen" id="id_patient_in_remobilisierungsabteilung_verlegen">
                <incoming>sf_patient_in_remob_abteilung_verlegen_xor_patient_in_remobilisierungsabteilung_verlegen</incoming>
                <outgoing>sf_patient_in_remobilisierungsabteilung_verlegen_join_of_patient_in_remob_abteilung_verlegen_xor</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_patient_in_remobilisierungsabteilung_verlegen" targetRef="id_join_of_patient_in_remob_abteilung_verlegen_xor" id="sf_patient_in_remobilisierungsabteilung_verlegen_join_of_patient_in_remob_abteilung_verlegen_xor"/>
            <userTask name="Patient in andere Abteilung verlegen" id="id_patient_in_andere_abteilung_verlegen">
                <incoming>sf_patient_in_remob_abteilung_verlegen_xor_patient_in_andere_abteilung_verlegen</incoming>
                <outgoing>sf_patient_in_andere_abteilung_verlegen_join_of_patient_in_remob_abteilung_verlegen_xor</outgoing>
            </userTask>
            <sequenceFlow sourceRef="id_patient_in_andere_abteilung_verlegen" targetRef="id_join_of_patient_in_remob_abteilung_verlegen_xor" id="sf_patient_in_andere_abteilung_verlegen_join_of_patient_in_remob_abteilung_verlegen_xor"/>
            <exclusiveGateway name="join" id="id_join_of_patient_in_remob_abteilung_verlegen_xor">
                <incoming>sf_patient_in_andere_abteilung_verlegen_join_of_patient_in_remob_abteilung_verlegen_xor</incoming>
                <incoming>sf_patient_in_remobilisierungsabteilung_verlegen_join_of_patient_in_remob_abteilung_verlegen_xor</incoming>
                <outgoing>sf_join_of_patient_in_remob_abteilung_verlegen_xor_post_op_genesungsprozess_erfolgreich</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_join_of_patient_in_remob_abteilung_verlegen_xor" targetRef="id_post_op_genesungsprozess_erfolgreich" id="sf_join_of_patient_in_remob_abteilung_verlegen_xor_post_op_genesungsprozess_erfolgreich"/>
            <exclusiveGateway name="post OP Genesungsprozess erfolgreich?" id="id_post_op_genesungsprozess_erfolgreich">
                <incoming>sf_join_of_patient_in_remob_abteilung_verlegen_xor_post_op_genesungsprozess_erfolgreich</incoming>
                <outgoing>sf_post_op_genesungsprozess_erfolgreich_join_of_post_op_genesungsprozess_erfolgreich</outgoing>
                <outgoing>sf_post_op_genesungsprozess_erfolgreich_patient_wird_entlassen_2</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_post_op_genesungsprozess_erfolgreich" targetRef="id_join_of_post_op_genesungsprozess_erfolgreich" name="else" id="sf_post_op_genesungsprozess_erfolgreich_join_of_post_op_genesungsprozess_erfolgreich">
                <conditionExpression id="sf_post_op_genesungsprozess_erfolgreich_join_of_post_op_genesungsprozess_erfolgreich_condition">else</conditionExpression>
            </sequenceFlow>
            <sequenceFlow sourceRef="id_post_op_genesungsprozess_erfolgreich" targetRef="id_patient_wird_entlassen_2" name="Ja" id="sf_post_op_genesungsprozess_erfolgreich_patient_wird_entlassen_2">
                <conditionExpression id="sf_post_op_genesungsprozess_erfolgreich_patient_wird_entlassen_2_condition">Ja</conditionExpression>
            </sequenceFlow>
            <exclusiveGateway name="join" id="id_join_of_post_op_genesungsprozess_erfolgreich">
                <incoming>sf_post_op_genesungsprozess_erfolgreich_join_of_post_op_genesungsprozess_erfolgreich</incoming>
                <incoming>sf_patient_wird_entlassen_2_join_of_post_op_genesungsprozess_erfolgreich</incoming>
                <outgoing>sf_join_of_post_op_genesungsprozess_erfolgreich_end107</outgoing>
            </exclusiveGateway>
            <sequenceFlow sourceRef="id_join_of_post_op_genesungsprozess_erfolgreich" targetRef="id_end107" id="sf_join_of_post_op_genesungsprozess_erfolgreich_end107"/>
            <userTask name="Patient wird entlassen" id="id_patient_wird_entlassen_2">
                <incoming>sf_post_op_genesungsprozess_erfolgreich_patient_wird_entlassen_2</incoming>
                <outgoing>sf_patient_wird_entlassen_2_join_of_post_op_genesungsprozess_erfolgreich</outgoing>
                <dataOutputAssociation id="df_id_patient_wird_entlassen_2_data_entlassungspapiere_2">
                    <targetRef>id_data_entlassungspapiere_2</targetRef>
                </dataOutputAssociation>
            </userTask>
            <sequenceFlow sourceRef="id_patient_wird_entlassen_2" targetRef="id_join_of_post_op_genesungsprozess_erfolgreich" id="sf_patient_wird_entlassen_2_join_of_post_op_genesungsprozess_erfolgreich"/>
            <dataObjectReference name="Entlassungspapiere" id="id_data_entlassungspapiere_2">
                <extensionElements>
                    <ns5:type xmlns:ns5="http://aist.fh-hagenberg.at/msbpmn/bpmn-extension/fhir">Entlassungspapiere</ns5:type>
                </extensionElements>
            </dataObjectReference>
            <endEvent name="end" id="id_end107">
                <incoming>sf_join_of_post_op_genesungsprozess_erfolgreich_end107</incoming>
            </endEvent>
        </subProcess>
        <sequenceFlow sourceRef="id_patient_wird_nicht_entlassen_grouping" targetRef="id_join_of_entlassung_nach_hause_moeglich" id="sf_patient_wird_nicht_entlassen_grouping_join_of_entlassung_nach_hause_moeglich"/>
        <exclusiveGateway name="join" id="id_join_of_entlassung_nach_hause_moeglich">
            <incoming>sf_patient_wird_nicht_entlassen_grouping_join_of_entlassung_nach_hause_moeglich</incoming>
            <incoming>sf_patient_wird_entlassen_join_of_entlassung_nach_hause_moeglich</incoming>
            <outgoing>sf_join_of_entlassung_nach_hause_moeglich_end85</outgoing>
        </exclusiveGateway>
        <sequenceFlow sourceRef="id_join_of_entlassung_nach_hause_moeglich" targetRef="id_end85" id="sf_join_of_entlassung_nach_hause_moeglich_end85"/>
        <endEvent name="end" id="id_end85">
            <incoming>sf_join_of_entlassung_nach_hause_moeglich_end85</incoming>
        </endEvent>
    </process>
    <ns4:BPMNDiagram>
        <ns4:BPMNPlane bpmnElement="HTEP">
            <ns4:BPMNShape bpmnElement="id_start106" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="7120.0" y="351.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_anschlussheilverfahren_einleiten" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="7200.0" y="326.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_patient_in_remob_abteilung_verlegen_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="7350.0" y="346.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_patient_in_remobilisierungsabteilung_verlegen" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="7440.0" y="416.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_patient_in_andere_abteilung_verlegen" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="7440.0" y="236.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_patient_in_remob_abteilung_verlegen_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="7590.0" y="341.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_post_op_genesungsprozess_erfolgreich" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="7680.0" y="332.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_post_op_genesungsprozess_erfolgreich" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="7920.0" y="326.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_patient_wird_entlassen_2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="7770.0" y="376.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end107" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="8010.0" y="331.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_post_op_genesungsprozess_erfolgreich_join_of_post_op_genesungsprozess_erfolgreich">
                <ns2:waypoint x="7720.0" y="352.6197183098592"/>
                <ns2:waypoint x="7820.0" y="352.6197183098592"/>
                <ns2:waypoint x="7820.0" y="346.6197183098592"/>
                <ns2:waypoint x="7920.0" y="346.6197183098592"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="7730.0" y="349.6197183098592" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_patient_in_remob_abteilung_verlegen_xor_post_op_genesungsprozess_erfolgreich">
                <ns2:waypoint x="7630.0" y="361.6197183098592"/>
                <ns2:waypoint x="7655.0" y="361.6197183098592"/>
                <ns2:waypoint x="7655.0" y="352.6197183098592"/>
                <ns2:waypoint x="7680.0" y="352.6197183098592"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_post_op_genesungsprozess_erfolgreich_patient_wird_entlassen_2">
                <ns2:waypoint x="7720.0" y="352.6197183098592"/>
                <ns2:waypoint x="7745.0" y="352.6197183098592"/>
                <ns2:waypoint x="7745.0" y="416.6197183098592"/>
                <ns2:waypoint x="7770.0" y="416.6197183098592"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="7655.0" y="384.6197183098592" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_patient_wird_entlassen_2_join_of_post_op_genesungsprozess_erfolgreich">
                <ns2:waypoint x="7870.0" y="416.6197183098592"/>
                <ns2:waypoint x="7895.0" y="416.6197183098592"/>
                <ns2:waypoint x="7895.0" y="346.6197183098592"/>
                <ns2:waypoint x="7920.0" y="346.6197183098592"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_patient_in_remob_abteilung_verlegen_xor_patient_in_andere_abteilung_verlegen">
                <ns2:waypoint x="7390.0" y="366.6197183098592"/>
                <ns2:waypoint x="7415.0" y="366.6197183098592"/>
                <ns2:waypoint x="7415.0" y="276.6197183098592"/>
                <ns2:waypoint x="7440.0" y="276.6197183098592"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="7325.0" y="321.6197183098592" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_patient_in_andere_abteilung_verlegen_join_of_patient_in_remob_abteilung_verlegen_xor">
                <ns2:waypoint x="7540.0" y="276.6197183098592"/>
                <ns2:waypoint x="7565.0" y="276.6197183098592"/>
                <ns2:waypoint x="7565.0" y="361.6197183098592"/>
                <ns2:waypoint x="7590.0" y="361.6197183098592"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_post_op_genesungsprozess_erfolgreich_end107">
                <ns2:waypoint x="7960.0" y="346.6197183098592"/>
                <ns2:waypoint x="8010.0" y="346.6197183098592"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start106_anschlussheilverfahren_einleiten">
                <ns2:waypoint x="7150.0" y="366.6197183098592"/>
                <ns2:waypoint x="7200.0" y="366.6197183098592"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_patient_in_remobilisierungsabteilung_verlegen_join_of_patient_in_remob_abteilung_verlegen_xor">
                <ns2:waypoint x="7540.0" y="456.6197183098592"/>
                <ns2:waypoint x="7565.0" y="456.6197183098592"/>
                <ns2:waypoint x="7565.0" y="361.6197183098592"/>
                <ns2:waypoint x="7590.0" y="361.6197183098592"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_anschlussheilverfahren_einleiten_patient_in_remob_abteilung_verlegen_xor">
                <ns2:waypoint x="7300.0" y="366.6197183098592"/>
                <ns2:waypoint x="7350.0" y="366.6197183098592"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_patient_in_remob_abteilung_verlegen_xor_patient_in_remobilisierungsabteilung_verlegen">
                <ns2:waypoint x="7390.0" y="366.6197183098592"/>
                <ns2:waypoint x="7415.0" y="366.6197183098592"/>
                <ns2:waypoint x="7415.0" y="456.6197183098592"/>
                <ns2:waypoint x="7440.0" y="456.6197183098592"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="7325.0" y="411.6197183098592" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_entlassungspapiere_2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="7802.0" y="526.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="df_id_patient_wird_entlassen_2_data_entlassungspapiere_2">
                <ns2:waypoint x="7820.0" y="457.0"/>
                <ns2:waypoint x="7820.0" y="527.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start92" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4240.0" y="3885.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_konservative_therapie_veranlassen_par" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4320.0" y="3880.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_beinachsentraining_veranlassen" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4410.0" y="3680.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_gewichtsreduktion_des_patienten_veranlassen" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4410.0" y="4040.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_entsprechende_medikation_veranlassen" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4410.0" y="3860.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_patellazentrierung_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4410.0" y="4220.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_muskelkraeftigung_veranlassen" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4410.0" y="3500.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_konservative_therapie_veranlassen_par" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4560.0" y="3880.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end93" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4650.0" y="3885.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_konservative_therapie_veranlassen_par_muskelkraeftigung_veranlassen">
                <ns2:waypoint x="4360.0" y="3900.0"/>
                <ns2:waypoint x="4385.0" y="3900.0"/>
                <ns2:waypoint x="4385.0" y="3540.0"/>
                <ns2:waypoint x="4410.0" y="3540.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_patellazentrierung_durchfuehren_join_of_konservative_therapie_veranlassen_par">
                <ns2:waypoint x="4510.0" y="4260.0"/>
                <ns2:waypoint x="4535.0" y="4260.0"/>
                <ns2:waypoint x="4535.0" y="3900.0"/>
                <ns2:waypoint x="4560.0" y="3900.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start92_konservative_therapie_veranlassen_par">
                <ns2:waypoint x="4270.0" y="3900.0"/>
                <ns2:waypoint x="4320.0" y="3900.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_entsprechende_medikation_veranlassen_join_of_konservative_therapie_veranlassen_par">
                <ns2:waypoint x="4510.0" y="3900.0"/>
                <ns2:waypoint x="4560.0" y="3900.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_konservative_therapie_veranlassen_par_beinachsentraining_veranlassen">
                <ns2:waypoint x="4360.0" y="3900.0"/>
                <ns2:waypoint x="4385.0" y="3900.0"/>
                <ns2:waypoint x="4385.0" y="3720.0"/>
                <ns2:waypoint x="4410.0" y="3720.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_beinachsentraining_veranlassen_join_of_konservative_therapie_veranlassen_par">
                <ns2:waypoint x="4510.0" y="3720.0"/>
                <ns2:waypoint x="4535.0" y="3720.0"/>
                <ns2:waypoint x="4535.0" y="3900.0"/>
                <ns2:waypoint x="4560.0" y="3900.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_konservative_therapie_veranlassen_par_entsprechende_medikation_veranlassen">
                <ns2:waypoint x="4360.0" y="3900.0"/>
                <ns2:waypoint x="4410.0" y="3900.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_gewichtsreduktion_des_patienten_veranlassen_join_of_konservative_therapie_veranlassen_par">
                <ns2:waypoint x="4510.0" y="4080.0"/>
                <ns2:waypoint x="4535.0" y="4080.0"/>
                <ns2:waypoint x="4535.0" y="3900.0"/>
                <ns2:waypoint x="4560.0" y="3900.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_konservative_therapie_veranlassen_par_gewichtsreduktion_des_patienten_veranlassen">
                <ns2:waypoint x="4360.0" y="3900.0"/>
                <ns2:waypoint x="4385.0" y="3900.0"/>
                <ns2:waypoint x="4385.0" y="4080.0"/>
                <ns2:waypoint x="4410.0" y="4080.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_konservative_therapie_veranlassen_par_patellazentrierung_durchfuehren">
                <ns2:waypoint x="4360.0" y="3900.0"/>
                <ns2:waypoint x="4385.0" y="3900.0"/>
                <ns2:waypoint x="4385.0" y="4260.0"/>
                <ns2:waypoint x="4410.0" y="4260.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_muskelkraeftigung_veranlassen_join_of_konservative_therapie_veranlassen_par">
                <ns2:waypoint x="4510.0" y="3540.0"/>
                <ns2:waypoint x="4535.0" y="3540.0"/>
                <ns2:waypoint x="4535.0" y="3900.0"/>
                <ns2:waypoint x="4560.0" y="3900.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_konservative_therapie_veranlassen_par_end93">
                <ns2:waypoint x="4600.0" y="3900.0"/>
                <ns2:waypoint x="4650.0" y="3900.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start96" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2500.0" y="2597.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_patient_administrativ_aufnehmen" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2580.0" y="2572.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_pflegerische_aufnahme_veranlassen" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2730.0" y="2572.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_medizinische_aufnahme_veranlassen" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2880.0" y="2572.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_op_aufklaerung_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3030.0" y="2572.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_einwilligung_des_patienten_einholen" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3180.0" y="2572.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end97" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3330.0" y="2597.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_pflegerische_aufnahme_veranlassen_medizinische_aufnahme_veranlassen">
                <ns2:waypoint x="2830.0" y="2612.0"/>
                <ns2:waypoint x="2880.0" y="2612.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_medizinische_aufnahme_veranlassen_op_aufklaerung_durchfuehren">
                <ns2:waypoint x="2980.0" y="2612.0"/>
                <ns2:waypoint x="3030.0" y="2612.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_einwilligung_des_patienten_einholen_end97">
                <ns2:waypoint x="3280.0" y="2612.0"/>
                <ns2:waypoint x="3330.0" y="2612.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_patient_administrativ_aufnehmen_pflegerische_aufnahme_veranlassen">
                <ns2:waypoint x="2680.0" y="2612.0"/>
                <ns2:waypoint x="2730.0" y="2612.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start96_patient_administrativ_aufnehmen">
                <ns2:waypoint x="2530.0" y="2612.0"/>
                <ns2:waypoint x="2580.0" y="2612.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_op_aufklaerung_durchfuehren_einwilligung_des_patienten_einholen">
                <ns2:waypoint x="3130.0" y="2612.0"/>
                <ns2:waypoint x="3180.0" y="2612.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_operationseinwilligung_2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3212.0" y="2682.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="df_id_einwilligung_des_patienten_einholen_data_operationseinwilligung_2">
                <ns2:waypoint x="3230.0" y="2652.0"/>
                <ns2:waypoint x="3230.0" y="2682.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start100" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3910.0" y="3035.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_bb_erheben" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3990.0" y="3010.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end101" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4140.0" y="3035.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_start100_bb_erheben">
                <ns2:waypoint x="3940.0" y="3050.0"/>
                <ns2:waypoint x="3990.0" y="3050.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="3875.0" y="3050.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_bb_erheben_end101">
                <ns2:waypoint x="4090.0" y="3050.0"/>
                <ns2:waypoint x="4140.0" y="3050.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start102" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3910.0" y="2705.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_cpr_erheben" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3990.0" y="2680.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end103" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4140.0" y="2705.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_cpr_erheben_end103">
                <ns2:waypoint x="4090.0" y="2720.0"/>
                <ns2:waypoint x="4140.0" y="2720.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start102_cpr_erheben">
                <ns2:waypoint x="3940.0" y="2720.0"/>
                <ns2:waypoint x="3990.0" y="2720.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="3875.0" y="2720.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start104" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4700.0" y="2798.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_operation_freigeben" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4780.0" y="2773.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_operation_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4930.0" y="2773.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_hueftvergleich_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5080.0" y="2798.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_hueftvergleich_durchfuehren_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5160.0" y="2773.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_postoperative_laborbefundung_2" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5310.0" y="2798.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_postoperative_laborbefundung_2_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5390.0" y="2773.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_postoperative_laborbefundung_4" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5540.0" y="2801.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_postoperative_laborbefundung_4_triggerAction" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5620.0" y="2782.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_weitere_laborbefunde_notwendig_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5770.0" y="2808.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_weitere_laborbefunde_notwendig_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="6010.0" y="2810.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_weitere_post_op_laborbefunde_erheben" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5860.0" y="2723.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_durchleuchtungsgezielte_lauensteinaufnahme_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="6100.0" y="2787.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end105" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="6250.0" y="2812.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_operation_freigeben_operation_durchfuehren">
                <ns2:waypoint x="4880.0" y="2813.0"/>
                <ns2:waypoint x="4930.0" y="2813.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_hueftvergleich_durchfuehren_triggerAction_postoperative_laborbefundung_2">
                <ns2:waypoint x="5260.0" y="2813.0"/>
                <ns2:waypoint x="5310.0" y="2813.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_weitere_laborbefunde_notwendig_xor_weitere_post_op_laborbefunde_erheben">
                <ns2:waypoint x="5810.0" y="2828.0"/>
                <ns2:waypoint x="5835.0" y="2828.0"/>
                <ns2:waypoint x="5835.0" y="2763.0"/>
                <ns2:waypoint x="5860.0" y="2763.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_weitere_laborbefunde_notwendig_xor_durchleuchtungsgezielte_lauensteinaufnahme_durchfuehren">
                <ns2:waypoint x="6050.0" y="2830.0"/>
                <ns2:waypoint x="6075.0" y="2830.0"/>
                <ns2:waypoint x="6075.0" y="2827.0"/>
                <ns2:waypoint x="6100.0" y="2827.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_postoperative_laborbefundung_4_triggerAction_weitere_laborbefunde_notwendig_xor">
                <ns2:waypoint x="5720.0" y="2822.0"/>
                <ns2:waypoint x="5745.0" y="2822.0"/>
                <ns2:waypoint x="5745.0" y="2828.0"/>
                <ns2:waypoint x="5770.0" y="2828.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_weitere_laborbefunde_notwendig_xor_join_of_weitere_laborbefunde_notwendig_xor">
                <ns2:waypoint x="5810.0" y="2828.0"/>
                <ns2:waypoint x="5910.0" y="2828.0"/>
                <ns2:waypoint x="5910.0" y="2830.0"/>
                <ns2:waypoint x="6010.0" y="2830.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="5820.0" y="2829.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_postoperative_laborbefundung_2_postoperative_laborbefundung_2_triggerAction">
                <ns2:waypoint x="5340.0" y="2813.0"/>
                <ns2:waypoint x="5390.0" y="2813.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_weitere_post_op_laborbefunde_erheben_join_of_weitere_laborbefunde_notwendig_xor">
                <ns2:waypoint x="5960.0" y="2763.0"/>
                <ns2:waypoint x="5985.0" y="2763.0"/>
                <ns2:waypoint x="5985.0" y="2830.0"/>
                <ns2:waypoint x="6010.0" y="2830.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start104_operation_freigeben">
                <ns2:waypoint x="4730.0" y="2813.0"/>
                <ns2:waypoint x="4780.0" y="2813.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_durchleuchtungsgezielte_lauensteinaufnahme_durchfuehren_end105">
                <ns2:waypoint x="6200.0" y="2827.0"/>
                <ns2:waypoint x="6250.0" y="2827.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_postoperative_laborbefundung_4_postoperative_laborbefundung_4_triggerAction">
                <ns2:waypoint x="5570.0" y="2816.0"/>
                <ns2:waypoint x="5595.0" y="2816.0"/>
                <ns2:waypoint x="5595.0" y="2822.0"/>
                <ns2:waypoint x="5620.0" y="2822.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_operation_durchfuehren_hueftvergleich_durchfuehren">
                <ns2:waypoint x="5030.0" y="2813.0"/>
                <ns2:waypoint x="5080.0" y="2813.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_hueftvergleich_durchfuehren_hueftvergleich_durchfuehren_triggerAction">
                <ns2:waypoint x="5110.0" y="2813.0"/>
                <ns2:waypoint x="5160.0" y="2813.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_postoperative_laborbefundung_2_triggerAction_postoperative_laborbefundung_4">
                <ns2:waypoint x="5490.0" y="2813.0"/>
                <ns2:waypoint x="5515.0" y="2813.0"/>
                <ns2:waypoint x="5515.0" y="2816.0"/>
                <ns2:waypoint x="5540.0" y="2816.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start98" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3650.0" y="2855.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_bb_crp_par" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3730.0" y="2850.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_cpr_aelter_als_7_tage_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3820.0" y="2590.0" width="410.0" height="230.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_bb_aelter_als_7_tage_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3820.0" y="2920.0" width="410.0" height="230.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_bb_crp_par" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4280.0" y="2857.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_kugelroentgen_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4370.0" y="2855.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_operationsfreigabe_moeglich_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4520.0" y="2893.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_operation_freigeben_und_durchfuehren_grouping" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4610.0" y="2633.0" width="1730.0" height="294.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_keine_operation_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5425.0" y="3027.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_operationsfreigabe_moeglich_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="6390.0" y="2901.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end99" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="6480.0" y="2906.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_bb_crp_par_cpr_aelter_als_7_tage_xor">
                <ns2:waypoint x="3770.0" y="2870.0"/>
                <ns2:waypoint x="3795.0" y="2870.0"/>
                <ns2:waypoint x="3795.0" y="2705.0"/>
                <ns2:waypoint x="3820.0" y="2705.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_operationsfreigabe_moeglich_xor_operation_freigeben_und_durchfuehren_grouping">
                <ns2:waypoint x="4560.0" y="2913.0"/>
                <ns2:waypoint x="4585.0" y="2913.0"/>
                <ns2:waypoint x="4585.0" y="2780.0"/>
                <ns2:waypoint x="4610.0" y="2780.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="4495.0" y="2846.5" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_bb_aelter_als_7_tage_xor_join_of_bb_crp_par">
                <ns2:waypoint x="4230.0" y="3035.0"/>
                <ns2:waypoint x="4255.0" y="3035.0"/>
                <ns2:waypoint x="4255.0" y="2877.0"/>
                <ns2:waypoint x="4280.0" y="2877.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="4165.0" y="2956.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_operationsfreigabe_moeglich_xor_end99">
                <ns2:waypoint x="6430.0" y="2921.0"/>
                <ns2:waypoint x="6480.0" y="2921.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_bb_crp_par_kugelroentgen_durchfuehren">
                <ns2:waypoint x="4320.0" y="2877.0"/>
                <ns2:waypoint x="4345.0" y="2877.0"/>
                <ns2:waypoint x="4345.0" y="2895.0"/>
                <ns2:waypoint x="4370.0" y="2895.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_bb_crp_par_bb_aelter_als_7_tage_xor">
                <ns2:waypoint x="3770.0" y="2870.0"/>
                <ns2:waypoint x="3795.0" y="2870.0"/>
                <ns2:waypoint x="3795.0" y="3035.0"/>
                <ns2:waypoint x="3820.0" y="3035.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_operation_freigeben_und_durchfuehren_grouping_join_of_operationsfreigabe_moeglich_xor">
                <ns2:waypoint x="6340.0" y="2780.0"/>
                <ns2:waypoint x="6365.0" y="2780.0"/>
                <ns2:waypoint x="6365.0" y="2921.0"/>
                <ns2:waypoint x="6390.0" y="2921.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start98_bb_crp_par">
                <ns2:waypoint x="3680.0" y="2870.0"/>
                <ns2:waypoint x="3730.0" y="2870.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_keine_operation_durchfuehren_join_of_operationsfreigabe_moeglich_xor">
                <ns2:waypoint x="5525.0" y="3067.0"/>
                <ns2:waypoint x="5957.5" y="3067.0"/>
                <ns2:waypoint x="5957.5" y="2921.0"/>
                <ns2:waypoint x="6390.0" y="2921.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_cpr_aelter_als_7_tage_xor_join_of_bb_crp_par">
                <ns2:waypoint x="4230.0" y="2705.0"/>
                <ns2:waypoint x="4255.0" y="2705.0"/>
                <ns2:waypoint x="4255.0" y="2877.0"/>
                <ns2:waypoint x="4280.0" y="2877.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="4165.0" y="2791.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_kugelroentgen_durchfuehren_operationsfreigabe_moeglich_xor">
                <ns2:waypoint x="4470.0" y="2895.0"/>
                <ns2:waypoint x="4495.0" y="2895.0"/>
                <ns2:waypoint x="4495.0" y="2913.0"/>
                <ns2:waypoint x="4520.0" y="2913.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_operationsfreigabe_moeglich_xor_keine_operation_durchfuehren">
                <ns2:waypoint x="4560.0" y="2913.0"/>
                <ns2:waypoint x="4992.5" y="2913.0"/>
                <ns2:waypoint x="4992.5" y="3067.0"/>
                <ns2:waypoint x="5425.0" y="3067.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="4902.5" y="2990.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start94" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2180.0" y="2604.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_operations_indikation_erstellen" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2260.0" y="2579.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_aufnahme_des_patienten" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2410.0" y="2482.0" width="1010.0" height="270.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_einwilligung_des_patienten_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3470.0" y="2590.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_patient_einwilligung_erhalten_group" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="3560.0" y="2500.0" width="3010.0" height="710.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_keine_operation_durchfuehren_1" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="5015.0" y="2320.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_einwilligung_des_patienten_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="6620.0" y="2588.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end95" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="6710.0" y="2593.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_aufnahme_des_patienten_einwilligung_des_patienten_xor">
                <ns2:waypoint x="3420.0" y="2617.0"/>
                <ns2:waypoint x="3445.0" y="2617.0"/>
                <ns2:waypoint x="3445.0" y="2610.0"/>
                <ns2:waypoint x="3470.0" y="2610.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_keine_operation_durchfuehren_1_join_of_einwilligung_des_patienten_xor">
                <ns2:waypoint x="5115.0" y="2360.0"/>
                <ns2:waypoint x="5867.5" y="2360.0"/>
                <ns2:waypoint x="5867.5" y="2608.0"/>
                <ns2:waypoint x="6620.0" y="2608.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_operations_indikation_erstellen_aufnahme_des_patienten">
                <ns2:waypoint x="2360.0" y="2619.0"/>
                <ns2:waypoint x="2385.0" y="2619.0"/>
                <ns2:waypoint x="2385.0" y="2617.0"/>
                <ns2:waypoint x="2410.0" y="2617.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_einwilligung_des_patienten_xor_keine_operation_durchfuehren_1">
                <ns2:waypoint x="3510.0" y="2610.0"/>
                <ns2:waypoint x="4262.5" y="2610.0"/>
                <ns2:waypoint x="4262.5" y="2360.0"/>
                <ns2:waypoint x="5015.0" y="2360.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="4172.5" y="2485.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_einwilligung_des_patienten_xor_end95">
                <ns2:waypoint x="6660.0" y="2608.0"/>
                <ns2:waypoint x="6710.0" y="2608.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start94_operations_indikation_erstellen">
                <ns2:waypoint x="2210.0" y="2619.0"/>
                <ns2:waypoint x="2260.0" y="2619.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_einwilligung_des_patienten_xor_patient_einwilligung_erhalten_group">
                <ns2:waypoint x="3510.0" y="2610.0"/>
                <ns2:waypoint x="3535.0" y="2610.0"/>
                <ns2:waypoint x="3535.0" y="2855.0"/>
                <ns2:waypoint x="3560.0" y="2855.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="3445.0" y="2732.5" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_patient_einwilligung_erhalten_group_join_of_einwilligung_des_patienten_xor">
                <ns2:waypoint x="6570.0" y="2855.0"/>
                <ns2:waypoint x="6595.0" y="2855.0"/>
                <ns2:waypoint x="6595.0" y="2608.0"/>
                <ns2:waypoint x="6620.0" y="2608.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_data_operationseinwilligung" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2897.0" y="3240.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="df_id_aufnahme_des_patienten_data_operationseinwilligung">
                <ns2:waypoint x="2915.0" y="2752.0"/>
                <ns2:waypoint x="2915.0" y="3240.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start88" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1070.0" y="1685.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_start90" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1060.0" y="1815.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ct_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1150.0" y="1660.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_mri_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1140.0" y="1790.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end89" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1300.0" y="1685.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end91" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1290.0" y="1815.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_mri_durchfuehren_end91">
                <ns2:waypoint x="1240.0" y="1830.0"/>
                <ns2:waypoint x="1290.0" y="1830.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ct_durchfuehren_end89">
                <ns2:waypoint x="1250.0" y="1700.0"/>
                <ns2:waypoint x="1300.0" y="1700.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start88_ct_durchfuehren">
                <ns2:waypoint x="1100.0" y="1700.0"/>
                <ns2:waypoint x="1150.0" y="1700.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start90_mri_durchfuehren">
                <ns2:waypoint x="1090.0" y="1830.0"/>
                <ns2:waypoint x="1140.0" y="1830.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_start86" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="500.0" y="1883.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_standardroentgen_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="580.0" y="1858.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_ganzbeinroentgen_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="730.0" y="1860.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_par_szinitigraphie_xor_ct_mir" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="880.0" y="1885.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_xor_ct_mri" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="970.0" y="1570.0" width="420.0" height="360.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_szintigraphie_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1130.0" y="2030.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_par_szinitigraphie_xor_ct_mir" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1440.0" y="1889.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end87" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1530.0" y="1894.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_start86_standardroentgen_durchfuehren">
                <ns2:waypoint x="530.0" y="1898.0"/>
                <ns2:waypoint x="580.0" y="1898.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_szintigraphie_durchfuehren_join_of_par_szinitigraphie_xor_ct_mir">
                <ns2:waypoint x="1230.0" y="2070.0"/>
                <ns2:waypoint x="1335.0" y="2070.0"/>
                <ns2:waypoint x="1335.0" y="1909.0"/>
                <ns2:waypoint x="1440.0" y="1909.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_par_szinitigraphie_xor_ct_mir_xor_ct_mri">
                <ns2:waypoint x="920.0" y="1905.0"/>
                <ns2:waypoint x="945.0" y="1905.0"/>
                <ns2:waypoint x="945.0" y="1750.0"/>
                <ns2:waypoint x="970.0" y="1750.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_par_szinitigraphie_xor_ct_mir_szintigraphie_durchfuehren">
                <ns2:waypoint x="920.0" y="1905.0"/>
                <ns2:waypoint x="1025.0" y="1905.0"/>
                <ns2:waypoint x="1025.0" y="2070.0"/>
                <ns2:waypoint x="1130.0" y="2070.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_xor_ct_mri_join_of_par_szinitigraphie_xor_ct_mir">
                <ns2:waypoint x="1390.0" y="1750.0"/>
                <ns2:waypoint x="1415.0" y="1750.0"/>
                <ns2:waypoint x="1415.0" y="1909.0"/>
                <ns2:waypoint x="1440.0" y="1909.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_standardroentgen_durchfuehren_ganzbeinroentgen_durchfuehren">
                <ns2:waypoint x="680.0" y="1898.0"/>
                <ns2:waypoint x="705.0" y="1898.0"/>
                <ns2:waypoint x="705.0" y="1900.0"/>
                <ns2:waypoint x="730.0" y="1900.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_ganzbeinroentgen_durchfuehren_par_szinitigraphie_xor_ct_mir">
                <ns2:waypoint x="830.0" y="1900.0"/>
                <ns2:waypoint x="855.0" y="1900.0"/>
                <ns2:waypoint x="855.0" y="1905.0"/>
                <ns2:waypoint x="880.0" y="1905.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_par_szinitigraphie_xor_ct_mir_end87">
                <ns2:waypoint x="1480.0" y="1909.0"/>
                <ns2:waypoint x="1530.0" y="1909.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_join_of_laborbefunde_erheben" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1880.0" y="1049.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_konservative_therapie_and_no_arthrose_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="6820.0" y="1045.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_join_of_entlassung_nach_hause_moeglich" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="8120.0" y="953.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_end85" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="8240.0" y="302.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_entlassung_nach_hause_moeglich" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="6940.0" y="306.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_patient_wird_entlassen" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="7515.0" y="223.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_patient_wird_nicht_entlassen_grouping" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="7030.0" y="146.0" width="1070.0" height="450.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_data_entlassungspapiere" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="7547.0" y="690.0" width="36.0" height="50.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="df_id_patient_wird_entlassen_data_entlassungspapiere">
                <ns2:waypoint x="7565.0" y="304.0"/>
                <ns2:waypoint x="7565.0" y="690.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_entlassung_nach_hause_moeglich_patient_wird_entlassen">
                <ns2:waypoint x="6980.0" y="326.6619718309859"/>
                <ns2:waypoint x="7247.5" y="326.6619718309859"/>
                <ns2:waypoint x="7247.5" y="263.6619718309859"/>
                <ns2:waypoint x="7515.0" y="263.6619718309859"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="7157.5" y="295.1619718309859" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_entlassung_nach_hause_moeglich_patient_wird_nicht_entlassen_grouping">
                <ns2:waypoint x="6980.0" y="326.6619718309859"/>
                <ns2:waypoint x="7005.0" y="326.6619718309859"/>
                <ns2:waypoint x="7005.0" y="371.61971830985925"/>
                <ns2:waypoint x="7030.0" y="371.61971830985925"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="6915.0" y="349.1408450704226" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_entlassungsmanagement" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="0.0" y="0.0" width="8300.0" height="770.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_nierenfunktionsparameter_erheben" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1760.0" y="1043.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_crp_erheben" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1760.0" y="1148.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_blutbild_erheben" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1760.0" y="1096.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_gerinnung_erheben" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1760.0" y="991.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_laborbefunde_erheben" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="1670.0" y="1090.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_laborbefunde_erheben_gerinnung_erheben">
                <ns2:waypoint x="1710.0" y="1110.0"/>
                <ns2:waypoint x="1735.0" y="1110.0"/>
                <ns2:waypoint x="1735.0" y="1031.4084507042253"/>
                <ns2:waypoint x="1760.0" y="1031.4084507042253"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_laborbefunde_erheben_nierenfunktionsparameter_erheben">
                <ns2:waypoint x="1710.0" y="1110.0"/>
                <ns2:waypoint x="1735.0" y="1110.0"/>
                <ns2:waypoint x="1735.0" y="1083.8028169014085"/>
                <ns2:waypoint x="1760.0" y="1083.8028169014085"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_laborbefunde_erheben_blutbild_erheben">
                <ns2:waypoint x="1710.0" y="1110.0"/>
                <ns2:waypoint x="1735.0" y="1110.0"/>
                <ns2:waypoint x="1735.0" y="1136.1971830985917"/>
                <ns2:waypoint x="1760.0" y="1136.1971830985917"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_laborbefunde_erheben_crp_erheben">
                <ns2:waypoint x="1710.0" y="1110.0"/>
                <ns2:waypoint x="1735.0" y="1110.0"/>
                <ns2:waypoint x="1735.0" y="1188.5915492957747"/>
                <ns2:waypoint x="1760.0" y="1188.5915492957747"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_labor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="0.0" y="770.0" width="8300.0" height="680.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_radiologische_befunde_erheben" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="410.0" y="1480.0" width="1210.0" height="690.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_radiologie" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="0.0" y="1450.0" width="8300.0" height="750.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_orthopaedische_anamnese_durchfuehren" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="110.0" y="3255.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_start84" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="30.0" y="3280.0" width="30.0" height="30.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_konservative_therapie_veranlassen" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="4150.0" y="3410.0" width="590.0" height="950.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_klinische_untersuchung" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="260.0" y="3255.0" width="100.0" height="80.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_operation_grouping" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2090.0" y="2230.0" width="4710.0" height="1080.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNShape bpmnElement="id_konservative_therapie_and_no_arthrose_xor" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="2000.0" y="3296.0" width="40.0" height="40.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_konservative_therapie_and_no_arthrose_xor_operation_grouping">
                <ns2:waypoint x="2040.0" y="3316.0"/>
                <ns2:waypoint x="2065.0" y="3316.0"/>
                <ns2:waypoint x="2065.0" y="2770.0000000000005"/>
                <ns2:waypoint x="2090.0" y="2770.0000000000005"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="1975.0" y="3043.0" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_konservative_therapie_and_no_arthrose_xor_konservative_therapie_veranlassen">
                <ns2:waypoint x="2040.0" y="3316.0"/>
                <ns2:waypoint x="3095.0" y="3316.0"/>
                <ns2:waypoint x="3095.0" y="3885.0"/>
                <ns2:waypoint x="4150.0" y="3885.0"/>
                <ns4:BPMNLabel>
                    <ns3:Bounds x="3005.0" y="3600.5" width="90.0" height="30.0"/>
                </ns4:BPMNLabel>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_start84_orthopaedische_anamnese_durchfuehren">
                <ns2:waypoint x="60.0" y="3295.0"/>
                <ns2:waypoint x="110.0" y="3295.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_orthopaedische_anamnese_durchfuehren_klinische_untersuchung">
                <ns2:waypoint x="210.0" y="3295.0"/>
                <ns2:waypoint x="260.0" y="3295.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNShape bpmnElement="id_orthopaedie" isHorizontal="true" isExpanded="true">
                <ns3:Bounds x="0.0" y="2200.0" width="8300.0" height="2190.0"/>
            </ns4:BPMNShape>
            <ns4:BPMNEdge bpmnElement="sf_crp_erheben_join_of_laborbefunde_erheben">
                <ns2:waypoint x="1860.0" y="1188.5915492957747"/>
                <ns2:waypoint x="1870.0" y="1188.5915492957747"/>
                <ns2:waypoint x="1870.0" y="1069.0"/>
                <ns2:waypoint x="1880.0" y="1069.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_patient_wird_nicht_entlassen_grouping_join_of_entlassung_nach_hause_moeglich">
                <ns2:waypoint x="8100.0" y="371.6197183098592"/>
                <ns2:waypoint x="8110.0" y="371.6197183098592"/>
                <ns2:waypoint x="8110.0" y="973.0"/>
                <ns2:waypoint x="8120.0" y="973.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_laborbefunde_erheben_konservative_therapie_and_no_arthrose_xor">
                <ns2:waypoint x="1920.0" y="1069.0"/>
                <ns2:waypoint x="1960.0" y="1069.0"/>
                <ns2:waypoint x="1960.0" y="3316.0"/>
                <ns2:waypoint x="2000.0" y="3316.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_nierenfunktionsparameter_erheben_join_of_laborbefunde_erheben">
                <ns2:waypoint x="1860.0" y="1083.8028169014085"/>
                <ns2:waypoint x="1870.0" y="1083.8028169014085"/>
                <ns2:waypoint x="1870.0" y="1069.0"/>
                <ns2:waypoint x="1880.0" y="1069.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_operation_grouping_join_of_konservative_therapie_and_no_arthrose_xor">
                <ns2:waypoint x="6800.0" y="2770.0"/>
                <ns2:waypoint x="6810.0" y="2770.0"/>
                <ns2:waypoint x="6810.0" y="1065.0"/>
                <ns2:waypoint x="6820.0" y="1065.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_blutbild_erheben_join_of_laborbefunde_erheben">
                <ns2:waypoint x="1860.0" y="1136.1971830985917"/>
                <ns2:waypoint x="1870.0" y="1136.1971830985917"/>
                <ns2:waypoint x="1870.0" y="1069.0"/>
                <ns2:waypoint x="1880.0" y="1069.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_gerinnung_erheben_join_of_laborbefunde_erheben">
                <ns2:waypoint x="1860.0" y="1031.4084507042253"/>
                <ns2:waypoint x="1870.0" y="1031.4084507042253"/>
                <ns2:waypoint x="1870.0" y="1069.0"/>
                <ns2:waypoint x="1880.0" y="1069.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_konservative_therapie_and_no_arthrose_xor_entlassung_nach_hause_moeglich">
                <ns2:waypoint x="6860.0" y="1065.0"/>
                <ns2:waypoint x="6900.0" y="1065.0"/>
                <ns2:waypoint x="6900.0" y="326.6619718309859"/>
                <ns2:waypoint x="6940.0" y="326.6619718309859"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_konservative_therapie_veranlassen_join_of_konservative_therapie_and_no_arthrose_xor">
                <ns2:waypoint x="4740.0" y="3885.0"/>
                <ns2:waypoint x="5780.0" y="3885.0"/>
                <ns2:waypoint x="5780.0" y="1065.0"/>
                <ns2:waypoint x="6820.0" y="1065.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_klinische_untersuchung_radiologische_befunde_erheben">
                <ns2:waypoint x="360.0" y="3295.0"/>
                <ns2:waypoint x="385.0" y="3295.0"/>
                <ns2:waypoint x="385.0" y="1825.0"/>
                <ns2:waypoint x="410.0" y="1825.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_radiologische_befunde_erheben_laborbefunde_erheben">
                <ns2:waypoint x="1620.0" y="1825.0"/>
                <ns2:waypoint x="1645.0" y="1825.0"/>
                <ns2:waypoint x="1645.0" y="1110.0"/>
                <ns2:waypoint x="1670.0" y="1110.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_patient_wird_entlassen_join_of_entlassung_nach_hause_moeglich">
                <ns2:waypoint x="7615.0" y="263.6619718309859"/>
                <ns2:waypoint x="7867.5" y="263.6619718309859"/>
                <ns2:waypoint x="7867.5" y="973.0"/>
                <ns2:waypoint x="8120.0" y="973.0"/>
            </ns4:BPMNEdge>
            <ns4:BPMNEdge bpmnElement="sf_join_of_entlassung_nach_hause_moeglich_end85">
                <ns2:waypoint x="8160.0" y="973.0"/>
                <ns2:waypoint x="8200.0" y="973.0"/>
                <ns2:waypoint x="8200.0" y="317.7887323943662"/>
                <ns2:waypoint x="8240.0" y="317.7887323943662"/>
            </ns4:BPMNEdge>
        </ns4:BPMNPlane>
    </ns4:BPMNDiagram>
</definitions>

```

<script type="text/javascript" src="../js/highlight.js"></script>