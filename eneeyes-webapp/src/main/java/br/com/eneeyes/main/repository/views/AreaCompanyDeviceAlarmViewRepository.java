package br.com.eneeyes.main.repository.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.views.AreaCompanyDetectorAlarmView;

public interface AreaCompanyDeviceAlarmViewRepository extends JpaRepository<AreaCompanyDetectorAlarmView, Long> {
		
//	List<AreaCompanyDetectorAlarmView> findByCompanyDetectorId(Long companyDetectorId);	
	
	List<AreaCompanyDetectorAlarmView> findByCompanyDeviceId(Long companyDeviceId);
	
	List<AreaCompanyDetectorAlarmView> findByAreaId(Long AreaId);
}