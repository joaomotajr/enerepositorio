package br.com.eneeyes.controllers.api.dto;

import java.util.Date;

public class JustificativaDto {

	private Long id;
	private TipoJustificativaDto tipoJustificativa;
	private String usuario;
	private Date data;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TipoJustificativaDto getTipoJustificativa() {
		return tipoJustificativa;
	}
	public void setTipoJustificativa(TipoJustificativaDto tipoJustificativa) {
		this.tipoJustificativa = tipoJustificativa;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public JustificativaDto(Long id, TipoJustificativaDto tipoJustificativa, String usuario, Date data) {
		super();
		this.id = id;
		this.tipoJustificativa = tipoJustificativa;
		this.usuario = usuario;
		this.data = data;
	}
}