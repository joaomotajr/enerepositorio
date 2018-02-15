package br.com.eneeyes.main.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.HistoricAlarm;

public interface HistoricAlarmRepository extends JpaRepository<HistoricAlarm, Long> {
	
	@Query("select h from HistoricAlarm h where h.companyDetectorId = ?1 and h.date between ?2 and ?2")
	public List<HistoricAlarm> findByCompanyDetectorIdAndLastUpdateBetween(Long companyDetectorId,  Date in, Date out);
	
	@Query("select h from HistoricAlarm h where h.companyDetectorId = ?1 and h.date between ?2 and ?3")
	public Page<HistoricAlarm> findByCompanyDetectorIdAndLastUpdateBetweenPaginated(Long companyDetectorId, Date in, Date out, Pageable pageable);
	
}
