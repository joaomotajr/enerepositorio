package br.com.eneeyes.main.model.enums;

public enum AlarmType {
	NORMAL(0), DETECCAO(1), ALERTA(2), EVACUACAO(3), OFF(4), OFFLINE(5), WITHOUT(6);
	
	private final int code;
	
	AlarmType(int code) { this.code = code; }

    int code() { return code; }

    public static AlarmType byCode(int code) {
        for (AlarmType alarmType: AlarmType.values()) {
            if (code == alarmType.code()) return alarmType;
        }
        throw new IllegalArgumentException("Alarm Inválido");
    } 

}
