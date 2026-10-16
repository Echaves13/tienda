# Etapa 1: Compilación
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

RUN mvn -f pom.xml clean package -DskipTests

# Etapa 2: Creación de la imagen final
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar ./app.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]

