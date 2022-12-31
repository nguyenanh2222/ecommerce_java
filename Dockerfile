FROM openjdk:8 as jdk
ADD target/auth-0.0.1-SNAPSHOT.jar my-maven-docker-project.jar
ENTRYPOINT ["java", "-jar","auth-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080