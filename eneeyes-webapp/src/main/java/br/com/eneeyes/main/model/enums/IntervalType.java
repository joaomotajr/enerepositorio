package br.com.eneeyes.main.model.enums;

public enum IntervalType {
	UMA_HORA(1),
    SEIS_HORAS(6),
    DOZE_HORAS(12),
    UM_DIA(24),
    DOIS_DIAS(48),
    SETE_DIAS(168),
    UM_MES(30);
	
	private int value;
	
	private IntervalType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}	
	
}
