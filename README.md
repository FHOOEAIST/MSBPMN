# MSBPMN - Model and Standards-Based Process-simulation in MediciNe
 
In MSBPMN (Model and Standards-Based Process-simulation in MediciNe) a standards-based model
transformation between BPMN 2.0 and HL7® FHIR® is developed.
The goal is to define clinical guidelines and treatment plans in HL7® FHIR® and to automatically
generate BPMN models, which are then used in the IT systems for process control and documentation.
The implementation of the model transformation provides a reference how a transformation between the
two standards can look. It is closely coordinated with the HL7 Workflow Project. This project aims to
make a significant contribution to the digitisation (of processes) of evidence-based medicine.

Detailed Documentation can be found [here](https://fhooeaist.github.io/msbpmn).

## Getting Started

A transformation from an adapted version of a Fhir PlanDefinition to the BPMN specification is provided. The transformation
can be used by adding a dependency to the transformation module, importing the spring configuration and using the service
method to transform it.

**Dependency:**

```xml
<dependency>
    <groupId>at.fh.hagenberg.aist.msbpmn</groupId>
    <artifactId>transformation</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

**SpringConfig:**

You have to include the spring config file into your own spring config or load the Spring Config directly.

```java
@Configuration
@Import(TransformationConfiguration.class)
public class MyConfig {...}
```

**ServiceMethod:**

You can inject the Transformation service where ever required and execute the transformation.

```java
@Autowired
Transformer<PlanDefinition, String> planDefinitionToBpmnStringTransformationService;

void doTransformation() {
  PlanDefinition pd = ...;
  String bpmnResult = planDefinitionToBpmnStringTransformationService.applyTransformation(pd);
}
```

Alternatively, you can use the service implementation.

```java
@Autowired
ModelTransformationService service;

void doTransformation() {
  String fhir = ...;
  String bpmn = service.fromFhir(fhir);
}
```

In addition to that the transformation was included as an operation into a [hapi-fhir](https://hapifhir.io/) server.
You are able to run the server inside a docker container using the fhooeaist/msbpmn-docker image 
(https://hub.docker.com/r/fhooeaist/msbpmn) or building it yourself.

```shell
docker container run -p 8080:8080 --name msbpmn --rm -d fhooeaist/msbpmn
```

```shell
# build it
docker image build -t msbpmn .

# run it
docker container run -p 8080:8080 msbpmn
```

Then you are able to access the server under: `localhost:8080`. Next you are able to upload a PlanDefinition to the
server by executing a post http request to `localhost:8080/fhir/PlanDefinition`. In success this will return the id
of the uploaded PlanDefinition which you can then use to execute an HTTP GET request and convert it into a BPMN. 
`localhost:8080/fhir/PlanDefinition/<id>/$bpmn` where you replace `<id>` with your PlanDefinition id. This will return
the BPMN representation as a result.

## FAQ

If you have any questions, please check out our [FAQ](https://fhooeaist.github.io/msbpmn/faq.html) section.

## Contributing

**First make sure to read our [general contribution guidelines](https://fhooeaist.github.io/CONTRIBUTING.html).**
   
## Licence

Copyright (c) 2020 the original author or authors.
DO NOT ALTER OR REMOVE COPYRIGHT NOTICES.

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at https://mozilla.org/MPL/2.0/.

The following files and packages are licensed under different conditions:

| Licence | Filepaths |
|-|-|
| **Apache 2.0**<br>see hapi-fhir/LICENSE | module hapi-fhir (hapi-fhir/*)<br>package &ast;&ast;/org/hl7/fhir/r4/&ast;&ast;<br>package &ast;&ast;/ca/uhn/fhir/util/&ast;&ast; |

## Research

If you are going to use this project as part of a research paper, we would ask you to reference this project by citing
it. DOI: TBD