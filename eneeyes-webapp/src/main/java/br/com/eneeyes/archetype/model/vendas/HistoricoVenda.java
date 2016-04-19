package br.com.eneeyes.archetype.model.vendas;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.eneeyes.controllers.api.dto.vendas.HistoricoVendaDto;

@Entity
@Table(name="historico_venda")
public class HistoricoVenda {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_")
    private Long id;
    
    @Column(name="DATA_MOVIMENTO_")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMovimento;
    
    @Column(name="VALOR_CREDITO_")
    private BigDecimal valorCredito;
    
    @Column(name="VALOR_DEBITO_")
    private BigDecimal valorDebito;
    
    @Column(name="VALOR_CANCELADO_")
    private BigDecimal valorCancelado;
    
    @Column(name="HORA_PRIMEIRA_VENDA_")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaPrimeiraVenda;
    
    @Column(name="HORA_ULTIMA_VENDA_")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaUltimaVenda;
    
    @Column(name="DATA_INTEGRACAO_")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataIntegracao;
    
    @Column(name="IS_TEF_")
    private Boolean tef;
    
	public HistoricoVenda() {
	}
	
	public HistoricoVenda(Long id, Date dataMovimento, BigDecimal valorCredito, BigDecimal valorDebito,
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

	public static HistoricoVenda fromHistoricoVendaDto(HistoricoVendaDto dto) {
		return new HistoricoVenda(dto.getId(), dto.getDataMovimento(), dto.getValorCredito(), dto.getValorDebito(), dto.getValorCancelado(), dto.getHoraPrimeiraVenda(), dto.getHoraUltimaVenda(),
				dto.getDataIntegracao(), dto.isTef());
	}

    public HistoricoVendaDto toHistoricoVendaDto() {
        return new HistoricoVendaDto(this.id, this.dataMovimento, this.valorCredito, this.valorDebito, this.valorCancelado, this.horaPrimeiraVenda, this.horaUltimaVenda, this.dataIntegracao, this.tef);
    }
}