package br.com.eneeyes.controllers.api.dto.contaCorrente;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.eneeyes.controllers.api.dto.pagamentos.ComprovanteVendaCieloDto;

public class ContaCorrenteDto implements Comparable<ContaCorrenteDto> {

	private Date data;
	private String status;
	private BigDecimal valor;
	private List<ComprovanteVendaCieloDto> listaComprovantes;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public List<ComprovanteVendaCieloDto> getListaComprovantes() {
		return listaComprovantes;
	}
	public void setListaComprovantes(List<ComprovanteVendaCieloDto> listaComprovantes) {
		this.listaComprovantes = listaComprovantes;
	}
	public void addAll(List<ComprovanteVendaCieloDto> listaComprovantes) {
		this.listaComprovantes.addAll(listaComprovantes);
	}
	@Override
	public int compareTo(ContaCorrenteDto o) {
		return getData().compareTo(o.getData());
	}
}
