FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/team-service.jar team-service.jar
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "team-service.jar"]
