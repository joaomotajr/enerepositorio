package br.com.eneeyes.main.model.register;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.model.enums.CommProtocol;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro de Transmissores
 * Obs: Sensores e transmissores são componentes do detector e não entram 
 * como Dispositivos, por tanto, tem uma tabela própria
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
	
	@Column(name = "COMM_PROTOCOL", columnDefinition = "int default 0")
	private CommProtocol commProtocol;	

	@Enumerated(EnumType.ORDINAL) 
	private CommProtocol CommProtocol() { 
	    return commProtocol; 
	}
		
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final Detector getDetector() {
		return detector;
	}

	public final void setDetector(Detector detector) {
		this.detector = detector;
	}
	
	public final CommProtocol getCommProtocol() {
		return commProtocol;
	}

	public final void setCommProtocol(CommProtocol commProtocol) {
		this.commProtocol = commProtocol;
	}
}
