CREATE VIEW `new_view` AS
SELECT  	
	uid, position_id, last_update, value,
    case 
		WHEN @last_id = position_id THEN @rank := @rank + 1 
		ELSE @rank := 1 AND @last_id := position_id 
        end AS rank
FROM  position_historic_view
ORDER BY
position_id, last_update desc


CREATE DEFINER=`root`@`localhost` PROCEDURE `historic_limit`(rows INT)
BEGIN

SELECT uid, company_detector_id, sensor_id, last_update, value
    FROM
      ( SELECT  @prev := '', @n := 0 ) init
    JOIN
      ( SELECT  @n := if((company_detector_id + '_' + sensor_id) != @prev, 1, @n + 1) AS n,
                @prev := (company_detector_id + '_' + sensor_id),
                uid, company_detector_id, sensor_id, last_update, value
            FROM  historic
            ORDER BY
                company_detector_id                
      ) x
    WHERE  n <= rows
    order by company_detector_id, sensor_id, last_update desc;   

END

-----------------------------------------------------------------------------------------------------------

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `position_historic_view` AS
    SELECT 
        NEXT_POSITION(`p`.`UID`) AS `count`,
        `h`.`UID` AS `UID`,
        `p`.`UID` AS `POSITION_ID`,
        `h`.`LAST_UPDATE` AS `last_update`,
        `h`.`VALUE` AS `VALUE`
    FROM
        (`position` `p`
        JOIN `historic` `h` ON (((`p`.`COMPANY_DETECTOR_ID` = `h`.`COMPANY_DETECTOR_ID`)
            AND (`p`.`SENSOR_ID` = `h`.`SENSOR_ID`))))
    ORDER BY `p`.`UID` , `h`.`UID` DESC