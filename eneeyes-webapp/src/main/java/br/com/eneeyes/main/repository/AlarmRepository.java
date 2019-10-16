package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.main.model.Alarm;
import br.com.eneeyes.main.model.Area;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

	public Area findByUid(Long uid);
	
	@Modifying
	@Transactional
	@Query("update Alarm a set a.alarmOn = ?1  where a.uid = ?2 ")
	int onOff(Boolean onOff, Long uid);
	
	
	@Query("select u from Alarm u where u.company.uid = ?1")
	public List<Alarm> findByCompanyId(Long companyId);
	
	@Query("select DISTINCT a from Alarm a "
			+ "LEFT JOIN FETCH a.alarmEmails "
			+ "LEFT JOIN FETCH a.alarmMobiles "
			+ "LEFT JOIN FETCH a.perfilAlarm1 "
			+ "LEFT JOIN FETCH a.perfilAlarm2 "
			+ "LEFT JOIN FETCH a.perfilAlarm3 "
			+ "LEFT JOIN FETCH a.company "
			+ "LEFT JOIN FETCH a.gas "
			+ "LEFT JOIN FETCH a.deviceType "
			+ "LEFT JOIN FETCH a.unitMeter ")
	List<Alarm> findAll();
	
}
