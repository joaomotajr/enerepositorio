package br.com.eneeyes.main.repository.historic;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.historic.HistoricViewHour;
import br.com.eneeyes.main.model.historic.IHistoricGroup;

public interface HistoricViewHourRepository extends JpaRepository<HistoricViewHour, Long> {
	
	@Query("select h from HistoricViewHour h where h.companyDeviceId = ?1 and h.lastUpdate between ?2 and ?3")
	List<IHistoricGroup>findByCompanyDeviceIdAndLastUpdateBetween(Long companyDeviceId,  Date in, Date out);
	
	@Query("select h from HistoricViewHour h where h.companyDeviceId = ?1 and h.lastUpdate between ?2 and ?3")
	public Page<IHistoricGroup> findByCompanyDeviceIdAndLastUpdateBetweenPaginated(Long companyDeviceId,  Date in, Date out, Pageable pageable);	
}
