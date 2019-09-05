package br.com.eneeyes.main.repository.historic.hour;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.historic.IHistoricGroup;
import br.com.eneeyes.main.model.historic.hour.HistoricHourView;

public interface HistoricGroupHourViewRepository extends JpaRepository<HistoricHourView, Long> {
	
	@Query(nativeQuery = true, value= 
			"select * from historic_hour_view h where h.company_device_id = ?1 and h.last_update between ?2 and ?3"
			+ " UNION  "
		 + "select * from historic_a_hour_view h where h.company_device_id = ?1 and h.last_update between ?2 and ?3")
	public List<IHistoricGroup> findByCompanyDeviceIdAndLastUpdateBetween(Long companyDevice, Date in, Date out);
	
	@Query(nativeQuery = true, value=			
			" SELECT " + 
			"        GETFAKEID() AS uid, " + 
			"        COMPANY_DEVICE_ID AS COMPANY_DEVICE_ID, " + 
			"        STR_TO_DATE(DATE_FORMAT(LAST_UPDATE, '%Y-%m-%d %H:00:00'), '%Y-%m-%d %H:%i:%s') AS last_update, " + 
			"        SUM(VALUE) AS sum_value, " + 
			"        AVG(VALUE) AS avg_value, " + 
			"        MAX(VALUE) AS value, " + 
			"        MAX(VALUE) AS max_value, " + 
			"        MIN(VALUE) AS min_value " + 
			" FROM ( " +			
			"		SELECT * FROM historic " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"		) as h " + 
			"	GROUP BY `COMPANY_DEVICE_ID` , DATE_FORMAT(LAST_UPDATE, '%d/%m/%Y'), HOUR(LAST_UPDATE);")
	public List<HistoricHourView> findByCompanyDeviceIdAndLastUpdateBetweenH(Long companyDevice, Date in, Date out);

	
	@Query(nativeQuery = true, value= 
			" SELECT " + 
			"        GETFAKEID() AS uid, " + 
			"        COMPANY_DEVICE_ID AS COMPANY_DEVICE_ID, " + 
			"        STR_TO_DATE(DATE_FORMAT(LAST_UPDATE, '%Y-%m-%d %H:00:00'), '%Y-%m-%d %H:%i:%s') AS last_update, " + 
			"        SUM(VALUE) AS sum_value, " + 
			"        AVG(VALUE) AS avg_value, " + 
			"        MAX(VALUE) AS value, " + 
			"        MAX(VALUE) AS max_value, " + 
			"        MIN(VALUE) AS min_value " + 
			" FROM ( " +
			"		SELECT * FROM historic_a " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"        union  " + 
			"		SELECT * FROM historic " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"		) as h " + 
			"	GROUP BY `COMPANY_DEVICE_ID` , DATE_FORMAT(LAST_UPDATE, '%d/%m/%Y'), HOUR(LAST_UPDATE);")
	public List<HistoricHourView> findByCompanyDeviceIdAndLastUpdateBetweenHA(Long companyDevice, Date in, Date out);
	
	@Query(nativeQuery = true, value=
			" SELECT " + 
			"        GETFAKEID() AS uid, " + 
			"        COMPANY_DEVICE_ID AS COMPANY_DEVICE_ID, " + 
			"        STR_TO_DATE(DATE_FORMAT(LAST_UPDATE, '%Y-%m-%d %H:00:00'), '%Y-%m-%d %H:%i:%s') AS last_update, " + 
			"        SUM(VALUE) AS sum_value, " + 
			"        AVG(VALUE) AS avg_value, " + 
			"        MAX(VALUE) AS value, " + 
			"        MAX(VALUE) AS max_value, " + 
			"        MIN(VALUE) AS min_value " + 
			" FROM ( " + 
			"		SELECT * FROM historic_b " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"        union  " + 
			"		SELECT * FROM historic_a " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"        union  " + 
			"		SELECT * FROM historic " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"		) as h " + 
			"	GROUP BY `COMPANY_DEVICE_ID` , DATE_FORMAT(LAST_UPDATE, '%d/%m/%Y'), HOUR(LAST_UPDATE);")
	public List<HistoricHourView> findByCompanyDeviceIdAndLastUpdateBetweenHAB(Long companyDevice, Date in, Date out);
	
	@Query(nativeQuery = true, value=
			" SELECT " + 
			"        GETFAKEID() AS uid, " + 
			"        COMPANY_DEVICE_ID AS COMPANY_DEVICE_ID, " + 
			"        STR_TO_DATE(DATE_FORMAT(LAST_UPDATE, '%Y-%m-%d %H:00:00'), '%Y-%m-%d %H:%i:%s') AS last_update, " + 
			"        SUM(VALUE) AS sum_value, " + 
			"        AVG(VALUE) AS avg_value, " + 
			"        MAX(VALUE) AS value, " + 
			"        MAX(VALUE) AS max_value, " + 
			"        MIN(VALUE) AS min_value " + 
			" FROM ( " +			
			"		SELECT * FROM historic_c " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"        union  " + 
			"		SELECT * FROM historic_b " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"        union  " + 
			"		SELECT * FROM historic_a " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"        union  " + 
			"		SELECT * FROM historic " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"		) as h " + 
			"	GROUP BY `COMPANY_DEVICE_ID` , DATE_FORMAT(LAST_UPDATE, '%d/%m/%Y'), HOUR(LAST_UPDATE);")
	public List<HistoricHourView> findByCompanyDeviceIdAndLastUpdateBetweenHABC(Long companyDevice, Date in, Date out);
	
	
	@Query(nativeQuery = true, value=
			" SELECT " + 
			"        GETFAKEID() AS uid, " + 
			"        COMPANY_DEVICE_ID AS COMPANY_DEVICE_ID, " + 
			"        STR_TO_DATE(DATE_FORMAT(LAST_UPDATE, '%Y-%m-%d %H:00:00'), '%Y-%m-%d %H:%i:%s') AS last_update, " + 
			"        SUM(VALUE) AS sum_value, " + 
			"        AVG(VALUE) AS avg_value, " + 
			"        MAX(VALUE) AS value, " + 
			"        MAX(VALUE) AS max_value, " + 
			"        MIN(VALUE) AS min_value " + 
			" FROM ( " + 
			"		SELECT * FROM historic_d 	 " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"		union  " + 
			"		SELECT * FROM historic_c " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"        union  " + 
			"		SELECT * FROM historic_b " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"        union  " + 
			"		SELECT * FROM historic_a " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"        union  " + 
			"		SELECT * FROM historic " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"		) as h " + 
			"	GROUP BY `COMPANY_DEVICE_ID` , DATE_FORMAT(LAST_UPDATE, '%d/%m/%Y'), HOUR(LAST_UPDATE);")
	public List<HistoricHourView> findByCompanyDeviceIdAndLastUpdateBetweenHABCD(Long companyDevice, Date in, Date out);
	
	
		
}