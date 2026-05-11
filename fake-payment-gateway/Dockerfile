FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/fake-payment-gateway-*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]