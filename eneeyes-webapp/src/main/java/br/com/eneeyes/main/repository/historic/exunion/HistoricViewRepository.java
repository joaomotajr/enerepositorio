package br.com.eneeyes.main.repository.historic.exunion;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.historic.IHistoric;
import br.com.eneeyes.main.model.historic.old.HistoricView;

public interface HistoricViewRepository extends JpaRepository<HistoricView, Long> {
	
	@Query("select h from HistoricView h where h.companyDeviceId = ?1 and h.lastUpdate between ?2 and ?3 ")
	List<IHistoric> findByCompanyDeviceIdAndAndLastUpdateBetween(Long companyDeviceId,  Date inicio, Date fim);
		
	@Query("select h from HistoricView h where h.companyDeviceId = ?1 and h.lastUpdate between ?2 and ?3 ")
	Page<IHistoric> findByCompanyDeviceIdAndAndLastUpdateBetweenPaginated(Long companyDeviceId,  Date inicio, Date fim, Pageable pageable);
	
}
