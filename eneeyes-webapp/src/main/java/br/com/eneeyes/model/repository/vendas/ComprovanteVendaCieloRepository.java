package br.com.eneeyes.model.repository.vendas;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.archetype.model.pagamentos.ComprovanteVendaCielo;

/**
 * Created by Junior on 02/03/15 .
 */
public interface ComprovanteVendaCieloRepository  extends JpaRepository<ComprovanteVendaCielo, Long> {
    
	@Query("select c from ComprovanteVendaCielo c where c.dataVendaAjuste = ?1 and c.nsuDoc = ?2 and c.valorCompraOuParcela = ?3")
    public ComprovanteVendaCielo findByDataNsuValor(Date dataVenda, Integer nsu, BigDecimal valor);
	
	@Query("select c from ComprovanteVendaCielo c where c.dataVendaAjuste = ?1 and c.nsuDoc = ?2 and c.valorTotalVendaCasoParceladoLoja = ?3")
    public ComprovanteVendaCielo findByDataNsuValorTotalVendaParcelada(Date dataVenda, Integer nsu, BigDecimal valor);
	
	@Query("select c from ComprovanteVendaCielo c where c.uid = ?1 and c.valorCompraOuParcela > 0")
    public List<ComprovanteVendaCielo> findByUid(String uid);
	
}
