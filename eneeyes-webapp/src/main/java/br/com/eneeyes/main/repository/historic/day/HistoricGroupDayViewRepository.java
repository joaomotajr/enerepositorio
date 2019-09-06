package br.com.eneeyes.main.repository.historic.day;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.historic.day.HistoricDayView;

public interface HistoricGroupDayViewRepository extends JpaRepository<HistoricDayView, Long> {

	@Query(nativeQuery = true, value=			
			" SELECT " + 
			"        GETFAKEID() AS uid, " + 
			"        COMPANY_DEVICE_ID AS COMPANY_DEVICE_ID, " + 
			"        STR_TO_DATE(DATE_FORMAT(LAST_UPDATE, '%Y-%m-%d 00:00:00'), '%Y-%m-%d %H:%i:%s') AS last_update, " + 
			"        SUM(VALUE) AS sum_value, " + 
			"        AVG(VALUE) AS avg_value, " + 
			"        MAX(VALUE) AS value, " + 
			"        MAX(VALUE) AS max_value, " + 
			"        MIN(VALUE) AS min_value " + 
			" FROM ( " +			
			"		SELECT * FROM historic " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"		) as h " + 
			"		GROUP BY COMPANY_DEVICE_ID , DATE_FORMAT(LAST_UPDATE, '%d/%m/%Y');")
	public List<HistoricDayView> findByCompanyDeviceIdAndLastUpdateBetweenH(Long companyDevice, Date in, Date out);
	
	@Query(nativeQuery = true, value=			
			" SELECT " + 
			"        GETFAKEID() AS uid, " + 
			"        COMPANY_DEVICE_ID AS COMPANY_DEVICE_ID, " + 
			"        STR_TO_DATE(DATE_FORMAT(LAST_UPDATE, '%Y-%m-%d 00:00:00'), '%Y-%m-%d %H:%i:%s') AS last_update, " + 
			"        SUM(VALUE) AS sum_value, " + 
			"        AVG(VALUE) AS avg_value, " + 
			"        MAX(VALUE) AS value, " + 
			"        MAX(VALUE) AS max_value, " + 
			"        MIN(VALUE) AS min_value " + 
			" FROM ( " +			
			"		SELECT * FROM historic_d " + 
			"		WHERE company_device_id = ?1 AND last_update between ?2 and ?3 " + 
			"		) as h " + 
			"		GROUP BY COMPANY_DEVICE_ID , DATE_FORMAT(LAST_UPDATE, '%d/%m/%Y');")
	public List<HistoricDayView> findByCompanyDeviceIdAndLastUpdateBetweenD(Long companyDevice, Date in, Date out);
	
	
	@Query(nativeQuery = true, value= 
			" SELECT " + 
			"        GETFAKEID() AS uid, " + 
			"        COMPANY_DEVICE_ID AS COMPANY_DEVICE_ID, " + 
			"        STR_TO_DATE(DATE_FORMAT(LAST_UPDATE, '%Y-%m-%d 00:00:00'), '%Y-%m-%d %H:%i:%s') AS last_update, " + 
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
			"		GROUP BY COMPANY_DEVICE_ID , DATE_FORMAT(LAST_UPDATE, '%d/%m/%Y');")
	public List<HistoricDayView> findByCompanyDeviceIdAndLastUpdateBetweenHA(Long companyDevice, Date in, Date out);
	
	@Query(nativeQuery = true, value=
			" SELECT " + 
			"        GETFAKEID() AS uid, " + 
			"        COMPANY_DEVICE_ID AS COMPANY_DEVICE_ID, " + 
			"        STR_TO_DATE(DATE_FORMAT(LAST_UPDATE, '%Y-%m-%d 00:00:00'), '%Y-%m-%d %H:%i:%s') AS last_update, " + 
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
			"		GROUP BY COMPANY_DEVICE_ID , DATE_FORMAT(LAST_UPDATE, '%d/%m/%Y');")
	public List<HistoricDayView> findByCompanyDeviceIdAndLastUpdateBetweenHAB(Long companyDevice, Date in, Date out);
	
	@Query(nativeQuery = true, value=
			" SELECT " + 
			"        GETFAKEID() AS uid, " + 
			"        COMPANY_DEVICE_ID AS COMPANY_DEVICE_ID, " + 
			"        STR_TO_DATE(DATE_FORMAT(LAST_UPDATE, '%Y-%m-%d 00:00:00'), '%Y-%m-%d %H:%i:%s') AS last_update, " + 
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
			"		GROUP BY COMPANY_DEVICE_ID , DATE_FORMAT(LAST_UPDATE, '%d/%m/%Y');")
	public List<HistoricDayView> findByCompanyDeviceIdAndLastUpdateBetweenHABC(Long companyDevice, Date in, Date out);	
	
	@Query(nativeQuery = true, value=
			" SELECT " + 
			"        GETFAKEID() AS uid, " + 
			"        COMPANY_DEVICE_ID AS COMPANY_DEVICE_ID, " + 
			"        STR_TO_DATE(DATE_FORMAT(LAST_UPDATE, '%Y-%m-%d 00:00:00'), '%Y-%m-%d %H:%i:%s') AS last_update, " + 
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
			"		GROUP BY COMPANY_DEVICE_ID , DATE_FORMAT(LAST_UPDATE, '%d/%m/%Y');")
	public List<HistoricDayView> findByCompanyDeviceIdAndLastUpdateBetweenHABCD(Long companyDevice, Date in, Date out);		
}