package br.com.eneeyes.main.model.register;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Junior on 06/06/2016.
 * Define tipos de dipositivos aceitos para Cadastro
 */

@Entity
@Table(name = "transmitter")
public class Transmitter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@OneToOne(cascade=CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="DETECTOR_ID")
	private Detector detector;
	
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}
}
