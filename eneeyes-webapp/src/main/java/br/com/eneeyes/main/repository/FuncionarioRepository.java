package br.com.eneeyes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.Funcionario;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	public Funcionario findByMatricula(String matricula);
	
	public Funcionario findByUid(Long uid);	
	
	public List<Funcionario> findByNomeContainingIgnoreCase(String nome);
	

}
