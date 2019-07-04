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
			
	public List<IHistoric> findByCompanyDeviceId(Long companyDeviceId);	
		
	@Query("select h from Historic h where h.companyDeviceId = ?1 and h.lastUpdate between ?2 and ?3 ")
	public List<IHistoric> findByCompanyDeviceIdAndLastUpdateBetween(Long companyDeviceId,  Date in, Date out);
	
	@Query("select h from Historic h where h.companyDeviceId = ?1 and h.lastUpdate between ?2 and ?3 ")
	public Page<IHistoric> findByCompanyDeviceIdAndLastUpdateBetweenPaginated(Long companyDeviceId,  Date in, Date out, Pageable pageable);
	
		
}