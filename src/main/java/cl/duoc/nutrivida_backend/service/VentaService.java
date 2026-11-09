package cl.duoc.nutrivida_backend.service;

import cl.duoc.nutrivida_backend.model.Venta;
import cl.duoc.nutrivida_backend.repository.VentaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    // Inyección del repositorio por constructor
    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    // Obtener todos los registros
    public List<Venta> obtenerTodos() {
        return ventaRepository.findAll();
    }

    // Buscar por ID (devuelve null si no existe)
    public Venta buscarPorId(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    // Guardar / crear nuevo registro
    public Venta guardar(Venta venta) {
        venta.setId(null); // asegura que se cree uno nuevo
        return ventaRepository.save(venta);
    }

    // Actualizar registro existente (devuelve null si no existe)
    public Venta actualizar(Long id, Venta datos) {
        Venta existente = buscarPorId(id);
        if (existente == null) {
            return null;
        }
        existente.setIdCliente(datos.getIdCliente());
        existente.setIdProducto(datos.getIdProducto());
        existente.setCantidad(datos.getCantidad());
        existente.setTotal(datos.getTotal());
        existente.setFormaPago(datos.getFormaPago());
        existente.setFecha(datos.getFecha());
        return ventaRepository.save(existente);
    }

    // Eliminar por ID (devuelve false si no existe)
    public boolean eliminar(Long id) {
        if (!ventaRepository.existsById(id)) {
            return false;
        }
        ventaRepository.deleteById(id);
        return true;
    }
}