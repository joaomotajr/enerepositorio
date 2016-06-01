package br.com.eneeyes.main.model;

public enum UnitType {
	UNICA(0), MATRIZ(1), FILIAL(2);
	
	private final int codigo;
	
	UnitType(int codigo) { this.codigo = codigo; }

    int codigo() { return codigo; }

    public static UnitType porCodigo(int codigo) {
        for (UnitType unitType: UnitType.values()) {
            if (codigo == unitType.codigo()) return unitType;
        }
        throw new IllegalArgumentException("tipo invalido");
    }    
}
