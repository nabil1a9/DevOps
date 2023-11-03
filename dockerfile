FROM openjdk:8-jdk-alpine
EXPOSE 9090
ADD ./target/*.jar app.jar
ENTRYPOINT [ "java", "-jar", "./app.jar" ]
