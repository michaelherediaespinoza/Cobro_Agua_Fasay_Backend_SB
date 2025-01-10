FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/cobroagua-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_cobroagua.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_cobroagua.jar"]