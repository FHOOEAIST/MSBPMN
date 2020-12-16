<#-- @ftlvariable name="graph" type="science.aist.gtf.graph.Graph<org.hl7.fhir.r4.model.BackboneElement>" -->

digraph G {
rankdir=LR;

<#list graph.vertices as vertex>
    ${vertex.element.id}<#if vertex.element.class.simpleName == "PlanDefinitionActionComponent" >[label = "${vertex.element.title}"]</#if>
</#list>

<#list graph.edges as edge>
    ${edge.source.element.id} -> ${edge.target.element.id} [label = "${edge.getMetaTagValue("edgetype").name()}"]
</#list>
}