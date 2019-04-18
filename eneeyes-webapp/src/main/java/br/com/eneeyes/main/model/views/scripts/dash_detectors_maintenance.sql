CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `dash_detectors_maintenance` AS
    SELECT 
        `cds`.`UID` AS `UID`,
        `c`.`NAME` AS `company_name`,
        `u`.`NAME` AS `unit_name`,
        `a`.`NAME` AS `area_name`,
        `cds`.`NAME` AS `company_detector_name`,
        `cds`.`LOCAL` AS `company_Detector_local`,
        `cds`.`MAINTENANCE_INTERVAL` AS `MAINTENANCE_INTERVAL`,
        `d`.`IMAGE` AS `image`,
        `cds`.`INSTALL_DATE` AS `INSTALL_DATE`,
        `z`.`LAST_DATE` AS `LAST_DATE`
    FROM
        ((((((`eneloga`.`company` `c`
        JOIN `eneloga`.`unit` `u` ON ((`c`.`UID` = `u`.`COMPANY_ID`)))
        JOIN `eneloga`.`area` `a` ON ((`u`.`UID` = `a`.`UNIT_ID`)))
        JOIN `eneloga`.`company_device` `cd` ON ((`a`.`UID` = `cd`.`AREA_ID`)))
        JOIN `eneloga`.`company_detector` `cds` ON ((`cd`.`UID` = `cds`.`COMPANY_DEVICE_ID`)))
        JOIN `eneloga`.`detector` `d` ON ((`cds`.`DETECTOR_ID` = `d`.`UID`)))
        LEFT JOIN (SELECT 
            `eneloga`.`company_detector_maintenance_historic`.`COMPANY_DETECTOR_ID` AS `companyDetectorId`,
                MAX(`eneloga`.`company_detector_maintenance_historic`.`DATE`) AS `LAST_DATE`,
                `eneloga`.`company_detector_maintenance_historic`.`DESCRIPTION` AS `description`
        FROM
            `eneloga`.`company_detector_maintenance_historic`
        WHERE
            (`eneloga`.`company_detector_maintenance_historic`.`HISTORIC_MAINTENACE_TYPE` = 2)
        GROUP BY `eneloga`.`company_detector_maintenance_historic`.`COMPANY_DETECTOR_ID`) `z` ON ((`cds`.`UID` = `z`.`companyDetectorId`)))
    WHERE
        (`cds`.`INSTALL_DATE` IS NOT NULL)