package br.com.eneeyes.controllers.api.dto.vendas;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DetalheVendaFiltroDto {

	private String dataInicial;
	private String dataFinal;
	private String data;
	private String cnpj;
	private String razaoSocial;
	private String adquirente;
	private String tipoVenda;
	private List<String> status;
	private String cartao;
	private String nsu;
	private String valor;
	
	@XmlElement
	public String getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	@XmlElement
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	@XmlElement
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@XmlElement
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	@XmlElement
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	@XmlElement
	public String getAdquirente() {
		return adquirente;
	}
	public void setAdquirente(String adquirente) {
		this.adquirente = adquirente;
	}
	@XmlElement
	public String getTipoVenda() {
		return tipoVenda;
	}
	public void setTipoVenda(String tipoVenda) {
		this.tipoVenda = tipoVenda;
	}
	@XmlElement
	public List<String> getStatus() {
		return status;
	}
	public void setStatus(List<String> status) {
		this.status = status;
	}
	@XmlElement
	public String getCartao() {
		return cartao;
	}
	public void setCartao(String cartao) {
		this.cartao = cartao;
	}
	@XmlElement
	public String getNsu() {
		return nsu;
	}
	public void setNsu(String nsu) {
		this.nsu = nsu;
	}
	@XmlElement
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}