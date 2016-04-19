package br.com.eneeyes.archetype.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class Criteria<T> {
	private T entity;
	
	private Map<String, Object> params = new HashMap<String, Object>();
	
	private Map<String, String> orders = new HashMap<String, String>();
	
	private Integer page;
	
	private Integer offset;
	
	private String order;
	
	private String orderType;

	public abstract Set<Predicate> loadCriteria(CriteriaBuilder cb, Root<T> root);
	
	public abstract void loadOrders(CriteriaBuilder cb, Root<T> root, CriteriaQuery<?> cq);
	
	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public Map<String, String> getOrders() {
		return orders;
	}

	public void setOrders(Map<String, String> orders) {
		this.orders = orders;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
}
