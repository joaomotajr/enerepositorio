package br.com.eneeyes.controllers.api.dto.cieloCodeData;

public class OrigemAjusteDto {

	private String cod;
	private String descricao;
	private String tipoAjuste;
	
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
	public String getTipoAjuste() {
		return tipoAjuste;
	}
	public void setTipoAjuste(String tipoAjuste) {
		this.tipoAjuste = tipoAjuste;
	}
	public OrigemAjusteDto(String cod, String descricao, String tipoAjuste) {
		this.cod = cod;
		this.descricao = descricao;
		this.tipoAjuste = tipoAjuste;
	}
	
	public OrigemAjusteDto() {
	}
}
