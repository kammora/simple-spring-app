FROM openjdk:11-slim as PACKAGE

ARG PORT=8080

EXPOSE ${PORT}

WORKDIR /app
COPY . .

RUN chmod +x ./mvnw

RUN  ./mvnw clean install -Dmaven.test.skip=true

FROM openjdk:11-slim as RELEASE

WORKDIR /app
COPY --from=PACKAGE /app/target/*.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
