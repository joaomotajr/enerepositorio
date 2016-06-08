package br.com.eneeyes.main.model.enums;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro dos Tipos de Unidades das Empresas 
 */

public enum CommProtocol {
	DESCONHECIDO(0), PROFIBUS(1), ETHERNET_IP(2), ETHERCAT(3), ASI(4), MODBUS(5), HART(6), HONEYWELL(7), DEVICENET(8) ;
	
	private final int code;
	
	CommProtocol(int code) { 
		this.code = code; 
	}

    int code() { 
    	return code; 
    }

    public static CommProtocol byCode(int code) {
        for (CommProtocol commProtocol: CommProtocol.values()) 
        {
            if (code == commProtocol.code()) return commProtocol;
        }
        
        throw new IllegalArgumentException("Protocolo Inválido");
    }    
}
