package br.com.eneeyes.archetype.service.identity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.archetype.model.acl.Role;
import br.com.eneeyes.archetype.model.acl.result.RoleResult;
import br.com.eneeyes.archetype.web.result.ResultMessageType;

@Repository
public class RoleServiceImpl implements RoleService {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public RoleResult findByValue(String value) {
		ResultMessageType resultType = ResultMessageType.SUCCESS;
		
		Role role = null;
		
		try {
			role = em.createQuery("From Role Where value = :value", Role.class)
						.setParameter("value", value)
							.getSingleResult();
		} catch (NoResultException e) {
			resultType = ResultMessageType.ERROR;
		}
		
		return new RoleResult(resultType, role);
	}
	
	public Role toNativeBean(Role source) {
		Role target = new Role();
		target.setId(source.getId());
		target.setName(source.getName());
		target.setValue(source.getValue());
		target.setStatus(source.getStatus());
		return target;
	}

}
