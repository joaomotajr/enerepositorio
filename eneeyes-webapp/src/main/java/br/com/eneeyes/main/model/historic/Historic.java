package br.com.eneeyes.main.model.historic;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import br.com.eneeyes.main.dto.HistoricDto;
import br.com.eneeyes.main.model.enums.LogOrigem;

@Entity
@Table(name = "historic")
@org.hibernate.annotations.Table(
		   appliesTo = "historic",
		   indexes = {
		      @Index(name="idxHistoricDate", columnNames = "LAST_UPDATE"),		      
		      @Index(name="idxHistoricCompanyDeviceAndDate", columnNames = {"COMPANY_DEVICE_ID", "LAST_UPDATE"})
		   }
		)
public class Historic {

    public Historic() {
    	
    }
    
    public Historic(HistoricDto dto) {
    	
    	this.uid = dto.getUid();
    	this.lastUpdate = dto.getLastUpdate();
    	this.value = dto.getValue();    	
    	this.companyDeviceId = dto.getCompanyDeviceId();
    	this.logOrigem = dto.getLogOrigem();
    }
    
    @PostUpdate
    public void updateHistoricId() {
        if (getUid() != null) {
             this.setUid(getUid());
        }
    }

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")	
	private Long uid;
	
	@Column(name = "LAST_UPDATE", nullable = false)
	private Date lastUpdate;

	@Column(name = "VALUE", nullable = true)
	private BigDecimal value;
	
	@Column(name="COMPANY_DEVICE_ID", nullable = false)
	private Long companyDeviceId;
	
	@Column(name = "LOG_ORIGEM", nullable = true, columnDefinition = "int default 0")
	private LogOrigem logOrigem;
		
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final Date getLastUpdate() {
		return lastUpdate;
	}

	public final void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public final Long getCompanyDeviceId() {
		return companyDeviceId;
	}

	public final void setCompanyDeviceId(Long companyDeviceId) {
		this.companyDeviceId = companyDeviceId;
	}

	public final LogOrigem getLogOrigem() {
		return logOrigem;
	}

	public final void setLogOrigem(LogOrigem logOrigem) {
		this.logOrigem = logOrigem;
	}	
}
