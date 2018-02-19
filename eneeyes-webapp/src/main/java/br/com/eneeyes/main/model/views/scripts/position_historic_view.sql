/*--------------------------------------------------------
 View Para retornar Dados do Historico de Medições.
 Possibilitando filtros e ordenações  
 Tela: 	logHistoric 		
---------------------------------------------------------*/

/* MYSQL */
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `position_historic_view` AS
    SELECT 
        `h`.`UID` AS `UID`,
        `p`.`UID` AS `POSITION_ID`,
        `h`.`LAST_UPDATE` AS `last_update`,
        `h`.`VALUE` AS `VALUE`
    FROM
        (`position` `p`
        JOIN `historic` `h` ON ((`p`.`COMPANY_DEVICE_ID` = `h`.`COMPANY_DEVICE_ID`)))
    ORDER BY `p`.`UID` , `h`.`UID` DESC