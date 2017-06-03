package br.com.eneeyes.archetype.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.dto.UserDto;
import br.com.eneeyes.archetype.model.Role;
import br.com.eneeyes.archetype.model.User;
import br.com.eneeyes.archetype.repository.UserRepository;
import br.com.eneeyes.archetype.utils.MessageDigester;
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
		// TODO Auto-generated method stub
		return null;
	}

	public BasicResult<?> pesquisaUserByLogin(String login) {
		Result<UserDto> result = new Result<UserDto>();
		
		try {
			User user = repository.findByLogin(login);

			if (user != null) {
				
				UserDto dto = new UserDto();
				
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
				
		User user = repository.findById(dto.getId());
		
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
			
//			try {
//				if(user.getHash() != MessageDigester.digestSha1(dto.getHash())) {
//					user.setHash(MessageDigester.digestSha1(dto.getHash()));
//				}
//			} catch (Throwable e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
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
	

}
