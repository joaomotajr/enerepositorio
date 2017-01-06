CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `dash_companies` AS
    SELECT 
        `c`.`UID` AS `company_id`,
        `c`.`NAME` AS `company`,
        `u`.`NAME` AS `units`,
        `a`.`NAME` AS `area`,
        (CASE
            WHEN (`cd`.`DEVICE_TYPE` = '1') THEN 'DETECTOR'
            WHEN ISNULL(`cd`.`DEVICE_TYPE`) THEN 'NENHUM'
            ELSE 'OUTROS'
        END) AS `device`,
        `cd`.`DEVICE_TYPE` AS `device_type`,
        `cds`.`NAME` AS `companyDetectorName`,
        `cds`.`UID` AS `companyDetector_id`,
        `cds`.`DETECTOR_ID` AS `detector_id`
    FROM
        ((((`company` `c`
        LEFT JOIN `unit` `u` ON ((`c`.`UID` = `u`.`COMPANY_ID`)))
        LEFT JOIN `area` `a` ON ((`u`.`UID` = `a`.`UNIT_ID`)))
        LEFT JOIN `company_device` `cd` ON ((`a`.`UID` = `cd`.`AREA_ID`)))
        LEFT JOIN `company_detector` `cds` ON ((`cd`.`UID` = `cds`.`COMPANY_DEVICE_ID`)))
    ORDER BY `c`.`NAME` , `u`.`NAME` , `a`.`NAME`