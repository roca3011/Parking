INSERT INTO TIPO_VEHICULO(TIPO_VEHICULO_ID, DESCRIPCION)VALUES(1, 'CARRO');
INSERT INTO TIPO_VEHICULO(TIPO_VEHICULO_ID, DESCRIPCION)VALUES(2, 'MOTO');

INSERT INTO VEHICULO(ID_VEHICULO, TIPO_VEHICULO_ID, CILINDRAJE, PLACA, FECHA_CREACION)VALUES(100, 2, 600, 'NGI-583', '2019-07-23');

INSERT INTO FACTURA(ID_FACTURA, FECHA_INGRESO, FECHA_SALIDA, VALOR_TOTAL, ID_VEHICULO, ESTADO)VALUES(1,'2019-07-23', NULL, 0, 100, TRUE);