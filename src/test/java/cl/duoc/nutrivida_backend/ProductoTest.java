package cl.duoc.nutrivida_backend;

import cl.duoc.nutrivida_backend.model.Producto;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductoTest {

    @Test
    void registrarProducto() {
        // ARRANGE (preparar)
        Producto producto = new Producto();
        Faker faker = new Faker();

        producto.setNombre("Plan de pérdida de peso");
        producto.setResumen(faker.lorem().sentence());
        producto.setDescripcion(faker.lorem().paragraph());
        producto.setPrecio(35000);

        // ACT (ejecutar)
        System.out.println(producto);

        // ASSERT (verificar)
        assertNotNull(producto);
        assertEquals("Plan de pérdida de peso", producto.getNombre());
        assertEquals(35000, producto.getPrecio());
    }

    @Test
    void actualizarPrecioProducto() {
        // ARRANGE (preparar)
        Faker faker = new Faker();
        Producto producto = new Producto();
        producto.setNombre(faker.food().dish());
        producto.setPrecio(faker.number().numberBetween(30000, 50000));

        // ACT (ejecutar)
        producto.setPrecio(39990);
        System.out.println(producto);

        // ASSERT (verificar)
        assertEquals(39990, producto.getPrecio());
    }
}