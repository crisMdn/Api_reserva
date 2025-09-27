# 🥘 API REST – Gestión de Reservas de Restaurante

Este proyecto implementa una **API REST** desarrollada con **Spring Boot** para gestionar de forma eficiente, segura y escalable las **reservas de mesas** en un restaurante.  
Está diseñado para integrarse fácilmente con aplicaciones web y móviles, facilitando la interacción entre clientes, personal administrativo y el sistema de disponibilidad en tiempo real.

---

## 🚀 Características principales

- ✅ **CRUD de Reservas**: Crear, actualizar, cancelar y listar reservas.  
- 📅 **Consulta de disponibilidad**: Ver mesas libres y ocupadas en fecha y turno específicos.  
- 👤 **Gestión de clientes**: Registro y consulta de clientes frecuentes.  
- 🔐 **Autenticación y seguridad**: Uso de buenas prácticas y tokens JWT.  
- 🧪 **Pruebas unitarias**: Implementadas con **JUnit** y **Mockito**.  
- 🛠️ **Código limpio**: Uso de **Lombok** para reducir código repetitivo.  
- 🗄️ **Base de datos**: Soporte para distintos gestores, recomendada **PostgreSQL**.  
- 🧩 **Pruebas de endpoints**: Realizadas con **Postman**.  

---

## 🛠️ Tecnologías utilizadas

- [Spring Boot](https://spring.io/projects/spring-boot)  
- [Lombok](https://projectlombok.org/)  
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)  
- [PostgreSQL](https://www.postgresql.org/)  
- [JUnit 5](https://junit.org/junit5/) + [Mockito](https://site.mockito.org/)  
- [Maven](https://maven.apache.org/)  
- [Postman](https://www.postman.com/) para pruebas de endpoints  

---

## ⚙️ Requisitos previos

- Java 17+  
- Maven 3+  
- PostgreSQL (o tu gestor SQL preferido)  
- IntelliJ IDEA (recomendado)  

---

## 📦 Instalación y configuración

1. **Clonar el repositorio**
```bash
git clone https://github.com/usuario/api-reservas.git
cd api-reservas
```

2. **Configurar la base de datos**  
Edita el archivo `src/main/resources/application.properties` con tus credenciales:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/reservasdb
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3. **Instalar dependencias y compilar**
```bash
mvn clean install
```

4. **Ejecutar la aplicación**
```bash
mvn spring-boot:run
```

---

## 🔗 Endpoints principales

| Método | Endpoint                  | Descripción                                 |
|--------|---------------------------|---------------------------------------------|
| POST   | `/api/reservas`           | Crear una nueva reserva                     |
| GET    | `/api/reservas`           | Listar todas las reservas                   |
| GET    | `/api/reservas/{id}`      | Consultar una reserva específica            |
| PUT    | `/api/reservas/{id}`      | Actualizar una reserva                      |
| DELETE | `/api/reservas/{id}`      | Cancelar una reserva                        |
| GET    | `/api/mesas/disponibles`  | Consultar mesas disponibles (fecha/turno)   |

> ⚡ Puedes probar cada endpoint usando **Postman** importando la colección incluida o creando tus propias peticiones.

---

## 🧪 Ejecución de pruebas

```bash
mvn test
```

---

## 📚 Casos de uso contemplados

- Registrar reserva  
- Consultar disponibilidad  
- Actualizar o cancelar reserva  
- Gestionar clientes  
- Listar reservas filtradas por fecha o turno  
- (Opcional) Registrar pagos y consumos futuros  

---

## 👥 Autores

- Armando Enrique García Méndez  
- Christopher Bryan Rodríguez Medina  
- Danilo Alejandro Quintana Vásquez  
- José Alfredo López Rivera  
- Raúl Enrique Arévalo Ávila  
