package br.com.eneeyes.controllers.api.dto.inconsistencia;

public class MotivoInconsistenciaDto {

	private Integer codigo;
	private String descricao;
	
	public MotivoInconsistenciaDto(Integer codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
