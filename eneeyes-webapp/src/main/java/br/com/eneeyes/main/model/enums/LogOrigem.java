package br.com.eneeyes.main.model.enums;

public enum LogOrigem {
	
	DEVICE(0), MANUAL(1), SYSTEM(2), OTHER(3);
	
	private final int code;
	
	LogOrigem(int code) { this.code = code; }

    int code() { return code; }

    public static LogOrigem byCode(int code) {
        for (LogOrigem logOrigem: LogOrigem.values()) {
            if (code == logOrigem.code()) return logOrigem;
        }
        throw new IllegalArgumentException("Origem Inválida");
    } 

}
