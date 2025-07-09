# -------- Stage 1: Build the application --------
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Set working directory
WORKDIR /vat-nic-backend-demo

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# -------- Stage 2: Create the runtime image --------
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /vat-nic-backend-demo

# Copy the built jar from the builder stage
COPY --from=builder /vat-nic-backend-demo/target/*.jar app.jar

# Expose the application's port 8083(local)
EXPOSE 10000 

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]