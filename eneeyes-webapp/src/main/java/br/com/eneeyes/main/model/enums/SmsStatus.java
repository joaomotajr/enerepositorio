package br.com.eneeyes.main.model.enums;

public enum SmsStatus {	
	OFF(0), PENDENT(1), SENDED(2), ERR_TRY_ONE(3), ERR_TRY(4), READED(5);
	
	private final int code;
	
	SmsStatus(int code) { this.code = code; }

    int code() { return code; }

    public static SmsStatus byCode(int code) {
        for (SmsStatus smsStatus: SmsStatus.values()) {
            if (code == smsStatus.code()) return smsStatus;
        }
        throw new IllegalArgumentException("SMS Status Inválido");
    } 

}
