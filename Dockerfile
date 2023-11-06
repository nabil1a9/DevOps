FROM openjdk:11
EXPOSE 8089
RUN apt-get update && apt-get install -y curl
RUN curl -o KaddemProject-1.0.jar http://192.168.1.3:8081/#browse/browse:maven-releases:tn%2Fesprit%2FKaddemProject%2F1.0%2FKaddemProject-1.0.jar
ADD target/*.jar KaddemProject-1.0.jar
ENTRYPOINT ["java","-jar","/KaddemProject-1.0.jar"]