CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `dash_companies_alarm` AS
    SELECT 
        `pa`.`UID` AS `uid`,
        `c`.`UID` AS `company_id`,
        `c`.`NAME` AS `company_name`,
        `u`.`NAME` AS `unit_name`,
        `a`.`NAME` AS `area_name`,
        `cds`.`NAME` AS `company_detector_name`,
        `s`.`NAME` AS `sensor_name`,
        `s`.`UID` AS `sensor_id`,
        `pa`.`LAST_VALUE` AS `last_value`,
        `pa`.`LAST_UPDATE` AS `last_update`,
        `pa`.`FIRST_UPDATE` AS `first_update`,
        `pa`.`ALARM_TYPE` AS `alarm_type`,
        `pa`.`EMAIL_STATUS` AS `email_status`,
        `pa`.`SMS_STATUS` AS `sms_status`,
        `pa`.`ALARM_STATUS` AS `alarm_status`,
        `pa`.`SOUND_STATUS` AS `sound_status`,
        `pa`.`ACTION` AS `action`,
        `g`.`NAME` AS `gas_name`,
        `s`.`UNIT_METER_GASES` AS `unit_meter_gases`
    FROM
        ((((((((`company` `c`
        LEFT JOIN `unit` `u` ON ((`c`.`UID` = `u`.`COMPANY_ID`)))
        LEFT JOIN `area` `a` ON ((`u`.`UID` = `a`.`UNIT_ID`)))
        LEFT JOIN `company_device` `cd` ON ((`a`.`UID` = `cd`.`AREA_ID`)))
        LEFT JOIN `company_detector` `cds` ON ((`cd`.`UID` = `cds`.`COMPANY_DEVICE_ID`)))
        LEFT JOIN `position_alarm` `pa` ON ((`cds`.`UID` = `pa`.`COMPANY_DETECTOR_ID`)))
        LEFT JOIN `sensor` `s` ON ((`pa`.`SENSOR_ID` = `s`.`UID`)))
        LEFT JOIN `sensor_gases` `sg` ON ((`s`.`UID` = `sg`.`SENSOR_ID`)))
        LEFT JOIN `gas` `g` ON ((`sg`.`GASES_ID` = `g`.`UID`)))
    WHERE
        (`pa`.`UID` IS NOT NULL)
    ORDER BY `pa`.`LAST_UPDATE` DESC