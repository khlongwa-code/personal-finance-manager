# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file from the target directory in the host machine to the container
COPY target/personal-finance-manager-1.0-SNAPSHOT-jar-with-dependencies.jar /app/personal-finance-manager.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "/app/personal-finance-manager.jar"]
