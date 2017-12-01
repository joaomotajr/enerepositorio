package br.com.eneeyes.main.repository.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.views.LogAuditoriaView;

public interface LogAuditoriaViewRepository extends JpaRepository<LogAuditoriaView, Long> {

	@Query("select u from LogAuditoriaView u where u.companyId = ?1")	
	public List<LogAuditoriaView> findByCompanyId(Long companyId);			
		
}