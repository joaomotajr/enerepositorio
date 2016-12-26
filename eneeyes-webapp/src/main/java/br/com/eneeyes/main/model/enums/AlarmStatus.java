package br.com.eneeyes.main.model.enums;

public enum AlarmStatus {
	CHECKED(0), UPDATED(1), PENDENT(1), READED(3);
	
	private final int code;
	
	AlarmStatus(int code) { this.code = code; }

    int code() { return code; }

    public static AlarmStatus byCode(int code) {
        for (AlarmStatus alarmStatus: AlarmStatus.values()) {
            if (code == alarmStatus.code()) return alarmStatus;
        }
        throw new IllegalArgumentException("Alarm Inválido");
    } 

}
