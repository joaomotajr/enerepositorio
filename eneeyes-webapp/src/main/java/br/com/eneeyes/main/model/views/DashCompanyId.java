package br.com.eneeyes.main.model.views;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DashCompanyId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "company_id")
	private Long companyId;
	
	@Column(name = "company_Device_id")
	private Long companyDeviceId;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result
				+ ((companyDeviceId == null) ? 0 : companyDeviceId.hashCode());
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
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (companyDeviceId == null) {
			if (other.companyDeviceId != null)
				return false;
		} else if (!companyDeviceId.equals(other.companyDeviceId))
			return false;
		return true;
	}
	


}
