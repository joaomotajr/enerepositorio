package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Query("select a from Company a where a.unit.uid = ?1")
	public List<Company> findByCompanyID(Long idCompany);
	
	public List<Company> findByCompany(Company company);
	
}
