package br.com.eneeyes.main.dto.register;

import br.com.eneeyes.main.model.register.Gas;

public class GasDto {
	
	private Long uid;	
	private String name;	
	private String cas;	
	private String formula;
//	private UnitMeterGases unitMeterGases;	
	
	public GasDto() {
		
	}
	
	public GasDto(Gas gas) {
		this.uid = gas.getUid();	
		this.name = gas.getName();
		this.cas = gas.getCas();
		this.formula = gas.getFormula();
//		this.unitMeterGases = gas.getUnitMeterGases();
	}

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getCas() {
		return cas;
	}

	public final void setCas(String cas) {
		this.cas = cas;
	}

	public final String getFormula() {
		return formula;
	}

	public final void setFormula(String formula) {
		this.formula = formula;
	}

//	public final UnitMeterGases getUnitMeterGases() {
//		return unitMeterGases;
//	}
//
//	public final void setUnitMeterGases(UnitMeterGases unitMeterGases) {
//		this.unitMeterGases = unitMeterGases;
//	}	
}
