-- Tabla de Equipos
CREATE TABLE equipos (
                         id BIGSERIAL PRIMARY KEY,
                         nombre VARCHAR(255) NOT NULL,
                         descripcion TEXT,
                         numero_serie VARCHAR(100) UNIQUE NOT NULL,
                         fecha_adquisicion DATE NOT NULL,
                         estado VARCHAR(50) NOT NULL DEFAULT 'OPERATIVO',
                         ubicacion VARCHAR(255),
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Tipos de Mantenimiento
CREATE TABLE tipos_mantenimiento (
                                     id BIGSERIAL PRIMARY KEY,
                                     nombre VARCHAR(100) NOT NULL UNIQUE,
                                     descripcion TEXT
);

-- Datos iniciales para tipos de mantenimiento
INSERT INTO tipos_mantenimiento (nombre, descripcion) VALUES
                                                          ('PREVENTIVO', 'Mantenimiento programado para prevenir fallos'),
                                                          ('CORRECTIVO', 'Mantenimiento por falla o mal funcionamiento');

-- Tabla de Mantenimientos
CREATE TABLE mantenimientos (
                                id BIGSERIAL PRIMARY KEY,
                                equipo_id BIGINT NOT NULL REFERENCES equipos(id) ON DELETE CASCADE,
                                tipo_mantenimiento_id BIGINT NOT NULL REFERENCES tipos_mantenimiento(id),
                                fecha_programada DATE NOT NULL,
                                fecha_ejecucion DATE,
                                descripcion TEXT,
                                estado VARCHAR(50) NOT NULL DEFAULT 'PENDIENTE',
                                observaciones TEXT,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);