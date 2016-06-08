package br.com.eneeyes.main.model.register;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.main.model.enums.UnitMeterGases;;

/**
 * Created by Junior on 06/06/2016.
 * cadastrar todas as informações dos gases a serem utilizados pelo 
 * sistema assim como informações descritivas.
 */

@Entity
@Table(name = "gases")
public class Gases {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NAME", nullable = true)
	private String name;
	
	@Column(name = "CAS", nullable = true)
	private String cas;
	
	@Column(name = "FORMULA", nullable = true)
	private String formula;

	@Column(name = "UNIT_METER_GASES", columnDefinition = "int default 0")
	private UnitMeterGases UnitMeterGases;	

	@Enumerated(EnumType.ORDINAL) 
	private UnitMeterGases UnitMeterGases() { 
	    return UnitMeterGases; 
	}
	
}
