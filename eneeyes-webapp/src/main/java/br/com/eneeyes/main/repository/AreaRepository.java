package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.Area;

public interface AreaRepository extends JpaRepository<Area, Long> {

	@Query("select a from Area a where a.unit.uid = ?1")
	public List<Area> findByUnitID(Long idUnit);		

	public Area findByUid(Long uid);	
	
}
