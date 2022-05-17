<#-- @ftlvariable name="samples" type="java.util.List<science.aist.msbpmn.service.documentation.DocumentationGenerator.DocumentationSample>" -->

#Fhir2BPMN

The transformation from HL7® FHIR® to bpmn is shown by a few examples:

<#list samples as sample>
 * [${sample.name}](fhir2bpmn/${sample.filename}.md)
</#list>