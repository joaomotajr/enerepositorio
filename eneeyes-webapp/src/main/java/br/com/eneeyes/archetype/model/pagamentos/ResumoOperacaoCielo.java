package br.com.eneeyes.archetype.model.pagamentos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.eneeyes.archetype.model.cieloCodeData.CodigoBandeira;
import br.com.eneeyes.archetype.model.cieloCodeData.CodigoProduto;
import br.com.eneeyes.archetype.model.cieloCodeData.MeioCaptura;
import br.com.eneeyes.archetype.model.cieloCodeData.OrigemAjuste;
import br.com.eneeyes.archetype.model.cieloCodeData.StatusPagamento;
import br.com.eneeyes.archetype.model.cieloCodeData.TipoTransacao;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.CodigoBandeiraDto;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.CodigoProdutoDto;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.MeioCapturaDto;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.OrigemAjusteDto;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.StatusPagamentoDto;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.TipoTransacaoDto;
import br.com.eneeyes.controllers.api.dto.pagamentos.ResumoOperacaoCieloDto;

@Entity
@Table(name="resumo_operacao_cielo")
public class ResumoOperacaoCielo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_")
	private Long id;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="resumos")  	
	private List<ComprovanteVendaCielo> comprovantes;
	
	@Column(name="ESTABELECIMENTO_SUBMISSOR_")	
	private Integer estabelecimentoSubmissor;
	
	@Column(name="NUMERO_RO_")
	private Integer numeroRO;
	
	@Column(name="PARCELA_")
	private Integer parcela;
	
	@Column(name="FILLER_")
	private String filler;
	
	@Column(name="PLANO_")
	private String plano;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TIPO_TRANSACAO_")
	private TipoTransacao tipoTransacao;
	
	@Column(name="DATA_APRESENTACAO_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataApresentacao;
	
	@Column(name="DATA_PREVISTA_PAGAMENTO_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPrevistaPagamento;
	
	@Column(name="DATA_ENVIO_BANCO_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEnvioBanco;
	
	@Column(name="VALOR_BRUTO_")
	private BigDecimal valorBruto;
	
	@Column(name="VALOR_COMISSAO_")
	private BigDecimal valorComissao;
	
	@Column(name="VALOR_REJEITADO_")
	private BigDecimal valorRejeitado;
	
	@Column(name="VALOR_LIQUIDO_")
	private BigDecimal valorLiquido;
	
	@Column(name="BANCO_")
	private Integer banco;
	
	@Column(name="AGENCIA_")
	private Integer agencia;
	
	@Column(name="CONTA_CORRENTE_")
	private String contaCorrente;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="STATUS_PAGAMENTO_")
	private StatusPagamento statusPagamento;
	
	@Column(name="QTDE_CV_ACEITO_")
	private Integer quantidadeCVAceitos;
	
	@Column(name="QTDE_CV_REJEITADO_")
	private Integer quantidadeCvsRejeitados;
	
	@Column(name="IDENT_REVENDA_ACELERACAO_")
	private String identificadorRevendaAceleracao;
	
	@Column(name="DATA_CAPTURA_TRANS_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCapturaTransacao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ORIGEM_AJUSTE_")
	private OrigemAjuste origemAjuste;
	
	@Column(name="VALOR_COMPLEMENTAR_")
	private BigDecimal valorComplementar;
	
	@Column(name="IDENT_PRODUTO_FINANCEIRO_")
	private String identificadorProdutoFinanceiro;
	
	@Column(name="NUMERO_OPERACAO_FINANCEIRA_")
	private Integer numeroOperacaoFinanceira;
	
	@Column(name="VALOR_BRUTO_ANTECIPADO_")
	private BigDecimal valorBrutoAntecipado;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="COD_BANDEIRA_")
	private CodigoBandeira codigoBandeira;
	
	@Column(name="NUMERO_UNICO_")
	private String numeroUnico;
	
	@Column(name="TAXA_COMISSAO_")
	private BigDecimal taxaComissao;
	
	@Column(name="TARIFA_")
	private BigDecimal tarifa;
	
	@Column(name="TAXA_GARANTIA_")
	private BigDecimal taxaGarantia;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="MEIO_CAPTURA_")
	private MeioCaptura meioCaptura;
	
	@Column(name="NUM_LOGICO_TERMINAL_")
	private Integer numeroLogicoTerminal;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IDENT_PDUTO_")
	private CodigoProduto identificadorProduto;
	
	@Column(name="USO_CIELO_")
	private String usoCielo;
	
	public ResumoOperacaoCielo(Long id, Integer estabelecimentoSubmissor,
			Integer numeroRO, Integer parcela, String filler, String plano, TipoTransacao tipoTransacao,
			Date dataApresentacao, Date dataPrevistaPagamento, Date dataEnvioBanco, BigDecimal valorBruto,
			BigDecimal valorComissao, BigDecimal valorRejeitado, BigDecimal valorLiquido, Integer banco,
			Integer agencia, String contaCorrente, StatusPagamento statusPagamento, Integer quantidadeCVAceitos,
			Integer quantidadeCvsRejeitados, String identificadorRevendaAceleracao, Date dataCapturaTransacao,
			OrigemAjuste origemAjuste, BigDecimal valorComplementar, String identificadorProdutoFinanceiro,
			Integer numeroOperacaoFinanceira, BigDecimal valorBrutoAntecipado, CodigoBandeira codigoBandeira,
			String numeroUnico, BigDecimal taxaComissao, BigDecimal tarifa, BigDecimal taxaGarantia,
			MeioCaptura meioCaptura, Integer numeroLogicoTerminal, CodigoProduto identificadorProduto, String usoCielo) {
		super();
		this.id = id;
		this.estabelecimentoSubmissor = estabelecimentoSubmissor;
		this.numeroRO = numeroRO;
		this.parcela = parcela;
		this.filler = filler;
		this.plano = plano;
		this.tipoTransacao = tipoTransacao;
		this.dataApresentacao = dataApresentacao;
		this.dataPrevistaPagamento = dataPrevistaPagamento;
		this.dataEnvioBanco = dataEnvioBanco;
		this.valorBruto = valorBruto;
		this.valorComissao = valorComissao;
		this.valorRejeitado = valorRejeitado;
		this.valorLiquido = valorLiquido;
		this.banco = banco;
		this.agencia = agencia;
		this.contaCorrente = contaCorrente;
		this.statusPagamento = statusPagamento;
		this.quantidadeCVAceitos = quantidadeCVAceitos;
		this.quantidadeCvsRejeitados = quantidadeCvsRejeitados;
		this.identificadorRevendaAceleracao = identificadorRevendaAceleracao;
		this.dataCapturaTransacao = dataCapturaTransacao;
		this.origemAjuste = origemAjuste;
		this.valorComplementar = valorComplementar;
		this.identificadorProdutoFinanceiro = identificadorProdutoFinanceiro;
		this.numeroOperacaoFinanceira = numeroOperacaoFinanceira;
		this.valorBrutoAntecipado = valorBrutoAntecipado;
		this.codigoBandeira = codigoBandeira;
		this.numeroUnico = numeroUnico;
		this.taxaComissao = taxaComissao;
		this.tarifa = tarifa;
		this.taxaGarantia = taxaGarantia;
		this.meioCaptura = meioCaptura;
		this.numeroLogicoTerminal = numeroLogicoTerminal;
		this.identificadorProduto = identificadorProduto;
		this.usoCielo = usoCielo;
	}
	
	public ResumoOperacaoCielo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<ComprovanteVendaCielo> getComprovantes() {
		return comprovantes;
	}

	public void setComprovantes(List<ComprovanteVendaCielo> comprovantes) {
		this.comprovantes = comprovantes;
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

	public void setNumero(Integer numeroRO) {
		this.numeroRO = numeroRO;
	}

	public Integer getParcela() {
		return parcela;
	}

	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public Date getDataApresentacao() {
		return dataApresentacao;
	}

	public void setDataApresentacao(Date dataApresentacao) {
		this.dataApresentacao = dataApresentacao;
	}

	public Date getDataPrevistaPagamento() {
		return dataPrevistaPagamento;
	}

	public void setDataPrevistaPagamento(Date dataPrevistaPagamento) {
		this.dataPrevistaPagamento = dataPrevistaPagamento;
	}

	public Date getDataEnvioBanco() {
		return dataEnvioBanco;
	}

	public void setDataEnvioBanco(Date dataEnvioBanco) {
		this.dataEnvioBanco = dataEnvioBanco;
	}

	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}

	public BigDecimal getValorComissao() {
		return valorComissao;
	}

	public void setValorComissao(BigDecimal valorComissao) {
		this.valorComissao = valorComissao;
	}

	public BigDecimal getValorRejeitado() {
		return valorRejeitado;
	}

	public void setValorRejeitado(BigDecimal valorRejeitado) {
		this.valorRejeitado = valorRejeitado;
	}

	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public Integer getBanco() {
		return banco;
	}

	public void setBanco(Integer banco) {
		this.banco = banco;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public String getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public Integer getQuantidadeCVAceitos() {
		return quantidadeCVAceitos;
	}

	public void setQuantidadeCVAceitos(Integer quantidadeCVAceitos) {
		this.quantidadeCVAceitos = quantidadeCVAceitos;
	}

	public Integer getQuantidadeCvsRejeitados() {
		return quantidadeCvsRejeitados;
	}

	public void setQuantidadeCvsRejeitados(Integer quantidadeCvsRejeitados) {
		this.quantidadeCvsRejeitados = quantidadeCvsRejeitados;
	}

	public String getIdentificadorRevendaAceleracao() {
		return identificadorRevendaAceleracao;
	}

	public void setIdentificadorRevendaAceleracao(String identificadorRevendaAceleracao) {
		this.identificadorRevendaAceleracao = identificadorRevendaAceleracao;
	}

	public Date getDataCapturaTransacao() {
		return dataCapturaTransacao;
	}

	public void setDataCapturaTransacao(Date dataCapturaTransacao) {
		this.dataCapturaTransacao = dataCapturaTransacao;
	}

	public OrigemAjuste getOrigemAjuste() {
		return origemAjuste;
	}

	public void setOrigemAjuste(OrigemAjuste origemAjuste) {
		this.origemAjuste = origemAjuste;
	}

	public BigDecimal getValorComplementar() {
		return valorComplementar;
	}

	public void setValorComplementar(BigDecimal valorComplementar) {
		this.valorComplementar = valorComplementar;
	}

	public String getIdentificadorProdutoFinanceiro() {
		return identificadorProdutoFinanceiro;
	}

	public void setIdentificadorProdutoFinancei(String identificadorProdutoFinanceira) {
		this.identificadorProdutoFinanceiro = identificadorProdutoFinanceira;
	}

	public Integer getNumeroOperacaoFinanceira() {
		return numeroOperacaoFinanceira;
	}

	public void setNumeroOperacaoFinanceira(Integer numeroOperacaoFinanceira) {
		this.numeroOperacaoFinanceira = numeroOperacaoFinanceira;
	}

	public BigDecimal getValorBrutoAntecipado() {
		return valorBrutoAntecipado;
	}

	public void setValorBrutoAntecipado(BigDecimal valorBrutoAntecipado) {
		this.valorBrutoAntecipado = valorBrutoAntecipado;
	}

	public CodigoBandeira getCodigoBandeira() {
		return codigoBandeira;
	}

	public void setCodigoBandeira(CodigoBandeira codigoBandeira) {
		this.codigoBandeira = codigoBandeira;
	}

	public String getNumeroUnico() {
		return numeroUnico;
	}

	public void setNumeroUnico(String numeroUnico) {
		this.numeroUnico = numeroUnico;
	}

	public BigDecimal getTaxaComissao() {
		return taxaComissao;
	}

	public void setTaxaComissao(BigDecimal taxaComissao) {
		this.taxaComissao = taxaComissao;
	}

	public BigDecimal getTarifa() {
		return tarifa;
	}

	public void setTarifa(BigDecimal tarifa) {
		this.tarifa = tarifa;
	}

	public BigDecimal getTaxaGarantia() {
		return taxaGarantia;
	}

	public void setTaxaGarantia(BigDecimal taxaGarantia) {
		this.taxaGarantia = taxaGarantia;
	}

	public MeioCaptura getMeioCaptura() {
		return meioCaptura;
	}

	public void setMeioCaptura(MeioCaptura meioCaptura) {
		this.meioCaptura = meioCaptura;
	}

	public Integer getNumeroLogicoTerminal() {
		return numeroLogicoTerminal;
	}

	public void setNumeroLogicoTerminal(Integer numeroLogicoTerminal) {
		this.numeroLogicoTerminal = numeroLogicoTerminal;
	}

	public CodigoProduto getIdentificadorProduto() {
		return identificadorProduto;
	}

	public void setIdentificadorProduto(CodigoProduto identificadorProduto) {
		this.identificadorProduto = identificadorProduto;
	}

	public String getUsoCielo() {
		return usoCielo;
	}

	public void setUsoCielo(String usoCielo) {
		this.usoCielo = usoCielo;
	}
	
	public static List<ResumoOperacaoCielo> fromListaResumoOperacaoDto(List<ResumoOperacaoCieloDto> listaResumoOperacaoDto) {
		List<ResumoOperacaoCielo> lista = new ArrayList<ResumoOperacaoCielo>();
		for (ResumoOperacaoCieloDto resumoOperacaoDto : listaResumoOperacaoDto) {
			lista.add(fromResumoVendaCieloDto(resumoOperacaoDto));
		}
		
		return lista;
	}
	
	public static List<ResumoOperacaoCieloDto> toListaResumoOperacaoDto(List<ResumoOperacaoCielo> listaResumoOperacao) {
		List<ResumoOperacaoCieloDto> lista = new ArrayList<ResumoOperacaoCieloDto>();
		for (ResumoOperacaoCielo resumoOperacao : listaResumoOperacao) {
			lista.add(resumoOperacao.toResumoOperacaoCieloDto());
		}
		
		return lista;
	}

	public static ResumoOperacaoCielo fromResumoVendaCieloDto(ResumoOperacaoCieloDto dto) {
		
		TipoTransacao tipoTransacao = null;
		OrigemAjuste origemAjuste = null;
		CodigoBandeira codigoBandeira = null;
		MeioCaptura meioCaptura = null;
		CodigoProduto codigoPduto = null;
		StatusPagamento status = null;
		
		if (dto.getOrigemAjuste() != null) {
			origemAjuste = OrigemAjuste.fromOrigemAjusteDto(dto.getOrigemAjuste());
		}
		
		if (dto.getMeioCaptura() != null) {
			meioCaptura = MeioCaptura.fromMeioCapturaDto(dto.getMeioCaptura());
		}
		
		if (dto.getCodigoBandeira() != null) {
			codigoBandeira = CodigoBandeira.fromCodigoBandeiraDto(dto.getCodigoBandeira());
		}
		
		if (dto.getIdentificadorProduto() != null) {
			codigoPduto = CodigoProduto.fromCodigoProdutoDto(dto.getIdentificadorProduto());
		}
		
		if (dto.getTipoTransacao() != null) {
			tipoTransacao = TipoTransacao.fromTipoTransacaoDto(dto.getTipoTransacao());
		}
		
		if (dto.getStatusPagamento() != null) {
			status = StatusPagamento.fromStatusPagamentoDto(dto.getStatusPagamento());
		}
		
		return new ResumoOperacaoCielo(dto.getId(),
			dto.getEstabelecimentoSubmissor(),
			dto.getNumeroRO(), 
			dto.getParcela(), 
			dto.getFiller(),
			dto.getPlano(),
			tipoTransacao,
			dto.getDataApresentacao(), 
			dto.getDataPrevistaPagamento(),
			dto.getDataEnvioBanco(),
			dto.getValorBruto(),
			dto.getValorComissao(),
			dto.getValorRejeitado(),
			dto.getValorLiquido(),
			dto.getBanco(),
			dto.getAgencia(),
			dto.getContaCorrente(),
			status,
			dto.getQuantidadeCVAceitos(),
			dto.getQuantidadeCvsRejeitados(),
			dto.getIdentificarRevendaAceleracao(),
			dto.getDataCapturaTransacao(),
			origemAjuste, 
			dto.getValorComplementar(),
			dto.getIdentificadorProdutoFinanceiro(), 
			dto.getNumeroOperacaoFinanceira(),
			dto.getValorBrutoAntecipado(),
			codigoBandeira,
			dto.getNumeroUnicoRO(),
			dto.getTaxaComissao(),
			dto.getTarifa(),
			dto.getTaxaGarantia(),
			meioCaptura, 
			dto.getNumeroLogicoTerminal(), 
			codigoPduto, 
			dto.getUsoCielo());
	}
	
	public ResumoOperacaoCieloDto toResumoOperacaoCieloDto() {
		OrigemAjusteDto origemAjuste = null;
		if (this.origemAjuste != null) {
			origemAjuste = this.origemAjuste.toOrigemAjusteDto();
		}
		
		MeioCapturaDto meioCaptura = null;
		if (this.meioCaptura != null) {
			meioCaptura = this.meioCaptura.toMeioCapturaDto();
		}
		
		CodigoBandeiraDto codigoBandeira = null;
		if (this.codigoBandeira != null) {
			codigoBandeira = this.codigoBandeira.toCodigoBandeiraDto();
		}
		
		CodigoProdutoDto codigoProduto = null;
		if (this.identificadorProduto != null) {
			codigoProduto = this.identificadorProduto.toCodigoProdutoDto();
		}
		
		TipoTransacaoDto tipoTransacao = null;
		if (this.tipoTransacao != null) {
			tipoTransacao = this.tipoTransacao.toTipoTransacaoDto();
		}
		
		StatusPagamentoDto status = null;
		if (this.statusPagamento != null) {
			status = this.statusPagamento.toStatusPagamentoDto();
		}
		
        return new ResumoOperacaoCieloDto(this.id,
			this.estabelecimentoSubmissor,
			this.numeroRO,
			this.parcela,
			this.filler,
			this.plano,
			tipoTransacao,
			this.dataApresentacao,
			this.dataPrevistaPagamento,
			this.dataEnvioBanco,
			this.valorBruto,
			this.valorComissao,
			this.valorRejeitado,
			this.valorLiquido,
			this.banco,
			this.agencia,
			this.contaCorrente,
			status,
			this.quantidadeCVAceitos,
			this.quantidadeCvsRejeitados,
			this.identificadorRevendaAceleracao,
			this.dataCapturaTransacao,
			origemAjuste,
			this.valorComplementar,
			this.identificadorProdutoFinanceiro,
			this.numeroOperacaoFinanceira,
			this.valorBrutoAntecipado,
			codigoBandeira,
			this.numeroUnico,
			this.taxaComissao,
			this.tarifa,
			this.taxaGarantia,
			meioCaptura,
			this.numeroLogicoTerminal,
			codigoProduto,
			this.usoCielo);
    }
	
	@Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof ResumoOperacaoCielo)
        {
            sameSame = this.id == ((ResumoOperacaoCielo) object).getId();
        }

        return sameSame;
    }
}