CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `dash_sumary` AS
    SELECT 
        `c`.`NAME` AS `NAME`,
        COUNT(DISTINCT `u`.`UID`) AS `units`,
        COUNT(DISTINCT `a`.`UID`) AS `areas`,
        COUNT(`cd`.`UID`) AS `devices`
    FROM
        (((`company` `c`
        LEFT JOIN `unit` `u` ON ((`c`.`UID` = `u`.`COMPANY_ID`)))
        LEFT JOIN `area` `a` ON ((`u`.`UID` = `a`.`UNIT_ID`)))
        LEFT JOIN `company_device` `cd` ON ((`a`.`UID` = `cd`.`AREA_ID`)))
    GROUP BY `c`.`NAME`