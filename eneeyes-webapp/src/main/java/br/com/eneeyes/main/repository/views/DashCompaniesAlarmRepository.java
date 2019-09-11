package br.com.eneeyes.main.repository.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.enums.AlarmStatus;
import br.com.eneeyes.main.model.views.DashCompaniesAlarm;

public interface DashCompaniesAlarmRepository extends JpaRepository<DashCompaniesAlarm, Long> {

	@Query("select d from DashCompaniesAlarm d where d.company_id = ?1 order by d.last_update desc")
	public List<DashCompaniesAlarm> findByCompanyId(Long companyId);
	
	@Query("select d from DashCompaniesAlarm d where d.company_id = ?1 and d.alarmStatus in ?2 order by d.last_update desc")
	public List<DashCompaniesAlarm> findByAlarmStatusAndCompanyId(Long companyId, List<AlarmStatus> alarmStatus);
	
	@Query("select d from DashCompaniesAlarm d order by d.last_update desc")
	public List<DashCompaniesAlarm> findAll();
	
	@Query("select d from DashCompaniesAlarm d where d.alarmStatus in ?1 order by d.last_update desc")
	public List<DashCompaniesAlarm> findByAlarmStatus(List<AlarmStatus> alarmStatus);
	
}
