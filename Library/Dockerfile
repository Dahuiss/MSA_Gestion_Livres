FROM openjdk:18-jdk

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8003

ENTRYPOINT ["java","-jar","/app.jar"]