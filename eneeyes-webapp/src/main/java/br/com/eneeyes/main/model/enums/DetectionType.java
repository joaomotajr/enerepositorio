package br.com.eneeyes.main.model.enums;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro dos Tipos de Unidades das Empresas 
 */

public enum DetectionType {
	OUTROS(0), CAT(1), FTA(2), FID(3), ECM(4), IR(5), BUT(6);
	
	private final int code;
	
	DetectionType(int code) { this.code = code; }

    int code() { return code; }

    public static DetectionType byCode(int code) {
        for (DetectionType detectionType: DetectionType.values()) {
            if (code == detectionType.code()) return detectionType;
        }
        throw new IllegalArgumentException("Tipo Inválido");
    }    
}
