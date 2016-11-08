package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.main.model.Alarm;
import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.CompanyDetectorAlarm;

public interface CompanyDetectorAlarmRepository extends JpaRepository<CompanyDetectorAlarm, Long> {
	
	@Query("select a from CompanyDetectorAlarm a where a.companyDetector = ?1")
	List<CompanyDetectorAlarm> FindByCompanyDetector(CompanyDetector companyDetector);
	
	
	@Modifying
	@Transactional
	@Query("update CompanyDetectorAlarm c set c.alarm = ?1 where c.companyDetector = ?2 and c.uid.sensorId = ?3")
	int updateAlarm(Alarm alarm, CompanyDetector companyDetector, Long sensorId);
		
	@Modifying
	@Transactional
	@Query("delete CompanyDetectorAlarm c where c.companyDetector = ?1 and c.uid.sensorId = ?2")
	int deleteAlarm(CompanyDetector companyDetector, Long sensorId);
	
	@Modifying
	@Transactional
	@Query("delete CompanyDetectorAlarm c where c.companyDetector.uid = ?1")
	int deleteAlarm(Long companyDetectorId);
	
	
	@Query("select c from CompanyDetectorAlarm c where c.companyDetector = ?2 and c.uid.sensorId = ?3")
	CompanyDetectorAlarm selectCompanyDetectorAlarmByCompanyDetectorAndSensor(CompanyDetector companyDetector, Long sensorId);
}
