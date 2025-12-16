# Multi-stage build for Spring Boot (Java 17) -> Cloud Run
# Build stage
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /workspace
COPY pom.xml .
# Pre-fetch dependencies for better caching
RUN mvn -q -e -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

# Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app
# Cloud Run sets PORT; Spring should bind to it (see application.yml server.port=${PORT:8080})
ENV PORT=8080
COPY --from=builder /workspace/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-XX:MaxRAMPercentage=75.0","-jar","/app/app.jar"]
