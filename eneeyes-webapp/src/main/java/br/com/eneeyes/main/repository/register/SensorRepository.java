package br.com.eneeyes.main.repository.register;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.Unit;
import br.com.eneeyes.main.model.register.Sensor;


public interface SensorRepository extends JpaRepository<Sensor, Long> {
	
	public Unit findByUid(Long uid);
	
}
