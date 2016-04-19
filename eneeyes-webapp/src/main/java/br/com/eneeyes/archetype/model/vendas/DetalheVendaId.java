package br.com.eneeyes.archetype.model.vendas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class DetalheVendaId implements Serializable {

	@Column(name="NSU_HOST_")
	private Integer nsuHost;
	
	@Column(name="DATA_LANCAMEN_")
    @Temporal(TemporalType.TIMESTAMP)
	private Date dataLancamen;
	
    public DetalheVendaId() {
 
    }
 
    public DetalheVendaId(Integer nsuHost, Date dataLancamen) {
        this.nsuHost = nsuHost;
        this.dataLancamen = dataLancamen;
    }
 
    public Integer getNsuHost() {
        return nsuHost;
    }
 
    public Date getDataLancamen() {
        return dataLancamen;
    }
 
    @Override
    public int hashCode() {
        return nsuHost.hashCode() + dataLancamen.hashCode();
    }
 
    // Must have an equals method
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DetalheVendaId) {
        	DetalheVendaId detalheVendaId = (DetalheVendaId) obj;
            return detalheVendaId.nsuHost.equals(this.nsuHost) && detalheVendaId.dataLancamen == this.dataLancamen;
        }
 
        return false;
    }
}
