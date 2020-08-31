FROM maven:3-adoptopenjdk-11 as builder

RUN mkdir -p /build
WORKDIR /build

COPY pom.xml /build
COPY src /build/src

RUN mvn dependency:resolve dependency:resolve-plugins
RUN mvn package


FROM openjdk:11-jre-slim as runtime

WORKDIR /usr/app

COPY --from=builder /build/target/*.jar /usr/app/app.jar

RUN sh -c 'touch app.jar'

ENTRYPOINT ["java","-jar","app.jar"]
