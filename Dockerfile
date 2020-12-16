# Create the image to build the project
FROM maven:3.6.3-openjdk-11 as java-build-stage

WORKDIR /app/build
COPY . .

# Workaround due to: https://github.com/FHOOEAIST/bpmn-viz
RUN apt-get update && apt-get install wget -y
RUN wget https://dl.bintray.com/flowtick/jgraphx/com/mxgraph/jgraphx/3.7.4/jgraphx-3.7.4.jar
RUN mvn install:install-file -Dfile="jgraphx-3.7.4.jar" -DgroupId="com.mxgraph" -DartifactId="mxgraph-all" -Dversion="3.7.4" -Dpackaging=jar

RUN mvn clean package -am -DskipTests -Dmaven.javadoc.skip=true

# create the image to run the jetty server
FROM jetty:9.4.30-jdk11

COPY LICENSE /var/lib/jetty/webapp/LICENSE
COPY LICENSE_APACHE_2_0 /var/lib/jetty/webapp/LICENSE_APACHE_2_0
COPY LICENSE_JGRAPH_MXGRAPH /var/lib/jetty/webapp/LICENSE_JGRAPH_MXGRAPH
COPY NOTICE /var/lib/jetty/webapp/NOTICE

COPY --from=java-build-stage /app/build/hapi-fhir/target/hapi-fhir-jpaserver.war /var/lib/jetty/webapps/ROOT.war