package br.com.eneeyes.main.model.historic;

import java.math.BigDecimal;
import java.util.Date;

import br.com.eneeyes.main.model.enums.LogOrigem;

public interface IHistoric {
	
	
	public Long getUid();
	
	public Date getLastUpdate();

	public BigDecimal getValue();
	
	public Long getCompanyDeviceId();
	
	public LogOrigem getLogOrigem();	
}
