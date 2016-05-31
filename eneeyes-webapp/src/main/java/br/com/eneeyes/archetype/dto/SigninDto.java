package br.com.eneeyes.archetype.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Alan on 17/05/2014.
 */
@XmlRootElement
public class SigninDto {
    String login;

    String credential;

    public SigninDto() {
    }

    public SigninDto(String login, String credential) {
        this.login = login;
        this.credential = credential;
    }

    @XmlAttribute
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @XmlAttribute
    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }
}
