package br.com.eneeyes.main.model.register;

import java.util.Date;

import javax.persistence.Column;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro de informações comuns a todos os dispositivos
 */

public abstract class BaseDevice {
		
	@Column(name = "DATE", nullable = true)
	private Date date;		
	
	public final Date getDate() {
		return date;
	}

	public final void setDate(Date date) {
		this.date = date;
	}

}
