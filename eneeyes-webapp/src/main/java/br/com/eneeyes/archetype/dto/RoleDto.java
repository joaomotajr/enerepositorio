package br.com.eneeyes.archetype.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.eneeyes.archetype.model.Permission;
import br.com.eneeyes.archetype.model.Role;


public class RoleDto  {	
	
	private Long id;	
	private String name;	
	private Set<Permission> permissions = new HashSet<Permission>();
	
	public RoleDto() {
		super();
	}

	public RoleDto(Long id) {
		super();
		this.id = id;
	}
	
	public RoleDto(Role role) {
		
		this.id = role.getId();	
		this.name = role.getName();
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