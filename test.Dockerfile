FROM maven:3.8.4-openjdk-11-slim as TEST

ARG PORT=8080

EXPOSE ${PORT}

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw
CMD ["./mvnw", "test"]