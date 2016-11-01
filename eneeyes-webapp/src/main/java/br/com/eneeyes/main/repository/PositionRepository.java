package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.register.Sensor;

public interface PositionRepository extends JpaRepository<Position, Long> {

//	@Query("select p from Position p where p.area.uid = ?1")
//	public Page<Position> findByAreaId(Long idUnit, Pageable pageable);
	
	public List<Position> findByCompanyDetector(CompanyDetector companyDetector);
	
	public Position findBySensor(Sensor sensor);
	
}
