package br.com.eneeyes.main.repository.procs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.eneeyes.main.model.procs.ProcDashboard;

public interface ProcDashboardRepository extends CrudRepository<ProcDashboard, Long> {

	@Query
	List<ProcDashboard> findDashBoardProcedure(String rows);
	
}
