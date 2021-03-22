FROM maven:3.6.3-openjdk-11 AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package


FROM openjdk:latest

CMD java -jar /data/service-discovery-0.0.1-SNAPSHOT.jar

COPY --from=maven_build /tmp/target/service-discovery-0.0.1-SNAPSHOT.jar /data/service-discovery-0.0.1-SNAPSHOT.jar