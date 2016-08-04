package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.CompanyDevice;

public interface CompanyDeviceRepository  extends JpaRepository<CompanyDevice, Long> {
	
	@Query("select c from CompanyDevice c where c.area.uid = ?1")
	public List<CompanyDevice> findCompanyDeviceByIdArea(Long uid);
	
}
