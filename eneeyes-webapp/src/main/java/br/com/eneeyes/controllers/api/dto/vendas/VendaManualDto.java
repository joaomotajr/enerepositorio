package br.com.eneeyes.controllers.api.dto.vendas;

import java.io.Serializable;


public class VendaManualDto implements Serializable {

	private String id;
	private String dataLancamen;
	private String hora;
	private String autorizador;
	private String nomeProduto;
	private String cpfCnpj;
	private String titComplTrans;
	private String estadoTransacao;
	private String nsuHost;
	private String valor;
	private String estabelecimento;
	private String numeroCartao;
	private String codigoAutoriz;
	private String terminalLogico;
	private String numPar;
	private String meioCaptura;
	private String nomeCliente;
	private String isEdit;
	
	public VendaManualDto() {
	}
	
	public VendaManualDto(String id,
			String dataLancamen, String hora, String autorizador,
			String nomeProduto, String cpfCnpj, String titComplTrans,
			String estadoTransacao, String nsuHost, String valor,
			String estabelecimento, String numeroCartao, String codigoAutoriz,
			String terminalLogico, String numPar, String meioCaptura, String nomeCliente,
			String isEdit) {
		super();
		this.id = id;
		this.dataLancamen = dataLancamen;
		this.hora = hora;
		this.autorizador = autorizador;
		this.nomeProduto = nomeProduto;
		this.cpfCnpj = cpfCnpj;
		this.titComplTrans = titComplTrans;
		this.estadoTransacao = estadoTransacao;
		this.nsuHost = nsuHost;
		this.valor = valor;
		this.estabelecimento = estabelecimento;
		this.numeroCartao = numeroCartao;
		this.codigoAutoriz = codigoAutoriz;
		this.terminalLogico = terminalLogico;
		this.numPar = numPar;
		this.meioCaptura = meioCaptura;
		this.nomeCliente = nomeCliente;
		this.isEdit = isEdit;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getNsuHost() {
		return nsuHost;
	}
	public void setNsuHost(String nsuHost) {
		this.nsuHost = nsuHost;
	}
	public String getEstadoTransacao() {
		return estadoTransacao;
	}
	public void setEstadoTransacao(String estadoTransacao) {
		this.estadoTransacao = estadoTransacao;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
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
	public String getNumPar() {
		return numPar;
	}
	public void setNumPar(String numPar) {
		this.numPar = numPar;
	}
	public String getDataLancamen() {
		return dataLancamen;
	}
	public void setDataLancamen(String dataLancamen) {
		this.dataLancamen = dataLancamen;
	}
	public String getTerminalLogico() {
		return terminalLogico;
	}
	public void setTerminalLogico(String terminalLogico) {
		this.terminalLogico = terminalLogico;
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
	public boolean isEdit() {
		if (isEdit.equals("true")) {
			return true;
		}
		return false;
	}
	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}
}