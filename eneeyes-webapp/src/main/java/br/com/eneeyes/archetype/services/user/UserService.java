package br.com.eneeyes.archetype.services.user;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.dto.user.UserDto;
import br.com.eneeyes.archetype.dto.user.UserPassDto;
import br.com.eneeyes.archetype.model.acl.User;
import br.com.eneeyes.archetype.model.acl.UserRepository;
import br.com.eneeyes.archetype.result.UserPassResult;
import br.com.eneeyes.archetype.result.UserResult;
import br.com.eneeyes.archetype.web.result.ResultErrorMessage;
import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.archetype.web.result.ResultSuccessMessage;


	@Service
	public class UserService implements IUserService {
	
	@Override
  	public UserResult save(UserDto userDto)
  	{
	  // TODO Auto-generated method stub
	  return null;
  	}

	@Inject
	private UserRepository repository;
	
	@Override
	public UserResult findAll(UserDto userDto)
	{
		UserResult result = new UserResult();
				
		try {
			List<User> lista = repository.findAll();

			if (lista != null) {
				
				List<UserDto> dto = new ArrayList<UserDto>();
				
				for (User user   : lista) {					
					dto.add(new UserDto(user) );
				}
				
				result.setListUser(dto);				
				result.setResultType( ResultMessageType.SUCCESS );
				
			} else {
				
				result.setResultType( ResultMessageType.ERROR );
				result.getMessages().add(new ResultSuccessMessage("GENERIC_SUCESS_NO_DATA"));
				
			}
		} catch (Exception e) {
			result.setResultType( ResultMessageType.ERROR );
			result.getMessages().add(new ResultErrorMessage( e.getMessage() ));
						
		}	
		
		return result;		
	}
	
	
	@Override
  	public UserPassResult updatePassword(UserPassDto userPassDto)
  	{
	  // TODO Auto-generated method stub
	  return null;
  	}


	@Override
	public UserResult delete(UserDto userDto)
	{
	// TODO Auto-generated method stub
	  return null;
	}	

}
