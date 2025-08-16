FROM openjdk:21-jdk-slim

WORKDIR /app

COPY /build/libs/*.jar gateway.jar

ENTRYPOINT ["java", "-jar", "gateway.jar"]