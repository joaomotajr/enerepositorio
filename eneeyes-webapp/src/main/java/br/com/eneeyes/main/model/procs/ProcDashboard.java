package br.com.eneeyes.main.model.procs;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

/**
 * The persistent class for the view_atividade database table.
 * 
 */

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		  name = "findDashBoardProcedure", 
		  procedureName = "dashboard", 
		  resultClasses = { ProcDashboard.class }, 
		  parameters = { 
				  @StoredProcedureParameter(name = "rows", type = String.class, mode = ParameterMode.IN) }) 
	})

@Entity(name = "ProcDashboard")
public class ProcDashboard implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name = "UID", unique=true, nullable=false)
	private Long uid;		
	
	@Column(name="HISTORIC_ID")
	private Long historicId;
	
	@Column(name="POSITION_ID")
    private Long positionId;
    
	@Column(name="LAST_UPDATE")
    private Date lastUpdate;
    		
	@Column(name = "VALUE")
	private BigDecimal value;

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

	public final Long getHistoricId() {
		return historicId;
	}

	public final void setHistoricId(Long historicId) {
		this.historicId = historicId;
	}

	public final Long getPositionId() {
		return positionId;
	}

	public final void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public final Date getLastUpdate() {
		return lastUpdate;
	}

	public final void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public final BigDecimal getValue() {
		return value;
	}

	public final void setValue(BigDecimal value) {
		this.value = value;
	}
		
}