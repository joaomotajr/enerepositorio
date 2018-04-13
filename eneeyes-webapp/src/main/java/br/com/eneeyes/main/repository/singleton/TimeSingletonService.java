package br.com.eneeyes.main.repository.singleton;

import javax.enterprise.context.ApplicationScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.main.service.historic.HistoricService;

@Service
@ApplicationScoped
public class TimeSingletonService  {
	
	@Autowired	
	HistoricService service;
	
	public static final String OPEN = "1";
	public static final String CLOSE = "0";	
	public static final Long INTERVAL_SEND_TIME = new Long(15000);
	public static String STATIC = "";
	public static Long COUNT_TIMER = new Long(0);
	public static Long DOOR_OPEN_COUNT_TIMER = new Long(0);
		
	public String process(Long uid, String value) {
		
		if(!(value.equals("0") ^ value.equals("1")))
			return "Wrong Value Params Incorreto ::: 0-> CLOSED / 1-> OPENED";
		
		String result="";
		
		//Mudou Status Devolve 0 para Fechado e 1 para Aberto
		if(!STATIC.equals(value.equals("0") ? CLOSE:OPEN)) {
			
			if(value.equals(CLOSE)) {
				service.saveByPositionUid(uid, new Long(0).toString());
				result = "Send DOOR CLOSED TO E-Gas";
				DOOR_OPEN_COUNT_TIMER = System.currentTimeMillis();
			}	
			else {
				service.saveByPositionUid(uid, new Long(100000).toString());
				result = "Send DOOR OPENED TO E-Gas";
			}
			COUNT_TIMER = System.currentTimeMillis();			 
			
		}		
		else {			
			
			//Status igual anterior, só Grava se tempo maior que INTERVAL_SEND_TIME
			if((System.currentTimeMillis() - COUNT_TIMER) > INTERVAL_SEND_TIME) {
			
				if(STATIC.equals(OPEN)) {
					result = "Send OPEN DOOR " + (System.currentTimeMillis() - DOOR_OPEN_COUNT_TIMER) / 1000 + " segs to E-Gas";
					Long timeOpened = (System.currentTimeMillis() - DOOR_OPEN_COUNT_TIMER) * 100000;
					service.saveByPositionUid(uid, timeOpened.toString());		
										
				}
				else {					
					service.saveByPositionUid(uid, new Long(0).toString());
					DOOR_OPEN_COUNT_TIMER = System.currentTimeMillis();
					result = "Send DOOR CLOSE to E-GAS";
				}
				
				COUNT_TIMER = System.currentTimeMillis();
			}	
			else {
				
				result = "Status do not change, DOOR stand " + (STATIC.equals(OPEN) ? "OPEN" : "CLOSE")  + " (Sleeping...): " + (System.currentTimeMillis() - COUNT_TIMER);
			}
			
			
		}
		STATIC = value.equals("0") ? CLOSE:OPEN;
		return result;			
	}
	
	
}
