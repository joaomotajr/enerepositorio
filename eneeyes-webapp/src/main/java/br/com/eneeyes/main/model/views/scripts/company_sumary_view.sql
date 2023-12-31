/* MYSQL */
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `company_sumary_view` AS
    SELECT 
        `a`.`UID` AS `uid`,
        `a`.`company_name` AS `company_name`,
        `a`.`company_image` AS `company_image`,
        SUM((CASE
            WHEN ISNULL(`a`.`unit_name`) THEN 0
            ELSE 1
        END)) AS `units`,
        SUM((CASE
            WHEN ISNULL(`a`.`area_name`) THEN 0
            ELSE 1
        END)) AS `areas`,
        SUM(`a`.`devices`) AS `devices`
    FROM
        (SELECT 
            `c`.`UID` AS `UID`,
                `c`.`NAME` AS `company_name`,
                `c`.`image` AS `company_image`,
                `u`.`NAME` AS `unit_name`,
                `a`.`NAME` AS `area_name`,
                SUM((CASE
                    WHEN ISNULL(`cd`.`NAME`) THEN 0
                    ELSE 1
                END)) AS `devices`
        FROM
            (((`eneloga`.`company_view` `c`
        LEFT JOIN `eneloga`.`unit` `u` ON ((`c`.`UID` = `u`.`COMPANY_ID`)))
        LEFT JOIN `eneloga`.`area` `a` ON ((`u`.`UID` = `a`.`UNIT_ID`)))
        LEFT JOIN `eneloga`.`company_device` `cd` ON ((`a`.`UID` = `cd`.`AREA_ID`)))
        GROUP BY `c`.`UID` , `c`.`NAME` , `u`.`UID` , `u`.`NAME` , `a`.`UID` , `a`.`NAME`) `a`
    GROUP BY `a`.`UID`