package br.com.eneeyes.main.dto.register;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.eneeyes.main.model.register.Detector;
import br.com.eneeyes.main.model.register.Sensor;
import br.com.eneeyes.main.model.register.Transmitter;

public class DetectorDto extends BaseDeviceDto {
		
	private Long uid;	
	private Transmitter transmitter;
	private List<SensorDto> sensorsDto = new ArrayList<SensorDto>();
	
	public DetectorDto() {
		
	}
	
	public DetectorDto(Detector detector) {
		this.uid = detector.getUid();
		this.name = detector.getName();
		this.manufacturer = detector.getManufacturer();
		this.model = detector.getModel();
		
		if (detector.getTransmitter()  != null) 
			this.transmitter = detector.getTransmitter();		
				
		if(detector.getSensors() != null)		
			this.sensorsDto = setSensorsDto(detector.getSensors());		
		
	}
	
	private final List<SensorDto> setSensorsDto(Set<Sensor> sensors) {
		List<SensorDto> lista = new ArrayList<SensorDto>();
		
		if(sensors != null && !sensors.isEmpty()) {
		
			Iterator<Sensor> itr = sensors.iterator();
			
			while (itr.hasNext()) {
				SensorDto dto = new SensorDto(itr.next());
				lista.add(dto);
			}
		}
		
		return lista;
	}
	
	public final List<SensorDto> getSensorsDto() {
		return sensorsDto;
	}

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final Transmitter getTransmitter() {
		return transmitter;
	}

	public final void setTransmitter(Transmitter transmitter) {
		this.transmitter = transmitter;
	}

}
