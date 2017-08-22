/*--------------------------------------------------------
 View Para retornar Dados do Historico de Medições,
 aqrupados por dia, retornando os máximos e minimos dentro
 de cada dia agrupado. 
   
 Tela: 	logHistoric 		
---------------------------------------------------------*/

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `historic_view_day` AS
SELECT 
        GETFAKEID() AS `uid`,
        `historic`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,
        `historic`.`SENSOR_ID` AS `SENSOR_ID`,        
        STR_TO_DATE(DATE_FORMAT(`historic`.`LAST_UPDATE`, '%Y-%m-%d 00:00:00'), '%Y-%m-%d %H:%i:%s') AS `last_update`,
        COUNT(0) AS `ticks`,
        MAX(`historic`.`VALUE`) AS `value`,
        MAX(`historic`.`VALUE`) AS `max_value`,
        MIN(`historic`.`VALUE`) AS `min_value`
    FROM
        `historic`
    GROUP BY `historic`.`COMPANY_DETECTOR_ID` , `historic`.`SENSOR_ID` , DATE_FORMAT(`historic`.`LAST_UPDATE`, '%d/%m/%Y')
    ORDER BY `historic`.`COMPANY_DETECTOR_ID` , DATE_FORMAT(`historic`.`LAST_UPDATE`, '%d/%m/%Y')
    
/*-----------
	SIMPLE
------------*/    
    
CREATE   
VIEW historic_view_day AS
SELECT 
        row_number() OVER () AS UID,
        historic.COMPANY_DETECTOR_ID AS COMPANY_DETECTOR_ID,
        historic.SENSOR_ID AS SENSOR_ID,                
        TO_char(historic.LAST_UPDATE, 'YYYY-MM-DD 00:00:00'),
        COUNT(0) AS TICKS,
        MAX(historic.VALUE) AS value,
        MAX(historic.VALUE) AS MAX_VALUE,
        MIN(historic.VALUE) AS MIN_VALUE
    FROM
        historic
    GROUP BY COMPANY_DETECTOR_ID , historic.SENSOR_ID , TO_char(historic.LAST_UPDATE, 'YYYY-MM-DD 00:00:00')
    ORDER BY COMPANY_DETECTOR_ID, TO_char(historic.LAST_UPDATE, 'YYYY-MM-DD 00:00:00')