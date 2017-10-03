package br.com.eneeyes.main.model.procs;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import br.com.eneeyes.main.model.views.DashCompaniesPosition;


/**
 * The persistent class for the view_atividade database table.
 * 
 */
@Entity
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		  name = "findDashBoardProcedure", procedureName = "dashboard", 
		  resultClasses = { ProcDashboard.class }, 
		  parameters = { 
				  @StoredProcedureParameter( name = "rows", type = String.class, mode = ParameterMode.IN) }) 
	})
public class ProcDashboard {
	
	@Id	
	@Column(name = "UID", unique=true, nullable=false)
	private Long uid;
		
	@ManyToOne
    @JoinColumn(name="POSITION_ID")
    private DashCompaniesPosition dashCompaniesPosition;
	
	@Column(name = "VALUE")
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