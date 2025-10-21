# Imagen base con Java 17
FROM openjdk:17-jdk-slim

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml (ruta ajustada a tu estructura)
COPY apireserva/apireserva/pom.xml .

# Descargar dependencias
RUN mvn dependency:go-offline

# Copiar el resto del proyecto
COPY apireserva/apireserva/. .

# Compilar sin correr tests
RUN mvn clean package -DskipTests

# Exponer el puerto
EXPOSE 8080

# Ejecutar el jar
CMD ["java", "-jar", "target/apireserva-0.0.1-SNAPSHOT.jar"]

