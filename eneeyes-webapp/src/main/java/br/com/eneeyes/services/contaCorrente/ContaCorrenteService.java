package br.com.eneeyes.services.contaCorrente;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.eneeyes.archetype.model.pagamentos.ComprovanteVendaCielo;
import br.com.eneeyes.archetype.model.pagamentos.ResumoOperacaoCielo;
import br.com.eneeyes.controllers.api.dto.contaCorrente.ContaCorrenteDto;
import br.com.eneeyes.controllers.api.dto.contaCorrente.ContaCorrenteFiltroDto;
import br.com.eneeyes.controllers.api.dto.pagamentos.ComprovanteVendaCieloDto;
import br.com.eneeyes.controllers.api.result.ContaCorrenteResult;
import br.com.eneeyes.model.repository.vendas.ResumoOperacaoCieloRepository;

@Named
public class ContaCorrenteService {
	
	@Inject 
	EntityManager entityManager;
	
	@Inject
    ResumoOperacaoCieloRepository resumoOperacaoCieloRepository;
	
	public ContaCorrenteResult pesquisar(ContaCorrenteFiltroDto contaCorrenteFiltro) {
		ContaCorrenteResult result = new ContaCorrenteResult();
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		Date dataInicio = null;
		Date dataFim = null;
		try {
			dataInicio = (Date)formatter.parse(contaCorrenteFiltro.getDataInicial());
			dataFim = (Date)formatter.parse(contaCorrenteFiltro.getDataFinal());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (contaCorrenteFiltro.getStatus().equals("Recebido")) {
			result.setListValue(getListaRecebido(dataInicio, dataFim));
			
		} else if (contaCorrenteFiltro.getStatus().equals("Receber")) {
			result.setListValue(getListaReceber(dataInicio, dataFim));
			
		} else {
			Set<ContaCorrenteDto> listaResumo = new HashSet<ContaCorrenteDto>();
			
			listaResumo.addAll(getListaRecebido(dataInicio, dataFim));
			listaResumo.addAll(getListaReceber(dataInicio, dataFim));
			
			result.setListValue(listaResumo);
		}
		
        return result;
    }
	
	private Set<ContaCorrenteDto> getListaRecebido(Date dataInicio, Date dataFim) {
		Set<ResumoOperacaoCielo> listaResumoRecebido = null;
		if (dataInicio.equals(dataFim)) {
			listaResumoRecebido = resumoOperacaoCieloRepository.findContaCorrenteRecebido(dataInicio);
		} else {
			listaResumoRecebido = resumoOperacaoCieloRepository.findContaCorrenteRecebido(dataInicio, dataFim);
		}
		
		Date dataAnterior = null;
		BigDecimal total = BigDecimal.ZERO;
		ContaCorrenteDto contaCorrente = null;
		Set<ContaCorrenteDto> listaRetorno = new HashSet<ContaCorrenteDto>();
		for (ResumoOperacaoCielo resumoOperacaoCielo : listaResumoRecebido) {
			if (dataAnterior != null && dataAnterior.equals(resumoOperacaoCielo.getDataEnvioBanco())) {
				total = total.add(resumoOperacaoCielo.getValorLiquido());
			} else {
				contaCorrente = new ContaCorrenteDto();
				contaCorrente.setData(resumoOperacaoCielo.getDataEnvioBanco());
				contaCorrente.setStatus("Recebido");
				contaCorrente.setListaComprovantes(new ArrayList<ComprovanteVendaCieloDto>());
				total = resumoOperacaoCielo.getValorLiquido();
				listaRetorno.add(contaCorrente);
			}
			contaCorrente.addAll(ComprovanteVendaCielo.toListaComprovanteVendaDto(resumoOperacaoCielo.getComprovantes()));
			contaCorrente.setValor(total);
			dataAnterior = resumoOperacaoCielo.getDataEnvioBanco();
		}
		
		return listaRetorno;
	}
	
	private Set<ContaCorrenteDto> getListaReceber(Date dataInicio, Date dataFim) {
		Set<ResumoOperacaoCielo> listaResumoReceber = null;
		if (dataInicio.equals(dataFim)) {
			listaResumoReceber = resumoOperacaoCieloRepository.findContaCorrenteReceber(dataInicio);
		} else {
			listaResumoReceber = resumoOperacaoCieloRepository.findContaCorrenteReceber(dataInicio, dataFim);
		}

		Date dataAnterior = null;
		BigDecimal total = BigDecimal.ZERO;
		ContaCorrenteDto contaCorrente = null;
		Set<ContaCorrenteDto> listaRetorno = new HashSet<ContaCorrenteDto>();
		for (ResumoOperacaoCielo resumoOperacaoCielo : listaResumoReceber) {
			if (dataAnterior != null && dataAnterior.equals(resumoOperacaoCielo.getDataPrevistaPagamento())) {
				total = total.add(resumoOperacaoCielo.getValorLiquido());
			} else {
				contaCorrente = new ContaCorrenteDto();
				contaCorrente.setData(resumoOperacaoCielo.getDataPrevistaPagamento());
				contaCorrente.setStatus("A receber");
				contaCorrente.setListaComprovantes(new ArrayList<ComprovanteVendaCieloDto>());
				total = resumoOperacaoCielo.getValorLiquido();
				listaRetorno.add(contaCorrente);
			}
			contaCorrente.addAll(ComprovanteVendaCielo.toListaComprovanteVendaDto(resumoOperacaoCielo.getComprovantes()));
			contaCorrente.setValor(total);
			dataAnterior = resumoOperacaoCielo.getDataPrevistaPagamento();
		}
		
		return listaRetorno;
	}
}