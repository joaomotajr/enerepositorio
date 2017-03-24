package br.com.eneeyes.main.model.enums;

public enum EmailStatus {
	OFF(0), PENDENT(1), SENDED(2), ERR_TRY_ONE(3), ERR_TRY(4);
	
	private final int code;
	
	EmailStatus(int code) { this.code = code; }

    int code() { return code; }

    public static EmailStatus byCode(int code) {
        for (EmailStatus emailStatus: EmailStatus.values()) {
            if (code == emailStatus.code()) return emailStatus;
        }
        throw new IllegalArgumentException("Email Status Inválido");
    } 

}
