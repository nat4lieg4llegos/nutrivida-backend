package cl.duoc.nutrivida_backend.service;

import cl.duoc.nutrivida_backend.model.Producto;
import cl.duoc.nutrivida_backend.repository.ProductoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    // Inyección del repositorio por constructor
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Obtener todos los registros
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // Buscar por ID (devuelve null si no existe)
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    // Guardar / crear nuevo registro
    public Producto guardar(Producto producto) {
        producto.setId(null); // asegura que se cree uno nuevo
        return productoRepository.save(producto);
    }

    // Actualizar registro existente (devuelve null si no existe)
    public Producto actualizar(Long id, Producto datos) {
        Producto existente = buscarPorId(id);
        if (existente == null) {
            return null;
        }
        existente.setNombre(datos.getNombre());
        existente.setIcono(datos.getIcono());
        existente.setResumen(datos.getResumen());
        existente.setDescripcion(datos.getDescripcion());
        existente.setPrecio(datos.getPrecio());
        existente.setImagen(datos.getImagen());
        return productoRepository.save(existente);
    }

    // Eliminar por ID (devuelve false si no existe)
    public boolean eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            return false;
        }
        productoRepository.deleteById(id);
        return true;
    }
}