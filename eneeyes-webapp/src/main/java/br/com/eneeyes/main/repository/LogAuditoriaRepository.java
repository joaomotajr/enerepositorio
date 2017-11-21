package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.LogAuditoria;

public interface LogAuditoriaRepository extends JpaRepository<LogAuditoria, Long> {

	@Query("select u from LogAuditoria u where u.companyId = ?1")	
	public List<LogAuditoria> findByCompanyId(Long companyId);			
		
}