package br.com.eneeyes.main.repository.views;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.views.DashCompaniesPosition;

public interface DashCompaniesPositionRepository extends JpaRepository<DashCompaniesPosition, Long> {
	
	@Query("select d from DashCompaniesPosition d where d.company_id = ?1")
	public List<DashCompaniesPosition> findByCompanyId(Long companyId);
	
	@Query("select d from DashCompaniesPosition d where d.last_update <= ?1")
	public List<DashCompaniesPosition> findByLastMinutesOfLastUpdate(Date lastMinutes);

	@Query("select d from DashCompaniesPosition d where d.last_update <= ?1 and d.company_id = ?2")
	public List<DashCompaniesPosition> findByLastMinutesOfLastUpdateAndCompanyId(Date lastMinutes, Long companyId);
		
}
