FROM eclipse-temurin:17-jdk-focal
LABEL MAINTAINER="kathirit87@gmail.com"
VOLUME /app
COPY target/project-tracker-0.0.1-SNAPSHOT.jar project-tracker-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/project-tracker-1.0.0.jar"]