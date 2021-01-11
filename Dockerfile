FROM gradle:6.7.1-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build bootJar --no-daemon

FROM openjdk:8-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
COPY --from=build /home/gradle/src/src/main/resources/json/data.json /app/src/main/resources/json/data.json

WORKDIR /app

ENTRYPOINT ["java", "-jar","./spring-boot-application.jar"]