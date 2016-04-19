package br.com.eneeyes.controllers.api.dto.user;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.eneeyes.archetype.utils.MessageDigester;

@XmlRootElement
public class UserPassDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String username;
	private String password;
	private String newPassword;
	private String confirm;
	
	public UserPassDto() {
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
	 * @return the username
	 */
	@XmlElement
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	@XmlElement
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the newPassword
	 */
	@XmlElement
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the confirm
	 */
	@XmlElement
	public String getConfirm() {
		return confirm;
	}

	/**
	 * @param confirm the confirm to set
	 */
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public void setPasswordDigestSha1() {
		try {
			setPassword(MessageDigester.digestSha1(getPassword()));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void setNewPasswordDigestSha1() {
		try {
			setNewPassword(MessageDigester.digestSha1(getNewPassword()));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void setConfirmdDigestSha1() {
		try {
			setConfirm(MessageDigester.digestSha1(getConfirm()));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
