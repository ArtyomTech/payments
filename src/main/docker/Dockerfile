FROM eclipse-temurin:21

WORKDIR /app

COPY target/springboot-start-project-0.0.1-SNAPSHOT.jar /app/spring-boot.jar

CMD ["java", "-jar", "/app/spring-boot.jar", "--spring.profiles.active=local"]
