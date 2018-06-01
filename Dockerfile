# Scarica sistema operativo alpine con jre
FROM openjdk:8-jre-alpine

# Crea la cartella e ci si sposta dentro
WORKDIR app

# Copia jar
COPY target/goosegame-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

# Esegue un comando
CMD ["java", "-jar", "app.jar"]


