package br.com.eneeyes.main.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "historic_a")
@org.hibernate.annotations.Table(
		   appliesTo = "historic_a",
		   indexes = {
		      @Index(name="idxHistoricADate", columnNames = "LAST_UPDATE"),		      
		      @Index(name="idxHistoricACompanySensorAndDate", columnNames = {"COMPANY_DETECTOR_ID", "SENSOR_ID", "LAST_UPDATE"})
		   }
		)
public class Historic_a {
   
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

}
