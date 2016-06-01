package br.com.eneeyes.main.model;

public enum TipoUnidade {
	UNICA(0), MATRIZ(1), FILIAL(2);
	
	private final int codigo;
	
	TipoUnidade(int codigo) { this.codigo = codigo; }

    int codigo() { return codigo; }

    public static TipoUnidade porCodigo(int codigo) {
        for (TipoUnidade tipoUnidade: TipoUnidade.values()) {
            if (codigo == tipoUnidade.codigo()) return tipoUnidade;
        }
        throw new IllegalArgumentException("tipo invalido");
    }    
}
