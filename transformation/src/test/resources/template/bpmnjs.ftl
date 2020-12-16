<#-- @ftlvariable name="bpmn" type="java.lang.String" -->

const containerBefore = document.getElementById('container');
const originalViewer = new BpmnJS({
    container: containerBefore
});

originalViewer.importXML(`${bpmn}`, function(err){
    originalViewer.get('canvas').zoom('fit-viewport');
});