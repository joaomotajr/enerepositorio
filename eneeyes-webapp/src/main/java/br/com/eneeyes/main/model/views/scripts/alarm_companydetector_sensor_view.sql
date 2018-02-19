/*--------------------------------------------------------
 View Para retornar Detectores que utilizam os Alarmes na
 Hora da Edição de Alarmes.
 Tela: alarm.
---------------------------------------------------------*/

/* MYSQL */

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `alarm_companydetector_sensor_view` AS
    SELECT 
        CONCAT(`cd`.`UID`, `s`.`UID`) AS `UID`,
        `a`.`UID` AS `ALARM_ID`,
        `cd`.`UID` AS `COMPANY_DETECTOR_ID`,
        `s`.`UID` AS `SENSOR_ID`,
        `cd`.`NAME` AS `COMPANY_DETECTOR_NAME`,
        `cd`.`LOCAL` AS `COMPANY_DETECTOR_LOCAL`,
        `s`.`NAME` AS `SENSOR_NAME`,
        `s`.`RANGE_MAX` AS `RANGE_MAX`
    FROM
        ((((`alarm` `a`
        JOIN `company_device` `cdv` ON ((`a`.`UID` = `cdv`.`ALARM_ID`)))
        JOIN `company_detector` `cd` ON ((`cdv`.`UID` = `cd`.`COMPANY_DEVICE_ID`)))
        JOIN `detector` `d` ON ((`cd`.`DETECTOR_ID` = `d`.`UID`)))
        JOIN `sensor` `s` ON ((`d`.`SENSOR_ID` = `s`.`UID`)))
    ORDER BY `a`.`UID`