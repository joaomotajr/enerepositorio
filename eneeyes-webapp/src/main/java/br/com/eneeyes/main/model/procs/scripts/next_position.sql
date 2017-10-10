
CREATE DEFINER=`root`@`localhost` FUNCTION `NEXT_POSITION`(detector char(5)) RETURNS int(11)
begin

	IF @current_detector = detector THEN 		
		SET @detector_rank := @detector_rank + 1;        
	else
		SET @detector_rank := 1;          
	END IF;     
    set @current_detector := detector;
    
	return @detector_rank;
END;

