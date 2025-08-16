FROM openjdk:21-jdk-slim

WORKDIR /app

COPY /build/libs/*.jar account.jar

ENTRYPOINT ["java", "-jar", "account.jar"]
