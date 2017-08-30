/* MYSQL */

CREATE VIEW `area_companydetector_alarm_view` AS
   SELECT 
        getFakeId() AS UID,
        cdv.AREA_ID AS AREA_ID,
        cd.UID AS COMPANY_DETECTOR_ID,
        s.UID AS SENSOR_ID,
        cd.NAME AS COMPANY_DETECTOR_NAME,
        cd.LOCAL AS COMPANY_DETECTOR_LOCAL,
        s.NAME AS SENSOR_NAME,
        s.RANGE_MIN AS RANGE_MIN,
        s.RANGE_MAX AS RANGE_MAX,
        s.UNIT_METER_GASES AS UNIT_METER_GASES,
        p.LAST_VALUE,
        p.LAST_UPDATE,
        p.ALARM_TYPE,
        a.NAME AS ALARM_NAME,
        a.ALARM_ON AS ALARM_ON,
        a.ALARM_1 AS ALARM_1,
        a.ALARM_2 AS ALARM_2,
        a.ALARM_3 AS ALARM_3,
        g.NAME AS GAS_NAME
    FROM
        company_device cdv
        JOIN company_detector cd ON (cdv.UID = cd.COMPANY_DEVICE_ID)
        JOIN detector d ON (cd.DETECTOR_ID = d.UID)
        JOIN detector_sensors ds ON (d.UID = ds.DETECTOR_ID)
        JOIN sensor s ON (ds.SENSOR_ID = s.UID)
        LEFT JOIN position p ON (p.COMPANY_DETECTOR_ID = cd.UID)
            AND (p.SENSOR_ID = s.UID)
        LEFT JOIN company_detector_alarms cda ON (cda.COMPANY_DETECTOR_ID = cd.UID)
            AND (cda.SENSOR_ID = s.UID)
        LEFT JOIN alarm a ON (cda.ALARM_ID = a.UID)
        LEFT JOIN gas g ON (a.GAS_ID = g.UID)
    ORDER BY cdv.AREA_ID , cda.COMPANY_DETECTOR_ID    
    
/* POSTGRES */
    
CREATE 
VIEW area_companydetector_alarm_view AS
    SELECT 
        row_number() OVER () AS UID,
        cdv.AREA_ID AS AREA_ID,
        cd.UID AS COMPANY_DETECTOR_ID,
        s.UID AS SENSOR_ID,
        cd.NAME AS COMPANY_DETECTOR_NAME,
        cd.LOCAL AS COMPANY_DETECTOR_LOCAL,
        s.NAME AS SENSOR_NAME,
        s.RANGE_MIN AS RANGE_MIN,
        s.RANGE_MAX AS RANGE_MAX,
        s.UNIT_METER_GASES AS UNIT_METER_GASES,
        p.LAST_VALUE,
        p.LAST_UPDATE,
        p.ALARM_TYPE,
        a.NAME AS ALARM_NAME,
        a.ALARM_ON AS ALARM_ON,
        a.ALARM_1 AS ALARM_1,
        a.ALARM_2 AS ALARM_2,
        a.ALARM_3 AS ALARM_3,
        g.NAME AS GAS_NAME
    FROM
        company_device cdv
        JOIN company_detector cd ON (cdv.UID = cd.COMPANY_DEVICE_ID)
        JOIN detector d ON (cd.DETECTOR_ID = d.UID)
        JOIN detector_sensors ds ON (d.UID = ds.DETECTOR_ID)
        JOIN sensor s ON (ds.SENSOR_ID = s.UID)
        LEFT JOIN position p ON (p.COMPANY_DETECTOR_ID = cd.UID)
            AND (p.SENSOR_ID = s.UID)
        LEFT JOIN company_detector_alarms cda ON (cda.COMPANY_DETECTOR_ID = cd.UID)
            AND (cda.SENSOR_ID = s.UID)
        LEFT JOIN alarm a ON (cda.ALARM_ID = a.UID)
        LEFT JOIN gas g ON (a.GAS_ID = g.UID)
    ORDER BY cdv.AREA_ID , cda.COMPANY_DETECTOR_ID    