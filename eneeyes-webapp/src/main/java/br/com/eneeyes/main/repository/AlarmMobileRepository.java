package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.main.model.AlarmMobile;

public interface AlarmMobileRepository extends JpaRepository<AlarmMobile, Long> {
	
	@Modifying
	@Transactional
	@Query("delete from AlarmMobile a where a.alarm.uid = ?1 ")
	void deleteByAlarmId(Long uid);	
	
	@Query("select u from AlarmMobile u where u.mobile.uid = ?1")
	public List<AlarmMobile> findByAlarmId(Long mobileId);
}
