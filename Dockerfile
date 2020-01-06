FROM openjdk:8
VOLUME /tmp
EXPOSE 8011
ADD ./target/springboot-servicio-manageMovements-0.0.1-SNAPSHOT.jar service-manageMovements.jar
ENTRYPOINT ["java","-jar","/service-manageMovements.jar"]
