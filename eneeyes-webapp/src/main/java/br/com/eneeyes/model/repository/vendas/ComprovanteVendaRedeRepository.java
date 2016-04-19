package br.com.eneeyes.model.repository.vendas;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.archetype.model.pagamentos.ComprovanteVendaRede;

/**
 * Created by Bruno on 02/03/15.
 */
public interface ComprovanteVendaRedeRepository  extends JpaRepository<ComprovanteVendaRede, Long> {
    
	@Query("select c from ComprovanteVendaRede c where c.dataVendaAjuste = ?1 and c.nsuDoc = ?2 and c.valorCompraOuParcela = ?3")
    public ComprovanteVendaRede findByDataNsuValor(Date dataVenda, Integer nsu, BigDecimal valor);
	
	@Query("select c from ComprovanteVendaRede c where c.dataVendaAjuste = ?1 and c.nsuDoc = ?2 and c.valorTotalVendaCasoParceladoLoja = ?3")
    public ComprovanteVendaRede findByDataNsuValorTotalVendaParcelada(Date dataVenda, Integer nsu, BigDecimal valor);
	
	@Query("select c from ComprovanteVendaRede c where c.uid = ?1 and c.valorCompraOuParcela > 0")
    public List<ComprovanteVendaRede> findByUid(String uid);
	
}
