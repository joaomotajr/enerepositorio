package br.com.eneeyes.main.model.historic.exunion;

import java.math.BigDecimal;
import java.util.Date;

public interface IHistoricDayGroup {	
	public Long getUid();
	public Date getLastUpdate();	
	public BigDecimal getMaxValue();		
	public BigDecimal getMinValue();	
	public BigDecimal getAvgValue();	
	public BigDecimal getSumValue();	
	public Long getCompanyDeviceId();		
}
