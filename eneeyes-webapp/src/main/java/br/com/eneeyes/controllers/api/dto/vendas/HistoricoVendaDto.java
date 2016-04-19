package br.com.eneeyes.controllers.api.dto.vendas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class HistoricoVendaDto implements Serializable {

	private Long id;
	private Date dataMovimento;
    private BigDecimal valorCredito;
    private BigDecimal valorDebito;
    private BigDecimal valorCancelado;
    private Date horaPrimeiraVenda;
    private Date horaUltimaVenda;
    private Date dataIntegracao;
    private Boolean tef;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataMovimento() {
		return dataMovimento;
	}
	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}
	public BigDecimal getValorCredito() {
		return valorCredito;
	}
	public void setValorCredito(BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
	}
	public BigDecimal getValorDebito() {
		return valorDebito;
	}
	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}
	public BigDecimal getValorCancelado() {
		return valorCancelado;
	}
	public void setValorCancelado(BigDecimal valorCancelado) {
		this.valorCancelado = valorCancelado;
	}
	public Date getHoraPrimeiraVenda() {
		return horaPrimeiraVenda;
	}
	public void setHoraPrimeiraVenda(Date horaPrimeiraVenda) {
		this.horaPrimeiraVenda = horaPrimeiraVenda;
	}
	public Date getHoraUltimaVenda() {
		return horaUltimaVenda;
	}
	public void setHoraUltimaVenda(Date horaUltimaVenda) {
		this.horaUltimaVenda = horaUltimaVenda;
	}
	public Date getDataIntegracao() {
		return dataIntegracao;
	}
	public void setDataIntegracao(Date dataIntegracao) {
		this.dataIntegracao = dataIntegracao;
	}
	public Boolean isTef() {
		return tef;
	}
	public void setTef(Boolean tef) {
		this.tef = tef;
	}
	public HistoricoVendaDto() {
	}
	
	public HistoricoVendaDto(Long id, Date dataMovimento, BigDecimal valorCredito, BigDecimal valorDebito,
			BigDecimal valorCancelado, Date horaPrimeiraVenda, Date horaUltimaVenda, Date dataIntegracao,
			Boolean tef) {
		super();
		this.id = id;
		this.dataMovimento = dataMovimento;
		this.valorCredito = valorCredito;
		this.valorDebito = valorDebito;
		this.valorCancelado = valorCancelado;
		this.horaPrimeiraVenda = horaPrimeiraVenda;
		this.horaUltimaVenda = horaUltimaVenda;
		this.dataIntegracao = dataIntegracao;
		this.tef = tef;
	}
	
}