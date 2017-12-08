package br.com.eneeyes.main.result;

import java.util.List;

public class GroupResult<T> extends BasicGroupResult<T> {
	
	private List<T> list;	
    	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> resultList) {
		this.list = resultList;
	}		
	
}
