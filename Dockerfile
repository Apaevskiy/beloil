FROM openjdk:8-jre
EXPOSE 8080
ARG JAR_FILE=target/app.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]