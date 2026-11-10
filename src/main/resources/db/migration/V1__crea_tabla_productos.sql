CREATE TABLE productos (
                           id INT NOT NULL AUTO_INCREMENT,
                           nombre VARCHAR(100) NOT NULL,
                           icono VARCHAR(10),
                           resumen VARCHAR(255) NOT NULL,
                           descripcion VARCHAR(1000) NOT NULL,
                           precio INT NOT NULL,
                           imagen VARCHAR(255),
                           PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;