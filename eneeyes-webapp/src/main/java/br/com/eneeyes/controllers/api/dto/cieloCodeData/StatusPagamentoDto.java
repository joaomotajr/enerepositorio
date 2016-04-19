package br.com.eneeyes.controllers.api.dto.cieloCodeData;

public class StatusPagamentoDto {

	private String cod;
	private String descricao;
	
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
	
	public StatusPagamentoDto(String cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public StatusPagamentoDto() {
	}
}
