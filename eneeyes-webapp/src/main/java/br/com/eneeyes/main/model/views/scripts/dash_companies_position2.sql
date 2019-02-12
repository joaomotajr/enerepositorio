/*-----------------------------------------------------------
 View Para retornar o Posicionamento Atual de cada dispositivo
 com as informações de origem e localização do mesmo. 
 Tela: 	dashboard
 		monitor
---------------------------------------------------------*/

/* MYSQL */
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `dash_companies_position2` AS
    SELECT 
        `pos`.`UID` AS `uid`,
        `c`.`NAME` AS `company_name`,
        `c`.`UID` AS `company_id`,
        `u`.`NAME` AS `unit_name`,
        `a`.`NAME` AS `area_name`,
        `cd`.`NAME` AS `company_device_name`,
        `cd`.`LOCAL` AS `COMPANY_DETECTOR_LOCAL`,
        `pos`.`UID` AS `position_id`,
        `pos`.`LAST_VALUE` AS `last_value`,
        `pos`.`ALARM_TYPE` AS `alarm_type`,
        `pos`.`LAST_UPDATE` AS `last_update`,
        `cdv`.`DEVICE_TYPE_ID` AS `DEVICE_TYPE_ID`,
        `g`.`NAME` AS `GAS_NAME`,
        `s`.`UNIT_METER_GASES` AS `unit_meter_gases`
    FROM
        ((((((((`company` `c`
        JOIN `unit` `u` ON ((`c`.`UID` = `u`.`COMPANY_ID`)))
        JOIN `area` `a` ON ((`u`.`UID` = `a`.`UNIT_ID`)))
        JOIN `company_device` `cdv` ON ((`a`.`UID` = `cdv`.`AREA_ID`)))
        JOIN `company_detector` `cd` ON ((`cdv`.`UID` = `cd`.`COMPANY_DEVICE_ID`)))
        JOIN `detector` `d` ON ((`d`.`UID` = `cd`.`DETECTOR_ID`)))
        LEFT JOIN `sensor` `s` ON ((`d`.`SENSOR_ID` = `s`.`UID`)))
        LEFT JOIN `gas` `g` ON ((`s`.`GAS_ID` = `g`.`UID`)))
        LEFT JOIN `position` `pos` ON ((`cdv`.`UID` = `pos`.`COMPANY_DEVICE_ID`)))
    WHERE
        (`pos`.`UID` IS NOT NULL) 
    UNION SELECT 
        `pos`.`UID` AS `uid`,
        `c`.`NAME` AS `company_name`,
        `c`.`UID` AS `company_id`,
        `u`.`NAME` AS `unit_name`,
        `a`.`NAME` AS `area_name`,
        `cg`.`NAME` AS `company_device_name`,
        `cg`.`LOCAL` AS `COMPANY_DETECTOR_LOCAL`,
        `pos`.`UID` AS `position_id`,
        `pos`.`LAST_VALUE` AS `last_value`,
        `pos`.`ALARM_TYPE` AS `alarm_type`,
        `pos`.`LAST_UPDATE` AS `last_update`,
        `cdv`.`DEVICE_TYPE_ID` AS `DEVICE_TYPE_ID`,
        NULL AS `GAS_NAME`,
        `g`.`UNIT_METER_GASES` AS `UNIT_METER_GASES`
    FROM
        ((((((`company` `c`
        JOIN `unit` `u` ON ((`c`.`UID` = `u`.`COMPANY_ID`)))
        JOIN `area` `a` ON ((`u`.`UID` = `a`.`UNIT_ID`)))
        JOIN `company_device` `cdv` ON ((`a`.`UID` = `cdv`.`AREA_ID`)))
        JOIN `company_generic` `cg` ON ((`cdv`.`UID` = `cg`.`COMPANY_DEVICE_ID`)))
        JOIN `generic` `g` ON ((`g`.`UID` = `cg`.`GENERIC_ID`)))
        LEFT JOIN `position` `pos` ON ((`cdv`.`UID` = `pos`.`COMPANY_DEVICE_ID`)))
    WHERE
        (`pos`.`UID` IS NOT NULL)