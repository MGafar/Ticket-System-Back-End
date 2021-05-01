FROM maven:latest

COPY . /build

WORKDIR /build

RUN mvn clean package

FROM openjdk:11 AS runtime
 
WORKDIR /opt/ticketsystem

COPY --from=0 /build/target/TicketSystem-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]

