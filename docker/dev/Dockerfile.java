FROM openjdk:18-alpine3.14

WORKDIR /api-harry-potter

ENV ENV="dev"

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
