# Utiliser l'image officielle OpenJDK 11
FROM openjdk:11-jdk

# Exposer le port sur lequel votre application Spring Boot écoute
EXPOSE 8089

# Ajouter le fichier JAR compilé de votre application Spring Boot au conteneur
ADD target/kaddemProject-0.0.1.jar kaddem-app.jar

# Exécuter l'application Spring Boot
ENTRYPOINT ["java","-jar","/kaddem-app.jar"]