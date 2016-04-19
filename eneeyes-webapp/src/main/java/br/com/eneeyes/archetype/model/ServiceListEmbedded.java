package br.com.eneeyes.archetype.model;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.archetype.web.result.ResultCollection;

public abstract class ServiceListEmbedded<T, C, R> extends ServiceEmbedded<T> {
	@PersistenceContext
	private EntityManager em;
	
	public abstract R newResultCollection();
	
	@Transactional
	public R pagination() {
		return pagination(Pagination.DEFAULT_INIT_PAGE, Pagination.DEFAULT_OFFSET, null);
	}

	@Transactional
	public R pagination(Criteria<T> criteria) {
		return pagination(criteria.getPage(), criteria.getOffset(), criteria);
	}

	@Transactional
	public R pagination(Integer page, Integer offset) {
		return pagination(page, offset, null);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public R pagination(Integer page, Integer offset,
			Criteria<T> criteria) {
		ResultCollection<T> collection = (ResultCollection<T>) newResultCollection();
		collection.setPage(page);
		collection.setOffset(offset);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cqcount = cb.createQuery(Long.class);
		Root<T> cqcountFrom = (Root<T>) cqcount.from(criteria.getEntity().getClass());
		CriteriaQuery<Long> cqcountSelect = cqcount.select(cb.count(cqcountFrom));
		Set<Predicate> countPredicates = criteria.loadCriteria(cb, cqcountFrom);
		cqcountSelect.where(countPredicates.toArray(new Predicate[countPredicates.size()]));
		Long total = em.createQuery(cqcount).getSingleResult();
		collection.setTotal(total);
		

		CriteriaQuery<T> cq = (CriteriaQuery<T>) cb.createQuery(criteria.getEntity().getClass());
		Root<T> cqfrom = (Root<T>) cq.from(criteria.getEntity().getClass());
		CriteriaQuery<T> cqselect = cq.select(cqfrom);
		Set<Predicate> selectPredicates = criteria.loadCriteria(cb, cqfrom);
		cqselect.where(selectPredicates.toArray(new Predicate[selectPredicates.size()]));
		criteria.loadOrders(cb, cqfrom, cq);
		TypedQuery<T> typedQuery = em.createQuery(cqselect);
		typedQuery.setFirstResult(page * offset);
		typedQuery.setMaxResults(offset);
		List<T> cache = typedQuery.getResultList();
		if(cache != null && cache.size() > 0) {
			T[] targetCollection = (T[]) Array.newInstance(criteria.getEntity().getClass(),cache.size());
			
			int p = 0;
			for(T dbT : cache) {
				targetCollection[p] = toNativeBean(dbT);
				p++;
			}
			
			collection.setItems(targetCollection);
		}
		
		if((cache == null || cache.size() < 1) && page > 0) {
			collection = (ResultCollection<T>) pagination(page-1, offset, criteria);
		}
		
		return (R) collection;
	}
}
