package br.com.eneeyes.main.repository.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.views.AreaCompanyDeviceAlarmView;

public interface AreaCompanyDeviceAlarmViewRepository extends JpaRepository<AreaCompanyDeviceAlarmView, Long> {
	
	List<AreaCompanyDeviceAlarmView> findByCompanyDeviceId(Long companyDeviceId);
	
	List<AreaCompanyDeviceAlarmView> findByAreaId(Long AreaId);
}
