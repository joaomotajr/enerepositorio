package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

	@Query("select p from Position p where p.unit.uid = ?1")
	public List<Position> findByPositionID(Long idPosition);
	
	@Query("select p from Position p where p.unit.uid = ?1")
	public Page<Position> findByUnitId(Long idUnit, Pageable pageable);

	public Position findByUid(Long uid);	
	
	
}
