FROM maven:3.3-jdk-8

RUN mvn dependency:get \
  -DremoteRepositories=http://http://192.168.33.10:8081/repository/maven-public/ \
  -DgroupId=tn.esprit \
  -DartifactId=KaddemProject \
  -Dversion=1.0 \
  -Dtransitive=false \
  -Ddest=./kaddem-api.jar

ENTRYPOINT [ "java", "-jar", "./kaddem-api.jar" ] 
