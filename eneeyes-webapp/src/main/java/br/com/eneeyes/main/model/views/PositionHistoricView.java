package br.com.eneeyes.main.model.views;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "position_historic_view")
public class PositionHistoricView {

    public PositionHistoricView() {
    	
    }     

	@Id	
	@Column(name = "UID")	
	private Long uid;
		
	@ManyToOne
    @JoinColumn(name="POSITION_ID")
    private DashCompaniesPosition dashCompaniesPosition;
		
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
}
