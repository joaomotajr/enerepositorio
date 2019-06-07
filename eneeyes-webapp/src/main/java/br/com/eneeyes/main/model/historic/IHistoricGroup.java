package br.com.eneeyes.main.model.historic;

import java.math.BigDecimal;
import java.util.Date;

public interface IHistoricGroup {	
	public Long getUid();
	public Date getLastUpdate();	
	public BigDecimal getMaxValue();		
	public BigDecimal getMinValue();	
	public BigDecimal getAvgValue();	
	public BigDecimal getSumValue();	
	public Long getCompanyDeviceId();		
}
