FROM openjdk:21-jdk-slim

WORKDIR /app

COPY /build/libs/*.jar exchange.jar

ENTRYPOINT ["java", "-jar", "exchange.jar"]