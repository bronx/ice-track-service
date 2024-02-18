FROM amazoncorretto:17
COPY build/libs/track-service-0.0.1-SNAPSHOT.jar track-service.jar
CMD ["java", "-jar", "track-service.jar"]