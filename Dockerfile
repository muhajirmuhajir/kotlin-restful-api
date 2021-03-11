FROM openjdk:slim

COPY ./build/libs/kotlin-restful-api-0.0.1.jar /app/application.jar

CMD ["java", "-jar", "app/application.jar"]