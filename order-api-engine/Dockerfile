FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/order-api-engine-*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]