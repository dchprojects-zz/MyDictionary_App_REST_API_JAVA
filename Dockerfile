FROM openjdk:11
ADD target/mydictionary_rest_api.jar mydictionary_rest_api.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "mydictionary_rest_api.jar"]