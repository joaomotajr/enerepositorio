/*--------------------------------------------------------
 View Para retornar Dados do Historico de Medições.
 Possibilitando filtros e ordenações  
 Tela: 	logHistoric 		
---------------------------------------------------------*/

/* MYSQL */

USE `enedb`;
CREATE 
     OR REPLACE ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `position_historic_view` AS
    SELECT 
        `h`.`UID` AS `UID`,
        `p`.`UID` AS `POSITION_ID`,
        `h`.`UID` AS `HISTORIC_ID`,
        `h`.`LAST_UPDATE` AS `last_update`,
        `h`.`VALUE` AS `VALUE`
    FROM
        `position` `p` join
        (
			SELECT UID, COMPANY_DETECTOR_ID, sensor_ID, last_update, value
			FROM   historic_view s
			WHERE 
			(
					SELECT 	COUNT(uid) 
					FROM 	historic_view  f
					WHERE (f.COMPANY_DETECTOR_ID = s.COMPANY_DETECTOR_ID AND f.sensor_ID = s.sensor_ID) and
						  f.uid >= s.uid                          
			) <= 11
        ) as h
        ON (`p`.`COMPANY_DETECTOR_ID` = `h`.`COMPANY_DETECTOR_ID` AND `p`.`SENSOR_ID` = `h`.`SENSOR_ID`);

        
    
/* POSTGRES */   
        
