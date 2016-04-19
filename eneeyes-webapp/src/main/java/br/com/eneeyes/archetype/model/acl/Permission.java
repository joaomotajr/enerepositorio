package br.com.eneeyes.archetype.model.acl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

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
	
	@Column(name="DESCRIPTION_")
	private String description;
	
	@Column(name="VALUE_", unique=true, nullable=false, length=100)
	@Pattern(regexp="[A-Z]{3, 100}")
	private String value;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PARENT_ID_")
	private Permission parent;

	@Column(name="STATUS_", nullable=false)
	@Enumerated(EnumType.STRING)
	private PermissionStatus status;
	
	public Permission() {
		super();
	}

	public Permission(String name, String description,
			String value, Permission parent, PermissionStatus status) {
		super();
		this.name = name;
		this.description = description;
		this.value = value;
		this.parent = parent;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Permission getParent() {
		return parent;
	}

	public void setParent(Permission parent) {
		this.parent = parent;
	}

	public PermissionStatus getStatus() {
		return status;
	}

	public void setStatus(PermissionStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", description="
				+ description + ", value=" + value + ", parent=" + parent
				+ ", statusSendMessage=" + status + "]";
	}
}