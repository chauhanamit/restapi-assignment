FROM openjdk:11
ADD target/rest-api.jar rest-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","rest-api.jar"]