package br.com.eneeyes.archetype.model.vendas;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.eneeyes.archetype.model.Justificativa;
import br.com.eneeyes.controllers.api.dto.vendas.DetalheVendaDto;
import br.com.eneeyes.controllers.api.dto.vendas.HistoricoVendaDto;
import br.com.eneeyes.controllers.api.dto.vendas.VendaManualDto;

@Entity
@Table(name="detalhe_venda")
public class DetalheVenda {
	
	@EmbeddedId
	private DetalheVendaId detalheVendaId;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_")
	private Long id;
	
    @ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="HISTORICO_ID_")
	private HistoricoVenda historicoVenda;
    
    @Column(name="HORA_")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    
    @Column(name="NOME_PRODUTO_")
	private String nomeProduto;
    
    @Column(name="CODIGO_EMPRESA_")
	private String codigoEmpresa;
    
    @Column(name="NSU_SITEF_")
	private String nsuSiTef;
    
    @Column(name="CODIGO_TRANSACAO_")
	private String codigoTransacao;
    
    @Column(name="ESTADO_TRANSACAO_")
	private String estadoTransacao;
    
    @Column(name="CODI_RESP_")
	private String codiResp;
    
    @Column(name="NUMERO_CARTAO_")
	private String numeroCartao;
    
    @Column(name="VALOR_")
	private BigDecimal valor;
    
    @Column(name="IDENTIFI_PDV_")
	private String identifiPDV;
    
    @Column(name="CODIGO_AUTORIZ_")
	private String codigoAutoriz;
    
    @Column(name="TIT_COMPL_TRANS_")
	private String titComplTrans;
    
    @Column(name="CODIGO_PROC_")
	private String codigoProc;
    
    @Column(name="NUM_PAR_")
	private String numPar;
    
    @Column(name="DOCUMENTO_CANC_")
	private String documentoCanc;
    
    @Column(name="VALOR_SAQUE_")
	private BigDecimal valorSaque;
    
    @Column(name="TAXA_SERVICO_")
	private BigDecimal taxaServico;
    
    @Column(name="TAXA_EMBARQUE_")
	private BigDecimal taxaEmbarque;
    
    @Column(name="TERMINAL_LOGICO_")
	private String terminalLogico;
    
    @Column(name="PRODUTO_FIDELIZE_")
	private String produtoFidelize;
    
    @Column(name="BIT_22_")
	private String bit22;
    
    @Column(name="ESTABELECIMENTO_")
	private String estabelecimento;
    
    @Column(name="CPF_CNPJ_")
	private String cpfCnpj;
    
    @Column(name="AUTORIZADOR_")
	private String autorizador;
    
    @Column(name="VALOR_PARCIAL_")
	private BigDecimal valorParcial;
    
    @Column(name="TAXA_COBRADA_")
	private BigDecimal taxaCobrada;
    
    @Column(name="NUMERO_PEDIDO_")
	private String numeroPedido;
    
    @Column(name="CODIGO_SERVICO_")
	private String codigoServico;
    
    @Column(name="MEIO_CAPTURA_")
    private String meioCaptura;
    
    @Column(name="NOME_CLIENTE_")
    private String nomeCliente;
    
    @Column(name="STATUS_CONCILIACAO_")
    private String statusConciliacao;
    
    @Column(name="INCONSISTENTE_")
    private Boolean inconsistente;
    
    @ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="JUSTIFICATIVA_ID_")
	private Justificativa justificativa;
    
	public DetalheVenda() {
	}

	public DetalheVenda(DetalheVendaId detalheVendaid, Long id, Date hora, String nomeProduto, String codigoEmpresa, String nsuSiTef,
			String codigoTransacao, String estadoTransacao, String codiResp, String numeroCartao, BigDecimal valor,
			String identifiPDV, String codigoAutoriz, String titComplTrans, String codigoProc, String numPar,
			Date dataLancamen, String documentoCanc, BigDecimal valorSaque, BigDecimal taxaServico,
			BigDecimal taxaEmbarque, String terminalLogico, String produtoFidelize, String bit22,
			String estabelecimento, String cpfCnpj, String autorizador, BigDecimal valorParcial, BigDecimal taxaCobrada,
			String numeroPedido, String codigoServico, String meioCaptura, String nomeCliente, String statusConciliacao, Boolean inconsistente,
			Justificativa justificativa) {
		super();
		this.detalheVendaId = detalheVendaid;
		this.id = id;
		this.hora = hora;
		this.nomeProduto = nomeProduto;
		this.codigoEmpresa = codigoEmpresa;
		this.nsuSiTef = nsuSiTef;
		this.codigoTransacao = codigoTransacao;
		this.estadoTransacao = estadoTransacao;
		this.codiResp = codiResp;
		this.numeroCartao = numeroCartao;
		this.valor = valor;
		this.identifiPDV = identifiPDV;
		this.codigoAutoriz = codigoAutoriz;
		this.titComplTrans = titComplTrans;
		this.codigoProc = codigoProc;
		this.numPar = numPar;
		this.documentoCanc = documentoCanc;
		this.valorSaque = valorSaque;
		this.taxaServico = taxaServico;
		this.taxaEmbarque = taxaEmbarque;
		this.terminalLogico = terminalLogico;
		this.produtoFidelize = produtoFidelize;
		this.bit22 = bit22;
		this.estabelecimento = estabelecimento;
		this.cpfCnpj = cpfCnpj;
		this.autorizador = autorizador;
		this.valorParcial = valorParcial;
		this.taxaCobrada = taxaCobrada;
		this.numeroPedido = numeroPedido;
		this.codigoServico = codigoServico;
		this.meioCaptura = meioCaptura;
		this.nomeCliente = nomeCliente;
		this.statusConciliacao = statusConciliacao;
		this.inconsistente = inconsistente;
		this.justificativa = justificativa;
	}

	public DetalheVendaId getDetalheVendaId() {
		return detalheVendaId;
	}

	public void setDetalheVendaId(DetalheVendaId detalheVendaId) {
		this.detalheVendaId = detalheVendaId;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HistoricoVenda getHistoricoVenda() {
		return historicoVenda;
	}

	public void setHistoricoVenda(HistoricoVenda historicoVenda) {
		this.historicoVenda = historicoVenda;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public String getNsuSiTef() {
		return nsuSiTef;
	}

	public void setNsuSiTef(String nsuSiTef) {
		this.nsuSiTef = nsuSiTef;
	}

	public String getCodigoTransacao() {
		return codigoTransacao;
	}

	public void setCodigoTransacao(String codigoTransacao) {
		this.codigoTransacao = codigoTransacao;
	}

	public String getEstadoTransacao() {
		return estadoTransacao;
	}

	public void setEstadoTransacao(String estadoTransacao) {
		this.estadoTransacao = estadoTransacao;
	}

	public String getCodiResp() {
		return codiResp;
	}

	public void setCodiResp(String codiResp) {
		this.codiResp = codiResp;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getIdentifiPDV() {
		return identifiPDV;
	}

	public void setIdentifiPDV(String identifiPDV) {
		this.identifiPDV = identifiPDV;
	}

	public String getCodigoAutoriz() {
		return codigoAutoriz;
	}

	public void setCodigoAutoriz(String codigoAutoriz) {
		this.codigoAutoriz = codigoAutoriz;
	}

	public String getTitComplTrans() {
		return titComplTrans;
	}

	public void setTitComplTrans(String titComplTrans) {
		this.titComplTrans = titComplTrans;
	}

	public String getCodigoProc() {
		return codigoProc;
	}

	public void setCodigoProc(String codigoProc) {
		this.codigoProc = codigoProc;
	}

	public String getNumPar() {
		return numPar;
	}

	public void setNumPar(String numPar) {
		this.numPar = numPar;
	}

	public String getDocumentoCanc() {
		return documentoCanc;
	}

	public void setDocumentoCanc(String documentoCanc) {
		this.documentoCanc = documentoCanc;
	}

	public BigDecimal getValorSaque() {
		return valorSaque;
	}

	public void setValorSaque(BigDecimal valorSaque) {
		this.valorSaque = valorSaque;
	}

	public BigDecimal getTaxaServico() {
		return taxaServico;
	}

	public void setTaxaServico(BigDecimal taxaServico) {
		this.taxaServico = taxaServico;
	}

	public BigDecimal getTaxaEmbarque() {
		return taxaEmbarque;
	}

	public void setTaxaEmbarque(BigDecimal taxaEmbarque) {
		this.taxaEmbarque = taxaEmbarque;
	}

	public String getTerminalLogico() {
		return terminalLogico;
	}

	public void setTerminalLogico(String terminalLogico) {
		this.terminalLogico = terminalLogico;
	}

	public String getProdutoFidelize() {
		return produtoFidelize;
	}

	public void setProdutoFidelize(String produtoFidelize) {
		this.produtoFidelize = produtoFidelize;
	}

	public String getBit22() {
		return bit22;
	}

	public void setBit22(String bit22) {
		this.bit22 = bit22;
	}

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(String estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getAutorizador() {
		return autorizador;
	}

	public void setAutorizador(String autorizador) {
		this.autorizador = autorizador;
	}

	public BigDecimal getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}

	public BigDecimal getTaxaCobrada() {
		return taxaCobrada;
	}

	public void setTaxaCobrada(BigDecimal taxaCobrada) {
		this.taxaCobrada = taxaCobrada;
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public String getCodigoServico() {
		return codigoServico;
	}

	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}
	
	public String getMeioCaptura() {
		return meioCaptura;
	}

	public void setMeioCaptura(String meioCaptura) {
		this.meioCaptura = meioCaptura;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public String getStatusConciliacao() {
		return statusConciliacao;
	}

	public void setStatusConciliacao(String statusConciliacao) {
		this.statusConciliacao = statusConciliacao;
	}
	
	public Boolean getInconsistente() {
		return inconsistente;
	}

	public void setInconsistente(Boolean inconsistente) {
		this.inconsistente = inconsistente;
	}
	
	public Justificativa getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(Justificativa justificativa) {
		this.justificativa = justificativa;
	}
	
	public static List<DetalheVendaDto> toListaDetalheVendaDto(List<DetalheVenda> listaDetalheVenda) {
		List<DetalheVendaDto> lista = new ArrayList<DetalheVendaDto>();
		for (DetalheVenda detalheVenda : listaDetalheVenda) {
			lista.add(detalheVenda.toDetalheVendaDto());
		}
		
		return lista;
	}

	public static DetalheVenda fromDetalheVendaDto(DetalheVendaDto dto) {
		DetalheVendaId detalheVendaId = new DetalheVendaId(dto.getNsuHost(), dto.getDataLancamen());
		
		Justificativa justificativa = null;
		if (dto.getJustificativa() != null) {
			justificativa = new Justificativa(dto.getJustificativa().getId(), dto.getJustificativa().getTipoJustificativa(), dto.getJustificativa().getUsuario(), dto.getJustificativa().getData());
		}
		
		return new DetalheVenda(detalheVendaId, dto.getId(), dto.getHora(), dto.getNomeProduto(), dto.getCodigoEmpresa(), dto.getNsuSiTef(), dto.getCodigoTransacao(), dto.getEstadoTransacao(), 
								dto.getCodiResp(), dto.getNumeroCartao(), dto.getValor(), dto.getIdentifiPDV(), dto.getCodigoAutoriz(), dto.getTitComplTrans(), dto.getCodigoProc(), dto.getNumPar(),
								dto.getDataLancamen(), dto.getDocumentoCanc(), dto.getValorSaque(), dto.getTaxaServico(), dto.getTaxaEmbarque(), dto.getTerminalLogico(), dto.getProdutoFidelize(),
								dto.getBit22(), dto.getEstabelecimento(), dto.getCpfCnpj(), dto.getAutorizador(), dto.getValorParcial(), dto.getTaxaCobrada(), dto.getNumeroPedido(), dto.getCodigoServico(),
								dto.getMeioCaptura(), dto.getNomeCliente(), dto.getStatusConciliacao(), dto.getInconsistente(), justificativa);
	}

    public DetalheVendaDto toDetalheVendaDto() {
    	HistoricoVendaDto HistoricoVendaDto = this.historicoVenda.toHistoricoVendaDto();
    	
        return new DetalheVendaDto(this.getId(), HistoricoVendaDto, this.hora, this.nomeProduto, this.codigoEmpresa, this.nsuSiTef, this.detalheVendaId.getNsuHost(), this.codigoTransacao, this.estadoTransacao, 
				this.codiResp, this.numeroCartao, this.valor, this.identifiPDV, this.codigoAutoriz, this.titComplTrans, this.codigoProc, this.numPar,
				this.detalheVendaId.getDataLancamen(), this.documentoCanc, this.valorSaque, this.taxaServico, this.taxaEmbarque, this.terminalLogico, this.produtoFidelize,
				this.bit22, this.estabelecimento, this.cpfCnpj, this.autorizador, this.valorParcial, this.taxaCobrada, this.numeroPedido, this.codigoServico, this.meioCaptura, this.nomeCliente, this.statusConciliacao,
				this.inconsistente, this.justificativa);
    }
    
    public static DetalheVenda fromVendaManulDto(VendaManualDto dto) throws ParseException {
    	SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");
    	SimpleDateFormat hourFormater = new SimpleDateFormat("HH:mm:ss");
    	
    	Long id = new Long(new Long(dto.getId().equals("") ? "0" : dto.getId()));
    	Date hora = hourFormater.parse(dto.getHora());
    	Date data = dateFormater.parse(dto.getDataLancamen());
    	
    	BigDecimal valor = new BigDecimal(dto.getValor().equals("0") ? "0" : dto.getValor().replace(".", "").replace(",", "."));
    	
    	DetalheVendaId detalheVendaId = new DetalheVendaId(new Integer(dto.getNsuHost()), data);
    	
		return new DetalheVenda(detalheVendaId, id, hora, dto.getNomeProduto(), null, null, null, dto.getEstadoTransacao(), 
								null, dto.getNumeroCartao(), valor, null, dto.getCodigoAutoriz(), dto.getTitComplTrans(), null, dto.getNumPar(),
								data, null, null, null, null, dto.getTerminalLogico(), null, null, dto.getEstabelecimento(), dto.getCpfCnpj(), 
								dto.getAutorizador(), null, null, null, null, dto.getMeioCaptura(), dto.getNomeCliente(), "Automática", false, null);
	}
}