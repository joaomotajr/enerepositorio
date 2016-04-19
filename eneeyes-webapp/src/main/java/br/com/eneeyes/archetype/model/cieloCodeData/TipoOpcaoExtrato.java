package br.com.eneeyes.archetype.model.cieloCodeData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.controllers.api.dto.cieloCodeData.OrigemAjusteDto;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.TipoOpcaoExtratoDto;

@Entity
@Table(name="tipo_opcao_extrato")

public class TipoOpcaoExtrato {
	@Id
    @Column(name="COD_")
    private String cod;
    
    @Column(name="DESCRICAO_")
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
	
	public TipoOpcaoExtrato(String cod, String descricao) {
		super();
		this.cod = cod;
		this.descricao = descricao;
	}

	public TipoOpcaoExtrato() {
		super();
	}
	
	public static TipoOpcaoExtrato fromTipoOpcaoExtratoDto(TipoOpcaoExtratoDto dto) {
		return new TipoOpcaoExtrato(dto.getCod(), dto.getDescricao());
	}

    public TipoOpcaoExtratoDto toTipoOpcaoExtratoDto() {
        return new TipoOpcaoExtratoDto(this.cod, this.descricao);
    }
}
