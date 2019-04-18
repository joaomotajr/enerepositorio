package br.com.eneeyes.main.model.views;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.SigmaStatus;

@Entity
@Subselect("select * from queue_sigma_view")
public class QueueSigmaView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public QueueSigmaView() {	
	}	
	
	@Id
	@Column(name = "uid")
	private Long uid;

	@Column(name = "sigma_status")
	private SigmaStatus sigmaStatus;

	private String alarm_name;
	private String celular;
	private Long company_detector_id;
	private String company_detector_name;
	private String company_detector_local;
	private BigDecimal last_value;
	private Date last_Update;	
	
	@Column(name = "alarm_type")
	private AlarmType alarmType;
	
	@Column(name="device_type")
	private int deviceType;		
	
	public Long getUid() {
		return uid;
	}
	
	public final SigmaStatus getSigmaStatus() {
		return sigmaStatus;
	}
	
	public final String getAlarm_name() {
		return alarm_name;
	}
	
	public final String getCelular() {
		return celular;
	}
	
	public Long getCompany_detector_id() {
		return company_detector_id;
	}	

	public final String getCompany_detector_name() {
		return company_detector_name;
	}
	
	public String getCompany_detector_local() {
		return company_detector_local;
	}
	
	public final BigDecimal getLast_value() {
		return last_value;
	}

	public final Date getLast_Update() {
		return last_Update;
	}
	
	public final AlarmType getAlarmType() {
		return alarmType;
	}
	
	public final int getDeviceType() {
		return deviceType;
	}
}