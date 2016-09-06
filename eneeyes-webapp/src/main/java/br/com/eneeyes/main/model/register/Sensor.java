package br.com.eneeyes.main.model.register;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.register.GasDto;
import br.com.eneeyes.main.dto.register.SensorDto;
import br.com.eneeyes.main.model.enums.DetectionType;
import br.com.eneeyes.main.model.enums.UnitMeterGases;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro dos Sensores que serão atribuídos ao Detectores.
 * Obs: Sensores e transmissores são componentes do detector e 
 * não entram como Dispositivos, por tanto, tem uma tabela própria
 */

@Entity
@Table(name = "sensor")
public class Sensor {
	
	public Sensor() {
		
	}
	
	public Sensor(SensorDto dto) {
		this.uid = dto.getUid();		
		this.detectionType = dto.getDetectionType();
		this.name = dto.getName();
		
		if(dto.getManufacturerDto() != null)
			this.manufacturer = new Manufacturer(dto.getManufacturerDto());
		
		this.model = dto.getModel();
		
		if(dto.getGasesDto() != null)
			this.gases = parseGases(dto.getGasesDto());
		
		this.unitMeterGases = dto.getUnitMeterGases();
		this.RangeMax = dto.getRangeMax();
		this.RangeMin = dto.getRangeMin();
		this.RangeUnit = dto.getRangeUnit();
	}	
	
	private final Set<Gas> parseGases(List<GasDto> gases) {		
		Set<Gas> lista = new HashSet<Gas>();		
		
		for (GasDto item   : gases) {			
			lista.add(new Gas(item));			
		}		
		return lista;		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "DETECTION_TYPE", columnDefinition = "int default 0")
	private DetectionType detectionType;	

	@Enumerated(EnumType.ORDINAL) 
	private DetectionType DetectionType() { 
	    return detectionType; 
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "sensor_gases",	 
	joinColumns = @JoinColumn(name = "SENSOR_ID", referencedColumnName = "UID"), 
	inverseJoinColumns = @JoinColumn(name = "GASES_ID", referencedColumnName = "UID"))	
	private Set<Gas> gases = new HashSet<Gas>();
	
	@Column(name = "NAME", nullable = true)
	String name;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="MANUFACTURER_ID", nullable = false)
	private Manufacturer manufacturer;
	
	@Column(name = "MODEL", nullable = true)
	String model;
	
	@Column(name = "UNIT_METER_GASES", columnDefinition = "int default 0")
	private UnitMeterGases unitMeterGases;

	@Enumerated(EnumType.ORDINAL) 
	private UnitMeterGases UnitMeterGases() { 
	    return unitMeterGases; 
	}
	
	@Column(name = "RANGE_MAX", nullable = true)		
	private Double RangeMax;
	
	@Column(name = "RANGE_MIN", nullable = true)		
	private Double RangeMin;
	
	@Column(name = "RANGE_UNIT", nullable = true)		
	private Double RangeUnit;
	
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

	public final DetectionType getDetectionType() {
		return detectionType;
	}

	public final void setDetectionType(DetectionType detectionType) {
		this.detectionType = detectionType;
	}
	
	public final Set<Gas> getGases() {
		return gases;
	}

	public final void setGases(Set<Gas> gases) {
		this.gases = gases;
	}
	
	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}
	
	public final Manufacturer getManufacturer() {
		return manufacturer;
	}

	public final void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public final String getModel() {
		return model;
	}

	public final void setModel(String model) {
		this.model = model;
	}
	
	public final UnitMeterGases getUnitMeterGases() {
		return unitMeterGases;
	}
	
	public final void setUnitMeterGases(UnitMeterGases unitMeterGases) {
		this.unitMeterGases = unitMeterGases;
	}
	
	public final Double getRangeMax() {
		return RangeMax;
	}

	public final void setRangeMax(Double rangeMax) {
		RangeMax = rangeMax;
	}

	public final Double getRangeMin() {
		return RangeMin;
	}

	public final void setRangeMin(Double rangeMin) {
		RangeMin = rangeMin;
	}

	public final Double getRangeUnit() {
		return RangeUnit;
	}

	public final void setRangeUnit(Double rangeUnit) {
		RangeUnit = rangeUnit;
	}	
}
