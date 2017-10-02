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
VIEW `historic_view` AS
    SELECT 
        `historic`.`UID` AS `UID`,
        `historic`.`LAST_UPDATE` AS `LAST_UPDATE`,
        `historic`.`VALUE` AS `VALUE`,
        `historic`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,
        `historic`.`SENSOR_ID` AS `SENSOR_ID`
    FROM
        `historic`
    ORDER BY `historic`.`COMPANY_DETECTOR_ID` , `historic`.`LAST_UPDATE` DESC
    
/* POSTGRES */    
        
CREATE         
VIEW historic_view AS
	SELECT 
    	UID AS UID,
    	LAST_UPDATE AS LAST_UPDATE,
    	VALUE,
    	COMPANY_DETECTOR_ID AS COMPANY_DETECTOR_ID,
    	SENSOR_ID AS SENSOR_ID
	FROM
    	historic
	ORDER BY historic.COMPANY_DETECTOR_ID , historic.LAST_UPDATE DESC