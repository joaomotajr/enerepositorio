package br.com.eneeyes.main.repository.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.views.AlarmCompanyDetectorSensorView;

public interface AlarmCompanyDetectorSensorViewRepository extends JpaRepository<AlarmCompanyDetectorSensorView, Long> {
	
	@Query("select a from AlarmCompanyDetectorSensorView a where a.alarm_id = ?1")
	List<AlarmCompanyDetectorSensorView> findByAlarmId(Long alarmId);
}
