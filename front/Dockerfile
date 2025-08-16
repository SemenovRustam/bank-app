FROM openjdk:21-jdk-slim

WORKDIR /app

COPY /build/libs/*.jar front.jar

ENTRYPOINT ["java", "-jar", "front.jar"]