FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY .mvn/ .mvn/
COPY src ./src

RUN mvn dependency:go-offline
RUN mvn package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/hotel-booking-1.0.0.jar"]
