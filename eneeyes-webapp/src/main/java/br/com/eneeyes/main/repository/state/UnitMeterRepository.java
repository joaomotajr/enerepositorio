package br.com.eneeyes.main.repository.state;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.state.UnitMeter;

public interface UnitMeterRepository extends JpaRepository<UnitMeter, Long> {
	
}
