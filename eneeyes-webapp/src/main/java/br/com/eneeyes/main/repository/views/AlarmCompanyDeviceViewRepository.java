package br.com.eneeyes.main.repository.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.views.AlarmCompanyDeviceView;

public interface AlarmCompanyDeviceViewRepository extends JpaRepository<AlarmCompanyDeviceView, Long> {
	
	@Query("select a from AlarmCompanyDeviceView a where a.alarmId = ?1")
	List<AlarmCompanyDeviceView> findByAlarmId(Long alarmId);
}
