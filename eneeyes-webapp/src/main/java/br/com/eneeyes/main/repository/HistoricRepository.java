package br.com.eneeyes.main.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.Historic;

public interface HistoricRepository extends JpaRepository<Historic, Long> {

	@Query("select p from Historic p where p.companyDetector = ?1")
	public Page<Historic> findByCompanyDetector(CompanyDetector companyDetector, Pageable pageable);
	
	public List<Historic> findByCompanyDetector(CompanyDetector companyDetector);
	
	public List<Historic> findByUpdateBetween(Date in, Date out);
	
	public List<Historic> findByCompanyDetectorAndUpdateBetween(CompanyDetector companyDetector, Date in, Date out);
	
	
}
