package br.com.eneeyes.main.dto.register;

import br.com.eneeyes.main.model.enums.CommProtocol;
import br.com.eneeyes.main.model.register.Transmitter;

public class TransmitterDto extends BaseDeviceDto {

	private Long uid;
	private CommProtocol commProtocol;
	
	public TransmitterDto() {
		
	}
	public TransmitterDto(Transmitter transmitter) {
		
		this.uid = transmitter.getUid();
		this.name = transmitter.getName();
		this.manufacturer = transmitter.getManufacturer();
		this.model = transmitter.getModel();
	}

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final CommProtocol getCommProtocol() {
		return commProtocol;
	}

	public final void setCommProtocol(CommProtocol commProtocol) {
		this.commProtocol = commProtocol;
	}

}
