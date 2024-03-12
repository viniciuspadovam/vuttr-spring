FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /vuttr-app
COPY . .
RUN mvn clean install

FROM openjdk:17
WORKDIR /vuttr-app
COPY --from=build /vuttr-app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]