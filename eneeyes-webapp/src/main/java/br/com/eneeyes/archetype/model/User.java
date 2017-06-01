package br.com.eneeyes.archetype.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import br.com.eneeyes.archetype.dto.UserDto;
import br.com.eneeyes.main.model.views.CompanyView;

@Entity
@Table(name="aln_id_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_")
	private Long id;
	
	@Column(name="CPF_")
	private String cpf;

	@Column(name="DISPLAYNAME_")
	private String displayName;
	
	@Column(name="NICKNAME_", length=100)
	private String nickname;
	
	@Column(name="FONE_")
	private String fone;
	
	@Column(name="CELL_")
	private String cell;
	
	@Column(name="EMAIL_")
	private String email;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="aln_id_user_roles", 
	joinColumns= @JoinColumn(name="USER_ID_", referencedColumnName="ID_"), 
	inverseJoinColumns= @JoinColumn(name="ROLE_ID_", referencedColumnName="ID_"))
	private Set<Role> roles = new HashSet<Role>();
		
	@Column(name="LOGIN_", unique=true, nullable=false, length=100)
	@NotBlank
	private String login;
	
	@Column(name="HASH_", nullable=false, length=40)
	@Pattern(regexp="[a-z0-9\\-]{30,40}")
	private String hash;
	
	@Column(name="STATUS_", nullable=false)
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	@Column(name="CREATE_DATE_", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_ID", nullable = true)
	private CompanyView company;

	public User() {
		super();
	}
	
	public User(String login, String hash, String nickname, UserStatus status) {
		super();
		this.login = login;
		this.hash = hash;
		this.nickname = nickname;
		this.status = status;
		this.createDate = new Date();
	}

	public User(UserDto userDto) {
		this.id = userDto.getId();
		this.cpf = userDto.getCpf();
		this.displayName = userDto.getDisplayName();
		this.nickname = userDto.getNickname();
		this.fone = userDto.getFone();
		this.cell = userDto.getCell();
		this.email = userDto.getEmail();
		
		if(userDto.getRoles() != null)
			this.roles = parseRoles(userDto.getRoles());
		
		this.login = userDto.getLogin();
		this.hash = userDto.getHash();
		this.status = userDto.getStatus();
		this.createDate = userDto.getCreateDate();
		this.company = userDto.getCompanyDto();
	}
	
	public final void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
	private final Set<Role> parseRoles(List<Role> roles) {		
		Set<Role> lista = new HashSet<Role>();		
		
		for (Role item   : roles) {
			
			Role role = new Role();
			role.setId(item.getId());
			role.setName(item.getName());
		
			lista.add(role);			
		}		
		return lista;		
	}
	
	/**
	 * @return the id
	 */
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
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @return the login
	 */
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
	 * @return the status
	 */
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
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public final CompanyView getCompany() {
		return company;
	}

	public final void setCompany(CompanyView company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", hash=" + hash
				+ ", nickname=" + nickname + ", roles=" + roles + ", statusSendMessage="
				+ status + ", createDate=" + createDate + "]";
	}

}