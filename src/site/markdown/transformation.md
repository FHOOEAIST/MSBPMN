# Transformation

The transformation is currently implemented in a single direction, transforming HL7® FHIR® PlanDefinitions into BPMN. To do
so some assumptions and restrictions were defined. These can be found in the restrictions to transformation section.

## Concepts

<table>
    <thead>
        <tr>
            <th>Type</th>
            <th>HL7 FHIR</th>
            <th>BPMN</th>
        </tr>
    </thead>
    <tbody>
<!-- - - - - - - - - -  Sequence Flow - - - - - - - - - -->
        <tr>
            <th>Sequence Flow</th>
            <td>
                <pre><code><![CDATA[<action>
    <id value='ad_1'/>
    <title value='Task 1'/>
    <relatedAction>
        <actionId value='ad_2'/>
        <relationship value='before-start'/>
    </relatedAction>
</action>

<action>
    <id value='ad_2'/>
    <title value='Task 2'/>
</action>]]></code></pre>
            </td>
            <td>
                <pre><code><![CDATA[<userTask name="Task 1" id="id_ad_1">
    <incoming>sf_start1_ad_1</incoming>
    <outgoing>sf_ad_1_ad_2</outgoing>
</userTask>
<sequenceFlow sourceRef="id_ad_1" targetRef="id_ad_2" id="sf_ad_1_ad_2"/>
<userTask name="Task 2" id="id_ad_2">
    <incoming>sf_ad_1_ad_2</incoming>
    <outgoing>sf_ad_2_ad_3</outgoing>
</userTask>




]]></code></pre>
            </td>
        </tr>
<!-- - - - - - - - - -  XOR - - - - - - - - - -->
        <tr>
            <th>Exclusive Split</th>
            <td>
<pre><code><![CDATA[<action>
    <id value="xor_group"/>
    <title value="Xor_group"/>
    <groupingBehavior value="logical-group"/>
    <selectionBehavior value="exactly-one"/>
    <action>
        <id value="ad_2"/>
        <title value="Task 2"/>
        <condition>
            <kind value="applicability"/>
            <expression>
                <description value="Some short description of the expression"/>
                <language value="EL"/>
                <expression value="$name==2" />
            </expression>
        </condition>
        <relatedAction>
            <actionId value="xor_group"/>
            <relationship value="before-end"/>
        </relatedAction>
    </action>
    <action>
        <id value="ad_3"/>
        <title value="Task 3"/>
        <condition>
            ...
        </condition>
        <relatedAction>
            <actionId value="xor_group"/>
            <relationship value="before-end"/>
        </relatedAction>
    </action>
    ...
</action>]]></code></pre>
            </td>
            <td>
<pre><code><![CDATA[<exclusiveGateway name="Xor_group" id="id_xor_group">
    <incoming>...</incoming>
    <outgoing>sf_xor_group_ad_2</outgoing>
    <outgoing>sf_xor_group_ad_3</outgoing>
</exclusiveGateway>
<sequenceFlow sourceRef="id_xor_group" targetRef="id_ad_2" name="$name==2" id="sf_xor_group_ad_2">
    <conditionExpression id="sf_xor_group_ad_2_condition">$name==2</conditionExpression>
</sequenceFlow>
<sequenceFlow sourceRef="id_xor_group" targetRef="id_ad_3" name="$name==1" id="sf_xor_group_ad_3">
    <conditionExpression id="sf_xor_group_ad_3_condition">$name==1</conditionExpression>
</sequenceFlow>
<userTask name="Task 2" id="id_ad_2">
    <incoming>sf_xor_group_ad_2</incoming>
    <outgoing>sf_ad_2_join_of_xor_group</outgoing>
</userTask>
<sequenceFlow sourceRef="id_ad_2" targetRef="id_join_of_xor_group" id="sf_ad_2_join_of_xor_group"/>
<userTask name="Task 3" id="id_ad_3">
    <incoming>sf_xor_group_ad_3</incoming>
    <outgoing>sf_ad_3_join_of_xor_group</outgoing>
</userTask>
<sequenceFlow sourceRef="id_ad_3" targetRef="id_join_of_xor_group" id="sf_ad_3_join_of_xor_group"/>
<exclusiveGateway name="join" id="id_join_of_xor_group">
    <incoming>sf_ad_2_join_of_xor_group</incoming>
    <incoming>sf_ad_3_join_of_xor_group</incoming>
    <outgoing>...</outgoing>
</exclusiveGateway>








]]></code></pre>
            </td>
        </tr>
