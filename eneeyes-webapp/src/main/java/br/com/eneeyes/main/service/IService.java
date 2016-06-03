package br.com.eneeyes.main.service;

import br.com.eneeyes.main.result.BasicResult;

public interface IService<T> {

	public BasicResult<?> save(T dto);
	public BasicResult<?> delete(Long uid);
	

}
