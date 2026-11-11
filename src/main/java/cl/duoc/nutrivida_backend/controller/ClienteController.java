package cl.duoc.nutrivida_backend.controller;

import cl.duoc.nutrivida_backend.model.Cliente;
import cl.duoc.nutrivida_backend.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Clientes", description = "Operaciones relacionadas con los pacientes de NutriVida")
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Obtiene todos los clientes", description = "Retorna la lista completa de pacientes registrados")
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.getClientes();

        if (clientes.isEmpty())
            return ResponseEntity.noContent().build(); // 204
        else
            return ResponseEntity.ok(clientes); // 200
    }

    @Operation(summary = "Obtiene un cliente por su identificador", description = "Retorna un paciente según su ID, con enlaces HATEOAS")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Cliente>> buscarPorId(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteService.getCliente(id);

        if (cliente.isEmpty())
            return ResponseEntity.notFound().build(); // 404

        EntityModel<Cliente> model = EntityModel.of(cliente.get());

        model.add(
                linkTo(
                        methodOn(ClienteController.class)
                                .buscarPorId(id)
                ).withSelfRel()
        );

        model.add(
                linkTo(
                        methodOn(ClienteController.class)
                                .listarClientes()
                ).withRel("todos-los-clientes")
        );

        return ResponseEntity.ok(model); // 200
    }

    @Operation(summary = "Registra un nuevo cliente", description = "Permite registrar un nuevo paciente")
    @PostMapping
    public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.saveCliente(cliente);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201
                .body(nuevoCliente);
    }

    @Operation(summary = "Actualiza un cliente existente", description = "Modifica la información de un paciente según su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarCliente(@RequestBody Cliente cliente, @PathVariable Integer id) {
        try {
            Cliente actualizado = clienteService.updateCliente(id, cliente);
            return ResponseEntity.ok(actualizado); // 200
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    @Operation(summary = "Elimina un cliente", description = "Elimina un paciente utilizando su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
        try {
            clienteService.deleteCliente(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}