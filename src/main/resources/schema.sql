CREATE TABLE IF NOT EXISTS `student` (
    `id` BIGINT NOT NULL,
    `name` VARCHAR(250) NOT NULL,
    `last_name` VARCHAR(250) NOT NULL,
    `age` INT NOT NULL,
    `status` VARCHAR(25) NOT NULL,
     PRIMARY KEY (id)
);

--INSERT INTO student (id, name, last_name, age, status) VALUES (1000, 'Juan', 'Fernando Pérez del Corral', 78, 'INACTIVO');
--INSERT INTO student (id, name, last_name, age, status) VALUES (1001, 'Valentina', 'Laverde de la Rosa', 20, 'INACTIVO');
--INSERT INTO student (id, name, last_name, age, status) VALUES (1002, 'Oscar', 'de la Renta', 49, 'INACTIVO');
--INSERT INTO student (id, name, last_name, age, status) VALUES (1003, 'Sara', 'Teresa Sánchez del Pinar', 34, 'INACTIVO');
--INSERT INTO student (id, name, last_name, age, status) VALUES (1004, 'Efraín', 'de las Casas Mejía', 45, 'INACTIVO');
--INSERT INTO student (id, name, last_name, age, status) VALUES (1005, 'Julieta', 'Ponce de León', 23, 'INACTIVO');
--INSERT INTO student (id, name, last_name, age, status) VALUES (1006, 'Martín Elias', 'de los Rios Acosta', 54, 'INACTIVO');