package cl.duoc.nutrivida_backend.service;

import cl.duoc.nutrivida_backend.model.Producto;
import cl.duoc.nutrivida_backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProducto(Integer id) {
        return productoRepository.findById(id);
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Integer id, Producto producto) {
        Optional<Producto> existe = getProducto(id);

        if (existe.isEmpty()) {
            throw new RuntimeException("No encontrado");
        } else {
            producto.setId(id);
            return productoRepository.save(producto);
        }
    }

    public void deleteProducto(Integer id) {
        if (productoRepository.existsById(id))
            productoRepository.deleteById(id);
        else
            throw new RuntimeException("No encontrado");
    }
}