package br.com.eneeyes.main.model.historic;

import java.math.BigDecimal;
import java.util.Date;

import br.com.eneeyes.main.model.enums.LogOrigem;

public interface IHistoric {
	
	
	public Long getUid() ;
	
	public void setUid(Long uid);

	public Date getLastUpdate() ;

	public void setLastUpdate(Date lastUpdate) ;

	public BigDecimal getValue() ;

	public void setValue(BigDecimal value) ;

	public Long getCompanyDetectorId() ;
	
	public void setCompanyDetectorId(Long companyDetectorId);

	public Long getSensorId();

	public void setSensorId(Long sensorId);

	public LogOrigem getLogOrigem();

	public void setLogOrigem(LogOrigem logOrigem);
	

}
