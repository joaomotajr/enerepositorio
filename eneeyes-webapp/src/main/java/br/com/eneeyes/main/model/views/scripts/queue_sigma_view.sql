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
        `s`.`UID` AS `SENSOR_ID`
    FROM
        (((((`position_alarm` `pa`
        JOIN `company_detector` `cd` ON ((`cd`.`UID` = `pa`.`COMPANY_DETECTOR_ID`)))
        JOIN `detector` `d` ON ((`cd`.`DETECTOR_ID` = `d`.`UID`)))
        JOIN `sensor` `s` ON ((`d`.`SENSOR_ID` = `s`.`UID`)))
        JOIN `gas` `g` ON ((`s`.`GAS_ID` = `g`.`UID`)))
        JOIN `alarm` `a` ON ((`cd`.`ALARM_ID` = `a`.`UID`)))
    WHERE
        (`pa`.`SIGMA_STATUS` = 1)