package cl.duoc.nutrivida_backend.repository;

import cl.duoc.nutrivida_backend.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}