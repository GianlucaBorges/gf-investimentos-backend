# Etapa 1: Build da aplicação
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

# Copia apenas o necessário para cache eficiente
COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagem final com apenas o .jar
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia apenas o .jar gerado
COPY --from=builder /app/target/back-gf-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar",  "--logging.file.name=application.log"]
