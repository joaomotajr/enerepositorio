package br.com.eneeyes.archetype.model.acl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="aln_id_role")
public class Role implements Serializable {
	private static final long serialVersionUID = -6199553165131428687L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_")
	private Long id;
	
	@Column(name="NAME_", unique=true, nullable=false, length=100)
	private String name;
	
	@Column(name="VALUE_", unique=true, nullable=false, length=100)
	@Pattern(regexp="[A-Z]{3,100}")
	private String value;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="aln_id_role_permissions", 
		joinColumns= @JoinColumn(name="ROLE_ID_", referencedColumnName="ID_"), 
		inverseJoinColumns= @JoinColumn(name="PERMISSION_ID_", referencedColumnName="ID_"))
	private Set<Permission> permissions = new HashSet<Permission>();
	
	@Column(name="STATUS_", nullable=false)
	@Enumerated(EnumType.STRING)
	private RoleStatus status;
	
	public Role() {
		super();
	}

	public Role(Long id) {
		super();
		this.id = id;
	}

	public Role(String name, String value, RoleStatus status) {
		super();
		this.name = name;
		this.value = value;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public RoleStatus getStatus() {
		return status;
	}

	public void setStatus(RoleStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", value=" + value
				+ ", permissions=" + permissions + ", statusSendMessage=" + status + "]";
	}
}