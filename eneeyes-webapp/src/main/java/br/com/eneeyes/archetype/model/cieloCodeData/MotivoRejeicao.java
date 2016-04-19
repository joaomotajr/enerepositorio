package br.com.eneeyes.archetype.model.cieloCodeData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.controllers.api.dto.cieloCodeData.MotivoRejeicaoDto;

@Entity
@Table(name="motivo_rejeicao")

public class MotivoRejeicao {
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
	
	public MotivoRejeicao(String cod, String descricao) {
		super();
		this.cod = cod;
		this.descricao = descricao;
	}

	public MotivoRejeicao() {
		super();
	}
	
	public static MotivoRejeicao fromMotivoRejeicaoDto(MotivoRejeicaoDto dto) {
		return new MotivoRejeicao(dto.getCod(), dto.getDescricao());
	}

    public MotivoRejeicaoDto toMotivoRejeicaoDto() {
        return new MotivoRejeicaoDto(this.cod, this.descricao);
    }
}
