package br.com.eneeyes.controllers.api.dto.pagamentos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.eneeyes.controllers.api.dto.cieloCodeData.CodigoBandeiraDto;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.CodigoProdutoDto;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.MeioCapturaDto;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.OrigemAjusteDto;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.StatusPagamentoDto;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.TipoTransacaoDto;


public class ResumoOperacaoRedeDto implements Serializable, Comparable<ResumoOperacaoRedeDto> {
	
	private Long id;
	private List<ComprovanteVendaCieloDto> comprovantes;
	private Integer tipoRegistro;
	private Integer estabelecimentoSubmissor;
	private Integer numeroRO;
	private Integer parcela;
	private String filler;
	private String plano;
	private TipoTransacaoDto tipoTransacao;
	private Date dataApresentacao;
	private Date dataPrevistaPagamento;
	private Date dataEnvioBanco;
	private BigDecimal valorBruto;
	private BigDecimal valorComissao;
	private BigDecimal valorRejeitado;
	private BigDecimal valorLiquido;
	private Integer banco;
	private Integer agencia;
	private String contaCorrente;
	private StatusPagamentoDto statusPagamento;
	private Integer quantidadeCVAceitos;
	private Integer quantidadeCvsRejeitados;
	private String identificarRevendaAceleracao;
	private Date dataCapturaTransacao;
	private OrigemAjusteDto origemAjuste;
	private BigDecimal valorComplementar;
	private String identificadorProdutoFinanceiro;
	private Integer numeroOperacaoFinanceira;
	private BigDecimal valorBrutoAntecipado;
	private CodigoBandeiraDto codigoBandeira;
	private String numeroUnicoRO;
	private BigDecimal taxaComissao;
	private BigDecimal tarifa;
	private BigDecimal taxaGarantia;
	private MeioCapturaDto meioCaptura;
	private Integer numeroLogicoTerminal;
	private CodigoProdutoDto identificadorProduto;
	private String usoRede;
	
	public ResumoOperacaoRedeDto() {
	}
	
