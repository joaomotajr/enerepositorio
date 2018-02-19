package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.CompanyDevice;
import br.com.eneeyes.main.model.CompanyGeneric;

public interface CompanyGenericRepository extends JpaRepository<CompanyGeneric, Long> {
	
	public CompanyGeneric findByCompanyDeviceUid(Long uid);
		
	public List<CompanyGeneric> findByCompanyDeviceIn(List<CompanyDevice> companyDevice);	
	
}
