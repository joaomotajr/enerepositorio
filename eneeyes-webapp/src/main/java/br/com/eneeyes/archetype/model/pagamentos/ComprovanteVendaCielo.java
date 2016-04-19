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
import br.com.eneeyes.controllers.api.dto.pagamentos.ComprovanteVendaCieloDto;
import br.com.eneeyes.controllers.api.dto.pagamentos.ResumoOperacaoCieloDto;
import br.com.eneeyes.controllers.api.dto.vendas.HistoricoVendaCieloDto;

@Entity
@Table(name="comprovante_venda_cielo")
public class ComprovanteVendaCielo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_")
	private Long id;
	
	@Column(name="UID_")
	private String uid;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="HISTORICO_ID_")
	private HistoricoVendaCielo historicoVendaCielo;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="resumo_comprovante", 
		joinColumns= @JoinColumn(name="COMPROVANTE_ID_", referencedColumnName="ID_"), 
		inverseJoinColumns= @JoinColumn(name="RESUMO_ID_", referencedColumnName="ID_"))
	private List<ResumoOperacaoCielo> resumos = new ArrayList<ResumoOperacaoCielo>();

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
	private Integer numeroLogicoTerminal;
	
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
	private String identificarCieloPremia;
	
	@Column(name="USO_CIELO_")
	private String usoCielo;
	
	public ComprovanteVendaCielo() {
	}

	public ComprovanteVendaCielo(Long id, String uid, List<ResumoOperacaoCielo> resumos, Integer estabelecimentoSubmissor,
			Integer numeroRO, String numeroCartaoTruncado, Date dataVendaAjuste, BigDecimal valorCompraOuParcela,
			Integer parcela, Integer totalParcelas, MotivoRejeicao motivoRejeicao, String codigoAutorizacao,
			String tid, Integer nsuDoc, BigDecimal valorComplementar, Integer digCartao,
			BigDecimal valorTotalVendaCasoParceladoLoja, BigDecimal valorProximaParcela, Integer numeroNotaFiscal,
			Integer identificacaoCartaoEmitidoExterior, Integer numeroLogicoTerminal,
			String identificadorTaxaEmbarqueOuValorEntrada, String referenciaOuCodigoPedido, Date horaTransacao,
			String numeroUnicoTransacao, String identificarCieloPremia, String usoCielo) {
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

	public HistoricoVendaCielo getHistoricoVendaCielo() {
		return historicoVendaCielo;
	}

	public void setHistoricoVendaCielo(HistoricoVendaCielo historicoVendaCielo) {
		this.historicoVendaCielo = historicoVendaCielo;
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
	
	public List<ResumoOperacaoCielo> getResumos() {
		return resumos;
	}

	public void setResumos(List<ResumoOperacaoCielo> resumos) {
		this.resumos = resumos;
	}
	
	public static Set<ComprovanteVendaCielo> fromListaComprovanteVendaDto(Set<ComprovanteVendaCieloDto> listaComprovanteVendaDto) {
		Set<ComprovanteVendaCielo> lista = new HashSet<ComprovanteVendaCielo>();
		for (ComprovanteVendaCieloDto comprovanteVendaCieloDto : listaComprovanteVendaDto) {
			lista.add(fromComprovanteVendaCieloDto(comprovanteVendaCieloDto));
		}
		
		return lista;
	}
	
	public static List<ComprovanteVendaCieloDto> toListaComprovanteVendaDto(List<ComprovanteVendaCielo> listaComprovanteVenda) {
		List<ComprovanteVendaCieloDto> lista = new ArrayList<ComprovanteVendaCieloDto>();
		for (ComprovanteVendaCielo comprovanteVendaCielo : listaComprovanteVenda) {
			lista.add(comprovanteVendaCielo.toComprovanteVendaCieloDto());
		}
		
		return lista;
	}
	
	public static ComprovanteVendaCielo fromComprovanteVendaCieloDto(ComprovanteVendaCieloDto dto) {
		
		MotivoRejeicao motivoRejeicao = null;
		
		if (dto.getMotivoRejeicao() != null) {
			motivoRejeicao = MotivoRejeicao.fromMotivoRejeicaoDto(dto.getMotivoRejeicao());
		}
		
		List<ResumoOperacaoCielo> resumos = null;
		if (dto.getResumos() != null) {
			resumos = ResumoOperacaoCielo.fromListaResumoOperacaoDto(dto.getResumos());
		}
		
		return new ComprovanteVendaCielo(dto.getId(),
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
			dto.getIdentificarCieloPremia(), 
			dto.getUsoCielo());
	}
	
	public ComprovanteVendaCieloDto toComprovanteVendaCieloDto() {
    	HistoricoVendaCieloDto historicoVendaDto = this.historicoVendaCielo.toHistoricoVendaCieloDto();
    	
    	MotivoRejeicaoDto motivoRejeicao = null;
    	if (this.motivoRejeicao != null) {
    		motivoRejeicao = this.motivoRejeicao.toMotivoRejeicaoDto();
    	}
    	
    	List<ResumoOperacaoCieloDto> listaResumoOperacao = ResumoOperacaoCielo.toListaResumoOperacaoDto(this.resumos);
    	
        return new ComprovanteVendaCieloDto(this.id,
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
				this.identificarCieloPremia,
				this.usoCielo);
    }
}