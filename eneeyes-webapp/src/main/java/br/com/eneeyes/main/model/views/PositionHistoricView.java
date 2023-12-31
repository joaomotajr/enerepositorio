package br.com.eneeyes.main.model.views;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Subselect;

import br.com.eneeyes.main.dto.views.PositionHistoricViewDto;

@Entity
@Subselect("select * from position_historic_view")
public class PositionHistoricView {

    public PositionHistoricView() {
    	    	
    }     
    
	public PositionHistoricView(PositionHistoricViewDto dto) {		
		this.uid = dto.getUid();
		this.lastUpdate = dto.getLastUpdate();	
		this.value = dto.getValue();		
	}

	@Id	
	@Column(name = "UID")	
	private Long uid;
		
	@ManyToOne
    @JoinColumn(name="POSITION_ID")
    private DashCompaniesPosition dashCompaniesPosition;
	
	@Column(name = "LAST_UPDATE", nullable = false)
	private Date lastUpdate;
		
	@Column(name="VALUE")
	private BigDecimal value;
	
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

	public final BigDecimal getValue() {
		return value;
	}

	public final void setValue(BigDecimal value) {
		this.value = value;
	}

	public final Date getLastUpdate() {
		return lastUpdate;
	}

	public final void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
}
