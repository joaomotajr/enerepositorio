CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `area_companydevice_alarm_view` AS
    SELECT 
        `p`.`UID` AS `UID`,
        `cdv`.`AREA_ID` AS `AREA_ID`,
        `cdv`.`UID` AS `COMPANY_DEvice_ID`,
        `cd`.`UID` AS `COMPANY_DETECTOR_ID`,
        `cd`.`NAME` AS `COMPANY_DETECTOR_NAME`,
        `cd`.`LOCAL` AS `COMPANY_DETECTOR_LOCAL`,
        `cdv`.`DEVICE_TYPE_ID` AS `device_type_id`,
        `s`.`NAME` AS `SENSOR_NAME`,
        `s`.`RANGE_MIN` AS `RANGE_MIN`,
        `s`.`RANGE_MAX` AS `RANGE_MAX`,
        `s`.`UNIT_METER_GASES` AS `UNIT_METER_GASES`,
        `p`.`LAST_VALUE` AS `LAST_VALUE`,
        `p`.`LAST_UPDATE` AS `LAST_UPDATE`,
        `p`.`ALARM_TYPE` AS `ALARM_TYPE`,
        `a`.`UID` AS `ALARM_ID`,
        `a`.`NAME` AS `ALARM_NAME`,
        `a`.`ALARM_ON` AS `ALARM_ON`,
        `a`.`ALARM_1` AS `ALARM_1`,
        `a`.`ALARM_11` AS `ALARM_11`,
        `a`.`ALARM_2` AS `ALARM_2`,
        `a`.`ALARM_3` AS `ALARM_3`,
        `g`.`NAME` AS `GAS_NAME`
    FROM
        ((((((`company_device` `cdv`
        JOIN `company_detector` `cd` ON ((`cdv`.`UID` = `cd`.`COMPANY_DEVICE_ID`)))
        JOIN `detector` `d` ON ((`cd`.`DETECTOR_ID` = `d`.`UID`)))
        JOIN `sensor` `s` ON ((`d`.`SENSOR_ID` = `s`.`UID`)))
        JOIN `gas` `g` ON ((`s`.`GAS_ID` = `g`.`UID`)))
        LEFT JOIN `alarm` `a` ON ((`cdv`.`ALARM_ID` = `a`.`UID`)))
        LEFT JOIN `position` `p` ON ((`p`.`COMPANY_DEVICE_ID` = `cdv`.`UID`))) 
    UNION SELECT 
        `p`.`UID` AS `UID`,
        `cdv`.`AREA_ID` AS `AREA_ID`,
        `cdv`.`UID` AS `COMPANY_DEvice_ID`,
        `cg`.`UID` AS `COMPANY_DETECTOR_ID`,
        `cg`.`NAME` AS `COMPANY_DETECTOR_NAME`,
        `cg`.`LOCAL` AS `COMPANY_DETECTOR_LOCAL`,
        `cdv`.`DEVICE_TYPE_ID` AS `device_type_id`,
        'GENERIC' AS `SENSOR_NAME`,
        `g`.`RANGE_MIN` AS `RANGE_MIN`,
        `g`.`RANGE_MAX` AS `RANGE_MAX`,
        `g`.`UNIT_METER_GASES` AS `UNIT_METER_GASES`,
        `p`.`LAST_VALUE` AS `LAST_VALUE`,
        `p`.`LAST_UPDATE` AS `LAST_UPDATE`,
        `p`.`ALARM_TYPE` AS `ALARM_TYPE`,
        `a`.`UID` AS `ALARM_ID`,
        `a`.`NAME` AS `ALARM_NAME`,
        `a`.`ALARM_ON` AS `ALARM_ON`,
        `a`.`ALARM_1` AS `ALARM_1`,
        `a`.`ALARM_11` AS `ALARM_11`,
        `a`.`ALARM_2` AS `ALARM_2`,
        `a`.`ALARM_3` AS `ALARM_3`,
        NULL AS `GAS_NAME`
    FROM
        ((((`company_device` `cdv`
        JOIN `company_generic` `cg` ON ((`cdv`.`UID` = `cg`.`COMPANY_DEVICE_ID`)))
        JOIN `generic` `g` ON ((`cg`.`GENERIC_ID` = `g`.`UID`)))
        LEFT JOIN `alarm` `a` ON ((`cdv`.`ALARM_ID` = `a`.`UID`)))
        LEFT JOIN `position` `p` ON ((`p`.`COMPANY_DEVICE_ID` = `cdv`.`UID`)))