<!-- - - - - - - - - -  Parallel - - - - - - - - - -->
        <tr>
            <th>Parallel Split</th>
            <td>
<pre><code><![CDATA[<action>
    <id value="par_group"/>
    <title value="par_group"/>
    <groupingBehavior value="logical-group"/>
    <selectionBehavior value="all"/>
    <action>
        <id value="ad_2"/>
        <title value="Task 2"/>
        <relatedAction>
            <actionId value="par_group"/>
            <relationship value="before-end"/>
        </relatedAction>
    </action>
    <action>
        <id value="ad_3"/>
        <title value="Task 3"/>
        <relatedAction>
            <actionId value="par_group"/>
            <relationship value="before-end"/>
        </relatedAction>
    </action>
</action>]]></code></pre>
            </td>
            <td>
<pre><code><![CDATA[<parallelGateway name="par_group" id="id_par_group">
    <incoming>...</incoming>
    <outgoing>sf_par_group_ad_3</outgoing>
    <outgoing>sf_par_group_ad_2</outgoing>
</parallelGateway>
<sequenceFlow sourceRef="id_par_group" targetRef="id_ad_3" id="sf_par_group_ad_3"/>
<sequenceFlow sourceRef="id_par_group" targetRef="id_ad_2" id="sf_par_group_ad_2"/>
<userTask name="Task 3" id="id_ad_3">
    <incoming>sf_par_group_ad_3</incoming>
    <outgoing>sf_ad_3_join_of_par_group</outgoing>
</userTask>
<sequenceFlow sourceRef="id_ad_3" targetRef="id_join_of_par_group" id="sf_ad_3_join_of_par_group"/>
<userTask name="Task 2" id="id_ad_2">
    <incoming>sf_par_group_ad_2</incoming>
    <outgoing>sf_ad_2_join_of_par_group</outgoing>
</userTask>
<sequenceFlow sourceRef="id_ad_2" targetRef="id_join_of_par_group" id="sf_ad_2_join_of_par_group"/>
<parallelGateway name="join" id="id_join_of_par_group">
    <incoming>sf_ad_2_join_of_par_group</incoming>
    <incoming>sf_ad_3_join_of_par_group</incoming>
    <outgoing>...</outgoing>
</parallelGateway>]]></code></pre>
            </td>
        </tr>
<!-- - - - - - - - - -  Data Flow - - - - - - - - - -->
        <tr>
            <th>Data Flow</th>
            <td>
<pre><code><![CDATA[<action>
    <id value="id_1"/>
    <title value="Titel 1"/>
    <output>
        <id value="data_elem_1"/>
        <name value="My Data Elem"/>
        <dataRequirement>
            <type value="MyType"/>
        </dataRequirement>
    </output>
    ...
</action>
<action>
    <id value="id_2"/>
    <title value="Titel 2"/>
    <input>
        <id value="data_input_1"/>
        <relatedRequirement>
            <requirementId value="data_elem_1"/>
        </relatedRequirement>
    </input>
    ...
</action>]]></code></pre>
            </td>
            <td>
<pre><code><![CDATA[<task id="id_1" name="Title 1">
  <dataOutputAssociation id="df_id1_data_elem_1">
    <targetRef>data_elem_1</targetRef>
  </dataOutputAssociation>
</task>
<task id="id_2" name="Title 2">
  <property id="prop_id_2_data_elem_1" name="prop_My Data Elem" />
  <dataInputAssociation id="df_id_2_data_elem_1">
    <sourceRef>data_elem_1</sourceRef>
    <targetRef>prop_id_2_data_elem_1</targetRef>
  </dataInputAssociation>
</task>
<dataObjectReference id="data_elem_1" name="My Data Elem"/>










]]></code></pre>
            </td>
        </tr>
<!-- - - - - - - - - -  Trigger - - - - - - - - - -->
        <tr>
            <th>Trigger</th>
            <td>
<pre><code><![CDATA[<action>
    <id value='ad_3'/>
    <title value='Put Pizza in Oven'/>
    <trigger>
        <type value="named-event"/>
        <name value="oven-to-180"/>
        <condition>
            <language value="text/cql"/>
            <expression value="Oven to 180°"/>
        </condition>
    </trigger>
</action>




]]></code></pre>
            </td>
            <td>
