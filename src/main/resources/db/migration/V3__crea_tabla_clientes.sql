CREATE TABLE clientes (
                          id INT NOT NULL AUTO_INCREMENT,
                          nombre VARCHAR(50) NOT NULL,
                          apellido VARCHAR(50) NOT NULL,
                          rut VARCHAR(12) NOT NULL,
                          fecha_nacimiento DATE NOT NULL,
                          telefono VARCHAR(12),
                          email VARCHAR(100) NOT NULL,
                          peso DOUBLE,
                          estatura INT,
                          objetivo VARCHAR(50),
                          PRIMARY KEY (id),
                          UNIQUE KEY uk_clientes_rut (rut),
                          UNIQUE KEY uk_clientes_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;