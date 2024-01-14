FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar ApiProject.jar
ENTRYPOINT ["java","-jar","/ApiProject.jar"]
EXPOSE 8080