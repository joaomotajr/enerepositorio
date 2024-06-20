DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clear_from_d_by_years`(_LIMIT int, _YEAR int)
BEGIN

	DECLARE _now DATETIME;	
    DECLARE _countRow INT;
    DECLARE _total INT;
    
    SET _now := NOW();
    SET _countRow := 1; 
    SET _total := 0; 
    	
	WHILE(_countRow != 0) DO
    
		START TRANSACTION ;					
			DELETE FROM historic_d WHERE LAST_UPDATE < _now - interval _YEAR year LIMIT _LIMIT;
            SET _countRow := ROW_COUNT();            			
            
            SET _total := _total + _countRow; 
            
            DO SLEEP(2);
		COMMIT;    
        
	 END WHILE;
     
     INSERT INTO log_event (date_time, event_name, rows_affected) values (now(), concat('DELETED FROM D BY ', _YEAR, ' year(s)'), _total );

	 SELECT _total ; 
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `move_to_a_by_hours`(_LIMIT int, _HOURS int)
BEGIN

	DECLARE _now DATETIME;	
    DECLARE _countRow INT;
    DECLARE _total INT;
    
    SET _now := NOW();
    SET _countRow := 1; 
    SET _total := 0; 
    	
	WHILE(_countRow != 0) DO
    
		START TRANSACTION ;
			INSERT INTO historic_a (SELECT  * FROM historic WHERE   LAST_UPDATE < _now - interval _HOURS hour LIMIT _LIMIT);			
			DELETE FROM historic WHERE LAST_UPDATE < _now - interval _HOURS hour LIMIT _LIMIT;
            SET _countRow := ROW_COUNT();            			
            
            SET _total := _total + _countRow; 
		COMMIT;    
        
	 END WHILE;
	 
	 INSERT INTO log_event (date_time, event_name, rows_affected) values (_now , concat('MOVE TO A BY ', _HOURS, ' Hour(s)'), _total );

	 SELECT _total ; 
END$$
DELIMITER ;

DELIMITER $$
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
	 
	 INSERT INTO log_event (date_time, event_name, rows_affected) values (_now, concat('MOVE TO B BY ', _Days, ' Day(s)'), _total );

	 SELECT _total ; 
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `move_to_c_by_days`(_LIMIT int)
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
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `move_to_d_by_months`(_LIMIT int, _MONTH int)
BEGIN

	DECLARE _now DATETIME;	
    DECLARE _countRow INT;
    DECLARE _total INT;
    
    SET _now := NOW();
    SET _countRow := 1; 
    SET _total := 0; 
    	
	WHILE(_countRow != 0) DO
    
		START TRANSACTION ;
			INSERT INTO historic_d (SELECT  * FROM historic_c WHERE   LAST_UPDATE < _now - interval _MONTH month LIMIT _LIMIT);			
			DELETE FROM historic_c WHERE LAST_UPDATE < _now - interval _MONTH month LIMIT _LIMIT;
            SET _countRow := ROW_COUNT();            			
            
            SET _total := _total + _countRow; 
            
            DO SLEEP(2);
		COMMIT;    
        
	 END WHILE;
     
     INSERT INTO log_event (date_time, event_name, rows_affected) values (now(), concat('MOVE TO D BY ', _MONTH, ' Month(s)'), _total );

	 SELECT _total ; 
END$$
DELIMITER ;
