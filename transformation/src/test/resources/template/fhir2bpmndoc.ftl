<#-- @ftlvariable name="name" type="java.lang.String" -->
<#-- @ftlvariable name="filename" type="java.lang.String" -->
<#-- @ftlvariable name="description" type="java.lang.String" -->
<#-- @ftlvariable name="fhir" type="java.lang.String" -->
<#-- @ftlvariable name="bpmn" type="java.lang.String" -->
<#-- @ftlvariable name="discussions" type="java.util.List<java.lang.String>" -->

# ${name}

${description}

<#if discussions??>
## Discussions

<#list discussions as discussion>
 * ${discussion}
</#list>
</#if>

## Graph

<script type="text/javascript" src="https://unpkg.com/bpmn-js@7.2.1/dist/bpmn-navigated-viewer.production.min.js"></script>
Usage: Mouse Click + Move: Scroll around. Ctrl + Mouse Wheel: Zoom.

<div id="container" style="width: 100%; height: 500px; border: 1px solid lightgray; overflow:auto;"></div>

<script type="text/javascript" src="../js/fhir2bpmn/${filename}.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.0.0/styles/darcula.min.css"></link>
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.0.0/highlight.min.js"></script>


## HL7 FHIR

```xml
${fhir}
```

## BPMN

```xml
${bpmn}
```

<script type="text/javascript" src="../js/highlight.js"></script>