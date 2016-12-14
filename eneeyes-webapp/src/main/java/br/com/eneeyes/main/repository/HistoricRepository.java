package br.com.eneeyes.main.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.Historic;
import br.com.eneeyes.main.model.register.Sensor;

public interface HistoricRepository extends JpaRepository<Historic, Long> {

	@Query("select p from Historic p where p.companyDetector = ?1")
	public Page<Historic> findByCompanyDetector(CompanyDetector companyDetector, Pageable pageable);
	
	public List<Historic> findByCompanyDetector(CompanyDetector companyDetector);
	
	public List<Historic> findByCompanyDetectorAndSensor(CompanyDetector companyDetector, Sensor sensor);
	
	public List<Historic> findByCompanyDetectorAndSensorAndLastUpdateBetween(CompanyDetector companyDetector, Sensor sensor, Date in, Date out);
	
	public List<Historic> findByLastUpdateBetween(Date in, Date out);
	
	public List<Historic> findByCompanyDetectorAndLastUpdateBetween(CompanyDetector companyDetector, Date in, Date out);
	
	
}
