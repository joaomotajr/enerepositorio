package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.PositionAlarm;
import br.com.eneeyes.main.model.enums.AlarmStatus;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.EmailStatus;
import br.com.eneeyes.main.model.enums.SmsStatus;
import br.com.eneeyes.main.model.register.Sensor;

public interface PositionAlarmRepository extends JpaRepository<PositionAlarm, Long> {
	
	public List<PositionAlarm> findByCompanyDetector(CompanyDetector companyDetector);
	
	public PositionAlarm findByUid(Long uid);
	
	public PositionAlarm findByCompanyDetectorAndSensor(CompanyDetector companyDetector, Sensor sensor);
	
	public PositionAlarm findByCompanyDetectorAndSensorAndAlarmType(CompanyDetector companyDetector, Sensor sensor, AlarmType alarmType);
	
	public Long countByCompanyDetectorAndSensor(CompanyDetector companyDetector, Sensor sensor);
	
	@Modifying
	@Transactional
	@Query("update PositionAlarm p set p.emailStatus = ?1 where p.uid = ?2 ")
	int updateEmailStatus(EmailStatus emailStatus, long uid);
	
	@Modifying
	@Transactional
	@Query("update PositionAlarm p set p.smsStatus = ?1 where p.uid = ?2 ")
	int updateSmsStatus(SmsStatus smsStatus, long uid);
		
	@Modifying
	@Transactional
	@Query("update PositionAlarm p set p.alarmStatus = ?1 where p.uid = ?2 ")
	int updateAlarmStatus(AlarmStatus alarmStatus, long uid);
	
	@Modifying
	@Transactional
	@Query("update PositionAlarm p set p.soundStatus = ?1 where p.uid = ?2 ")
	int updateSoundStatus(Boolean soundStatus, long uid);
	
}
