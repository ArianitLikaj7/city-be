#
# Build stage
#
FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:17-jdk-slim
COPY --from=build /target/city-be-0.0.1-SNAPSHOT.jar city-be.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","city-be.jar"]