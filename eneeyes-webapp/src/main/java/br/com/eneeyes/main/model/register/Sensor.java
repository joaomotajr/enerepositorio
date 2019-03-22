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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.register.SensorDto;
import br.com.eneeyes.main.model.enums.DetectionType;
import br.com.eneeyes.main.model.enums.UnitMeterGases;
import br.com.eneeyes.main.model.state.UnitMeter;

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
	
	public Sensor(Long uid) {
		this.uid = uid;
	}
	
	public Sensor(SensorDto dto) {
		this.uid = dto.getUid();		
		this.detectionType = dto.getDetectionType();
		this.name = dto.getName();
		
		if(dto.getManufacturerDto() != null)
			this.manufacturer = new Manufacturer(dto.getManufacturerDto());
		
		this.model = dto.getModel();
		this.gas = new Gas(dto.getGasDto());	
		this.unitMeter = dto.getUnitMeter();
		this.rangeMax = dto.getRangeMax();
		this.rangeMin = dto.getRangeMin();
		this.rangeUnit = dto.getRangeUnit();
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
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="GAS_ID", nullable = false)
	private Gas gas;
	
	@Column(name = "NAME", nullable = true)
	String name;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="MANUFACTURER_ID", nullable = false)
	private Manufacturer manufacturer;
	
	@Column(name = "MODEL", nullable = true)
	String model;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="UNIT_METER_ID", nullable = false)
	private UnitMeter unitMeter;
		
	@Column(name = "RANGE_MAX", nullable = false)		
	private Double rangeMax;
	
	@Column(name = "RANGE_MIN", nullable = false)		
	private Double rangeMin;
	
	@Column(name = "RANGE_UNIT", nullable = true)		
	private Double rangeUnit;
	
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

	public final String getName() {
		return name;
	}

	public Gas getGas() {
		return gas;
	}

	public void setGas(Gas gas) {
		this.gas = gas;
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
		
	public UnitMeter getUnitMeter() {
		return unitMeter;
	}

	public void setUnitMeter(UnitMeter unitMeter) {
		this.unitMeter = unitMeter;
	}

	public final Double getRangeMax() {
		return rangeMax;
	}

	public final void setRangeMax(Double rangeMax) {
		this.rangeMax = rangeMax;
	}

	public final Double getRangeMin() {
		return rangeMin;
	}

	public final void setRangeMin(Double rangeMin) {
		this.rangeMin = rangeMin;
	}

	public final Double getRangeUnit() {
		return rangeUnit;
	}

	public final void setRangeUnit(Double rangeUnit) {
		this.rangeUnit = rangeUnit;
	}	
}
