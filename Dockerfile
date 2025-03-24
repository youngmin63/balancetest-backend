# 1. Java 17 기반 이미지 사용
FROM openjdk:17-jdk-slim

# 2. 작업 디렉토리 생성
WORKDIR /app

# 3. Gradle 빌드된 jar 복사 (jar 이름은 실제 생성된 것과 맞춰야 함)
COPY build/libs/balancetest-0.0.1-SNAPSHOT.jar app.jar

# 4. 서버가 외부 포트로 열릴 수 있도록
EXPOSE 8080

# 5. 실행 명령
ENTRYPOINT ["java", "-jar", "app.jar"]
