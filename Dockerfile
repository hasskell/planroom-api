FROM eclipse-temurin:21-jre-alpine
ARG JAR=build/libs/*.jar
COPY ${JAR} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
