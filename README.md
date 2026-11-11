# NutriVida — Backend (Spring Boot)

API REST de la Clínica Nutricional NutriVida para gestionar **productos** (planes nutricionales), **clientes** (pacientes) y **ventas**.

**Asignatura:** DSY1104 Desarrollo Fullstack II
**Integrantes:** Natalie Gallegos, Renato Le Fort

## Tecnologías

| Herramienta | Uso |
| :--- | :--- |
| Java 21 + Spring Boot 4 | Backend y API REST |
| Spring Data JPA + MySQL (XAMPP) | Persistencia de datos |
| Lombok | Getters, setters y constructores de las entidades |
| Flyway | Migraciones y versiones de la base de datos |
| Springdoc OpenAPI (Swagger) | Documentación de los servicios REST |
| Spring HATEOAS | Enlaces en los GET por id |
| Spring Security + JJWT | Autenticación con token JWT |
| JUnit + DataFaker | Pruebas unitarias con datos generados |

## Cómo ejecutar

1. Abrir **XAMPP** e iniciar **MySQL** (usuario `root`, sin contraseña).
2. Abrir el proyecto en IntelliJ y esperar a que Maven descargue las dependencias.
3. Ejecutar la clase `NutrividaBackendApplication`.
4. La API queda disponible en `http://localhost:8080`.

No es necesario crear la base de datos a mano: la conexión usa `createDatabaseIfNotExist=true` y **Flyway** crea las tablas y carga los datos de prueba al iniciar.

## Base de datos (Flyway)

Las migraciones están en `src/main/resources/db/migration`:

| Versión | Archivo | Qué hace |
| :--- | :--- | :--- |
| V1 | `V1__crea_tabla_productos.sql` | Crea la tabla `productos` |
| V2 | `V2__inserta_productos.sql` | Inserta los 4 planes nutricionales |
| V3 | `V3__crea_tabla_clientes.sql` | Crea la tabla `clientes` |
| V4 | `V4__inserta_clientes.sql` | Inserta 3 clientes de prueba |
| V5 | `V5__crea_tabla_ventas.sql` | Crea la tabla `ventas` |
| V6 | `V6__inserta_ventas.sql` | Inserta 3 ventas de prueba |

Flyway registra las migraciones aplicadas en la tabla `flyway_schema_history`. Hibernate no modifica las tablas (`spring.jpa.hibernate.ddl-auto=none`).

## Documentación (Swagger)

Con la aplicación corriendo, abrir:

**http://localhost:8080/swagger-ui/index.html**

Ahí se ven y se pueden probar todos los endpoints, agrupados en Productos, Clientes, Ventas y Autenticación.

## Endpoints

| Método | Ruta | Descripción | Requiere token |
| :--- | :--- | :--- | :--- |
| POST | `/api/v1/auth/login` | Inicia sesión y entrega el token JWT | No |
| GET | `/api/v1/productos` | Lista todos los productos | No |
| GET | `/api/v1/productos/{id}` | Busca un producto (con enlaces HATEOAS) | No |
| POST | `/api/v1/productos` | Crea un producto | **Sí** |
| PUT | `/api/v1/productos/{id}` | Actualiza un producto | **Sí** |
| DELETE | `/api/v1/productos/{id}` | Elimina un producto | **Sí** |

Clientes (`/api/v1/clientes`) y ventas (`/api/v1/ventas`) tienen los mismos 5 endpoints y las mismas reglas: los **GET son públicos** y **POST, PUT y DELETE requieren token**.

### Códigos de respuesta

| Código | Cuándo |
| :--- | :--- |
| 200 | Consulta o actualización correcta |
| 201 | Registro creado |
| 204 | Lista vacía o registro eliminado |
| 401 | Falta el token, es inválido o el login es incorrecto |
| 404 | El id no existe |

## Autenticación JWT

### Credenciales del administrador

| Usuario | Contraseña |
| :--- | :--- |
| admin | admin |

### Cómo usar el token

1. Hacer `POST /api/v1/auth/login` con:
   ```json
   {
     "usuario": "admin",
     "password": "admin"
   }
   ```
2. La respuesta entrega el token (válido por 1 hora):
   ```json
   {
     "token": "eyJhbGciOiJIUzM4NCJ9..."
   }
   ```
3. Enviar el token en las peticiones POST, PUT y DELETE con el header:
   ```
   Authorization: Bearer <token>
   ```
   En Swagger: presionar **Authorize**, pegar solo el token (sin "Bearer") y presionar **Authorize → Close**.

### Cómo funciona

- `JwtUtil` genera y valida los tokens, firmados con la clave `jwt.secret`.
- `JwtFilter` revisa el header `Authorization` en cada petición.
- `SecurityConfig` define qué rutas son públicas y cuáles requieren token.
- `AuthController` valida el usuario y la contraseña y entrega el token.

> La clave secreta y las credenciales están en `application.properties` con fines académicos. En un ambiente real deben ir en variables de entorno.

## Pruebas

Seis pruebas unitarias con **JUnit** y **DataFaker** que siguen el patrón **AAA** (Arrange, Act, Assert). Están en `src/test/java/cl/duoc/nutrivida_backend`:

- `ProductoTest`: registrar producto y actualizar su precio.
- `ClienteTest`: registrar cliente y actualizar su peso.
- `VentaTest`: registrar venta (cálculo del total) y asignar forma de pago.

Para ejecutarlas en IntelliJ: abrir la clase y presionar ▶ junto al nombre. El detalle de cada caso está en [`TESTING_PLAN.md`](TESTING_PLAN.md).

## Estructura

```
src/main/java/cl/duoc/nutrivida_backend/
├── config/        SwaggerConfig, SecurityConfig
├── controller/    ProductoController, ClienteController, VentaController, AuthController
├── model/         Producto, Cliente, Venta, LoginRequest
├── repository/    ProductoRepository, ClienteRepository, VentaRepository
├── security/      JwtUtil, JwtFilter
├── service/       ProductoService, ClienteService, VentaService
└── NutrividaBackendApplication.java

src/main/resources/
├── application.properties
└── db/migration/  V1 a V6 (Flyway)

src/test/java/cl/duoc/nutrivida_backend/
└── ProductoTest, ClienteTest, VentaTest
```
