package br.com.eneeyes.archetype.services;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.archetype.model.User;
import br.com.eneeyes.archetype.repository.UserRepository;
import br.com.eneeyes.archetype.result.IdentityResult;
import br.com.eneeyes.archetype.utils.MessageDigester;
import br.com.eneeyes.archetype.web.result.ResultErrorMessage;
import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.archetype.web.result.ResultSuccessMessage;

@Service
public class IdentityServiceImpl implements IdentityService {

	private Log log = LogFactory.getLog(getClass());

    @Inject
    private br.com.eneeyes.archetype.web.config.auth.SecurityManager securityManager;

    @Autowired
	private UserRepository repository;

	@Autowired
	private Validator validator;

	@Transactional
	public IdentityResult findByLogin(String login) {
		ResultMessageType resultType = ResultMessageType.SUCCESS;
		User user = null;
		try {
			user = repository.findByLogin(login);
		} catch (NoResultException e) {
			log.error(e);
			resultType = ResultMessageType.ERROR;
		}
		return new IdentityResult(resultType, null, user);
	}

	@Transactional
	public IdentityResult save(User user) {
		IdentityResult result = new IdentityResult();
		ResultMessageType resultType = ResultMessageType.ERROR;
		
		try {
            if(user.getId() == null || user.getId() < 1) {
                user.setCreateDate(new Date());
            }
            if((user.getId() == null || user.getId() < 1) && (user.getHash() == null || user.getHash().trim().length() < 1)) {
                user.setHash(MessageDigester.digestSha1(UUID.randomUUID().toString()));
			}
			
			result = persist(user);
			resultType = result.getResultType();
			
			if(ResultMessageType.SUCCESS.equals(result.getResultType()))
				result.getMessages().add(new ResultSuccessMessage(""));
		} catch(Exception e) {
			log.error(e);
			resultType = ResultMessageType.ERROR;
			result.getMessages().add(new ResultErrorMessage(e.getMessage()));
		} catch (Throwable e) {
			log.error(e);
			resultType = ResultMessageType.ERROR;
			result.getMessages().add(new ResultErrorMessage(e.getMessage()));
		}
		
		result.setResultType(resultType);
		
		return result;
	}
	
	@Transactional
	public IdentityResult persist(User user) {
		IdentityResult result = new IdentityResult();
		ResultMessageType resultType = ResultMessageType.SUCCESS;
		
		try {
			if(user == null) 
				throw new IllegalArgumentException();
			
			Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
			for(ConstraintViolation<User> violation : constraintViolations) 
				result.getMessages().add(new ResultErrorMessage(violation.getPropertyPath()+"#"+violation.getMessage()));
			
			if(result.getErrorMessages().size() > 0)
				resultType = ResultMessageType.ERROR;
			
			if(!ResultMessageType.ERROR.equals(resultType))
				repository.save(user);
			
			
		} catch(Exception e) {
			log.error(e);
			resultType = ResultMessageType.ERROR;
			result.getMessages().add(new ResultErrorMessage(e.getMessage()));
		}
		
		result.setResultType(resultType);
		
		return result;
	}

	@Transactional
	public IdentityResult remove(Long userId) {
		IdentityResult result = new IdentityResult();
		ResultMessageType resultType = ResultMessageType.SUCCESS;
		try {
			if(userId == null || userId < 1) 
				throw new IllegalArgumentException();
			repository.delete(userId);
			result.getMessages().add(new ResultSuccessMessage(""));
		} catch(Exception e) {
			log.error(e);
			resultType = ResultMessageType.ERROR;
		}
		result.setResultType(resultType);
		return result;
	}

	@Override
    public IdentityResult changePassword(String pass1, String pass2) {
        
		IdentityResult result = new IdentityResult();
        ResultMessageType resultType = ResultMessageType.SUCCESS;
        
        try {
            User user = repository.findOne(securityManager.currentUser().getId());

            if (!Pattern.matches("^[0-9a-zA-ZáéíóúàâêôãõüçÁÉÍÓÚÀÂÊÔÃÕÜÇöÖñÑ@. ]{6,10}$", pass1)) {
                result.addMessage(new ResultErrorMessage("form.erro.site.changePassword.invalido"));
                resultType = ResultMessageType.ERROR;
                return result;
            }

            if (!pass1.equals(pass2)) {
                result.addMessage(new ResultErrorMessage("form.erro.site.changePassword.naoConfere"));
                resultType = ResultMessageType.ERROR;
                return result;
            }

            if (user.getLogin() == null || !Pattern.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", user.getLogin())) {
                result.addMessage(new ResultErrorMessage("form.erro.site.changePassword.perfilIncompleto"));
                resultType = ResultMessageType.ERROR;
                return result;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 3);
            
            Map<String, Object> token = new HashMap<String, Object>();
            
            token.put("expire",calendar.getTime());
            token.put("hash",MessageDigester.digestSha1(pass1));

            user.setHash(MessageDigester.digestSha1(pass1).toString());
            
            save(user);

            result.addMessage(new ResultSuccessMessage("form.success.site.changePassword"));
        } catch (Throwable throwable) {
            result.addMessage(new ResultErrorMessage("system.error.changePassword"));
            log.error(throwable);
        }
        result.setResultType(resultType);
        return result;
    }

    @Override
	public boolean deveTrocarSenha(User user) throws Exception
	{
    	// TODO Auto-generated method stub
    	return false;
	}

}