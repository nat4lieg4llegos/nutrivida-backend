-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-11-2026 a las 06:51:22
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `nutrivida_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `estatura` int(11) DEFAULT NULL,
  `fecha_nacimiento` date NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `objetivo` varchar(50) DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `rut` varchar(12) NOT NULL,
  `telefono` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `apellido`, `email`, `estatura`, `fecha_nacimiento`, `nombre`, `objetivo`, `peso`, `rut`, `telefono`) VALUES
(1, 'Rojas', 'ana.rojas@gmail.com', 162, '1990-05-10', 'Ana', 'Bajar de peso', 63, '12.345.678-9', '+56912345678');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(1000) NOT NULL,
  `icono` varchar(10) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  `precio` int(11) NOT NULL,
  `resumen` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `descripcion`, `icono`, `imagen`, `nombre`, `precio`, `resumen`) VALUES
(1, 'Programa nutricional enfocado en reducir grasa corporal de manera saludable y sostenible, adaptado a cada paciente.', '🌿', 'plan-perdida-peso.png', 'Plan de pérdida de peso', 39990, 'Enfocado en reducir grasa corporal de manera saludable y sostenible.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `forma_pago` varchar(20) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`id`, `cantidad`, `fecha`, `forma_pago`, `id_cliente`, `id_producto`, `total`) VALUES
(1, 2, '2026-11-10', 'credito', 1, 1, 79980);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK1c96wv36rk2hwui7qhjks3mvg` (`email`),
  ADD UNIQUE KEY `UK1ul8fvgp3gkl0xrm5efjox4bu` (`rut`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
