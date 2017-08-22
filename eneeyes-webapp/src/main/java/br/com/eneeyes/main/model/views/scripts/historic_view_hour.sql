/*--------------------------------------------------------
 View Para retornar Dados do Historico de Medições,
 aqrupados por hora, retornando os máximos e minimos dentro
 de cada hora agrupada. 
   
 Tela: 	logHistoric 		
---------------------------------------------------------*/

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `historic_view_hour` AS
    SELECT 
        GETFAKEID() AS `uid`,
        `historic`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,
        `historic`.`SENSOR_ID` AS `SENSOR_ID`,
        STR_TO_DATE(DATE_FORMAT(`historic`.`LAST_UPDATE`, '%Y-%m-%d %H:00:00'), '%Y-%m-%d %H:%i:%s') AS `last_update`,
        COUNT(0) AS `ticks`,
        MAX(`historic`.`VALUE`) AS `value`,
        MAX(`historic`.`VALUE`) AS `max_value`,
        MIN(`historic`.`VALUE`) AS `min_value`
    FROM
        `historic`
    GROUP BY `historic`.`COMPANY_DETECTOR_ID` , `historic`.`SENSOR_ID` , DATE_FORMAT(`historic`.`LAST_UPDATE`, '%d/%m/%Y') , HOUR(`historic`.`LAST_UPDATE`)
    ORDER BY `historic`.`COMPANY_DETECTOR_ID` , DATE_FORMAT(`historic`.`LAST_UPDATE`, '%d/%m/%Y') , HOUR(`historic`.`LAST_UPDATE`)
    
/*-----------
	SIMPLE
------------*/
    
    
CREATE     
VIEW historic_view_hour AS
    SELECT 
        row_number() OVER () AS UID,
        COMPANY_DETECTOR_ID AS COMPANY_DETECTOR_ID,
        SENSOR_ID AS SENSOR_ID,
        TO_char(historic.LAST_UPDATE, 'YYYY-MM-DD 00:00:00') AS LAST_UPDATE,
        COUNT(0) AS ticks,
        MAX(historic.VALUE) AS value,
        MAX(historic.VALUE) AS max_value,
        MIN(historic.VALUE) AS min_value
    FROM
        historic
    GROUP BY COMPANY_DETECTOR_ID , SENSOR_ID , TO_char(historic.LAST_UPDATE, 'YYYY-MM-DD 00:00:00'), extract(hour from LAST_UPDATE)
    ORDER BY COMPANY_DETECTOR_ID , TO_char(historic.LAST_UPDATE, 'YYYY-MM-DD 00:00:00') , extract(hour from LAST_UPDATE)    