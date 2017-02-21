CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `dash_companies_position` AS
    SELECT 
        `pos`.`UID` AS `UID`,
        `c`.`NAME` AS `company_name`,
        `u`.`NAME` AS `unit_name`,
        `a`.`NAME` AS `area_name`,
        `cds`.`NAME` AS `company_detector_name`,
        `s`.`NAME` AS `sensor_name`,
        `s`.`UID` AS `sensor_id`,
        `pos`.`LAST_UPDATE` AS `ALARM_TYPE`,
        `pos`.`LAST_UPDATE` AS `LAST_UPDATE`
    FROM
        ((((((`company` `c`
        LEFT JOIN `unit` `u` ON ((`c`.`UID` = `u`.`COMPANY_ID`)))
        LEFT JOIN `area` `a` ON ((`u`.`UID` = `a`.`UNIT_ID`)))
        LEFT JOIN `company_device` `cd` ON ((`a`.`UID` = `cd`.`AREA_ID`)))
        LEFT JOIN `company_detector` `cds` ON ((`cd`.`UID` = `cds`.`COMPANY_DEVICE_ID`)))
        LEFT JOIN `position` `pos` ON ((`cds`.`UID` = `pos`.`COMPANY_DETECTOR_ID`)))
        LEFT JOIN `sensor` `s` ON ((`pos`.`SENSOR_ID` = `s`.`UID`)))
    WHERE
        (`pos`.`UID` IS NOT NULL)
    ORDER BY `pos`.`LAST_UPDATE` DESC