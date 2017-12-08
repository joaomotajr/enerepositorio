package br.com.eneeyes.main.result;

import com.google.gson.Gson;

public class LogResult<T> extends Result<T> {
		
	private T dto;

	@Override
	public T getT() {
		return dto;
	}

	@Override
	public void setEntity(T dto) {
		this.dto = dto;
	}	
	
	@Override
	public String toString() {
		
		return new Gson().toJson(dto);
	}	
}
