package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.main.model.AlarmEmail;

public interface AlarmEmailRepository extends JpaRepository<AlarmEmail, Long> {
	
	@Modifying
	@Transactional
	@Query("delete from AlarmEmail a where a.alarm.uid = ?1 ")
	void deleteByAlarmId(Long uid);	
	
	@Query("select u from AlarmEmail u where u.alarm.uid = ?1")
	public List<AlarmEmail> findByAlarmId(Long alarmId);
}
