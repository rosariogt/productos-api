# RTSDK Java
#FROM openjdk:11-jre-slim-buster
#WORKDIR /app
##COPY --from=builder /app/target/proyecto2-1.0-SNAPSHOT.jar /app/proyecto2-1.0-SNAPSHOT.jar
#
#CMD ["java", "-jar","target/proyecto2-1.0-SNAPSHOT.jar"]

# Use an official Maven image as a parent image
FROM maven:3.8.1-openjdk-11 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the Maven project
RUN mvn clean package

# Use a smaller base image for the application runtime
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage to the runtime image
COPY --from=builder /app/target/producto-with-dependencies.jar .

# Specify the command to run your application
CMD ["java", "-jar", "producto-with-dependencies.jar"]