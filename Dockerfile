FROM gradle:7.6.0-jdk17 AS builder
COPY . /home/app
WORKDIR /home/app
RUN gradle build

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /home/app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
