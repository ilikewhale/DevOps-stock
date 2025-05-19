
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY build/libs/stock-0.0.1-SNAPSHOT.jar skala-stock.jar
EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java", "-jar", "/app/skala-stock.jar"]

