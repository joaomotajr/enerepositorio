package br.com.eneeyes.model.repository.vendas;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.archetype.model.pagamentos.HistoricoVendaCielo;

/**
 * Created by Bruno on 02/03/15.
 */
public interface HistoricoVendaCieloRepository  extends JpaRepository<HistoricoVendaCielo, Long> {
	
	@Query("select h from HistoricoVendaCielo h where dataProcessamento = ?1 and sequencia = ?2 and opcaoExtrato.cod = ?3")
    public List<HistoricoVendaCielo> findBySequenciaExtrato(Date dataMovimento, Integer sequencia, String opcaoExtrato);
    
}
