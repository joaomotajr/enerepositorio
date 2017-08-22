/*--------------------------------------------------------
 View Para retornar SMSs em Status pendentes para envio.
 Uso em Serviços de envio de sms: processSmsService 
---------------------------------------------------------*/
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `queue_sigma_view` AS
    SELECT 
        `pa`.`UID` AS `UID`,
        `pa`.`SIGMA_STATUS` AS `SIGMA_STATUS`,
        `a`.`NAME` AS `ALARM_NAME`,
        `pa`.`LAST_UPDATE` AS `LAST_UPDATE`,
        `pa`.`LAST_VALUE` AS `LAST_VALUE`,
        `pa`.`ALARM_TYPE` AS `ALARM_TYPE`,
        `a`.`NAME` AS `NAME`,
        `a`.`CELULAR` AS `CELULAR`,
        `cda`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,
        `cd`.`NAME` AS `COMPANY_DETECTOR_NAME`,
        `cda`.`SENSOR_ID` AS `SENSOR_ID`
    FROM
        (((`position_alarm` `pa`
        JOIN `company_detector_alarms` `cda` ON (((`pa`.`COMPANY_DETECTOR_ID` = `cda`.`COMPANY_DETECTOR_ID`)
            AND (`pa`.`SENSOR_ID` = `cda`.`SENSOR_ID`))))
        JOIN `alarm` `a` ON ((`cda`.`ALARM_ID` = `a`.`UID`)))
        JOIN `company_detector` `cd` ON ((`cda`.`COMPANY_DETECTOR_ID` = `cd`.`UID`)))
    WHERE
        (`pa`.`SIGMA_STATUS` = 1)

/*-----------
	SIMPLE
------------*/       
        
CREATE     
VIEW queue_sigma_view AS
    SELECT 
        pa.UID AS UID,
        pa.SIGMA_STATUS AS SIGMA_STATUS,
        a.NAME AS ALARM_NAME,
        pa.LAST_UPDATE AS LAST_UPDATE,
        pa.LAST_VALUE AS LAST_VALUE,
        pa.ALARM_TYPE AS ALARM_TYPE,
        a.NAME AS NAME,
        a.CELULAR AS CELULAR,
        cda.COMPANY_DETECTOR_ID AS COMPANY_DETECTOR_ID,
        cd.NAME AS COMPANY_DETECTOR_NAME,
        cda.SENSOR_ID AS SENSOR_ID
    FROM
        (((position_alarm pa
        JOIN company_detector_alarms cda ON (((pa.COMPANY_DETECTOR_ID = cda.COMPANY_DETECTOR_ID)
            AND (pa.SENSOR_ID = cda.SENSOR_ID))))
        JOIN alarm a ON ((cda.ALARM_ID = a.UID)))
        JOIN company_detector cd ON ((cda.COMPANY_DETECTOR_ID = cd.UID)))
    WHERE
        (pa.SIGMA_STATUS = 1)        