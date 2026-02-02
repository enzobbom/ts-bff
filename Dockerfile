# Basic Docker script
#FROM eclipse-temurin:17-jdk-jammy
#WORKDIR /app
#COPY target/bff-0.0.1-SNAPSHOT.jar /app/bff.jar
#EXPOSE 8083
#CMD ["java", "-jar", "/app/bff.jar"]

# Build
FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .

# Run .jar
RUN mvn clean install -DskipTest
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar /app/bff.jar
EXPOSE 8083
CMD ["java", "-jar", "/app/bff.jar"]
