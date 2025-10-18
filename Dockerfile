FROM eclipse-temurin:17-jdk-jammy

COPY target/WeatherApp-1.0.jar app.jar

EXPOSE 8096

ENTRYPOINT ["java", "-jar", "/app.jar"]

