package br.com.eneeyes.main.dto.register;

import br.com.eneeyes.main.model.enums.DetectionType;
import br.com.eneeyes.main.model.register.Sensor;
import br.com.eneeyes.main.model.state.UnitMeter;

public class SensorDto extends BaseDeviceDto implements Comparable<SensorDto> {
	
	private Long uid;
	private DetectionType detectionType;
	private GasDto gasDto;
	private UnitMeter unitMeter;
	private Double rangeMax;	
	private Double rangeMin;			
	private Double rangeUnit;
	private DetectorDto detectorDto;
	
	public SensorDto() {		
	}
	
	public SensorDto(Sensor sensor) {
		super();
		
		this.uid = sensor.getUid();
		this.name = sensor.getName();		
		this.manufacturerDto = new ManufacturerDto(sensor.getManufacturer());
		this.model = sensor.getModel();
		this.detectionType = sensor.getDetectionType();		
		this.gasDto = new GasDto(sensor.getGas());	
		this.unitMeter = sensor.getUnitMeter();
       	this.rangeMax = sensor.getRangeMax();
       	this.rangeMin = sensor.getRangeMin();
       	this.rangeUnit = sensor.getRangeUnit();
	}
	
	public final Long getUid() {
		return uid;
	}

	public GasDto getGasDto() {
		return gasDto;
	}

	public void setGasDto(GasDto gasDto) {
		this.gasDto = gasDto;
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
	
	public final UnitMeter getUnitMeter() {
		return unitMeter;
	}
	
	public final void setUnitMeter(UnitMeter unitMeter) {
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
	
	public final DetectorDto getDetectorDto() {
		return detectorDto;
	}

	public void setDetectorDto(DetectorDto detectorDto) {
		this.detectorDto = detectorDto;
	}
	
	@Override
	public int compareTo(SensorDto sensorDto) {
		return sensorDto.getUid().compareTo(this.uid);		
	}
}
