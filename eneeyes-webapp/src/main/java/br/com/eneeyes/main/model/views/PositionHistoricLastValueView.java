package br.com.eneeyes.main.model.views;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.views.PositionHistoricLastValueViewDto;


@Entity
@Table(name = "position_historic_last_value_view")
public class PositionHistoricLastValueView {

    public PositionHistoricLastValueView() {
    	    	
    }     
    
	public PositionHistoricLastValueView(PositionHistoricLastValueViewDto dto) {		
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
