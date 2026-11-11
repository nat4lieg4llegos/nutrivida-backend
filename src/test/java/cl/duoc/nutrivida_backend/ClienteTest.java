package cl.duoc.nutrivida_backend;

import cl.duoc.nutrivida_backend.model.Cliente;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClienteTest {

    @Test
    void registrarCliente() {
        // ARRANGE (preparar)
        Cliente cliente = new Cliente();
        Faker faker = new Faker();

        cliente.setNombre(faker.name().firstName());
        cliente.setApellido(faker.name().lastName());
        cliente.setEmail(faker.internet().emailAddress());
        cliente.setFechaNacimiento(LocalDate.of(1995, 3, 20));
        cliente.setEstatura(faker.number().numberBetween(150, 190));
        cliente.setObjetivo("Bajar de peso");

        // ACT (ejecutar)
        System.out.println(cliente);

        // ASSERT (verificar)
        assertNotNull(cliente);
        assertNotNull(cliente.getEmail());
        assertEquals("Bajar de peso", cliente.getObjetivo());
    }

    @Test
    void actualizarPesoCliente() {
        // ARRANGE (preparar)
        Faker faker = new Faker();
        Cliente cliente = new Cliente();
        cliente.setNombre(faker.name().firstName());
        cliente.setPeso(faker.number().randomDouble(1, 50, 120));

        // ACT (ejecutar)
        cliente.setPeso(68.5);
        System.out.println(cliente);

        // ASSERT (verificar)
        assertEquals(68.5, cliente.getPeso());
    }
}