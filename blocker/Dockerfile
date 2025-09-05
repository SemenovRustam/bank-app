FROM openjdk:21-jdk-slim

WORKDIR /app

COPY /build/libs/*.jar blocker.jar

ENTRYPOINT ["java", "-jar", "blocker.jar"]