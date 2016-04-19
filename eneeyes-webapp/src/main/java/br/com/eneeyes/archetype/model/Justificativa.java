package br.com.eneeyes.archetype.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.eneeyes.controllers.api.dto.JustificativaDto;

@Entity
@Table(name="justificativa")
public class Justificativa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_")
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TIPO_JUSTIFICATIVA_ID_")
	private TipoJustificativa tipoJustificativa;
	
	@JoinColumn(name="USUARIO_LOGIN_")
	private String usuario;
	
	@Column(name="DATA_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoJustificativa getTipoJustificativa() {
		return tipoJustificativa;
	}

	public void setTipoJustificativa(TipoJustificativa tipoJustificativa) {
		this.tipoJustificativa = tipoJustificativa;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Justificativa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Justificativa(Long id, TipoJustificativa tipoJustificativa, String usuario, Date data) {
		super();
		this.id = id;
		this.tipoJustificativa = tipoJustificativa;
		this.usuario = usuario;
		this.data = data;
	}
	
	public static Justificativa fromJustificativaDto(JustificativaDto dto) {
		
		TipoJustificativa tipoJustificativa = new TipoJustificativa(dto.getTipoJustificativa().getId(), dto.getTipoJustificativa().getDescricao());
		
		return new Justificativa(dto.getId(), tipoJustificativa, dto.getUsuario(), dto.getData());
	}
}