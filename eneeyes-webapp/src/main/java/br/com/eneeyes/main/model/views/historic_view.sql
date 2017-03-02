CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `historic_view` AS
    SELECT 
        `position`.`UID` AS `UID`,
        `position`.`LAST_UPDATE` AS `LAST_UPDATE`,
        `position`.`LAST_VALUE` AS `LAST_VALUE`,
        `position`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,
        `position`.`SENSOR_ID` AS `SENSOR_ID`,
        `position`.`ALARM_TYPE` AS `ALARM_TYPE`
    FROM
        `position`
    ORDER BY `position`.`COMPANY_DETECTOR_ID` , `position`.`LAST_UPDATE` DESC