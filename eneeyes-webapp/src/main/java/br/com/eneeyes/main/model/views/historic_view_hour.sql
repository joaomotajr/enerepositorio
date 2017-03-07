CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `historic_view_hour` AS
    SELECT 
    	FLOOR((RAND() * 99999)) AS `UID`,
        `historic`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,
        `historic`.`SENSOR_ID` AS `SENSOR_ID`,
        DATE_FORMAT(`historic`.`LAST_UPDATE`, '%d/%m/%Y') AS `last_update_date`,
        DATE_FORMAT(`historic`.`LAST_UPDATE`, '%Y%m%d') AS `last_update_date_number`,
        COUNT(0) AS `ticks`,
        HOUR(`historic`.`LAST_UPDATE`) AS `last_update_hour`,
        MAX(`historic`.`VALUE`) AS `max_value`,
        MIN(`historic`.`VALUE`) AS `min_value`
    FROM
        `historic`
    GROUP BY `historic`.`COMPANY_DETECTOR_ID` , `historic`.`SENSOR_ID` , DATE_FORMAT(`historic`.`LAST_UPDATE`, '%d/%m/%Y') , HOUR(`historic`.`LAST_UPDATE`)
    ORDER BY `historic`.`COMPANY_DETECTOR_ID` , DATE_FORMAT(`historic`.`LAST_UPDATE`, '%d/%m/%Y') , HOUR(`historic`.`LAST_UPDATE`)