package br.com.eneeyes.main.dto.register;

import br.com.eneeyes.main.model.register.Controller;

public class ControllerDto extends BaseDeviceDto {
	private Long uid;
	
	public ControllerDto() {
		
	}
	public ControllerDto(Controller controller) {
		
		this.uid = controller.getUid();
		this.name = controller.getName();
		this.manufacturer = controller.getManufacturer();
		this.model = controller.getModel();
	}

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}
}
