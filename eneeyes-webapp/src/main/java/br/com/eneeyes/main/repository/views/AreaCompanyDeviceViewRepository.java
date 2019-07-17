package br.com.eneeyes.main.repository.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.views.AreaCompanyDeviceView;

public interface AreaCompanyDeviceViewRepository extends JpaRepository<AreaCompanyDeviceView, Long> {
	
	@Query("select a from AreaCompanyDeviceView a where a.areaId = ?1")
	List<AreaCompanyDeviceView> findByAreaId(Long areaId);
}
