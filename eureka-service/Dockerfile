FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/eureka-service-0.0.1-SNAPSHOT.jar eureka-service.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "eureka-service.jar"]
