
SELECT position_id, NAME, HISTORIC_ID, value, LAST_UPDATE, 
  SUBSTRING_INDEX(
    SUBSTRING_INDEX(
      GROUP_CONCAT(position_id ORDER BY LAST_UPDATE desc), ',', qtde),
    ',', -1)
    AS Name  
FROM
  position_historic_full_view, tinyint_asc
WHERE
  tinyint_asc.qtde >= 0 AND tinyint_asc.qtde <= 5
GROUP BY
  position_id, qtde
ORDER BY position_id, qtde

CREATE DEFINER=`root`@`localhost` PROCEDURE `plus1inout`(IN arg int, OUT res int)
BEGIN 
    set res = arg + 1;
END


CREATE DEFINER=`root`@`localhost` PROCEDURE `dashboard`(rows char)
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
END


CREATE DEFINER=`root`@`localhost` PROCEDURE `historic_limit`(rows INT)
BEGIN

SELECT uid, company_detector_id, sensor_id, last_update, value
    FROM
      ( SELECT  @prev := '', @n := 0 ) init
    JOIN
      ( SELECT  @n := if((company_detector_id + '_' + sensor_id) != @prev, 1, @n + 1) AS n,
                @prev := (company_detector_id + '_' + sensor_id),
                uid, company_detector_id, sensor_id, last_update, value
            FROM  historic
            ORDER BY
                company_detector_id                
      ) x
    WHERE  n <= rows
    order by company_detector_id, sensor_id, last_update desc;
    

END