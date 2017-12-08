package br.com.eneeyes.main.repository.views;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.views.LogAuditoriaView;

public interface LogAuditoriaViewRepository extends JpaRepository<LogAuditoriaView, Long> {

	@Query("select a from LogAuditoriaView a where a.companyId = ?1")	
	public List<LogAuditoriaView> findByCompanyId(Long companyId);	
	
	@Query("select a from LogAuditoriaView a where a.companyId = ?1 and a.dateTime between ?2 and ?3 order by a.dateTime desc")	
	public Page<LogAuditoriaView> findByCompanyIdAndDateTimeBetweenPaginated(Long companyId, Date in, Date out, Pageable pageable);
	
	@Query("select a from LogAuditoriaView a where a.dateTime between ?1 and ?2 order by a.dateTime desc")	
	public Page<LogAuditoriaView> findByDateTimeBetweenPaginated(Date in, Date out, Pageable pageable);
		
}