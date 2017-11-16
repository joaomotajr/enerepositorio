package br.com.eneeyes.archetype.utils;

import java.util.Calendar;
import java.util.Date;

public final class Util {
    
	public  static Date addMonth(Date date, int qtde) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, qtde);			
		
		return c.getTime();		
	}
}