package br.com.eneeyes.main.model.enums;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro dos Tipos de Unidades das Empresas 
 */

public enum UnitType {
	UNICA(0), MATRIZ(1), FILIAL(2);
	
	private final int code;
	
	UnitType(int code) { this.code = code; }

    int code() { return code; }

    public static UnitType byCode(int code) {
        for (UnitType unitType: UnitType.values()) {
            if (code == unitType.code()) return unitType;
        }
        throw new IllegalArgumentException("tipo invalido");
    }    
}
