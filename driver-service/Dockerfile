FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/driver-service-0.0.1-SNAPSHOT.jar driver-service.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "driver-service.jar"]
