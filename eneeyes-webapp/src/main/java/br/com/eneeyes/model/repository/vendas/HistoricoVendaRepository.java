package br.com.eneeyes.model.repository.vendas;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.archetype.model.vendas.HistoricoVenda;

/**
 * Created by Bruno on 02/03/15.
 */
public interface HistoricoVendaRepository  extends JpaRepository<HistoricoVenda, Long> {
	
	@Query("select h from HistoricoVenda h where dataMovimento = ?1 and tef = ?2")
    public HistoricoVenda findByDataMovimento(Date dataMovimento, Boolean isOrigemTef);
    
}
