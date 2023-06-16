FROM openjdk:17-jdk
WORKDIR /afcursos
COPY target/*.war /afcursos/afcursos-0.0.1-SNAPSHOT.war
EXPOSE 9090
CMD java -XX:+UseContainerSupport -Xmx512m  -Dserver.port=9090 -jar afcursos-0.0.1-SNAPSHOT.war