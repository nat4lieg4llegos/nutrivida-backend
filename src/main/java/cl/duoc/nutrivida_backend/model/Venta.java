package cl.duoc.nutrivida_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Sin relaciones JPA: se guarda solo el ID del cliente y del producto
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Integer total;

    @Column(name = "forma_pago", nullable = false, length = 20)
    private String formaPago;

    @Column(nullable = false)
    private LocalDate fecha;
}