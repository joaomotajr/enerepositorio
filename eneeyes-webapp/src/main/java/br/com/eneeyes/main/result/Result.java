package br.com.eneeyes.main.result;

import java.util.List;

public class Result<T> extends BasicResult<T> {
	
	private List<T> list;
	private T dto;
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public T getT() {
		return dto;
	}

	public void setEntity(T dto) {
		this.dto = dto;
	}		
}
