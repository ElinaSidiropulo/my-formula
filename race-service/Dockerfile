FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/race-service-0.0.1-SNAPSHOT.jar race-service.jar
EXPOSE 9092
ENTRYPOINT ["java", "-jar", "race-service.jar"]
