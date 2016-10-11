package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.CompanyDetectorAlarm;

public interface CompanyDetectorAlarmRepository extends JpaRepository<CompanyDetectorAlarm, Long> {
	
	@Query("select a from CompanyDetectorAlarm a where a.companyDetector = ?1")
	List<CompanyDetectorAlarm> FindByCompanyDetector(CompanyDetector companyDetector);
}
