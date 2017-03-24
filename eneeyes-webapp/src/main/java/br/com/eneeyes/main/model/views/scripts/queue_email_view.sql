CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `queue_email_view` AS
    SELECT 
        `pa`.`UID` AS `UID`,
        `pa`.`EMAIL_STATUS` AS `EMAIL_STATUS`,
        `a`.`NAME` AS `NAME`,
        `a`.`EMAIL` AS `EMAIL`,
        `cda`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,
        `cda`.`SENSOR_ID` AS `SENSOR_ID`
    FROM
        ((`position_alarm` `pa`
        JOIN `company_detector_alarms` `cda` ON (((`pa`.`COMPANY_DETECTOR_ID` = `cda`.`COMPANY_DETECTOR_ID`)
            AND (`pa`.`SENSOR_ID` = `cda`.`SENSOR_ID`))))
        JOIN `alarm` `a` ON ((`cda`.`ALARM_ID` = `a`.`UID`)))
    WHERE
        (`pa`.`EMAIL_STATUS` = 2)