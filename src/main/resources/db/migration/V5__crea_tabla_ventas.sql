CREATE TABLE ventas (
                        id INT NOT NULL AUTO_INCREMENT,
                        id_cliente INT NOT NULL,
                        id_producto INT NOT NULL,
                        cantidad INT NOT NULL,
                        total INT NOT NULL,
                        forma_pago VARCHAR(20) NOT NULL,
                        fecha DATE NOT NULL,
                        PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;