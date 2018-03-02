
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `alarm_companydevice_view` AS
    SELECT 
        CONCAT(`cd`.`UID`, `s`.`UID`) AS `UID`,
        `a`.`UID` AS `ALARM_ID`,
        `cdv`.`UID` AS `COMPANY_DEVICE_ID`,
        `cd`.`NAME` AS `COMPANY_DEVICE_NAME`,
        `cd`.`LOCAL` AS `COMPANY_DEVICE_LOCAL`,
        `s`.`NAME` AS `DEVICE_NAME`,
        `s`.`RANGE_MAX` AS `RANGE_MAX`
    FROM
        ((((`alarm` `a`
        JOIN `company_device` `cdv` ON ((`a`.`UID` = `cdv`.`ALARM_ID`)))
        JOIN `company_detector` `cd` ON ((`cdv`.`UID` = `cd`.`COMPANY_DEVICE_ID`)))
        JOIN `detector` `d` ON ((`cd`.`DETECTOR_ID` = `d`.`UID`)))
        JOIN `sensor` `s` ON ((`d`.`SENSOR_ID` = `s`.`UID`))) 
    UNION SELECT 
        CONCAT(`cg`.`UID`, `g`.`UID`) AS `UID`,
        `a`.`UID` AS `ALARM_ID`,
        `cdv`.`UID` AS `COMPANY_DEVICE_ID`,
        `cg`.`NAME` AS `COMPANY_DEVICE_NAME`,
        `cg`.`LOCAL` AS `COMPANY_DEVICE_LOCAL`,
        `g`.`NAME` AS `DEVICE_NAME`,
        `g`.`RANGE_MAX` AS `RANGE_MAX`
    FROM
        (((`alarm` `a`
        JOIN `company_device` `cdv` ON ((`a`.`UID` = `cdv`.`ALARM_ID`)))
        JOIN `company_generic` `cg` ON ((`cdv`.`UID` = `cg`.`COMPANY_DEVICE_ID`)))
        JOIN `generic` `g` ON ((`cg`.`GENERIC_ID` = `g`.`UID`)))
    ORDER BY `UID`