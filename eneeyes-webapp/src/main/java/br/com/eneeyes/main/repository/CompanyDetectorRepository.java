package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.CompanyDevice;

public interface CompanyDetectorRepository extends JpaRepository<CompanyDetector, Long> {

	public CompanyDetector findByCompanyDevice(CompanyDevice companyDevice );
		
	public List<CompanyDetector> findByCompanyDeviceIn(List<CompanyDevice> companyDevice);	
	
	@Modifying
	@Transactional	
	@Query("update CompanyDetector cd set cd.latitude = ?1, cd.longitude = ?2 where cd.uid = ?3")
	int setLatitudeLongitude(Double latitude, Double longitude, Long uid);
}
