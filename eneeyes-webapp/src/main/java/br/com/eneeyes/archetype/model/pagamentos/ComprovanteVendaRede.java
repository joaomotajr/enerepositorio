package br.com.eneeyes.archetype.model.pagamentos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.eneeyes.archetype.model.cieloCodeData.MotivoRejeicao;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.MotivoRejeicaoDto;
import br.com.eneeyes.controllers.api.dto.pagamentos.ComprovanteVendaRedeDto;
import br.com.eneeyes.controllers.api.dto.pagamentos.ResumoOperacaoRedeDto;
import br.com.eneeyes.controllers.api.dto.vendas.HistoricoVendaRedeDto;

@Entity
@Table(name="comprovante_venda_rede")
public class ComprovanteVendaRede {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_")
	private Long id;
	
	@Column(name="UID_")
	private String uid;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="HISTORICO_ID_")
	private HistoricoVendaRede historicoVendaRede;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="resumo_comprovante", 
		joinColumns= @JoinColumn(name="COMPROVANTE_ID_", referencedColumnName="ID_"), 
		inverseJoinColumns= @JoinColumn(name="RESUMO_ID_", referencedColumnName="ID_"))
	private List<ResumoOperacaoRede> resumos = new ArrayList<ResumoOperacaoRede>();

	@Column(name="ESTABELECIMENTO_SUBMISSOR_")
	private Integer estabelecimentoSubmissor;
	
	@Column(name="NUMERO_RO_")
	private Integer numeroRO;
	
	@Column(name="NUM_CARTAO_TRUNCADO_")
	private String numeroCartaoTruncado;
	
	@Column(name="DATA_VENDA_AJUSTE_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVendaAjuste;
	
	@Column(name="VALOR_COMPRA_PARCELA_")
	private BigDecimal valorCompraOuParcela;
	
	@Column(name="PARCELA_")
	private Integer parcela;
	
	@Column(name="TOTAL_PARCELAS_")
	private Integer totalParcelas;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="MOTIVO_REJEICAO_")
	private MotivoRejeicao motivoRejeicao;
	
	@Column(name="COD_AUTORIZACAO_")
	private String codigoAutorizacao;
	
	@Column(name="TID_")
	private String tid;
	
	@Column(name="NSU_DOC_")
	private Integer nsuDoc;
	
	@Column(name="VALOR_COMPLEMENTAR_")
	private BigDecimal valorComplementar;
	
	@Column(name="DIG_CARTAO_")
	private Integer digCartao;
	
	@Column(name="VALOR_TOTAL_VENDA_PARC_")
	private BigDecimal valorTotalVendaCasoParceladoLoja;
	
	@Column(name="VALOR_PROXIMA_PARCELA_")
	private BigDecimal valorProximaParcela;
	
	@Column(name="NUM_NOTA_FISCAL_")
	private Integer numeroNotaFiscal;
	
	@Column(name="IDENT_CARTAO_EMITIDO_EXT_")
	private Integer identificacaoCartaoEmitidoExterior;
	
	@Column(name="NUM_LOGICO_TERMINAL_")
	private String numeroLogicoTerminal;
	
	@Column(name="IDENT_TAXA_EMBARQUE_VLR_ENTRADA_")
	private String identificadorTaxaEmbarqueOuValorEntrada;
	
	@Column(name="REF_COD_PEDIDO_")
	private String referenciaOuCodigoPedido;
	
	@Column(name="HORA_TRANSACAO_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaTransacao;
	
	@Column(name="NUM_UNICO_TRANSACAO_")
	private String numeroUnicoTransacao;
	
	@Column(name="IDENT_CIELO_PREMIA_")
	private String identificarRedePremia;
	
	@Column(name="USO_CIELO_")
	private String usoRede;
	
	public ComprovanteVendaRede() {
	}

	public ComprovanteVendaRede(Long id, String uid, List<ResumoOperacaoRede> resumos, Integer estabelecimentoSubmissor,
			Integer numeroRO, String numeroCartaoTruncado, Date dataVendaAjuste, BigDecimal valorCompraOuParcela,
			Integer parcela, Integer totalParcelas, MotivoRejeicao motivoRejeicao, String codigoAutorizacao,
			String tid, Integer nsuDoc, BigDecimal valorComplementar, Integer digCartao,
			BigDecimal valorTotalVendaCasoParceladoLoja, BigDecimal valorProximaParcela, Integer numeroNotaFiscal,
			Integer identificacaoCartaoEmitidoExterior, String numeroLogicoTerminal,
			String identificadorTaxaEmbarqueOuValorEntrada, String referenciaOuCodigoPedido, Date horaTransacao,
			String numeroUnicoTransacao, String identificarRedePremia, String usoRede) {
		super();
		this.id = id;
		this.uid = uid;
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
		this.identificarRedePremia = identificarRedePremia;
		this.usoRede = usoRede;
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

	public HistoricoVendaRede getHistoricoVendaRede() {
		return historicoVendaRede;
	}

	public void setHistoricoVendaRede(HistoricoVendaRede historicoVendaRede) {
		this.historicoVendaRede = historicoVendaRede;
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

	public MotivoRejeicao getMotivoRejeicao() {
		return motivoRejeicao;
	}

	public void setMotivoRejeicao(MotivoRejeicao motivoRejeicao) {
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

	public String getNumeroLogicoTerminal() {
		return numeroLogicoTerminal;
	}

	public void setNumeroLogicoTerminal(String numeroLogicoTerminal) {
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

	public String getIdentificarRedePremia() {
		return identificarRedePremia;
	}

	public void setIdentificarRedePremia(String identificarRedePremia) {
		this.identificarRedePremia = identificarRedePremia;
	}

	public String getUsoRede() {
		return usoRede;
	}

	public void setUsoRede(String usoRede) {
		this.usoRede = usoRede;
	}
	
	public List<ResumoOperacaoRede> getResumos() {
		return resumos;
	}

	public void setResumos(List<ResumoOperacaoRede> resumos) {
		this.resumos = resumos;
	}
	
	public static Set<ComprovanteVendaRede> fromListaComprovanteVendaDto(Set<ComprovanteVendaRedeDto> listaComprovanteVendaDto) {
		Set<ComprovanteVendaRede> lista = new HashSet<ComprovanteVendaRede>();
		for (ComprovanteVendaRedeDto comprovanteVendaRedeDto : listaComprovanteVendaDto) {
			lista.add(fromComprovanteVendaRedeDto(comprovanteVendaRedeDto));
		}
		
		return lista;
	}
	
	public static List<ComprovanteVendaRedeDto> toListaComprovanteVendaDto(List<ComprovanteVendaRede> listaComprovanteVenda) {
		List<ComprovanteVendaRedeDto> lista = new ArrayList<ComprovanteVendaRedeDto>();
		for (ComprovanteVendaRede comprovanteVendaRede : listaComprovanteVenda) {
			lista.add(comprovanteVendaRede.toComprovanteVendaRedeDto());
		}
		
		return lista;
	}
	
	public static ComprovanteVendaRede fromComprovanteVendaRedeDto(ComprovanteVendaRedeDto dto) {
		
		MotivoRejeicao motivoRejeicao = null;
		
		if (dto.getMotivoRejeicao() != null) {
			motivoRejeicao = MotivoRejeicao.fromMotivoRejeicaoDto(dto.getMotivoRejeicao());
		}
		
		List<ResumoOperacaoRede> resumos = null;
		if (dto.getResumos() != null) {
			resumos = ResumoOperacaoRede.fromListaResumoOperacaoDto(dto.getResumos());
		}
		
		return new ComprovanteVendaRede(dto.getId(),
				dto.getUid(),
				resumos,
			dto.getEstabelecimentoSubmissor(), 
			dto.getNumeroRO(), 
			dto.getNumeroCartaoTruncado(),
			dto.getDataVendaAjuste(), 
			dto.getValorCompraOuParcela(), 
			dto.getParcela(), 
			dto.getTotalParcelas(), 
			motivoRejeicao, 
			dto.getCodigoAutorizacao(), 
			dto.getTid(), 
			dto.getNsuDoc(),
			dto.getValorComplementar(), 
			dto.getDigCartao(), 
			dto.getValorTotalVendaCasoParceladoLoja(),
			dto.getValorProximaParcela(), 
			dto.getNumeroNotaFiscal(), 
			dto.getIdentificacaoCartaoEmitidoExterior(),
			dto.getNumeroLogicoTerminal(), 
			dto.getIdentificadorTaxaEmbarqueOuValorEntrada(),
			dto.getReferenciaOuCodigoPedido(), 
			dto.getHoraTransacao(), 
			dto.getNumeroUnicoTransacao(),
			dto.getIdentificarRedePremia(), 
			dto.getUsoRede());
	}
	
	public ComprovanteVendaRedeDto toComprovanteVendaRedeDto() {
    	HistoricoVendaRedeDto historicoVendaDto = this.historicoVendaRede.toHistoricoVendaRedeDto();
    	
    	MotivoRejeicaoDto motivoRejeicao = null;
    	if (this.motivoRejeicao != null) {
    		motivoRejeicao = this.motivoRejeicao.toMotivoRejeicaoDto();
    	}
    	
    	List<ResumoOperacaoRedeDto> listaResumoOperacao = ResumoOperacaoRede.toListaResumoOperacaoDto(this.resumos);
    	
        return new ComprovanteVendaRedeDto(this.id,
        		this.uid,
        		historicoVendaDto,
        		listaResumoOperacao,
        		this.estabelecimentoSubmissor,
        		this.numeroRO,
        		this.numeroCartaoTruncado,
        		this.dataVendaAjuste,
				this.valorCompraOuParcela,
				this.parcela,
				this.totalParcelas,
				motivoRejeicao,
				this.codigoAutorizacao,
				this.tid,
				this.nsuDoc,
				this.valorComplementar,
				this.digCartao,
				this.valorTotalVendaCasoParceladoLoja,
				this.valorProximaParcela,
				this.numeroNotaFiscal,
				this.identificacaoCartaoEmitidoExterior,
				this.numeroLogicoTerminal,
				this.identificadorTaxaEmbarqueOuValorEntrada,
				this.referenciaOuCodigoPedido,
				this.horaTransacao,
				this.numeroUnicoTransacao,
				this.identificarRedePremia,
				this.usoRede);
    }
}