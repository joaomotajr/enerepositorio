package br.com.eneeyes.controllers.api.dto.cieloCodeData;

public class CodigoProdutoDto {

	private String cod;
	private String descricao;
	private String tipo;
	
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public CodigoProdutoDto(String cod, String descricao, String tipo) {
		this.cod = cod;
		this.descricao = descricao;
		this.tipo = tipo;
	}
	
	public CodigoProdutoDto() {
	}
	
}
