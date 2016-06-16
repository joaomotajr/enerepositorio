package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	public List<Company> findByUid(Long uid);
	
}
