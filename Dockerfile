FROM openjdk:15-jdk-slim as bulid
WORKDIR application
# file is made in windows so the line endigs has to be
# changed for unix one, dos2unix is used for that
RUN apt-get update && \
    apt-get install dos2unix

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# converting file mvnw to Unix format
RUN dos2unix mvnw && \
 ./mvnw install -DskipTests

RUN cp /application/target/*.jar app.jar && \
    java -Djarmode=layertools -jar app.jar extract

FROM openjdk:15-jdk-slim
WORKDIR application
COPY --from=bulid application/dependencies/ ./
COPY --from=bulid application/spring-boot-loader/ ./
COPY --from=bulid application/snapshot-dependencies/ ./
COPY --from=bulid application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]