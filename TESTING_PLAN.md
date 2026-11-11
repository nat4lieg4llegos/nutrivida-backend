# Plan de pruebas - NutriVida Backend

## Objetivo
Validar el correcto funcionamiento de las entidades Producto, Cliente y Venta del backend de la Clínica Nutricional NutriVida.

## Herramientas

* Java 21
* Spring Boot
* JUnit
* DataFaker

## Cómo ejecutar
En IntelliJ, abrir cada clase de `src/test/java/cl/duoc/nutrivida_backend` y presionar el botón ▶ junto al nombre de la clase.

Todas las pruebas siguen el patrón **AAA**: Arrange (preparar), Act (ejecutar), Assert (verificar).


## CP-01: Registrar producto
### Clase / Método
`ProductoTest` / `registrarProducto()`

### Objetivo
* Verificar que un producto (plan nutricional) pueda crearse con información válida.
* El nombre del producto sea "Plan de pérdida de peso" y su precio sea 35000.

### Datos
Resumen y descripción generados con DataFaker (`faker.lorem()`).

### Resultado
✅ Pasa


## CP-02: Actualizar precio de un producto
### Clase / Método
`ProductoTest` / `actualizarPrecioProducto()`

### Objetivo
* Verificar que el precio de un producto pueda modificarse.
* El nuevo precio sea 39990.

### Datos
Nombre y precio inicial generados con DataFaker (`faker.food()`, `faker.number()`).

### Resultado
✅ Pasa


## CP-03: Registrar cliente
### Clase / Método
`ClienteTest` / `registrarCliente()`

### Objetivo
* Verificar que un cliente (paciente) pueda crearse con información válida.
* El cliente tenga correo y su objetivo sea "Bajar de peso".

### Datos
Nombre, apellido, correo y estatura generados con DataFaker (`faker.name()`, `faker.internet()`, `faker.number()`).

### Resultado
✅ Pasa


## CP-04: Actualizar peso de un cliente
### Clase / Método
`ClienteTest` / `actualizarPesoCliente()`

### Objetivo
* Verificar que el peso de un cliente pueda modificarse.
* El nuevo peso sea 68.5 kg.

### Datos
Nombre y peso inicial generados con DataFaker (`faker.name()`, `faker.number().randomDouble()`).

### Resultado
✅ Pasa


## CP-05: Registrar venta
### Clase / Método
`VentaTest` / `registrarVenta()`

### Objetivo
* Verificar que una venta pueda crearse con información válida.
* El total de 2 sesiones de un plan de $35.000 sea 70000.

### Datos
Cliente de la venta generado con DataFaker (`faker.number()`).

### Resultado
✅ Pasa


## CP-06: Forma de pago de una venta
### Clase / Método
`VentaTest` / `formaPagoVenta()`

### Objetivo
* Verificar que la forma de pago de una venta pueda asignarse.
* La forma de pago sea "debito".

### Datos
Cantidad generada con DataFaker (`faker.number()`).

### Resultado
✅ Pasa


## Resumen de ejecución

| Clase | Pruebas | Resultado |
| :--- | :--- | :--- |
| ProductoTest | 2 | ✅ 2 pasan |
| ClienteTest | 2 | ✅ 2 pasan |
| VentaTest | 2 | ✅ 2 pasan |
| **Total** | **6** | **✅ 6 pasan** |

Se ejecutaron 6 pruebas unitarias y las 6 pasaron. No se detectaron defectos.
