package br.com.eneeyes.main.result;

import com.google.gson.Gson;

public class LogResult<T> extends Result<T> {
		
	private T dto;

	public T getT() {
		return dto;
	}

	public void setEntity(T dto) {
		this.dto = dto;
	}	
	
	@Override
	public String toString() {
		
		return "DTO Result=" + new Gson().toJson(dto);
	}	
}
