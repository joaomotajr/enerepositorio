package br.com.eneeyes.main.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.register.Sensor;

public interface PositionRepository extends JpaRepository<Position, Long> {
	
	public List<Position> findByCompanyDetector(CompanyDetector companyDetector);
	
	public Position findByCompanyDetectorAndSensor(CompanyDetector companyDetector, Sensor sensor);
	
	public Long countByCompanyDetectorAndSensor(CompanyDetector companyDetector, Sensor sensor);
		
	public List<Position> findByCompanyDetectorAndLastUpdateBetween(CompanyDetector companyDetector, Date in, Date out);

	public List<Position> findByCompanyDetectorIn(List<CompanyDetector> lista);
	
	public List<Position> findByLastUpdateBetween(Date in, Date out);
	
	public List<Position> findByLastUpdateLessThan(Date date);
	
}
