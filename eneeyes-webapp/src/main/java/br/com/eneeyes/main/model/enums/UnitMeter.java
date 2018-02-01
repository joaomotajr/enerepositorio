package br.com.eneeyes.main.model.enums;

/**
 *  
 */

public enum UnitMeter {
	DESCONHECIDO(0), PPM(1), PPB(2), LEL_PERCENT(3), LEL_PERCENT_METRO(4), PERCENT_VOLUME(5), CELSIUS(6), VOLT(7), AMPERE(8), MINUTE(9), SECOND(10), KWH(11);
	
	private final int code;
	
	UnitMeter(int code) { 
		this.code = code; 
	}

    int code() { 
    	return code; 
    }

    public static UnitMeter byCode(int code) {
        for (UnitMeter unitMeter: UnitMeter.values()) 
        {
            if (code == unitMeter.code()) return unitMeter;
        }
        
        throw new IllegalArgumentException("Unidade Inválida");
    }    
}
