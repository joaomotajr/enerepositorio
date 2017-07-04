package br.com.eneeyes.main.model.enums;

/**
 * Created by Junior on 29/06/2017.
 * Cadastro dos Tipos de Unidades das Empresas 
 */

public enum HistoricMaintenaceType {
	DESCONHECIDO(0), MAINTENANCE(1), CALIBRATION(2), OUTROS(3);
	
	private final int code;
	
	HistoricMaintenaceType(int code) { this.code = code; }

    int code() { return code; }

    public static HistoricMaintenaceType byCode(int code) {
        for (HistoricMaintenaceType historicMaintenaceType: HistoricMaintenaceType.values()) {
            if (code == historicMaintenaceType.code()) return historicMaintenaceType;
        }
        throw new IllegalArgumentException("tipo inválido");
    }    
}
