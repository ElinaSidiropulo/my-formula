FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar
EXPOSE 9093
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]
