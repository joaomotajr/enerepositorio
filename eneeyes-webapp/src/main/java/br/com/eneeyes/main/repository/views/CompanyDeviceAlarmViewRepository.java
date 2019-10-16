package br.com.eneeyes.main.repository.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.views.CompanyDeviceAlarmView;

public interface CompanyDeviceAlarmViewRepository extends JpaRepository<CompanyDeviceAlarmView, Long> {
	
	@Query("select DISTINCT c from CompanyDeviceAlarmView c INNER JOIN FETCH c.alarm a "
			+ "LEFT JOIN FETCH a.alarmEmails "
			+ "LEFT JOIN FETCH a.alarmMobiles "
			+ "LEFT JOIN FETCH a.perfilAlarm1 "
			+ "LEFT JOIN FETCH a.perfilAlarm2 "
			+ "LEFT JOIN FETCH a.perfilAlarm3 "
			+ "LEFT JOIN FETCH a.company "
			+ "LEFT JOIN FETCH a.gas "
			+ "LEFT JOIN FETCH a.deviceType "
			+ "LEFT JOIN FETCH a.unitMeter ")
	List<CompanyDeviceAlarmView> findAll();
}
