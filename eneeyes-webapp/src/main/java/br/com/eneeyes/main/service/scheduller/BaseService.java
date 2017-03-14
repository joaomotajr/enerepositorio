/**
 * 
 */
package br.com.eneeyes.main.service.scheduller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.inject.Inject;

/**
 * @author f771274
 *
 */
public abstract class BaseService {

	protected final String localhost = "127.0.0.1";
	protected final String usernameAdmin = "SKYNET";
	protected final String segundaASexta = "Mon-Fri";
	protected final String seteETrezeHoras = "7, 13";
	protected final String timestampFormat = "dd/MM/yyyy às HH:mm:ss";

	@Inject
	protected Logger log;
	
	public abstract void sheculle();

	/**
	 * Inclusão do Usuário do Sistema - SKYNET
	 */
//	protected void setUsername() {
//		PrincipalThreadLocal.USER.set(new Principal().username(usernameAdmin).role(Roles.ADMIN).ip(localhost));
//	}

	public void init() {
		log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ": Start Automatico :: " + new SimpleDateFormat(timestampFormat).format(Calendar.getInstance().getTime()));
		//setUsername();
	}

	@Timeout
	public void timeout(Timer timer) {
		log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ": Ocorreu Timeout :: ");
	}
}
