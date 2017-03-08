CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `dash_totals_companies` AS
    SELECT 
        `c`.`NAME` AS `NAME`,
        (CASE
            WHEN ISNULL(`u`.`COMPANY_ID`) THEN 0
            ELSE 1
        END) AS `units`,
        (CASE
            WHEN ISNULL(`a`.`UNIT_ID`) THEN 0
            ELSE 1
        END) AS `areas`,
        (CASE
            WHEN ISNULL(`cd`.`AREA_ID`) THEN 0
            ELSE 1
        END) AS `devices`
    FROM
        (((`company` `c`
        LEFT JOIN `unit` `u` ON ((`c`.`UID` = `u`.`COMPANY_ID`)))
        LEFT JOIN `area` `a` ON ((`u`.`UID` = `a`.`UNIT_ID`)))
        LEFT JOIN `company_device` `cd` ON ((`a`.`UID` = `cd`.`AREA_ID`)))
    GROUP BY `u`.`COMPANY_ID` , `a`.`UNIT_ID` , `cd`.`AREA_ID`