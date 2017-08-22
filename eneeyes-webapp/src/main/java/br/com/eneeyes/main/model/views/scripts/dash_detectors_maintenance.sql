/* MYSQL */

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
          cds.MAINTENANCE_INTERVAL,
        `d`.`IMAGE` AS `image`,
        `cds`.`INSTALL_DATE` AS `INSTALL_DATE`,
        `z`.`LAST_DATE` AS `LAST_DATE`
    FROM
        ((((((`enedb`.`company` `c`
        JOIN `enedb`.`unit` `u` ON ((`c`.`UID` = `u`.`COMPANY_ID`)))
        JOIN `enedb`.`area` `a` ON ((`u`.`UID` = `a`.`UNIT_ID`)))
        JOIN `enedb`.`company_device` `cd` ON ((`a`.`UID` = `cd`.`AREA_ID`)))
        JOIN `enedb`.`company_detector` `cds` ON ((`cd`.`UID` = `cds`.`COMPANY_DEVICE_ID`)))
        JOIN `enedb`.`detector` `d` ON ((`cds`.`DETECTOR_ID` = `d`.`UID`)))
        LEFT JOIN (SELECT 
            `enedb`.`company_detector_maintenance_historic`.`COMPANY_DETECTOR_ID` AS `companyDetectorId`,
                MAX(`enedb`.`company_detector_maintenance_historic`.`DATE`) AS `LAST_DATE`,
                `enedb`.`company_detector_maintenance_historic`.`DESCRIPTION` AS `description`
        FROM
            `enedb`.`company_detector_maintenance_historic`
        WHERE
            (`enedb`.`company_detector_maintenance_historic`.`HISTORIC_MAINTENACE_TYPE` = 2)
        GROUP BY `enedb`.`company_detector_maintenance_historic`.`COMPANY_DETECTOR_ID`) `z` ON ((`cds`.`UID` = `z`.`companyDetectorId`)))
    WHERE
        (`cds`.`INSTALL_DATE` IS NOT NULL)

/* POSTGRES */
        
CREATE     
VIEW dash_detectors_maintenance AS
    SELECT 
        cds.UID AS UID,
        c.NAME AS company_name,
        u.NAME AS unit_name,
        a.NAME AS area_name,
        cds.NAME AS company_detector_name,
        cds.MAINTENANCE_INTERVAL,
        d.IMAGE AS image,
        cds.INSTALL_DATE AS INSTALL_DATE,
        z.LAST_DATE AS LAST_DATE
    FROM
        company c
        JOIN unit u ON (c.UID = u.COMPANY_ID)
        JOIN area a ON (u.UID = a.UNIT_ID)
        JOIN company_device cd ON (a.UID = cd.AREA_ID)
        JOIN company_detector cds ON (cd.UID = cds.COMPANY_DEVICE_ID)
        JOIN detector d ON (cds.DETECTOR_ID = d.UID)
        LEFT JOIN (SELECT 
            COMPANY_DETECTOR_ID AS companyDetectorId,
            MAX(DATE) AS LAST_DATE,
            DESCRIPTION
        FROM
            company_detector_maintenance_historic
        WHERE
            HISTORIC_MAINTENACE_TYPE = 2
        GROUP BY COMPANY_DETECTOR_ID, DESCRIPTION) z ON (cds.UID = z.companyDetectorId)
    WHERE
        cds.INSTALL_DATE IS NOT NULL