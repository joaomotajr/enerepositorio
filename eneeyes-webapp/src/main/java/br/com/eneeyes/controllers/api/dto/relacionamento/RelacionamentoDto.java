package br.com.eneeyes.controllers.api.dto.relacionamento;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RelacionamentoDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long filialId;
	private Long userId;
	private List<String> listaCnpjFiliais;
	private List<String> listaLoginUsers;
	
	public RelacionamentoDto() {
	}

	/**
	 * @return the filialId
	 */
	@XmlElement
	public Long getFilialId() {
		return filialId;
	}

	/**
	 * @param filialId the filialId to set
	 */
	public void setFilialId(Long filialId) {
		this.filialId = filialId;
	}

	/**
	 * @return the userId
	 */
	@XmlElement
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the listaCnpjFiliais
	 */
	@XmlElement
	public List<String> getListaCnpjFiliais() {
		return listaCnpjFiliais;
	}

	/**
	 * @param listaCnpjFiliais the listaCnpjFiliais to set
	 */
	public void setListaCnpjFiliais(List<String> listaCnpjFiliais) {
		this.listaCnpjFiliais = listaCnpjFiliais;
	}

	/**
	 * @return the listaLoginUsers
	 */
	@XmlElement
	public List<String> getListaLoginUsers() {
		return listaLoginUsers;
	}

	/**
	 * @param listaLoginUsers the listaLoginUsers to set
	 */
	public void setListaLoginUsers(List<String> listaLoginUsers) {
		this.listaLoginUsers = listaLoginUsers;
	}

}
