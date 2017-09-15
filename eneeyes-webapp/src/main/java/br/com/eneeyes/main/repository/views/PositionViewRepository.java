package br.com.eneeyes.main.repository.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.views.PositionView;

public interface PositionViewRepository extends JpaRepository<PositionView, Long> {
	
	
	@Query("select p from PositionView p where p.companyDetectorId = ?1")
	public List<PositionView> findByCompanyDetectorId(Long companyDetectorId);
	
	@Query("select p from PositionView p where p.companyDetectorId = ?1 and p.sensor.uid = ?2")
	public PositionView findByCompanyDetectorIdAndSensorId(Long companyDetectorId, Long sensorId);
				
}
