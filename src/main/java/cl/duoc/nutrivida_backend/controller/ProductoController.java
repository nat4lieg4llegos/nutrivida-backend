package cl.duoc.nutrivida_backend.controller;

import cl.duoc.nutrivida_backend.model.Producto;
import cl.duoc.nutrivida_backend.service.ProductoService;
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

@Tag(name = "Productos", description = "Operaciones relacionadas con los planes nutricionales de NutriVida")
@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Obtiene todos los productos", description = "Retorna la lista completa de planes nutricionales registrados")
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = productoService.getProductos();

        if (productos.isEmpty())
            return ResponseEntity.noContent().build(); // 204
        else
            return ResponseEntity.ok(productos); // 200
    }

    @Operation(summary = "Obtiene un producto por su identificador", description = "Retorna un plan nutricional según su ID, con enlaces HATEOAS")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Producto>> buscarPorId(@PathVariable Integer id) {
        Optional<Producto> producto = productoService.getProducto(id);

        if (producto.isEmpty())
            return ResponseEntity.notFound().build(); // 404

        EntityModel<Producto> model = EntityModel.of(producto.get());

        model.add(
                linkTo(
                        methodOn(ProductoController.class)
                                .buscarPorId(id)
                ).withSelfRel()
        );

        model.add(
                linkTo(
                        methodOn(ProductoController.class)
                                .listarProductos()
                ).withRel("todos-los-productos")
        );

        return ResponseEntity.ok(model); // 200
    }

    @Operation(summary = "Registra un nuevo producto", description = "Permite crear un nuevo plan nutricional")
    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.saveProducto(producto);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201
                .body(nuevoProducto);
    }

    @Operation(summary = "Actualiza un producto existente", description = "Modifica la información de un plan nutricional según su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Producto> editarProducto(@RequestBody Producto producto, @PathVariable Integer id) {
        try {
            Producto actualizado = productoService.updateProducto(id, producto);
            return ResponseEntity.ok(actualizado); // 200
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    @Operation(summary = "Elimina un producto", description = "Elimina un plan nutricional utilizando su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        try {
            productoService.deleteProducto(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}