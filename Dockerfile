#Etapa 1: Build con Gradle 8.14.2 y JDK 21 (compilacion)
FROM gradle:8.14-jdk-21 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle bootJar --no-deamon

#Etapa 2: Runtime con JDK 21 (Ejecucion)
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar platzi_play.jar
EXPOSE 8080
ENTRYPOINT ["java ","-Dspring.profiles.active=prod", "-jar", "platzi-play-1.0.0.jar"]