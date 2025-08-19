FROM openjdk:21-jdk-slim

WORKDIR /app

COPY /build/libs/*.jar cash.jar

ENTRYPOINT ["java", "-jar", "cash.jar"]