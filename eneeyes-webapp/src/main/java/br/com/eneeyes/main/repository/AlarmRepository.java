package br.com.eneeyes.main.repository;

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
	@Query("update Alarm a set a.alarmOff = ?1  where a.uid = ?2 ")
	int onOff(Boolean onOff, Long uid);
	
}
