CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `dash_companies_position` AS
    SELECT 
        `pos`.`UID` AS `uid`,
        `c`.`NAME` AS `company_name`,
        `u`.`NAME` AS `unit_name`,
        `a`.`NAME` AS `area_name`,
        `cds`.`NAME` AS `company_detector_name`,
        `s`.`NAME` AS `sensor_name`,
        `s`.`UID` AS `sensor_id`,
        `pos`.`LAST_VALUE` AS `last_value`,
        `pos`.`ALARM_TYPE` AS `alarm_type`,
        `pos`.`LAST_UPDATE` AS `last_update`,
        `g`.`NAME` AS `gas_name`,
        `s`.`UNIT_METER_GASES` AS `unit_meter_gases`
    FROM
        ((((((((`company` `c`
        LEFT JOIN `unit` `u` ON ((`c`.`UID` = `u`.`COMPANY_ID`)))
        LEFT JOIN `area` `a` ON ((`u`.`UID` = `a`.`UNIT_ID`)))
        LEFT JOIN `company_device` `cd` ON ((`a`.`UID` = `cd`.`AREA_ID`)))
        LEFT JOIN `company_detector` `cds` ON ((`cd`.`UID` = `cds`.`COMPANY_DEVICE_ID`)))
        LEFT JOIN `position` `pos` ON ((`cds`.`UID` = `pos`.`COMPANY_DETECTOR_ID`)))
        LEFT JOIN `sensor` `s` ON ((`pos`.`SENSOR_ID` = `s`.`UID`)))
        LEFT JOIN `sensor_gases` `sg` ON ((`s`.`UID` = `sg`.`SENSOR_ID`)))
        LEFT JOIN `gas` `g` ON ((`sg`.`GASES_ID` = `g`.`UID`)))
    WHERE
        (`pos`.`UID` IS NOT NULL)
    ORDER BY `pos`.`LAST_UPDATE` DESC