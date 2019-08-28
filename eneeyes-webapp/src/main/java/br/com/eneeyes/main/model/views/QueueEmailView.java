package br.com.eneeyes.main.model.views;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Null;

import org.hibernate.annotations.Subselect;

import br.com.eneeyes.main.model.AlarmEmail;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.EmailStatus;
import br.com.eneeyes.main.model.state.PerfilAlarm;

@Entity
@Subselect("select * from queue_email_view")
public class QueueEmailView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public QueueEmailView() {		
	
	}	
	
	@Id
	@Column(name = "uid")
	private Long uid;

	@Column(name = "email_status")
	private EmailStatus emailStatus;
	private String alarm_name;	
	private String alarm_id;
	private Long company_detector_id;
	private String company_detector_name;
	private String company_detector_local;
	private String company_name;
	private String unit_name;
	private String area_name;
	private String area_local;	
	private BigDecimal last_value;
	private Date last_Update;
	
	@Column(name="device_type")
	private String deviceType;
	
	@Column(name = "alarm_type")
	private AlarmType alarmType;
	
	private String gas_name;
	
	@Column(name="UNIT_METER_DESCRIPTION")
	private String unitMeterDescription;	
	
	@Column(name="UNIT_METER_SYMBOL")
	private String unitMeterSymbol;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="PERFIL_ALARM_ID1", nullable = false)
	private PerfilAlarm perfilAlarm1;	
		
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="PERFIL_ALARM_ID2")
	@Null
	private PerfilAlarm perfilAlarm2;
		
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@Null
	@JoinColumn(name="PERFIL_ALARM_ID3")
	private PerfilAlarm perfilAlarm3;
	
	@OneToMany(targetEntity = AlarmEmail.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="alarm_id", referencedColumnName="alarm_id")
	private Set<AlarmEmail> alarmEmails = new HashSet<AlarmEmail>();
		
	public Set<AlarmEmail> getAlarmEmails() {
		return alarmEmails;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getUid() {
		return uid;
	}

	public EmailStatus getEmailStatus() {
		return emailStatus;
	}

	public String getAlarm_name() {
		return alarm_name;
	}

	public String getAlarm_id() {
		return alarm_id;
	}
	
	public Long getCompany_detector_id() {
		return company_detector_id;
	}

	public String getCompany_detector_name() {
		return company_detector_name;
	}

	public String getCompany_detector_local() {
		return company_detector_local;
	}

	public String getCompany_name() {
		return company_name;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public String getArea_name() {
		return area_name;
	}

	public String getArea_local() {
		return area_local;
	}

	public BigDecimal getLast_value() {
		return last_value;
	}

	public Date getLast_Update() {
		return last_Update;
	}

	public AlarmType getAlarmType() {
		return alarmType;
	}

	public String getGas_name() {
		return gas_name;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public String getUnitMeterDescription() {
		return unitMeterDescription;
	}

	public String getUnitMeterSymbol() {
		return unitMeterSymbol;
	}
	
	public PerfilAlarm getPerfilAlarm1() {
		return perfilAlarm1;
	}

	public PerfilAlarm getPerfilAlarm2() {
		return perfilAlarm2;
	}

	public PerfilAlarm getPerfilAlarm3() {
		return perfilAlarm3;
	}	
}