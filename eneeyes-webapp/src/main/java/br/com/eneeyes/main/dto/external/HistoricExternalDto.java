package br.com.eneeyes.main.dto.external;

public class HistoricExternalDto {
	
	public HistoricExternalDto() {		
	}		

	private Long companyId;	
	public final Long getCompanyId() {
		return companyId;
	}
	public final void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	private Long unit_id;	
	public Long getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(Long unit_id) {
		this.unit_id = unit_id;
	}

	private Long area_id;	
	public Long getArea_id() {
		return area_id;
	}
	public void setArea_id(Long area_id) {
		this.area_id = area_id;
	}
	
	private String companyDetectorName;
	public String getCompanyDetectorName() {
		return companyDetectorName;
	}
	public void setCompanyDetectorName(String companyDetectorName) {
		this.companyDetectorName = companyDetectorName;
	}	
	
	
	

}