/*--------------------------------------------------------
 View Para retornar Dados do Historico de Medições.
 Possibilitando filtros e ordenações  
 Tela: 	logHistoric 		
---------------------------------------------------------*/

/* MYSQL */
SELECT 	UID, P.POSITION_ID, VALUE, LAST_UPDATE 	
FROM enedb.position_historic_view p
join 
(
	SELECT 	
	MAX(rank) max, position_id
	FROM enedb.position_historic_view 
	group by POSITION_ID
) as max
on (p.position_id = max.position_id and rank > max - 10)
        
    
/* POSTGRES */   
        
