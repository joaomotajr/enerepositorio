package br.com.eneeyes.main.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.CompanyDevice;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.enums.AlarmType;

public interface PositionRepository extends JpaRepository<Position, Long> {

	public Long countByCompanyDevice(CompanyDevice companyDevice);
		
	public List<Position> findByCompanyDeviceAndLastUpdateBetween(CompanyDevice companyDevice, Date in, Date out);

	public List<Position> findByCompanyDeviceIn(List<CompanyDetector> lista);
	
	@Query("select DISTINCT p from Position p INNER JOIN FETCH p.companyDevice cd WHERE p.lastUpdate < ?1 and p.alarmType not in ?2")
	public List<Position> findByLastUpdateLessThanAndAlarmTypeNotIn(Date date, List<AlarmType> withoutOrOffAlarms);
	
	@Modifying
	@Transactional
	@Query("update Position p set p.alarmType = ?1 where p.uid = ?2")
	int updateAlarmType(AlarmType alarmType, Long uid);
	
	@Modifying
	@Transactional
	@Query("update Position p set p.alarmType = ?1, p.lastValue = ?2, p.lastUpdate = ?3, p.lastUpdateLatencia = ?4,  p.historicId = ?5  where p.uid = ?6")
	int updatePositionById(AlarmType alarmType, BigDecimal value, Date lastUpdate, Date lastUpdateLatencia, Long historicId,  Long uid);
		
}
