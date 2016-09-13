package br.com.eneeyes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.Alarm;
import br.com.eneeyes.main.model.Area;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

	public Area findByUid(Long uid);	
	
}
