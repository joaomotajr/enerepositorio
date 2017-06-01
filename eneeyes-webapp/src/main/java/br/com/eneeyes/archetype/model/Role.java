package br.com.eneeyes.archetype.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="aln_id_role_permissions", 
		joinColumns= @JoinColumn(name="ROLE_ID_", referencedColumnName="ID_"), 
		inverseJoinColumns= @JoinColumn(name="PERMISSION_ID_", referencedColumnName="ID_"))
	private Set<Permission> permissions = new HashSet<Permission>();
	
	public Role() {
		super();
	}

	public Role(Long id) {
		super();
		this.id = id;
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

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "permissions=" + permissions + "]";
	}
}