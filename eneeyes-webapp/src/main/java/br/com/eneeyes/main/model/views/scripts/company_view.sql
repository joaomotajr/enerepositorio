/*--------------------------------------------------------
 View Para retornar Dados de Empresas sem carregas as 
 tabelas/obejtos a ela associados nem JPA
 Telas: companies
 		logHistoric
 		alarm
---------------------------------------------------------*/

/* MYSQL */

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
        
/* POSTGRES */        
        
    CREATE     
	VIEW company_view AS
    SELECT 
        company.UID AS UID,
        company.NAME AS NAME,
        company.DESCRIPTION AS DESCRIPTION,
        company.UID AS COMPANY_ID
    FROM
        company