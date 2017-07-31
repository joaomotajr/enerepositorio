package br.com.eneeyes.main.model.register;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.register.GasDto;

/**
 * Created by Junior on 31/06/2017.
 * cadastrar todas as informações dos gases a serem utilizados pelo 
 * sistema assim como informações descritivas.
 */

@Entity
@Table(name = "parameter")
public class Parameter {
	
	public Parameter() {
		
	}
	
	public Parameter(GasDto dto) {
		this.uid = dto.getUid();
			
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;	
}
