package br.com.eneeyes.main.model.historic;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log_event")
public class logEvent {
   
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")	
	private Long uid;
	
	@Column(name = "DATE_TIME")
	private Date dateTime;

	@Column(name = "EVENT_NAME")
	private String eventName;
	
	@Column(name = "ROWS_AFFECTED")
	private Integer rowsAffected;

}
