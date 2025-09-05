FROM openjdk:21-jdk-slim

WORKDIR /app

COPY /build/libs/*.jar transfer.jar

ENTRYPOINT ["java", "-jar", "transfer.jar"]