package br.com.eneeyes.main.model.enums;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro dos Tipos de Unidades das Empresas 
 */

public enum UnitMeterGases {
	DESCONHECIDO(0), PPM(1), PPB(2), LEL_PERCENT(3), LEL_PERCENT_METRO(4), PERCENT_VOLUME(5);
	
	private final int code;
	
	UnitMeterGases(int code) { 
		this.code = code; 
	}

    int code() { 
    	return code; 
    }

    public static UnitMeterGases byCode(int code) {
        for (UnitMeterGases unitMeterGases: UnitMeterGases.values()) 
        {
            if (code == unitMeterGases.code()) return unitMeterGases;
        }
        
        throw new IllegalArgumentException("Protocolo Inválido");
    }    
}
