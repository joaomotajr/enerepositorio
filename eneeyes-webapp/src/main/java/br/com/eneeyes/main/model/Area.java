package br.com.eneeyes.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "area")
public class Area {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NAME", nullable = false)
	private String NAME;
	
	@Column(name = "DESCRIPTION", nullable = true)
	private String descricao;
	
	@Column(name = "LOCAL", nullable = true)		
	private Double Local;
	
	@Column(name = "LATITUDE", nullable = true)		
	private Double Latitude;
	
	@Column(name = "LONGITUDE", nullable = true)		
	private Double Longitude;
	
	@Column(name = "CLASSIFIED", nullable = true)
	private Boolean Classificada;
}
