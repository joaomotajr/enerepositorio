package br.com.eneeyes.main.model.enums;

public enum SigmaStatus {
	OFF(0), ON(1), SENDED(2), ERROR(3);
	
	private final int code;
	
	SigmaStatus(int code) { this.code = code; }

    int code() { return code; }

    public static SigmaStatus byCode(int code) {
        for (SigmaStatus sigmaStatus: SigmaStatus.values()) {
            if (code == sigmaStatus.code()) return sigmaStatus;
        }
        throw new IllegalArgumentException("Status Inválido");
    } 

}
