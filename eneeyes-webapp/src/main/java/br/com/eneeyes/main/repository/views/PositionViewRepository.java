package br.com.eneeyes.main.repository.views;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.views.PositionView;

public interface PositionViewRepository extends JpaRepository<PositionView, Long> {
		
	@Query("select p from PositionView p where p.companyDeviceId = ?1")
	public PositionView findByCompanyDeviceId(Long companyDetectorDeviceId);	
				
}
