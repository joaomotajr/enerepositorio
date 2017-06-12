package br.com.eneeyes.main.repository.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.views.DashCompaniesAlarm;

public interface DashCompaniesAlarmRepository extends JpaRepository<DashCompaniesAlarm, Long> {

	@Query("select d from DashCompaniesAlarm d where d.company_id = ?1")
	public List<DashCompaniesAlarm> findByCompanyId(Long companyId);
	
}
