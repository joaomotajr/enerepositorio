CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `alarm_companydetector_sensor_view` AS
    SELECT 
        CONCAT(`cda`.`COMPANY_DETECTOR_ID`,
                `cda`.`SENSOR_ID`) AS `UID`,
        `cda`.`ALARM_ID` AS `ALARM_ID`,
        `cda`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,
        `cda`.`SENSOR_ID` AS `SENSOR_ID`,
        `cd`.`NAME` AS `COMPANY_DETECTOR_NAME`,
        `s`.`NAME` AS `SENSOR_NAME`
    FROM
        ((`company_detector_alarms` `cda`
        JOIN `company_detector` `cd` ON ((`cda`.`COMPANY_DETECTOR_ID` = `cd`.`UID`)))
        JOIN `sensor` `s` ON ((`cda`.`SENSOR_ID` = `s`.`UID`)))
    ORDER BY `cda`.`ALARM_ID`