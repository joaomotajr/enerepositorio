package br.com.eneeyes.model.repository.vendas;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.archetype.model.pagamentos.HistoricoVendaRede;

/**
 * Created by Bruno on 02/03/15.
 */
public interface HistoricoVendaRedeRepository  extends JpaRepository<HistoricoVendaRede, Long> {
	
	@Query("select h from HistoricoVendaRede h where dataProcessamento = ?1 and sequencia = ?2")
    public List<HistoricoVendaRede> findBySequenciaExtrato(Date dataMovimento, Integer sequencia);
    
}
