package br.com.eneeyes.controllers.api.dto.inconsistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.eneeyes.controllers.api.dto.pagamentos.ComprovanteVendaCieloDto;
import br.com.eneeyes.controllers.api.dto.vendas.DetalheVendaDto;


public class InconsistenciaDto implements Serializable {

	private Date data;
	private Date hora;
	private Integer nsu;
	private String adquirente;
	private String cnpj;
	private String tipoVenda;
	private BigDecimal valorVenda;
	private Integer parcelas;
	private String status;
	private List<MotivoInconsistenciaDto> motivos;
	private DetalheVendaDto vendaDto;
	private ComprovanteVendaCieloDto comprovanteDto;
	private BigDecimal taxaVenda;
	private BigDecimal taxaComprovante;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	public Integer getNsu() {
		return nsu;
	}
	public void setNsu(Integer nsu) {
		this.nsu = nsu;
	}
	public String getAdquirente() {
		return adquirente;
	}
	public void setAdquirente(String adquirente) {
		this.adquirente = adquirente;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getTipoVenda() {
		return tipoVenda;
	}
	public void setTipoVenda(String tipoVenda) {
		this.tipoVenda = tipoVenda;
	}
	public BigDecimal getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
	public Integer getParcelas() {
		return parcelas;
	}
	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<MotivoInconsistenciaDto> getMotivos() {
		return motivos;
	}
	public void setMotivos(List<MotivoInconsistenciaDto> motivos) {
		this.motivos = motivos;
	}
	public DetalheVendaDto getVendaDto() {
		return vendaDto;
	}
	public void setVendaDto(DetalheVendaDto vendaDto) {
		this.vendaDto = vendaDto;
	}
	public ComprovanteVendaCieloDto getComprovanteDto() {
		return comprovanteDto;
	}
	public void setComprovanteDto(ComprovanteVendaCieloDto comprovanteDto) {
		this.comprovanteDto = comprovanteDto;
	}
	public BigDecimal getTaxaVenda() {
		return taxaVenda;
	}
	public void setTaxaVenda(BigDecimal taxaVenda) {
		this.taxaVenda = taxaVenda;
	}
	public BigDecimal getTaxaComprovante() {
		return taxaComprovante;
	}
	public void setTaxaComprovante(BigDecimal taxaComprovante) {
		this.taxaComprovante = taxaComprovante;
	}
	public Integer getStatusInconsistencia() {
		if (this.motivos.size() > 1) {
			return 0;
		} else {
			return this.motivos.get(0).getCodigo();
		}
	}
	public Boolean getPendente() {
		for (MotivoInconsistenciaDto motivoInconsistenciaDto : motivos) {
			if (motivoInconsistenciaDto.getCodigo() == 4) {
				return true;
			}
		}
		return false;
	}
}