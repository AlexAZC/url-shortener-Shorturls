# Usa una imagen de Maven con Java 21 para desarrollar la app de Spring
FROM eclipse-temurin:21-jdk AS build

# Especificamos el directorio
WORKDIR /app

# Copiamos el Maven Wrapper y el archivo pom.xml
COPY mvnw ./
COPY .mvn/ .mvn/

# Nos aseguramos que el Maven Wrapper es ejecutable
RUN chmod +x mvnw

# Copiamos el archivo pom.xml y instalamos sus dependencias
COPY pom.xml ./
RUN ./mvnw dependency:go-offline

# Copiamos el src completo y manejamos la aplicacion
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Usamos una imagen de Java 21 para iniciar la aplicacion
FROM eclipse-temurin:21-jre

# Especificamos el directorio
WORKDIR /app

# Copiamos el archivo jar terminado del stado de manejo
COPY --from=build /app/target/*.jar app.jar

# Especificamos el port que seria 8080
EXPOSE 8080

# Especificamos el comando para que la aplicacion pueda iniciar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

