/*--------------------------------------------------------
 View Para retornar os Eventos de Alarme (PositionAlarm)
 com as informações de origem do detector em questão
 bem como ações e status 
 Tela: 	logAlarm
 		monitor
---------------------------------------------------------*/

/* MYSQL */

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `dash_companies_alarm` AS
    SELECT 
        `pa`.`UID` AS `uid`,
        `c`.`NAME` AS `company_name`,
        `c`.`UID` AS `company_id`,
        `u`.`NAME` AS `unit_name`,
        `a`.`NAME` AS `area_name`,
        `cd`.`NAME` AS `company_detector_name`,
        `cd`.`LOCAL` AS `COMPANY_DETECTOR_LOCAL`,
        `pa`.`LAST_VALUE` AS `last_value`,
        `pa`.`LAST_UPDATE` AS `last_update`,
        `pa`.`FIRST_UPDATE` AS `first_update`,
        `pa`.`ALARM_TYPE` AS `alarm_type`,
        `pa`.`SIGMA_STATUS` AS `sigma_status`,
        `pa`.`EMAIL_STATUS` AS `email_status`,
        `pa`.`Sms_STATUS` AS `sms_status`,
        `pa`.`ALARM_STATUS` AS `alarm_status`,
        `pa`.`SOUND_STATUS` AS `sound_status`,
        `pa`.`ACTION` AS `action`,
        `g`.`NAME` AS `GAS_NAME`,
        `s`.`UNIT_METER_ID` AS `UNIT_METER_ID`,
        `cdv`.`DEVICE_TYPE_ID` AS `DEVICE_TYPE_ID`
    FROM
        ((((((((`company` `c`
        JOIN `unit` `u` ON ((`c`.`UID` = `u`.`COMPANY_ID`)))
        JOIN `area` `a` ON ((`u`.`UID` = `a`.`UNIT_ID`)))
        JOIN `company_device` `cdv` ON ((`a`.`UID` = `cdv`.`AREA_ID`)))
        JOIN `company_detector` `cd` ON ((`cdv`.`UID` = `cd`.`COMPANY_DEVICE_ID`)))
        JOIN `detector` `d` ON ((`d`.`UID` = `cd`.`DETECTOR_ID`)))
        LEFT JOIN `sensor` `s` ON ((`d`.`SENSOR_ID` = `s`.`UID`)))
        LEFT JOIN `gas` `g` ON ((`s`.`GAS_ID` = `g`.`UID`)))
        LEFT JOIN `position_alarm` `pa` ON ((`cdv`.`UID` = `pa`.`COMPANY_DEVICE_ID`)))
    WHERE
        (`pa`.`UID` IS NOT NULL) 
    UNION SELECT 
        `pa`.`UID` AS `uid`,
        `c`.`NAME` AS `company_name`,
        `c`.`UID` AS `company_id`,
        `u`.`NAME` AS `unit_name`,
        `a`.`NAME` AS `area_name`,
        `cg`.`NAME` AS `company_detector_name`,
        `cg`.`LOCAL` AS `COMPANY_DETECTOR_LOCAL`,
        `pa`.`LAST_VALUE` AS `last_value`,
        `pa`.`LAST_UPDATE` AS `last_update`,
        `pa`.`FIRST_UPDATE` AS `first_update`,
        `pa`.`ALARM_TYPE` AS `alarm_type`,
        `pa`.`SIGMA_STATUS` AS `sigma_status`,
        `pa`.`EMAIL_STATUS` AS `email_status`,
        `pa`.`Sms_STATUS` AS `sms_status`,
        `pa`.`ALARM_STATUS` AS `alarm_status`,
        `pa`.`SOUND_STATUS` AS `sound_status`,
        `pa`.`ACTION` AS `action`,
        NULL AS `GAS_NAME`,
        `g`.`UNIT_METER_ID` AS `UNIT_METER_id`,
        `cdv`.`DEVICE_TYPE_ID` AS `DEVICE_TYPE_ID`
    FROM
        ((((((`company` `c`
        JOIN `unit` `u` ON ((`c`.`UID` = `u`.`COMPANY_ID`)))
        JOIN `area` `a` ON ((`u`.`UID` = `a`.`UNIT_ID`)))
        JOIN `company_device` `cdv` ON ((`a`.`UID` = `cdv`.`AREA_ID`)))
        JOIN `company_generic` `cg` ON ((`cdv`.`UID` = `cg`.`COMPANY_DEVICE_ID`)))
        JOIN `generic` `g` ON ((`g`.`UID` = `cg`.`GENERIC_ID`)))
        LEFT JOIN `position_alarm` `pa` ON ((`cdv`.`UID` = `pa`.`COMPANY_DEVICE_ID`)))
    WHERE
        (`pa`.`UID` IS NOT NULL)