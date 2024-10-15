FROM ubuntu:latest

RUN apt-get update
RUN apt-get install -y openjdk-17-jre curl

COPY target/personal-finance-manager-1.0-SNAPSHOT-jar-with-dependencies.jar /financial-manager.jar

WORKDIR /app
EXPOSE 5050
CMD ["java", "-jar", "financial-manager.jar"]