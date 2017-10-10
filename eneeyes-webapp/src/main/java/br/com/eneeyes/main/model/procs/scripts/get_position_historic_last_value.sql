
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_position_historic_last_value`(rows char)
BEGIN
	select  `h`.`UID` AS `UID`,        
        `h`.`UID` AS `HISTORIC_ID`,        
        `p`.`UID` AS `POSITION_ID`,
        h.last_update,
        `h`.`VALUE` AS `VALUE`
	FROM position p JOIN
		(
		SELECT uid, company_detector_id, sensor_id, last_update, value
			FROM
			  ( SELECT  @prev := '', @n := 0 ) init
			JOIN
			  ( SELECT  @n := if((company_detector_id + '_' + sensor_id) != @prev, 1, @n + 1) AS n,
						@prev := (company_detector_id + '_' + sensor_id),
					uid, company_detector_id, sensor_id, last_update, value
				FROM  historic
				ORDER BY
					company_detector_id, last_update desc
			) x
			WHERE  n <= rows
			order by company_detector_id, sensor_id, last_update desc
		) as h
		ON (((`p`.`COMPANY_DETECTOR_ID` = `h`.`COMPANY_DETECTOR_ID`)  AND (`p`.`SENSOR_ID` = `h`.`SENSOR_ID`)))
        order by p.company_detector_id, p.sensor_id, h.last_update desc;
END;

