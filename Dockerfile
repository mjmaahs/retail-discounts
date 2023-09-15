# Use the official OpenJDK 17 image as a base image
FROM openjdk:17-jdk-alpine
# Metadata as a label
LABEL maintainer="mjmaahs@gmail.com"
# Working directory inside the container
WORKDIR /app
# Copy the JAR file into the container at /app
COPY target/retaildiscounts-0.0.1-SNAPSHOT.jar /app/retaildiscounts-0.0.1-SNAPSHOT.jar
# Port the app listens on
EXPOSE 8080
# Command to run the application
CMD ["java", "-jar", "/app/retaildiscounts-0.0.1-SNAPSHOT.jar"]