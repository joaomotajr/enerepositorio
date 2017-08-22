package br.com.eneeyes.main.repository.singleton;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.eneeyes.main.model.register.Parameter;

@Component
@Scope("singleton")
public class ParameterSingletonRepository  {
	
	static Parameter parameter;
		
	public static boolean init() {
		return (parameter == null);			
	}
	
	public static void populate(Parameter param) {
				
		parameter = param;		
	}
	
	public static Parameter getParameter() {
				
		return parameter;
	}
	
}
