package br.com.eneeyes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.CompanyDetectorShadow;

public interface CompanyDetectorShadowRepository extends JpaRepository<CompanyDetectorShadow, Long> {
	
	public CompanyDetectorShadow findByCompanyDeviceId(long uid);
	
}
