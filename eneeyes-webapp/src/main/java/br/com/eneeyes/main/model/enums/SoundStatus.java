package br.com.eneeyes.main.model.enums;

public enum SoundStatus {
	OFF(0), ON(1), SILENT(2);
	
	private final int code;
	
	SoundStatus(int code) { this.code = code; }

    int code() { return code; }

    public static SoundStatus byCode(int code) {
        for (SoundStatus soundStatus: SoundStatus.values()) {
            if (code == soundStatus.code()) return soundStatus;
        }
        throw new IllegalArgumentException("Alarm Inválido");
    } 

}
