package br.com.eneeyes.main.repository.historic.exunion;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.historic.IHistoricGroup;
import br.com.eneeyes.main.model.historic.exunion.HistoricViewDay;

public interface HistoricViewDayRepository extends JpaRepository<HistoricViewDay, Long> {
		
//	@Query("select h from HistoricViewDay h where h.companyDeviceId = ?1 and h.lastUpdate between ?2 and ?3")
//	List<IHistoricDayGroup>findByCompanyDeviceIdAndLastUpdateBetween(Long companyDeviceId,  Date in, Date out);
	
	@Query("select h from HistoricViewDay h where h.companyDeviceId = ?1 and h.lastUpdate between ?2 and ?3")
	public Page<IHistoricGroup> findByCompanyDeviceIdAndLastUpdateBetweenPaginated(Long companyDeviceId,  Date in, Date out, Pageable pageable);	
}
