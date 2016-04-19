package br.com.eneeyes.archetype.model.cieloCodeData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.controllers.api.dto.cieloCodeData.OrigemAjusteDto;

@Entity
@Table(name="origem_ajuste")

public class OrigemAjuste {
	@Id
    @Column(name="COD_")
    private String cod;
    
    @Column(name="DESCRICAO_")
	private String descricao;
    
    @Column(name="TIPO_AJUSTE_")
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

	public OrigemAjuste(String cod, String descricao, String tipoAjuste) {
		super();
		this.cod = cod;
		this.descricao = descricao;
		this.tipoAjuste = tipoAjuste;
	}

	public OrigemAjuste() {
		super();
	}
	
	public static OrigemAjuste fromOrigemAjusteDto(OrigemAjusteDto dto) {
		return new OrigemAjuste(dto.getCod(), dto.getDescricao(), dto.getTipoAjuste());
	}

    public OrigemAjusteDto toOrigemAjusteDto() {
        return new OrigemAjusteDto(this.cod, this.descricao, this.tipoAjuste);
    }
}
