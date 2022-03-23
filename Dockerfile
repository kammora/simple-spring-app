FROM maven:3.8.4-openjdk-11-slim as COMPILE

WORKDIR /app
COPY . .

RUN ./mvnw clean
RUN ./mvnw compile

FROM maven:3.8.4-openjdk-11-slim as TEST

WORKDIR /app
RUN chown $USER /app

COPY --from=COMPILE /app /app
RUN ./mvnw test

FROM maven:3.8.4-openjdk-11-slim as PACKAGE

WORKDIR /app
COPY --from=TEST /app /app
RUN ./mvnw package

FROM openjdk:11-slim as RELEASE

WORKDIR /app
COPY --from=PACKAGE /app/target/*.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
