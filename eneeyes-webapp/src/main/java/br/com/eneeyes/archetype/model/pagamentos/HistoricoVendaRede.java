package br.com.eneeyes.archetype.model.pagamentos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.eneeyes.archetype.model.cieloCodeData.TipoOpcaoExtrato;
import br.com.eneeyes.controllers.api.dto.cieloCodeData.TipoOpcaoExtratoDto;
import br.com.eneeyes.controllers.api.dto.vendas.HistoricoVendaRedeDto;

@Entity
@Table(name="historico_venda_rede")
public class HistoricoVendaRede {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_")
	private Long id;
	
	@Column(name="ESTABELECIMENTO_MATRIZ_")
	private Integer estabelecimentoMatriz;
	
	@Column(name="DATA_PROCESSAMENTO_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataProcessamento;
	
	@Column(name="PERIODO_INICIAL_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date periodoInicial;
	
	@Column(name="PERIODO_FINAL_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date periodoFinal;
	
	@Column(name="SEQUENCIA_")
	private Integer sequencia;
	
	@Column(name="EMPRESA_ADQUIRENTE_")
	private String empresaAdquirente;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="OPCAO_EXTRATO_")
	private TipoOpcaoExtrato opcaoExtrato;
	
	@Column(name="VAN_")
	private String van;
	
	@Column(name="CAIXA_POSTAL_")
	private String caixaPostal;
	
	@Column(name="VERSAO_LAYOUT_")
	private String versaoLayout;
	
	@Column(name="USO_CIELO_")
	private String usoRede;
	
	@Column(name="INTEGRACAO_MANUAL_")
	private Boolean isManual;

	public HistoricoVendaRede() {
	}

	public HistoricoVendaRede(Long id, Integer estabelecimentoMatriz, Date dataProcessamento, Date periodoInicial,
			Date periodoFinal, Integer sequencia, String empresaAdquirente, TipoOpcaoExtrato opcaoExtrato, String van,
			String caixaPostal, String versaoLayout, String usoRede, Boolean isManual) {
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
		this.usoRede = usoRede;
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

	public TipoOpcaoExtrato getOpcaoExtrato() {
		return opcaoExtrato;
	}

	public void setOpcaoExtrato(TipoOpcaoExtrato opcaoExtrato) {
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

	public String getUsoRede() {
		return usoRede;
	}

	public void setUsoRede(String usoRede) {
		this.usoRede = usoRede;
	}
	
	public Boolean getIsManual() {
		return isManual;
	}

	public void setIsManual(Boolean isManual) {
		this.isManual = isManual;
	}

	public static HistoricoVendaRede fromHistoricoVendaRedeDto(HistoricoVendaRedeDto dto) {
		TipoOpcaoExtrato tipoOpcaoExtrato = null;
		if (dto.getOpcaoExtrato() != null) {
			tipoOpcaoExtrato = TipoOpcaoExtrato.fromTipoOpcaoExtratoDto(dto.getOpcaoExtrato());
		}
		
		return new HistoricoVendaRede(dto.getId(), dto.getEstabelecimentoMatriz(), dto.getDataProcessamento(), dto.getPeriodoInicial(),
				dto.getPeriodoFinal(), dto.getSequencia(), dto.getEmpresaAdquirente(), tipoOpcaoExtrato, dto.getVan(),
				dto.getCaixaPostal(), dto.getVersaoLayout(), dto.getUsoRede(), dto.getIsManual());
	}

    public HistoricoVendaRedeDto toHistoricoVendaRedeDto() {
    	TipoOpcaoExtratoDto opcaoExtratoDto = null;
    	if (this.opcaoExtrato != null) {
    		opcaoExtratoDto = this.opcaoExtrato.toTipoOpcaoExtratoDto();
    	}
    	
        return new HistoricoVendaRedeDto(this.id, this.estabelecimentoMatriz, this.dataProcessamento, this.periodoInicial, this.periodoFinal,
        		this.sequencia, this.empresaAdquirente, opcaoExtratoDto, this.van, this.caixaPostal, this.versaoLayout, this.usoRede, this.isManual);
    }
}