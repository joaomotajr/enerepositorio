package br.com.eneeyes.archetype.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.dto.UserDto;
import br.com.eneeyes.archetype.dto.UserPassDto;
import br.com.eneeyes.archetype.model.Role;
import br.com.eneeyes.archetype.model.User;
import br.com.eneeyes.archetype.repository.UserRepository;
import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;


	@Service
	public class UserService {

  	public BasicResult<?> save(UserDto dto)
  	{
  		Result<UserDto> result = new Result<UserDto>(); 	
  		dto.setHashDigestSha1();
  		
		User user = new User(dto);
		user.setCreateDate(new Date());
				
		user = repository.save(user);
				
		dto.setId(user.getId());
		dto.setCreateDate(user.getCreateDate());
		
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");	
		
		return result;
  	}

	@Inject
	private UserRepository repository;
		
	public BasicResult<?> listAll()
	{
		Result<UserDto> result = new Result<UserDto>();
				
		try {
			List<User> lista = repository.findAll();

			if (lista != null) {
				
				List<UserDto> dto = new ArrayList<UserDto>();
				
				for (User user   : lista) {					
					dto.add(new UserDto(user) );
				}
				
				result.setList(dto);				
				result.setResultType( ResultMessageType.SUCCESS );
				
			} else {
				
				result.setResultType( ResultMessageType.NO_DATA );
								
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setResultType( ResultMessageType.ERROR );									
		}	
		
		return result;		
	}

	public BasicResult<?> delete(Long uid) {
		Result<UserDto> result = new Result<UserDto>(); 	
		
		try {			
			repository.delete(uid);
			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Usuário Excluído.");
			
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setSystemMessage(e.getMessage());			
		}		
		
		return result;
	}

	public BasicResult<?> pesquisaUserByLogin(String login) {
		Result<UserDto> result = new Result<UserDto>();
		
		try {
			User user = repository.findByLogin(login);

			if (user != null) {
				
				UserDto dto = new UserDto(user);
				
				result.setEntity(dto); ;				
				result.setResultType( ResultMessageType.SUCCESS );
				
			} else {
				
				result.setResultType( ResultMessageType.NO_DATA );
								
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setResultType( ResultMessageType.ERROR );									
		}	
		
		return result;
	}

	public BasicResult<?> updateUser(UserDto dto) {
		Result<UserDto> result = new Result<UserDto>(); 	
				
		User user = repository.findOne(dto.getId());
		
		if (user != null) {
			
			user.setCpf(dto.getCpf());
			user.setDisplayName(dto.getDisplayName());
			user.setNickname(dto.getNickname());
			user.setFone(dto.getFone());
			user.setCell(dto.getCell());
			user.setEmail(dto.getEmail());
			user.setStatus(dto.getStatus());			
			user.setCreateDate(new Date());
			user.setCompany(dto.getCompanyDto());
						
			if(dto.getReset()) {
				dto.setHashDigestSha1();
				user.setReset(true);
				user.setHash(dto.getHash());	
			}				
			
			Set<Role> roles = new HashSet<Role>();
			for (Role item   : dto.getRoles()) {
				
				Role role = new Role();
				role.setId(item.getId());
				role.setName(item.getName());
			
				roles.add(role);			
			}				
			
			user.setRoles(roles);
						
			user = repository.save(user);
								
			result.setEntity( new UserDto(user) );
			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Executado com sucesso.");			
		}
		else {
			result.setResultType( ResultMessageType.NO_DATA );
		}	
		
		return result;
	}

	public BasicResult<?> listOne(Long uid) {
		Result<UserDto> result = new Result<UserDto>();
		
		try {
			User user = repository.findOne(uid);

			if (user != null) {
				
				UserDto dto = new UserDto(user);
				
				result.setEntity(dto); ;				
				result.setResultType( ResultMessageType.SUCCESS );
				
			} else {
				
				result.setResultType( ResultMessageType.NO_DATA );
								
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setResultType( ResultMessageType.ERROR );									
		}	
		
		return result;
	}

	public BasicResult<?> updatePassword(UserPassDto userPassDto) {
		
		Result<UserDto> result = new Result<UserDto>(); 	
		
		try {
			User user = repository.findOne(userPassDto.getUserId());
			
			if (user != null) {
				
				if (!Pattern.matches("^[0-9a-zA-ZáéíóúàâêôãõüçÁÉÍÓÚÀÂÊÔÃÕÜÇöÖñÑ@. ]{6,10}$", userPassDto.getNewPassword())) {	                
					result.setResultType( ResultMessageType.ERROR );
	                return result;
	            }

	            if (!userPassDto.getNewPassword().equals(userPassDto.getConfirm())) {	                
	            	result.setResultType( ResultMessageType.ERROR );
	                return result;
	            }

	            if (user.getLogin() == null || !Pattern.matches("^[A-z][A-z0-9]*$", user.getLogin())) {	                
	            	result.setResultType( ResultMessageType.ERROR );
	                return result;
	            }
	            	            
	            Calendar calendar = Calendar.getInstance();
	            calendar.add(Calendar.DATE, 3);
	            
	            Map<String, Object> token = new HashMap<String, Object>();
	            
	            userPassDto.setConfirmdDigestSha1();
	            token.put("expire", calendar.getTime());
	            token.put("hash", userPassDto.getConfirm());            
	            
	            user.setReset(false);
				user.setHash(userPassDto.getConfirm());
				
				user = repository.save(user);
	            
				result.setEntity( new UserDto(user) );				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Senha Atualizada com Sucesso.");
			}
		
		} catch (Throwable throwable) {
			
			result.setIsError(true);
			result.setResultType( ResultMessageType.ERROR );
			result.setMessage(throwable.getMessage());	         
	    }
		
		return result;
	}

	public BasicResult<?> updateUserProfile(UserDto dto) {
		Result<UserDto> result = new Result<UserDto>(); 	
		
		User user = repository.findOne(dto.getId());
		
		if (user != null) {
			
			user.setLogin(dto.getLogin());
			user.setCpf(dto.getCpf());
			user.setDisplayName(dto.getDisplayName());
			user.setNickname(dto.getNickname());
			user.setFone(dto.getFone());
			user.setCell(dto.getCell());
			user.setEmail(dto.getEmail());		
			user.setCreateDate(new Date());
						
			user = repository.save(user);
								
			result.setEntity( new UserDto(user) );
			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Executado com sucesso.");			
		}
		else {
			result.setResultType( ResultMessageType.NO_DATA );
		}	
		
		return result;
	}
	

}
