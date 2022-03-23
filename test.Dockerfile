FROM maven:3.8.4-openjdk-11-slim as TEST

ARG PORT=8080

EXPOSE ${PORT}

RUN mvn clean
CMD ["mvn", "test"]