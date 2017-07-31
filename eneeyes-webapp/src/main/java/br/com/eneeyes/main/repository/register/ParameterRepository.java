package br.com.eneeyes.main.repository.register;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.register.Parameter;

public interface ParameterRepository  extends JpaRepository<Parameter, Long> {

}
