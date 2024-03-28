FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/ecommerce-project-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]