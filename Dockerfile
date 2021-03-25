FROM openjdk:11

#Environment vars
ENV PROFILE=default
ENV PORT=8761
ENV DISCOVER_INSTANCES_HOST=localhost
ENV DISCOVER_AS_CLIENT=false
ENV FETCH_REGISTRY=false
ENV DISCOVERY_USER=user
ENV DISCOVERY_PASSWORD=password
ENV CONFIG_URI=http://localhost:8888
ENV CONFIG_USER=user
ENV CONFIG_PASSWORD=password


#Copy app
COPY *.jar app.jar

#Run app with envirement
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${PROFILE}", "app.jar"]
CMD ["--$PORT","--$DISCOVER_INSTANCES_HOST","--$DISCOVER_AS_CLIENT","--$FETCH_REGISTRY","--$DISCOVERY_USER","--$DISCOVERY_PASSWORD", "--$CONFIG_URI", "--$CONFIG_USER", "--$CONFIG_PASSWORD" ]
