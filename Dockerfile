FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY ./target/*.jar SpringBootRest.jar
ENTRYPOINT ["java", "-jar", "/SpringBootRest.jar"]
EXPOSE 8080