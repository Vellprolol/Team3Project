#
# Build stage
#
FROM maven:3.8.5-openjdk-17-slim AS build
COPY src /app/src
COPY pom.xml /app
WORKDIR /app
RUN mvn clean install

#
# Package stage
#
FROM tomcat:8.5.78-jdk17-openjdk-slim-buster
ENV TZ=Europe/Moscow
COPY --from=build /app/target/Team3Project.war /usr/local/tomcat/webapps
WORKDIR /usr/local/tomcat/bin
EXPOSE 8080
CMD ["catalina.sh", "run"]
