package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.Area;

public interface AreaRepository extends JpaRepository<Area, Long> {

	List<Area> findByAreaID(Long uid);

	//@Query("select a from Area a where a.unit.uid = ?1")
	//public List<Area> findByUnitID(Long idUnit);
	
	//public List<Area> findByUnit(Unit unit);
	
}
