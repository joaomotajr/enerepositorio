package br.com.eneeyes.main.result;

import java.util.List;

import br.com.eneeyes.main.result.MainBaseResult;
import br.com.eneeyes.main.model.Company;
import br.com.eneeyes.main.dto.CompanyDto;

public class CompanyResult extends MainBaseResult<Company> {
		
	private Company company;
	private List<CompanyDto> listCompany;

	public CompanyResult() {
	}
	
	public Company getUser() {
		return company;
	}

	public void setUser(Company company) {
		this.company = company;
	}

	public List<CompanyDto> getListCompany() {
		return listCompany;
	}

	public void setListCompany(List<CompanyDto> listCompany) {
		this.listCompany = listCompany;
	}
	
	@Override
	public String toString() {
		return "UserResult{" +
				"Company=" + company + "," +
				"resultType=" + getResultType() + "," +
				'}';
	}
	
}
