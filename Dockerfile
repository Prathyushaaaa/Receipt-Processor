# Use an official OpenJDK runtime as a parent image
FROM openjdk:20-jdk-slim

# Set the working directory in the container to /app
WORKDIR /app

# Copy the application's jar file into the container at /app
COPY target/ReceiptProcessor-0.0.2-SNAPSHOT.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
