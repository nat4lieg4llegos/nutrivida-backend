package cl.duoc.nutrivida_backend.service;

import cl.duoc.nutrivida_backend.model.Venta;
import cl.duoc.nutrivida_backend.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> getVentas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> getVenta(Integer id) {
        return ventaRepository.findById(id);
    }

    public Venta saveVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Venta updateVenta(Integer id, Venta venta) {
        Optional<Venta> existe = getVenta(id);

        if (existe.isEmpty()) {
            throw new RuntimeException("No encontrado");
        } else {
            venta.setId(id);
            return ventaRepository.save(venta);
        }
    }

    public void deleteVenta(Integer id) {
        if (ventaRepository.existsById(id))
            ventaRepository.deleteById(id);
        else
            throw new RuntimeException("No encontrado");
    }
}