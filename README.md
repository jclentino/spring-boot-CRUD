# spring-boot-CRUD
CRUD básico de 2 entidades creado con Spring Boot y MySQL 

Mini aplicacion en la que puedes registrar usuarios y docentes realizando operqaciones CRUD con cada uno.


## Configuración

Asegurate de tener todas las dependencias instaladas, debes tener una base de datos creada con el nombre app, y crear 2 entidades para poder hacerlo correr 

    CREATE TABLE Usuarios (
        cedula INT PRIMARY KEY,
        clave VARCHAR(255) NOT NULL,
        nombre VARCHAR(255) NOT NULL,
        telefono VARCHAR(20) NOT NULL,
        email VARCHAR(255) UNIQUE NOT NULL
    );

    CREATE TABLE Docentes (
        id INT PRIMARY KEY AUTO_INCREMENT,
        usuario_id INT,
        nombre VARCHAR(255) NOT NULL,
        apellidos VARCHAR(255) NOT NULL,
        email VARCHAR(255) NOT NULL,
        telefono VARCHAR(20) NOT NULL,
        blog VARCHAR(255),
        profesional BOOLEAN,
        escalaron BOOLEAN,
        idioma VARCHAR(50),
        años_experiencia INT,
        area_trabajo VARCHAR(255),
        FOREIGN KEY (usuario_id) REFERENCES Usuarios(cedula)
    );

y insertar algunos registros de prueba 


    INSERT INTO Usuarios (cedula, clave, nombre, telefono, email)
    VALUES (1, 'juan1', 'Juan', '305', 'j@gmail.com');
    
    INSERT INTO Docentes (usuario_id, nombre, apellidos, email, telefono, blog, profesional, escalaron, idioma, años_experiencia, area_trabajo)
    VALUES (1, 'Jhon', 'Arrieta Arrieta', 'jhon@gmail.com', '456', 'jhonarrietablog.com', 1, 1, 'Español', 10, 'Software');
