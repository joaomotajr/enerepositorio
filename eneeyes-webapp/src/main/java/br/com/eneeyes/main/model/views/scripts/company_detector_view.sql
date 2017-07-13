CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `company_detector_view` AS
    SELECT 
        GETFAKEID() AS `uid`,
        `cd`.`UID` AS `company_Detector_id`,
        `cd`.`NAME` AS `company_Detector_name`,
        `d`.`UID` AS `detector_id`,
        `d`.`NAME` AS `detector_name`,
        `s`.`UID` AS `sensor_id`,
        `s`.`NAME` AS `sensor_name`
    FROM
        (((`company_detector` `cd`
        JOIN `detector` `d` ON ((`cd`.`DETECTOR_ID` = `d`.`UID`)))
        JOIN `detector_sensors` `ds` ON ((`d`.`UID` = `ds`.`DETECTOR_ID`)))
        JOIN `sensor` `s` ON ((`ds`.`SENSOR_ID` = `s`.`UID`)))