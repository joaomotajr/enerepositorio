package br.com.eneeyes.controllers.api.dto.pagamentos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.eneeyes.controllers.api.dto.cieloCodeData.MeioCapturaDto;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.MotivoRejeicaoDto;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.OrigemAjusteDto;
import br.com.eneeyes.controllers.api.dto.vendas.HistoricoVendaCieloDto;


public class ComprovanteVendaCieloDto implements Serializable {
	
	private Long id;
	private String uid;
	private HistoricoVendaCieloDto historicoVendaCieloDto;
	private List<ResumoOperacaoCieloDto> resumos;
	private Integer tipoRegistro;
	private Integer estabelecimentoSubmissor;
	private Integer numeroRO;
	private String numeroCartaoTruncado;
	private Date dataVendaAjuste;
	private BigDecimal valorCompraOuParcela;
	private Integer parcela;	
	private Integer totalParcelas;
	private MotivoRejeicaoDto motivoRejeicao;
	private String codigoAutorizacao;
	private String tid;
	private Integer nsuDoc;
	private BigDecimal valorComplementar;
	private Integer digCartao;
	private BigDecimal valorTotalVendaCasoParceladoLoja;
	private BigDecimal valorProximaParcela;
	private Integer numeroNotaFiscal;
	private Integer identificacaoCartaoEmitidoExterior;
	private Integer numeroLogicoTerminal;
	private String identificadorTaxaEmbarqueOuValorEntrada;
	private String referenciaOuCodigoPedido;
	private Date horaTransacao;
	private String numeroUnicoTransacao;
	private String identificarCieloPremia;
	private String usoCielo;
	
	public ComprovanteVendaCieloDto() {
	}

