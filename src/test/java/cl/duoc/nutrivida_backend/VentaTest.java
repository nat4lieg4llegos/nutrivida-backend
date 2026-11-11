package cl.duoc.nutrivida_backend;

import cl.duoc.nutrivida_backend.model.Venta;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VentaTest {

    @Test
    void registrarVenta() {
        // ARRANGE (preparar)
        Venta venta = new Venta();
        Faker faker = new Faker();
        int precioPlan = 35000;

        venta.setIdCliente(faker.number().numberBetween(1, 4));
        venta.setIdProducto(1);
        venta.setCantidad(2);
        venta.setFormaPago("credito");
        venta.setFecha(LocalDate.now());

        // ACT (ejecutar)
        venta.setTotal(precioPlan * venta.getCantidad());
        System.out.println(venta);

        // ASSERT (verificar)
        assertNotNull(venta);
        assertEquals(70000, venta.getTotal());
    }

    @Test
    void formaPagoVenta() {
        // ARRANGE (preparar)
        Faker faker = new Faker();
        Venta venta = new Venta();
        venta.setCantidad(faker.number().numberBetween(1, 5));

        // ACT (ejecutar)
        venta.setFormaPago("debito");
        System.out.println(venta);

        // ASSERT (verificar)
        assertEquals("debito", venta.getFormaPago());
    }
}