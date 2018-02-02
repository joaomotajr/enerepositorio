/*--------------------------------------------------------
 View Para retornar Emails em Status pendentes para envio.
 Uso em Serviços de envio de Emails: processEmailService 
---------------------------------------------------------*/

/* MYSQL */

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `queue_email_view` AS
    SELECT 
        `pa`.`UID` AS `UID`,
        `pa`.`EMAIL_STATUS` AS `EMAIL_STATUS`,
        `a`.`NAME` AS `ALARM_NAME`,
        `pa`.`LAST_UPDATE` AS `LAST_UPDATE`,
        `pa`.`LAST_VALUE` AS `LAST_VALUE`,
        `pa`.`ALARM_TYPE` AS `ALARM_TYPE`,
        `a`.`NAME` AS `NAME`,
        `a`.`EMAIL` AS `EMAIL`,
        `a`.`EMAIL1` AS `EMAIL1`,
        `cda`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,
        `cd`.`NAME` AS `COMPANY_DETECTOR_NAME`,
        `s`.`UID` AS `SENSOR_ID`,
        `g`.`NAME` AS `GAS_NAME`,
        `s`.`UNIT_METER_GASES` AS `UNIT_METER_GASES`
    FROM
        ((((((`position_alarm` `pa`
        JOIN `company_detector_alarms` `cda` ON ((`pa`.`COMPANY_DETECTOR_ID` = `cda`.`COMPANY_DETECTOR_ID`)))
        JOIN `company_detector` `cd` ON ((`cda`.`COMPANY_DETECTOR_ID` = `cd`.`UID`)))
        JOIN `detector` `d` ON ((`cd`.`DETECTOR_ID` = `d`.`UID`)))
        JOIN `sensor` `s` ON ((`d`.`SENSOR_ID` = `s`.`UID`)))
        JOIN `gas` `g` ON ((`s`.`GAS_ID` = `g`.`UID`)))
        JOIN `alarm` `a` ON ((`cda`.`ALARM_ID` = `a`.`UID`)))
    WHERE
        ((`pa`.`EMAIL_STATUS` = 1)
            OR (`pa`.`EMAIL_STATUS` = 3))
            
/* POSTGRES */            
            
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `queue_email_view` AS
    SELECT 
        pa.UID AS UID,
        pa.EMAIL_STATUS AS EMAIL_STATUS,
        a.NAME AS ALARM_NAME,
        pa.LAST_UPDATE AS LAST_UPDATE,
        pa.LAST_VALUE AS LAST_VALUE,
        pa.ALARM_TYPE AS ALARM_TYPE,
        a.NAME AS NAME,
        a.EMAIL AS EMAIL,
        a.EMAIL1 AS EMAIL1,
        cda.COMPANY_DETECTOR_ID AS COMPANY_DETECTOR_ID,
        cd.NAME AS COMPANY_DETECTOR_NAME,
        s.uid AS SENSOR_ID,
        g.NAME AS GAS_NAME,
        s.UNIT_METER_GASES AS UNIT_METER_GASES
    FROM
        position_alarm pa
        JOIN company_detector_alarms cda ON (pa.COMPANY_DETECTOR_ID = cda.COMPANY_DETECTOR_ID)            
        JOIN company_detector cd ON cda.COMPANY_DETECTOR_ID = cd.UID
        JOIN detector d ON cd.detector_ID = d.UID
        JOIN sensor s ON d.SENSOR_ID = s.UID        
        JOIN gas g ON s.GAS_ID = g.UID
        JOIN alarm a ON cda.ALARM_ID = a.UID
        
    WHERE
        pa.EMAIL_STATUS = 1
            OR pa.EMAIL_STATUS = 3