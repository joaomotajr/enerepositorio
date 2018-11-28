declare historic_id bigint(20);  
  declare company_device_id int;
  declare area_id int;
  declare last_update datetime;
  declare value double;
  declare old_value double;

  declare cur1 CURSOR FOR   
	  SELECT ha.uid historic_id, cd.uid company_device_id, CD.AREA_ID, HA.LAST_UPDATE, ha.value
		FROM enedb.historic_a ha 
			inner join company_device cd on (ha.company_device_id = cd.uid)   
		where cd.device_type = 7    
		LIMIT _LIMIT;  

  declare CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
  
  SET old_value  := 0; 
  
  OPEN cur1;  

  read_loop: LOOP
    FETCH cur1 INTO historic_id, company_device_id, area_id, last_update, value;  
    
    IF done THEN 
      LEAVE read_loop;
    END IF;          
    
    if value = 0 and old_value != 0 then		
		insert into historic_OPEN VALUES (historic_id, company_device_id, area_id, last_update, old_value);
	end if;
	set old_value = value;
    
    
  END LOOP;

  CLOSE cur1;  
END