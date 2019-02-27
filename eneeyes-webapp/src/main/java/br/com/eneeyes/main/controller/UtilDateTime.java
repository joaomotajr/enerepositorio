package br.com.eneeyes.main.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilDateTime {
	
	private int day, mon, year, hour, min;
	private Calendar c;
	
	public UtilDateTime() {		
		 this.c = Calendar.getInstance();
		 this.day = c.get(Calendar.DAY_OF_MONTH);
		 this.mon = c.get(Calendar.MONTH)+1;
		 this.year = c.get(Calendar.YEAR);
		 this.hour = c.get(Calendar.HOUR);
		 this.min = c.get(Calendar.MINUTE);
	}

	public int getDay() {
		return day;
	}

	public int getMon() {
		return mon;
	}

	public int getYear() {
		return year;
	}

	public int getHour() {
		return hour;
	}

	public int getMin() {
		return min;
	}
	
	public Long getOffSet() {
		return c.getTimeInMillis();
	}
	
	public String getDateNumber() {
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
		return s.format(c.getTime());
	}
	
	public Date getTime() {		
		return c.getTime();
	}
	
	public String getDateTime() {
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return s.format(c.getTime());
	}		
	
}
