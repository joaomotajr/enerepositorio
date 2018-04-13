CREATE DEFINER=`root`@`localhost` PROCEDURE `move_to_a_by_minutes`(_LIMIT int, _MINUTES int)
BEGIN

	DECLARE _now DATETIME;	
    DECLARE _countRow INT;
    DECLARE _total INT;
    
    SET _now := NOW();
    SET _countRow := 1; 
    SET _total := 0; 
    	
	WHILE(_countRow != 0) DO
    
		START TRANSACTION ;
			INSERT INTO historic_a (SELECT  * FROM historic WHERE   LAST_UPDATE < _now - interval _MINUTES minute LIMIT _LIMIT);			
			DELETE FROM historic WHERE LAST_UPDATE < _now - interval _MINUTES minute LIMIT _LIMIT;
            SET _countRow := ROW_COUNT();            			
            
            SET _total := _total + _countRow; 
		COMMIT;    
        
	 END WHILE;
	 
     INSERT INTO log_event (date_time, event_name, rows_affected) values (now(), concat('MOVE TO A BY ', _MINUTES, ' Minutes(s)'), _total );
     
	 SELECT _total ; 
END