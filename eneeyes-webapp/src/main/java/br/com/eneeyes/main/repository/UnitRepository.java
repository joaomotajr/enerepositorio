package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.Company;
import br.com.eneeyes.main.model.Unit;


public interface UnitRepository extends JpaRepository<Unit, Long> {

	public List<Unit> findByCompany(Company company);
	
	@Query("select a from Unit a where a.company.uid = ?1")
	public List<Unit> findByCompanyID(Long idCompany);
	
	public List<Unit> findByUid(Long uid);
	
}
