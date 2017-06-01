package br.com.eneeyes.archetype.model;

import java.util.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> login;
	public static volatile SingularAttribute<User, String> hash;
	public static volatile SingularAttribute<User, String> nickname;
	public static volatile SetAttribute<User, Role> roles;
	public static volatile SingularAttribute<User, Date> createDate;
	public static volatile SingularAttribute<User, UserStatus> status;
}
