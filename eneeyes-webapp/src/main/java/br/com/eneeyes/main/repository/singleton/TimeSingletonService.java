package br.com.eneeyes.main.repository.singleton;

import javax.enterprise.context.ApplicationScoped;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.eneeyes.main.service.historic.HistoricService;

@ApplicationScoped
public class TimeSingletonService  {
	
	@Autowired
	static
	HistoricService service;
	
	public static final String OPEN = "1";
	public static final String CLOSE = "0";	
	public static final Long LIMIT_TIME = new Long(10000);
	public static String STATIC = "";
	public static Long COUNT_TIMER = new Long(0);	
		
	public static String process(Long uid, String value) {
		
		String result="";
		
		//Mudou Status Devolve 0 para Fechado e 1 para Aberto
		if(!STATIC.equals(value)) {
			
			if(value.equals(CLOSE)) {
				//service.saveByPositionUid(uid, new Long(0).toString());
				result = "Fechou Porta";
			}	
			else {
				//service.saveByPositionUid(uid, new Long(1).toString());
				result = "Abriu Porta";
			}
			COUNT_TIMER = System.currentTimeMillis();			 
				
		}		
		else {			
			COUNT_TIMER = System.currentTimeMillis() - COUNT_TIMER;
			
			//Status igual anterior, só Grava se tempo maior que 10segs			
			if(COUNT_TIMER > LIMIT_TIME) {
			
				if(STATIC.equals(OPEN)) {
					//service.saveByPositionUid(uid, COUNT_TIMER.toString());		
					result = "Porta Aberta á " + COUNT_TIMER.toString();
				}
				else {					
					//service.saveByPositionUid(uid, new Long(0).toString());
					result = "Porta Fechada";
				}
			}	
			else {
				result = "Status Não Mudou (Sleeping...):  " + COUNT_TIMER.toString();
			}

		}
		STATIC = value;
		return result;			
	}
	
	
}
