FROM openjdk

COPY ./target/service-discovery-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app
RUN sh -c 'touch service-discovery-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","service-discovery-0.0.1-SNAPSHOT.jar"]
