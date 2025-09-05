FROM openjdk:21-jdk-slim

WORKDIR /app

COPY /build/libs/*.jar notifications.jar

ENTRYPOINT ["java", "-jar", "notifications.jar"]