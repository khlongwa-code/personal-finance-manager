# Use Maven official image with JDK 17 as the base image
FROM maven:3.8.7-openjdk-17 AS builder

# Set working directory
WORKDIR /app

# Copy the pom.xml file and download dependencies
COPY pom.xml .

# Download dependencies without running the tests
RUN mvn dependency:go-offline

# Copy the project source code
COPY src ./src

# Build the application without running tests
RUN mvn clean package -DskipTests

# Use a lightweight JDK image for runtime
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the packaged JAR file from the build stage
COPY --from=builder /app/target/personal-finance-manager-1.0-SNAPSHOT-jar-with-dependencies.jar /app/app.jar

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

