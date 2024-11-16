# Use Maven and JDK 21 on Alpine Linux for building
FROM maven:3.8.8-eclipse-temurin-21-alpine AS build

# Create an app directory and copy the code there
WORKDIR /app
COPY . /app

# Build the Maven project
RUN mvn clean package -DskipTests

# Start a new, lightweight image to run the application
FROM eclipse-temurin:21-jdk-alpine

# Create an app directory in the new container
WORKDIR /app

# Copy the packaged JAR file from the build image to the app directory
COPY --from=build /app/target/*.jar /app/app.jar

# Expose port 8080
EXPOSE 8080

# Specify the command to run your application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
