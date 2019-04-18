/*--------------------------------------------------------
 View Para retornar SMSs em Status pendentes para envio.
 Uso em Serviços de envio de sms: processSmsService 
---------------------------------------------------------*/

/* MYSQL */
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `queue_sigma_view` AS
    SELECT 
        `pa`.`UID` AS `UID`,
        `pa`.`SIGMA_STATUS` AS `SIGMA_STATUS`,
        `a`.`NAME` AS `ALARM_NAME`,
        `pa`.`LAST_UPDATE` AS `LAST_UPDATE`,
        `pa`.`LAST_VALUE` AS `LAST_VALUE`,
        `pa`.`ALARM_TYPE` AS `ALARM_TYPE`,
        `a`.`NAME` AS `NAME`,
        `a`.`CELULAR` AS `CELULAR`,
        `cd`.`UID` AS `COMPANY_DETECTOR_ID`,
        `cd`.`NAME` AS `COMPANY_DETECTOR_NAME`,
        `cd`.`LOCAL` AS `COMPANY_DETECTOR_LOCAL`
    FROM
        (((((`position_alarm` `pa`
        JOIN `company_device` `cdv` ON ((`cdv`.`UID` = `pa`.`COMPANY_DEVICE_ID`)))
        JOIN `company_detector` `cd` ON ((`cd`.`COMPANY_DEVICE_ID` = `cdv`.`UID`)))
        JOIN `detector` `d` ON ((`cd`.`DETECTOR_ID` = `d`.`UID`)))
        JOIN `sensor` `s` ON ((`d`.`SENSOR_ID` = `s`.`UID`)))
        JOIN `alarm` `a` ON ((`cdv`.`ALARM_ID` = `a`.`UID`)))
    WHERE
        (`pa`.`SIGMA_STATUS` = 1) 
    UNION SELECT 
        `pa`.`UID` AS `UID`,
        `pa`.`SIGMA_STATUS` AS `SIGMA_STATUS`,
        `a`.`NAME` AS `ALARM_NAME`,
        `pa`.`LAST_UPDATE` AS `LAST_UPDATE`,
        `pa`.`LAST_VALUE` AS `LAST_VALUE`,
        `pa`.`ALARM_TYPE` AS `ALARM_TYPE`,
        `a`.`NAME` AS `NAME`,
        `a`.`CELULAR` AS `CELULAR`,
        `cg`.`UID` AS `COMPANY_DETECTOR_ID`,
        `cg`.`NAME` AS `COMPANY_DETECTOR_NAME`,
        `cg`.`LOCAL` AS `COMPANY_DETECTOR_LOCAL`
    FROM
        ((((`position_alarm` `pa`
        JOIN `company_device` `cdv` ON ((`cdv`.`UID` = `pa`.`COMPANY_DEVICE_ID`)))
        JOIN `company_generic` `cg` ON ((`cg`.`COMPANY_DEVICE_ID` = `cdv`.`UID`)))
        JOIN `generic` `g` ON ((`cg`.`GENERIC_ID` = `g`.`UID`)))
        JOIN `alarm` `a` ON ((`cdv`.`ALARM_ID` = `a`.`UID`)))
    WHERE
        (`pa`.`SIGMA_STATUS` = 1)