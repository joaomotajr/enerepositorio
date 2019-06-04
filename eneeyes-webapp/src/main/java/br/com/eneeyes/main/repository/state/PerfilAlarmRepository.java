package br.com.eneeyes.main.repository.state;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.state.PerfilAlarm;

public interface PerfilAlarmRepository extends JpaRepository<PerfilAlarm, Long> {
	
}
