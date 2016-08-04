package br.com.eneeyes.main.model.enums;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro dos Tipos de Unidades das Empresas 
 */

public enum DeviceType {
	OUTROS(0), DETECTOR(1), PLC(2), CONTROLLER(3), ALARM(4), BLOCKER(5);
	
	private final int code;
	
	DeviceType(int code) { this.code = code; }

    int code() { return code; }

    public static DeviceType byCode(int code) {
        for (DeviceType deviceType: DeviceType.values()) {
            if (code == deviceType.code()) return deviceType;
        }
        throw new IllegalArgumentException("dispositivo inválido");
    }    
}
