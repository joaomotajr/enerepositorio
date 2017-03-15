CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `company_view` AS
    SELECT 
        `company`.`UID` AS `UID`,
        `company`.`NAME` AS `NAME`,
        `company`.`DESCRIPTION` AS `DESCRIPTION`,
        `company`.`UID` AS `COMPANY_ID`
    FROM
        `company`