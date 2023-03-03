FROM openjdk:19-jdk-slim

WORKDIR /app

COPY pom.xml .
COPY src src

RUN apt-get update && apt-get install -y maven
RUN mvn clean test

CMD ["mvn", "test"]