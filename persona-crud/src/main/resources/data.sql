-- Crear la base de datos
CREATE DATABASE personabdd;

-- Usar la base de datos
USE personabdd;

-- Crear la tabla persona
CREATE TABLE persona (
    id_persona BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_persona VARCHAR(255) NOT NULL,
    edad_persona INT NOT NULL,
    telefono_persona VARCHAR(20),
    sexo_persona VARCHAR(10),
    id_ocupacion BIGINT,
    fecha_nac DATE
);

-- Insertar datos iniciales
INSERT INTO persona (nombre_persona, edad_persona, telefono_persona, sexo_persona, id_ocupacion, fecha_nac) VALUES
('Juan Pérez', 30, '555-1234', 'Masculino', 1, '1993-05-15'),
('María López', 25, '555-5678', 'Femenino', 2, '1998-08-20'),
('Carlos García', 40, '555-8765', 'Masculino', 1, '1983-12-30'),
('Ana Martínez', 35, '555-4321', 'Femenino', 3, '1988-03-10');
