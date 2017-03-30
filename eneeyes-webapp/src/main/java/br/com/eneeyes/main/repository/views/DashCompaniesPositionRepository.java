package br.com.eneeyes.main.repository.views;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.views.DashCompaniesPosition;

public interface DashCompaniesPositionRepository extends JpaRepository<DashCompaniesPosition, Long> {

	@Query("select d from DashCompaniesPosition d where d.last_update between ?1 and ?2")
	public List<DashCompaniesPosition> findByLastUpdateBetween(Date in, Date out);
	
	@Query("select d from DashCompaniesPosition d where d.last_update <= ?1")
	public List<DashCompaniesPosition> findByLastMinutesOfLastUpdate(Date lastMinutes);
	
}
