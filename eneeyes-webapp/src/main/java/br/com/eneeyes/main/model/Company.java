package br.com.eneeyes.main.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name = "company")
	public class Company {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "UID")
		private Long uid;
		
		@Column(name = "NOME", nullable = false)
		private String NAME;
		
		@Column(name = "EMAIL", nullable = true)
		private String email;
		
		@Column(name = "URL", nullable = true)
		private String url;
	
		@Column(name = "PHONE", nullable = true)
		private String phone;
		
		@Column(name = "MOBILE", nullable = true)
		private String mobile;		
		
		@Column(name = "ADDRESS", nullable = true)
		private String address;
		
		@Column(name = "CITY", nullable = true)
		private String city;
		
		@Column(name = "STATE", nullable = true)
		private String atate;
		
		@Column(name = "ZIP", nullable = true)
		private String zip;

		@Column(name = "TIPO_UNIDADE", columnDefinition = "int default 0")
		private TipoUnidade tipoUnidade;	
		
		@Column(name = "DATA", nullable = true)
		private Date data;
		
		@Column(name = "LATITUDE", nullable = true)		
		private Double Latitude;
		
		@Column(name = "LONGITUDE", nullable = true)		
		private Double Longitude;

		@Enumerated(EnumType.ORDINAL) 
		private TipoUnidade TipoUnidade() { 
		    return tipoUnidade; 
		}
		
		public TipoUnidade getTipoUnidade() {
			return tipoUnidade;
		}

		public void setTipoUnidade(TipoUnidade tipoUnidade) {
			this.tipoUnidade = tipoUnidade;
			if (tipoUnidade == null ) {			
				this.tipoUnidade = TipoUnidade.UNICA;
			}	
			else { 
				this.tipoUnidade = tipoUnidade;
			}
		}		
	}
