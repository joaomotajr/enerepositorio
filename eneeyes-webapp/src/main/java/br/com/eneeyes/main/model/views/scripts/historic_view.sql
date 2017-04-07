/*--------------------------------------------------------
 View Para retornar Dados do Historico de Medições.
 Possibilitando filtros e ordenações  
 Tela: 	logHistoric 		
---------------------------------------------------------*/
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `historic_view` AS
    SELECT 
        `historic`.`UID` AS `UID`,
        `historic`.`LAST_UPDATE` AS `LAST_UPDATE`,
        `historic`.`LAST_VALUE` AS `LAST_VALUE`,
        `historic`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,
        `historic`.`SENSOR_ID` AS `SENSOR_ID`,
        `historic`.`ALARM_TYPE` AS `ALARM_TYPE`
    FROM
        `historic`
    ORDER BY `historic`.`COMPANY_DETECTOR_ID` , `historic`.`LAST_UPDATE` DESC