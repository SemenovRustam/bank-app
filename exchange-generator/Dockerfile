FROM openjdk:21-jdk-slim

WORKDIR /app

COPY /build/libs/*.jar exchange-generator.jar

ENTRYPOINT ["java", "-jar", "exchange-generator.jar"]