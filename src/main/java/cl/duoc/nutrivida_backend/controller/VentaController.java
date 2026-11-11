package cl.duoc.nutrivida_backend.controller;

import cl.duoc.nutrivida_backend.model.Venta;
import cl.duoc.nutrivida_backend.service.VentaService;
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

@Tag(name = "Ventas", description = "Operaciones relacionadas con las ventas de NutriVida")
@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Operation(summary = "Obtiene todas las ventas", description = "Retorna la lista completa de ventas registradas")
    @GetMapping
    public ResponseEntity<List<Venta>> listarVentas() {
        List<Venta> ventas = ventaService.getVentas();

        if (ventas.isEmpty())
            return ResponseEntity.noContent().build(); // 204
        else
            return ResponseEntity.ok(ventas); // 200
    }

    @Operation(summary = "Obtiene una venta por su identificador", description = "Retorna una venta según su ID, con enlaces HATEOAS")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Venta>> buscarPorId(@PathVariable Integer id) {
        Optional<Venta> venta = ventaService.getVenta(id);

        if (venta.isEmpty())
            return ResponseEntity.notFound().build(); // 404

        EntityModel<Venta> model = EntityModel.of(venta.get());

        model.add(
                linkTo(
                        methodOn(VentaController.class)
                                .buscarPorId(id)
                ).withSelfRel()
        );

        model.add(
                linkTo(
                        methodOn(VentaController.class)
                                .listarVentas()
                ).withRel("todas-las-ventas")
        );

        return ResponseEntity.ok(model); // 200
    }

    @Operation(summary = "Registra una nueva venta", description = "Permite registrar una nueva venta")
    @PostMapping
    public ResponseEntity<Venta> agregarVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.saveVenta(venta);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201
                .body(nuevaVenta);
    }

    @Operation(summary = "Actualiza una venta existente", description = "Modifica la información de una venta según su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Venta> editarVenta(@RequestBody Venta venta, @PathVariable Integer id) {
        try {
            Venta actualizada = ventaService.updateVenta(id, venta);
            return ResponseEntity.ok(actualizada); // 200
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    @Operation(summary = "Elimina una venta", description = "Elimina una venta utilizando su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Integer id) {
        try {
            ventaService.deleteVenta(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}