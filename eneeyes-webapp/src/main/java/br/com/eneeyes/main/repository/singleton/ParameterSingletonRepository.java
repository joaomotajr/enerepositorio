package br.com.eneeyes.main.repository.singleton;

import javax.enterprise.context.ApplicationScoped;

import br.com.eneeyes.main.model.register.Parameter;

@ApplicationScoped
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
