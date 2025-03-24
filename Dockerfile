# 1단계: 빌드 전용
FROM gradle:7.6.0-jdk17 AS builder
WORKDIR /home/app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew build

# 2단계: 실행 전용 (더 가볍고 빠름)
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /home/app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
