package br.com.eneeyes.archetype.model.acl;

public enum RoleType {
	
	USER(0l),
	ADMINISTRATOR(1l), 
	CONTRACTOR(2l), 
	MANAGER(3l),
	OPERATOR(4l);
	
	private Long id;
	
	RoleType(Long id) {
		setId(id);
	}

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}
	
	public String getName(Long id) {
		String name = "";
		switch (id.intValue()) {
		case 1:
			name = "administrator";
			break;
		case 2:
			name = "contractor";
			break;
		case 3:
			name = "manager";
			break;
		case 4:
			name = "operator";
			break;			
		}
		return name;
	}
	
}
