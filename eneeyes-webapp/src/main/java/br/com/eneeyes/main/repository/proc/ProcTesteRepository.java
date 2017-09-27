package br.com.eneeyes.main.repository.proc;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.eneeyes.main.model.procs.ProcTeste;

public interface ProcTesteRepository extends CrudRepository<ProcTeste, Long> {

  // Explicitly mapped to named stored procedure {@code User.plus1} in the {@link EntityManager}.
  // By default, we would've try to find a procedure declaration named User.plus1BackedByOtherNamedStoredProcedure
  @Procedure(name = "ProcTeste.plus1")
  Integer plus1BackedByOtherNamedStoredProcedure(@Param("arg") Integer arg);

  // Directly map the method to the stored procedure in the database (to avoid the annotation madness on your domain classes).
  @Procedure
  Integer plus1inout(Integer arg);
}