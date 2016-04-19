package br.com.eneeyes.model.repository.vendas;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.archetype.model.pagamentos.ResumoOperacaoRede;

/**
 * Created by Bruno on 02/03/15.
 */
public interface ResumoOperacaoRedeRepository  extends JpaRepository<ResumoOperacaoRede, Long> {
	
	@Query("select r from ResumoOperacaoRede r where r.numeroUnico like ?1% and r.parcela = ?2")
    public ResumoOperacaoRede findByNumeroUnicoParcela(String chave, Integer parcela);
	
	@Query("select r from ResumoOperacaoRede r where r.numeroUnico like ?1%")
    public List<ResumoOperacaoRede> findByNumeroUnico(String chave);
 
	@Query("select r from ResumoOperacaoRede r where r.dataEnvioBanco between ?1 and ?2 and r.statusPagamento.cod = 01 and r.valorLiquido > 0 order by r.dataEnvioBanco desc")
    public Set<ResumoOperacaoRede> findContaCorrenteRecebido(Date dataInicio, Date dataFim);
	
	@Query("select r from ResumoOperacaoRede r where r.dataEnvioBanco = ?1 and r.statusPagamento.cod = 01 and r.valorLiquido > 0 order by r.dataEnvioBanco desc")
    public Set<ResumoOperacaoRede> findContaCorrenteRecebido(Date dataInicio);
	
	@Query("select r from ResumoOperacaoRede r where r.dataPrevistaPagamento between ?1 and ?2 and r.statusPagamento.cod != 01 order by r.dataPrevistaPagamento desc")
    public Set<ResumoOperacaoRede> findContaCorrenteReceber(Date dataInicio, Date dataFim);
	
	@Query("select r from ResumoOperacaoRede r where r.dataPrevistaPagamento = ?1 and r.statusPagamento.cod != 01 order by r.dataPrevistaPagamento desc")
    public Set<ResumoOperacaoRede> findContaCorrenteReceber(Date dataInicio);
	
}
