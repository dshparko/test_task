# Этап сборки
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

COPY user-service/pom.xml ./
COPY user-service/src ./src

RUN mvn clean package -DskipTests=true

# Этап запуска
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /app/target/user-service-0.0.1-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java", "--enable-preview", "-jar", "/app/app.jar"]
