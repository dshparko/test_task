# Этап сборки
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

COPY notification-service/pom.xml ./
COPY notification-service/src ./src

RUN mvn clean package -DskipTests=true

# Этап запуска
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /app/target/notification-service-0.0.1-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
