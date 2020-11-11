FROM openjdk:11-jdk-slim
VOLUME /home/demo
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]