/*--------------------------------------------------------
 View Para retornar Dados do Historico de Medições,
 aqrupados por hora, retornando os máximos e minimos dentro
 de cada hora agrupada. 
   
 Tela: 	logHistoric 		
---------------------------------------------------------*/

/* MYSQL */

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `historic_view_hour` AS
    SELECT 
        GETFAKEID() AS `uid`,
        COMPANY_DEVICE_ID,        
        STR_TO_DATE(DATE_FORMAT(`historic`.`LAST_UPDATE`, '%Y-%m-%d %H:00:00'), '%Y-%m-%d %H:%i:%s') AS `last_update`,
        COUNT(0) AS `ticks`,
        MAX(`historic`.`VALUE`) AS `value`,
        MAX(`historic`.`VALUE`) AS `max_value`,
        MIN(`historic`.`VALUE`) AS `min_value`
    FROM
        `historic`
    GROUP BY `historic`.`COMPANY_DEVICE_ID` , DATE_FORMAT(`historic`.`LAST_UPDATE`, '%d/%m/%Y') , HOUR(`historic`.`LAST_UPDATE`)
    ORDER BY `historic`.`COMPANY_DEVICE_ID` , DATE_FORMAT(`historic`.`LAST_UPDATE`, '%d/%m/%Y') , HOUR(`historic`.`LAST_UPDATE`)
    