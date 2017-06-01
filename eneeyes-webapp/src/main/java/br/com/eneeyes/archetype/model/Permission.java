package br.com.eneeyes.archetype.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="aln_id_permission")
public class Permission implements Serializable {
	private static final long serialVersionUID = -2957539388044985595L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_")
	private Long id;
	
	@Column(name="NAME_", unique=true, nullable=false, length=100)
	private String name;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PARENT_ID_")
	private Permission parent;
	
	public Permission() {
		super();
	}

	public Permission(String name, String description,
			String value, Permission parent) {
		super();
		this.name = name;
		this.parent = parent;
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

	public Permission getParent() {
		return parent;
	}

	public void setParent(Permission parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", parent=" + parent + "]";
	}
}