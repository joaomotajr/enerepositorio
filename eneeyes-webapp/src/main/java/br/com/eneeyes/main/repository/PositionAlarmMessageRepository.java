package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.PositionAlarm;
import br.com.eneeyes.main.model.PositionAlarmMessage;

public interface PositionAlarmMessageRepository extends JpaRepository<PositionAlarmMessage, Long> {
	
	List<PositionAlarmMessage> findByPositionAlarm(PositionAlarm positionAlarm );

	@Query("select p from PositionAlarmMessage p where p.positionAlarm.uid = ?1")
	List<PositionAlarmMessage> findByPositionAlarmId(Long positionAlarmId);
	
}
