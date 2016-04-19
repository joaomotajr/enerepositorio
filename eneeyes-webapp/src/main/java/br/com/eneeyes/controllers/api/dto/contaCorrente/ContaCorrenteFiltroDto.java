package br.com.eneeyes.controllers.api.dto.contaCorrente;


public class ContaCorrenteFiltroDto {

	private String dataInicial;
	private String dataFinal;
	private String adquirente;
	private String status;
	
	public String getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	public String getAdquirente() {
		return adquirente;
	}
	public void setAdquirente(String adquirente) {
		this.adquirente = adquirente;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}