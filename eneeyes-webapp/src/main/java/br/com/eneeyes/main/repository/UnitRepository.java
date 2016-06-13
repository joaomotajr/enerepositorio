package br.com.eneeyes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.main.model.Unit;


public interface UnitRepository extends JpaRepository<Unit, Long> {

	@Modifying
	@Transactional
	@Query("update Unit c set c.parent.uid = ?1 where c.uid = ?2")
	int setParentFor(Long parent, Long uid);

}
