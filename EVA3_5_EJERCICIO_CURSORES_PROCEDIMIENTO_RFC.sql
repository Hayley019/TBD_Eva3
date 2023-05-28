DELIMITER //

CREATE PROCEDURE actualizar_rfc()
BEGIN
	DECLARE contador INT DEFAULT 0;
	
	UPDATE personas SET clave_rfc = generar_rfc(nombre, apellido_pat, apellido_mat, fecha_nac)
	WHERE clave_rfc IS NULL;
	SET contador = ROW_COUNT();	
	
	SELECT CONCAT('Se actualizaron ', contador, ' registros') AS mensaje;
END //

DELIMITER ;
