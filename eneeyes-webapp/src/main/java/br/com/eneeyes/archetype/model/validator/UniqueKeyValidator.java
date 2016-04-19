package br.com.eneeyes.archetype.model.validator;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UniqueKeyValidator implements ConstraintValidator<UniqueKey, Serializable>, EntityManagerAwareValidator {

	private Log log = LogFactory.getLog(getClass());
	
	@PersistenceContext
	private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private String[] columnNames;

    public void initialize(UniqueKey constraintAnnotation) {
        this.columnNames = constraintAnnotation.columnNames();

    }

    public boolean isValid(Serializable target, ConstraintValidatorContext context) {
        Class<?> entityClass = target.getClass();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Object> cq = criteriaBuilder.createQuery();

        Root<?> cqfrom = cq.from(entityClass);

        CriteriaQuery<Object> cqselect = cq.select(cqfrom);
        
        List<Predicate> predicates = new ArrayList<Predicate> (columnNames.length);

        try {
        	PropertyDescriptor idDesc = new PropertyDescriptor("id", entityClass);
        	Method idReadMethod = idDesc.getReadMethod();
        	Object idPropertyValue = idReadMethod.invoke(target);
        	Predicate idPredicate = criteriaBuilder.notEqual(cqfrom.get("id"), idPropertyValue);
            predicates.add(idPredicate);
        	
            for(int i=0; i<columnNames.length; i++) {
                String propertyName = columnNames[i];
                PropertyDescriptor desc = new PropertyDescriptor(propertyName, entityClass);
                Method readMethod = desc.getReadMethod();
                Object propertyValue = readMethod.invoke(target);
                Predicate predicate = criteriaBuilder.equal(cqfrom.get(propertyName), propertyValue);
                predicates.add(predicate);
            }
            cqselect.where(predicates.toArray(new Predicate[predicates.size()]));
            
            TypedQuery<Object> typedQuery = entityManager.createQuery(cqselect);
            
            List<Object> resultSet = typedQuery.getResultList(); 
            
            return resultSet.size() == 0;
        } catch (Exception e) {
            log.error(e);
        	throw new RuntimeException(e);
        }
    }

}