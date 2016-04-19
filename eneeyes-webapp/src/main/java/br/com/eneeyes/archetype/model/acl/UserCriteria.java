package br.com.eneeyes.archetype.model.acl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.eneeyes.archetype.model.Criteria;

public class UserCriteria extends Criteria<User> {

	private Log log = LogFactory.getLog(getClass());
	
	private String login;
	
	private String nickname;
	
	private String createDateStart;
	
	private String createDateEnd;
	
	private UserStatus status;

	private String roleName;
	
	private String roleValue;
	
	public UserCriteria() {
		super();
		setEntity(new User());
	}

	public void build() {
		if(getLogin() != null) {
			String login = getLogin();
			if(login.indexOf("%") < 0)
				login = login.concat("%");
			getParams().put("login", login);
		}
		
		if(getNickname() != null) {
			String nickname = getNickname();
			if(nickname.indexOf("%") < 0)
				nickname = nickname.concat("%");
			getParams().put("nickname", nickname);
		}
		
		if(getCreateDateStart() != null)
			getParams().put("createDateStart", getCreateDateStart());
		
		if(getCreateDateEnd() != null)
			getParams().put("createDateEnd", getCreateDateEnd());
		
		if(getStatus() != null)
			getParams().put("statusSendMessage", getStatus());
	}
	
	@Override
	public Set<Predicate> loadCriteria(CriteriaBuilder cb, Root<User> root) {
		Set<Predicate> predicates = new HashSet<Predicate>();
		if(getParams() != null) {
			for(Iterator<Map.Entry<String, Object>> iter = getParams().entrySet().iterator(); iter.hasNext();) {
				Map.Entry<String, Object> entry = iter.next();
				
				Predicate predicate = null;
				
				if("Role.name".equals(entry.getKey())) {
					String name = (String)entry.getValue();
					if(name == null)
						name = getRoleName();
					if(name != null) {
						SetJoin<User, Role> node = root.join(User_.roles, JoinType.LEFT);
						predicate = cb.equal(node.get(Role_.name), name);
					}
				}
				
				if("Role.value".equals(entry.getKey()) && !"null".equals(entry.getValue())) {
					String value = (String)entry.getValue();
					if(value == null)
						value = getRoleValue();
					if(value != null) {
						SetJoin<User, Role> node = root.join(User_.roles, JoinType.LEFT);
						predicate = cb.equal(node.get(Role_.value), value);
					}
				}
				
				if("login".equals(entry.getKey())) {
					String login = (String)entry.getValue();
					if(login == null)
						login = getLogin();
					if(login != null && login.length() > 0) {
						Expression<String> path = root.get(User_.login);
						predicate = cb.like(path, login);
					}
				}
				
				if("nickname".equals(entry.getKey())) {
					String nickname = (String)entry.getValue();
					if(nickname == null)
						nickname = getNickname();
					if(nickname != null && nickname.length() > 0) {
						Expression<String> path = root.get(User_.nickname);
						predicate = cb.like(path, nickname);
					}
				}
				
				if("createDateStart".equals(entry.getKey()) && !"null".equals(entry.getValue())) {
					String createDateStart = (String)entry.getValue();
					if(createDateStart == null)
						createDateStart = getCreateDateStart();
					if(createDateStart != null) {
						try {
							if(createDateStart.trim().matches("[0-9]{2}/[0-9]{2}/[0-9]{4}"))
								createDateStart = createDateStart.concat(" 00:00:00");
							Date dt = (new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")).parse(createDateStart);
							Expression<Date> path = root.get(User_.createDate);
							predicate = cb.greaterThanOrEqualTo(path, dt);
						} catch (ParseException e) {
							log.error(e);
						}
					}
				}
				
				if("createDateEnd".equals(entry.getKey()) && !"null".equals(entry.getValue())) {
					String createDateEnd = (String)entry.getValue();
					if(createDateEnd == null)
						createDateEnd = getCreateDateEnd();
					if(createDateEnd != null) {
						try {
							if(createDateEnd.trim().matches("[0-9]{2}/[0-9]{2}/[0-9]{4}"))
								createDateEnd = createDateEnd.concat(" 23:59:59");
							Date dt = (new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")).parse(createDateEnd);
							Expression<Date> path = root.get(User_.createDate);
							predicate = cb.lessThanOrEqualTo(path, dt);
						} catch (ParseException e) {
							log.error(e);
						}
					}
				}
				
				if("statusSendMessage".equals(entry.getKey())) {
					UserStatus status = (UserStatus)entry.getValue();
					if(status == null)
						status = getStatus();
					if(status != null)
						predicate = cb.equal(root.get(User_.status), status);
				}
				
				if(predicate != null)
					predicates.add(predicate);
			}
		}
		return predicates;
	}

	@Override
	public void loadOrders(CriteriaBuilder cb, Root<User> root,
			CriteriaQuery<?> cq) {
		if(getOrders() != null) {
			for(Iterator<Map.Entry<String, String>> iter = getOrders().entrySet().iterator(); iter.hasNext();) {
				Map.Entry<String, String> entry = iter.next();
				
				Path<?> path = null;
				
				if("login".equals(entry.getKey()) || "nickname".equals(entry.getKey()) || "createDate".equals(entry.getKey()) || "statusSendMessage".equals(entry.getKey()))
					path = root.get(entry.getKey());
				
				if("Role.name".equals(entry.getKey())) {
					SetJoin<User, Role> node = root.join(User_.roles, JoinType.LEFT);
					path = node.get(Role_.name);
				}
				
				if("asc".equals(entry.getValue()))
					cq.orderBy(cb.asc(path));

				if("desc".equals(entry.getValue()))
					cq.orderBy(cb.desc(path));
			}
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(String createDateStart) {
		this.createDateStart = createDateStart;
	}

	public String getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}

}
