/* MYSQL */
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `area_companydevice_alarm_view` AS
    SELECT 
        GETFAKEID() AS `UID`,
        `cdv`.`AREA_ID` AS `AREA_ID`,
        `cdv`.`UID` AS `COMPANY_DEvice_ID`,
        `p`.`LAST_VALUE` AS `LAST_VALUE`,
        `p`.`LAST_UPDATE` AS `LAST_UPDATE`,
        `p`.`ALARM_TYPE` AS `ALARM_TYPE`,
        `a`.`UID` AS `ALARM_ID`,
        `a`.`NAME` AS `ALARM_NAME`,
        `a`.`ALARM_ON` AS `ALARM_ON`,
        `a`.`ALARM_1` AS `ALARM_1`,
        `a`.`ALARM_2` AS `ALARM_2`,
        `a`.`ALARM_3` AS `ALARM_3`
    FROM
        ((`company_device` `cdv`
        LEFT JOIN `alarm` `a` ON ((`cdv`.`ALARM_ID` = `a`.`UID`)))
        LEFT JOIN `position` `p` ON ((`p`.`COMPANY_DEVICE_ID` = `cdv`.`UID`)))
    ORDER BY `cdv`.`AREA_ID`