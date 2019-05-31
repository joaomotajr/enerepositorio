/*--------------------------------------------------------
 View Para retornar SMSs em Status pendentes para envio.
 Uso em Serviços de envio de sms: processSmsService 
---------------------------------------------------------*/

/* MYSQL */

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `queue_sms_view` AS
    SELECT 
        `pa`.`UID` AS `UID`,
        `pa`.`Sms_STATUS` AS `SMS_STATUS`,
        `a`.`NAME` AS `ALARM_NAME`,
        `pa`.`LAST_UPDATE` AS `LAST_UPDATE`,
        `pa`.`LAST_VALUE` AS `LAST_VALUE`,
        `pa`.`ALARM_TYPE` AS `ALARM_TYPE`,
        `a`.`NAME` AS `NAME`,
        `a`.`CELULAR` AS `CELULAR`,
        `a`.`CELULAR1` AS `CELULAR1`,
        `cd`.`UID` AS `COMPANY_DETECTOR_ID`,
        `cd`.`NAME` AS `COMPANY_DETECTOR_NAME`,
        `cd`.`LOCAL` AS `COMPANY_DETECTOR_LOCAL`,
        `g`.`NAME` AS `GAS_NAME`,
        `c`.`NAME` AS `company_name`,
        `u`.`NAME` AS `unit_name`,
        `area`.`NAME` AS `area_name`,
        `area`.`LOCAL` AS `area_local`,
        `um`.`DESCRIPTION` AS `unit_meter_description`,
        `um`.`SYMBOL` AS `unit_meter_symbol`,
        `dt`.`TYPE` AS `device_type`,
        `dt`.`SYMBOL` AS `device_symbol`
    FROM
        (((((((((((`position_alarm` `pa`
        JOIN `company_device` `cdv` ON ((`cdv`.`UID` = `pa`.`COMPANY_DEVICE_ID`)))
        JOIN `device_type` `dt` ON ((`cdv`.`DEVICE_TYPE_ID` = `dt`.`UID`)))
        JOIN `area` ON ((`cdv`.`AREA_ID` = `area`.`UID`)))
        JOIN `unit` `u` ON ((`area`.`UNIT_ID` = `u`.`UID`)))
        JOIN `company` `c` ON ((`u`.`COMPANY_ID` = `c`.`UID`)))
        JOIN `company_detector` `cd` ON ((`cd`.`COMPANY_DEVICE_ID` = `cdv`.`UID`)))
        JOIN `detector` `d` ON ((`cd`.`DETECTOR_ID` = `d`.`UID`)))
        JOIN `sensor` `s` ON ((`d`.`SENSOR_ID` = `s`.`UID`)))
        JOIN `unit_meter` `um` ON ((`s`.`UNIT_METER_ID` = `um`.`UID`)))
        JOIN `gas` `g` ON ((`s`.`GAS_ID` = `g`.`UID`)))
        JOIN `alarm` `a` ON ((`cdv`.`ALARM_ID` = `a`.`UID`)))
    WHERE
        ((`pa`.`Sms_STATUS` = 1)
            OR (`pa`.`Sms_STATUS` = 3)) 
    UNION SELECT 
        `pa`.`UID` AS `UID`,
        `pa`.`Sms_STATUS` AS `SMS_STATUS`,
        `a`.`NAME` AS `ALARM_NAME`,
        `pa`.`LAST_UPDATE` AS `LAST_UPDATE`,
        `pa`.`LAST_VALUE` AS `LAST_VALUE`,
        `pa`.`ALARM_TYPE` AS `ALARM_TYPE`,
        `a`.`NAME` AS `NAME`,
        `a`.`CELULAR` AS `CELULAR`,
        `a`.`CELULAR1` AS `CELULAR1`,
        `cg`.`UID` AS `companyDetector_id`,
        `cg`.`NAME` AS `companyDetector_name`,
        `cg`.`LOCAL` AS `companyDetector_local`,
        'NONE' AS `GAS_NAME`,
        `c`.`NAME` AS `company_name`,
        `u`.`NAME` AS `unit_name`,
        `area`.`NAME` AS `area_name`,
        `area`.`LOCAL` AS `area_local`,
        `um`.`DESCRIPTION` AS `unit_meter_description`,
        `um`.`SYMBOL` AS `unit_meter_symbol`,
        `dt`.`TYPE` AS `device_type`,
        `dt`.`SYMBOL` AS `device_symbol`
    FROM
        (((((((((`position_alarm` `pa`
        JOIN `company_device` `cdv` ON ((`cdv`.`UID` = `pa`.`COMPANY_DEVICE_ID`)))
        JOIN `device_type` `dt` ON ((`cdv`.`DEVICE_TYPE_ID` = `dt`.`UID`)))
        JOIN `area` ON ((`cdv`.`AREA_ID` = `area`.`UID`)))
        JOIN `unit` `u` ON ((`area`.`UNIT_ID` = `u`.`UID`)))
        JOIN `company` `c` ON ((`u`.`COMPANY_ID` = `c`.`UID`)))
        JOIN `company_generic` `cg` ON ((`cg`.`COMPANY_DEVICE_ID` = `cdv`.`UID`)))
        JOIN `generic` `g` ON ((`g`.`UID` = `cg`.`GENERIC_ID`)))
        JOIN `unit_meter` `um` ON ((`g`.`UNIT_METER_ID` = `um`.`UID`)))
        JOIN `alarm` `a` ON ((`cdv`.`ALARM_ID` = `a`.`UID`)))
    WHERE
        ((`pa`.`Sms_STATUS` = 1)
            OR (`pa`.`Sms_STATUS` = 3))