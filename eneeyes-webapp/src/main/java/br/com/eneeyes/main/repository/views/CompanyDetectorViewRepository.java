package br.com.eneeyes.main.repository.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.views.CompanyDetectorView;

public interface CompanyDetectorViewRepository extends JpaRepository<CompanyDetectorView, Long> {
	
	public Long countBySensorId(Long sensorId);
	
	public Long countBySensorIdIn(List<Long> sensorIds);

	public Long countByDetectorId(Long detectorId);	
}
