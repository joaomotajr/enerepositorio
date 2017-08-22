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
        CONCAT(`cda`.`COMPANY_DETECTOR_ID`,
                `cda`.`SENSOR_ID`) AS `UID`,
        `cda`.`ALARM_ID` AS `ALARM_ID`,
        `cda`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,
        `cda`.`SENSOR_ID` AS `SENSOR_ID`,
        `cd`.`NAME` AS `COMPANY_DETECTOR_NAME`,
        `cd`.`LOCAL` AS `COMPANY_DETECTOR_LOCAL`,
        `s`.`NAME` AS `SENSOR_NAME`,
        `s`.`RANGE_MAX` AS `RANGE_MAX`
    FROM
        ((`company_detector_alarms` `cda`
        JOIN `company_detector` `cd` ON ((`cda`.`COMPANY_DETECTOR_ID` = `cd`.`UID`)))
        JOIN `sensor` `s` ON ((`cda`.`SENSOR_ID` = `s`.`UID`)))
    ORDER BY `cda`.`ALARM_ID`

/* POSTGRES */
    
CREATE 
VIEW alarm_companydetector_sensor_view AS
    SELECT 
        CONCAT(cda.COMPANY_DETECTOR_ID,
                cda.SENSOR_ID) AS UID,
        cda.ALARM_ID AS ALARM_ID,
        cda.COMPANY_DETECTOR_ID AS COMPANY_DETECTOR_ID,
        cda.SENSOR_ID AS SENSOR_ID,
        cd.NAME AS COMPANY_DETECTOR_NAME,
        cd.LOCAL AS COMPANY_DETECTOR_LOCAL,
        s.NAME AS SENSOR_NAME,
        s.RANGE_MAX AS RANGE_MAX
    FROM
        company_detector_alarms cda
        JOIN company_detector cd ON (cda.COMPANY_DETECTOR_ID = cd.UID)
        JOIN sensor s ON (cda.SENSOR_ID = s.UID)
    ORDER BY cda.ALARM_ID
    