package br.com.eneeyes.archetype.model.cieloCodeData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.archetype.model.vendas.DetalheVenda;
import br.com.eneeyes.archetype.model.vendas.DetalheVendaId;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.CodigoBandeiraDto;
import br.com.eneeyes.controllers.api.dto.vendas.DetalheVendaDto;
import br.com.eneeyes.controllers.api.dto.vendas.HistoricoVendaDto;

@Entity
@Table(name="codigo_bandeira")

public class CodigoBandeira {
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
	
	public CodigoBandeira(String cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public CodigoBandeira() {
	}

	public static CodigoBandeira fromCodigoBandeiraDto(CodigoBandeiraDto dto) {
		return new CodigoBandeira(dto.getCod(), dto.getDescricao());
	}

    public CodigoBandeiraDto toCodigoBandeiraDto() {
        return new CodigoBandeiraDto(this.cod, this.descricao);
    }
}
