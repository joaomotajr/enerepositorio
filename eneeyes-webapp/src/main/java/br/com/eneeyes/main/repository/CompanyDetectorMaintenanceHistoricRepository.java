package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.CompanyDetectorMaintenanceHistoric;

public interface CompanyDetectorMaintenanceHistoricRepository  extends JpaRepository<CompanyDetectorMaintenanceHistoric, Long> {
	
	@Query("select h from CompanyDetectorMaintenanceHistoric h where h.companyDetectorId = ?1 ")
	public List<CompanyDetectorMaintenanceHistoric> findByCompanyDetectorId(Long companyDetectorId);	
}
