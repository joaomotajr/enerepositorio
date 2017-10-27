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

	 SELECT _total ; 
END