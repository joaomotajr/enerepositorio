package br.com.eneeyes.main.repository.historic;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.historic.HistoricC;

public interface HistoricCRepository extends JpaRepository<HistoricC, Long> {
			
	public List<HistoricC> findByCompanyDetectorId(Long companyDetectorId);	
		
	@Query("select h from HistoricView h where h.companyDetectorId = ?1 and h.sensorId = ?2 and h.lastUpdate between ?3 and ?4")
	public List<HistoricC> findByCompanyDetectorIdAndLastUpdateBetween(Long companyDetectorId, Date in, Date out);

	@Query("select h from HistoricView h where h.companyDetectorId = ?1 and h.sensorId = ?2 and h.lastUpdate between ?3 and ?4")
	public List<HistoricC> findByCompanyDetectorIdAndSensorIdAndLastUpdateBetween(Long companyDetectorId, Long sensorId, Date in, Date out);
		
}