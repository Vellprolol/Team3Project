#
# Build stage
#
FROM docker pull maven:3.8.6-openjdk-11-slim AS build
COPY src /app/src
COPY pom.xml /app
WORKDIR /app
RUN mvn clean install

#
# Package stage
#
FROM tomcat:8.5.82-jre8-openjdk-slim-buster
ENV TZ=Europe/Moscow
COPY --from=build /app/target/Team3Project.war /usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]