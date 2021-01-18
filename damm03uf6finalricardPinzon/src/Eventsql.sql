DROP DATABASE IF EXISTS damm03uf6final;
CREATE DATABASE damm03uf6final;
DROP USER IF EXISTS administrador;
CREATE USER administrador IDENTIFIED BY 'admin_event';
GRANT ALL PRIVILEGES ON damm03uf6final.* TO administrador WITH GRANT OPTION;
USE damm03uf6final;

CREATE TABLE Event(
    id INT AUTO_INCREMENT,
    nom VARCHAR(30),
    descripcio VARCHAR(300),
    data_event DATE,
    system_event boolean,
    PRIMARY KEY(id)
);

#INSERT INTO event_examen (nom, descripcio, data_event, system_event) VALUES('X GAMES','Competicio de skate, etc...', '2010-06-18', true);

SELECT * FROM Event;
SELECT * FROM Event where id =1;