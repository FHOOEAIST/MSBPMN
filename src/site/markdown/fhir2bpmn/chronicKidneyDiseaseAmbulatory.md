
# Chronic Kidney Disease - Ambulatory

Transformation of an adapted version of the Chronic Kidney Disease - Ambulatory process as defined [here](http://hl7.org/fhir/uv/cpg/2019Sep/PlanDefinition-cc-cpg-plan-ckd.xml.html).

## Discussions

 * What is the general idea of this PlanDefinition?
 * Does this show a process sequence or just different departments and how they are organized?

## Graph

<script type="text/javascript" src="https://unpkg.com/bpmn-js@7.2.1/dist/bpmn-navigated-viewer.production.min.js"></script>
Usage: Mouse Click + Move: Scroll around. Ctrl + Mouse Wheel: Zoom.

<div id="container" style="width: 100%; height: 500px; border: 1px solid lightgray; overflow:auto;"></div>

<script type="text/javascript" src="../js/fhir2bpmn/chronicKidneyDiseaseAmbulatory.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.0.0/styles/darcula.min.css"></link>
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.0.0/highlight.min.js"></script>


## HL7 FHIR

```xml
<!--
  ~ Copyright (c) 2020 the original author or authors.
  ~ DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
  ~
  ~ This Source Code Form is subject to the terms of the Mozilla Public
  ~ License, v. 2.0. If a copy of the MPL was not distributed with this
  ~ file, You can obtain one at https://mozilla.org/MPL/2.0/.
  -->

<PlanDefinition xmlns="http://hl7.org/fhir">
    <id value="cc-cpg-plan-ckd"/>
    <text>
        <status value="generated"/>
        <div xmlns="http://www.w3.org/1999/xhtml"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: cc-cpg-plan-ckd</p><p><b>url</b>: <a href="http://hl7.org/fhir/uv/cpg/PlanDefinition/cc-cpg-plan-ckd">http://hl7.org/fhir/uv/cpg/PlanDefinition/cc-cpg-plan-ckd</a></p><p><b>version</b>: 0.1.0</p><p><b>name</b>: ChronicKidneyDiseaseAmbulatory</p><p><b>title</b>: Chronic Kidney Disease - Ambulatory</p><p><b>type</b>: Order Set <span style="background: LightGoldenRodYellow">(Details : {http://terminology.hl7.org/CodeSystem/plan-definition-type code 'order-set' = 'Order Set', given as 'Order Set'})</span></p><p><b>status</b>: draft</p><p><b>experimental</b>: true</p><p><b>date</b>: Jul 27, 2019 8:00:00 PM</p><p><b>publisher</b>: HL7 FHIR Clinical Guidelines Example Artifact</p><p><b>description</b>: Chronic Kidney Disease - Ambulatory</p><p><b>useContext</b>: </p><p><b>jurisdiction</b>: World <span style="background: LightGoldenRodYellow">(Details : {http://unstats.un.org/unsd/methods/m49/m49.htm code '001' = 'World', given as 'World'})</span></p><p><b>copyright</b>: Copyright © Elsevier, and others.</p><p><b>approvalDate</b>: Jul 28, 2019</p><p><b>lastReviewDate</b>: Jul 28, 2019</p><p><b>effectivePeriod</b>: Jul 28, 2019 12:00:00 AM --&gt; (ongoing)</p><p><b>topic</b>: Treatment <span style="background: LightGoldenRodYellow">(Details : {http://terminology.hl7.org/CodeSystem/definition-topic code 'treatment' = 'Treatment', given as 'Treatment'})</span></p><p><b>relatedArtifact</b>: </p><p><b>library</b>: <a href="Library/ckd-recommendations">Library/ckd-recommendations</a></p><blockquote><p><b>action</b></p><p><b>id</b>: 34387235</p><p><b>title</b>: Assessment Scales</p><p><b>groupingBehavior</b>: visual-group</p><h3>Actions</h3><table class="grid"><tr><td>-</td></tr><tr><td>*</td></tr></table></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 34387242</p><p><b>title</b>: General Care</p><p><b>groupingBehavior</b>: visual-group</p><h3>Actions</h3><table class="grid"><tr><td>-</td></tr><tr><td>*</td></tr></table></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 34387251</p><p><b>title</b>: Medications</p><p><b>documentation</b>: </p><p><b>groupingBehavior</b>: visual-group</p><h3>Actions</h3><table class="grid"><tr><td>-</td></tr><tr><td>*</td></tr><tr><td>*</td></tr><tr><td>*</td></tr></table></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 34387287</p><p><b>title</b>: Laboratory</p><p><b>groupingBehavior</b>: visual-group</p><h3>Actions</h3><table class="grid"><tr><td>-</td></tr><tr><td>*</td></tr><tr><td>*</td></tr></table></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: 34387217</p><p><b>title</b>: Radiology</p><p><b>groupingBehavior</b>: visual-group</p><h3>Actions</h3><table class="grid"><tr><td>-</td></tr><tr><td>*</td></tr></table></blockquote><blockquote><p><b>action</b></p><p><b>id</b>: cc-cpg-activity-referral-nephrology</p><p><b>title</b>: Referrals</p><p><b>groupingBehavior</b>: visual-group</p><h3>Actions</h3><table class="grid"><tr><td>-</td></tr><tr><td>*</td></tr><tr><td>*</td></tr></table></blockquote></div>
    </text>
    <url value="http://hl7.org/fhir/uv/cpg/PlanDefinition/cc-cpg-plan-ckd"/>
    <version value="0.1.0"/>
    <name value="ChronicKidneyDiseaseAmbulatory"/>
    <title value="Chronic Kidney Disease - Ambulatory"/>
    <type>
        <coding>
            <system value="http://terminology.hl7.org/CodeSystem/plan-definition-type"/>
            <code value="order-set"/>
            <display value="Order Set"/>
        </coding>
    </type>
    <status value="draft"/>
    <experimental value="true"/>
    <date value="2019-07-27T20:00:00-04:00"/>
    <publisher value="HL7 FHIR Clinical Guidelines Example Artifact"/>
    <description value="Chronic Kidney Disease - Ambulatory"/>
    <useContext>
        <code>
            <system value="http://terminology.hl7.org/CodeSystem/usage-context-type"/>
            <code value="focus"/>
            <display value="Clinical Focus"/>
        </code>
        <valueCodeableConcept>
            <coding>
                <system value="http://snomed.info/sct"/>
                <code value="709044004"/>
                <display value="Chronic kidney disease (disorder)"/>
            </coding>
        </valueCodeableConcept>
    </useContext>
    <jurisdiction>
        <coding>
            <system value="http://unstats.un.org/unsd/methods/m49/m49.htm"/>
            <code value="001"/>
            <display value="World"/>
        </coding>
    </jurisdiction>
    <copyright value="Copyright © Elsevier, and others."/>
    <approvalDate value="2019-07-28"/>
    <lastReviewDate value="2019-07-28"/>
    <effectivePeriod>
        <start value="2019-07-28T00:00:00-04:00"/>
    </effectivePeriod>
    <topic>
        <coding>
            <system value="http://terminology.hl7.org/CodeSystem/definition-topic"/>
            <code value="treatment"/>
            <display value="Treatment"/>
        </coding>
        <text value="Treatment"/>
    </topic>
    <relatedArtifact>
        <type value="justification"/>
        <display
                value="&lt;p&gt;SYNOPSIS - Chronic Kidney Disease&lt;/p&gt;

&lt;ul&gt;
	&lt;li&gt;
	&lt;p&gt;KEY POINTS&lt;/p&gt;

	&lt;ul&gt;
		&lt;li&gt;Decline in function of the kidney characterized by at least 3 months of reduced GFR (less than 60 mL/minute/ 1.73 m&amp;sup2;) or at least 3 months of structural or functional kidney damage&lt;/li&gt;
		&lt;li&gt;Assessment of both GFR and albuminuria is necessary to diagnose chronic kidney disease and monitor disease progression&lt;/li&gt;
		&lt;li&gt;GFR is most commonly estimated through measuring serum creatinine and the use of GFR estimating equations, either the Modification of Diet in Renal Disease Study equation or the Chronic Kidney Disease Epidemiology Collaboration equation&lt;/li&gt;
		&lt;li&gt;Albuminuria is measured by urine albumin/creatinine ratio; greater than 30 mg/g indicates albuminuria&lt;/li&gt;
		&lt;li&gt;Chronic kidney disease is commonly associated with hypertension, diabetes, and cardiovascular disease&lt;/li&gt;
		&lt;li&gt;First line therapy includes ACE inhibitors and/or angiotensin II receptor blockers to reduce albuminuria and hypertension&lt;/li&gt;
		&lt;li&gt;If left untreated, chronic kidney disease can progress to end-stage renal disease requiring dialysis or renal transplant
		&lt;ul&gt;
			&lt;li&gt;Symptoms of end-stage renal disease (eg, pruritus, refractory electrolyte imbalances, metabolic acidosis, severe nausea, neurologic impairments) typically occur when GFR is 5 to 10 mL/minute/1.73 m&amp;sup2;&lt;/li&gt;
		&lt;/ul&gt;
		&lt;/li&gt;
		&lt;li&gt;Carefully monitor electrolyte levels, hemoglobin, parathyroid hormone levels, and sodium bicarbonate levels to detect complications of chronic kidney disease, including cardiovascular disease, anemia, bone mineral disease, and metabolic acidosis&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;
	&lt;p&gt;URGENT ACTION&lt;/p&gt;

	&lt;ul&gt;
		&lt;li&gt;Hyperkalemia may require urgent treatment in patients being treated for chronic kidney disease
		&lt;ul&gt;
			&lt;li&gt;Urgent treatment consists of calcium chloride or calcium gluconate and regimens of sodium bicarbonate, glucose and insulin, or nebulized albuterol&lt;/li&gt;
		&lt;/ul&gt;
		&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;
	&lt;p&gt;PITFALLS&lt;/p&gt;

	&lt;ul&gt;
		&lt;li&gt;Early stages are often asymptomatic, causing chronic kidney disease to be untreated, leading to further progression of kidney damage and worse prognosis&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;"/>
        <citation
                value="Chronic Kidney Disease Clinical Overview. ClinicalKey. &lt;a href=&#39;https://www.clinicalkey.com/#!/content/clinical_overview/67-s2.0-26a91efc-ff97-4e11-b49f-c70331e79cee&#39; target=&#39;_blank&#39;&gt;Source&lt;/a&gt;"/>
        <url
                value="http://himss19.ordersetsmanager.com/evidenceviewer/#/7CBBBE9B75E10232E05352E3610A5325/7CBBBE9B75E10232E05352E3610A5325"/>
    </relatedArtifact>
    <library value="Library/ckd-recommendations"/>
    <action id="id_34387235">
        <title value="Assessment Scales"/>
        <groupingBehavior value="visual-group"/>
        <action id="id_34387238">
            <title value="GFR Equations and Classification according to GFR Category"/>
            <description value="Nonorderable narrative"/>
            <textEquivalent
                    value="GFR Equations and Classification according to GFR Category"/>
            <documentation>
                <type value="justification"/>
                <display
                        value="&lt;p&gt;Obtain serum creatinine for evaluation of GFR&lt;/p&gt;

&lt;ul&gt;
	&lt;li&gt;Estimate GFR from serum creatinine using 1 of 2 equations
	&lt;ul&gt;
		&lt;li&gt;Chronic Kidney Disease Epidemiology Collaboration equation is preferred for reporting estimated GFR; more accurately represents true GFR, especially at GFR above 60 mL/minute/1.73 m&amp;sup2;&lt;/li&gt;
		&lt;li&gt;Modification of Diet in Renal Disease equation underestimates true GFR in patients with GFR above 60 mL/minute/1.73 m&amp;sup2;
		&lt;ul&gt;
			&lt;li&gt;Less accurate than the Chronic Kidney Disease Epidemiology Collaboration equation, though still widely used by many laboratories&lt;/li&gt;
		&lt;/ul&gt;
		&lt;/li&gt;
		&lt;li&gt;A GFR calculator (using the Chronic Kidney Disease Epidemiology Collaboration equation) is available from the&amp;nbsp;National Kidney Foundation&amp;nbsp;and the&amp;nbsp;National Institute of Diabetes and Digestive and Kidney Diseases&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;Gold standard is to measure clearance of continuously infused inulin over 24 hours; however, this is neither practical nor cost effective&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;If GFR is suspected to be inaccurate (eg, severe malnutrition, paraplegia, amputated extremity) testing involves a 24-hour urine collection&lt;/li&gt;
&lt;/ul&gt;

&lt;p&gt;&amp;nbsp;&lt;/p&gt;

&lt;p&gt;GFR equations&lt;/p&gt;

&lt;ul&gt;
	&lt;li&gt;Chronic Kidney Disease Epidemiology Collaboration equation&lt;a target=&quot;_blank&quot; href=&quot;https://pocauthoring.elsevier.com/coeditor/document/26a91efc-ff97-4e11-b49f-c70331e79cee/preview#reference-cbb61812-55e8-48a8-9bca-e7c9be225e6f&quot;&gt;&lt;sup&gt;43&lt;/sup&gt;&lt;/a&gt;

	&lt;ul&gt;
		&lt;li&gt;GFR (mL/minute/1.73 m&amp;sup2;) = 141 &amp;times; min(Scr/&amp;kappa;, 1)^&amp;alpha; &amp;times; max(Scr/&amp;kappa;, 1)^-1.209 &amp;times; 0.993^Age &amp;times; 1.018 [if female] &amp;times; 1.159 [if black]
		&lt;ul&gt;
			&lt;li&gt;Scr = serum creatinine&lt;/li&gt;
			&lt;li&gt;&amp;kappa; = 61.9 for females and 79.6 for males&lt;/li&gt;
			&lt;li&gt;&amp;alpha; = -0.329 for females and -0.411 for males&lt;/li&gt;
			&lt;li&gt;Min indicates the minimum of Scr/&amp;kappa; or 1&lt;/li&gt;
			&lt;li&gt;Max indicates the maximum of Scr/&amp;kappa; or 1&lt;/li&gt;
		&lt;/ul&gt;
		&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;Modification of Diet in Renal Disease equation
	&lt;ul&gt;
		&lt;li&gt;GFR (mL/minute/1.73 m&amp;sup2;) = 175 &amp;times; (Scr)^-1.154 &amp;times; (age)^-0.203 &amp;times; 0.742 [if female] &amp;times; 1.212 [if black]&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;

&lt;p&gt;&amp;nbsp;&lt;/p&gt;

&lt;p&gt;Classification according to GFR category&lt;/p&gt;

&lt;ul&gt;
	&lt;li&gt;G1: normal or high renal function
	&lt;ul&gt;
		&lt;li&gt;GFR: greater than 90 mL/minute/1.73 m&amp;sup2;&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;G2: mildly decreased renal function
	&lt;ul&gt;
		&lt;li&gt;GFR: 60 to 89 mL/minute/1.73 m&amp;sup2;&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;G3a: mildly to moderately decreased renal function
	&lt;ul&gt;
		&lt;li&gt;GFR: 45 to 59 mL/minute/1.73 m&amp;sup2;&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;G3b: moderately to severely decreased renal function
	&lt;ul&gt;
		&lt;li&gt;GFR: 30 to 44 mL/minute/1.73 m&amp;sup2;&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;G4: severely decreased renal function
	&lt;ul&gt;
		&lt;li&gt;GFR: 15 to 29 mL/minute/1.73 m&amp;sup2;&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;G5: kidney failure
	&lt;ul&gt;
		&lt;li&gt;GFR: less than 15 mL/minute/1.73 m&amp;sup2;&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;Combined GFR and albuminuria stage more accurately denotes the risk of progression of chronic kidney disease&lt;/li&gt;
&lt;/ul&gt;

&lt;p&gt;&amp;nbsp;&lt;/p&gt;

&lt;p&gt;Verify chronicity of kidney disease&lt;/p&gt;

&lt;ul&gt;
	&lt;li&gt;If GFR less than 60 mL/minute/1.73 m&amp;sup2; (GFR categories G3a-G5) or markers of kidney damage present, review history and previous measurements to determine duration of kidney disease
	&lt;ul&gt;
		&lt;li&gt;If duration is greater than 3 months, chronic kidney disease is confirmed&lt;/li&gt;
		&lt;li&gt;If duration is less than 3 months or unclear, chronic kidney disease is not confirmed; patients may have chronic kidney disease or acute kidney diseases (including acute kidney injury) or both, and tests should be repeated accordingly&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;"/>
                <citation
                        value="Chronic Kidney Disease Clinical Overview. ClinicalKey. &lt;a href=&#39;https://www.clinicalkey.com/#!/content/clinical_overview/67-s2.0-26a91efc-ff97-4e11-b49f-c70331e79cee&#39; target=&#39;_blank&#39;&gt;Source&lt;/a&gt;"/>
                <url
                        value="http://himss19.ordersetsmanager.com/evidenceviewer/#/7CBBBE9B75E10232E05352E3610A5325/7CBBBE9B75E80232E05352E3610A5325"/>
            </documentation>
            <requiredBehavior value="could"/>
            <precheckBehavior value="no"/>
        </action>
		<relatedAction> <!-- added -->
			<actionId value="id_34387242"/>  <!-- added -->
			<relationship value="before-start"/>  <!-- added -->
		</relatedAction>
    </action>
    <action id="id_34387242">
        <title value="General Care"/>
        <groupingBehavior value="visual-group"/>
        <action id="id_34387243">
            <title value="Patient Education"/>
            <documentation>
                <type value="justification"/>
                <display
                        value="&lt;p&gt;To improve long-term outcomes, lifestyle modifications to&amp;nbsp;lower&amp;nbsp;blood pressure to less than 140/90 mmHg should be made for those who are NOT at high risk for cardiovascular disease.&lt;/p&gt;

&lt;p&gt;&amp;nbsp;&lt;/p&gt;

&lt;p&gt;The modifications include:&lt;/p&gt;

&lt;ul&gt;
	&lt;li&gt;Weight reduction, if indicated, and maintaining a healthy weight with body mass index (BMI) of 20 to 25&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;Reducing salt intake to 2 g per day of sodium, unless contraindicated&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;Following a regular exercise program aiming for at least 30 minutes, 5 times a week, depending on cardiovascular tolerance&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;Alcohol intake not more than 2 standard drinks per day for men and one standard drink per day for women&lt;/li&gt;
&lt;/ul&gt;"/>
                <citation
                        value="Kidney Disease: Improving Global Outcomes (KDIGO) Blood Pressure Work Group. KDIGO clinical practice guideline for the management of blood pressure in chronic kidney disease. Kidney Int. 2012;2 (Suppl), 337-414. &lt;a href=&#39;http://www.kdigo.org/clinical_practice_guidelines/pdf/KDIGO_BP_GL.pdf&#39; target=&#39;_blank&#39;&gt;Source&lt;/a&gt;"/>
                <url
                        value="http://himss19.ordersetsmanager.com/evidenceviewer/#/7CBBBE9B75E10232E05352E3610A5325/7CBBBE9B75ED0232E05352E3610A5325"/>
            </documentation>
            <groupingBehavior value="visual-group"/>
            <action id="cc_cpg_activity_edu_hypertension">
                <title
                        value="Patient education: Managing Your High Blood Pressure (Hypertension)"/>
                <description value="General Care"/>
                <requiredBehavior value="could"/>
                <precheckBehavior value="no"/>
				<relatedAction> <!-- added -->
					<actionId value="cc_cpg_activity_edu_renal_diet"/>  <!-- added -->
					<relationship value="before-start"/>  <!-- added -->
				</relatedAction>
            </action>
            <action id="cc_cpg_activity_edu_renal_diet">
                <title value="Patient education: Renal diet"/>
                <description value="General Care"/>
                <requiredBehavior value="could"/>
                <precheckBehavior value="no"/>
            </action>
        </action>
		<relatedAction> <!-- added -->
			<actionId value="id_34387251"/>  <!-- added -->
			<relationship value="before-start"/>  <!-- added -->
		</relatedAction>
    </action>
    <action id="id_34387251">
        <title value="Medications"/>
        <documentation>
            <type value="justification"/>
            <display
                    value="&lt;p&gt;Drug therapy&lt;/p&gt;

&lt;ul&gt;
	&lt;li&gt;Select drug dosages based on GFR, and carefully monitor kidney function when prescribing nephrotoxic medications, as change in renal function alters drug metabolism&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;Consult the Kidney Disease: Improving Global Outcomes Conference report for detailed dosing considerations and strategies for acute and chronic kidney disease&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;Considerations for drugs commonly used by patients with chronic kidney disease
	&lt;ul&gt;
		&lt;li&gt;ACE inhibitors
		&lt;ul&gt;
			&lt;li&gt;Used to reduce blood pressure in the kidneys and reduce albuminurin&lt;/li&gt;
			&lt;li&gt;Dual therapy with angiotensin receptor blockers is not recommended&lt;/li&gt;
			&lt;li&gt;Use lower dose in patients with GFR less than 45 mL/minute/1.73 m&amp;sup2;; do not routinely discontinue when GFR is less than 30 mL/minute/1.73 m&amp;sup2; (remains nephroprotective)&lt;/li&gt;
			&lt;li&gt;Follow serum potassium&lt;/li&gt;
		&lt;/ul&gt;
		&lt;/li&gt;
		&lt;li&gt;Angiotensin receptor blockers
		&lt;ul&gt;
			&lt;li&gt;Used to reduce blood pressure in the kidneys and reduce albuminuria&lt;/li&gt;
			&lt;li&gt;Dual therapy with ACE inhibitor is not recommended&lt;/li&gt;
			&lt;li&gt;Use lower dose in patients with GFR less than 45 mL/minute/1.73 m&amp;sup2;; do not routinely discontinue when GFR is less than 30 mL/minute/1.73 m&amp;sup2; (remains nephroprotective)&lt;/li&gt;
			&lt;li&gt;Follow serum potassium&lt;/li&gt;
		&lt;/ul&gt;
		&lt;/li&gt;
		&lt;li&gt;Calcium channel blockers
		&lt;ul&gt;
			&lt;li&gt;Can be used in combination with ACE inhibitor or angiotensin receptor blocker to control hypertension&lt;/li&gt;
			&lt;li&gt;3 main classes
			&lt;ul&gt;
				&lt;li&gt;Benzothiazepines (diltiazem)
				&lt;ul&gt;
					&lt;li&gt;Preferred over dihydropyridines because of an antiproteinuric effect&lt;/li&gt;
				&lt;/ul&gt;
				&lt;/li&gt;
				&lt;li&gt;Phenylalkylamines (verapamil)
				&lt;ul&gt;
					&lt;li&gt;Preferred over dihydropyridines because it has an antiproteinuric effect (no clear indication to discriminate use of benzothiazepines versus phenylalkylamines)&lt;/li&gt;
				&lt;/ul&gt;
				&lt;/li&gt;
				&lt;li&gt;Dihydropyridines (eg, nifedipine, amlodipine)&lt;/li&gt;
			&lt;/ul&gt;
			&lt;/li&gt;
			&lt;li&gt;Avoid prescribing calcium channel blockers without ACE inhibitor or angiotensin II receptor blocker, as sole use can lead to increased hyperfiltration and increased albuminuria&lt;/li&gt;
		&lt;/ul&gt;
		&lt;/li&gt;
		&lt;li&gt;Aldosterone receptor antagonists
		&lt;ul&gt;
			&lt;li&gt;Spironolactone (nonselective)
			&lt;ul&gt;
				&lt;li&gt;Carefully monitor for hyperkalemia&lt;/li&gt;
			&lt;/ul&gt;
			&lt;/li&gt;
			&lt;li&gt;Eplerenone (selective)
			&lt;ul&gt;
				&lt;li&gt;Carefully monitor for hyperkalemia&lt;/li&gt;
			&lt;/ul&gt;
			&lt;/li&gt;
		&lt;/ul&gt;
		&lt;/li&gt;
		&lt;li&gt;Antidiabetic agents
		&lt;ul&gt;
			&lt;li&gt;Choice of therapy depends on type of diabetes, degree of glycemic control needed, and level of current kidney function&lt;/li&gt;
			&lt;li&gt;Insulin
			&lt;ul&gt;
				&lt;li&gt;May need dose reduction when GFR is less than 30 mL/minute/1.73 m&amp;sup2; to avoid hypoglycemia as insulin is partly renally excreted&lt;/li&gt;
				&lt;li&gt;No evidence-based guidelines or recommendations exist specifying which types of insulin to use or avoid depending on severity of chronic kidney disease&lt;/li&gt;
			&lt;/ul&gt;
			&lt;/li&gt;
			&lt;li&gt;Sulfonylureas
			&lt;ul&gt;
				&lt;li&gt;First-generation sulfonylureas are contraindicated as they are affected by kidney function and increase risks of hypoglycemia&lt;/li&gt;
				&lt;li&gt;Glipizide
				&lt;ul&gt;
					&lt;li&gt;Second-generation sulfonylurea; preferred in patients with chronic kidney disease as it is metabolized primarily in the liver&lt;/li&gt;
				&lt;/ul&gt;
				&lt;/li&gt;
			&lt;/ul&gt;
			&lt;/li&gt;
			&lt;li&gt;Biguanides
			&lt;ul&gt;
				&lt;li&gt;Metformin
				&lt;ul&gt;
					&lt;li&gt;Relatively contraindicated when GFR is less than 30 mL/minute/1.73 m&amp;sup2; as there is a risk of lactic acidosis; consider risk-benefit if GFR is stable&lt;/li&gt;
				&lt;/ul&gt;
				&lt;/li&gt;
			&lt;/ul&gt;
			&lt;/li&gt;
		&lt;/ul&gt;
		&lt;/li&gt;
		&lt;li&gt;Diuretics
		&lt;ul&gt;
			&lt;li&gt;Monitor for hyperkalemia and hypotension as diuretics can cause fluid imbalance resulting in electrolyte level disparities&lt;/li&gt;
			&lt;li&gt;Thiazide
			&lt;ul&gt;
				&lt;li&gt;Once daily recommended in patients with GFR of 30 mL/minute/1.73 m&amp;sup2; or higher (categories G1-G3)&lt;/li&gt;
			&lt;/ul&gt;
			&lt;/li&gt;
			&lt;li&gt;Loop diuretics
			&lt;ul&gt;
				&lt;li&gt;Once or twice daily recommended in patients with GFR less than 30 mL/minute/1.73 m&amp;sup2; (categories G4-G5)&lt;/li&gt;
			&lt;/ul&gt;
			&lt;/li&gt;
		&lt;/ul&gt;
		&lt;/li&gt;
		&lt;li&gt;Analgesics
		&lt;ul&gt;
			&lt;li&gt;Acetaminophen is the analgesic recommended for short-term treatment of mild to moderate pain in patients with stages 3 to 5 chronic kidney disease; considered analgesic of choice for all patients with chronic kidney disease&lt;/li&gt;
			&lt;li&gt;NSAIDs may be used for short-term therapy in patients up to stage 3 chronic kidney disease, with regular monitoring of renal function&lt;/li&gt;
		&lt;/ul&gt;
		&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;"/>
            <citation
                    value="Chronic Kidney Disease Clinical Overview. ClinicalKey. &lt;a href=&#39;https://www.clinicalkey.com/#!/content/clinical_overview/67-s2.0-26a91efc-ff97-4e11-b49f-c70331e79cee&#39; target=&#39;_blank&#39;&gt;Source&lt;/a&gt;"/>
            <url
                    value="http://himss19.ordersetsmanager.com/evidenceviewer/#/7CBBBE9B75E10232E05352E3610A5325/7CBBBE9B75F50232E05352E3610A5325"/>
        </documentation>
        <groupingBehavior value="visual-group"/>
        <action id="id_34387256">
            <title value="Antihypertensives"/>
            <documentation>
                <type value="justification"/>
                <display
                        value="&lt;p&gt;According to the Joint National Committee-8, establish a target blood pressure of 140/90 mmHg for patients with&amp;nbsp;chronic kidney disease patients (expert opinion, grade E).&amp;nbsp;&amp;nbsp;&lt;/p&gt;

&lt;ul&gt;
	&lt;li&gt;According to the American College of Physicians,&amp;nbsp;select either an ACE&amp;nbsp;inhibitor (moderate-quality evidence) or an angiotensin II-receptor blocker (high-quality evidence) for patients with hypertension and stage 1 to 3 chronic kidney disease.&lt;/li&gt;
	&lt;li&gt;After starting an ACE&amp;nbsp;inhibitor, measure the short-term follow-up creatinine level&amp;nbsp;and use the results to prompt further attention if it shows a rise of greater than 30%.
	&lt;ul&gt;
		&lt;li&gt;Consider alternative causes of acute kidney injury as well as renal artery stenosis.&lt;/li&gt;
		&lt;li&gt;Consider stopping the ACE inhibitor or&amp;nbsp;angiotensin II receptor blocker​&amp;nbsp;medication.&amp;nbsp;&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;"/>
                <citation
                        value="Kidney Disease: Improving Global Outcomes (KDIGO) Blood Pressure Work Group. KDIGO clinical practice guideline for the management of blood pressure in chronic kidney disease. Kidney Int. 2012;2 (Suppl), 337-414. &lt;a href=&#39;http://www.kdigo.org/clinical_practice_guidelines/pdf/KDIGO_BP_GL.pdf&#39; target=&#39;_blank&#39;&gt;Source&lt;/a&gt;

Qaseem A, Hopkins, RH Jr, et al. Screening, monitoring, and treatment of stage 1 to 3 chronic kidney disease: a clinical practice guideline from the American College of Physicians. Ann Intern Med. 2013;&lt;a href=&#39;http://annals.org/article.aspx?articleid=1757302&#39; target=&#39;_blank&#39;&gt;Source&lt;/a&gt;

James P, Oparil S, Carter B, et al. 2014 Evidence-Based Guideline for the Management of High Blood Pressure in Adults. Report From the Panel Members Appointed to the Eighth Joint National Committee (JNC 8). JAMA. 2014;311(5), 507-520. &lt;a href=&#39;http://jama.jamanetwork.com/article.aspx?articleid=1791497&#39; target=&#39;_blank&#39;&gt;Source&lt;/a&gt;"/>
                <url
                        value="http://himss19.ordersetsmanager.com/evidenceviewer/#/7CBBBE9B75E10232E05352E3610A5325/7CBBBE9B75FA0232E05352E3610A5325"/>
            </documentation>
            <groupingBehavior value="visual-group"/>
            <action id="id_34387257">
                <title
                        value="Enalapril Oral Tablet; 10 mg 1 time a day (dispense 30 tablet(s); 3 refills)"/>
                <description value="Formulary"/>
                <requiredBehavior value="could"/>
                <precheckBehavior value="no"/>
				<relatedAction> <!-- added -->
					<actionId value="id_34387258"/>  <!-- added -->
					<relationship value="before-start"/>  <!-- added -->
				</relatedAction>
            </action>
            <action id="id_34387258">
                <title
                        value="Lisinopril 20 MG Oral Tablet; 1 tablet(s) 1 time a day (dispense 30 tablet(s); 3 refills)"/>
                <description value="Formulary"/>
                <requiredBehavior value="could"/>
                <precheckBehavior value="no"/>
            </action>
			<relatedAction> <!-- added -->
				<actionId value="id_34387261"/>  <!-- added -->
				<relationship value="before-start"/>  <!-- added -->
			</relatedAction>
        </action>
        <action id="id_34387261">
            <title value="Antilipemics"/>
            <groupingBehavior value="visual-group"/>
            <action id="id_34387264">
                <title
                        value="Simvastatin Oral Tablet; 5 mg 1 time a day (dispense 30 tablet(s); 3 refills)"/>
                <description value="Formulary"/>
                <requiredBehavior value="could"/>
                <precheckBehavior value="no"/>
            </action>
			<relatedAction> <!-- added -->
				<actionId value="id_34387265"/>  <!-- added -->
				<relationship value="before-start"/>  <!-- added -->
			</relatedAction>
        </action>
        <action id="id_34387265">
            <title value="Diuretics"/>
            <documentation>
                <type value="justification"/>
                <display
                        value="&lt;p&gt;Consider using thiazides&amp;nbsp;as diuretics in patients with early stages of chronic kidney disease.&lt;/p&gt;

&lt;ul&gt;
	&lt;li&gt;When&amp;nbsp;GFR falls below 30-50&amp;nbsp;mL/minute/1.73 m&lt;sup&gt;2&lt;/sup&gt;, thiazides as less effective.&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;Give loop diuretics once or twice daily to&amp;nbsp;patients with GFR&amp;nbsp;less than 30 mL/minute/1.73 m&lt;sup&gt;2&lt;/sup&gt;&lt;sup&gt; &lt;/sup&gt;(chronic kidney disease stages 4-5).&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;Instruct patients to follow a low-sodium diet in addition to using diuretics to optimize&amp;nbsp;volume status.&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;Limit the use of potassium-sparing diuretics, such as triamterene and amiloride, in patients with chronic kidney disease because of the risk of hyperkalemia.&lt;/li&gt;
&lt;/ul&gt;

&lt;p&gt;&amp;nbsp;&lt;/p&gt;"/>
                <citation
                        value="Kidney Disease: Improving Global Outcomes (KDIGO) Blood Pressure Work Group. KDIGO clinical practice guideline for the management of blood pressure in chronic kidney disease. Kidney Int. 2012;2 (Suppl), 337-414. &lt;a href=&#39;http://www.kdigo.org/clinical_practice_guidelines/pdf/KDIGO_BP_GL.pdf&#39; target=&#39;_blank&#39;&gt;Source&lt;/a&gt;"/>
                <url
                        value="http://himss19.ordersetsmanager.com/evidenceviewer/#/7CBBBE9B75E10232E05352E3610A5325/7CBBBE9B76030232E05352E3610A5325"/>
            </documentation>
            <groupingBehavior value="visual-group"/>
            <action id="id_34387266">
                <title
                        value="Furosemide Oral Tablet; 20 mg 1 time a day (dispense 30 tablet(s); 3 refills)"/>
                <description value="Formulary"/>
                <requiredBehavior value="could"/>
                <precheckBehavior value="no"/>
            </action>
        </action>
		<relatedAction> <!-- added -->
			<actionId value="id_34387287"/>  <!-- added -->
			<relationship value="before-start"/>  <!-- added -->
		</relatedAction>
    </action>
    <action id="id_34387287">
        <title value="Laboratory"/>
        <groupingBehavior value="visual-group"/>
        <action id="id_34387288">
            <title value="Chemistry"/>
            <documentation>
                <type value="justification"/>
                <display
                        value="&lt;p&gt;Electrolyte analysis&lt;/p&gt;

&lt;ul&gt;
	&lt;li&gt;Abnormalities of electrolytes and other solutes suggest disorders of renal tubular reabsorption and secretion&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;Persistent abnormalities (lasting more than 3 months) in serum phosphate, potassium, parathyroid hormone, or calcium levels indicate decreased renal function associated with chronic kidney disease
	&lt;ul&gt;
		&lt;li&gt;Potassium: greater than 5.5 mEq/L indicative of hyperkalemia or less than 4.0 mEq/L indicating hypokalemia&lt;/li&gt;
		&lt;li&gt;Parathyroid hormone: results greater than 65 pg/mL are above the reference range&lt;/li&gt;
		&lt;li&gt;Calcium: less than 8.4 mg/dL is below the reference range&lt;/li&gt;
		&lt;li&gt;Phosphorus: less than 4.6 mg/dL is below the reference range&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;

&lt;p&gt;&amp;nbsp;&lt;/p&gt;

&lt;p&gt;Provide management of electrolyte disturbances&lt;/p&gt;

&lt;ul&gt;
	&lt;li&gt;Hyperkalemia and hypokalemia
	&lt;ul&gt;
		&lt;li&gt;High (greater than 5.5 mEq/L)&amp;nbsp;or&amp;nbsp;low (less than 4 mEq/L)&amp;nbsp;potassium levels are associated with increased mortality for patients with chronic kidney disease&lt;/li&gt;
		&lt;li&gt;Patients with chronic kidney disease have a high risk of developing hyperkalemia, which can cause cardiac arrhythmias and sudden death
		&lt;ul&gt;
			&lt;li&gt;8% to 73% of patients with chronic kidney disease develop hyperkalemia compared to 2.6% to 3.2% in the general population&lt;/li&gt;
		&lt;/ul&gt;
		&lt;/li&gt;
		&lt;li&gt;Patients with hypokalemia have an 82% increased risk of reaching end-stage renal disease&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;Hyperphosphatemia
	&lt;ul&gt;
		&lt;li&gt;Target serum phosphorus is 2.7 to 4.6 mg/dL for categories G3 and G4, and 3.5 to 5.5 mg/dL for category G5&lt;/li&gt;
		&lt;li&gt;Reduce phosphorus intake and consult nephrologist for treatment with phosphate binders&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
&lt;/ul&gt;

&lt;p&gt;&amp;nbsp;&lt;/p&gt;

&lt;ul&gt;
	&lt;li&gt;Guideline recommends&amp;nbsp;monitoring serum levels of calcium, phosphate, PTH, and alkaline phosphatase activity beginning in CKD G3a (Level of recommendation: 1C). In children, we suggest such monitoring beginning in CKD G2 (Level of recommendation: 2D).&lt;/li&gt;
	&lt;li&gt;In patients with CKD G3a&amp;ndash;G5D, it is reasonable to base the frequency of monitoring serum calcium, phosphate, and PTH on the presence and magnitude of abnormalities, and the rate of progression of CKD(Level of recommendation: Not Graded).
	&lt;ul&gt;
		&lt;li&gt;Reasonable monitoring intervals would be:
		&lt;ul&gt;
			&lt;li&gt;&amp;nbsp;In CKD G3a&amp;ndash;G3b: for serum calcium and phosphate, every 6&amp;ndash;12 months; and for PTH, based on baseline level and CKD progression.&lt;/li&gt;
			&lt;li&gt;&amp;nbsp;In CKD G4: for serum calcium and phosphate, every 3&amp;ndash;6 months; and for PTH, every 6&amp;ndash;12 months.&lt;/li&gt;
			&lt;li&gt;&amp;nbsp;In CKD G5, including G5D: for serum calcium and phosphate, every 1&amp;ndash;3 months; and for PTH, every 3&amp;ndash;6 months.&lt;/li&gt;
			&lt;li&gt;&amp;nbsp;In CKD G4&amp;ndash;G5D: for alkaline phosphatase activity, every 12 months, or more frequently in the presence of elevated PTH.&lt;/li&gt;
		&lt;/ul&gt;
		&lt;/li&gt;
	&lt;/ul&gt;
	&lt;/li&gt;
	&lt;li&gt;In CKD patients receiving treatments for CKD-MBD, or in whom biochemical abnormalities are identified, it is reasonable to increase the frequency of measurements to monitor for trends and treatment efficacy and side effects (Level of recommendation: Not Graded).&lt;/li&gt;
&lt;/ul&gt;"/>
                <citation
                        value="KDIGO Board. KDIGO 2017 Clinical Practice Guideline Update for the Diagnosis, Evaluation, Prevention, and Treatment of Chronic Kidney Disease–Mineral and Bone Disorder (CKD-MBD). Kidney Int Suppl. 2017;7(1), 1-59. &lt;a href=&#39;http://kdigo.org/wp-content/uploads/2017/02/2017-KDIGO-CKD-MBD-GL-Update.pdf&#39; target=&#39;_blank&#39;&gt;Source&lt;/a&gt;

Chronic Kidney Disease Clinical Overview. ClinicalKey. &lt;a href=&#39;https://www.clinicalkey.com/#!/content/clinical_overview/67-s2.0-26a91efc-ff97-4e11-b49f-c70331e79cee&#39; target=&#39;_blank&#39;&gt;Source&lt;/a&gt;"/>
                <url
                        value="http://himss19.ordersetsmanager.com/evidenceviewer/#/7CBBBE9B75E10232E05352E3610A5325/7CBBBE9B761A0232E05352E3610A5325"/>
            </documentation>
            <groupingBehavior value="visual-group"/>
            <action id="cc_cpg_activity_lab_metabolic">
                <title value="Lab: Comprehensive Metabolic Panel, Once"/>
                <description value="Laboratory"/>
                <requiredBehavior value="could"/>
                <precheckBehavior value="no"/>
            </action>
			<relatedAction> <!-- added -->
				<actionId value="id_34387206"/>  <!-- added -->
				<relationship value="before-start"/>  <!-- added -->
			</relatedAction>
        </action>
        <action id="id_34387206">
            <title value="Urine"/>
            <documentation>
                <type value="justification"/>
                <display
                        value="&lt;p&gt;For patients with a positive dipstick test&amp;nbsp;(1+ or greater), perform&amp;nbsp;confirmation of proteinuria by a quantitative measurement (protein-to-creatinine ratio or albumin-to-creatinine ratio) within 3 months.&lt;/p&gt;

&lt;p&gt;&amp;nbsp;&lt;/p&gt;

&lt;p&gt;When screening adults at increased risk for chronic kidney disease, measure albumin&amp;nbsp;in a spot urine sample using either of the following:&amp;nbsp;&lt;/p&gt;

&lt;ul&gt;
	&lt;li&gt;Albumin-specific dipstick&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;Albumin-to-creatinine ratio&lt;/li&gt;
&lt;/ul&gt;

&lt;p&gt;When monitoring proteinuria in adults with chronic kidney disease, measure the protein-to-creatinine ratio in spot urine samples using:&amp;nbsp;&lt;/p&gt;

&lt;ul&gt;
	&lt;li&gt;​Albumin-to-creatinine ratio&lt;/li&gt;
&lt;/ul&gt;

&lt;ul&gt;
	&lt;li&gt;Total protein-to-creatinine, only if albumin-to-creatinine ratio is more than 500-1000 mg/g&amp;nbsp;(57-113 mg/mmol)&lt;/li&gt;
&lt;/ul&gt;"/>
                <citation
                        value="Kidney Disease: Improving Global Outcomes (KDIGO) CKD Work Group. KDIGO 2012 Clinical Practice Guideline for the Evaluation and Management of Chronic Kidney Disease. Kidney Inter. 2013;Suppl 3, 1-150. &lt;a href=&#39;http://www.kdigo.org/clinical_practice_guidelines/pdf/CKD/KDIGO_2012_CKD_GL.pdf&#39; target=&#39;_blank&#39;&gt;Source&lt;/a&gt;"/>
                <url
                        value="http://himss19.ordersetsmanager.com/evidenceviewer/#/7CBBBE9B75E10232E05352E3610A5325/7CBBBE9B75C80232E05352E3610A5325"/>
            </documentation>
            <groupingBehavior value="visual-group"/>
            <action id="cc_cpg_activity_lab_creatinine">
                <title value="Lab: Creatinine, Urine Random, Once"/>
                <description value="Laboratory"/>
                <requiredBehavior value="could"/>
                <precheckBehavior value="no"/>
            </action>
        </action>
		<relatedAction> <!-- added -->
			<actionId value="id_34387217"/>  <!-- added -->
			<relationship value="before-start"/>  <!-- added -->
		</relatedAction>
    </action>
    <action id="id_34387217">
        <title value="Radiology"/>
        <groupingBehavior value="visual-group"/>
        <action id="cc-cpg-activity-ultrasound-renal">
            <title value="Ultrasound"/>
            <groupingBehavior value="visual-group"/>
            <action id="id_34387219">
                <title
                        value="Ultrasound, Renal; History: [add diagnosis, symptom(s)]; Question: [add reason for exam]"/>
                <description value="Imaging Studies"/>
                <requiredBehavior value="could"/>
                <precheckBehavior value="no"/>
            </action>
        </action>
		<relatedAction> <!-- added -->
			<actionId value="cc_cpg_activity_referral_nephrology"/>  <!-- added -->
			<relationship value="before-start"/>  <!-- added -->
		</relatedAction>
    </action>
    <action id="cc_cpg_activity_referral_nephrology">
        <title value="Referrals"/>
        <groupingBehavior value="visual-group"/>
        <action id="id_34387224">
            <title
                    value="Referral: Nephrology; History: [add diagnosis, symptom(s)]; Question: [add reason for referral]"/>
            <description value="Referrals"/>
            <requiredBehavior value="could"/>
            <precheckBehavior value="no"/>
			<relatedAction> <!-- added -->
				<actionId value="cc_cpg_activity_referral_dietition"/>  <!-- added -->
				<relationship value="before-start"/>  <!-- added -->
			</relatedAction>
        </action>
        <action id="cc_cpg_activity_referral_dietition">
            <title
                    value="Referral: Dietitian; History: chronic kidney disease; Question: [add reason for referral]"/>
            <description value="Referrals"/>
            <requiredBehavior value="could"/>
            <precheckBehavior value="no"/>
        </action>
    </action>
</PlanDefinition>
```

## BPMN

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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

```

<script type="text/javascript" src="../js/highlight.js"></script>