<pre><code><![CDATA[<intermediateCatchEvent name="Put Pizza in Oven" id="id_ad_3">
    <incoming>...</incoming>
    <outgoing>sf_ad_3_ad_3_triggerAction</outgoing>
    <conditionalEventDefinition id="event_ad_3">
        <condition xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
                   xsi:type="tFormalExpression" 
                   language="text/cql">
            Oven to 180°
        </condition>
    </conditionalEventDefinition>
</intermediateCatchEvent>
<sequenceFlow sourceRef="id_ad_3" targetRef="id_ad_3_triggerAction" id="sf_ad_3_ad_3_triggerAction"/>
<userTask name="Put Pizza in Oven" id="id_ad_3_triggerAction">
    <incoming>sf_ad_3_ad_3_triggerAction</incoming>
    <outgoing>...</outgoing>
</userTask>]]></code></pre>
            </td>
        </tr>
<!-- - - - - - - - - -  Actor - - - - - - - - - -->
        <tr>
            <th>Actor / <br/>Participants</th>
            <td>
<pre><code><![CDATA[<actor>
    <id value="actor_1"/>
    <label value="Max Mustermann"/>
    <description value="Patient"/>
    <options>
        <type value="patient"/>
    </options>
    <options>
        <type value="patient"/>
        <role>
            <coding>
                <system value="some_system"/>
                <code value="some_code"/>
            </coding>
        </role>
    </options>
</actor>
<action>
    <id value="ad_1" />
    <title value="Action 1"/>
    <participant>
        <actor value="actor_1"/>
        <function>
            <coding>
                <system value="some_system"/>
                <code value="some_code"/>
            </coding>
        </function>
    </participant>
</action>]]></code></pre>
            </td>
            <td>
<pre><code><![CDATA[<process ...>
    <laneSet>
        <lane name="Max Mustermann" id="id_actor_1">
            <flowNodeRef>id_ad_1</flowNodeRef>
        </lane>
    </laneSet>
    <userTask name="Action 1" id="id_ad_1">
        <incoming>sf_start18_ad_1</incoming>
        <outgoing>sf_ad_1_ad_2</outgoing>
    </userTask>
</process>



















]]></code></pre>
            </td>
        </tr>
    </tbody>
</table>

## Restrictions to the transformation

### Related Actions

Each action that is not the last action in the process needs to define the next related action.

The relationship must be before-start for normal sequence flow. There is no support to define addition flow behaviour 
by defining `before-end` (except gateways) or similar relation ship values.

The only exception to that rule are parallel or xor gateways, this can be found in the according section.

### XOR Gateways

Currently, there is no support to define action with condition without them being surrounded by a xor action. There are
also some restriction to the xor-group as well. The groupingBehaviour is fixed to `logical-group`. In addition to that 
selectionBehavior is mandatory and restricted to `exactly-one` or `at-most-one`. As a finale restriction it is defined,
that every child action of a xor_group must have a condition (with a defined expression.expression value) and in 
addition to that, must have a relatedAction to the xor-group itself, with a relationship-value: `before-end`.
The transformation will ignore any condition that is defined by an action that is outside a xor-scope. 

### Parallel Gateway

A parallel gateway is similar to an xor-gateway. The only difference is, that the selection behaviour must be set to `all`.

### ID

Every action must have an id assign. In general, it makes sense to assign an id to every element, to make them unique.
In addition to that BPMN has the restriction, that an ID must start with a letter. To avoid redirecting that restriction
to the PlanDefinition the transformation prefixes every id that is reused from the PlanDefinition with "id_".  

### Data Flow

Data Flow Elements are a custom defined extensions that are not part of the HL7® FHIR® Model. Thus means, that the normal
HL7® FHIR® Specification will fail the validation of Data Flow on Action elements, when they are adapted for our transformation.
Both input and output can have relatedRequirements, although for input they are mandatory whereas for output the value
of the relatedRequirement is ignored. Each output or input element again must have an ID. Every input element must have
at least one but possibly multiple related requirement. Whereas it is defined, that this related requirement to an output
element has to exist. This mean, that if an input element reference an id, that an action which has an output with this
ID must exist.

### Events

Action can define triggers to start them at a specific time or on a specific condition. This concept is restricted to
the behaviour that only a single trigger is allowed per action. In addition to that it is defined, that the trigger
type `periodic` is for timed events, whereas the trigger type `namedevent` is for conditional events. For condition
events it is also defined, that hey must have a condition inside the trigger.

### Participants

Participants are getting transformed into BPMN lines. To re-identify the same participant the role of the participant is 
used. Thus means, that every participant in the process must have a role defined. As an identification the first coding
of a role is used. Therefore, system and code element will be concatenated and are the unique identifier for the participant.
Note: Only the process child activities in BPMN can be in different lanes. That means that if a subprocess (or a action
with sub actions in HL7® FHIR®) has assigned a participant all child elements/actions will be inside the same lane. No matter
if they have participants defined or not.       