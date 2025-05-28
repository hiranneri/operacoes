FROM alpine/java:21.0.4-jre
COPY target/operacoes-0.0.1-SNAPSHOT.jar operacoes.jar
ENTRYPOINT ["java", "-jar", "/operacoes.jar"]
