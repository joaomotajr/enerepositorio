package br.com.eneeyes.main.model.historic.hour;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

import br.com.eneeyes.main.model.historic.IHistoricGroup;

@Entity
@Subselect("select * from historic_b_hour_view")
public class HistoricBHourView implements IHistoricGroup {

    public HistoricBHourView() {
    	
    }     

    @Id	
	@Column(name = "UID")	
	private Long uid;	
	
	@Column(name="COMPANY_DEVICE_ID")
	private Long companyDeviceId;
	
	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;
			
	@Column(name="MAX_VALUE")
	private BigDecimal maxValue;
	
	@Column(name="MIN_VALUE")
	private BigDecimal minValue;
	
	@Column(name="AVG_VALUE")
	private BigDecimal avgValue;
	
	@Column(name="SUM_VALUE")
	private BigDecimal sumValue;

	public Long getUid() {
		return uid;
	}

	public Long getCompanyDeviceId() {
		return companyDeviceId;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public BigDecimal getMaxValue() {
		return maxValue;
	}

	public BigDecimal getMinValue() {
		return minValue;
	}

	public BigDecimal getAvgValue() {
		return avgValue;
	}

	public BigDecimal getSumValue() {
		return sumValue;
	}	
}
