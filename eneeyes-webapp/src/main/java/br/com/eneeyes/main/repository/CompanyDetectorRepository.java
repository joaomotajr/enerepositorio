package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.CompanyDevice;

public interface CompanyDetectorRepository extends JpaRepository<CompanyDetector, Long> {

	public List<CompanyDetector> findByCompanyDevice(CompanyDevice companyDevice );
	public List<CompanyDetector> findByCompanyDeviceIn(List<CompanyDevice> companyDevice );	
}
