DELIMITER //

CREATE FUNCTION generar_rfc(nombre VARCHAR(50), apellido_pat VARCHAR(50), apellido_mat VARCHAR(50), fecha_nac DATE)
RETURNS VARCHAR(15) deterministic
BEGIN
	DECLARE letraNombre VARCHAR(1);
	DECLARE letrasPat VARCHAR(2);
	DECLARE letraMat VARCHAR(1);
	DECLARE año VARCHAR(4);
	DECLARE mes VARCHAR(2);
	DECLARE dia VARCHAR(2);
	DECLARE rfc VARCHAR(15);
	
	SET letraNombre = LEFT(nombre, 1);
	SET letrasPat = LEFT(apellido_pat, 2);
	
	IF apellido_mat IS NULL THEN
		SET letraMat = 'X';
	ELSE
		SET letraMat = LEFT(apellido_mat, 1);
	END IF;
	
	SET año = YEAR(fecha_nac);
	SET mes = MONTH(fecha_nac);
	SET dia = DAY(fecha_nac);
	
	SET rfc = CONCAT(letrasPat, letraMat, letraNombre, año, mes, dia);
	
	RETURN rfc;
END //

DELIMITER ;