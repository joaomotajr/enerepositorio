package br.com.eneeyes.archetype.dto.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.eneeyes.archetype.model.acl.Role;
import br.com.eneeyes.archetype.model.acl.User;
import br.com.eneeyes.archetype.model.acl.UserStatus;
import br.com.eneeyes.archetype.utils.MessageDigester;

@XmlRootElement
public class UserDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String cpf;
	private String cnpj;
	private String displayName;
	private String nickname;
	private String fone;
	private String cell;
	private String email;
	private Long role;
	private String login;
	private String hash;
	private UserStatus status;
	private Date createDate;
	private Long filialId;
	private String providerId;
	private String providerUserId;
	private String profileUrl;
	private String imageUrl;
	private String accessToken;
	private String secret;
	private String refreshToken;
	private Long expireTime;
	
	public UserDto() {
	}

	public UserDto(User user) {
		this.id = user.getId();
		this.cpf = user.getCpf();
		this.cnpj = user.getCnpj();
		this.displayName = user.getDisplayName();
		this.nickname = user.getNickname();
		this.fone = user.getFone();
		this.cell = user.getCell();
		this.email = user.getEmail();
		if(user.getRoles() != null) {
			Iterator<Role> iter = user.getRoles().iterator();
			Role role = (Role) iter.next();
			this.role = role.getId();
		}
		this.login = user.getLogin();
		this.hash = user.getHash();
		this.status = user.getStatus();
		this.createDate = user.getCreateDate();
		this.providerId = user.getProviderId();
		this.providerUserId = user.getProviderUserId();
		this.profileUrl = user.getProfileUrl();
		this.imageUrl = user.getImageUrl();
		this.accessToken = user.getAccessToken();
		this.secret = user.getSecret();
		this.refreshToken = user.getRefreshToken();
		this.expireTime = user.getExpireTime();
	}

	/**
	 * @return the id
	 */
	@XmlElement
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the cpf
	 */
	@XmlElement
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	/**
	 * @return the cnpj
	 */
	@XmlElement
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * @param cnpj the cnpj to set
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	/**
	 * @return the displayName
	 */
	@XmlElement
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	/**
	 * @return the nickname
	 */
	@XmlElement
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * @return the fone
	 */
	@XmlElement
	public String getFone() {
		return fone;
	}

	/**
	 * @param fone the fone to set
	 */
	public void setFone(String fone) {
		this.fone = fone;
	}

	/**
	 * @return the cell
	 */
	@XmlElement
	public String getCell() {
		return cell;
	}

	/**
	 * @param cell the cell to set
	 */
	public void setCell(String cell) {
		this.cell = cell;
	}

	/**
	 * @return the email
	 */
	@XmlElement
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the role
	 */
	@XmlElement
	public Long getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Long role) {
		this.role = role;
	}
	
	/**
	 * @return the login
	 */
	@XmlElement
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the hash
	 */
	@XmlElement
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	/**
	 * @param hash the hash to set
	 */
	public void setHashDigestSha1() {
		try {
			setHash(MessageDigester.digestSha1(getHash()));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the status
	 */
	@XmlElement
	public UserStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
	/**
	 * @return the createDate
	 */
	@XmlElement
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	 * @return the providerId
	 */
	@XmlElement
	public String getProviderId() {
		return providerId;
	}

	/**
	 * @param providerId the providerId to set
	 */
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	/**
	 * @return the providerUserId
	 */
	@XmlElement
	public String getProviderUserId() {
		return providerUserId;
	}

	/**
	 * @param providerUserId the providerUserId to set
	 */
	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	/**
	 * @return the profileUrl
	 */
	@XmlElement
	public String getProfileUrl() {
		return profileUrl;
	}

	/**
	 * @param profileUrl the profileUrl to set
	 */
	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	/**
	 * @return the imageUrl
	 */
	@XmlElement
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the accessToken
	 */
	@XmlElement
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the secret
	 */
	@XmlElement
	public String getSecret() {
		return secret;
	}

	/**
	 * @param secret the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * @return the refreshToken
	 */
	@XmlElement
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * @return the expireTime
	 */
	@XmlElement
	public Long getExpireTime() {
		return expireTime;
	}

	/**
	 * @param expireTime the expireTime to set
	 */
	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

}