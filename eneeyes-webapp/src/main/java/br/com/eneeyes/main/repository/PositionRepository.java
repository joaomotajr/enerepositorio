package br.com.eneeyes.main.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.register.Sensor;

public interface PositionRepository extends JpaRepository<Position, Long> {

//	@Query("select p from Position p where p.area.uid = ?1")
//	public Page<Position> findByAreaId(Long idUnit, Pageable pageable);
	
	public List<Position> findByCompanyDetector(CompanyDetector companyDetector);
	
	public Position findByCompanyDetectorAndSensor(CompanyDetector companyDetector, Sensor sensor);
	
	public Long countByCompanyDetectorAndSensor(CompanyDetector companyDetector, Sensor sensor);
	
	public List<Position> findByCompanyDetectorAndLastUpdateBetween(CompanyDetector companyDetector, Date in, Date out);
		
	public List<Position> findByLastCheckedAfter(Date lastChecked);
	
	public List<Position> findByLastCheckedBetween(Date in, Date out);
	
	@Modifying
	@Transactional
	@Query("update Position p set p.lastChecked = ?1  where p.companyDetector = ?2 and p.sensor = ?3")
	int setCheckDate(Date lastChecked, CompanyDetector companyDetector, Sensor sensor);
	
}
