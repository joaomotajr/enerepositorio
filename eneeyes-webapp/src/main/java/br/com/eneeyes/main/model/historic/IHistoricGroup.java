package br.com.eneeyes.main.model.historic;

import java.math.BigDecimal;
import java.util.Date;

public interface IHistoricGroup {	
	
	public Long getUid() ;
	
	public void setUid(Long uid);

	public Date getLastUpdate() ;

	public void setLastUpdate(Date lastUpdate) ;
	
	public BigDecimal getMaxValue();
	
	public void setMaxValue(BigDecimal value);
	
	public BigDecimal getMinValue();
	
	public void setMinValue(BigDecimal value);

//	public Long getCompanyDetectorId() ;
	
	public Long getCompanyDeviceId() ;
	
	//public void setCompanyDetectorId(Long companyDetectorId);
	
	public void setCompanyDeviceId(Long companyDeviceId);

//	public Long getSensorId();
//
//	public void setSensorId(Long sensorId);
	
}
