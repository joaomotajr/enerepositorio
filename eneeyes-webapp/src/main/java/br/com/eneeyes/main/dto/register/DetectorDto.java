package br.com.eneeyes.main.dto.register;

import br.com.eneeyes.main.model.register.Detector;

public class DetectorDto extends BaseDeviceDto {
		
	private Long uid;	
	private TransmitterDto transmitterDto;
	private SensorDto sensorDto;
	private String image;

	public DetectorDto() {		
	}
	
	public DetectorDto(Detector detector) {
		this.uid = detector.getUid();
		this.name = detector.getName();
		this.manufacturerDto = new ManufacturerDto(detector.getManufacturer());
		this.model = detector.getModel();
		
		if (detector.getImage() != null) {
			byte[] image = detector.getImage();
			this.image = new String(image);
		}		
		
		if (detector.getTransmitter()  != null) 
			this.transmitterDto = new TransmitterDto(detector.getTransmitter());		
				
		this.sensorDto = new SensorDto(detector.getSensor());
		
	}

	public final Long getUid() {
		return uid;
	}

	public SensorDto getSensorDto() {
		return sensorDto;
	}

	public void setSensorDto(SensorDto sensorDto) {
		this.sensorDto = sensorDto;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public TransmitterDto getTransmitterDto() {
		return transmitterDto;
	}

	public void setTransmitterDto(TransmitterDto transmitterDto) {
		this.transmitterDto = transmitterDto;
	}
	
	public final String getImage() {
		return image;
	}

	public final void setImage(String image) {
		this.image = image;
	}

}
