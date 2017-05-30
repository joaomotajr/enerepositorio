package br.com.eneeyes.archetype.services.identity;

import java.io.IOException;
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
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eneeyes.archetype.model.ServiceListEmbedded;
import br.com.eneeyes.archetype.model.acl.Role;
import br.com.eneeyes.archetype.model.acl.User;
import br.com.eneeyes.archetype.model.acl.UserCriteria;
import br.com.eneeyes.archetype.model.acl.UserRepository;
import br.com.eneeyes.archetype.result.UserCollectionResult;
import br.com.eneeyes.archetype.result.UserResult;
import br.com.eneeyes.archetype.utils.MessageDigester;
import br.com.eneeyes.archetype.web.result.ResultErrorMessage;
import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.archetype.web.result.ResultSuccessMessage;

@Service
public class IdentityServiceImpl extends ServiceListEmbedded<User, UserCriteria, UserCollectionResult> implements IdentityService {

	private Log log = LogFactory.getLog(getClass());

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private br.com.eneeyes.archetype.web.config.auth.SecurityManager securityManager;

    @Autowired
	private UserRepository repository;

	@Autowired
	private Validator validator;
	
    @SuppressWarnings("unused")
	@Autowired
	private RoleService roleService;

	@Override
	public UserCollectionResult newResultCollection() {
		return new UserCollectionResult();
	}

	@Override
	public User toNativeBean(User source) {
		User target = new User();

        if (source == null) {
            return target;
        }

		target.setId(source.getId());
		target.setLogin(source.getLogin());
		target.setNickname(source.getNickname());
		target.setDisplayName(source.getDisplayName());
		target.setHash(source.getHash());
		target.setCreateDate(source.getCreateDate());
		target.setStatus(source.getStatus());

		for(Role role : source.getRoles()) {
			target.getRoles().add(role);
		}
		return target;
	}

	@Transactional
	public UserResult findByLogin(String login) {
		ResultMessageType resultType = ResultMessageType.SUCCESS;
		User user = null;
		try {
			user = repository.findByLogin(login);
		} catch (NoResultException e) {
			log.error(e);
			resultType = ResultMessageType.ERROR;
		}
		return new UserResult(resultType, null, user);
	}

	@Transactional
	public UserResult save(User user) {
		UserResult result = new UserResult();
		ResultMessageType resultType = ResultMessageType.ERROR;
		
		try {
            if(user.getId() == null || user.getId() < 1) {
                user.setCreateDate(new Date());
            }
            if((user.getId() == null || user.getId() < 1) && (user.getHash() == null || user.getHash().trim().length() < 1)) {
                user.setHash(MessageDigester.digestSha1(UUID.randomUUID().toString()));
			}
			
			if(user.getRoles().size() < 1) {
				result.getMessages().add(new ResultErrorMessage("roles#may not be empty"));
				result.setUser(toNativeBean(user));
				return result;
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
		result.setUser(toNativeBean(user));
		
		return result;
	}
	
	@Transactional
	public UserResult persist(User user) {
		UserResult result = new UserResult();
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
		result.setUser(toNativeBean(user));
		
		return result;
	}

	@Transactional
	public UserResult remove(Long userId) {
		UserResult result = new UserResult();
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
    public UserResult changePassword(String pass1, String pass2) {
        
		UserResult result = new UserResult();
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
            
            String json = objectMapper.writeValueAsString(token);
            String refreshToken = MessageDigester.encode64(json);

            user.setHash(MessageDigester.digestSha1(pass1).toString());
            user.setRefreshToken(refreshToken);
            
            save(user);

            result.addMessage(new ResultSuccessMessage("form.success.site.changePassword"));
        } catch (Throwable throwable) {
            result.addMessage(new ResultErrorMessage("system.error.changePassword"));
            log.error(throwable);
        }
        result.setResultType(resultType);
        return result;
    }



	public UserResult findByRefreshToken(String refreshToken) throws Exception {
		UserResult result = new UserResult();
		try {
			String json = MessageDigester.decode64(refreshToken);
			
			@SuppressWarnings("unchecked")
			Map<String, Object> token = objectMapper.readValue(json, Map.class);

			Long now = Calendar.getInstance().getTime().getTime();
			Long expireTime = (Long)token.get("expire");
			if (now > expireTime) {
				result.addMessage(new ResultErrorMessage("auth.message.token.expired"));
				return result;
			}

			User user = repository.findByRefreshToken(refreshToken);
			if (user == null || !user.getHash().equals((String)token.get("hash"))) {
				result.addMessage(new ResultErrorMessage("auth.message.token.invalid"));
				return result;
			}

			result.setUser(user);

			result.addMessage(new ResultSuccessMessage("auth.message.token.valid"));
		} catch (IOException e) {
			result.addMessage(new ResultErrorMessage("auth.message.invalid"));
			log.error(e);
		}
		return result;
	}

	  @Override
	  public boolean deveTrocarSenha(User user) throws Exception
	  {
	    // TODO Auto-generated method stub
	    return false;
	  }

	@Override
	public UserResult findUserConnection(String providerId, String providerUserId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public UserCollectionResult findUsersConnections(String providerId, Set<String> ids) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}