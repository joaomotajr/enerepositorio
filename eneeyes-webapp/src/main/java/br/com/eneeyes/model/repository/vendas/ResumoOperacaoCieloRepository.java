package br.com.eneeyes.model.repository.vendas;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.archetype.model.pagamentos.ResumoOperacaoCielo;

/**
 * Created by Bruno on 02/03/15.
 */
public interface ResumoOperacaoCieloRepository  extends JpaRepository<ResumoOperacaoCielo, Long> {
	
	@Query("select r from ResumoOperacaoCielo r where r.numeroUnico like ?1% and r.parcela = ?2")
    public ResumoOperacaoCielo findByNumeroUnicoParcela(String chave, Integer parcela);
	
	@Query("select r from ResumoOperacaoCielo r where r.numeroUnico like ?1%")
    public List<ResumoOperacaoCielo> findByNumeroUnico(String chave);
 
	@Query("select r from ResumoOperacaoCielo r where r.dataEnvioBanco between ?1 and ?2 and r.statusPagamento.cod = 01 and r.valorLiquido > 0 order by r.dataEnvioBanco desc")
    public Set<ResumoOperacaoCielo> findContaCorrenteRecebido(Date dataInicio, Date dataFim);
	
	@Query("select r from ResumoOperacaoCielo r where r.dataEnvioBanco = ?1 and r.statusPagamento.cod = 01 and r.valorLiquido > 0 order by r.dataEnvioBanco desc")
    public Set<ResumoOperacaoCielo> findContaCorrenteRecebido(Date dataInicio);
	
	@Query("select r from ResumoOperacaoCielo r where r.dataPrevistaPagamento between ?1 and ?2 and r.statusPagamento.cod != 01 order by r.dataPrevistaPagamento desc")
    public Set<ResumoOperacaoCielo> findContaCorrenteReceber(Date dataInicio, Date dataFim);
	
	@Query("select r from ResumoOperacaoCielo r where r.dataPrevistaPagamento = ?1 and r.statusPagamento.cod != 01 order by r.dataPrevistaPagamento desc")
    public Set<ResumoOperacaoCielo> findContaCorrenteReceber(Date dataInicio);
	
	@Modifying
    @Transactional
    @Query("update ResumoOperacaoCielo r set r.dataEnvioBanco = ?1, r.statusPagamento.cod = ?2 where r.id = ?3")
    public void updateDataStatusBanco(Date dateEnvioBanco, String status, Long id);
	
}
