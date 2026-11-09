package cl.duoc.nutrivida_backend.controller;

import cl.duoc.nutrivida_backend.model.Venta;
import cl.duoc.nutrivida_backend.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> listarVentas() {
        List<Venta> ventas = ventaService.getVentas();

        if (ventas.isEmpty())
            return ResponseEntity.noContent().build(); // 204
        else
            return ResponseEntity.ok(ventas); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarPorId(@PathVariable Integer id) {
        Optional<Venta> venta = ventaService.getVenta(id);

        if (venta.isPresent())
            return ResponseEntity.ok(venta.get()); // 200
        else
            return ResponseEntity.notFound().build(); // 404
    }

    @PostMapping
    public ResponseEntity<Venta> agregarVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.saveVenta(venta);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201
                .body(nuevaVenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> editarVenta(@RequestBody Venta venta, @PathVariable Integer id) {
        try {
            Venta actualizada = ventaService.updateVenta(id, venta);
            return ResponseEntity.ok(actualizada); // 200
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }

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