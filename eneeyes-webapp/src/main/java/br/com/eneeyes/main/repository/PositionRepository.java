package br.com.eneeyes.main.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.enums.AlarmType;

public interface PositionRepository extends JpaRepository<Position, Long> {
	
//	@Query("select p from Position p where p.companyDetector.uid = ?1 and p.sensor.uid = ?2")
//	public Position findByCompanyDetectorIdAndSensorId(Long companyDetectorId, Long sensorId);
//	
//	public Long countByCompanyDetectorAndSensor(CompanyDetector companyDetector, Sensor sensor);
	
	public Long countByCompanyDetector(CompanyDetector companyDetector);
		
	public List<Position> findByCompanyDetectorAndLastUpdateBetween(CompanyDetector companyDetector, Date in, Date out);

	public List<Position> findByCompanyDetectorIn(List<CompanyDetector> lista);
	
	public List<Position> findByLastUpdateLessThanAndAlarmTypeNotIn(Date date, List<AlarmType> withoutOrOffAlarms);
	
//	@Modifying
//	@Transactional
//	@Query("update Position p set p.alarmType = ?1 where p.companyDetector.uid = ?2 and p.sensor.uid = ?3")
//	int updateAlarmType(AlarmType alarmType, Long companyDetectorId, Long sensorId);
	
	@Modifying
	@Transactional
	@Query("update Position p set p.alarmType = ?1 where p.uid = ?2")
	int updateAlarmType(AlarmType alarmType, Long uid);
	
	@Modifying
	@Transactional
	@Query("update Position p set p.alarmType = ?1, p.lastValue = ?2, p.lastUpdate = ?3,  p.historicId = ?4  where p.uid = ?5")
	int updatePositionById(AlarmType alarmType, BigDecimal value, Date lastUpdate, Long historicId,  Long uid);
	
//	@Query("update Position p set p.alarmType = ?1, p.lastValue = ?2, p.lastUpdate = ?3,  p.historic = ?4  where p.uid = ?5")
//	int updatePositionById(AlarmType alarmType, BigDecimal value, Date lastUpdate, Historic historic,  Long uid);
		
}
