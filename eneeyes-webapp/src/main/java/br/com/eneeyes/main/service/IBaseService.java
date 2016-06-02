package br.com.eneeyes.main.service;

import br.com.eneeyes.main.result.MainBaseResult;

public interface IBaseService<T> {

	public MainBaseResult<?> save();
	
	public MainBaseResult<?> save2();
	
}
