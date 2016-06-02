package br.com.eneeyes.main.result;

import java.util.List;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.CompanyDto;

public class CompanyResult extends BasicResult<CompanyDto> {
			
	private List<CompanyDto> list;
	private CompanyDto companyDto;
	
	public List<CompanyDto> getList() {
		return list;
	}

	public void setList(List<CompanyDto> list) {
		this.list = list;
	}

	public CompanyDto getCompany() {
		return companyDto;
	}

	public void setCompany(CompanyDto company) {
		this.companyDto = company;
	}
	
	
	@Override
	public String toString() {
		return "UserResult{" +
				"Company=" + companyDto + "," +
				"resultType=" + getResultType() + "," +
				'}';
	}

	@Override
	public ResultMessageType getResultType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResultType(ResultMessageType resultType) {
		// TODO Auto-generated method stub
		
	}
	
}
