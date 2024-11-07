#!/bin/bash
# Compile the project if not already compiled
mvn clean compile

# Run the Server class from the compiled files in target/classes
java -cp target/classes server.Server
