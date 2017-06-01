package br.com.eneeyes.archetype.model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
public class Role_ {
	public static volatile SingularAttribute<Role, Long> id;
	public static volatile SingularAttribute<Role, String> name;
	public static volatile SingularAttribute<Role, String> value;
	public static volatile SetAttribute<Role, Permission> permissions;
	public static volatile SetAttribute<Role, User> users;
}
