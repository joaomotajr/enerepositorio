package br.com.eneeyes.main.repository.historic;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.historic.HistoricA;
import br.com.eneeyes.main.model.historic.IHistoric;

public interface HistoricARepository extends JpaRepository<HistoricA, Long> {
	
	@Query("select h from HistoricA h where h.companyDeviceId = ?1 and h.lastUpdate between ?2 and ?3")
	public List<IHistoric> findByCompanyDeviceIdAndAndLastUpdateBetween(Long companyDeviceId, Date in, Date out);

	@Query("select h from HistoricA h where h.companyDeviceId = ?1 and h.lastUpdate between ?2 and ?2 ")
	public Page<IHistoric> findByCompanyDeviceIdAndAndLastUpdateBetweenPaginated(Long companyDeviceId, Date in, Date out, Pageable pageable);
		
}