package br.com.eneeyes.controllers.api.dto.vendas;

import java.util.Date;

import br.com.eneeyes.controllers.api.dto.cieloCodeData.TipoOpcaoExtratoDto;

public class HistoricoVendaCieloDto {

	private Long id;
	private Integer estabelecimentoMatriz;
	private Date dataProcessamento;
	private Date periodoInicial;
	private Date periodoFinal;
	private Integer sequencia;
	private String empresaAdquirente;
	private TipoOpcaoExtratoDto opcaoExtrato;
	private String van;
	private String caixaPostal;
	private String versaoLayout;
	private String usoCielo;
	private Boolean isManual;
	
	public HistoricoVendaCieloDto() {
	}
	
	public HistoricoVendaCieloDto(Long id, Integer estabelecimentoMatriz, Date dataProcessamento, Date periodoInicial,
			Date periodoFinal, Integer sequencia, String empresaAdquirente, TipoOpcaoExtratoDto opcaoExtrato, String van,
			String caixaPostal, String versaoLayout, String usoCielo, Boolean isManual) {
		super();
		this.id = id;
		this.estabelecimentoMatriz = estabelecimentoMatriz;
		this.dataProcessamento = dataProcessamento;
		this.periodoInicial = periodoInicial;
		this.periodoFinal = periodoFinal;
		this.sequencia = sequencia;
		this.empresaAdquirente = empresaAdquirente;
		this.opcaoExtrato = opcaoExtrato;
		this.van = van;
		this.caixaPostal = caixaPostal;
		this.versaoLayout = versaoLayout;
		this.usoCielo = usoCielo;
		this.isManual = isManual;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getEstabelecimentoMatriz() {
		return estabelecimentoMatriz;
	}
	public void setEstabelecimentoMatriz(Integer estabelecimentoMatriz) {
		this.estabelecimentoMatriz = estabelecimentoMatriz;
	}
	public Date getDataProcessamento() {
		return dataProcessamento;
	}
	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	public Date getPeriodoInicial() {
		return periodoInicial;
	}
	public void setPeriodoInicial(Date periodoInicial) {
		this.periodoInicial = periodoInicial;
	}
	public Date getPeriodoFinal() {
		return periodoFinal;
	}
	public void setPeriodoFinal(Date periodoFinal) {
		this.periodoFinal = periodoFinal;
	}
	public Integer getSequencia() {
		return sequencia;
	}
	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}
	public String getEmpresaAdquirente() {
		return empresaAdquirente;
	}
	public void setEmpresaAdquirente(String empresaAdquirente) {
		this.empresaAdquirente = empresaAdquirente;
	}
	public TipoOpcaoExtratoDto getOpcaoExtrato() {
		return opcaoExtrato;
	}
	public void setOpcaoExtrato(TipoOpcaoExtratoDto opcaoExtrato) {
		this.opcaoExtrato = opcaoExtrato;
	}
	public String getVan() {
		return van;
	}
	public void setVan(String van) {
		this.van = van;
	}
	public String getCaixaPostal() {
		return caixaPostal;
	}
	public void setCaixaPostal(String caixaPostal) {
		this.caixaPostal = caixaPostal;
	}
	public String getVersaoLayout() {
		return versaoLayout;
	}
	public void setVersaoLayout(String versaoLayout) {
		this.versaoLayout = versaoLayout;
	}
	public String getUsoCielo() {
		return usoCielo;
	}
	public void setUsoCielo(String usoCielo) {
		this.usoCielo = usoCielo;
	}
	public Boolean getIsManual() {
		return isManual;
	}
	public void setIsManual(Boolean isManual) {
		this.isManual = isManual;
	}
}
