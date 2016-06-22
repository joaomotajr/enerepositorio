package br.com.eneeyes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.CompanyDevice;

public interface CompanyDeviceRepository  extends JpaRepository<CompanyDevice, Long> {
	
}
