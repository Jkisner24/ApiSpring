FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY ./SpringBootRest/target/*.jar SpringBootRest.jar
ENTRYPOINT ["java", "-jar", "/SpringBootRest.jar"]
