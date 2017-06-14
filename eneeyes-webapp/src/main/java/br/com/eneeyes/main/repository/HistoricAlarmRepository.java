package br.com.eneeyes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.HistoricAlarm;

public interface HistoricAlarmRepository extends JpaRepository<HistoricAlarm, Long> {
	
}
