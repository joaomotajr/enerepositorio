/*--------------------------------------------------------
 View Para retornar Dados de Empresas, Unidade e Áreas
 com os respectivos Detectores 
 Tela: 	logHistoric
 		simulador
---------------------------------------------------------*/

/* MYSQL */

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `dash_companies2` AS
    SELECT 
        `c`.`UID` AS `company_id`,
        `cdv`.`UID` AS `company_device_id`,
        `c`.`NAME` AS `company`,
        `u`.`NAME` AS `unit`,
        `u`.`UID` AS `unit_id`,
        `a`.`NAME` AS `area`,
        `a`.`UID` AS `area_id`,
        `s`.`NAME` AS `sensor_name`,
        `d`.`NAME` AS `detector_name`,
        `s`.`RANGE_MAX` AS `RANGE_MAX`,
        `s`.`UNIT_METER_GASES` AS `UNIT_METER_GASES`,
        `dt`.`TYPE` AS `device`,
        `dt`.`UID` AS `device_type`,
        `cd`.`NAME` AS `companyDetector_name`,
        `cd`.`LOCAL` AS `companyDetector_local`,
        `cd`.`UID` AS `companyDetector_id`,
        `d`.`UID` AS `detector_id`
    FROM
        (((((((`company` `c`
        LEFT JOIN `unit` `u` ON ((`c`.`UID` = `u`.`COMPANY_ID`)))
        LEFT JOIN `area` `a` ON ((`u`.`UID` = `a`.`UNIT_ID`)))
        LEFT JOIN `company_device` `cdv` ON ((`a`.`UID` = `cdv`.`AREA_ID`)))
        JOIN `device_type` `dt` ON ((`cdv`.`DEVICE_TYPE_ID` = `dt`.`UID`)))
        LEFT JOIN `company_detector` `cd` ON ((`cdv`.`UID` = `cd`.`COMPANY_DEVICE_ID`)))
        JOIN `detector` `d` ON ((`d`.`UID` = `cd`.`DETECTOR_ID`)))
        JOIN `sensor` `s` ON ((`s`.`UID` = `d`.`SENSOR_ID`))) 
    UNION SELECT 
        `c`.`UID` AS `company_id`,
        `cdv`.`UID` AS `company_device_id`,
        `c`.`NAME` AS `company`,
        `u`.`NAME` AS `unit`,
        `u`.`UID` AS `unit_id`,
        `a`.`NAME` AS `area`,
        `a`.`UID` AS `area_id`,
        'GENERIC' AS `sensor_name`,
        `g`.`NAME` AS `detector_name`,
        `g`.`RANGE_MAX` AS `RANGE_MAX`,
        `g`.`UNIT_METER_GASES` AS `UNIT_METER_GASES`,
        `dt`.`TYPE` AS `device`,
        `dt`.`UID` AS `device_type`,
        `cg`.`NAME` AS `companyDetector_name`,
        `cg`.`LOCAL` AS `companyDetector_local`,
        `cg`.`UID` AS `companyDetector_id`,
        `g`.`UID` AS `detector_id`
    FROM
        ((((((`company` `c`
        LEFT JOIN `unit` `u` ON ((`c`.`UID` = `u`.`COMPANY_ID`)))
        LEFT JOIN `area` `a` ON ((`u`.`UID` = `a`.`UNIT_ID`)))
        LEFT JOIN `company_device` `cdv` ON ((`a`.`UID` = `cdv`.`AREA_ID`)))
        JOIN `device_type` `dt` ON ((`cdv`.`DEVICE_TYPE_ID` = `dt`.`UID`)))
        LEFT JOIN `company_generic` `cg` ON ((`cdv`.`UID` = `cg`.`COMPANY_DEVICE_ID`)))
        JOIN `generic` `g` ON ((`g`.`UID` = `cg`.`GENERIC_ID`)))