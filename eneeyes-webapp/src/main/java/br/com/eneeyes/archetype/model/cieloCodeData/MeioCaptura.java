package br.com.eneeyes.archetype.model.cieloCodeData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.controllers.api.dto.cieloCodeData.MeioCapturaDto;

@Entity
@Table(name="meio_captura")

public class MeioCaptura {
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
	
	public MeioCaptura(String cod, String descricao) {
		super();
		this.cod = cod;
		this.descricao = descricao;
	}

	public MeioCaptura() {
		super();
	}
	
	public static MeioCaptura fromMeioCapturaDto(MeioCapturaDto dto) {
		return new MeioCaptura(dto.getCod(), dto.getDescricao());
	}

    public MeioCapturaDto toMeioCapturaDto() {
        return new MeioCapturaDto(this.cod, this.descricao);
    }
}
