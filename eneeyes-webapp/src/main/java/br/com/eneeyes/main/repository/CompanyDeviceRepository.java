package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.main.model.Area;
import br.com.eneeyes.main.model.CompanyDevice;

public interface CompanyDeviceRepository  extends JpaRepository<CompanyDevice, Long> {

	@Query("select c from CompanyDevice c where c.area.uid = ?1")
	public List<CompanyDevice> findCompanyDeviceByAreaId(Long uid);
	
	@Modifying
	@Transactional
	@Query("update CompanyDevice c set c.name = ?1  where c.uid = ?2 ")
	int updateCompanyDeviceName(String name, Long uid);
	
	public Long countByArea(Area area);	
}