	public ComprovanteVendaCieloDto(Long id, String uid, HistoricoVendaCieloDto historicoVendaCieloDto, List<ResumoOperacaoCieloDto> resumos, Integer estabelecimentoSubmissor, Integer numeroRO,
			String numeroCartaoTruncado, Date dataVendaAjuste, BigDecimal valorCompraOuParcela, Integer parcela,
			Integer totalParcelas, MotivoRejeicaoDto motivoRejeicao, String codigoAutorizacao, String tid, Integer nsuDoc,
			BigDecimal valorComplementar, Integer digCartao, BigDecimal valorTotalVendaCasoParceladoLoja,
			BigDecimal valorProximaParcela, Integer numeroNotaFiscal, Integer identificacaoCartaoEmitidoExterior,
			Integer numeroLogicoTerminal, String identificadorTaxaEmbarqueOuValorEntrada,
			String referenciaOuCodigoPedido, Date horaTransacao, String numeroUnicoTransacao,
			String identificarCieloPremia, String usoCielo) {
		super();
		this.id = id;
		this.uid = uid;
		this.historicoVendaCieloDto = historicoVendaCieloDto;
		this.resumos = resumos;
		this.estabelecimentoSubmissor = estabelecimentoSubmissor;
		this.numeroRO = numeroRO;
		this.numeroCartaoTruncado = numeroCartaoTruncado;
		this.dataVendaAjuste = dataVendaAjuste;
		this.valorCompraOuParcela = valorCompraOuParcela;
		this.parcela = parcela;
		this.totalParcelas = totalParcelas;
		this.motivoRejeicao = motivoRejeicao;
		this.codigoAutorizacao = codigoAutorizacao;
		this.tid = tid;
		this.nsuDoc = nsuDoc;
		this.valorComplementar = valorComplementar;
		this.digCartao = digCartao;
		this.valorTotalVendaCasoParceladoLoja = valorTotalVendaCasoParceladoLoja;
		this.valorProximaParcela = valorProximaParcela;
		this.numeroNotaFiscal = numeroNotaFiscal;
		this.identificacaoCartaoEmitidoExterior = identificacaoCartaoEmitidoExterior;
		this.numeroLogicoTerminal = numeroLogicoTerminal;
		this.identificadorTaxaEmbarqueOuValorEntrada = identificadorTaxaEmbarqueOuValorEntrada;
		this.referenciaOuCodigoPedido = referenciaOuCodigoPedido;
		this.horaTransacao = horaTransacao;
		this.numeroUnicoTransacao = numeroUnicoTransacao;
		this.identificarCieloPremia = identificarCieloPremia;
		this.usoCielo = usoCielo;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public HistoricoVendaCieloDto getHistoricoVendaCieloDto() {
		return historicoVendaCieloDto;
	}
	public void setHistoricoVendaCieloDto(HistoricoVendaCieloDto historicoVendaCieloDto) {
		this.historicoVendaCieloDto = historicoVendaCieloDto;
	}
	public Integer getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(Integer tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public Integer getEstabelecimentoSubmissor() {
		return estabelecimentoSubmissor;
	}
	public void setEstabelecimentoSubmissor(Integer estabelecimentoSubmissor) {
		this.estabelecimentoSubmissor = estabelecimentoSubmissor;
	}
	public Integer getNumeroRO() {
		return numeroRO;
	}
	public void setNumeroRO(Integer numeroRO) {
		this.numeroRO = numeroRO;
	}
	public String getNumeroCartaoTruncado() {
		return numeroCartaoTruncado;
	}
	public void setNumeroCartaoTruncado(String numeroCartaoTruncado) {
		this.numeroCartaoTruncado = numeroCartaoTruncado;
	}
	public Date getDataVendaAjuste() {
		return dataVendaAjuste;
	}
	public void setDataVendaAjuste(Date dataVendaAjuste) {
		this.dataVendaAjuste = dataVendaAjuste;
	}
	public BigDecimal getValorCompraOuParcela() {
		return valorCompraOuParcela;
	}
	public void setValorCompraOuParcela(BigDecimal valorCompraOuParcela) {
		this.valorCompraOuParcela = valorCompraOuParcela;
	}
	public Integer getParcela() {
		return parcela;
	}
	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}
	public Integer getTotalParcelas() {
		return totalParcelas;
	}
	public void setTotalParcelas(Integer totalParcelas) {
		this.totalParcelas = totalParcelas;
	}
	public MotivoRejeicaoDto getMotivoRejeicao() {
		return motivoRejeicao;
	}
	public void setMotivoRejeicao(MotivoRejeicaoDto motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}
	public String getCodigoAutorizacao() {
		return codigoAutorizacao;
	}
	public void setCodigoAutorizacao(String codigoAutorizacao) {
		this.codigoAutorizacao = codigoAutorizacao;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public Integer getNsuDoc() {
		return nsuDoc;
	}
	public void setNsuDoc(Integer nsuDoc) {
		this.nsuDoc = nsuDoc;
	}
	public BigDecimal getValorComplementar() {
		return valorComplementar;
	}
	public void setValorComplementar(BigDecimal valorComplementar) {
		this.valorComplementar = valorComplementar;
	}
	public Integer getDigCartao() {
		return digCartao;
	}
	public void setDigCartao(Integer digCartao) {
		this.digCartao = digCartao;
	}
	public BigDecimal getValorTotalVendaCasoParceladoLoja() {
		return valorTotalVendaCasoParceladoLoja;
	}
	public void setValorTotalVendaCasoParceladoLoja(BigDecimal valorTotalVendaCasoParceladoLoja) {
		this.valorTotalVendaCasoParceladoLoja = valorTotalVendaCasoParceladoLoja;
	}
	public BigDecimal getValorProximaParcela() {
		return valorProximaParcela;
	}
	public void setValorProximaParcela(BigDecimal valorProximaParcela) {
		this.valorProximaParcela = valorProximaParcela;
	}
	public Integer getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}
	public void setNumeroNotaFiscal(Integer numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}
	public Integer getIdentificacaoCartaoEmitidoExterior() {
		return identificacaoCartaoEmitidoExterior;
	}
	public void setIdentificacaoCartaoEmitidoExterior(Integer identificacaoCartaoEmitidoExterior) {
		this.identificacaoCartaoEmitidoExterior = identificacaoCartaoEmitidoExterior;
	}
	public Integer getNumeroLogicoTerminal() {
		return numeroLogicoTerminal;
	}
	public void setNumeroLogicoTerminal(Integer numeroLogicoTerminal) {
		this.numeroLogicoTerminal = numeroLogicoTerminal;
	}
	public String getIdentificadorTaxaEmbarqueOuValorEntrada() {
		return identificadorTaxaEmbarqueOuValorEntrada;
	}
	public void setIdentificadorTaxaEmbarqueOuValorEntrada(String identificadorTaxaEmbarqueOuValorEntrada) {
		this.identificadorTaxaEmbarqueOuValorEntrada = identificadorTaxaEmbarqueOuValorEntrada;
	}
	public String getReferenciaOuCodigoPedido() {
		return referenciaOuCodigoPedido;
	}
	public void setReferenciaOuCodigoPedido(String referenciaOuCodigoPedido) {
		this.referenciaOuCodigoPedido = referenciaOuCodigoPedido;
	}
	public Date getHoraTransacao() {
		return horaTransacao;
	}
	public void setHoraTransacao(Date horaTransacao) {
		this.horaTransacao = horaTransacao;
	}
	public String getNumeroUnicoTransacao() {
		return numeroUnicoTransacao;
	}
	public void setNumeroUnicoTransacao(String numeroUnicoTransacao) {
		this.numeroUnicoTransacao = numeroUnicoTransacao;
	}
	public String getIdentificarCieloPremia() {
		return identificarCieloPremia;
	}
	public void setIdentificarCieloPremia(String identificarCieloPremia) {
		this.identificarCieloPremia = identificarCieloPremia;
	}
	public String getUsoCielo() {
		return usoCielo;
	}
	public void setUsoCielo(String usoCielo) {
		this.usoCielo = usoCielo;
	}
	public List<ResumoOperacaoCieloDto> getResumos() {
		return resumos;
	}
	public List<ResumoOperacaoCieloDto> getResumosEnvioBanco() {
		List<ResumoOperacaoCieloDto> resumos = new ArrayList<ResumoOperacaoCieloDto>();
		for (ResumoOperacaoCieloDto resumoOperacaoCieloDto : this.resumos) {
			if (resumoOperacaoCieloDto.getDataEnvioBanco() != null) {
				resumos.add(resumoOperacaoCieloDto);
			}
		}
		return resumos;
	}
	public void setResumos(List<ResumoOperacaoCieloDto> resumos) {
		this.resumos = resumos;
	}
	public BigDecimal getTaxa() {
		ResumoOperacaoCieloDto resumo = getPrimeiroResumo();
		if (resumo != null) {
			return resumo.getTaxaComissao();
		}
		return null;
	}
	public String getTipoVenda() {
		ResumoOperacaoCieloDto resumo = getPrimeiroResumo();
		if (resumo != null) {
			return resumo.getIdentificadorProduto().getTipo();
		}
		return null;
	}
	public Date getDataEnvioBanco() {
		ResumoOperacaoCieloDto resumo = getPrimeiroResumo();
		if (resumo != null) {
			return resumo.getDataEnvioBanco();
		}
		return null;
	}
	public Date getDataPrevistaPagamento() {
		ResumoOperacaoCieloDto resumo = getPrimeiroResumo();
		if (resumo != null) {
			return resumo.getDataPrevistaPagamento();
		}
		return null;
	}
	public OrigemAjusteDto getOrigemAjuste() {
		ResumoOperacaoCieloDto resumo = getPrimeiroResumo();
		if (resumo != null) {
			return resumo.getOrigemAjuste();
		}
		return null;
	}
	public MeioCapturaDto getMeioCaptura() {
		ResumoOperacaoCieloDto resumo = getPrimeiroResumo();
		if (resumo != null) {
			return resumo.getMeioCaptura();
		}
		return null;
	}
	private ResumoOperacaoCieloDto getPrimeiroResumo() {
		if (this.resumos != null) {
			Collections.sort(this.resumos);
			for (ResumoOperacaoCieloDto resumoOperacaoCieloDto : this.resumos) {
				return resumoOperacaoCieloDto;
			}
		}
		return null;
	}
}