CREATE DEFINER=`root`@`localhost` PROCEDURE `move_to_b_by_days`(_LIMIT int, _DAYS int)
BEGIN

	DECLARE _now DATETIME;	
    DECLARE _countRow INT;
    DECLARE _total INT;
    
    SET _now := NOW();
    SET _countRow := 1; 
    SET _total := 0; 
    	
	WHILE(_countRow != 0) DO
    
		START TRANSACTION ;
			INSERT INTO historic_b (SELECT  * FROM historic_a WHERE   LAST_UPDATE < _now - interval _DAYS day LIMIT _LIMIT);			
			DELETE FROM historic_a WHERE LAST_UPDATE < _now - interval _DAYS day LIMIT _LIMIT;
            SET _countRow := ROW_COUNT();            			
            
            SET _total := _total + _countRow; 
            
            DO SLEEP(2);
		COMMIT;    
        
	 END WHILE;
	 
	 INSERT INTO log_event (date_time, event_name, rows_affected) values (now(), concat('MOVE TO B BY ', _DAYS, ' Day(s)'), _total );

	 SELECT _total ; 
END