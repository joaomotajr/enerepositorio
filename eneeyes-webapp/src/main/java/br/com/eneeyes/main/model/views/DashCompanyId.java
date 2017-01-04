package br.com.eneeyes.main.model.views;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DashCompanyId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "company_id")
	private Long company_id;
	
	@Column(name = "companyDetector_id")
	private Long companyDetector_id;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((company_id == null) ? 0 : company_id.hashCode());
		result = prime * result
				+ ((companyDetector_id == null) ? 0 : companyDetector_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DashCompanyId other = (DashCompanyId) obj;
		if (company_id == null) {
			if (other.company_id != null)
				return false;
		} else if (!company_id.equals(other.company_id))
			return false;
		if (companyDetector_id == null) {
			if (other.companyDetector_id != null)
				return false;
		} else if (!companyDetector_id.equals(other.companyDetector_id))
			return false;
		return true;
	}
	


}
