package br.com.eneeyes.archetype.model.acl;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Permission.class)
public class Permission_ {
	public static volatile SingularAttribute<Permission, Long> id;
	public static volatile SingularAttribute<Permission, String> name;
	public static volatile SingularAttribute<Permission, String> description;
	public static volatile SingularAttribute<Permission, String> value;
	public static volatile SingularAttribute<Permission, Permission> parent;
	public static volatile SetAttribute<Permission, Role> roles;
	public static volatile SingularAttribute<Permission, PermissionStatus> status;
}
