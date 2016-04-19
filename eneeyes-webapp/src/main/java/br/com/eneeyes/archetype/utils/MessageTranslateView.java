package br.com.eneeyes.archetype.utils;

/**
 * Enum dos atributos message-translate.js utilizado 
 * para renderizacao de mensagens na view 
 */
public enum MessageTranslateView {
	
	GENERIC_ERROR("generic.error"),
	GENERIC_SUCESS_NO_DATA("generic.success.no.data"),
	
	PASSWORD_ERROR("password.error"),
	NEW_PASSWORD_ERROR_DISTINCTS("new.password.error.distincts"),
	NEW_PASSWORD_ERROR_USED("new.password.error.used"),
	NEW_PASSWORD_GENERIC_ERROR("new.password.generic.error"),
	NEW_PASSWORD_SUCCESS("new.password.success"),
	
	USER_ERROR_ROLE("user.error.role"),
	USER_ERROR_EXIST("user.error.exist"),
	USER_ERROR_FILIAL_EXIST("user.error.filial.exist"),
	USER_SUCCESS_SAVE("user.success.save"),
	USER_SUCCESS_UPDATE("user.success.update"),
	USER_SUCCESS_DELETE("user.success.delete"),
	USER_SUCCESS_FOUND("user.success.found"),
	USER_RELATED_SUCCESS("user_related_success"),
	
	FILIAL_ERROR_USER("filial.error.user"),
	FILIAL_ERROR_USER_ROLE("filial.error.user.role"),
	FILIAL_ERROR_EXIST("filial.error.exist"),
	FILIAL_SUCCESS_SAVE("filial.success.save"),
	FILIAL_SUCCESS_UPDATE("filial.success.update"),
	FILIAL_SUCCESS_DELETE("filial.success.delete"),
	FILIAL_RELATED_SUCCESS("filial_related_success"),

	FILIAL_USER_GROUPED("filial_user_grouped"),
	
	GRUPO_ERROR_EXIST("grupo.error.exist"),
	GRUPO_SUCCESS_SAVE("grupo.success.save"),
	GRUPO_SUCCESS_UPDATE("grupo.success.update"),
	GRUPO_SUCCESS_DELETE("grupo.success.delete");
	
	private String value;
	
	MessageTranslateView(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	private void setValue(String value) {
		this.value = value;
	}

}
