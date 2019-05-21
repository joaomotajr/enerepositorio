    package br.com.eneeyes.main.model.register;

import java.io.UnsupportedEncodingException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.register.GenericDto;
import br.com.eneeyes.main.model.state.DeviceType;
import br.com.eneeyes.main.model.state.UnitMeter;

/**
 * Created by Junior on 30/01/2018.
 * Cadastro de Dispositivos Genéricos
 */

@Entity
@Table(name = "generic")
public class Generic {
	
	public Generic() {
		
	}
	
	public Generic(GenericDto dto) {
		this.uid = dto.getUid();		
		this.name = dto.getName();
		this.deviceType = dto.getDeviceType();
		this.unitMeter = dto.getUnitMeter();
		
		if (dto.getManufacturerDto() != null)
			this.manufacturer = new Manufacturer(dto.getManufacturerDto());
		
		this.model = dto.getModel();
		this.rangeMax = dto.getRangeMax();
		this.rangeMin = dto.getRangeMin();
		
		if (dto.getImage() != null) {
			try {
					
				byte[] image = null;
				image = dto.getImage().getBytes("US-ASCII");
				this.setImage(image);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}	
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NAME", nullable = true)
	String name;
	
//	@Column(name = "UNIT_METER_GASES", columnDefinition = "int default 0")
//	private UnitMeterGases unitMeterGases;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="UNIT_METER_ID", nullable = false)
	private UnitMeter unitMeter;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="DEVICE_TYPE_ID", nullable = false)
	private DeviceType deviceType;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="MANUFACTURER_ID", nullable = false)
	private Manufacturer manufacturer;	

	@Column(name = "MODEL", nullable = true)
	String model;
	
	@Column(name = "RANGE_MAX", nullable = true)		
	private Double rangeMax;
	
	@Column(name = "RANGE_MIN", nullable = true)		
	private Double rangeMin;
	
	@Lob
	@Column(name = "IMAGE", nullable = true)
	byte[] image;
	
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}
	
//	public UnitMeterGases getUnitMeterGases() {
//		return unitMeterGases;
//	}
//
//	public void setUnitMeterGases(UnitMeterGases unitMeterGases) {
//		this.unitMeterGases = unitMeterGases;
//	}

	public UnitMeter getUnitMeter() {
		return unitMeter;
	}

	public void setUnitMeter(UnitMeter unitMeter) {
		this.unitMeter = unitMeter;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
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

	public Double getRangeMax() {
		return rangeMax;
	}

	public void setRangeMax(Double rangeMax) {
		this.rangeMax = rangeMax;
	}

	public Double getRangeMin() {
		return rangeMin;
	}

	public void setRangeMin(Double rangeMin) {
		this.rangeMin = rangeMin;
	}
	
	public final byte[] getImage() {
		return image;
	}

	public final void setImage(byte[] image) {
		this.image = image;
	}
}