FROM openjdk:11
ARG JAR_FILE=target/*.jar
ADD target/mydictionary_rest_api.jar mydictionary_rest_api.jar
COPY ${JAR_FILE} mydictionary_rest_api.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "mydictionary_rest_api.jar"]