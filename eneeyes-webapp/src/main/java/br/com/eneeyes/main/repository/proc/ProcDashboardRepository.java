package br.com.eneeyes.main.repository.proc;

import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.eneeyes.main.model.procs.ProcDashboard;

public interface ProcDashboardRepository extends CrudRepository<ProcDashboard, Long> {

	@Procedure("ProcDashboard.findDashBoardProcedure")
	List<ProcDashboard> dashboard(@Param("rows") Integer arg);
}

