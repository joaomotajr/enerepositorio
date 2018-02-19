package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.main.model.CompanyDevice;
import br.com.eneeyes.main.model.CompanyGeneric;

public interface CompanyGenericRepository extends JpaRepository<CompanyGeneric, Long> {
	
	public CompanyGeneric findByCompanyDeviceUid(Long uid);
		
	public List<CompanyGeneric> findByCompanyDeviceIn(List<CompanyDevice> companyDevice);	
		
	@Modifying
	@Transactional	
	@Query("update CompanyGeneric cd set cd.alarm.uid = ?1 where cd.uid = ?2")
	int setAlarm(Long alarm_id, Long uid);
}
