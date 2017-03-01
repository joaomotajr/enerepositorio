CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `dash_companies_alarms` AS
    SELECT 
        `c`.`UID` AS `company_id`,
        `c`.`NAME` AS `company`,
        `u`.`NAME` AS `units`,
        `a`.`NAME` AS `area`,
        `cd`.`DEVICE_TYPE` AS `device_type`,
        `cds`.`NAME` AS `companyDetectorName`,
        `cds`.`UID` AS `companyDetector_id`,
        `cds`.`DETECTOR_ID` AS `detector_id`,
        `pa`.`UID` AS `position_alarm_id`,
        `pa`.`SENSOR_ID` AS `position_alarm_sensor_id`
    FROM
        (((((`company` `c`
        LEFT JOIN `unit` `u` ON ((`c`.`UID` = `u`.`COMPANY_ID`)))
        LEFT JOIN `area` `a` ON ((`u`.`UID` = `a`.`UNIT_ID`)))
        LEFT JOIN `company_device` `cd` ON ((`a`.`UID` = `cd`.`AREA_ID`)))
        LEFT JOIN `company_detector` `cds` ON ((`cd`.`UID` = `cds`.`COMPANY_DEVICE_ID`)))
        LEFT JOIN `position_alarm` `pa` ON ((`cds`.`UID` = `pa`.`COMPANY_DETECTOR_ID`)))
    ORDER BY `c`.`NAME` , `u`.`NAME` , `a`.`NAME`