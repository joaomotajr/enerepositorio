package br.com.eneeyes.archetype.model.cieloCodeData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.controllers.api.dto.cieloCodeData.StatusPagamentoDto;

@Entity
@Table(name="status_pagamento")

public class StatusPagamento {
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
	
	public StatusPagamento(String cod, String descricao) {
		super();
		this.cod = cod;
		this.descricao = descricao;
	}

	public StatusPagamento() {
		super();
	}
	
	public static StatusPagamento fromStatusPagamentoDto(StatusPagamentoDto dto) {
		return new StatusPagamento(dto.getCod(), dto.getDescricao());
	}

    public StatusPagamentoDto toStatusPagamentoDto() {
        return new StatusPagamentoDto(this.cod, this.descricao);
    }
}
