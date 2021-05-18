FROM openjdk:latest
EXPOSE 8080
COPY target/bieber-tweets-1.0.0-SNAPSHOT.jar twitter-service.jar
ENTRYPOINT ["java","-jar","/twitter-service.jar"]
