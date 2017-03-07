package br.com.eneeyes.main.repository.views;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.views.HistoricViewDay;

public interface HistoricViewDayRepository extends JpaRepository<HistoricViewDay, Long> {
	
	@Query("select h from HistoricViewDay h where h.company_detector_id = ?1 and h.sensor_id = ?2 and h.last_update between ?3 and ?4 ")
	List<HistoricViewDay> findByCompanyDetectorIdAndSensorIdAndLastUpdateBetween(Long companyDetectorId, Long sensorId, Date inicio, Date fim);
	
}
