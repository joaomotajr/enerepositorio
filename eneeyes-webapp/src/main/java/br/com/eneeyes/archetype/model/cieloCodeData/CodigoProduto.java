package br.com.eneeyes.archetype.model.cieloCodeData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.controllers.api.dto.cieloCodeData.CodigoProdutoDto;

@Entity
@Table(name="codigo_produto")

public class CodigoProduto {
	@Id
    @Column(name="COD_")
    private String cod;
    
    @Column(name="DESCRICAO_")
	private String descricao;
    
    @Column(name="TIPO_")
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

	public CodigoProduto(String cod, String descricao, String tipo) {
		super();
		this.cod = cod;
		this.descricao = descricao;
		this.tipo = tipo;
	}

	public CodigoProduto() {
		super();
	}
	
	public static CodigoProduto fromCodigoProdutoDto(CodigoProdutoDto dto) {
		return new CodigoProduto(dto.getCod(), dto.getDescricao(), dto.getTipo());
	}

    public CodigoProdutoDto toCodigoProdutoDto() {
        return new CodigoProdutoDto(this.cod, this.descricao, this.tipo);
    }
}
