package br.com.eneeyes.controllers.api.dto.vendas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.eneeyes.archetype.model.Justificativa;


public class DetalheVendaDto implements Serializable {

	private Long id;
	private HistoricoVendaDto historicoVenda;
	private Date hora;
	private String nomeProduto;
	private String codigoEmpresa;
	private String nsuSiTef;
	private Integer nsuHost;
	private String codigoTransacao;
	private String estadoTransacao;
	private String codiResp;
	private String numeroCartao;
	private BigDecimal valor;
	private String identifiPDV;
	private String codigoAutoriz;
	private String titComplTrans;
	private String codigoProc;
	private String numPar;
	private Date dataLancamen;
	private String documentoCanc;
	private BigDecimal valorSaque;
	private BigDecimal taxaServico;
	private BigDecimal taxaEmbarque;
	private String terminalLogico;
	private String produtoFidelize;
	private String bit22;
	private String estabelecimento;
	private String cpfCnpj;
	private String autorizador;
	private BigDecimal valorParcial;
	private BigDecimal taxaCobrada;
	private String numeroPedido;
	private String codigoServico;
	private String meioCaptura;
	private String nomeCliente;
	private String statusConciliacao;
	private Boolean inconsistente;
	private Justificativa justificativa;
	
	public DetalheVendaDto() {
	}
	
	public DetalheVendaDto(Long id, HistoricoVendaDto historicoVenda, Date hora, String nomeProduto, String codigoEmpresa, String nsuSiTef,
			Integer nsuHost, String codigoTransacao, String estadoTransacao, String codiResp, String numeroCartao,
			BigDecimal valor, String identifiPDV, String codigoAutoriz, String titComplTrans, String codigoProc,
			String numPar, Date dataLancamen, String documentoCanc, BigDecimal valorSaque, BigDecimal taxaServico,
			BigDecimal taxaEmbarque, String terminalLogico, String produtoFidelize, String bit22,
			String estabelecimento, String cpfCnpj, String autorizador, BigDecimal valorParcial, BigDecimal taxaCobrada,
			String numeroPedido, String codigoServico, String meioCaptura, String nomeCliente, String statusConciliacao, Boolean inconsistente,
			Justificativa justificativa) {
		super();
		this.id = id;
		this.historicoVenda = historicoVenda;
		this.hora = hora;
		this.nomeProduto = nomeProduto;
		this.codigoEmpresa = codigoEmpresa;
		this.nsuSiTef = nsuSiTef;
		this.nsuHost = nsuHost;
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
		this.dataLancamen = dataLancamen;
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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public HistoricoVendaDto getHistoricoVenda() {
		return historicoVenda;
	}

	public void setHistoricoVenda(HistoricoVendaDto historicoVenda) {
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
	public Integer getNsuHost() {
		return nsuHost;
	}
	public void setNsuHost(Integer nsuHost) {
		this.nsuHost = nsuHost;
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
	public Date getDataLancamen() {
		return dataLancamen;
	}
	public void setDataLancamen(Date dataLancamen) {
		this.dataLancamen = dataLancamen;
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
}