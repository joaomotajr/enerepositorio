CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `log_auditoria_view` AS
    SELECT 
        `a`.`UID` AS `UID`,
        (CASE
            WHEN (`a`.`ACTION` = 0) THEN 'UPDATE'
            WHEN (`a`.`ACTION` = 1) THEN 'CREATE'
            WHEN (`a`.`ACTION` = 2) THEN 'DELETE'
            WHEN (`a`.`ACTION` = 3) THEN 'SELECT'
            ELSE 'DESCONHECIDO'
        END) AS `ACTION_NAME`,
        `u`.`DISPLAYNAME_` AS `USER_NAME`,
        `a`.`DATE_TIME` AS `DATE_TIME`,
        `a`.`DETAIL` AS `DETAIL`,
        `a`.`ENTITY` AS `ENTITY`,
        `c`.`NAME` AS `COMPANY_NAME`,
        `c`.`UID` AS `COMPANY_ID`
    FROM
        ((`log_auditoria` `a`
        JOIN `aln_id_user` `u` ON ((`a`.`USER_ID` = `u`.`ID_`)))
        LEFT JOIN `company` `c` ON ((`u`.`COMPANY_ID` = `c`.`UID`)))