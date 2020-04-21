FROM openjdk:8-jre-slim

RUN mkdir /app
COPY build/libs/*.jar /app/spring-boot-application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/spring-boot-application.jar"]
