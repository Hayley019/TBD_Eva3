create trigger mensaje after insert on personas
for each row
set @mensaje = "PERSONA INSERTADA CON EXITO";

DELIMITER //
create trigger datos_previos_update_per after update on personas
for each row
BEGIN
	insert into auditoria_per
    values(
		OLD.id,
        OLD.nombre,
        OLD.apellido_pat,
        OLD.apellido_mat,
        OLD.fecha_nac,
        OLD.clave_rfc        
    );
END // 
DELIMITER ;

DELIMITER //
create trigger nuevo_actor before insert on actor
for each row

BEGIN
	declare actorID int;
    declare calcular_id int;
    
    select max(actor_id) into actorID from actor;
    if actorID is null then
		set calcular_id = 1;
    else
		set calcular_id = actorID +1;
	end if;
	set new.actor_id = calcular_id;
END//
DELIMITER ;