package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.CompanyDetectorMaintenanceHistoric;

public interface CompanyDetectorMaintenanceHistoricRepository  extends JpaRepository<CompanyDetectorMaintenanceHistoric, Long> {

	List<CompanyDetectorMaintenanceHistoric> findByCompanyDetector(CompanyDetector companyDetector);
	
}
