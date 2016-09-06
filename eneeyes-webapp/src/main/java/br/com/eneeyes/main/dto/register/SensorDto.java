package br.com.eneeyes.main.dto.register;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.eneeyes.main.model.enums.DetectionType;
import br.com.eneeyes.main.model.enums.UnitMeterGases;
import br.com.eneeyes.main.model.register.Gas;
import br.com.eneeyes.main.model.register.Sensor;

public class SensorDto extends BaseDeviceDto {
	
	private Long uid;
	private DetectionType detectionType;
	private List<GasDto> gasesDto = new ArrayList<GasDto>();
	private UnitMeterGases unitMeterGases;
	private Double RangeMax;	
	private Double RangeMin;			
	private Double RangeUnit;	
	
	public SensorDto() {
		super();
	}
	
	public SensorDto(Sensor sensor) {
		super();
		
		this.uid = sensor.getUid();
		this.name = sensor.getName();
		this.manufacturerDto = new ManufacturerDto(sensor.getManufacturer());
		this.model = sensor.getModel();
		this.detectionType = sensor.getDetectionType();
		
		if(sensor.getGases() != null)		
			this.gasesDto = parseGasesDto(sensor.getGases());
		
		this.unitMeterGases = sensor.getUnitMeterGases();
       	this.RangeMax = sensor.getRangeMax();
       	this.RangeMin = sensor.getRangeMin();
       	this.RangeUnit = sensor.getRangeUnit();
	}
	
	private final List<GasDto> parseGasesDto(Set<Gas> gases) {
		List<GasDto> lista = new ArrayList<GasDto>();
		
		if(gases != null && !gases.isEmpty()) {
		
			Iterator<Gas> itr = gases.iterator();
			
			while (itr.hasNext()) {
				GasDto dto = new GasDto(itr.next());
				lista.add(dto);
			}
		}
		
		return lista;
	}
	
	public final List<GasDto> getGasesDto() {
		return gasesDto;
	}

	public final void setGasesDto(List<GasDto> gasesDto) {
		this.gasesDto = gasesDto;
	}
	
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
		
		if (detectionType == null ) {			
			this.detectionType = DetectionType.OUTROS;
		}	
		else { 
			this.detectionType = detectionType;
		}
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
