# Create the image to build the project
FROM maven:3.6.3-openjdk-11 as java-build-stage

WORKDIR /app/build
COPY . .

RUN mvn clean package -am -DskipTests -Dmaven.javadoc.skip=true

# create the image to run the jetty server
FROM jetty:9.4.30-jdk11

COPY LICENSE /var/lib/jetty/webapp/LICENSE
COPY LICENSE_APACHE_2_0 /var/lib/jetty/webapp/LICENSE_APACHE_2_0
COPY NOTICE /var/lib/jetty/webapp/NOTICE

COPY --from=java-build-stage /app/build/hapi-fhir/target/hapi-fhir-jpaserver.war /var/lib/jetty/webapps/ROOT.war