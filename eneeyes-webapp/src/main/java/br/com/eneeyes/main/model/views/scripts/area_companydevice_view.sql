CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `area_companydevice_view` AS
    SELECT 
        CONCAT(`cd`.`UID`, `s`.`UID`) AS `UID`,
        `cdv`.`AREA_ID` AS `area_id`,
        `cdv`.`UID` AS `COMPANY_DEVICE_ID`,
        `cd`.`NAME` AS `COMPANY_DEVICE_NAME`,
        `cd`.`LOCAL` AS `COMPANY_DEVICE_LOCAL`,
        `cd`.`LATITUDE` AS `latitude`,
        `cd`.`LONGITUDE` AS `longitude`,
        `s`.`NAME` AS `DEVICE_NAME`,
        `s`.`RANGE_MAX` AS `RANGE_MAX`,
        `s`.`RANGE_MIN` AS `RANGE_MIN`
    FROM
        (((`company_device` `cdv`
        LEFT JOIN `company_detector` `cd` ON ((`cdv`.`UID` = `cd`.`COMPANY_DEVICE_ID`)))
        LEFT JOIN `detector` `d` ON ((`cd`.`DETECTOR_ID` = `d`.`UID`)))
        LEFT JOIN `sensor` `s` ON ((`d`.`SENSOR_ID` = `s`.`UID`))) 
    UNION SELECT 
        CONCAT(`cg`.`UID`, `g`.`UID`) AS `UID`,
        `cdv`.`AREA_ID` AS `area_id`,
        `cdv`.`UID` AS `COMPANY_DEVICE_ID`,
        `cg`.`NAME` AS `COMPANY_DEVICE_NAME`,
        `cg`.`LOCAL` AS `COMPANY_DEVICE_LOCAL`,
        `cg`.`LATITUDE` AS `latitude`,
        `cg`.`LONGITUDE` AS `longitude`,
        `g`.`NAME` AS `DEVICE_NAME`,
        `g`.`RANGE_MAX` AS `RANGE_MAX`,
        `g`.`RANGE_MIN` AS `RANGE_MIN`
    FROM
        ((`company_device` `cdv`
        LEFT JOIN `company_generic` `cg` ON ((`cdv`.`UID` = `cg`.`COMPANY_DEVICE_ID`)))
        LEFT JOIN `generic` `g` ON ((`cg`.`GENERIC_ID` = `g`.`UID`)))
    ORDER BY `UID`