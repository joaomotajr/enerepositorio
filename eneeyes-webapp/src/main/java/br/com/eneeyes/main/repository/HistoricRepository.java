package br.com.eneeyes.main.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.Historic;

public interface HistoricRepository extends JpaRepository<Historic, Long> {
	
	//public List<Historic> findByCompanyDetector(CompanyDetector companyDetector);	
	public List<Historic> findByCompanyDetectorId(Long companyDetectorId);	
	
	//public List<Historic> findByCompanyDetectorAndLastUpdateBetween(CompanyDetector companyDetector, Date in, Date out);
	public List<Historic> findByCompanyDetectorIdAndLastUpdateBetween(Long companyDetectorId, Date in, Date out);

//	public List<Historic> findByCompanyDetectorAndSensorAndLastUpdateBetween(CompanyDetector companyDetector, Sensor sensor, Date in, Date out);
	public List<Historic> findByCompanyDetectorIdAndSensorIdAndLastUpdateBetween(Long companyDetectorId, Long sensorId, Date in, Date out);
		
}
