package br.com.eneeyes.main.model.historic;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import br.com.eneeyes.main.model.enums.LogOrigem;

@Entity
@Table(name = "historic_b")
@org.hibernate.annotations.Table(
		   appliesTo = "historic_b",
		   indexes = {
		      @Index(name="idxHistoricBDate", columnNames = "LAST_UPDATE"),		      
		      @Index(name="idxHistoricBCompanySensorAndDate", columnNames = {"COMPANY_DETECTOR_ID", "SENSOR_ID", "LAST_UPDATE"})
		   }
		)
public class HistoricB {
   
	@Id	
	@Column(name = "UID")	
	private Long uid;
	
	@Column(name = "LAST_UPDATE", nullable = false)
	private Date lastUpdate;

	@Column(name = "VALUE", nullable = true)
	private BigDecimal value;
	
	@Column(name="COMPANY_DETECTOR_ID", nullable = false)
	private Long companyDetectorId;
	
	@Column(name="SENSOR_ID", nullable = false)
	private Long sensorId;
	
	@Column(name = "LOG_ORIGEM", nullable = true)
	private LogOrigem logOrigem;
		
}
