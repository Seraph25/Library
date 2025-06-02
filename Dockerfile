# Етап 1: Збірка з Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn dependency:go-offline
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/lib-rary-1.0.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]