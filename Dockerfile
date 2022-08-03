## sytax=docker/dockerfile:1
##Which official Java image
#FROM openjdk:19-ea-33-jdk-slim-buster
##Working directory
#WORKDIR /app
##Copy from your host to Container
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
##Run inside the image
#RUN ./mvnw dependency:go-offline
#COPY src ./src
##run inside container
#CMD ["./mvnw", "spring-boot:run"]
