package cl.duoc.nutrivida_backend.controller;

import cl.duoc.nutrivida_backend.model.Venta;
import cl.duoc.nutrivida_backend.service.VentaService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    // Inyección del servicio por constructor
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    // GET /api/ventas -> obtener todos
    @GetMapping
    public List<Venta> obtenerTodos() {
        return ventaService.obtenerTodos();
    }

    // GET /api/ventas/{id} -> buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarPorId(@PathVariable Long id) {
        Venta venta = ventaService.buscarPorId(id);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(venta);
    }

    // POST /api/ventas -> crear
    @PostMapping
    public ResponseEntity<Venta> guardar(@RequestBody Venta venta) {
        Venta nueva = ventaService.guardar(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // PUT /api/ventas/{id} -> actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(@PathVariable Long id, @RequestBody Venta venta) {
        Venta actualizada = ventaService.actualizar(id, venta);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    // DELETE /api/ventas/{id} -> eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!ventaService.eliminar(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}