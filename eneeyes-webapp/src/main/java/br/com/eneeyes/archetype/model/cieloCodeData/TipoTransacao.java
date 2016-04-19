package br.com.eneeyes.archetype.model.cieloCodeData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.controllers.api.dto.cieloCodeData.TipoTransacaoDto;

@Entity
@Table(name="tipo_transacao")

public class TipoTransacao {
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
	
	public TipoTransacao(String cod, String descricao) {
		super();
		this.cod = cod;
		this.descricao = descricao;
	}

	public TipoTransacao() {
		super();
	}
	
	public static TipoTransacao fromTipoTransacaoDto(TipoTransacaoDto dto) {
		return new TipoTransacao(dto.getCod(), dto.getDescricao());
	}

    public TipoTransacaoDto toTipoTransacaoDto() {
        return new TipoTransacaoDto(this.cod, this.descricao);
    }
}
