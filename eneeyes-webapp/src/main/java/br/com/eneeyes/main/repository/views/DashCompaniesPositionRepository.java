package br.com.eneeyes.main.repository.views;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.views.DashCompaniesPosition;

public interface DashCompaniesPositionRepository extends JpaRepository<DashCompaniesPosition, Long> {
	
	@Query("select distinct d from DashCompaniesPosition d LEFT JOIN FETCH d.positionHistoricView")
	public List<DashCompaniesPosition> findAll();
	
	@Query("select d from DashCompaniesPosition d where d.companyId = ?1")
	public List<DashCompaniesPosition> findByCompanyId(Long companyId);
	
	@Query("select d from DashCompaniesPosition d where d.lastUpdate <= ?1")
	public List<DashCompaniesPosition> findByLastMinutesOfLastUpdate(Date lastMinutes);

	@Query("select d from DashCompaniesPosition d where d.lastUpdate <= ?1 and d.companyId = ?2")
	public List<DashCompaniesPosition> findByLastMinutesOfLastUpdateAndCompanyId(Date lastMinutes, Long companyId);
		
}
