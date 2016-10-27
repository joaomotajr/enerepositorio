package br.com.eneeyes.main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.CompanyDevice;
import br.com.eneeyes.main.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

	@Query("select p from Position p where p.area.uid = ?1")
	public Page<Position> findByAreaId(Long idUnit, Pageable pageable);
	
	public Position findByCompanyDevice(CompanyDevice companyDevice);
	
}
