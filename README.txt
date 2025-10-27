# 🧩 Demo CRUD Backend (Spring Boot 3 + MySQL)

Backend del sistema con autenticación JWT, control de roles (`SUPER_ADMIN`, `USER`) y CRUD de Productos y Categorías.

---

## 🚀 Tecnologías
- Java 17+
- Spring Boot 3 (Web, Security, Data JPA)
- MySQL / MariaDB
- Lombok
- Gradle o Maven

---

## ⚙️ Configuración

1) Crear base de datos local:
```sql
CREATE DATABASE tarea_crud CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2) Editar `src/main/resources/application.properties` (o `.yml`):
```properties
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/tarea_crud?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

3) Ejecutar
- IntelliJ IDEA: Run la clase principal.
- Gradle: `./gradlew bootRun`
- Maven:  `./mvnw spring-boot:run`

---

##  Credenciales por defecto

| Rol         | Email                   | Contraseña   |
|-------------|-------------------------|--------------|
| SUPER_ADMIN | **superadmin@demo.com** | **sopadepollo** |

> Los nuevos usuarios creados vía `/auth/signup` obtienen rol `USER` por defecto.

---

##  Autenticación

- Login: `POST /auth/login`
```json
{ "email": "superadmin@demo.com", "password": "sopadepollo" }
```
- Header para requests autenticadas:
```
Authorization: Bearer <token>
```

---

##  Endpoints principales

### Auth
- `POST /auth/signup` → registro
- `POST /auth/login`  → login (devuelve token, usuario, expiración)

### Productos (`/api/productos`)
- `GET /api/productos`               → listar (USER / SUPER_ADMIN)
- `GET /api/productos/{id}`          → detalle (USER / SUPER_ADMIN)
- `POST /api/productos`              → crear (SUPER_ADMIN)
- `PUT /api/productos/{id}`          → actualizar (SUPER_ADMIN)
- `DELETE /api/productos/{id}`       → eliminar (SUPER_ADMIN)

> Nota: para crear productos debe existir previamente una Categoría válida. El payload acepta `categoria: { "id": <ID> }`.

### Categorías (`/api/categorias`)
- `GET /api/categorias`              → listar (USER / SUPER_ADMIN)
- `GET /api/categorias/{id}`         → detalle (USER / SUPER_ADMIN)
- `POST /api/categorias`             → crear (SUPER_ADMIN)
- `PUT /api/categorias/{id}`         → actualizar (SUPER_ADMIN)
- `DELETE /api/categorias/{id}`      → eliminar (SUPER_ADMIN)

### Usuarios (`/api/usuarios`)
- `GET /api/usuarios`                → listar usuarios (solo SUPER_ADMIN, devuelve DTO `UserSummary`)

---

##  Pruebas rápidas (Postman)

1) Login
```
POST http://localhost:8080/auth/login
Body: { "email": "superadmin@demo.com", "password": "sopadepollo" }
```

2) Crear categoría
```
POST http://localhost:8080/api/categorias
Body:
{ "nombre": "Tecnología", "descripcion": "Gadgets y electrónicos" }
```

3) Crear producto (usar un `categoria.id` existente)
```
POST http://localhost:8080/api/productos
Body:
{
  "nombre": "Teclado",
  "descripcion": "Mecánico",
  "precio": 49.99,
  "cantidad": 10,
  "categoria": { "id": 1 }
}
```

---

##  Notas
- Roles disponibles: `USER`, `SUPER_ADMIN`.
- Los endpoints protegidos validan roles con `@PreAuthorize`.
- El DTO `UserSummary` evita exponer contraseñas en `/api/usuarios`.
