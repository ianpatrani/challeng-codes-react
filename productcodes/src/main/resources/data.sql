CREATE TABLE tipo_medicamento
(
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    activo BOOLEAN
);



CREATE TABLE MEDICAMENTO
(
    codigo           INT PRIMARY KEY,
    nombre_comercial VARCHAR(255),
    droga            VARCHAR(255),
    tipo_id          INT,
    FOREIGN KEY (tipo_id) REFERENCES TIPO_MEDICAMENTO (id)
);