	public ResumoOperacaoRedeDto(Long id, Integer estabelecimentoSubmissor, Integer numeroRO,
			Integer parcela, String filler, String plano, TipoTransacaoDto tipoTransacao, Date dataApresentacao,
			Date dataPrevistaPagamento, Date dataEnvioBanco, BigDecimal valorBruto, BigDecimal valorComissao,
			BigDecimal valorRejeitado, BigDecimal valorLiquido, Integer banco, Integer agencia, String contaCorrente,
			StatusPagamentoDto statusPagamento, Integer quantidadeCVAceitos, Integer quantidadeCvsRejeitados,
			String identificarRevendaAceleracao, Date dataCapturaTransacao, OrigemAjusteDto origemAjuste,
			BigDecimal valorComplementar, String identificadorProdutoFinanceiro, Integer numeroOperacaoFinanceira,
			BigDecimal valorBrutoAntecipado, CodigoBandeiraDto codigoBandeira, String numeroUnicoRO, BigDecimal taxaComissao,
			BigDecimal tarifa, BigDecimal taxaGarantia, MeioCapturaDto meioCaptura, Integer numeroLogicoTerminal,
			CodigoProdutoDto identificadorProduto, String usoRede) {
		super();
		this.id = id;
		this.estabelecimentoSubmissor = estabelecimentoSubmissor;
		this.numeroRO = numeroRO;
		this.parcela = parcela;
		this.filler = filler;
		this.plano = plano;
		this.tipoTransacao = tipoTransacao;
		this.dataApresentacao = dataApresentacao;
		this.dataPrevistaPagamento = dataPrevistaPagamento;
		this.dataEnvioBanco = dataEnvioBanco;
		this.valorBruto = valorBruto;
		this.valorComissao = valorComissao;
		this.valorRejeitado = valorRejeitado;
		this.valorLiquido = valorLiquido;
		this.banco = banco;
		this.agencia = agencia;
		this.contaCorrente = contaCorrente;
		this.statusPagamento = statusPagamento;
		this.quantidadeCVAceitos = quantidadeCVAceitos;
		this.quantidadeCvsRejeitados = quantidadeCvsRejeitados;
		this.identificarRevendaAceleracao = identificarRevendaAceleracao;
		this.dataCapturaTransacao = dataCapturaTransacao;
		this.origemAjuste = origemAjuste;
		this.valorComplementar = valorComplementar;
		this.identificadorProdutoFinanceiro = identificadorProdutoFinanceiro;
		this.numeroOperacaoFinanceira = numeroOperacaoFinanceira;
		this.valorBrutoAntecipado = valorBrutoAntecipado;
		this.codigoBandeira = codigoBandeira;
		this.numeroUnicoRO = numeroUnicoRO;
		this.taxaComissao = taxaComissao;
		this.tarifa = tarifa;
		this.taxaGarantia = taxaGarantia;
		this.meioCaptura = meioCaptura;
		this.numeroLogicoTerminal = numeroLogicoTerminal;
		this.identificadorProduto = identificadorProduto;
		this.usoRede = usoRede;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(Integer tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public Integer getEstabelecimentoSubmissor() {
		return estabelecimentoSubmissor;
	}
	public void setEstabelecimentoSubmissor(Integer estabelecimentoSubmissor) {
		this.estabelecimentoSubmissor = estabelecimentoSubmissor;
	}
	public Integer getNumeroRO() {
		return numeroRO;
	}
	public void setNumeroRO(Integer numeroRO) {
		this.numeroRO = numeroRO;
	}
	public Integer getParcela() {
		return parcela;
	}
	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	public String getPlano() {
		return plano;
	}
	public void setPlano(String plano) {
		this.plano = plano;
	}
	public TipoTransacaoDto getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(TipoTransacaoDto tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	public Date getDataApresentacao() {
		return dataApresentacao;
	}
	public void setDataApresentacao(Date dataApresentacao) {
		this.dataApresentacao = dataApresentacao;
	}
	public Date getDataPrevistaPagamento() {
		return dataPrevistaPagamento;
	}
	public void setDataPrevistaPagamento(Date dataPrevistaPagamento) {
		this.dataPrevistaPagamento = dataPrevistaPagamento;
	}
	public Date getDataEnvioBanco() {
		return dataEnvioBanco;
	}
	public void setDataEnvioBanco(Date dataEnvioBanco) {
		this.dataEnvioBanco = dataEnvioBanco;
	}
	public BigDecimal getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}
	public BigDecimal getValorComissao() {
		return valorComissao;
	}
	public void setValorComissao(BigDecimal valorComissao) {
		this.valorComissao = valorComissao;
	}
	public BigDecimal getValorRejeitado() {
		return valorRejeitado;
	}
	public void setValorRejeitado(BigDecimal valorRejeitado) {
		this.valorRejeitado = valorRejeitado;
	}
	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
	public Integer getBanco() {
		return banco;
	}
	public void setBanco(Integer banco) {
		this.banco = banco;
	}
	public Integer getAgencia() {
		return agencia;
	}
	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}
	public String getContaCorrente() {
		return contaCorrente;
	}
	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}
	public StatusPagamentoDto getStatusPagamento() {
		return statusPagamento;
	}
	public void setStatusPagamento(StatusPagamentoDto statusPagamento) {
		this.statusPagamento = statusPagamento;
	}
	public Integer getQuantidadeCVAceitos() {
		return quantidadeCVAceitos;
	}
	public void setQuantidadeCVAceitos(Integer quantidadeCVAceitos) {
		this.quantidadeCVAceitos = quantidadeCVAceitos;
	}
	public Integer getQuantidadeCvsRejeitados() {
		return quantidadeCvsRejeitados;
	}
	public void setQuantidadeCvsRejeitados(Integer quantidadeCvsRejeitados) {
		this.quantidadeCvsRejeitados = quantidadeCvsRejeitados;
	}
	public String getIdentificarRevendaAceleracao() {
		return identificarRevendaAceleracao;
	}
	public void setIdentificarRevendaAceleracao(String identificarRevendaAceleracao) {
		this.identificarRevendaAceleracao = identificarRevendaAceleracao;
	}
	public Date getDataCapturaTransacao() {
		return dataCapturaTransacao;
	}
	public void setDataCapturaTransacao(Date dataCapturaTransacao) {
		this.dataCapturaTransacao = dataCapturaTransacao;
	}
	public OrigemAjusteDto getOrigemAjuste() {
		return origemAjuste;
	}
	public void setOrigemAjuste(OrigemAjusteDto origemAjuste) {
		this.origemAjuste = origemAjuste;
	}
	public BigDecimal getValorComplementar() {
		return valorComplementar;
	}
	public void setValorComplementar(BigDecimal valorComplementar) {
		this.valorComplementar = valorComplementar;
	}
	public String getIdentificadorProdutoFinanceiro() {
		return identificadorProdutoFinanceiro;
	}
	public void setIdentificadorProdutoFinanceiro(String identificadorProdutoFinanceiro) {
		this.identificadorProdutoFinanceiro = identificadorProdutoFinanceiro;
	}
	public Integer getNumeroOperacaoFinanceira() {
		return numeroOperacaoFinanceira;
	}
	public void setNumeroOperacaoFinanceira(Integer numeroOperacaoFinanceira) {
		this.numeroOperacaoFinanceira = numeroOperacaoFinanceira;
	}
	public BigDecimal getValorBrutoAntecipado() {
		return valorBrutoAntecipado;
	}
	public void setValorBrutoAntecipado(BigDecimal valorBrutoAntecipado) {
		this.valorBrutoAntecipado = valorBrutoAntecipado;
	}
	public CodigoBandeiraDto getCodigoBandeira() {
		return codigoBandeira;
	}
	public void setCodigoBandeira(CodigoBandeiraDto codigoBandeira) {
		this.codigoBandeira = codigoBandeira;
	}
	public String getNumeroUnicoRO() {
		return numeroUnicoRO;
	}
	public void setNumeroUnicoRO(String numeroUnicoRO) {
		this.numeroUnicoRO = numeroUnicoRO;
	}
	public BigDecimal getTaxaComissao() {
		return taxaComissao;
	}
	public void setTaxaComissao(BigDecimal taxaComissao) {
		this.taxaComissao = taxaComissao;
	}
	public BigDecimal getTarifa() {
		return tarifa;
	}
	public void setTarifa(BigDecimal tarifa) {
		this.tarifa = tarifa;
	}
	public BigDecimal getTaxaGarantia() {
		return taxaGarantia;
	}
	public void setTaxaGarantia(BigDecimal taxaGarantia) {
		this.taxaGarantia = taxaGarantia;
	}
	public MeioCapturaDto getMeioCaptura() {
		return meioCaptura;
	}
	public void setMeioCaptura(MeioCapturaDto meioCaptura) {
		this.meioCaptura = meioCaptura;
	}
	public Integer getNumeroLogicoTerminal() {
		return numeroLogicoTerminal;
	}
	public void setNumeroLogicoTerminal(Integer numeroLogicoTerminal) {
		this.numeroLogicoTerminal = numeroLogicoTerminal;
	}
	public CodigoProdutoDto getIdentificadorProduto() {
		return identificadorProduto;
	}
	public void setIdentificadorProduto(CodigoProdutoDto identificadorProduto) {
		this.identificadorProduto = identificadorProduto;
	}
	public String getUsoRede() {
		return usoRede;
	}
	public void setUsoRede(String usoRede) {
		this.usoRede = usoRede;
	}
	public List<ComprovanteVendaCieloDto> getComprovantes() {
		return comprovantes;
	}
	public void setComprovantes(List<ComprovanteVendaCieloDto> comprovantes) {
		this.comprovantes = comprovantes;
	}
	
	@Override
	public int compareTo(ResumoOperacaoRedeDto o) {
		if (this.dataEnvioBanco == null || o.getDataEnvioBanco() == null) {
			return 0;
		}
			
	    return this.dataEnvioBanco.compareTo(o.getDataEnvioBanco());
	}
}