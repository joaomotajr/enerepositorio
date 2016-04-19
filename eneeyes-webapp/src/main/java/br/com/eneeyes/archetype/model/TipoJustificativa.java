package br.com.eneeyes.archetype.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.controllers.api.dto.TipoJustificativaDto;

@Entity
@Table(name="tipo_justificativa")
public class TipoJustificativa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_")
	private Long id;
	
	@Column(name="DESCRICAO_")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoJustificativa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoJustificativa(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	public static List<TipoJustificativa> fromListaTipoJustificativaDto(List<TipoJustificativaDto> listaTipoJustificativaDto) {
		List<TipoJustificativa> lista = new ArrayList<TipoJustificativa>();
		for (TipoJustificativaDto tipoJustificativaDto : listaTipoJustificativaDto) {
			lista.add(fromTipoJustificativaDto(tipoJustificativaDto));
		}
		
		return lista;
	}
	
	public static List<TipoJustificativaDto> toListaTipoJustificativaDto(List<TipoJustificativa> listaTipoJustificativa) {
		List<TipoJustificativaDto> lista = new ArrayList<TipoJustificativaDto>();
		for (TipoJustificativa tipoJustificativa : listaTipoJustificativa) {
			lista.add(tipoJustificativa.toTipoJustificativaDto());
		}
		
		return lista;
	}
	
	public static TipoJustificativa fromTipoJustificativaDto(TipoJustificativaDto dto) {
		return new TipoJustificativa(dto.getId(), dto.getDescricao());
	}

    public TipoJustificativaDto toTipoJustificativaDto() {
        return new TipoJustificativaDto(this.id, this.descricao);
    }
}