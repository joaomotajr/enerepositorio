CREATE DEFINER=`root`@`localhost` PROCEDURE `move_to_c_by_7day`(_LIMIT int)
BEGIN

	DECLARE _now DATETIME;	
    DECLARE _countRow INT;
    DECLARE _total INT;
    
    SET _now := NOW();
    SET _countRow := 1; 
    SET _total := 0; 
    	
	WHILE(_countRow != 0) DO
    
		START TRANSACTION ;
			INSERT INTO historic_c (SELECT  * FROM historic_b WHERE   LAST_UPDATE < _now - interval 7 day LIMIT _LIMIT);			
			DELETE FROM historic_b WHERE LAST_UPDATE < _now - interval 7 day LIMIT _LIMIT;
            SET _countRow := ROW_COUNT();            			
            
            SET _total := _total + _countRow; 
            
            DO SLEEP(2);
		COMMIT;    
        
	 END WHILE;
     
     INSERT INTO log_event (date_time, event_name, rows_affected) values (now(), concat('MOVE TO C BY ', '7', ' Day(s)'), _total );

	 SELECT _total ; 
END