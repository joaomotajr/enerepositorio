package br.com.eneeyes.main.service.scheduller;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class checkDeviceCalibration extends BaseService {

	@Override
	@Schedule(dayOfWeek = segundaASexta, hour = seteETrezeHoras)
	public void schedule() {
		// TODO Auto-generated method stub
		super.init();
	}

}
