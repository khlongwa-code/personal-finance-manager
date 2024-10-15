# Use a base image with JDK installed
FROM eclipse-temurin:17-jdk-alpine as builder

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use a lightweight image to run the application
FROM eclipse-temurin:17-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/personal-finance-manager-1.0-SNAPSHOT-jar-with-dependencies.jar ./app.jar

# Expose the port the app runs on (change as needed)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
