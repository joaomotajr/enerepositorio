/*--------------------------------------------------------
 View Para retornar SMSs em Status pendentes para envio.
 Uso em Serviços de envio de sms: processSmsService 
---------------------------------------------------------*/
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `queue_sms_view` AS
    SELECT 
        `pa`.`UID` AS `UID`,
        `pa`.`SMS_STATUS` AS `SMS_STATUS`,
        `a`.`NAME` AS `ALARM_NAME`,
        `pa`.`LAST_UPDATE` AS `LAST_UPDATE`,
        `pa`.`LAST_VALUE` AS `LAST_VALUE`,
        `pa`.`ALARM_TYPE` AS `ALARM_TYPE`,
        `a`.`NAME` AS `NAME`,
        `a`.`CELULAR` AS `CELULAR`,
        `a`.`CELULAR1` AS `CELULAR1`,
        `cda`.`COMPANY_DETECTOR_ID` AS `COMPANY_DETECTOR_ID`,
        `cd`.`NAME` AS `COMPANY_DETECTOR_NAME`,
        `cda`.`SENSOR_ID` AS `SENSOR_ID`,
        `g`.`NAME` AS `GAS_NAME`,
        `s`.`UNIT_METER_GASES` AS `UNIT_METER_GASES`
    FROM
        ((((((`position_alarm` `pa`
        JOIN `company_detector_alarms` `cda` ON (((`pa`.`COMPANY_DETECTOR_ID` = `cda`.`COMPANY_DETECTOR_ID`)
            AND (`pa`.`SENSOR_ID` = `cda`.`SENSOR_ID`))))
        JOIN `sensor` `s` ON ((`pa`.`SENSOR_ID` = `s`.`UID`)))
        JOIN `sensor_gases` `sg` ON ((`s`.`UID` = `sg`.`SENSOR_ID`)))
        JOIN `gas` `g` ON ((`sg`.`GASES_ID` = `g`.`UID`)))
        JOIN `alarm` `a` ON ((`cda`.`ALARM_ID` = `a`.`UID`)))
        JOIN `company_detector` `cd` ON ((`cda`.`COMPANY_DETECTOR_ID` = `cd`.`UID`)))
    WHERE
        ((`pa`.`SMS_STATUS` = 1)
            OR (`pa`.`SMS_STATUS` = 3))
            
/*-----------
	SIMPLE
------------*/       
            
            
CREATE     
VIEW queue_sms_view AS
    SELECT 
        pa.UID AS UID,
        pa.SMS_STATUS AS SMS_STATUS,
        a.NAME AS ALARM_NAME,
        pa.LAST_UPDATE AS LAST_UPDATE,
        pa.LAST_VALUE AS LAST_VALUE,
        pa.ALARM_TYPE AS ALARM_TYPE,
        a.NAME AS NAME,
        a.CELULAR AS CELULAR,
        a.CELULAR1 AS CELULAR1,
        cda.COMPANY_DETECTOR_ID AS COMPANY_DETECTOR_ID,
        cd.NAME AS COMPANY_DETECTOR_NAME,
        cda.SENSOR_ID AS SENSOR_ID,
        g.NAME AS GAS_NAME,
        s.UNIT_METER_GASES AS UNIT_METER_GASES
    FROM
        ((((((position_alarm pa
        JOIN company_detector_alarms cda ON (((pa.COMPANY_DETECTOR_ID = cda.COMPANY_DETECTOR_ID)
            AND (pa.SENSOR_ID = cda.SENSOR_ID))))
        JOIN sensor s ON ((pa.SENSOR_ID = s.UID)))
        JOIN sensor_gases sg ON ((s.UID = sg.SENSOR_ID)))
        JOIN gas g ON ((sg.GASES_ID = g.UID)))
        JOIN alarm a ON ((cda.ALARM_ID = a.UID)))
        JOIN company_detector cd ON ((cda.COMPANY_DETECTOR_ID = cd.UID)))
    WHERE
        ((pa.SMS_STATUS = 1)
            OR (pa.SMS_STATUS = 3))            