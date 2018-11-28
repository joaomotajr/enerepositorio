CREATE DEFINER=`root`@`%` PROCEDURE `proc_split_open_close`(_LIMIT int)
BEGIN
  declare done INT DEFAULT false;
  declare historic_id bigint(20);  
  declare company_device_id int;
  declare area_id int;
  declare last_update datetime;  
  declare anterior_last_update datetime;  
  declare value double;
  declare anterior_value double;
  declare last_historic_id bigint(20);

  declare cur1 CURSOR FOR   
	  SELECT h.uid historic_id, cd.uid company_device_id, CD.AREA_ID, H.LAST_UPDATE, h.value
		FROM enedb.historic_a h 
			inner join company_device cd on (h.company_device_id = cd.uid)   
		where cd.device_type = 7 and h.value >= 0  
		LIMIT _LIMIT;  

  declare CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
  
  SET anterior_value  := 0; 
  
  OPEN cur1;  

  read_loop: LOOP
    FETCH cur1 INTO historic_id, company_device_id, area_id, last_update, value;  
    
    IF done THEN 
		/* 
		Atualiza para o ultima historic inserido para não quebrar uma abertura  
        pela metade, face nao termos esse controle por causa do LIMIT de linhas  
		*/
		update control_historic_open set LAST_OPEN_ROW = last_historic_id, last_update = now();
		LEAVE read_loop;      
    END IF;
    
    IF value = 0 AND anterior_value != 0 THEN		
		set last_historic_id = historic_id;
		insert into historic_OPEN VALUES (historic_id, company_device_id, area_id, last_update, anterior_value);        
	END IF;
	SET anterior_value = value;
    SET anterior_last_update = last_update;
    
  END LOOP;

  CLOSE cur1;  
END