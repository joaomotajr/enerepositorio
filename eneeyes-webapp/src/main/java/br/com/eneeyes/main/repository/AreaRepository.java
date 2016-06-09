package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.Area;
import br.com.eneeyes.main.model.Company;

public interface AreaRepository extends JpaRepository<Area, Long> {

	@Query("select a from Area a where a.company.uid = ?1")
	public List<Area> findByCompanyID(Long idCompany);
	
	public List<Area> findByCompany(Company company);
	
}
