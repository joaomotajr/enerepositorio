package br.com.eneeyes.main.model.views;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Subselect;

import br.com.eneeyes.main.model.Alarm;

@Entity
@Subselect("select * from companydevice_alarm_view")
public class CompanyDeviceAlarmView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public CompanyDeviceAlarmView() {		
	
	}
	
	@Id
	@Column(name = "UID")	
	private Long uid;
		
	
	@ManyToOne
    @JoinColumn(name="ALARM_ID")
    private Alarm alarm;	

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}
	
}