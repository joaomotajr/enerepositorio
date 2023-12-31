CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `companydevice_alarm_view` AS
    SELECT 
        `cdv`.`UID` AS `UID`,
        `a`.`UID` AS `alarm_id`,
        `a`.`ALARM_1` AS `ALARM_1`,
        `a`.`ALARM_2` AS `ALARM_2`,
        `a`.`ALARM_3` AS `ALARM_3`,
        `a`.`ALARM_OFF` AS `ALARM_OFF`,
        `a`.`NAME` AS `NAME`,
        `a`.`GAS_ID` AS `GAS_ID`,
        `a`.`ACTION1` AS `ACTION1`,
        `a`.`ACTION2` AS `ACTION2`,
        `a`.`ACTION3` AS `ACTION3`,
        `a`.`ALARM_ACTION` AS `ALARM_ACTION`,
        `a`.`ALARM_EMAIL` AS `ALARM_EMAIL`,
        `a`.`ALARM_ON` AS `ALARM_ON`,
        `a`.`ALARM_SMS` AS `ALARM_SMS`,
        `a`.`CELULAR` AS `CELULAR`,
        `a`.`EMAIL` AS `EMAIL`,
        `a`.`COMPANY_ID` AS `COMPANY_ID`,
        `a`.`ALARM_SOUND` AS `ALARM_SOUND`,
        `a`.`ALARM_SIGMA` AS `ALARM_SIGMA`,
        `a`.`CELULAR1` AS `CELULAR1`,
        `a`.`EMAIL1` AS `EMAIL1`,
        `a`.`ACTION4` AS `ACTION4`,
        `a`.`ALARM_11` AS `ALARM_11`,
        `a`.`ALARM_22` AS `ALARM_22`,
        `a`.`ALARM_33` AS `ALARM_33`,
        `dt`.`TYPE` AS `DEVICE_TYPE`,
        `a`.`ALARM2_ON` AS `ALARM2_ON`,
        `a`.`ALARM3_ON` AS `ALARM3_ON`
    FROM
        ((`company_device` `cdv`
        JOIN `alarm` `a` ON ((`cdv`.`ALARM_ID` = `a`.`UID`)))
        JOIN `device_type` `dt` ON ((`a`.`DEVICE_TYPE_ID` = `dt`.`UID`)))
    ORDER BY `cdv`.`UID`