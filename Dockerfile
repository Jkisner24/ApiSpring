FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY ./SpringBootRest/target/*.jar SpringBootRest.jar
ENV SERVER_PORT=80
ENTRYPOINT ["java", "-jar", "/SpringBootRest.jar"]
