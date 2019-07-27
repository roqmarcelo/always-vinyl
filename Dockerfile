FROM openjdk:8-jdk-alpine

MAINTAINER Roque Santos <roquesantosdev@gmail.com>

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/always-vinyl-1.0.0.jar

ADD ${JAR_FILE} always-vinyl.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/always-vinyl.jar"]