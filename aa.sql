
CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(100) NOT NULL,
    contraseña VARCHAR(100) NOT NULL,
    rol ENUM('ALUMNO', 'PROFESOR', 'ADMIN') NOT NULL
);

CREATE TABLE Dispositivos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cantidad_disponible INT NOT NULL
);

CREATE TABLE Prestamos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_profesor INT NOT NULL,
    id_alumno INT NOT NULL,
    id_dispositivo INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    FOREIGN KEY (id_profesor) REFERENCES Usuarios(id),
    FOREIGN KEY (id_alumno) REFERENCES Usuarios(id),
    FOREIGN KEY (id_dispositivo) REFERENCES Dispositivos(id)
);

CREATE TABLE Reservas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_alumno INT NOT NULL,
    id_dispositivo INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    FOREIGN KEY (id_alumno) REFERENCES Usuarios(id),
    FOREIGN KEY (id_dispositivo) REFERENCES Dispositivos(id)
);


INSERT INTO Usuarios (correo, contraseña, rol) VALUES
('admin@institucion.edu', 'contraseña_encriptada', 'ADMIN'),
('profesor1@institucion.edu', 'contraseña_encriptada', 'PROFESOR'),
('alumno1@institucion.edu', 'contraseña_encriptada', 'ALUMNO');


INSERT INTO Dispositivos (nombre, cantidad_disponible) VALUES
('Laptop', 10),
('Tablet', 5),
('Teléfono', 8);
