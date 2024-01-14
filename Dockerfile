FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar SpringBoot.jar
ENTRYPOINT ["java","-jar","/SpringBoot.jar"]
EXPOSE 8080