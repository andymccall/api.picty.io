FROM openjdk:8-jdk-alpine
RUN apk add --no-cache bash
COPY target/picty-api-0.1-SNAPSHOT.jar /picty/picty-api.jar
COPY src/main/resources/application-container.properties /picty/application.properties
COPY src/docker/start.sh start.sh
RUN chmod +x start.sh
COPY src/docker/wait-for-port.sh wait-for-port.sh
RUN chmod +x wait-for-port.sh
EXPOSE 8080
ENTRYPOINT ["./start.sh", "$@"]