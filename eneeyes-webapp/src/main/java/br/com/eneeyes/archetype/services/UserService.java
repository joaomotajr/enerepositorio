package br.com.eneeyes.archetype.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.dto.UserDto;
import br.com.eneeyes.archetype.model.User;
import br.com.eneeyes.archetype.repository.UserRepository;
import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;


	@Service
	public class UserService {

  	public BasicResult<?> save(UserDto userDto)
  	{
	  // TODO Auto-generated method stub
	  return null;
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
	

}
