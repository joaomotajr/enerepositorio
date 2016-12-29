package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.PositionAlarm;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.register.Sensor;

public interface PositionAlarmRepository extends JpaRepository<PositionAlarm, Long> {
	
	public List<PositionAlarm> findByCompanyDetector(CompanyDetector companyDetector);
	
	public PositionAlarm findByCompanyDetectorAndSensor(CompanyDetector companyDetector, Sensor sensor);
	
	public PositionAlarm findByCompanyDetectorAndSensorAndAlarmType(CompanyDetector companyDetector, Sensor sensor, AlarmType alarmType);
	
	public Long countByCompanyDetectorAndSensor(CompanyDetector companyDetector, Sensor sensor);
	
}
