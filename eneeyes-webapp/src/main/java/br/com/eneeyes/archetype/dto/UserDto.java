package br.com.eneeyes.archetype.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.eneeyes.archetype.model.Role;
import br.com.eneeyes.archetype.model.User;
import br.com.eneeyes.archetype.model.UserStatus;
import br.com.eneeyes.archetype.utils.MessageDigester;
import br.com.eneeyes.main.model.views.CompanyView;

@XmlRootElement
public class UserDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String cpf;
	private String displayName;
	private String nickname;
	private String fone;
	private String cell;
	private String email;	
	private List<Role> roles;	
	private String login;
	private String hash;
	private UserStatus status;
	private Date createDate;	
	private CompanyView companyDto;
	
	public UserDto() {
	}

	public UserDto(User user) {
		this.id = user.getId();
		this.cpf = user.getCpf();

		this.displayName = user.getDisplayName();
		this.nickname = user.getNickname();
		this.fone = user.getFone();
		this.cell = user.getCell();
		this.email = user.getEmail();
		
		if(user.getRoles() != null)
			this.roles = parseRoles(user.getRoles());
		
		this.login = user.getLogin();
		this.hash = user.getHash();
		this.status = user.getStatus();
		this.createDate = user.getCreateDate();
		this.companyDto = user.getCompany();
	}
	
	private final List<Role> parseRoles(Set<Role> roles) {		
		List<Role> lista = new ArrayList<Role>();		
		
		for (Role item   : roles) {
			
			Role role = new Role();
			role.setId(item.getId());
			role.setName(item.getName());
		
			lista.add(role);			
		}		
		return lista;		
	}
	
	public final List<Role> getRoles() {
		return roles;
	}

	public final void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	/**
	 * @return the id
	 */
	@XmlElement
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the cpf
	 */
	@XmlElement
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
	/**
	 * @return the displayName
	 */
	@XmlElement
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	/**
	 * @return the nickname
	 */
	@XmlElement
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * @return the fone
	 */
	@XmlElement
	public String getFone() {
		return fone;
	}

	/**
	 * @param fone the fone to set
	 */
	public void setFone(String fone) {
		this.fone = fone;
	}

	/**
	 * @return the cell
	 */
	@XmlElement
	public String getCell() {
		return cell;
	}

	/**
	 * @param cell the cell to set
	 */
	public void setCell(String cell) {
		this.cell = cell;
	}

	/**
	 * @return the email
	 */
	@XmlElement
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
			
	/**
	 * @return the login
	 */
	@XmlElement
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the hash
	 */
	@XmlElement
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	/**
	 * @param hash the hash to set
	 */
	public void setHashDigestSha1() {
		try {
			setHash(MessageDigester.digestSha1(getHash()));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the status
	 */
	@XmlElement
	public UserStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
	/**
	 * @return the createDate
	 */
	@XmlElement
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public final CompanyView getCompanyDto() {
		return companyDto;
	}

	public final void setCompanyDto(CompanyView companyDto) {
		this.companyDto = companyDto;
	}
}
