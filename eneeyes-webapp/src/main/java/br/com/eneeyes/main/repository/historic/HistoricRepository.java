package br.com.eneeyes.main.repository.historic;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.historic.Historic;
import br.com.eneeyes.main.model.historic.IHistoric;

public interface HistoricRepository extends JpaRepository<Historic, Long> {
			
	public List<Historic> findByCompanyDetectorId(Long companyDetectorId);	
		
	//public List<Historic> findByCompanyDetectorIdAndLastUpdateBetween(Long companyDetectorId, Date in, Date out);

	//public List<Historic> findByCompanyDetectorIdAndSensorIdAndLastUpdateBetween(Long companyDetectorId, Long sensorId, Date in, Date out);
	
	@Query("select h from HistoricView h where h.companyDetectorId = ?1 and h.sensorId = ?2 and h.lastUpdate between ?3 and ?4 ")
	public List<IHistoric> findByCompanyDetectorIdAndSensorIdAndLastUpdateBetween(Long companyDetectorId, Long sensorId, Date in, Date out);
	
	@Query("select h from HistoricView h where h.companyDetectorId = ?1 and h.sensorId = ?2 and h.lastUpdate between ?3 and ?4 ")
	public Page<IHistoric> findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(Long companyDetectorId, Long sensorId, Date in, Date out, Pageable pageable);
	
		
}