package br.com.eneeyes.main.repository.historic;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.historic.HistoricViewHourAll;
import br.com.eneeyes.main.model.historic.IHistoricGroup;

public interface HistoricViewHourAllRepository extends JpaRepository<HistoricViewHourAll, Long> {
	
	@Query("select h from HistoricViewHourAll h where h.companyDetectorId = ?1 and h.lastUpdate between ?2 and ?3")
	List<IHistoricGroup>findByCompanyDetectorIdAndAndLastUpdateBetween(Long companyDetectorId,  Date in, Date out);
	
	@Query("select h from HistoricViewHourAll h where h.companyDetectorId = ?1 and h.lastUpdate between ?2 and ?3")
	public Page<IHistoricGroup> findByCompanyDetectorIdAndAndLastUpdateBetweenPaginated(Long companyDetectorId,  Date in, Date out, Pageable pageable);	
}
